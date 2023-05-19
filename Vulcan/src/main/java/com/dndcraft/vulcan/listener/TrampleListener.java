package com.dndcraft.vulcan.listener;

import com.dndcraft.vulcan.Vulcan;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class TrampleListener implements Listener {

    private final Vulcan plugin;

    @EventHandler
    public void onTrample(PlayerInteractEvent e){
        if(e.getAction() == Action.PHYSICAL){
            if (e.getClickedBlock().getBlockData().getMaterial() == Material.FARMLAND){
                e.setUseInteractedBlock(Event.Result.DENY);
                e.setCancelled(true);
            }
        }
    }

}
