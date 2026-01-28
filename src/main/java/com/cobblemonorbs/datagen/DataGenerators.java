package com.cobblemonorbs.datagen;

import com.cobblemonorbs.CobblemonOrbs;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

/**
 * Data generation event handler.
 */
@EventBusSubscriber(modid = CobblemonOrbs.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
        // Generate item models (uses custom provider that bypasses texture validation)
        generator.addProvider(event.includeClient(),
            new ModItemModelProvider(packOutput));
        
        // Generate recipes
        generator.addProvider(event.includeServer(),
            new ModRecipeProvider(packOutput, lookupProvider));
    }
}
