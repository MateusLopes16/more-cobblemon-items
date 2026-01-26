# Cobblemon Legendary Orbs

**Cobblemon Legendary Orbs** is a lightweight, configurable Minecraft mod that introduces **unique spawn‑trigger items** ("Legendary Orbs") for Pokémon in Cobblemon.  
Consume an orb to spawn the corresponding Pokémon near the player.

This mod is designed for **server admins**, **modpack creators**, and **players** who want a controlled, lore‑friendly way to obtain rare Pokémon. While it focuses on Legendaries and Mythicals by default, **admins can add orbs for any Pokémon that exists in Cobblemon**.

---

## ✨ Features

- Create orbs for any Pokémon (Legendaries, Mythicals, or regular Pokémon)  
- Consume to spawn the Pokémon instantly  
- Fully configurable through JSON  
- Optional crafting per orb  
- Customizable orb designs and textures  
- Requires Cobblemon  
- Admin‑friendly configuration

---

## 🎁 What the Mod Provides

By default, this mod includes:

- **All Legendary/Mythical/Paradox Orbs** – Individual orbs for each Legendary, Mythical and Paradox Pokémon
- **Shiny Orb** – Spawns a random shiny Mythical or Legendary or Paradox Pokémon  
- **Ultimate Orb** – Spawns a random Mythical or Legendary or Paradox Pokémon with full 31 IVs  
- **Ultimate Shiny Orb** – Spawns a random shiny Mythical or Legendary or Paradox Pokémon with full 31 IVs
- **Paradox Shiny Orb** – Spawns a random shiny Paradox Pokémon  
- **Paradox Ultimate Orb** – Spawns a random Paradox Pokémon with full 31 IVs  
- **Paradox Ultimate Shiny Orb** – Spawns a random shiny Paradox Pokémon with full 31 IVs
- **Mythical Shiny Orb** – Spawns a random shiny Mythical Pokémon  
- **Mythical Ultimate Orb** – Spawns a random Mythical Pokémon with full 31 IVs  
- **Mythical Ultimate Shiny Orb** – Spawns a random shiny Mythical Pokémon with full 31 IVs
- **Legendary Shiny Orb** – Spawns a random shiny Legendary Pokémon  
- **Legendary Ultimate Orb** – Spawns a random Legendary Pokémon with full 31 IVs  
- **Legendary Ultimate Shiny Orb** – Spawns a random shiny Legendary Pokémon with full 31 IVs

---

## 📦 Requirements

- Minecraft (matching your mod version)  
- Cobblemon  
- Fabric / Forge / NeoForge (depending on your build)

---

## ⚙️ Configuration

All configuration is handled through a JSON file.  
Admins can freely edit:

- Which Pokémon have orbs (any Pokémon in Cobblemon)  
- Whether orbs are craftable  
- Crafting recipes  
- Spawn settings (level, shiny probability, radius, etc.)  
- Orb design/texture paths

### Base Template

```
{
  "legendaries": [
    {
      "pokemon": "pokemon_name",
      "craftableorb": true,
      "craft": {
        "type": "shaped",
        "pattern": [
          " A ",
          "ABA",
          " A "
        ],
        "keys": {
          "A": "minecraft:diamond",
          "B": "minecraft:nether_star"
        }
      },
      "spawn": {
        "shiny": 0.001,
        "level": 70,
        "radius": 5
      },
      "design": {
        "path": "textures/items/orbs/default.png"
      }
    }
  ]
}
```

### Configuration Details

#### Spawn Settings
- **shiny**: Probability of the Pokémon being shiny (0.0 to 1.0). Default: `0.001` (0.1% chance)  
- **level**: The level at which the Pokémon will spawn  
- **radius**: Spawn radius around the player in blocks

#### Design Path
- **path**: Specifies the texture file for the orb item  
- Default designs are located in `textures/items/orbs/default.png`  
- Custom textures can be placed in your resource pack and referenced here  
- Example: `"path": "textures/items/orbs/custom/mewtwo_orb.png"`

#### Adding Orbs for Any Pokémon
Admins can create orbs for **any Pokémon** that exists in Cobblemon, not just Legendaries or Mythicals. Simply add a new entry with the Pokémon's name to the configuration file.

## 📥 Installation

1. Install Cobblemon  
2. Drop this mod’s `.jar` into your `mods/` folder  
3. Launch the game  
4. Edit the config file if needed  
5. Enjoy spawning your favorite legendaries

