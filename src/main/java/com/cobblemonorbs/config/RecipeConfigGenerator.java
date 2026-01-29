package com.cobblemonorbs.config;

import com.cobblemonorbs.CobblemonOrbs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.neoforged.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * Generates editable config files from the mod's built-in recipes.
 * This runs once on first startup to create files that server admins can edit.
 */
public class RecipeConfigGenerator {
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve(CobblemonOrbs.MOD_ID);
    private static final Path RECIPES_DIR = CONFIG_DIR.resolve("recipes");
    
    /**
     * Export all built-in recipes to the config folder.
     * Only creates files that don't already exist (won't overwrite user edits).
     */
    public static void exportRecipesToConfig() {
        try {
            Files.createDirectories(RECIPES_DIR);
            
            // Try to read recipes from the mod's resources
            ClassLoader classLoader = RecipeConfigGenerator.class.getClassLoader();
            
            // List of all recipe files to export
            String[] recipeNames = {
                // Crafting components
                "arceus_crown", "arceus_paw", "green_gem",
                // Arceus
                "arceus_orb",
                // Gen 1
                "articuno_orb", "zapdos_orb", "moltres_orb", "mewtwo_orb",
                // Gen 2
                "raikou_orb", "entei_orb", "suicune_orb", "lugia_orb", "ho_oh_orb",
                // Gen 3
                "regirock_orb", "regice_orb", "registeel_orb", "latias_orb", "latios_orb",
                "kyogre_orb", "groudon_orb", "rayquaza_orb",
                // Gen 4
                "uxie_orb", "mesprit_orb", "azelf_orb", "dialga_orb", "palkia_orb",
                "heatran_orb", "regigigas_orb", "giratina_orb", "cresselia_orb",
                // Gen 5
                "cobalion_orb", "terrakion_orb", "virizion_orb", "tornadus_orb",
                "thundurus_orb", "reshiram_orb", "zekrom_orb", "landorus_orb", "kyurem_orb",
                // Gen 6
                "xerneas_orb", "yveltal_orb", "zygarde_orb",
                // Gen 7
                "type_null_orb", "silvally_orb", "tapukoko_orb", "tapulele_orb",
                "tapubulu_orb", "tapufini_orb", "cosmog_orb", "cosmoem_orb",
                "solgaleo_orb", "lunala_orb", "necrozma_orb",
                // Gen 8
                "zacian_orb", "zamazenta_orb", "eternatus_orb", "kubfu_orb", "urshifu_orb",
                "regieleki_orb", "regidrago_orb", "glastrier_orb", "spectrier_orb", "calyrex_orb",
                // Gen 9
                "wochien_orb", "chienpao_orb", "tinglu_orb", "chiyu_orb",
                "koraidon_orb", "miraidon_orb", "ogerpon_orb", "terapagos_orb", "pecharunt_orb",
                // Mythicals
                "mew_orb", "celebi_orb", "jirachi_orb", "deoxys_orb", "phione_orb", "manaphy_orb",
                "darkrai_orb", "shaymin_orb", "victini_orb", "keldeo_orb",
                "meloetta_orb", "genesect_orb", "diancie_orb", "hoopa_orb", "volcanion_orb",
                "magearna_orb", "marshadow_orb", "zeraora_orb", "meltan_orb", "melmetal_orb",
                "zarude_orb", "okidogi_orb", "munkidori_orb", "fezandipiti_orb",
                // Paradox
                "greattusk_orb", "screamtail_orb", "brutebonnet_orb", "fluttermane_orb",
                "slitherwing_orb", "sandyshocks_orb", "roaringmoon_orb", "walkingwake_orb",
                "gougingfire_orb", "ragingbolt_orb",
                "irontreads_orb", "ironbundle_orb", "ironhands_orb", "ironjugulis_orb",
                "ironmoth_orb", "ironthorns_orb", "ironvaliant_orb", "ironleaves_orb",
                "ironboulder_orb", "ironcrown_orb"
            };
            
            int created = 0;
            int skipped = 0;
            
            for (String recipeName : recipeNames) {
                Path targetFile = RECIPES_DIR.resolve(recipeName + ".json");
                
                // Skip if file already exists (don't overwrite user edits)
                if (Files.exists(targetFile)) {
                    skipped++;
                    continue;
                }
                
                // Try to load the recipe from mod resources
                String resourcePath = "data/" + CobblemonOrbs.MOD_ID + "/recipe/" + recipeName + ".json";
                try (InputStream is = classLoader.getResourceAsStream(resourcePath)) {
                    if (is != null) {
                        String content = new String(is.readAllBytes());
                        
                        // Parse and add config fields
                        JsonObject json = GSON.fromJson(content, JsonObject.class);
                        
                        // Create a wrapper with config options
                        JsonObject configWrapper = new JsonObject();
                        configWrapper.addProperty("_comment", "Set enabled to false to disable this recipe. You can also modify the recipe below.");
                        configWrapper.addProperty("enabled", true);
                        
                        // Copy all original recipe fields
                        for (String key : json.keySet()) {
                            configWrapper.add(key, json.get(key));
                        }
                        
                        Files.writeString(targetFile, GSON.toJson(configWrapper));
                        created++;
                    }
                } catch (Exception e) {
                    CobblemonOrbs.LOGGER.warn("Could not export recipe: {}", recipeName, e);
                }
            }
            
            CobblemonOrbs.LOGGER.info("Recipe config export complete: {} created, {} skipped (already exist)", 
                created, skipped);
            
        } catch (IOException e) {
            CobblemonOrbs.LOGGER.error("Failed to export recipes to config", e);
        }
    }
}
