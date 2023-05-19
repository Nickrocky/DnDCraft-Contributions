package com.dndcraft.craftchat.gui;

import com.dndcraft.atlas.menu.Menu;
import com.dndcraft.atlas.menu.icon.Icon;
import com.dndcraft.atlas.menu.icon.SimpleButton;
import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import com.dndcraft.atlas.util.ItemUtil;
import com.dndcraft.craftchat.CraftChat;
import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftchat.chat.FriendRequest;
import com.dndcraft.craftchat.util.FriendRequestUtil;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import com.dndcraft.craftcodex.api.account.Account;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestMenu {

    public void openMenu(Player player, ChatProfile profile){
        var requests = profile.getRequests();

        List<Icon> icons = new ArrayList();
        for(FriendRequest r : requests){
            Account sender = CraftCodexAPI.getApi().getAccountManager().getAccount(r.getAccountIDOfSender());
            Component acceptTooltip = new BukkitComponentBuilder().append("To accept this friend request left click!", AtlasColor.LIME).build();
            Component denyTooltip = new BukkitComponentBuilder().append("To deny this friend request shift left click!", AtlasColor.RED).build();
            ItemStack head = ItemUtil.make(Material.PLAYER_HEAD, Component.text(sender.getDNDCraftName()), acceptTooltip, denyTooltip);
                icons.add(new SimpleButton(head, (menuAction) -> {
                    if(menuAction.getClick().isLeftClick()){
                        if(menuAction.getClick().isShiftClick()){
                            FriendRequestUtil.denyRequest(r);
                        }else{
                            FriendRequestUtil.acceptRequest(player, r);
                        }
                    }
                }));
        }
        Menu.fromIcons(ChatColor.DARK_GRAY + "Friend Requests", icons).openSession(player);
    }
}
