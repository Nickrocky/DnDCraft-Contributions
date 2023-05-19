package com.dndcraft.craftcodex.api.util.interactions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ManaInteractions {

    REGENERATED("Mana Regenerated"),
    SPENT("Mana Spent"),
    GAINED("Mana Gained"),
    ;

    /**
     * What particularly is happening in this interaction
     * */
    private final String reasonText;

}
