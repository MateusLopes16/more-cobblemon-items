package com.cobblemonorbs.registry;

import com.cobblemonorbs.CobblemonOrbs;
import com.cobblemonorbs.config.ConfigurableRecipeCondition;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * Registers custom recipe conditions for the mod.
 */
public class ModConditions {
    
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITIONS =
            DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, CobblemonOrbs.MOD_ID);
    
    public static final Supplier<MapCodec<ConfigurableRecipeCondition>> CONFIGURABLE_RECIPE =
            CONDITIONS.register("configurable_recipe", () -> ConfigurableRecipeCondition.CODEC);
    
    public static void register(IEventBus modEventBus) {
        CONDITIONS.register(modEventBus);
    }
}
