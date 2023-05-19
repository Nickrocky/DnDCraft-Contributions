package com.dndcraft.gaia.api.item.food;

import com.dndcraft.gaia.api.item.ItemModel;
import com.dndcraft.gaia.api.item.ReflectiveStateModel;
import com.dndcraft.gaia.api.item.GaiaRarity;
import com.dndcraft.atlas.util.ItemUtil;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import static com.dndcraft.gaia.api.GaiaAPI.NBT_GAIA_FOOD_COMPLEX;
import static com.dndcraft.gaia.api.GaiaAPI.NBT_GAIA_FOOD_PORTIONS;

public abstract class GaiaComplexFoodItem extends GaiaFoodItem implements ReflectiveStateModel {

    @Getter int portions;

    public GaiaComplexFoodItem(String registryName, String itemName, ItemModel model, GaiaRarity rarity, double saturation, double foodRestorationValue, int portions, String description) {
        super(registryName, itemName, model, rarity, saturation, foodRestorationValue, description);
        this.portions = portions;
    }

    @Override
    public ItemStack toItemStack() {
        var item =  super.toItemStack();
        ItemUtil.setTag(item, NBT_GAIA_FOOD_COMPLEX, "yes");
        ItemUtil.setTag(item, NBT_GAIA_FOOD_PORTIONS, portions);
        return item;
    }
}
