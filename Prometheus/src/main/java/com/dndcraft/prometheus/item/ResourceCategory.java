package com.dndcraft.prometheus.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourceCategory {

    CONSUMABLE('ꌓ'),
    CRAFTING_MATERIAL('ꌔ'),
    INGREDIENT('ꌖ'),
    MISCELLANEOUS('ꌘ'),
    FLORA('ꌕ'),
    INSTRUMENT('ꌗ'),
    TOOLS('ꌛ'),
    WEAPONS('ꌜ'),
    REAGENT('ꌙ'),
    ROCKS_N_MINERALS('ꌚ'),
    BUILDING_RESOURCE('ꌐ'),
    ;

    private final char resourceCatChar;
}
