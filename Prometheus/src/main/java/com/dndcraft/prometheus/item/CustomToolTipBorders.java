package com.dndcraft.prometheus.item;

import com.dndcraft.atlas.util.AtlasColor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CustomToolTipBorders {
    ORANGE(AtlasColor.RSV_LEGENDARY_RARITY),
    YELLOW(AtlasColor.YELLOW),
    LIME(AtlasColor.LIME),
    GREEN(AtlasColor.RSV_UNCOMMON_RARITY),
    AQUA(AtlasColor.AQUA),
    BLUE(AtlasColor.RSV_RARE_RARITY),
    PURPLE(AtlasColor.RSV_EPIC_RARITY),
    WHITE(AtlasColor.RSV_COMMON_RARITY),
    BROWN(AtlasColor.BROWN),
    RED(AtlasColor.RSV_RELIC_RARITY),
    GRAY(AtlasColor.GRAY);

    private final AtlasColor color;

}
