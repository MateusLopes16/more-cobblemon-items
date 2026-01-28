package com.cobblemonorbs.item.api;

import java.util.Random;

/**
 * Represents spawn settings for a Pokémon orb.
 */
public record SpawnSettings(
    int minLevel,
    int maxLevel,
    double shinyChance,
    boolean forceShiny,
    boolean forcePerfectIVs,
    int spawnRadius
) {
    private static final Random RANDOM = new Random();
    
    /**
     * Default spawn settings for standard orbs (random level 1-100).
     */
    public static final SpawnSettings DEFAULT = new SpawnSettings(1, 100, 0.001, false, false, 5);
    
    /**
     * Spawn settings for shiny orbs (guaranteed shiny, random level 1-100).
     */
    public static final SpawnSettings SHINY = new SpawnSettings(1, 100, 1.0, true, false, 5);
    
    /**
     * Spawn settings for ultimate orbs (perfect IVs, random level 1-100).
     */
    public static final SpawnSettings ULTIMATE = new SpawnSettings(1, 100, 0.001, false, true, 5);
    
    /**
     * Spawn settings for ultimate shiny orbs (shiny + perfect IVs, random level 1-100).
     */
    public static final SpawnSettings ULTIMATE_SHINY = new SpawnSettings(1, 100, 1.0, true, true, 5);
    
    /**
     * Gets a random level between minLevel and maxLevel (inclusive).
     */
    public int getRandomLevel() {
        if (minLevel == maxLevel) {
            return minLevel;
        }
        return RANDOM.nextInt(maxLevel - minLevel + 1) + minLevel;
    }
    
    /**
     * Legacy method for backwards compatibility - returns a random level.
     */
    public int level() {
        return getRandomLevel();
    }
    
    /**
     * Creates a builder for custom spawn settings.
     */
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private int minLevel = 1;
        private int maxLevel = 100;
        private double shinyChance = 0.001;
        private boolean forceShiny = false;
        private boolean forcePerfectIVs = false;
        private int spawnRadius = 5;
        
        public Builder levelRange(int min, int max) {
            this.minLevel = min;
            this.maxLevel = max;
            return this;
        }
        
        public Builder fixedLevel(int level) {
            this.minLevel = level;
            this.maxLevel = level;
            return this;
        }
        
        public Builder shinyChance(double chance) {
            this.shinyChance = chance;
            return this;
        }
        
        public Builder forceShiny(boolean force) {
            this.forceShiny = force;
            if (force) {
                this.shinyChance = 1.0;
            }
            return this;
        }
        
        public Builder forcePerfectIVs(boolean force) {
            this.forcePerfectIVs = force;
            return this;
        }
        
        public Builder spawnRadius(int radius) {
            this.spawnRadius = radius;
            return this;
        }
        
        public SpawnSettings build() {
            return new SpawnSettings(minLevel, maxLevel, shinyChance, forceShiny, forcePerfectIVs, spawnRadius);
        }
    }
}
