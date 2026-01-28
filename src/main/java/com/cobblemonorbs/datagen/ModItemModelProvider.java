package com.cobblemonorbs.datagen;

import com.cobblemonorbs.CobblemonOrbs;
import com.cobblemonorbs.registry.ModItems;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Generates item models for all orbs.
 * Uses raw JSON output to bypass texture validation since textures will be added later.
 */
public class ModItemModelProvider implements DataProvider {
    
    private final PackOutput output;
    private final List<ModelEntry> models = new ArrayList<>();
    
    public ModItemModelProvider(PackOutput output) {
        this.output = output;
    }
    
    protected void registerModels() {
        // Crafting component items
        simpleItem(ModItems.ARCEUS_PAW, "arceus_paw");
        simpleItem(ModItems.ARCEUS_CROWN, "arceus_crown");
        simpleItem(ModItems.GREEN_GEM, "green_gem");
        
        // Special Orbs - use special textures (fallback to default)
        simpleOrbItem(ModItems.SHINY_ORB, "orb_shiny");
        simpleOrbItem(ModItems.ULTIMATE_ORB, "orb_ultimate");
        simpleOrbItem(ModItems.ULTIMATE_SHINY_ORB, "orb_ultimate_shiny");
        
        simpleOrbItem(ModItems.LEGENDARY_SHINY_ORB, "orb_legendary_shiny");
        simpleOrbItem(ModItems.LEGENDARY_ULTIMATE_ORB, "orb_legendary_ultimate");
        simpleOrbItem(ModItems.LEGENDARY_ULTIMATE_SHINY_ORB, "orb_legendary_ultimate_shiny");
        
        simpleOrbItem(ModItems.MYTHICAL_SHINY_ORB, "orb_mythical_shiny");
        simpleOrbItem(ModItems.MYTHICAL_ULTIMATE_ORB, "orb_mythical_ultimate");
        simpleOrbItem(ModItems.MYTHICAL_ULTIMATE_SHINY_ORB, "orb_mythical_ultimate_shiny");
        
        simpleOrbItem(ModItems.PARADOX_SHINY_ORB, "orb_paradox_shiny");
        simpleOrbItem(ModItems.PARADOX_ULTIMATE_ORB, "orb_paradox_ultimate");
        simpleOrbItem(ModItems.PARADOX_ULTIMATE_SHINY_ORB, "orb_paradox_ultimate_shiny");
        
        // Register all legendary orbs
        ModItems.getLegendaryOrbs().forEach((pokemonId, item) -> 
            simpleOrbItem(item, "orb_legendary"));
        
        // Register all mythical orbs
        ModItems.getMythicalOrbs().forEach((pokemonId, item) -> 
            simpleOrbItem(item, "orb_mythical"));
        
        // Register all paradox orbs
        ModItems.getParadoxOrbs().forEach((pokemonId, item) -> 
            simpleOrbItem(item, "orb_paradox"));
    }
    
    private void simpleItem(DeferredItem<Item> item, String textureName) {
        String itemPath = item.getId().getPath();
        models.add(new ModelEntry(itemPath, CobblemonOrbs.MOD_ID + ":item/" + textureName));
    }
    
    private void simpleOrbItem(DeferredItem<Item> item, String textureName) {
        String itemPath = item.getId().getPath();
        models.add(new ModelEntry(itemPath, CobblemonOrbs.MOD_ID + ":item/" + textureName));
    }
    
    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        registerModels();
        
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Path basePath = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK)
            .resolve(CobblemonOrbs.MOD_ID)
            .resolve("models")
            .resolve("item");
        
        for (ModelEntry entry : models) {
            JsonObject json = new JsonObject();
            json.addProperty("parent", "minecraft:item/generated");
            
            JsonObject textures = new JsonObject();
            textures.addProperty("layer0", entry.texture);
            json.add("textures", textures);
            
            Path modelPath = basePath.resolve(entry.name + ".json");
            futures.add(DataProvider.saveStable(cache, json, modelPath));
        }
        
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }
    
    @Override
    public String getName() {
        return "Item Models: " + CobblemonOrbs.MOD_ID;
    }
    
    private record ModelEntry(String name, String texture) {}
}
