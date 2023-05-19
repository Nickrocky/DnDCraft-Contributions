package com.dndcraft.prometheus.listener;

import com.dndcraft.prometheus.util.RankComponents;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler (ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onChat(AsyncChatEvent e){
        e.setCancelled(true);
        Component playerMessage = e.originalMessage();
        Component playerPrefixByRank = RankComponents.getRank(e.getPlayer());
        Component playerName = e.getPlayer().name();
        playerName.color(NamedTextColor.GRAY);

        for(Audience player : e.viewers()){
            player.sendMessage(playerPrefixByRank.append(playerName).append(Component.text(": ")).append(playerMessage));
        }

    }

}
