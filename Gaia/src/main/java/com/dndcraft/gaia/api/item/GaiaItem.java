package com.dndcraft.gaia.api.item;

import com.dndcraft.prometheus.api.PrometheusAPI;
import com.dndcraft.gaia.api.GaiaAPI;
import com.dndcraft.atlas.util.ItemUtil;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
public abstract class GaiaItem {

    private boolean valid;

    private String itemName;
    private String registryName;
    private String description;
    private ResourceCategory category;
    private GaiaRarity rarity;
    private Material baseItem;
    private int customModelData;

    public GaiaItem(String registryName, String itemName, ItemModel model, GaiaRarity rarity, ResourceCategory category, String description){
        this.category = category;
        this.itemName = itemName;
        this.description = description;
        this.baseItem = model.getBaseItemMaterial();
        this.customModelData = model.getCustomModelDataNumber();
        this.rarity = rarity;
        this.registryName = registryName;
        valid = isValid();
    }

    /**
     * Turns the item into an itemstack
     * */
    public ItemStack toItemStack(){
        var item = PrometheusAPI.getApi().getProItemUtil().makeGaiaBorderedItem(baseItem, rarity.getBorderColor(), rarity.getRarityComponent(), category.name(), 0f, itemName, description);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
        ItemUtil.setTag(item, GaiaAPI.NBT_GAIA_REGISTRY_NAME, registryName);
        ItemUtil.setTag(item, GaiaAPI.NBT_GAIA_ITEM_VERSION, GaiaAPI.CURRENT_ITEM_VERSION);
        return item;
    }

    /**
     * Checks if the item is valid and can be registered
     * @apiNote This is to stop people from coding a particular class of items incorrectly, its just a preliminary check before
     * the item is registered
     * @return if the item has been programmed properly
     * */
    public abstract boolean isValid();

    /**
     * If you want this item to show up on ItemManager#listRegisteredItems()
     * @Apinote: By default all items are visible by registry, this allows for them to be spawned with /gaia give <item>
     * If you opt to override this method and make it not visible, YOU WILL BE UNABLE TO SPAWN YOUR ITEM WITH /gaia give
     * @return if this item appears on ItemManager#listRegisteredItems()
     * */
    public boolean isRegistryVisible(){
        return true;
    }

}
