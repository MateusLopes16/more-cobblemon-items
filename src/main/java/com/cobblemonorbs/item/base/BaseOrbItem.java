package com.cobblemonorbs.item.base;

import com.cobblemonorbs.CobblemonOrbs;
import com.cobblemonorbs.item.api.IOrbItem;
import com.cobblemonorbs.item.api.OrbCategory;
import com.cobblemonorbs.item.api.SpawnSettings;
import com.cobblemonorbs.pokemon.PokemonSpawnHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Base class for all orb items. Provides common functionality for orb usage and tooltips.
 */
public abstract class BaseOrbItem extends Item implements IOrbItem {
    
    protected final String pokemonId;
    protected final OrbCategory category;
    protected final SpawnSettings spawnSettings;
    
    public BaseOrbItem(String pokemonId, OrbCategory category, SpawnSettings spawnSettings) {
        super(new Item.Properties()
            .stacksTo(16)
            .rarity(getRarityForCategory(category)));
        this.pokemonId = pokemonId;
        this.category = category;
        this.spawnSettings = spawnSettings;
    }
    
    public BaseOrbItem(String pokemonId, OrbCategory category) {
        this(pokemonId, category, SpawnSettings.DEFAULT);
    }
    
    private static Rarity getRarityForCategory(OrbCategory category) {
        return switch (category) {
            case LEGENDARY -> Rarity.EPIC;
            case MYTHICAL -> Rarity.RARE;
            case PARADOX -> Rarity.RARE;
            case SPECIAL -> Rarity.EPIC;
            case RANDOM -> Rarity.UNCOMMON;
        };
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        
        if (!level.isClientSide()) {
            if (onOrbUse(level, player, stack)) {
                // Consume the orb on successful use
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                
                // Play success sound
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.0F);
                
                return InteractionResultHolder.success(stack);
            } else {
                // Play fail sound
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.VILLAGER_NO, SoundSource.PLAYERS, 1.0F, 1.0F);
                
                return InteractionResultHolder.fail(stack);
            }
        }
        
        return InteractionResultHolder.consume(stack);
    }
    
    @Override
    public boolean onOrbUse(Level level, Player player, ItemStack stack) {
        String targetPokemon = getTargetPokemon();
        if (targetPokemon == null || targetPokemon.isEmpty()) {
            CobblemonOrbs.LOGGER.error("Failed to determine target Pokémon for orb: {}", pokemonId);
            return false;
        }
        
        return PokemonSpawnHandler.spawnPokemon(level, player, targetPokemon, spawnSettings);
    }
    
    /**
     * Gets the actual Pokémon to spawn. Override for random selection.
     */
    protected String getTargetPokemon() {
        return pokemonId;
    }
    
    @Override
    public String getPokemonIdentifier() {
        return pokemonId;
    }
    
    @Override
    public OrbCategory getCategory() {
        return category;
    }
    
    @Override
    public boolean isShinyOrb() {
        return spawnSettings.forceShiny();
    }
    
    @Override
    public boolean isPerfectIVOrb() {
        return spawnSettings.forcePerfectIVs();
    }
    
    public SpawnSettings getSpawnSettings() {
        return spawnSettings;
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        
        // Add category info
        tooltipComponents.add(Component.translatable("tooltip.cobblemonorbs.category", 
            category.getDisplayName()).withStyle(ChatFormatting.GRAY));
        
        // Add spawn level range info
        tooltipComponents.add(Component.translatable("tooltip.cobblemonorbs.level_range", 
            spawnSettings.minLevel(), spawnSettings.maxLevel()).withStyle(ChatFormatting.BLUE));
        
        if (spawnSettings.forceShiny()) {
            tooltipComponents.add(Component.translatable("tooltip.cobblemonorbs.shiny")
                .withStyle(ChatFormatting.GOLD));
        }
        
        if (spawnSettings.forcePerfectIVs()) {
            tooltipComponents.add(Component.translatable("tooltip.cobblemonorbs.perfect_ivs")
                .withStyle(ChatFormatting.LIGHT_PURPLE));
        }
        
        // Usage instruction
        tooltipComponents.add(Component.translatable("tooltip.cobblemonorbs.use_hint")
            .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }
    
    @Override
    public boolean isFoil(ItemStack stack) {
        return spawnSettings.forceShiny() || spawnSettings.forcePerfectIVs();
    }
}
