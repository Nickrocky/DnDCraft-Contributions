package com.dndcraft.prometheus.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public enum RankComponents {

    ADMIN("prometheus.admin", 'ꌄ'),
    DEVELOPER("prometheus.developer", 'ꌂ'),
    //Moderator
    BUILDER("prometheus.builder", 'ꌅ'),
    //Event Team
    PLAYER("prometheus.player", 'ꌞ'),
    ;

    private final String pex;
    private final char special;

    public static Component getRank(Player player){
        for(RankComponents components : RankComponents.values()){
            if(player.hasPermission(components.getPex())) return Component.text(components.getSpecial(), NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false);
        }
        return Component.text(PLAYER.getSpecial(), NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false);
    }

}
