package com.cobblemonorbs.item.impl;

import com.cobblemonorbs.item.api.OrbCategory;
import com.cobblemonorbs.item.api.SpawnSettings;
import com.cobblemonorbs.item.base.BaseOrbItem;

/**
 * A specific orb that spawns a single, predetermined Pokémon.
 * Used for individual Legendary, Mythical, and Paradox Pokémon orbs.
 */
public class SpecificPokemonOrb extends BaseOrbItem {
    
    public SpecificPokemonOrb(String pokemonId, OrbCategory category) {
        super(pokemonId, category, SpawnSettings.DEFAULT);
    }
    
    public SpecificPokemonOrb(String pokemonId, OrbCategory category, SpawnSettings settings) {
        super(pokemonId, category, settings);
    }
    
    @Override
    protected String getTargetPokemon() {
        return pokemonId;
    }
}
