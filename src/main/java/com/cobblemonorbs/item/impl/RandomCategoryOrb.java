package com.cobblemonorbs.item.impl;

import com.cobblemonorbs.data.PokemonDataManager;
import com.cobblemonorbs.item.api.OrbCategory;
import com.cobblemonorbs.item.api.SpawnSettings;
import com.cobblemonorbs.item.base.BaseOrbItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Random;

/**
 * An orb that spawns a random Pokémon from a specified category.
 * Categories include: all legendaries/mythicals/paradox, only legendaries, only mythicals, only paradox.
 */
public class RandomCategoryOrb extends BaseOrbItem {
    
    private static final Random RANDOM = new Random();
    
    public enum SelectionCategory {
        ALL("all", "All Legendary/Mythical/Paradox"),
        LEGENDARY_ONLY("legendary", "Legendary"),
        MYTHICAL_ONLY("mythical", "Mythical"),
        PARADOX_ONLY("paradox", "Paradox");
        
        private final String id;
        private final String displayName;
        
        SelectionCategory(String id, String displayName) {
            this.id = id;
            this.displayName = displayName;
        }
        
        public String getId() {
            return id;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    private final SelectionCategory selectionCategory;
    
    public RandomCategoryOrb(SelectionCategory selectionCategory, SpawnSettings settings) {
        super(selectionCategory.getId() + "_random", OrbCategory.RANDOM, settings);
        this.selectionCategory = selectionCategory;
    }
    
    @Override
    protected String getTargetPokemon() {
        List<String> candidates = switch (selectionCategory) {
            case ALL -> PokemonDataManager.getAllSpecialPokemon();
            case LEGENDARY_ONLY -> PokemonDataManager.getLegendaryPokemon();
            case MYTHICAL_ONLY -> PokemonDataManager.getMythicalPokemon();
            case PARADOX_ONLY -> PokemonDataManager.getParadoxPokemon();
        };
        
        if (candidates.isEmpty()) {
            return "pikachu"; // Fallback
        }
        
        return candidates.get(RANDOM.nextInt(candidates.size()));
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        // Add random selection info before calling super
        tooltipComponents.add(Component.translatable("tooltip.cobblemonorbs.random", 
            selectionCategory.getDisplayName()).withStyle(ChatFormatting.YELLOW));
        
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
    
    public SelectionCategory getSelectionCategory() {
        return selectionCategory;
    }
}
