package com.dndcraft.craftcodex.listener;

import com.dndcraft.atlas.util.objects.AtlasLocation;
import com.dndcraft.craftcodex.CraftCodex;
import com.dndcraft.craftcodex.account.Account;
import com.dndcraft.craftcodex.storage.CraftStorage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        CraftCodex.getCCAccountManager().loadOrCreate(e.getPlayer());
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent e){
        //Session code
        Account account = CraftCodex.getCCAccountManager().getAccount(e.getPlayer().getUniqueId());
        account.getSession().setLogoutLocation(new AtlasLocation(e.getPlayer().getLocation()));
        account.getSession().setLogoutTime();
        CraftStorage.addEntry(account.getSession());
        //-------
    }

}
