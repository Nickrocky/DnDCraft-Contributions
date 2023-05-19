package com.dndcraft.prometheus.block;

import com.dndcraft.prometheus.api.block.CustomBlocks;
import com.dndcraft.prometheus.item.ResourceCategory;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public class CustomBlock {

    private final CustomBlockType blockType;
    private final CustomBlocks registryKey;
    private final int customModelData;
    private final String itemName;
    private final CustomNoteblockBlock blockstateData;
    private final ItemStack baseItem;
    private final ResourceCategory category;
    private final String description;

    public CustomBlock(CustomBlocks customBlock, CustomBlockType blockType, int customModelData, ResourceCategory category, ItemStack baseItem, String itemName, String description){
        this.blockType = blockType;
        this.registryKey = customBlock;
        this.customModelData = customModelData;
        this.itemName = itemName;
        this.category = category;
        this.baseItem = baseItem;
        this.description = description;
        this.blockstateData = null;
        //if(!blockType.equals(CustomBlockType.STRAIGHT_RETEXTURE)) throw new InvalidCustomBlockPropertiesException();
    }

    public CustomBlock(CustomBlocks customBlock, CustomNoteblockBlock blockstateData, int customModelData, ResourceCategory category, String itemName, String description){
        this.blockType = CustomBlockType.NOTEBLOCK;
        this.customModelData = customModelData;
        this.itemName = itemName;
        this.registryKey = customBlock;
        this.blockstateData = blockstateData;
        this.baseItem = new ItemStack(Material.CHARCOAL);
        var meta = baseItem.getItemMeta();
        meta.setCustomModelData(customModelData);
        baseItem.setItemMeta(meta);
        this.description = description;
        this.category = category;
    }



}
