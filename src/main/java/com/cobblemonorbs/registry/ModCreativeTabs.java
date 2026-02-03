package com.cobblemonorbs.registry;

import com.cobblemonorbs.CobblemonOrbs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Handles registration of creative mode tabs.
 */
public class ModCreativeTabs {
    
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CobblemonOrbs.MOD_ID);
    
    // Main Creative Tab for all orbs
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ORBS_TAB = CREATIVE_TABS.register("orbs_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.cobblemonorbs"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> ModItems.MEW_ORB.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                // Crafting Components (for Arceus Orb)
                output.accept(ModItems.ARCEUS_PAW.get());
                output.accept(ModItems.ARCEUS_CROWN.get());
                output.accept(ModItems.GREEN_GEM.get());
                
                // Classic Random Orbs (normal random stats)
                output.accept(ModItems.RANDOM_ORB.get());
                output.accept(ModItems.LEGENDARY_ORB.get());
                output.accept(ModItems.MYTHICAL_ORB.get());
                output.accept(ModItems.PARADOX_ORB.get());
                
                // Special/Random Orbs
                output.accept(ModItems.SHINY_ORB.get());
                output.accept(ModItems.ULTIMATE_ORB.get());
                output.accept(ModItems.ULTIMATE_SHINY_ORB.get());
                
                // Category-specific random orbs
                output.accept(ModItems.LEGENDARY_SHINY_ORB.get());
                output.accept(ModItems.LEGENDARY_ULTIMATE_ORB.get());
                output.accept(ModItems.LEGENDARY_ULTIMATE_SHINY_ORB.get());
                
                output.accept(ModItems.MYTHICAL_SHINY_ORB.get());
                output.accept(ModItems.MYTHICAL_ULTIMATE_ORB.get());
                output.accept(ModItems.MYTHICAL_ULTIMATE_SHINY_ORB.get());
                
                output.accept(ModItems.PARADOX_SHINY_ORB.get());
                output.accept(ModItems.PARADOX_ULTIMATE_ORB.get());
                output.accept(ModItems.PARADOX_ULTIMATE_SHINY_ORB.get());
                
                // All individual legendary orbs
                ModItems.getLegendaryOrbs().values().forEach(item -> output.accept(item.get()));
                
                // All individual mythical orbs
                ModItems.getMythicalOrbs().values().forEach(item -> output.accept(item.get()));
                
                // All individual paradox orbs
                ModItems.getParadoxOrbs().values().forEach(item -> output.accept(item.get()));
            })
            .build());
    
    /**
     * Register creative tabs to the event bus.
     */
    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
