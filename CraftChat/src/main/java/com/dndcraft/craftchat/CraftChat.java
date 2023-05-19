package com.dndcraft.craftchat;

import com.dndcraft.atlas.command.Commands;
import com.dndcraft.craftchat.command.ChatCommand;
import com.dndcraft.craftchat.command.FriendCommand;
import com.dndcraft.craftchat.listener.CraftChatListener;
import com.dndcraft.craftchat.manager.ChannelManager;
import com.dndcraft.craftchat.manager.ChatProfileManager;
import com.dndcraft.craftchat.storage.CraftStorage;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import lombok.SneakyThrows;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftChat extends JavaPlugin {

    private static CraftChat instance;
    public static CraftChat get() {
        return instance;
    }

    private static ChannelManager channelManager;
    public static ChannelManager getChannelManager(){
        return channelManager;
    }

    private static ChatProfileManager chatProfileManager;
    public static ChatProfileManager getChatProfileManager(){
        return chatProfileManager;
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
        chatProfileManager = new ChatProfileManager();
        channelManager = new ChannelManager();
        CraftStorage.start();
        getLogger().severe("CraftCodexAPI-Core: " + ((CraftCodexAPI.getApi() != null) ? "TRUE" : "FALSE"));
        Commands.build(getCommand("friend"), FriendCommand::new);
        Commands.build(getCommand("chat"), ChatCommand::new);
        getServer().getPluginManager().registerEvents(new CraftChatListener(), this);

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
