package com.cobblemonorbs.registry;

import com.cobblemonorbs.CobblemonOrbs;
import com.cobblemonorbs.data.PokemonDataManager;
import com.cobblemonorbs.item.api.OrbCategory;
import com.cobblemonorbs.item.api.SpawnSettings;
import com.cobblemonorbs.item.impl.CraftingComponentItem;
import com.cobblemonorbs.item.impl.RandomCategoryOrb;
import com.cobblemonorbs.item.impl.SpecificPokemonOrb;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Handles registration of all orb items.
 */
public class ModItems {
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CobblemonOrbs.MOD_ID);
    
    // Maps for easy access to registered items by Pokémon ID
    private static final Map<String, DeferredItem<Item>> LEGENDARY_ORBS = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> MYTHICAL_ORBS = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> PARADOX_ORBS = new HashMap<>();
    
    // ============================================
    // Crafting Component Items (for Arceus Orb)
    // ============================================
    
    public static final DeferredItem<Item> ARCEUS_PAW = registerItem("arceus_paw",
        () -> new CraftingComponentItem(Rarity.EPIC));
    
    public static final DeferredItem<Item> ARCEUS_CROWN = registerItem("arceus_crown",
        () -> new CraftingComponentItem(Rarity.EPIC));
    
    public static final DeferredItem<Item> GREEN_GEM = registerItem("green_gem",
        () -> new CraftingComponentItem(Rarity.RARE));
    
    // ============================================
    // Random/Special Orbs
    // ============================================
    
    // Classic Orbs (random from category, normal random stats)
    public static final DeferredItem<Item> RANDOM_ORB = registerItem("random_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.ALL, SpawnSettings.DEFAULT));

    public static final DeferredItem<Item> LEGENDARY_ORB = registerItem("legendary_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.LEGENDARY_ONLY, SpawnSettings.DEFAULT));

    public static final DeferredItem<Item> MYTHICAL_ORB = registerItem("mythical_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.MYTHICAL_ONLY, SpawnSettings.DEFAULT));

    public static final DeferredItem<Item> PARADOX_ORB = registerItem("paradox_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.PARADOX_ONLY, SpawnSettings.DEFAULT));

    // Shiny Orbs (random from category, guaranteed shiny)
    public static final DeferredItem<Item> SHINY_ORB = registerItem("shiny_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.ALL, SpawnSettings.SHINY));

    public static final DeferredItem<Item> LEGENDARY_SHINY_ORB = registerItem("legendary_shiny_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.LEGENDARY_ONLY, SpawnSettings.SHINY));
    
    public static final DeferredItem<Item> MYTHICAL_SHINY_ORB = registerItem("mythical_shiny_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.MYTHICAL_ONLY, SpawnSettings.SHINY));
    
    public static final DeferredItem<Item> PARADOX_SHINY_ORB = registerItem("paradox_shiny_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.PARADOX_ONLY, SpawnSettings.SHINY));
    
    // Ultimate Orbs (random from category, perfect IVs)
    public static final DeferredItem<Item> ULTIMATE_ORB = registerItem("ultimate_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.ALL, SpawnSettings.ULTIMATE));
    
    public static final DeferredItem<Item> LEGENDARY_ULTIMATE_ORB = registerItem("legendary_ultimate_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.LEGENDARY_ONLY, SpawnSettings.ULTIMATE));
    
    public static final DeferredItem<Item> MYTHICAL_ULTIMATE_ORB = registerItem("mythical_ultimate_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.MYTHICAL_ONLY, SpawnSettings.ULTIMATE));
    
    public static final DeferredItem<Item> PARADOX_ULTIMATE_ORB = registerItem("paradox_ultimate_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.PARADOX_ONLY, SpawnSettings.ULTIMATE));
    
    // Ultimate Shiny Orbs (random from category, shiny + perfect IVs)
    public static final DeferredItem<Item> ULTIMATE_SHINY_ORB = registerItem("ultimate_shiny_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.ALL, SpawnSettings.ULTIMATE_SHINY));
    
    public static final DeferredItem<Item> LEGENDARY_ULTIMATE_SHINY_ORB = registerItem("legendary_ultimate_shiny_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.LEGENDARY_ONLY, SpawnSettings.ULTIMATE_SHINY));
    
    public static final DeferredItem<Item> MYTHICAL_ULTIMATE_SHINY_ORB = registerItem("mythical_ultimate_shiny_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.MYTHICAL_ONLY, SpawnSettings.ULTIMATE_SHINY));
    
    public static final DeferredItem<Item> PARADOX_ULTIMATE_SHINY_ORB = registerItem("paradox_ultimate_shiny_orb",
        () -> new RandomCategoryOrb(RandomCategoryOrb.SelectionCategory.PARADOX_ONLY, SpawnSettings.ULTIMATE_SHINY));
    
    // ============================================
    // Legendary Pokémon Orbs
    // ============================================
    
    // Generation 1
    public static final DeferredItem<Item> ARTICUNO_ORB = registerLegendaryOrb("articuno");
    public static final DeferredItem<Item> ZAPDOS_ORB = registerLegendaryOrb("zapdos");
    public static final DeferredItem<Item> MOLTRES_ORB = registerLegendaryOrb("moltres");
    public static final DeferredItem<Item> MEWTWO_ORB = registerLegendaryOrb("mewtwo");
    
    // Generation 2
    public static final DeferredItem<Item> RAIKOU_ORB = registerLegendaryOrb("raikou");
    public static final DeferredItem<Item> ENTEI_ORB = registerLegendaryOrb("entei");
    public static final DeferredItem<Item> SUICUNE_ORB = registerLegendaryOrb("suicune");
    public static final DeferredItem<Item> LUGIA_ORB = registerLegendaryOrb("lugia");
    public static final DeferredItem<Item> HO_OH_ORB = registerLegendaryOrb("ho-oh");
    
    // Generation 3
    public static final DeferredItem<Item> REGIROCK_ORB = registerLegendaryOrb("regirock");
    public static final DeferredItem<Item> REGICE_ORB = registerLegendaryOrb("regice");
    public static final DeferredItem<Item> REGISTEEL_ORB = registerLegendaryOrb("registeel");
    public static final DeferredItem<Item> LATIAS_ORB = registerLegendaryOrb("latias");
    public static final DeferredItem<Item> LATIOS_ORB = registerLegendaryOrb("latios");
    public static final DeferredItem<Item> KYOGRE_ORB = registerLegendaryOrb("kyogre");
    public static final DeferredItem<Item> GROUDON_ORB = registerLegendaryOrb("groudon");
    public static final DeferredItem<Item> RAYQUAZA_ORB = registerLegendaryOrb("rayquaza");
    
    // Generation 4
    public static final DeferredItem<Item> UXIE_ORB = registerLegendaryOrb("uxie");
    public static final DeferredItem<Item> MESPRIT_ORB = registerLegendaryOrb("mesprit");
    public static final DeferredItem<Item> AZELF_ORB = registerLegendaryOrb("azelf");
    public static final DeferredItem<Item> DIALGA_ORB = registerLegendaryOrb("dialga");
    public static final DeferredItem<Item> PALKIA_ORB = registerLegendaryOrb("palkia");
    public static final DeferredItem<Item> HEATRAN_ORB = registerLegendaryOrb("heatran");
    public static final DeferredItem<Item> REGIGIGAS_ORB = registerLegendaryOrb("regigigas");
    public static final DeferredItem<Item> GIRATINA_ORB = registerLegendaryOrb("giratina");
    public static final DeferredItem<Item> CRESSELIA_ORB = registerLegendaryOrb("cresselia");
    
    // Generation 5
    public static final DeferredItem<Item> COBALION_ORB = registerLegendaryOrb("cobalion");
    public static final DeferredItem<Item> TERRAKION_ORB = registerLegendaryOrb("terrakion");
    public static final DeferredItem<Item> VIRIZION_ORB = registerLegendaryOrb("virizion");
    public static final DeferredItem<Item> TORNADUS_ORB = registerLegendaryOrb("tornadus");
    public static final DeferredItem<Item> THUNDURUS_ORB = registerLegendaryOrb("thundurus");
    public static final DeferredItem<Item> RESHIRAM_ORB = registerLegendaryOrb("reshiram");
    public static final DeferredItem<Item> ZEKROM_ORB = registerLegendaryOrb("zekrom");
    public static final DeferredItem<Item> LANDORUS_ORB = registerLegendaryOrb("landorus");
    public static final DeferredItem<Item> KYUREM_ORB = registerLegendaryOrb("kyurem");
    
    // Generation 6
    public static final DeferredItem<Item> XERNEAS_ORB = registerLegendaryOrb("xerneas");
    public static final DeferredItem<Item> YVELTAL_ORB = registerLegendaryOrb("yveltal");
    public static final DeferredItem<Item> ZYGARDE_ORB = registerLegendaryOrb("zygarde");
    
    // Generation 7
    public static final DeferredItem<Item> TYPE_NULL_ORB = registerLegendaryOrb("type-null");
    public static final DeferredItem<Item> SILVALLY_ORB = registerLegendaryOrb("silvally");
    public static final DeferredItem<Item> TAPU_KOKO_ORB = registerLegendaryOrb("tapukoko");
    public static final DeferredItem<Item> TAPU_LELE_ORB = registerLegendaryOrb("tapulele");
    public static final DeferredItem<Item> TAPU_BULU_ORB = registerLegendaryOrb("tapubulu");
    public static final DeferredItem<Item> TAPU_FINI_ORB = registerLegendaryOrb("tapufini");
    public static final DeferredItem<Item> COSMOG_ORB = registerLegendaryOrb("cosmog");
    public static final DeferredItem<Item> COSMOEM_ORB = registerLegendaryOrb("cosmoem");
    public static final DeferredItem<Item> SOLGALEO_ORB = registerLegendaryOrb("solgaleo");
    public static final DeferredItem<Item> LUNALA_ORB = registerLegendaryOrb("lunala");
    public static final DeferredItem<Item> NECROZMA_ORB = registerLegendaryOrb("necrozma");
    
    // Generation 8
    public static final DeferredItem<Item> ZACIAN_ORB = registerLegendaryOrb("zacian");
    public static final DeferredItem<Item> ZAMAZENTA_ORB = registerLegendaryOrb("zamazenta");
    public static final DeferredItem<Item> ETERNATUS_ORB = registerLegendaryOrb("eternatus");
    public static final DeferredItem<Item> KUBFU_ORB = registerLegendaryOrb("kubfu");
    public static final DeferredItem<Item> URSHIFU_ORB = registerLegendaryOrb("urshifu");
    public static final DeferredItem<Item> REGIELEKI_ORB = registerLegendaryOrb("regieleki");
    public static final DeferredItem<Item> REGIDRAGO_ORB = registerLegendaryOrb("regidrago");
    public static final DeferredItem<Item> GLASTRIER_ORB = registerLegendaryOrb("glastrier");
    public static final DeferredItem<Item> SPECTRIER_ORB = registerLegendaryOrb("spectrier");
    public static final DeferredItem<Item> CALYREX_ORB = registerLegendaryOrb("calyrex");
    
    // Generation 9
    public static final DeferredItem<Item> WO_CHIEN_ORB = registerLegendaryOrb("wochien");
    public static final DeferredItem<Item> CHIEN_PAO_ORB = registerLegendaryOrb("chienpao");
    public static final DeferredItem<Item> TING_LU_ORB = registerLegendaryOrb("tinglu");
    public static final DeferredItem<Item> CHI_YU_ORB = registerLegendaryOrb("chiyu");
    public static final DeferredItem<Item> KORAIDON_ORB = registerLegendaryOrb("koraidon");
    public static final DeferredItem<Item> MIRAIDON_ORB = registerLegendaryOrb("miraidon");
    public static final DeferredItem<Item> OKIDOGI_ORB = registerLegendaryOrb("okidogi");
    public static final DeferredItem<Item> MUNKIDORI_ORB = registerLegendaryOrb("munkidori");
    public static final DeferredItem<Item> FEZANDIPITI_ORB = registerLegendaryOrb("fezandipiti");
    public static final DeferredItem<Item> OGERPON_ORB = registerLegendaryOrb("ogerpon");
    public static final DeferredItem<Item> TERAPAGOS_ORB = registerLegendaryOrb("terapagos");
    
    // ============================================
    // Mythical Pokémon Orbs
    // ============================================
    
    public static final DeferredItem<Item> MEW_ORB = registerMythicalOrb("mew");
    public static final DeferredItem<Item> CELEBI_ORB = registerMythicalOrb("celebi");
    public static final DeferredItem<Item> JIRACHI_ORB = registerMythicalOrb("jirachi");
    public static final DeferredItem<Item> DEOXYS_ORB = registerMythicalOrb("deoxys");
    public static final DeferredItem<Item> PHIONE_ORB = registerMythicalOrb("phione");
    public static final DeferredItem<Item> MANAPHY_ORB = registerMythicalOrb("manaphy");
    public static final DeferredItem<Item> DARKRAI_ORB = registerMythicalOrb("darkrai");
    public static final DeferredItem<Item> SHAYMIN_ORB = registerMythicalOrb("shaymin");
    public static final DeferredItem<Item> ARCEUS_ORB = registerMythicalOrb("arceus");
    public static final DeferredItem<Item> VICTINI_ORB = registerMythicalOrb("victini");
    public static final DeferredItem<Item> KELDEO_ORB = registerMythicalOrb("keldeo");
    public static final DeferredItem<Item> MELOETTA_ORB = registerMythicalOrb("meloetta");
    public static final DeferredItem<Item> GENESECT_ORB = registerMythicalOrb("genesect");
    public static final DeferredItem<Item> DIANCIE_ORB = registerMythicalOrb("diancie");
    public static final DeferredItem<Item> HOOPA_ORB = registerMythicalOrb("hoopa");
    public static final DeferredItem<Item> VOLCANION_ORB = registerMythicalOrb("volcanion");
    public static final DeferredItem<Item> MAGEARNA_ORB = registerMythicalOrb("magearna");
    public static final DeferredItem<Item> MARSHADOW_ORB = registerMythicalOrb("marshadow");
    public static final DeferredItem<Item> ZERAORA_ORB = registerMythicalOrb("zeraora");
    public static final DeferredItem<Item> MELTAN_ORB = registerMythicalOrb("meltan");
    public static final DeferredItem<Item> MELMETAL_ORB = registerMythicalOrb("melmetal");
    public static final DeferredItem<Item> ZARUDE_ORB = registerMythicalOrb("zarude");
    public static final DeferredItem<Item> PECHARUNT_ORB = registerMythicalOrb("pecharunt");
    
    // ============================================
    // Paradox Pokémon Orbs
    // ============================================
    
    // Scarlet Paradox (Past)
    public static final DeferredItem<Item> GREAT_TUSK_ORB = registerParadoxOrb("greattusk");
    public static final DeferredItem<Item> SCREAM_TAIL_ORB = registerParadoxOrb("screamtail");
    public static final DeferredItem<Item> BRUTE_BONNET_ORB = registerParadoxOrb("brutebonnet");
    public static final DeferredItem<Item> FLUTTER_MANE_ORB = registerParadoxOrb("fluttermane");
    public static final DeferredItem<Item> SLITHER_WING_ORB = registerParadoxOrb("slitherwing");
    public static final DeferredItem<Item> SANDY_SHOCKS_ORB = registerParadoxOrb("sandyshocks");
    public static final DeferredItem<Item> ROARING_MOON_ORB = registerParadoxOrb("roaringmoon");
    
    // Violet Paradox (Future)
    public static final DeferredItem<Item> IRON_TREADS_ORB = registerParadoxOrb("irontreads");
    public static final DeferredItem<Item> IRON_BUNDLE_ORB = registerParadoxOrb("ironbundle");
    public static final DeferredItem<Item> IRON_HANDS_ORB = registerParadoxOrb("ironhands");
    public static final DeferredItem<Item> IRON_JUGULIS_ORB = registerParadoxOrb("ironjugulis");
    public static final DeferredItem<Item> IRON_MOTH_ORB = registerParadoxOrb("ironmoth");
    public static final DeferredItem<Item> IRON_THORNS_ORB = registerParadoxOrb("ironthorns");
    public static final DeferredItem<Item> IRON_VALIANT_ORB = registerParadoxOrb("ironvaliant");
    
    // DLC Paradox
    public static final DeferredItem<Item> WALKING_WAKE_ORB = registerParadoxOrb("walkingwake");
    public static final DeferredItem<Item> IRON_LEAVES_ORB = registerParadoxOrb("ironleaves");
    public static final DeferredItem<Item> GOUGING_FIRE_ORB = registerParadoxOrb("gougingfire");
    public static final DeferredItem<Item> RAGING_BOLT_ORB = registerParadoxOrb("ragingbolt");
    public static final DeferredItem<Item> IRON_CROWN_ORB = registerParadoxOrb("ironcrown");
    public static final DeferredItem<Item> IRON_BOULDER_ORB = registerParadoxOrb("ironboulder");
    
    // ============================================
    // Helper Methods
    // ============================================
    
    private static DeferredItem<Item> registerItem(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }
    
    private static DeferredItem<Item> registerLegendaryOrb(String pokemonId) {
        String itemId = PokemonDataManager.toItemId(pokemonId);
        DeferredItem<Item> item = registerItem(itemId,
            () -> new SpecificPokemonOrb(pokemonId, OrbCategory.LEGENDARY));
        LEGENDARY_ORBS.put(pokemonId, item);
        return item;
    }
    
    private static DeferredItem<Item> registerMythicalOrb(String pokemonId) {
        String itemId = PokemonDataManager.toItemId(pokemonId);
        DeferredItem<Item> item = registerItem(itemId,
            () -> new SpecificPokemonOrb(pokemonId, OrbCategory.MYTHICAL));
        MYTHICAL_ORBS.put(pokemonId, item);
        return item;
    }
    
    private static DeferredItem<Item> registerParadoxOrb(String pokemonId) {
        String itemId = PokemonDataManager.toItemId(pokemonId);
        DeferredItem<Item> item = registerItem(itemId,
            () -> new SpecificPokemonOrb(pokemonId, OrbCategory.PARADOX));
        PARADOX_ORBS.put(pokemonId, item);
        return item;
    }
    
    /**
     * Get all registered legendary orbs.
     */
    public static Map<String, DeferredItem<Item>> getLegendaryOrbs() {
        return LEGENDARY_ORBS;
    }
    
    /**
     * Get all registered mythical orbs.
     */
    public static Map<String, DeferredItem<Item>> getMythicalOrbs() {
        return MYTHICAL_ORBS;
    }
    
    /**
     * Get all registered paradox orbs.
     */
    public static Map<String, DeferredItem<Item>> getParadoxOrbs() {
        return PARADOX_ORBS;
    }
    
    /**
     * Register items to the event bus.
     */
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        CobblemonOrbs.LOGGER.info("Registered {} items total", ITEMS.getEntries().size());
    }
}
