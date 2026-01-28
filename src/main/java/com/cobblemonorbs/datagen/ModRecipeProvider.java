package com.cobblemonorbs.datagen;

import com.cobblemonorbs.CobblemonOrbs;
import com.cobblemonorbs.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

/**
 * Generates crafting recipes for all orbs.
 * Uses Minecraft items and Cobblemon items for crafting ingredients.
 */
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }
    
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // ============================================
        // Crafting Component Recipes
        // ============================================
        
        // Green Gem = 4 Emeralds + Diamond in center
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GREEN_GEM.get())
            .pattern(" E ")
            .pattern("EDE")
            .pattern(" E ")
            .define('E', Items.EMERALD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_emerald", has(Items.EMERALD))
            .save(recipeOutput);
        
        // Arceus Paw = 4 Gold Blocks + Nether Star
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARCEUS_PAW.get())
            .pattern(" G ")
            .pattern("GNG")
            .pattern(" G ")
            .define('G', Items.GOLD_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Arceus Crown = 3 Netherite Ingots + 2 Gold Blocks + Diamond Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARCEUS_CROWN.get())
            .pattern("NNN")
            .pattern("GDG")
            .pattern("   ")
            .define('N', Items.NETHERITE_INGOT)
            .define('G', Items.GOLD_BLOCK)
            .define('D', Items.DIAMOND_BLOCK)
            .unlockedBy("has_netherite", has(Items.NETHERITE_INGOT))
            .save(recipeOutput);
        
        // ============================================
        // Arceus Orb (Special Recipe with custom items)
        // ============================================
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARCEUS_ORB.get())
            .pattern("PCP")
            .pattern("GNG")
            .pattern("PGP")
            .define('P', ModItems.ARCEUS_PAW.get())
            .define('C', ModItems.ARCEUS_CROWN.get())
            .define('G', ModItems.GREEN_GEM.get())
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_arceus_crown", has(ModItems.ARCEUS_CROWN.get()))
            .save(recipeOutput);
        
        // ============================================
        // Generation 1 Legendary Orbs
        // ============================================
        
        // Articuno Orb - Ice themed (Ice + Packed Ice + Blue Ice + Diamond)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARTICUNO_ORB.get())
            .pattern("BIB")
            .pattern("PDP")
            .pattern("BIB")
            .define('B', Items.BLUE_ICE)
            .define('I', Items.PACKED_ICE)
            .define('P', Items.PRISMARINE_CRYSTALS)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_blue_ice", has(Items.BLUE_ICE))
            .save(recipeOutput);
        
        // Zapdos Orb - Electric themed (Lightning Rod + Gold + Glowstone)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ZAPDOS_ORB.get())
            .pattern("LGL")
            .pattern("GDG")
            .pattern("LGL")
            .define('L', Items.LIGHTNING_ROD)
            .define('G', Items.GLOWSTONE)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_lightning_rod", has(Items.LIGHTNING_ROD))
            .save(recipeOutput);
        
        // Moltres Orb - Fire themed (Blaze Powder + Fire Charge + Campfire)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MOLTRES_ORB.get())
            .pattern("FCF")
            .pattern("BDB")
            .pattern("FCF")
            .define('F', Items.FIRE_CHARGE)
            .define('C', Items.CAMPFIRE)
            .define('B', Items.BLAZE_POWDER)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_blaze_powder", has(Items.BLAZE_POWDER))
            .save(recipeOutput);
        
        // Mewtwo Orb - Psychic themed (Ender Eye + Amethyst + Obsidian)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MEWTWO_ORB.get())
            .pattern("EAE")
            .pattern("ONO")
            .pattern("EAE")
            .define('E', Items.ENDER_EYE)
            .define('A', Items.AMETHYST_SHARD)
            .define('O', Items.OBSIDIAN)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // ============================================
        // Generation 2 Legendary Orbs
        // ============================================
        
        // Raikou Orb - Electric/Beast themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RAIKOU_ORB.get())
            .pattern("LGL")
            .pattern("CDC")
            .pattern("LGL")
            .define('L', Items.LIGHTNING_ROD)
            .define('G', Items.GOLD_INGOT)
            .define('C', Items.COPPER_BLOCK)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_lightning_rod", has(Items.LIGHTNING_ROD))
            .save(recipeOutput);
        
        // Entei Orb - Fire/Beast themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENTEI_ORB.get())
            .pattern("BLB")
            .pattern("MDM")
            .pattern("BLB")
            .define('B', Items.BLAZE_ROD)
            .define('L', Items.LAVA_BUCKET)
            .define('M', Items.MAGMA_BLOCK)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_blaze_rod", has(Items.BLAZE_ROD))
            .save(recipeOutput);
        
        // Suicune Orb - Water/Beast themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUICUNE_ORB.get())
            .pattern("PWP")
            .pattern("HDH")
            .pattern("PWP")
            .define('P', Items.PRISMARINE_CRYSTALS)
            .define('W', Items.WATER_BUCKET)
            .define('H', Items.HEART_OF_THE_SEA)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_heart_of_sea", has(Items.HEART_OF_THE_SEA))
            .save(recipeOutput);
        
        // Lugia Orb - Psychic/Flying/Sea themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LUGIA_ORB.get())
            .pattern("FPF")
            .pattern("HNH")
            .pattern("FPF")
            .define('F', Items.FEATHER)
            .define('P', Items.PRISMARINE_SHARD)
            .define('H', Items.HEART_OF_THE_SEA)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Ho-Oh Orb - Fire/Flying/Rainbow themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HO_OH_ORB.get())
            .pattern("GBG")
            .pattern("FNF")
            .pattern("GBG")
            .define('G', Items.GOLD_BLOCK)
            .define('B', Items.BLAZE_ROD)
            .define('F', Items.FIRE_CHARGE)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // ============================================
        // Generation 3 Legendary Orbs
        // ============================================
        
        // Regirock Orb - Rock themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REGIROCK_ORB.get())
            .pattern("SDS")
            .pattern("RDR")
            .pattern("SDS")
            .define('S', Items.SANDSTONE)
            .define('D', Items.DEEPSLATE)
            .define('R', Items.REDSTONE_BLOCK)
            .unlockedBy("has_redstone_block", has(Items.REDSTONE_BLOCK))
            .save(recipeOutput);
        
        // Regice Orb - Ice themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REGICE_ORB.get())
            .pattern("BPB")
            .pattern("IDI")
            .pattern("BPB")
            .define('B', Items.BLUE_ICE)
            .define('P', Items.PACKED_ICE)
            .define('I', Items.ICE)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_blue_ice", has(Items.BLUE_ICE))
            .save(recipeOutput);
        
        // Registeel Orb - Steel themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REGISTEEL_ORB.get())
            .pattern("INI")
            .pattern("IDI")
            .pattern("INI")
            .define('I', Items.IRON_BLOCK)
            .define('N', Items.NETHERITE_SCRAP)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_netherite_scrap", has(Items.NETHERITE_SCRAP))
            .save(recipeOutput);
        
        // Latias Orb - Psychic/Dragon (Red themed)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LATIAS_ORB.get())
            .pattern("RFR")
            .pattern("ADA")
            .pattern("RFR")
            .define('R', Items.RED_DYE)
            .define('F', Items.FEATHER)
            .define('A', Items.AMETHYST_SHARD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
            .save(recipeOutput);
        
        // Latios Orb - Psychic/Dragon (Blue themed)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LATIOS_ORB.get())
            .pattern("BFB")
            .pattern("ADA")
            .pattern("BFB")
            .define('B', Items.BLUE_DYE)
            .define('F', Items.FEATHER)
            .define('A', Items.AMETHYST_SHARD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
            .save(recipeOutput);
        
        // Kyogre Orb - Water themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KYOGRE_ORB.get())
            .pattern("PHP")
            .pattern("HNH")
            .pattern("PHP")
            .define('P', Items.PRISMARINE_CRYSTALS)
            .define('H', Items.HEART_OF_THE_SEA)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Groudon Orb - Ground/Fire themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GROUDON_ORB.get())
            .pattern("MLM")
            .pattern("LNL")
            .pattern("MLM")
            .define('M', Items.MAGMA_BLOCK)
            .define('L', Items.LAVA_BUCKET)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Rayquaza Orb - Dragon/Flying themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RAYQUAZA_ORB.get())
            .pattern("EDE")
            .pattern("GNG")
            .pattern("EDE")
            .define('E', Items.EMERALD_BLOCK)
            .define('D', Items.DRAGON_BREATH)
            .define('G', Items.GOLD_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // ============================================
        // Generation 4 Legendary Orbs
        // ============================================
        
        // Lake Trio (Uxie, Mesprit, Azelf) - Psychic themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UXIE_ORB.get())
            .pattern("YAY")
            .pattern("ADA")
            .pattern("YAY")
            .define('Y', Items.YELLOW_DYE)
            .define('A', Items.AMETHYST_SHARD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MESPRIT_ORB.get())
            .pattern("PAP")
            .pattern("ADA")
            .pattern("PAP")
            .define('P', Items.PINK_DYE)
            .define('A', Items.AMETHYST_SHARD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AZELF_ORB.get())
            .pattern("BAB")
            .pattern("ADA")
            .pattern("BAB")
            .define('B', Items.BLUE_DYE)
            .define('A', Items.AMETHYST_SHARD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
            .save(recipeOutput);
        
        // Dialga Orb - Steel/Dragon (Time themed)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIALGA_ORB.get())
            .pattern("DCD")
            .pattern("INI")
            .pattern("DCD")
            .define('D', Items.DIAMOND_BLOCK)
            .define('C', Items.CLOCK)
            .define('I', Items.IRON_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Palkia Orb - Water/Dragon (Space themed)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PALKIA_ORB.get())
            .pattern("PEP")
            .pattern("ENE")
            .pattern("PEP")
            .define('P', Items.PINK_DYE)
            .define('E', Items.ENDER_PEARL)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Heatran Orb - Fire/Steel themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HEATRAN_ORB.get())
            .pattern("IMI")
            .pattern("MDM")
            .pattern("IMI")
            .define('I', Items.IRON_BLOCK)
            .define('M', Items.MAGMA_BLOCK)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_magma_block", has(Items.MAGMA_BLOCK))
            .save(recipeOutput);
        
        // Regigigas Orb - Normal (needs all 3 Regis)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REGIGIGAS_ORB.get())
            .pattern("RIS")
            .pattern("INI")
            .pattern("SIR")
            .define('R', ModItems.REGIROCK_ORB.get())
            .define('I', ModItems.REGICE_ORB.get())
            .define('S', ModItems.REGISTEEL_ORB.get())
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_registeel_orb", has(ModItems.REGISTEEL_ORB.get()))
            .save(recipeOutput);
        
        // Giratina Orb - Ghost/Dragon themed
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GIRATINA_ORB.get())
            .pattern("OSO")
            .pattern("SNS")
            .pattern("OSO")
            .define('O', Items.OBSIDIAN)
            .define('S', Items.SOUL_SAND)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Cresselia Orb - Psychic (Moon themed)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CRESSELIA_ORB.get())
            .pattern("GAG")
            .pattern("ADA")
            .pattern("GAG")
            .define('G', Items.GLOW_INK_SAC)
            .define('A', Items.AMETHYST_SHARD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_glow_ink", has(Items.GLOW_INK_SAC))
            .save(recipeOutput);
        
        // ============================================
        // Generation 5 Legendary Orbs
        // ============================================
        
        // Swords of Justice
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COBALION_ORB.get())
            .pattern("ISI")
            .pattern("SDS")
            .pattern("ISI")
            .define('I', Items.IRON_INGOT)
            .define('S', Items.IRON_SWORD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_iron_sword", has(Items.IRON_SWORD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TERRAKION_ORB.get())
            .pattern("RSR")
            .pattern("SDS")
            .pattern("RSR")
            .define('R', Items.COBBLESTONE)
            .define('S', Items.STONE_SWORD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_stone_sword", has(Items.STONE_SWORD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VIRIZION_ORB.get())
            .pattern("LSL")
            .pattern("SDS")
            .pattern("LSL")
            .define('L', Items.OAK_LEAVES)
            .define('S', Items.WOODEN_SWORD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_wooden_sword", has(Items.WOODEN_SWORD))
            .save(recipeOutput);
        
        // Forces of Nature
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TORNADUS_ORB.get())
            .pattern("FGF")
            .pattern("GDG")
            .pattern("FGF")
            .define('F', Items.FEATHER)
            .define('G', Items.GLASS)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_feather", has(Items.FEATHER))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.THUNDURUS_ORB.get())
            .pattern("LGL")
            .pattern("GDG")
            .pattern("LGL")
            .define('L', Items.LIGHTNING_ROD)
            .define('G', Items.GLOWSTONE)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_lightning_rod", has(Items.LIGHTNING_ROD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LANDORUS_ORB.get())
            .pattern("DSD")
            .pattern("SES")
            .pattern("DSD")
            .define('D', Items.DIRT)
            .define('S', Items.SAND)
            .define('E', Items.DIAMOND)
            .unlockedBy("has_dirt", has(Items.DIRT))
            .save(recipeOutput);
        
        // Tao Trio
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RESHIRAM_ORB.get())
            .pattern("QFQ")
            .pattern("FNF")
            .pattern("QFQ")
            .define('Q', Items.QUARTZ_BLOCK)
            .define('F', Items.FIRE_CHARGE)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ZEKROM_ORB.get())
            .pattern("OLO")
            .pattern("LNL")
            .pattern("OLO")
            .define('O', Items.OBSIDIAN)
            .define('L', Items.LIGHTNING_ROD)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KYUREM_ORB.get())
            .pattern("BIB")
            .pattern("INI")
            .pattern("BIB")
            .define('B', Items.BLUE_ICE)
            .define('I', Items.IRON_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // ============================================
        // Generation 6 Legendary Orbs
        // ============================================
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.XERNEAS_ORB.get())
            .pattern("LGL")
            .pattern("GNG")
            .pattern("LGL")
            .define('L', Items.OAK_LOG)
            .define('G', Items.GLOW_BERRIES)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.YVELTAL_ORB.get())
            .pattern("RSR")
            .pattern("SNS")
            .pattern("RSR")
            .define('R', Items.RED_DYE)
            .define('S', Items.SOUL_SAND)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ZYGARDE_ORB.get())
            .pattern("GEG")
            .pattern("ENE")
            .pattern("GEG")
            .define('G', Items.GREEN_DYE)
            .define('E', Items.EMERALD)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // ============================================
        // Generation 7 Legendary Orbs
        // ============================================
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TYPE_NULL_ORB.get())
            .pattern("IDI")
            .pattern("DND")
            .pattern("IDI")
            .define('I', Items.IRON_INGOT)
            .define('D', Items.DIAMOND)
            .define('N', Items.NETHERITE_SCRAP)
            .unlockedBy("has_netherite_scrap", has(Items.NETHERITE_SCRAP))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SILVALLY_ORB.get())
            .pattern("TDT")
            .pattern("DND")
            .pattern("TDT")
            .define('T', ModItems.TYPE_NULL_ORB.get())
            .define('D', Items.DIAMOND_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_type_null_orb", has(ModItems.TYPE_NULL_ORB.get()))
            .save(recipeOutput);
        
        // Tapu Guardians
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TAPU_KOKO_ORB.get())
            .pattern("YLY")
            .pattern("LDL")
            .pattern("YLY")
            .define('Y', Items.YELLOW_DYE)
            .define('L', Items.LIGHTNING_ROD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_lightning_rod", has(Items.LIGHTNING_ROD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TAPU_LELE_ORB.get())
            .pattern("PAP")
            .pattern("ADA")
            .pattern("PAP")
            .define('P', Items.PINK_DYE)
            .define('A', Items.AMETHYST_SHARD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TAPU_BULU_ORB.get())
            .pattern("GLG")
            .pattern("LDL")
            .pattern("GLG")
            .define('G', Items.GREEN_DYE)
            .define('L', Items.OAK_LEAVES)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_leaves", has(Items.OAK_LEAVES))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TAPU_FINI_ORB.get())
            .pattern("BPB")
            .pattern("PDP")
            .pattern("BPB")
            .define('B', Items.BLUE_DYE)
            .define('P', Items.PRISMARINE_CRYSTALS)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_prismarine", has(Items.PRISMARINE_CRYSTALS))
            .save(recipeOutput);
        
        // Cosmog line
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COSMOG_ORB.get())
            .pattern("GAG")
            .pattern("ADA")
            .pattern("GAG")
            .define('G', Items.GLOW_INK_SAC)
            .define('A', Items.AMETHYST_SHARD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_glow_ink", has(Items.GLOW_INK_SAC))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COSMOEM_ORB.get())
            .pattern("CDC")
            .pattern("DND")
            .pattern("CDC")
            .define('C', ModItems.COSMOG_ORB.get())
            .define('D', Items.DIAMOND_BLOCK)
            .define('N', Items.GOLD_BLOCK)
            .unlockedBy("has_cosmog_orb", has(ModItems.COSMOG_ORB.get()))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOLGALEO_ORB.get())
            .pattern("GCG")
            .pattern("CNS")
            .pattern("GCG")
            .define('G', Items.GOLD_BLOCK)
            .define('C', ModItems.COSMOEM_ORB.get())
            .define('N', Items.NETHER_STAR)
            .define('S', Items.SUNFLOWER)
            .unlockedBy("has_cosmoem_orb", has(ModItems.COSMOEM_ORB.get()))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LUNALA_ORB.get())
            .pattern("ACG")
            .pattern("CNA")
            .pattern("GCA")
            .define('A', Items.AMETHYST_BLOCK)
            .define('C', ModItems.COSMOEM_ORB.get())
            .define('N', Items.NETHER_STAR)
            .define('G', Items.GLOW_INK_SAC)
            .unlockedBy("has_cosmoem_orb", has(ModItems.COSMOEM_ORB.get()))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NECROZMA_ORB.get())
            .pattern("OPO")
            .pattern("PNP")
            .pattern("OPO")
            .define('O', Items.OBSIDIAN)
            .define('P', Items.PRISMARINE_CRYSTALS)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // ============================================
        // Generation 8 Legendary Orbs
        // ============================================
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ZACIAN_ORB.get())
            .pattern("SNS")
            .pattern("GDG")
            .pattern("SNS")
            .define('S', Items.NETHERITE_SWORD)
            .define('N', Items.NETHER_STAR)
            .define('G', Items.GOLD_BLOCK)
            .define('D', Items.DIAMOND_BLOCK)
            .unlockedBy("has_netherite_sword", has(Items.NETHERITE_SWORD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ZAMAZENTA_ORB.get())
            .pattern("SNS")
            .pattern("GDG")
            .pattern("SNS")
            .define('S', Items.SHIELD)
            .define('N', Items.NETHER_STAR)
            .define('G', Items.GOLD_BLOCK)
            .define('D', Items.DIAMOND_BLOCK)
            .unlockedBy("has_shield", has(Items.SHIELD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ETERNATUS_ORB.get())
            .pattern("ENE")
            .pattern("NSN")
            .pattern("ENE")
            .define('E', Items.END_STONE)
            .define('N', Items.NETHER_STAR)
            .define('S', Items.DRAGON_BREATH)
            .unlockedBy("has_dragon_breath", has(Items.DRAGON_BREATH))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KUBFU_ORB.get())
            .pattern("BFB")
            .pattern("FDF")
            .pattern("BFB")
            .define('B', Items.BAMBOO)
            .define('F', Items.FEATHER)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_bamboo", has(Items.BAMBOO))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.URSHIFU_ORB.get())
            .pattern("KDK")
            .pattern("DND")
            .pattern("KDK")
            .define('K', ModItems.KUBFU_ORB.get())
            .define('D', Items.DIAMOND_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_kubfu_orb", has(ModItems.KUBFU_ORB.get()))
            .save(recipeOutput);
        
        // New Regis
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REGIELEKI_ORB.get())
            .pattern("LCL")
            .pattern("CDC")
            .pattern("LCL")
            .define('L', Items.LIGHTNING_ROD)
            .define('C', Items.COPPER_BLOCK)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_copper_block", has(Items.COPPER_BLOCK))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REGIDRAGO_ORB.get())
            .pattern("DBD")
            .pattern("BRB")
            .pattern("DBD")
            .define('D', Items.DRAGON_BREATH)
            .define('B', Items.BONE_BLOCK)
            .define('R', Items.REDSTONE_BLOCK)
            .unlockedBy("has_dragon_breath", has(Items.DRAGON_BREATH))
            .save(recipeOutput);
        
        // Horses
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLASTRIER_ORB.get())
            .pattern("BIB")
            .pattern("IDI")
            .pattern("BIB")
            .define('B', Items.BLUE_ICE)
            .define('I', Items.IRON_HORSE_ARMOR)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_blue_ice", has(Items.BLUE_ICE))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPECTRIER_ORB.get())
            .pattern("SHS")
            .pattern("HNH")
            .pattern("SHS")
            .define('S', Items.SOUL_SAND)
            .define('H', Items.DIAMOND_HORSE_ARMOR)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_soul_sand", has(Items.SOUL_SAND))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CALYREX_ORB.get())
            .pattern("GCS")
            .pattern("NRN")
            .pattern("SCG")
            .define('G', ModItems.GLASTRIER_ORB.get())
            .define('S', ModItems.SPECTRIER_ORB.get())
            .define('C', ModItems.ARCEUS_CROWN.get())
            .define('N', Items.NETHER_STAR)
            .define('R', Items.CARROT)
            .unlockedBy("has_glastrier_orb", has(ModItems.GLASTRIER_ORB.get()))
            .save(recipeOutput);
        
        // ============================================
        // Generation 9 Legendary Orbs
        // ============================================
        
        // Treasures of Ruin
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WO_CHIEN_ORB.get())
            .pattern("LAL")
            .pattern("ADA")
            .pattern("LAL")
            .define('L', Items.DARK_OAK_LEAVES)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHIEN_PAO_ORB.get())
            .pattern("BAB")
            .pattern("ADA")
            .pattern("BAB")
            .define('B', Items.BLUE_ICE)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TING_LU_ORB.get())
            .pattern("DAD")
            .pattern("AEA")
            .pattern("DAD")
            .define('D', Items.DEEPSLATE)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('E', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHI_YU_ORB.get())
            .pattern("FAF")
            .pattern("ADA")
            .pattern("FAF")
            .define('F', Items.FIRE_CHARGE)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        // Box Legendaries
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KORAIDON_ORB.get())
            .pattern("RDR")
            .pattern("DNR")
            .pattern("RDR")
            .define('R', Items.RED_DYE)
            .define('D', Items.DRAGON_BREATH)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MIRAIDON_ORB.get())
            .pattern("PDP")
            .pattern("DNP")
            .pattern("PDP")
            .define('P', Items.PURPLE_DYE)
            .define('D', Items.DRAGON_BREATH)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Loyal Three
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OKIDOGI_ORB.get())
            .pattern("PBP")
            .pattern("BDB")
            .pattern("PBP")
            .define('P', Items.PINK_DYE)
            .define('B', Items.BONE)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_bone", has(Items.BONE))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MUNKIDORI_ORB.get())
            .pattern("GAG")
            .pattern("ADA")
            .pattern("GAG")
            .define('G', Items.GREEN_DYE)
            .define('A', Items.AMETHYST_SHARD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FEZANDIPITI_ORB.get())
            .pattern("PFP")
            .pattern("FDF")
            .pattern("PFP")
            .define('P', Items.PURPLE_DYE)
            .define('F', Items.FEATHER)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_feather", has(Items.FEATHER))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OGERPON_ORB.get())
            .pattern("LML")
            .pattern("MNM")
            .pattern("LML")
            .define('L', Items.OAK_LEAVES)
            .define('M', Items.CARVED_PUMPKIN)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_carved_pumpkin", has(Items.CARVED_PUMPKIN))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TERAPAGOS_ORB.get())
            .pattern("PAP")
            .pattern("ANA")
            .pattern("PAP")
            .define('P', Items.PRISMARINE_CRYSTALS)
            .define('A', Items.AMETHYST_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // ============================================
        // Mythical Pokémon Orbs
        // ============================================
        
        // Mew - Pink/Psychic
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MEW_ORB.get())
            .pattern("PAP")
            .pattern("ANA")
            .pattern("PAP")
            .define('P', Items.PINK_DYE)
            .define('A', Items.AMETHYST_SHARD)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Celebi - Grass/Psychic (Time travel)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELEBI_ORB.get())
            .pattern("LCL")
            .pattern("CNC")
            .pattern("LCL")
            .define('L', Items.OAK_LEAVES)
            .define('C', Items.CLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Jirachi - Steel/Psychic (Wish/Star)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JIRACHI_ORB.get())
            .pattern("GIG")
            .pattern("INI")
            .pattern("GIG")
            .define('G', Items.GLOW_INK_SAC)
            .define('I', Items.IRON_INGOT)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Deoxys - Psychic (Space)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DEOXYS_ORB.get())
            .pattern("EAE")
            .pattern("ENE")
            .pattern("EAE")
            .define('E', Items.END_STONE)
            .define('A', Items.AMETHYST_SHARD)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Phione - Water
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PHIONE_ORB.get())
            .pattern("PWP")
            .pattern("WDW")
            .pattern("PWP")
            .define('P', Items.PRISMARINE_SHARD)
            .define('W', Items.WATER_BUCKET)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_prismarine", has(Items.PRISMARINE_SHARD))
            .save(recipeOutput);
        
        // Manaphy - Water
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MANAPHY_ORB.get())
            .pattern("HPH")
            .pattern("PNP")
            .pattern("HPH")
            .define('H', Items.HEART_OF_THE_SEA)
            .define('P', Items.PRISMARINE_CRYSTALS)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_heart_of_sea", has(Items.HEART_OF_THE_SEA))
            .save(recipeOutput);
        
        // Darkrai - Dark
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DARKRAI_ORB.get())
            .pattern("OSO")
            .pattern("SNS")
            .pattern("OSO")
            .define('O', Items.OBSIDIAN)
            .define('S', Items.SOUL_SAND)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Shaymin - Grass
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SHAYMIN_ORB.get())
            .pattern("FLF")
            .pattern("LNL")
            .pattern("FLF")
            .define('F', Items.POPPY)
            .define('L', Items.OAK_LEAVES)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Victini - Psychic/Fire
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VICTINI_ORB.get())
            .pattern("FAF")
            .pattern("ANA")
            .pattern("FAF")
            .define('F', Items.FIRE_CHARGE)
            .define('A', Items.AMETHYST_SHARD)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Keldeo - Water/Fighting
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KELDEO_ORB.get())
            .pattern("PSP")
            .pattern("SDS")
            .pattern("PSP")
            .define('P', Items.PRISMARINE_CRYSTALS)
            .define('S', Items.IRON_SWORD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_iron_sword", has(Items.IRON_SWORD))
            .save(recipeOutput);
        
        // Meloetta - Normal/Psychic
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MELOETTA_ORB.get())
            .pattern("NAG")
            .pattern("ADA")
            .pattern("GAN")
            .define('N', Items.NOTE_BLOCK)
            .define('A', Items.AMETHYST_SHARD)
            .define('G', Items.GREEN_DYE)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_note_block", has(Items.NOTE_BLOCK))
            .save(recipeOutput);
        
        // Genesect - Bug/Steel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GENESECT_ORB.get())
            .pattern("INI")
            .pattern("RDR")
            .pattern("INI")
            .define('I', Items.IRON_BLOCK)
            .define('N', Items.NETHERITE_INGOT)
            .define('R', Items.REDSTONE_BLOCK)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_netherite", has(Items.NETHERITE_INGOT))
            .save(recipeOutput);
        
        // Diancie - Rock/Fairy
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIANCIE_ORB.get())
            .pattern("DAD")
            .pattern("ANA")
            .pattern("DAD")
            .define('D', Items.DIAMOND_BLOCK)
            .define('A', Items.AMETHYST_SHARD)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Hoopa - Psychic/Ghost
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HOOPA_ORB.get())
            .pattern("EGE")
            .pattern("GNG")
            .pattern("EGE")
            .define('E', Items.ENDER_PEARL)
            .define('G', Items.GOLD_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Volcanion - Fire/Water
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOLCANION_ORB.get())
            .pattern("MWM")
            .pattern("WNW")
            .pattern("MWM")
            .define('M', Items.MAGMA_BLOCK)
            .define('W', Items.WATER_BUCKET)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Magearna - Steel/Fairy
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGEARNA_ORB.get())
            .pattern("IGI")
            .pattern("GNG")
            .pattern("IGI")
            .define('I', Items.IRON_BLOCK)
            .define('G', Items.GOLD_INGOT)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Marshadow - Fighting/Ghost
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MARSHADOW_ORB.get())
            .pattern("SSS")
            .pattern("SNS")
            .pattern("SSS")
            .define('S', Items.SOUL_SAND)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Zeraora - Electric
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ZERAORA_ORB.get())
            .pattern("LGL")
            .pattern("GNG")
            .pattern("LGL")
            .define('L', Items.LIGHTNING_ROD)
            .define('G', Items.GOLD_INGOT)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Meltan - Steel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MELTAN_ORB.get())
            .pattern("INI")
            .pattern("NRN")
            .pattern("INI")
            .define('I', Items.IRON_NUGGET)
            .define('N', Items.GOLD_NUGGET)
            .define('R', Items.REDSTONE)
            .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
            .save(recipeOutput);
        
        // Melmetal - Steel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MELMETAL_ORB.get())
            .pattern("MIM")
            .pattern("INI")
            .pattern("MIM")
            .define('M', ModItems.MELTAN_ORB.get())
            .define('I', Items.IRON_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_meltan_orb", has(ModItems.MELTAN_ORB.get()))
            .save(recipeOutput);
        
        // Zarude - Dark/Grass
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ZARUDE_ORB.get())
            .pattern("VSV")
            .pattern("SDS")
            .pattern("VSV")
            .define('V', Items.VINE)
            .define('S', Items.SOUL_SAND)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_vine", has(Items.VINE))
            .save(recipeOutput);
        
        // Pecharunt - Poison/Ghost
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PECHARUNT_ORB.get())
            .pattern("PSP")
            .pattern("SDS")
            .pattern("PSP")
            .define('P', Items.FERMENTED_SPIDER_EYE)
            .define('S', Items.SOUL_SAND)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_fermented_spider_eye", has(Items.FERMENTED_SPIDER_EYE))
            .save(recipeOutput);
        
        // ============================================
        // Paradox Pokémon Orbs
        // ============================================
        
        // Past Paradox (Scarlet) - Use Ancient Debris
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GREAT_TUSK_ORB.get())
            .pattern("DAD")
            .pattern("AEA")
            .pattern("DAD")
            .define('D', Items.DEEPSLATE)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('E', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SCREAM_TAIL_ORB.get())
            .pattern("PAP")
            .pattern("AEA")
            .pattern("PAP")
            .define('P', Items.PINK_DYE)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('E', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BRUTE_BONNET_ORB.get())
            .pattern("MAM")
            .pattern("AEA")
            .pattern("MAM")
            .define('M', Items.RED_MUSHROOM_BLOCK)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('E', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLUTTER_MANE_ORB.get())
            .pattern("GAG")
            .pattern("AEA")
            .pattern("GAG")
            .define('G', Items.GLOW_INK_SAC)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('E', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SLITHER_WING_ORB.get())
            .pattern("FAF")
            .pattern("AEA")
            .pattern("FAF")
            .define('F', Items.FIRE_CHARGE)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('E', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SANDY_SHOCKS_ORB.get())
            .pattern("SAL")
            .pattern("AEA")
            .pattern("LAS")
            .define('S', Items.SAND)
            .define('L', Items.LIGHTNING_ROD)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('E', Items.DIAMOND)
            .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ROARING_MOON_ORB.get())
            .pattern("DAD")
            .pattern("ANA")
            .pattern("DAD")
            .define('D', Items.DRAGON_BREATH)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // Future Paradox (Violet) - Use Iron Blocks
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_TREADS_ORB.get())
            .pattern("IRI")
            .pattern("RDR")
            .pattern("IRI")
            .define('I', Items.IRON_BLOCK)
            .define('R', Items.REDSTONE_BLOCK)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_iron_block", has(Items.IRON_BLOCK))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_BUNDLE_ORB.get())
            .pattern("IBI")
            .pattern("BDB")
            .pattern("IBI")
            .define('I', Items.IRON_BLOCK)
            .define('B', Items.BLUE_ICE)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_iron_block", has(Items.IRON_BLOCK))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_HANDS_ORB.get())
            .pattern("IRI")
            .pattern("LDL")
            .pattern("IRI")
            .define('I', Items.IRON_BLOCK)
            .define('R', Items.REDSTONE_BLOCK)
            .define('L', Items.LIGHTNING_ROD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_iron_block", has(Items.IRON_BLOCK))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_JUGULIS_ORB.get())
            .pattern("IDI")
            .pattern("DRD")
            .pattern("IDI")
            .define('I', Items.IRON_BLOCK)
            .define('D', Items.DRAGON_BREATH)
            .define('R', Items.REDSTONE_BLOCK)
            .unlockedBy("has_iron_block", has(Items.IRON_BLOCK))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_MOTH_ORB.get())
            .pattern("IFI")
            .pattern("FDF")
            .pattern("IFI")
            .define('I', Items.IRON_BLOCK)
            .define('F', Items.FIRE_CHARGE)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_iron_block", has(Items.IRON_BLOCK))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_THORNS_ORB.get())
            .pattern("ILI")
            .pattern("LDL")
            .pattern("ILI")
            .define('I', Items.IRON_BLOCK)
            .define('L', Items.LIGHTNING_ROD)
            .define('D', Items.DIAMOND)
            .unlockedBy("has_iron_block", has(Items.IRON_BLOCK))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_VALIANT_ORB.get())
            .pattern("IAI")
            .pattern("ANA")
            .pattern("IAI")
            .define('I', Items.IRON_BLOCK)
            .define('A', Items.AMETHYST_SHARD)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        // DLC Paradox
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WALKING_WAKE_ORB.get())
            .pattern("PAP")
            .pattern("ANA")
            .pattern("PAP")
            .define('P', Items.PRISMARINE_CRYSTALS)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_LEAVES_ORB.get())
            .pattern("ILI")
            .pattern("LNL")
            .pattern("ILI")
            .define('I', Items.IRON_BLOCK)
            .define('L', Items.OAK_LEAVES)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOUGING_FIRE_ORB.get())
            .pattern("FAF")
            .pattern("ANA")
            .pattern("FAF")
            .define('F', Items.FIRE_CHARGE)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RAGING_BOLT_ORB.get())
            .pattern("LAL")
            .pattern("ANA")
            .pattern("LAL")
            .define('L', Items.LIGHTNING_ROD)
            .define('A', Items.ANCIENT_DEBRIS)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_CROWN_ORB.get())
            .pattern("IAI")
            .pattern("ANA")
            .pattern("IAI")
            .define('I', Items.IRON_BLOCK)
            .define('A', Items.AMETHYST_BLOCK)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_BOULDER_ORB.get())
            .pattern("ISI")
            .pattern("SNS")
            .pattern("ISI")
            .define('I', Items.IRON_BLOCK)
            .define('S', Items.STONE)
            .define('N', Items.NETHER_STAR)
            .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
            .save(recipeOutput);
    }
}
