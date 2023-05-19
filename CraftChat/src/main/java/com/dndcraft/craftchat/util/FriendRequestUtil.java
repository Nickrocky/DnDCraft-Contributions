package com.dndcraft.craftchat.util;

import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import com.dndcraft.craftchat.CraftChat;
import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftchat.chat.FriendRequest;
import com.dndcraft.craftchat.storage.CraftStorage;
import com.dndcraft.craftcodex.api.account.Account;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FriendRequestUtil {

    public static void sendRequest(Account sender, Account recipient, String context){
        FriendRequest request = new FriendRequest(sender.getAccountID(), recipient.getAccountID(), context);
        CraftStorage.addFriendRequest(request);
        ChatProfile profile = CraftChat.getChatProfileManager().getChatProfileByAccountID(recipient.getAccountID());
        profile.getRequests().add(request);
    }

    public static void denyRequest(FriendRequest request){
        CraftStorage.removeFriendRequest(request);
        ChatProfile profile = CraftChat.getChatProfileManager().getChatProfileByAccountID(request.getAccountIDOfRecipient());
        profile.getRequests().remove(request);
    }

    public static void acceptRequest(Player accepter, FriendRequest request){
        CraftStorage.removeFriendRequest(request);
        CraftStorage.addFriend(request.getAccountIDOfSender(), request.getAccountIDOfRecipient());
        CraftStorage.addFriend(request.getAccountIDOfRecipient(), request.getAccountIDOfSender());
        ChatProfile recipient = CraftChat.getChatProfileManager().getChatProfileByAccountID(request.getAccountIDOfRecipient());
        ChatProfile sender = CraftChat.getChatProfileManager().getChatProfileByAccountID(request.getAccountIDOfSender());
        recipient.getRequests().remove(request);
        sender.getFriendsList().add(request.getAccountIDOfRecipient());
        recipient.getFriendsList().add(request.getAccountIDOfSender());
        var friendComponent = new BukkitComponentBuilder().append("You have added ", AtlasColor.DANDELION).append(""+sender.getPlayerAccount().getDNDCraftName(), AtlasColor.LIME, TextDecoration.BOLD).append(" as a friend!", AtlasColor.DANDELION).build();
        var senderComponent = new BukkitComponentBuilder().append(""+recipient.getPlayerAccount().getDNDCraftName(), AtlasColor.LIME, TextDecoration.BOLD).append(" has accepted your friends request!", AtlasColor.DANDELION).build();
        accepter.sendMessage(friendComponent);
        Player player = Bukkit.getPlayer(sender.getPlayerAccount().getMinecraftUUID());
        if(player != null){
            player.sendMessage(senderComponent);
        }
    }
}
