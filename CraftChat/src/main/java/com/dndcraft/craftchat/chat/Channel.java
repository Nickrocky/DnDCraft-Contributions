package com.dndcraft.craftchat.chat;

import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import com.dndcraft.craftchat.CraftChat;
import lombok.Getter;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A firm channel is a channel with an audience either defined by permissions, or opt-in availability
 * Channels like Yell, Whisper, etc are called soft-channels as they are dynamically made by range.
 * */
public class Channel implements ForwardingAudience {

    @Getter private List<Player> players;
    private Channels channel;

    public Channel(Channels channel){
        this.channel = channel;
        this.players = new ArrayList<>();
    }

    public Channel(Channels channel, List<Player> players){
        this.players = players;
        this.channel = channel;
    }

    public Channel(Channels channel, Player... players){
        this.players = Arrays.asList(players);
        this.channel = channel;
    }

    @Override
    public @NotNull Iterable<? extends Audience> audiences() {
        return players;
    }


    public void sendCraftChatBroadcast(AtlasColor color, String content){
        var chatComponent = new BukkitComponentBuilder()
                .append(channel.channelIdentifier)
                .append(": ", AtlasColor.WHITE)
                .append(content, color)
                .build();
        players.stream().forEach(p -> p.sendMessage(chatComponent));
    }

    public void sendCraftChatMessage(ChatProfile from, String content){
        CraftChat.get().getLogger().severe("Temp 3");
        CraftChat.get().getLogger().severe("Message in chat type of: " + from.getChannel().name());
        CraftChat.get().getLogger().severe("Message in content of: " + content);
        CraftChat.get().getLogger().severe("From: " + from.getPlayerAccount().getDNDCraftName() + " To: " + players.size() + "players.");
        //CraftChat.get().getLogger().severe("Content: "+ content);
        //players.stream().forEach(p -> CraftChat.get().getLogger().severe("Player: " + ((Player) p).getName()));
        //CraftChat.get().getLogger().severe("Temp 4");
        var chatComponent = new BukkitComponentBuilder()
                .append(channel.channelIdentifier)
                .append(from.getPlayerAccount().getDNDCraftName(), from.getChatNameColor())
                .append(": ", AtlasColor.WHITE)
                .append(content, from.getMsgColor())
                .build();
        //CraftChat.get().getLogger().severe("Temp 5");
        //Bukkit.getConsoleSender().sendMessage(chatComponent);
        //CraftChat.get().getLogger().severe("Temp 6");
        //CraftChat.get().getLogger().severe("Count: " + players.size());
        players.stream().forEach(p -> p.sendMessage(chatComponent));
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public boolean isAMember(Player player){
        return players.contains(player);
    }


}
