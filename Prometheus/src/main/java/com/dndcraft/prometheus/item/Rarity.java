package com.dndcraft.prometheus.item;

import com.dndcraft.prometheus.api.item.BorderColor;
import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import lombok.Getter;
import net.kyori.adventure.text.Component;

import static com.dndcraft.atlas.util.AtlasColor.*;

/**
 * This file mirrors GaiaRarity in Gaia
 * Why is it here, bc Its awkward to do weird shit when you need these components fuck off
 * */

@Getter
public enum Rarity {

    COMMON(RSV_COMMON_RARITY, "Common Item", BorderColor.WHITE),
    UNCOMMON(RSV_UNCOMMON_RARITY, "Uncommon Item", BorderColor.GREEN),
    RARE(RSV_RARE_RARITY, "Rare Item", BorderColor.BLUE),
    EPIC(RSV_EPIC_RARITY, "Epic Item", BorderColor.PURPLE),
    LEGENDARY(RSV_LEGENDARY_RARITY, "Legendary Item", BorderColor.ORANGE),
    RELIC(RSV_RELIC_RARITY, "Relic Item", BorderColor.RED),
    ;

    private final AtlasColor color;
    private final Component rarityComponent;
    private final String rarityText;
    private final BorderColor borderColor;

    Rarity(AtlasColor color, String rarityText, BorderColor borderColor){
        this.color = color;
        this.rarityText = rarityText;
        this.borderColor = borderColor;
        rarityComponent = new BukkitComponentBuilder().append(rarityText, color).build();
    }
}
