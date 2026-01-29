package com.cobblemonorbs.config;

import com.cobblemonorbs.CobblemonOrbs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.neoforged.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Manages configuration files for recipes and items.
 * Creates editable JSON files in the config folder that server admins can modify.
 */
public class ConfigFileManager {
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve(CobblemonOrbs.MOD_ID);
    private static final Path RECIPES_DIR = CONFIG_DIR.resolve("recipes");
    private static final Path ITEMS_DIR = CONFIG_DIR.resolve("items");
    
    // Cache of disabled recipes
    private static final Set<String> DISABLED_RECIPES = new HashSet<>();
    private static final Map<String, JsonObject> CUSTOM_RECIPES = new HashMap<>();
    
    /**
     * Initialize config directories and files.
     * Should be called during mod initialization.
     */
    public static void initialize() {
        try {
            // Create directories
            Files.createDirectories(CONFIG_DIR);
            Files.createDirectories(RECIPES_DIR);
            Files.createDirectories(ITEMS_DIR);
            
            // Create README file
            createReadme();
            
            // Load existing configs
            loadRecipeConfigs();
            
            CobblemonOrbs.LOGGER.info("Config file manager initialized at: {}", CONFIG_DIR);
        } catch (IOException e) {
            CobblemonOrbs.LOGGER.error("Failed to initialize config directories", e);
        }
    }
    
    /**
     * Generate default recipe config files for all items.
     * This creates editable JSON files that mirror the mod's recipes.
     */
    public static void generateDefaultRecipeConfigs(Map<String, JsonObject> recipes) {
        for (Map.Entry<String, JsonObject> entry : recipes.entrySet()) {
            String recipeName = entry.getKey();
            JsonObject recipe = entry.getValue();
            
            Path recipeFile = RECIPES_DIR.resolve(recipeName + ".json");
            
            // Only create if it doesn't exist (don't overwrite user edits)
            if (!Files.exists(recipeFile)) {
                try {
                    // Add an "enabled" field to control if the recipe is active
                    JsonObject configRecipe = recipe.deepCopy();
                    configRecipe.addProperty("_enabled", true);
                    configRecipe.addProperty("_comment", "Set _enabled to false to disable this recipe");
                    
                    Files.writeString(recipeFile, GSON.toJson(configRecipe));
                    CobblemonOrbs.LOGGER.debug("Created recipe config: {}", recipeName);
                } catch (IOException e) {
                    CobblemonOrbs.LOGGER.error("Failed to create recipe config: {}", recipeName, e);
                }
            }
        }
    }
    
    /**
     * Generate item config files for enabling/disabling items and customizing properties.
     */
    public static void generateItemConfigs(List<String> itemIds) {
        for (String itemId : itemIds) {
            Path itemFile = ITEMS_DIR.resolve(itemId + ".json");
            
            if (!Files.exists(itemFile)) {
                try {
                    JsonObject itemConfig = new JsonObject();
                    itemConfig.addProperty("_comment", "Configuration for " + itemId);
                    itemConfig.addProperty("enabled", true);
                    itemConfig.addProperty("craftable", true);
                    
                    // Add spawn settings for orbs
                    if (itemId.endsWith("_orb")) {
                        JsonObject spawnSettings = new JsonObject();
                        spawnSettings.addProperty("_comment", "Leave as -1 to use global defaults");
                        spawnSettings.addProperty("level", -1);
                        spawnSettings.addProperty("shinyChance", -1.0);
                        spawnSettings.addProperty("guaranteeShiny", false);
                        spawnSettings.addProperty("perfectIVs", false);
                        itemConfig.add("spawnSettings", spawnSettings);
                    }
                    
                    Files.writeString(itemFile, GSON.toJson(itemConfig));
                    CobblemonOrbs.LOGGER.debug("Created item config: {}", itemId);
                } catch (IOException e) {
                    CobblemonOrbs.LOGGER.error("Failed to create item config: {}", itemId, e);
                }
            }
        }
    }
    
    /**
     * Load recipe configurations and determine which are disabled.
     */
    private static void loadRecipeConfigs() {
        DISABLED_RECIPES.clear();
        CUSTOM_RECIPES.clear();
        
        try {
            if (Files.exists(RECIPES_DIR)) {
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(RECIPES_DIR, "*.json")) {
                    for (Path file : stream) {
                        try {
                            String content = Files.readString(file);
                            JsonObject json = JsonParser.parseString(content).getAsJsonObject();
                            
                            String recipeName = file.getFileName().toString().replace(".json", "");
                            
                            // Check if recipe is disabled
                            if (json.has("_enabled") && !json.get("_enabled").getAsBoolean()) {
                                DISABLED_RECIPES.add(recipeName);
                                CobblemonOrbs.LOGGER.info("Recipe disabled by config: {}", recipeName);
                            } else {
                                // Store custom recipe (removing our config fields)
                                JsonObject cleanRecipe = json.deepCopy();
                                cleanRecipe.remove("_enabled");
                                cleanRecipe.remove("_comment");
                                CUSTOM_RECIPES.put(recipeName, cleanRecipe);
                            }
                        } catch (Exception e) {
                            CobblemonOrbs.LOGGER.error("Failed to load recipe config: {}", file, e);
                        }
                    }
                }
            }
        } catch (IOException e) {
            CobblemonOrbs.LOGGER.error("Failed to load recipe configs", e);
        }
        
        CobblemonOrbs.LOGGER.info("Loaded {} custom recipes, {} disabled recipes", 
            CUSTOM_RECIPES.size(), DISABLED_RECIPES.size());
    }
    
    /**
     * Check if a recipe is disabled by config.
     */
    public static boolean isRecipeDisabled(String recipeName) {
        return DISABLED_RECIPES.contains(recipeName);
    }
    
    /**
     * Get custom recipe if modified, otherwise return null.
     */
    public static JsonObject getCustomRecipe(String recipeName) {
        return CUSTOM_RECIPES.get(recipeName);
    }
    
    /**
     * Check if an item is enabled.
     */
    public static boolean isItemEnabled(String itemId) {
        Path itemFile = ITEMS_DIR.resolve(itemId + ".json");
        if (Files.exists(itemFile)) {
            try {
                String content = Files.readString(itemFile);
                JsonObject json = JsonParser.parseString(content).getAsJsonObject();
                if (json.has("enabled")) {
                    return json.get("enabled").getAsBoolean();
                }
            } catch (Exception e) {
                CobblemonOrbs.LOGGER.error("Failed to read item config: {}", itemId, e);
            }
        }
        return true; // Default to enabled
    }
    
    /**
     * Check if an item is craftable.
     */
    public static boolean isItemCraftable(String itemId) {
        Path itemFile = ITEMS_DIR.resolve(itemId + ".json");
        if (Files.exists(itemFile)) {
            try {
                String content = Files.readString(itemFile);
                JsonObject json = JsonParser.parseString(content).getAsJsonObject();
                if (json.has("craftable")) {
                    return json.get("craftable").getAsBoolean();
                }
            } catch (Exception e) {
                CobblemonOrbs.LOGGER.error("Failed to read item config: {}", itemId, e);
            }
        }
        return true; // Default to craftable
    }
    
    /**
     * Get item-specific spawn settings.
     * Returns null if not configured (use defaults).
     */
    public static ItemSpawnSettings getItemSpawnSettings(String itemId) {
        Path itemFile = ITEMS_DIR.resolve(itemId + ".json");
        if (Files.exists(itemFile)) {
            try {
                String content = Files.readString(itemFile);
                JsonObject json = JsonParser.parseString(content).getAsJsonObject();
                if (json.has("spawnSettings")) {
                    JsonObject settings = json.getAsJsonObject("spawnSettings");
                    return new ItemSpawnSettings(
                        settings.has("level") ? settings.get("level").getAsInt() : -1,
                        settings.has("shinyChance") ? settings.get("shinyChance").getAsDouble() : -1.0,
                        settings.has("guaranteeShiny") && settings.get("guaranteeShiny").getAsBoolean(),
                        settings.has("perfectIVs") && settings.get("perfectIVs").getAsBoolean()
                    );
                }
            } catch (Exception e) {
                CobblemonOrbs.LOGGER.error("Failed to read item spawn settings: {}", itemId, e);
            }
        }
        return null;
    }
    
    /**
     * Reload all configurations from disk.
     */
    public static void reload() {
        loadRecipeConfigs();
        CobblemonOrbs.LOGGER.info("Reloaded config files");
    }
    
    /**
     * Create a README file explaining how to use the config system.
     */
    private static void createReadme() {
        Path readmeFile = CONFIG_DIR.resolve("README.txt");
        if (!Files.exists(readmeFile)) {
            try {
                String content = """
                    ========================================
                    Cobblemon Legendary Orbs - Config Guide
                    ========================================
                    
                    This folder contains configuration files for customizing the mod.
                    
                    FOLDERS:
                    --------
                    
                    /recipes/
                        Contains JSON files for each craftable item's recipe.
                        - Set "_enabled": false to disable a recipe
                        - Modify the recipe ingredients and pattern to customize
                    
                    /items/
                        Contains JSON files for each item's settings.
                        - "enabled": false - Completely disable the item
                        - "craftable": false - Disable only the recipe (item still exists)
                        - "spawnSettings" - Override spawn settings for specific orbs:
                            - "level": Set specific level (or -1 for default)
                            - "shinyChance": Set shiny chance (or -1 for default)
                            - "guaranteeShiny": true/false
                            - "perfectIVs": true/false
                    
                    EXAMPLES:
                    ---------
                    
                    To disable Mewtwo orb recipe:
                    1. Open /items/mewtwo_orb.json
                    2. Set "craftable": false
                    
                    To make Arceus orb always spawn shiny with perfect IVs:
                    1. Open /items/arceus_orb.json
                    2. Set "spawnSettings.guaranteeShiny": true
                    3. Set "spawnSettings.perfectIVs": true
                    
                    To change a recipe:
                    1. Open /recipes/[item_name].json
                    2. Modify the "key" and "pattern" fields
                    3. Restart the server
                    
                    NOTES:
                    ------
                    - Changes require a server restart to take effect
                    - Invalid JSON will cause the file to be skipped
                    - Delete a config file to regenerate it with defaults
                    
                    """;
                Files.writeString(readmeFile, content);
            } catch (IOException e) {
                CobblemonOrbs.LOGGER.error("Failed to create README", e);
            }
        }
    }
    
    /**
     * Get the config directory path.
     */
    public static Path getConfigDir() {
        return CONFIG_DIR;
    }
    
    /**
     * Item-specific spawn settings.
     */
    public record ItemSpawnSettings(int level, double shinyChance, boolean guaranteeShiny, boolean perfectIVs) {
        public boolean hasCustomLevel() {
            return level > 0;
        }
        
        public boolean hasCustomShinyChance() {
            return shinyChance >= 0;
        }
    }
}
