package com.cobblemonorbs;

import com.cobblemonorbs.config.ConfigFileManager;
import com.cobblemonorbs.config.OrbConfig;
import com.cobblemonorbs.config.RecipeConfigGenerator;
import com.cobblemonorbs.registry.ModConditions;
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

import java.util.ArrayList;

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
        
        // Register recipe conditions
        ModConditions.register(modEventBus);
        
        // Register server events
        NeoForge.EVENT_BUS.register(this);
        
        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, OrbConfig.SPEC);
        
        LOGGER.info("Cobblemon Legendary Orbs initialized!");
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        // Initialize config file manager (creates config folder structure)
        event.enqueueWork(() -> {
            ConfigFileManager.initialize();
            
            // Generate item configs for all registered items
            var itemIds = new ArrayList<String>();
            ModItems.getLegendaryOrbs().forEach((id, item) -> itemIds.add(item.getId().getPath()));
            ModItems.getMythicalOrbs().forEach((id, item) -> itemIds.add(item.getId().getPath()));
            ModItems.getParadoxOrbs().forEach((id, item) -> itemIds.add(item.getId().getPath()));
            itemIds.add("arceus_orb");
            itemIds.add("arceus_paw");
            itemIds.add("arceus_crown");
            itemIds.add("green_gem");
            itemIds.add("random_orb");
            itemIds.add("legendary_orb");
            itemIds.add("mythical_orb");
            itemIds.add("paradox_orb");
            itemIds.add("shiny_orb");
            itemIds.add("ultimate_orb");
            itemIds.add("ultimate_shiny_orb");
            itemIds.add("legendary_shiny_orb");
            itemIds.add("legendary_ultimate_orb");
            itemIds.add("legendary_ultimate_shiny_orb");
            itemIds.add("mythical_shiny_orb");
            itemIds.add("mythical_ultimate_orb");
            itemIds.add("mythical_ultimate_shiny_orb");
            itemIds.add("paradox_shiny_orb");
            itemIds.add("paradox_ultimate_orb");
            itemIds.add("paradox_ultimate_shiny_orb");
            
            ConfigFileManager.generateItemConfigs(itemIds);
            
            // Export recipes to config folder for customization
            RecipeConfigGenerator.exportRecipesToConfig();
        });
        
        LOGGER.info("Cobblemon Legendary Orbs common setup complete!");
    }
    
    @net.neoforged.bus.api.SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Cobblemon Legendary Orbs server starting!");
    }
}
