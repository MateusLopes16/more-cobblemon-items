package com.cobblemonorbs.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Configuration for Cobblemon Legendary Orbs.
 */
public class OrbConfig {
    
    public static final ModConfigSpec SPEC;
    
    // Spawn Settings
    public static final ModConfigSpec.IntValue DEFAULT_SPAWN_LEVEL;
    public static final ModConfigSpec.DoubleValue DEFAULT_SHINY_CHANCE;
    public static final ModConfigSpec.IntValue DEFAULT_SPAWN_RADIUS;
    
    // Feature Toggles
    public static final ModConfigSpec.BooleanValue ENABLE_LEGENDARY_ORBS;
    public static final ModConfigSpec.BooleanValue ENABLE_MYTHICAL_ORBS;
    public static final ModConfigSpec.BooleanValue ENABLE_PARADOX_ORBS;
    public static final ModConfigSpec.BooleanValue ENABLE_RANDOM_ORBS;
    
    // Gameplay Settings
    public static final ModConfigSpec.BooleanValue CONSUME_ON_USE;
    public static final ModConfigSpec.BooleanValue SHOW_PARTICLE_EFFECTS;
    public static final ModConfigSpec.BooleanValue BROADCAST_SPAWNS;
    
    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        
        builder.comment("Cobblemon Legendary Orbs Configuration")
               .push("general");
        
        // Spawn Settings
        builder.comment("Default spawn settings for orbs")
               .push("spawn");
        
        DEFAULT_SPAWN_LEVEL = builder
            .comment("Default level for spawned Pokémon")
            .defineInRange("defaultLevel", 70, 1, 100);
        
        DEFAULT_SHINY_CHANCE = builder
            .comment("Default shiny chance for non-shiny orbs (0.0 to 1.0)")
            .defineInRange("defaultShinyChance", 0.001, 0.0, 1.0);
        
        DEFAULT_SPAWN_RADIUS = builder
            .comment("Default spawn radius around the player (in blocks)")
            .defineInRange("defaultSpawnRadius", 5, 1, 20);
        
        builder.pop();
        
        // Feature Toggles
        builder.comment("Enable or disable orb categories")
               .push("features");
        
        ENABLE_LEGENDARY_ORBS = builder
            .comment("Enable individual Legendary Pokémon orbs")
            .define("enableLegendaryOrbs", true);
        
        ENABLE_MYTHICAL_ORBS = builder
            .comment("Enable individual Mythical Pokémon orbs")
            .define("enableMythicalOrbs", true);
        
        ENABLE_PARADOX_ORBS = builder
            .comment("Enable individual Paradox Pokémon orbs")
            .define("enableParadoxOrbs", true);
        
        ENABLE_RANDOM_ORBS = builder
            .comment("Enable random category orbs (Shiny Orb, Ultimate Orb, etc.)")
            .define("enableRandomOrbs", true);
        
        builder.pop();
        
        // Gameplay Settings
        builder.comment("Gameplay settings")
               .push("gameplay");
        
        CONSUME_ON_USE = builder
            .comment("Whether orbs are consumed when used")
            .define("consumeOnUse", true);
        
        SHOW_PARTICLE_EFFECTS = builder
            .comment("Show particle effects when spawning Pokémon")
            .define("showParticleEffects", true);
        
        BROADCAST_SPAWNS = builder
            .comment("Broadcast to all players when a legendary/mythical is spawned")
            .define("broadcastSpawns", false);
        
        builder.pop();
        
        builder.pop(); // general
        
        SPEC = builder.build();
    }
}
