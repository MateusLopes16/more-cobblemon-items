package com.cobblemonorbs.config;

import com.cobblemonorbs.CobblemonOrbs;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.neoforged.neoforge.common.conditions.ICondition;

/**
 * Recipe condition that checks if an item is craftable via config.
 */
public record ConfigurableRecipeCondition(String itemId) implements ICondition {
    
    public static final MapCodec<ConfigurableRecipeCondition> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            com.mojang.serialization.Codec.STRING.fieldOf("item").forGetter(ConfigurableRecipeCondition::itemId)
        ).apply(instance, ConfigurableRecipeCondition::new)
    );
    
    @Override
    public boolean test(ICondition.IContext context) {
        // Check if the item is craftable via config
        boolean craftable = ConfigFileManager.isItemCraftable(itemId);
        if (!craftable) {
            CobblemonOrbs.LOGGER.debug("Recipe for {} disabled by config", itemId);
        }
        return craftable;
    }
    
    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
    
    @Override
    public String toString() {
        return "configurable_recipe(\"" + itemId + "\")";
    }
}
