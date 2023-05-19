package com.dndcraft.gaia.api.item;

import com.dndcraft.gaia.refactored.api.ModelMap;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
@Getter
public class InterfaceItem {
    private final Component name;
    private final String registryName;
    private final Material baseItem;
    private final int customModelData;

    public InterfaceItem(String registryName, Component name, ItemModel model){
        this.name = name;
        this.registryName = registryName;
        this.baseItem = model.getBaseItemMaterial();
        this.customModelData = model.getCustomModelDataNumber();
    }

    public ItemStack toItemStack(){
        ItemStack itemStack = new ItemStack(baseItem);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setCustomModelData(customModelData);
        meta.displayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
