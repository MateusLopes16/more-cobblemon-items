package com.cobblemonorbs.pokemon;

import com.cobblemonorbs.CobblemonOrbs;
import com.cobblemonorbs.item.api.SpawnSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

/**
 * Handles the spawning of Pokémon from orbs using the Cobblemon API.
 * 
 * Note: Due to mapping differences between Cobblemon (Yarn/intermediary) and 
 * NeoForge (Mojang mappings), direct Cobblemon API calls need to be done
 * through reflection or a separate compatibility layer at runtime.
 */
public class PokemonSpawnHandler {
    
    private static final Random RANDOM = new Random();
    
    /**
     * Spawns a Pokémon near the player with the given settings.
     *
     * @param level The world/level
     * @param player The player spawning the Pokémon
     * @param pokemonId The Pokémon species identifier (e.g., "mewtwo", "mew")
     * @param settings The spawn settings to use
     * @return true if the spawn was successful
     */
    public static boolean spawnPokemon(Level level, Player player, String pokemonId, SpawnSettings settings) {
        if (level.isClientSide() || !(level instanceof ServerLevel serverLevel)) {
            return false;
        }
        
        try {
            // Calculate spawn position
            Vec3 spawnPos = calculateSpawnPosition(player, settings.spawnRadius());
            
            // Get random level from settings
            int spawnLevel = settings.getRandomLevel();
            
            // Use reflection to call Cobblemon API to avoid mapping issues
            boolean success = spawnPokemonViaReflection(serverLevel, spawnPos, pokemonId, spawnLevel, settings);
            
            if (success) {
                String pokemonName = formatPokemonName(pokemonId);
                if (settings.forceShiny()) {
                    player.sendSystemMessage(Component.translatable("message.cobblemonorbs.spawned_shiny", pokemonName, spawnLevel));
                } else {
                    player.sendSystemMessage(Component.translatable("message.cobblemonorbs.spawned", pokemonName, spawnLevel));
                }
                
                CobblemonOrbs.LOGGER.info("Player {} spawned {} (Lv. {}) at {}", 
                    player.getName().getString(), pokemonName, spawnLevel, spawnPos);
                
                return true;
            }
            
            player.sendSystemMessage(Component.translatable("message.cobblemonorbs.spawn_failed"));
            return false;
            
        } catch (Exception e) {
            CobblemonOrbs.LOGGER.error("Failed to spawn Pokémon: {}", pokemonId, e);
            player.sendSystemMessage(Component.translatable("message.cobblemonorbs.spawn_failed"));
            return false;
        }
    }
    
    /**
     * Spawns a Pokémon using reflection to avoid compile-time mapping issues.
     */
    private static boolean spawnPokemonViaReflection(ServerLevel serverLevel, Vec3 spawnPos, 
            String pokemonId, int level, SpawnSettings settings) {
        try {
            // Get PokemonSpecies class
            Class<?> pokemonSpeciesClass = Class.forName("com.cobblemon.mod.common.api.pokemon.PokemonSpecies");
            Object speciesInstance = pokemonSpeciesClass.getField("INSTANCE").get(null);
            
            // Get the species by name
            var getByNameMethod = pokemonSpeciesClass.getMethod("getByName", String.class);
            Object species = getByNameMethod.invoke(speciesInstance, pokemonId.toLowerCase());
            
            if (species == null) {
                CobblemonOrbs.LOGGER.error("Unknown Pokémon species: {}", pokemonId);
                return false;
            }
            
            // Create the Pokemon instance
            Class<?> speciesClass = Class.forName("com.cobblemon.mod.common.pokemon.Species");
            var createMethod = speciesClass.getMethod("create", int.class);
            Object pokemon = createMethod.invoke(species, level);
            
            // Apply shiny status
            Class<?> pokemonClass = Class.forName("com.cobblemon.mod.common.pokemon.Pokemon");
            if (settings.forceShiny() || RANDOM.nextDouble() < settings.shinyChance()) {
                var setShinyMethod = pokemonClass.getMethod("setShiny", boolean.class);
                setShinyMethod.invoke(pokemon, true);
            }
            
            // Apply perfect IVs if configured
            if (settings.forcePerfectIVs()) {
                setPerfectIVsViaReflection(pokemon, pokemonClass);
            }
            
            // Get PokemonEntity class and create instance
            // Constructor signature: PokemonEntity(Level, Pokemon, EntityType)
            // We need to use the Level class (parent of ServerLevel) and get the entity type
            Class<?> pokemonEntityClass = Class.forName("com.cobblemon.mod.common.entity.pokemon.PokemonEntity");
            Class<?> levelClass = Class.forName("net.minecraft.world.level.Level");
            Class<?> entityTypeClass = Class.forName("net.minecraft.world.entity.EntityType");
            
            // Get CobblemonEntities.POKEMON entity type
            Class<?> cobblemonEntitiesClass = Class.forName("com.cobblemon.mod.common.CobblemonEntities");
            Object pokemonEntityType = cobblemonEntitiesClass.getField("POKEMON").get(null);
            
            var entityConstructor = pokemonEntityClass.getConstructor(levelClass, pokemonClass, entityTypeClass);
            Object pokemonEntity = entityConstructor.newInstance(serverLevel, pokemon, pokemonEntityType);
            
            // Set position
            var setPosMethod = pokemonEntityClass.getMethod("setPos", double.class, double.class, double.class);
            setPosMethod.invoke(pokemonEntity, spawnPos.x, spawnPos.y, spawnPos.z);
            
            // Add to world
            var addEntityMethod = serverLevel.getClass().getMethod("addFreshEntity", 
                Class.forName("net.minecraft.world.entity.Entity"));
            addEntityMethod.invoke(serverLevel, pokemonEntity);
            
            return true;
            
        } catch (ClassNotFoundException e) {
            CobblemonOrbs.LOGGER.error("Cobblemon not found! Make sure Cobblemon is installed.", e);
            return false;
        } catch (Exception e) {
            CobblemonOrbs.LOGGER.error("Error spawning Pokémon via reflection", e);
            return false;
        }
    }
    
    /**
     * Sets all IVs to 31 (perfect) using reflection.
     */
    private static void setPerfectIVsViaReflection(Object pokemon, Class<?> pokemonClass) {
        try {
            Class<?> statsClass = Class.forName("com.cobblemon.mod.common.api.pokemon.stats.Stats");
            var setIVMethod = pokemonClass.getMethod("setIV", 
                Class.forName("com.cobblemon.mod.common.api.pokemon.stats.Stat"), int.class);
            
            // Get each stat and set IV to 31
            String[] statNames = {"HP", "ATTACK", "DEFENCE", "SPECIAL_ATTACK", "SPECIAL_DEFENCE", "SPEED"};
            for (String statName : statNames) {
                Object stat = statsClass.getField(statName).get(null);
                setIVMethod.invoke(pokemon, stat, 31);
            }
        } catch (Exception e) {
            CobblemonOrbs.LOGGER.error("Error setting perfect IVs", e);
        }
    }
    
    /**
     * Formats a Pokémon ID into a display name.
     */
    private static String formatPokemonName(String pokemonId) {
        if (pokemonId == null || pokemonId.isEmpty()) {
            return pokemonId;
        }
        // Capitalize first letter and replace underscores with spaces
        String formatted = pokemonId.replace("_", " ");
        return Character.toUpperCase(formatted.charAt(0)) + formatted.substring(1);
    }
    
    /**
     * Calculates a spawn position around the player within the specified radius.
     */
    private static Vec3 calculateSpawnPosition(Player player, int radius) {
        double angle = RANDOM.nextDouble() * 2 * Math.PI;
        double distance = RANDOM.nextDouble() * radius + 1;
        
        double x = player.getX() + Math.cos(angle) * distance;
        double z = player.getZ() + Math.sin(angle) * distance;
        double y = player.getY();
        
        // Try to find a safe Y position
        Level level = player.level();
        BlockPos checkPos = BlockPos.containing(x, y, z);
        
        // Check if the position is valid (not inside a block)
        for (int i = 0; i < 5; i++) {
            if (!level.getBlockState(checkPos.above(i)).isSolid() && 
                !level.getBlockState(checkPos.above(i + 1)).isSolid()) {
                y = checkPos.getY() + i;
                break;
            }
        }
        
        return new Vec3(x, y, z);
    }
}
