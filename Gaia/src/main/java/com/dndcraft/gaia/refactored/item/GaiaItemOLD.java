package com.dndcraft.gaia.refactored.item;

import com.dndcraft.gaia.refactored.api.ModelMap;
import com.dndcraft.prometheus.api.PrometheusAPI;
import com.dndcraft.gaia.api.item.GaiaRarity;
import com.dndcraft.gaia.api.item.ResourceCategory;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import com.dndcraft.atlas.util.ItemUtil;
import com.dndcraft.gaia.api.GaiaAPI;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * name - Name of the item
 * descriptionText - The text you want to be
 * */

@Getter
public class GaiaItemOLD {

    private String name;
    private String registryName;
    private String descriptionText;
    private ResourceCategory category;
    private GaiaRarity rarity;
    private Material baseItem;
    private int customModelData;

    public GaiaItemOLD(String registryName, String name, ModelMap modelMap, GaiaRarity rarity, ResourceCategory category, String desc){
        var nameComponent = new BukkitComponentBuilder();
        this.category = category;
        this.name = name;
        this.descriptionText = desc;
        this.baseItem = modelMap.getBaseItem();
        this.customModelData = modelMap.getModelNumber();
        this.rarity = rarity;
        this.registryName = registryName;
    }

    public ItemStack toItemStack(){
        var item = PrometheusAPI.getApi().getProItemUtil().makeGaiaBorderedItem(baseItem, rarity.getBorderColor(), rarity.getRarityComponent(), category.name(), 0f, name, descriptionText);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
        ItemUtil.setTag(item, GaiaAPI.NBT_GAIA_REGISTRY_NAME, registryName);
        ItemUtil.setTag(item, GaiaAPI.NBT_GAIA_ITEM_VERSION, registryName);
        return item;
    }

}
