package com.dndcraft.craftchat.manager;

import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import com.dndcraft.craftchat.CraftChat;
import com.dndcraft.craftchat.chat.Channels;
import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftchat.chat.Channel;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import com.dndcraft.craftcodex.api.account.Account;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChannelManager {

    private Channel GLOBAL_OOC_CHAT;
    private Channel HELP_CHAT;
    private Channel DEVELOPER_CHAT;
    private Channel RULESMITH_CHAT;
    private Channel LORESMITH_CHAT;
    private Channel BUILDER_CHAT;
    private Channel GLOBAL_EVENT_BROADCAST;
    private Channel GLOBAL_BROADCAST;
    //private List<FirmChannel> temporaryFirmChannels; Implement the ability to do floating firm channels, ex. a town announcement channel, etc etc
    //todo^
    public ChannelManager(){
        GLOBAL_OOC_CHAT = new Channel(Channels.GLOBAL_OOC);
        HELP_CHAT = new Channel(Channels.HELP_CHAT);
        DEVELOPER_CHAT = new Channel(Channels.DEVELOPER_CHAT);
        RULESMITH_CHAT = new Channel(Channels.RULESMITH_CHAT);
        LORESMITH_CHAT = new Channel(Channels.LORESMITH_CHAT);
        BUILDER_CHAT = new Channel(Channels.BUILDER_CHAT);
        GLOBAL_EVENT_BROADCAST = new Channel(Channels.GLOBAL_EVENT_BROADCAST);
        GLOBAL_BROADCAST = new Channel(Channels.GLOBAL_BROADCAST);
    }

    public void routeBroadcast(Channels forcedChannel, String text){
        switch (forcedChannel){
            case GLOBAL_BROADCAST -> GLOBAL_BROADCAST.sendCraftChatBroadcast(AtlasColor.DANDELION, text);
            case GLOBAL_EVENT_BROADCAST -> GLOBAL_EVENT_BROADCAST.sendCraftChatBroadcast(AtlasColor.LIME, text);
        }
    }

    public void routeChat(Channels channel, Player player, String text){
        Channels actualChannel = null;
        Account account = CraftCodexAPI.getApi().getAccountManager().getAccount(player.getUniqueId());
        ChatProfile profile = CraftChat.getChatProfileManager().getChatProfileByAccountID(account.getAccountID());
        if(channel == null){

            actualChannel = profile.getChannel();
        }else{
            actualChannel = channel;
        }
        switch (actualChannel){
            case GLOBAL_OOC -> GLOBAL_OOC_CHAT.sendCraftChatMessage(profile, text);
            case DEVELOPER_CHAT -> DEVELOPER_CHAT.sendCraftChatMessage(profile, text);
            case BUILDER_CHAT -> BUILDER_CHAT.sendCraftChatMessage(profile, text);
            case RULESMITH_CHAT -> RULESMITH_CHAT.sendCraftChatMessage(profile, text);
            case LORESMITH_CHAT -> LORESMITH_CHAT.sendCraftChatMessage(profile, text);
            case HELP_CHAT -> HELP_CHAT.sendCraftChatMessage(profile, text);
            case YELL -> sendInRange(Channels.YELL, player, Channels.YELL.getRange(), profile, text);
            case QUIET -> sendInRange(Channels.QUIET, player, Channels.QUIET.getRange(), profile, text);
            case SPEAK -> sendInRange(Channels.SPEAK, player, Channels.SPEAK.getRange(), profile, text);
            case WHISPER -> sendInRange(Channels.WHISPER, player, Channels.WHISPER.getRange(), profile, text);
        }
    }

    public void sendInRange(Channels channelType, Player player, int range, ChatProfile profile, String content){
        List<Player> playersInRange = new ArrayList<>();
        playersInRange.add(player);
        Bukkit.getScheduler().runTask(CraftChat.get(), () -> {
            for(Entity en : player.getNearbyEntities(range, range, range)){
                //CraftChat.get().getLogger().severe("Entity Found!: "+en.getName());
                if(en instanceof Player){
                    playersInRange.add((Player) en);
                }
            }
            var chatComponent = new BukkitComponentBuilder()
                    .append(channelType.getChannelIdentifier())
                    .append(profile.getPlayerAccount().getDNDCraftName(), profile.getChatNameColor())
                    .append(": ", AtlasColor.WHITE)
                    .append(content, profile.getMsgColor())
                    .build();
            Bukkit.getScheduler().runTaskAsynchronously(CraftChat.get(), () -> {
                for(Player p : playersInRange){
                    //CraftChat.get().getLogger().severe("Entities in list " + p.getName());
                    p.sendMessage(chatComponent);
                }
            });
        });



    }

    public void leaveChats(ChatProfile profile, Player player){
        if(player.hasPermission("craftchat.chat.ooc") && profile.isOpt_GLOBAL_OOC()){
            GLOBAL_OOC_CHAT.removePlayer(player);
            CraftChat.get().getLogger().severe("New Count: "+GLOBAL_OOC_CHAT.getPlayers().size());
            CraftChat.get().getLogger().severe("LEAVE OOC: " + player.getName());
        }
        if(player.hasPermission("craftchat.chat.dev")){
            DEVELOPER_CHAT.removePlayer(player);
            CraftChat.get().getLogger().severe("LEAVE DEV: " + player.getName());
        }
        if(player.hasPermission("craftchat.chat.mod")){
            RULESMITH_CHAT.removePlayer(player);
            CraftChat.get().getLogger().severe("LEAVE MOD: " + player.getName());
        }
        if(player.hasPermission("craftchat.chat.builder")){
            BUILDER_CHAT.removePlayer(player);
            CraftChat.get().getLogger().severe("LEAVE BUILDER: " + player.getName());
        }
        if(player.hasPermission("craftchat.chat.global_event")){
            GLOBAL_EVENT_BROADCAST.removePlayer(player);
            CraftChat.get().getLogger().severe("LEAVE GLOBAL EVENT: " + player.getName());
        }
        if(player.hasPermission("craftchat.chat.global_broadcast")){
            GLOBAL_BROADCAST.removePlayer(player);
            CraftChat.get().getLogger().severe("LEAVE GLOBAL BROADCAST: " + player.getName());
        }
        if(player.hasPermission("craftchat.chat.lore")){
            LORESMITH_CHAT.removePlayer(player);
            CraftChat.get().getLogger().severe("LEAVE LORE: " + player.getName());
        }
        if(player.hasPermission("craftchat.chat.help") && profile.isOpt_HELP_CHAT()){
            HELP_CHAT.removePlayer(player);
            CraftChat.get().getLogger().severe("LEAVE HELP: " + player.getName());
        }
    }

    public void joinChats(ChatProfile profile, Player player){
        if(player.hasPermission("craftchat.chat.ooc") && profile.isOpt_GLOBAL_OOC()){
            GLOBAL_OOC_CHAT.addPlayer(player);
            CraftChat.get().getLogger().severe("New Count: "+GLOBAL_OOC_CHAT.getPlayers().size());
            CraftChat.get().getLogger().severe("JOINED OOC: " + player.getName());
        }
        if(player.hasPermission("craftchat.chat.dev")){
            DEVELOPER_CHAT.addPlayer(player);
            CraftChat.get().getLogger().severe("JOINED DEV: " + player.getName());

        }
        if(player.hasPermission("craftchat.chat.mod")){
            RULESMITH_CHAT.addPlayer(player);
            CraftChat.get().getLogger().severe("JOINED MOD: " + player.getName());

        }
        if(player.hasPermission("craftchat.chat.builder")){
            BUILDER_CHAT.addPlayer(player);
            CraftChat.get().getLogger().severe("JOINED BUILDER: " + player.getName());

        }
        if(player.hasPermission("craftchat.chat.global_event")){
            GLOBAL_EVENT_BROADCAST.addPlayer(player);
            CraftChat.get().getLogger().severe("JOINED GLOBAL EVENT: " + player.getName());

        }
        if(player.hasPermission("craftchat.chat.global_broadcast")){
            GLOBAL_BROADCAST.addPlayer(player);
            CraftChat.get().getLogger().severe("JOINED GLOBAL BROADCAST: " + player.getName());

        }
        if(player.hasPermission("craftchat.chat.lore")){
            LORESMITH_CHAT.addPlayer(player);
            CraftChat.get().getLogger().severe("JOINED LORE: " + player.getName());

        }
        if(player.hasPermission("craftchat.chat.help") && profile.isOpt_HELP_CHAT()){
            HELP_CHAT.addPlayer(player);
            CraftChat.get().getLogger().severe("JOINED HELP: " + player.getName());

        }
    }

}
