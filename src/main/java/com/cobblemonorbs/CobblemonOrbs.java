package com.cobblemonorbs;

import com.cobblemonorbs.config.OrbConfig;
import com.cobblemonorbs.registry.ModCreativeTabs;
import com.cobblemonorbs.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

/**
 * Main mod class for Cobblemon Legendary Orbs.
 * This mod adds spawn orbs for Pokémon that can be consumed to spawn the corresponding Pokémon.
 */
@Mod(CobblemonOrbs.MOD_ID)
public class CobblemonOrbs {
    
    public static final String MOD_ID = "cobblemonorbs";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public CobblemonOrbs(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing Cobblemon Legendary Orbs...");
        
        // Register the common setup method
        modEventBus.addListener(this::commonSetup);
        
        // Register all items
        ModItems.register(modEventBus);
        
        // Register creative tabs
        ModCreativeTabs.register(modEventBus);
        
        // Register server events
        NeoForge.EVENT_BUS.register(this);
        
        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, OrbConfig.SPEC);
        
        LOGGER.info("Cobblemon Legendary Orbs initialized!");
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Cobblemon Legendary Orbs common setup complete!");
    }
    
    @net.neoforged.bus.api.SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Cobblemon Legendary Orbs server starting!");
    }
}
