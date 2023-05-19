package com.dndcraft.prometheus.api.manager;

import com.dndcraft.prometheus.api.item.BorderColor;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ProItemUtil {

    /**
     * Sends a message to the player on the bar below the action bar
     * Mojang please I beg - nickrocky
     * */
    void sendMessage(Player player, Component component);

    /**
     * Makes a Gaia-Style item with a border, display, and lore
     * @param material item base material
     * @param borderColor the color of the border
     * @param rarityComponent the rarity component for the item in the lore
     * @param category category to sort the item
     * @param weight the mass of the item
     * @param displayName the display name of the item
     * @param lore the lore of a particular item
     * */
    ItemStack makeGaiaBorderedItem(Material material, BorderColor borderColor, Component rarityComponent, String category, double weight, String displayName, String lore);

    /**
     * Makes an alchemy item for Gaia because they are *special* kms
     * @param material item base material
     * @param borderColor the color of the border
     * @param rarityComponent the rarity component for the item in the lore
     * @param registryName the registry name of the item in gaia
     * @param displayName the display name of the item
     * @param lore the lore of a particular item
     * */
    ItemStack makeGaiaAlchemyReagentItem(Material material, BorderColor borderColor, Component rarityComponent, String registryName, String displayName, String lore);

    /**
     * Makes an item with a border, display, and lore
     * @param material item base material
     * @param borderColor the color of the border
     * @param displayName the display name of the item
     * @param lore the lore of a particular item
     * */
    ItemStack makeBorderedItem(Material material, BorderColor borderColor, String displayName, String lore);

    /**
     * Applies a tool tip border directly to the item passed to the function
     * @param itemStack Item needing the modification
     * */
    void apply(ItemStack itemStack, BorderColor borderColor);

    /**
     * Apply the custom tooltip border to the itemstack.
     * @return Copy of original itemstack with the border attached
     * */
    ItemStack applyAndReturnCopy(ItemStack itemStack, BorderColor borderColor);



}
