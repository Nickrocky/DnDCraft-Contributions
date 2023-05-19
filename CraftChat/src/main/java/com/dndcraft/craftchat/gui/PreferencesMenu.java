package com.dndcraft.craftchat.gui;

import com.dndcraft.atlas.menu.MenuBuilder;
import com.dndcraft.atlas.menu.icon.Icon;
import com.dndcraft.atlas.menu.icon.SimpleButton;
import com.dndcraft.atlas.util.ItemUtil;
import com.dndcraft.craftchat.chat.ChatProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public class PreferencesMenu {

    public Icon[] menuIcons;

    public void openMenu(Player player, ChatProfile profile){
        menuIcons = new Icon[9];
        var friend = ItemUtil.make(Material.PLAYER_HEAD, 1, Component.text("Friends List"), Component.text("Click here to list your current friends"));
        menuIcons[0] = new SimpleButton(friend, (menuAction) -> {
            FriendMenu menu = new FriendMenu();
            menu.openMenu(player, profile);
        });

        /**
         * Idea
         * Put the context for how you actually met that person
         * If they add someone with /friend add let them pass a small text blurb to explain who they are/where they met
         * */
        var requests = ItemUtil.make(Material.WRITABLE_BOOK, 1, Component.text("Friend Requests"), Component.text("Click here to see your current friend requests"));
        menuIcons[1] = new SimpleButton(requests, (menuAction) -> {
            FriendRequestMenu menu = new FriendRequestMenu();
            menu.openMenu(player, profile);
        });

        var blocked = ItemUtil.make(Material.WITHER_SKELETON_SKULL, 1, Component.text("Blocked Players"), Component.text("Click here to view your blocked player list."));
        menuIcons[2] = new SimpleButton(blocked, menuAction -> {

        });

        var anim_name_color = ItemUtil.make(Material.BLUE_WOOL, 1, Component.text("Chat Name Color Selector"));
        menuIcons[4] = new SimpleButton(anim_name_color, menuAction -> {

        });
        var anim_msg_color = ItemUtil.make(Material.CYAN_WOOL, 1, Component.text("Message Color Selector"));
        menuIcons[7] = new SimpleButton(anim_msg_color, menuAction -> {

        });

        MenuBuilder menuBuilder = new MenuBuilder(ChatColor.DARK_GRAY + "Chat Preferences", InventoryType.DISPENSER);
        //Add the icons to the menu
        for(int i = 0; i < menuIcons.length; i++) {
            try {
                if(menuIcons[i] != null) menuBuilder.icon(i, menuIcons[i]);
            }catch(Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        menuBuilder.build().openSession(player);
    }

}

