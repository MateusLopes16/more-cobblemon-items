package com.cobblemonorbs.util;

import com.cobblemonorbs.CobblemonOrbs;
import com.cobblemonorbs.data.PokemonDataManager;
import com.cobblemonorbs.item.api.OrbCategory;

/**
 * Utility class for common operations.
 */
public class OrbUtils {
    
    /**
     * Determines the category for a Pokémon based on its ID.
     */
    public static OrbCategory getCategoryForPokemon(String pokemonId) {
        if (PokemonDataManager.isLegendary(pokemonId)) {
            return OrbCategory.LEGENDARY;
        } else if (PokemonDataManager.isMythical(pokemonId)) {
            return OrbCategory.MYTHICAL;
        } else if (PokemonDataManager.isParadox(pokemonId)) {
            return OrbCategory.PARADOX;
        }
        return OrbCategory.SPECIAL;
    }
    
    /**
     * Converts a Pokémon species name to a translation key.
     * E.g., "mewtwo" -> "item.cobblemonorbs.mewtwo_orb"
     */
    public static String getTranslationKey(String pokemonId) {
        String itemId = PokemonDataManager.toItemId(pokemonId);
        return "item." + CobblemonOrbs.MOD_ID + "." + itemId;
    }
    
    /**
     * Formats a Pokémon name for display.
     * Handles special cases like "ho-oh" -> "Ho-Oh"
     */
    public static String formatPokemonName(String pokemonId) {
        if (pokemonId == null || pokemonId.isEmpty()) {
            return "";
        }
        
        // Special cases
        return switch (pokemonId.toLowerCase()) {
            case "ho-oh" -> "Ho-Oh";
            case "type-null" -> "Type: Null";
            case "tapukoko" -> "Tapu Koko";
            case "tapulele" -> "Tapu Lele";
            case "tapubulu" -> "Tapu Bulu";
            case "tapufini" -> "Tapu Fini";
            case "wochien" -> "Wo-Chien";
            case "chienpao" -> "Chien-Pao";
            case "tinglu" -> "Ting-Lu";
            case "chiyu" -> "Chi-Yu";
            case "greattusk" -> "Great Tusk";
            case "screamtail" -> "Scream Tail";
            case "brutebonnet" -> "Brute Bonnet";
            case "fluttermane" -> "Flutter Mane";
            case "slitherwing" -> "Slither Wing";
            case "sandyshocks" -> "Sandy Shocks";
            case "roaringmoon" -> "Roaring Moon";
            case "irontreads" -> "Iron Treads";
            case "ironbundle" -> "Iron Bundle";
            case "ironhands" -> "Iron Hands";
            case "ironjugulis" -> "Iron Jugulis";
            case "ironmoth" -> "Iron Moth";
            case "ironthorns" -> "Iron Thorns";
            case "ironvaliant" -> "Iron Valiant";
            case "walkingwake" -> "Walking Wake";
            case "ironleaves" -> "Iron Leaves";
            case "gougingfire" -> "Gouging Fire";
            case "ragingbolt" -> "Raging Bolt";
            case "ironcrown" -> "Iron Crown";
            case "ironboulder" -> "Iron Boulder";
            default -> PokemonDataManager.toDisplayName(pokemonId);
        };
    }
    
    /**
     * Validates that a Pokémon ID is valid format.
     */
    public static boolean isValidPokemonId(String pokemonId) {
        if (pokemonId == null || pokemonId.isEmpty()) {
            return false;
        }
        // Pokémon IDs should be lowercase with only letters, numbers, and hyphens
        return pokemonId.matches("[a-z][a-z0-9\\-]*");
    }
}
