package com.dndcraft.vulcan.managers.warp;

import com.dndcraft.vulcan.Vulcan;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

/**
 * @author Commissar_Voop
 */

public class WarpListeners implements Listener {

    /**
     * Event check for the warps
     * @param e Bukkit Event
     */
    @EventHandler
    void onWorldLoad(WorldLoadEvent e) {
        Vulcan.get().getWarpManager().eventExecuteOnLoad(e.getWorld());
    }

    /**
     * Event check for the warps
     * @param e Bukkit Event
     */
    @EventHandler
    void onWorldUnLoad(WorldUnloadEvent e) {
        Vulcan.get().getWarpManager().eventExecuteOnUnLoad(e.getWorld());
    }
}
