package com.cobblemonorbs.item.impl;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

/**
 * Simple crafting component item used for creating orbs.
 * These items have no special functionality on their own.
 */
public class CraftingComponentItem extends Item {
    
    public CraftingComponentItem(Rarity rarity) {
        super(new Item.Properties()
            .stacksTo(64)
            .rarity(rarity));
    }
    
    public CraftingComponentItem() {
        this(Rarity.RARE);
    }
}
