package com.dndcraft.craftchat.gui;

import com.dndcraft.atlas.menu.Menu;
import com.dndcraft.atlas.menu.icon.Icon;
import com.dndcraft.atlas.menu.icon.SimpleButton;
import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import com.dndcraft.atlas.util.ItemUtil;
import com.dndcraft.craftchat.CraftChat;
import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import com.dndcraft.craftcodex.api.account.Account;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorSelectorMenu {

    public void openColorSelector(Player player, ColorSelectorType type){
        List<Icon> menuIconList = new ArrayList<>();
        for(AtlasColor color : AtlasColor.values()){
            var iconColor = Color.fromRGB(color.toTextColor().red(), color.toTextColor().green(), color.toTextColor().blue());
            var displayComponent = new BukkitComponentBuilder(StringUtils.capitalize(color.name().toLowerCase())).color(color).build();
            var loreComponent = new BukkitComponentBuilder("The quick brown fox jumps over the lazy dog").color(color).build();
            ItemStack potion = ItemUtil.makePotion(iconColor, displayComponent, Arrays.asList(loreComponent));
            menuIconList.add(new SimpleButton(potion, (menuAction) -> {
                PreferencesMenu preferencesMenu = new PreferencesMenu();
                Account account = CraftCodexAPI.getApi().getAccountManager().getAccount(player.getUniqueId());
                ChatProfile profile = CraftChat.getChatProfileManager().getChatProfileByAccountID(account.getAccountID());
                switch (type)
                {
                    case MSG -> profile.setMsgColor(color);
                    case NAME -> profile.setChatNameColor(color);
                }
                preferencesMenu.openMenu(player, profile);
            }));
        }
        Menu.fromIcons(ChatColor.DARK_GRAY + type.getTypeString() +" Color Selector", menuIconList).openSession(player);
    }
}
