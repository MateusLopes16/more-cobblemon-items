package com.cobblemonorbs.item.api;

/**
 * Enumeration of orb categories used for classification and filtering.
 */
public enum OrbCategory {
    LEGENDARY("legendary", "Legendary"),
    MYTHICAL("mythical", "Mythical"),
    PARADOX("paradox", "Paradox"),
    SPECIAL("special", "Special"),
    RANDOM("random", "Random");
    
    private final String id;
    private final String displayName;
    
    OrbCategory(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
    
    public String getId() {
        return id;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static OrbCategory fromId(String id) {
        for (OrbCategory category : values()) {
            if (category.id.equalsIgnoreCase(id)) {
                return category;
            }
        }
        return SPECIAL;
    }
}
