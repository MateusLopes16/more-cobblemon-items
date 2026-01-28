package com.cobblemonorbs.item;

import com.cobblemonorbs.item.api.OrbCategory;
import com.cobblemonorbs.item.api.SpawnSettings;
import com.cobblemonorbs.item.impl.RandomCategoryOrb;
import com.cobblemonorbs.item.impl.SpecificPokemonOrb;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

/**
 * Factory class for creating orb items with fluent API.
 * Makes it easy to create new orbs with custom settings.
 */
public class OrbItemFactory {
    
    /**
     * Creates a supplier for a specific Pokémon orb with default settings.
     */
    public static Supplier<Item> createSpecific(String pokemonId, OrbCategory category) {
        return () -> new SpecificPokemonOrb(pokemonId, category);
    }
    
    /**
     * Creates a supplier for a specific Pokémon orb with custom spawn settings.
     */
    public static Supplier<Item> createSpecific(String pokemonId, OrbCategory category, SpawnSettings settings) {
        return () -> new SpecificPokemonOrb(pokemonId, category, settings);
    }
    
    /**
     * Creates a supplier for a specific Pokémon orb that spawns shiny.
     */
    public static Supplier<Item> createSpecificShiny(String pokemonId, OrbCategory category) {
        return () -> new SpecificPokemonOrb(pokemonId, category, SpawnSettings.SHINY);
    }
    
    /**
     * Creates a supplier for a specific Pokémon orb with perfect IVs.
     */
    public static Supplier<Item> createSpecificUltimate(String pokemonId, OrbCategory category) {
        return () -> new SpecificPokemonOrb(pokemonId, category, SpawnSettings.ULTIMATE);
    }
    
    /**
     * Creates a supplier for a specific Pokémon orb that's shiny with perfect IVs.
     */
    public static Supplier<Item> createSpecificUltimateShiny(String pokemonId, OrbCategory category) {
        return () -> new SpecificPokemonOrb(pokemonId, category, SpawnSettings.ULTIMATE_SHINY);
    }
    
    /**
     * Creates a supplier for a random category orb with default settings.
     */
    public static Supplier<Item> createRandom(RandomCategoryOrb.SelectionCategory selectionCategory) {
        return () -> new RandomCategoryOrb(selectionCategory, SpawnSettings.DEFAULT);
    }
    
    /**
     * Creates a supplier for a random category orb with custom settings.
     */
    public static Supplier<Item> createRandom(RandomCategoryOrb.SelectionCategory selectionCategory, SpawnSettings settings) {
        return () -> new RandomCategoryOrb(selectionCategory, settings);
    }
    
    /**
     * Creates a supplier for a random category shiny orb.
     */
    public static Supplier<Item> createRandomShiny(RandomCategoryOrb.SelectionCategory selectionCategory) {
        return () -> new RandomCategoryOrb(selectionCategory, SpawnSettings.SHINY);
    }
    
    /**
     * Creates a supplier for a random category ultimate orb (perfect IVs).
     */
    public static Supplier<Item> createRandomUltimate(RandomCategoryOrb.SelectionCategory selectionCategory) {
        return () -> new RandomCategoryOrb(selectionCategory, SpawnSettings.ULTIMATE);
    }
    
    /**
     * Creates a supplier for a random category ultimate shiny orb.
     */
    public static Supplier<Item> createRandomUltimateShiny(RandomCategoryOrb.SelectionCategory selectionCategory) {
        return () -> new RandomCategoryOrb(selectionCategory, SpawnSettings.ULTIMATE_SHINY);
    }
    
    /**
     * Builder for creating orbs with custom configurations.
     */
    public static class OrbBuilder {
        private String pokemonId;
        private OrbCategory category = OrbCategory.SPECIAL;
        private SpawnSettings.Builder settingsBuilder = SpawnSettings.builder();
        
        public OrbBuilder pokemonId(String pokemonId) {
            this.pokemonId = pokemonId;
            return this;
        }
        
        public OrbBuilder category(OrbCategory category) {
            this.category = category;
            return this;
        }
        
        public OrbBuilder level(int level) {
            this.settingsBuilder.fixedLevel(level);
            return this;
        }
        
        public OrbBuilder levelRange(int minLevel, int maxLevel) {
            this.settingsBuilder.levelRange(minLevel, maxLevel);
            return this;
        }
        
        public OrbBuilder shiny() {
            this.settingsBuilder.forceShiny(true);
            return this;
        }
        
        public OrbBuilder perfectIVs() {
            this.settingsBuilder.forcePerfectIVs(true);
            return this;
        }
        
        public OrbBuilder shinyChance(double chance) {
            this.settingsBuilder.shinyChance(chance);
            return this;
        }
        
        public OrbBuilder spawnRadius(int radius) {
            this.settingsBuilder.spawnRadius(radius);
            return this;
        }
        
        public Supplier<Item> build() {
            SpawnSettings settings = settingsBuilder.build();
            return () -> new SpecificPokemonOrb(pokemonId, category, settings);
        }
    }
    
    /**
     * Creates a new orb builder for fluent configuration.
     */
    public static OrbBuilder builder() {
        return new OrbBuilder();
    }
}
