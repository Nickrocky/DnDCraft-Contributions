package com.dndcraft.craftchat.gui;

import com.dndcraft.atlas.menu.Menu;
import com.dndcraft.atlas.menu.icon.Icon;
import com.dndcraft.atlas.menu.icon.Pad;
import com.dndcraft.atlas.util.ItemUtil;
import com.dndcraft.craftchat.CraftChat;
import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import com.dndcraft.craftcodex.api.account.Account;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FriendMenu {

    public void openMenu(Player player, ChatProfile profile){
        var friends = profile.getFriendsList();
        List<Icon> friendElement = new ArrayList<>();
        for(int i : friends){
            Account account = CraftCodexAPI.getApi().getAccountManager().getAccount(i);
            friendElement.add(new Pad(ItemUtil.make(Material.PLAYER_HEAD, Component.text(account.getDNDCraftName()), Component.text("This is a placeholder for account profiles"))));
        }
        Menu.fromIcons(ChatColor.DARK_GRAY + "Friends List", friendElement).openSession(player);
    }

}
