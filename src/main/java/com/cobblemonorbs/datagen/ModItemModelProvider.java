package com.cobblemonorbs.datagen;

import com.cobblemonorbs.CobblemonOrbs;
import com.cobblemonorbs.registry.ModItems;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Generates item models for all orbs.
 * Uses raw JSON output to bypass texture validation since textures will be added later.
 */
public class ModItemModelProvider implements DataProvider {
    
    private final PackOutput output;
    private final List<ModelEntry> models = new ArrayList<>();
    
    public ModItemModelProvider(PackOutput output) {
        this.output = output;
    }
    
    protected void registerModels() {
        // Crafting component items
        simpleItem(ModItems.ARCEUS_PAW, "arceus_paw");
        simpleItem(ModItems.ARCEUS_CROWN, "arceus_crown");
        simpleItem(ModItems.GREEN_GEM, "green_gem");
        
        // Classic Orbs - random from category with normal stats
        simpleOrbItem(ModItems.RANDOM_ORB, "orb_random");
        simpleOrbItem(ModItems.LEGENDARY_ORB, "orb_legendary");
        simpleOrbItem(ModItems.MYTHICAL_ORB, "orb_mythical");
        simpleOrbItem(ModItems.PARADOX_ORB, "orb_paradox");
        
        // Special Orbs - use special textures (fallback to default)
        simpleOrbItem(ModItems.SHINY_ORB, "orb_shiny");
        simpleOrbItem(ModItems.ULTIMATE_ORB, "orb_ultimate");
        simpleOrbItem(ModItems.ULTIMATE_SHINY_ORB, "orb_ultimate_shiny");
        
        simpleOrbItem(ModItems.LEGENDARY_SHINY_ORB, "orb_legendary_shiny");
        simpleOrbItem(ModItems.LEGENDARY_ULTIMATE_ORB, "orb_legendary_ultimate");
        simpleOrbItem(ModItems.LEGENDARY_ULTIMATE_SHINY_ORB, "orb_legendary_ultimate_shiny");
        
        simpleOrbItem(ModItems.MYTHICAL_SHINY_ORB, "orb_mythical_shiny");
        simpleOrbItem(ModItems.MYTHICAL_ULTIMATE_ORB, "orb_mythical_ultimate");
        simpleOrbItem(ModItems.MYTHICAL_ULTIMATE_SHINY_ORB, "orb_mythical_ultimate_shiny");
        
        simpleOrbItem(ModItems.PARADOX_SHINY_ORB, "orb_paradox_shiny");
        simpleOrbItem(ModItems.PARADOX_ULTIMATE_ORB, "orb_paradox_ultimate");
        simpleOrbItem(ModItems.PARADOX_ULTIMATE_SHINY_ORB, "orb_paradox_ultimate_shiny");
        
        // Register all legendary orbs - use individual textures if available
        ModItems.getLegendaryOrbs().forEach((pokemonId, item) -> {
            String textureName = getTextureNameForPokemon(pokemonId);
            simpleOrbItem(item, textureName);
        });
        
        // Register all mythical orbs - use individual textures if available
        ModItems.getMythicalOrbs().forEach((pokemonId, item) -> {
            String textureName = getTextureNameForPokemon(pokemonId);
            simpleOrbItem(item, textureName);
        });
        
        // Register all paradox orbs - use individual textures if available
        ModItems.getParadoxOrbs().forEach((pokemonId, item) -> {
            String textureName = getTextureNameForPokemon(pokemonId);
            simpleOrbItem(item, textureName);
        });
    }
    
    /**
     * Maps Pokémon IDs to their texture file names.
     * Some textures have different names than the Pokémon ID (e.g., ho-oh -> hoho).
     * Falls back to orb_default if no specific texture exists.
     */
    private String getTextureNameForPokemon(String pokemonId) {
        // Map of pokemonId -> texture name for textures with different names
        return switch (pokemonId) {
            case "ho-oh" -> "hoho";
            case "moltres" -> "sulfura";
            case "cobalion" -> "cobaltium";
            case "terrakion" -> "terrakium";
            case "virizion" -> "viridium";
            case "thundurus" -> "fulguris";
            case "landorus" -> "boreas";
            case "type-null" -> "type0";
            case "silvally" -> "silvalie";
            case "tapukoko" -> "tokorico";
            case "tapulele" -> "tokopiyon";
            case "tapubulu" -> "tokotoro";
            case "tapufini" -> "tokopisco";
            case "cosmoem" -> "cosmovoum";
            case "eternatus" -> "ethernatos";
            case "urshifu" -> "shifours";
            case "kubfu" -> "wushours";
            case "enamorus" -> "enamorus";
            // These have matching texture names
            case "articuno", "zapdos", "mewtwo", "raikou", "entei", "suicune", "lugia",
                 "regirock", "regice", "registeel", "latias", "latios", "kyogre", "groudon", "rayquaza",
                 "uxie", "mesprit", "azelf", "dialga", "palkia", "heatran", "regigigas", "giratina", "cresselia",
                 "reshiram", "zekrom", "kyurem", "tornadus",
                 "xerneas", "yveltal", "zygarde", "cosmog", "solgaleo", "lunala", "necrozma",
                 "zacian", "zamazenta", "regieleki", "regidrago",
                 // Mythical Pokémon with matching texture names
                 "mew", "celebi", "jirachi", "deoxys", "darkrai" -> pokemonId;
            // Default fallback for textures not yet created
            // add a case for all paradow orbs
            case "orb_paradox_shiny", "orb_paradox_ultimate", "orb_paradox_ultimate_shiny", "orb_paradox" -> "orb_paradox";
            case "orb_mythical_shiny", "orb_mythical_ultimate", "orb_mythical_ultimate_shiny", "orb_mythical" -> "orb_mythical";
            case "orb_legendary_shiny", "orb_legendary_ultimate", "orb_legendary_ultimate_shiny", "orb_legendary" -> "orb_legendary";
            default -> "orb_default";
        };
    }
    
    private void simpleItem(DeferredItem<Item> item, String textureName) {
        String itemPath = item.getId().getPath();
        models.add(new ModelEntry(itemPath, CobblemonOrbs.MOD_ID + ":item/" + textureName));
    }
    
    private void simpleOrbItem(DeferredItem<Item> item, String textureName) {
        String itemPath = item.getId().getPath();
        models.add(new ModelEntry(itemPath, CobblemonOrbs.MOD_ID + ":item/" + textureName));
    }
    
    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        registerModels();
        
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Path basePath = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK)
            .resolve(CobblemonOrbs.MOD_ID)
            .resolve("models")
            .resolve("item");
        
        for (ModelEntry entry : models) {
            JsonObject json = new JsonObject();
            json.addProperty("parent", "minecraft:item/generated");
            
            JsonObject textures = new JsonObject();
            textures.addProperty("layer0", entry.texture);
            json.add("textures", textures);
            
            Path modelPath = basePath.resolve(entry.name + ".json");
            futures.add(DataProvider.saveStable(cache, json, modelPath));
        }
        
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }
    
    @Override
    public String getName() {
        return "Item Models: " + CobblemonOrbs.MOD_ID;
    }
    
    private record ModelEntry(String name, String texture) {}
}
