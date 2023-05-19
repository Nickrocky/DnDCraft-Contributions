package com.dndcraft.craftchat.listener;

import com.dndcraft.craftchat.CraftChat;
import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import com.dndcraft.craftcodex.api.account.Account;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CraftChatListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Account account = CraftCodexAPI.getApi().getAccountManager().getAccount(e.getPlayer().getUniqueId());
        if(!CraftChat.getChatProfileManager().exists(account.getAccountID())){
            CraftChat.getChatProfileManager().createChatProfile(account.getAccountID());
        }else{
            CraftChat.getChatProfileManager().loadChatProfile(account.getAccountID());
        }
        CraftChat.getChannelManager().joinChats(CraftChat.getChatProfileManager().getChatProfileByAccountID(account.getAccountID()), e.getPlayer());
        e.joinMessage(Component.text(""));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Account account = CraftCodexAPI.getApi().getAccountManager().getAccount(e.getPlayer().getUniqueId());
        ChatProfile profile = CraftChat.getChatProfileManager().getChatProfileByAccountID(account.getAccountID());
        //CraftChat.getChatProfileManager().saveChatProfile();
        CraftChat.getChannelManager().leaveChats(profile, e.getPlayer());
    }

    @EventHandler
    public void playerChatEvent(AsyncChatEvent e){
        e.setCancelled(true);
        TextComponent textComponent = (TextComponent) e.originalMessage();
        CraftChat.getChannelManager().routeChat(null, e.getPlayer(), textComponent.content());
    }

}
