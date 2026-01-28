package com.cobblemonorbs.item.api;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Interface for all orb items that can be consumed to spawn Pokémon.
 * Implementations should define the specific behavior for spawning.
 */
public interface IOrbItem {
    
    /**
     * Called when the orb is used by a player.
     * 
     * @param level The world/level
     * @param player The player using the orb
     * @param stack The item stack being used
     * @return true if the orb was successfully used
     */
    boolean onOrbUse(Level level, Player player, ItemStack stack);
    
    /**
     * Get the name of the Pokémon this orb spawns.
     * For random orbs, this may return a category name.
     * 
     * @return The Pokémon name or category
     */
    String getPokemonIdentifier();
    
    /**
     * Get the category of this orb (e.g., "legendary", "mythical", "paradox").
     * 
     * @return The orb category
     */
    OrbCategory getCategory();
    
    /**
     * Whether this orb spawns a shiny Pokémon.
     * 
     * @return true if guaranteed shiny
     */
    boolean isShinyOrb();
    
    /**
     * Whether this orb spawns a Pokémon with perfect IVs.
     * 
     * @return true if guaranteed perfect IVs
     */
    boolean isPerfectIVOrb();
}
