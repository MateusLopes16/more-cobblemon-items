package com.cobblemonorbs.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the lists of Pokémon by category.
 * Contains all Legendary, Mythical, and Paradox Pokémon identifiers.
 */
public class PokemonDataManager {
    
    // All Legendary Pokémon
    private static final List<String> LEGENDARY_POKEMON = List.of(
        // Generation 1
        "articuno", "zapdos", "moltres", "mewtwo",
        // Generation 2
        "raikou", "entei", "suicune", "lugia", "ho-oh",
        // Generation 3
        "regirock", "regice", "registeel", "latias", "latios", 
        "kyogre", "groudon", "rayquaza",
        // Generation 4
        "uxie", "mesprit", "azelf", "dialga", "palkia", "heatran", 
        "regigigas", "giratina", "cresselia",
        // Generation 5
        "cobalion", "terrakion", "virizion", "tornadus", "thundurus", 
        "reshiram", "zekrom", "landorus", "kyurem",
        // Generation 6
        "xerneas", "yveltal", "zygarde",
        // Generation 7
        "type-null", "silvally", "tapukoko", "tapulele", "tapubulu", "tapufini",
        "cosmog", "cosmoem", "solgaleo", "lunala", "necrozma",
        // Generation 8
        "zacian", "zamazenta", "eternatus", "kubfu", "urshifu",
        "regieleki", "regidrago", "glastrier", "spectrier", "calyrex",
        // Generation 9
        "wochien", "chienpao", "tinglu", "chiyu",
        "koraidon", "miraidon", "okidogi", "munkidori", "fezandipiti",
        "ogerpon", "terapagos"
    );
    
    // All Mythical Pokémon
    private static final List<String> MYTHICAL_POKEMON = List.of(
        // Generation 1
        "mew",
        // Generation 2
        "celebi",
        // Generation 3
        "jirachi", "deoxys",
        // Generation 4
        "phione", "manaphy", "darkrai", "shaymin", "arceus",
        // Generation 5
        "victini", "keldeo", "meloetta", "genesect",
        // Generation 6
        "diancie", "hoopa", "volcanion",
        // Generation 7
        "magearna", "marshadow", "zeraora",
        // Generation 8
        "meltan", "melmetal", "zarude",
        // Generation 9
        "pecharunt"
    );
    
    // All Paradox Pokémon
    private static final List<String> PARADOX_POKEMON = List.of(
        // Scarlet Paradox (Past)
        "greattusk", "screamtail", "brutebonnet", "fluttermane",
        "slitherwing", "sandyshocks", "roaringmoon",
        // Violet Paradox (Future)
        "irontreads", "ironbundle", "ironhands", "ironjugulis",
        "ironmoth", "ironthorns", "ironvaliant",
        // Teal Mask / Indigo Disk
        "walkingwake", "ironleaves", "gougingfire", "ragingbolt", "ironcrown", "ironboulder"
    );
    
    private static List<String> allSpecialPokemon;
    
    /**
     * Get all Legendary Pokémon identifiers.
     */
    public static List<String> getLegendaryPokemon() {
        return Collections.unmodifiableList(LEGENDARY_POKEMON);
    }
    
    /**
     * Get all Mythical Pokémon identifiers.
     */
    public static List<String> getMythicalPokemon() {
        return Collections.unmodifiableList(MYTHICAL_POKEMON);
    }
    
    /**
     * Get all Paradox Pokémon identifiers.
     */
    public static List<String> getParadoxPokemon() {
        return Collections.unmodifiableList(PARADOX_POKEMON);
    }
    
    /**
     * Get all special Pokémon (Legendary + Mythical + Paradox).
     */
    public static List<String> getAllSpecialPokemon() {
        if (allSpecialPokemon == null) {
            allSpecialPokemon = new ArrayList<>();
            allSpecialPokemon.addAll(LEGENDARY_POKEMON);
            allSpecialPokemon.addAll(MYTHICAL_POKEMON);
            allSpecialPokemon.addAll(PARADOX_POKEMON);
            allSpecialPokemon = Collections.unmodifiableList(allSpecialPokemon);
        }
        return allSpecialPokemon;
    }
    
    /**
     * Check if a Pokémon is a Legendary.
     */
    public static boolean isLegendary(String pokemonId) {
        return LEGENDARY_POKEMON.contains(pokemonId.toLowerCase());
    }
    
    /**
     * Check if a Pokémon is a Mythical.
     */
    public static boolean isMythical(String pokemonId) {
        return MYTHICAL_POKEMON.contains(pokemonId.toLowerCase());
    }
    
    /**
     * Check if a Pokémon is a Paradox Pokémon.
     */
    public static boolean isParadox(String pokemonId) {
        return PARADOX_POKEMON.contains(pokemonId.toLowerCase());
    }
    
    /**
     * Get the total count of all special Pokémon.
     */
    public static int getTotalSpecialCount() {
        return LEGENDARY_POKEMON.size() + MYTHICAL_POKEMON.size() + PARADOX_POKEMON.size();
    }
    
    /**
     * Convert Pokémon ID to a display-friendly name.
     * E.g., "mewtwo" -> "Mewtwo", "ho-oh" -> "Ho Oh"
     */
    public static String toDisplayName(String pokemonId) {
        if (pokemonId == null || pokemonId.isEmpty()) {
            return "";
        }
        
        String[] parts = pokemonId.split("[\\-_]");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                result.append(" ");
            }
            String part = parts[i];
            if (!part.isEmpty()) {
                result.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    result.append(part.substring(1).toLowerCase());
                }
            }
        }
        
        return result.toString();
    }
    
    /**
     * Convert a Pokémon ID to an item ID format.
     * E.g., "mewtwo" -> "mewtwo_orb", "ho-oh" -> "ho_oh_orb"
     */
    public static String toItemId(String pokemonId) {
        return pokemonId.toLowerCase().replace("-", "_") + "_orb";
    }
}
