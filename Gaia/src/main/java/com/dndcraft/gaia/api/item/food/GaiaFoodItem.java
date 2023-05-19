package com.dndcraft.gaia.api.item.food;

import com.dndcraft.gaia.api.item.GaiaItem;
import com.dndcraft.gaia.api.item.exception.InvalidFoodItemBaseException;
import com.dndcraft.gaia.api.item.ItemModel;
import com.dndcraft.gaia.api.item.GaiaRarity;
import com.dndcraft.gaia.api.item.ResourceCategory;
import com.dndcraft.atlas.util.ItemUtil;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

import static com.dndcraft.gaia.api.GaiaAPI.*;
import static org.bukkit.Material.*;

public class GaiaFoodItem extends GaiaItem {

    @Getter private final double saturation, foodRestorationValue;

    private static final List<Material> foodBaseItems = Arrays.asList(APPLE, MUSHROOM_STEW, BREAD, PORKCHOP, COOKED_PORKCHOP,
            GOLDEN_APPLE, ENCHANTED_GOLDEN_APPLE, COD, COOKED_COD, SALMON, COOKED_SALMON, TROPICAL_FISH, PUFFERFISH, COOKIE, MELON_SLICE,
            DRIED_KELP, BEEF, COOKED_BEEF, CHICKEN, COOKED_CHICKEN, ROTTEN_FLESH, SPIDER_EYE,
            CARROT, POTATO, BAKED_POTATO, POISONOUS_POTATO, PUMPKIN_PIE, RABBIT, COOKED_RABBIT,
            RABBIT_STEW, MUTTON, COOKED_MUTTON, BEETROOT, BEETROOT_SOUP, SWEET_BERRIES,
            GLOW_BERRIES, HONEY_BOTTLE);

    public GaiaFoodItem(String registryName, String itemName, ItemModel model, GaiaRarity rarity, double saturation, double foodRestorationValue, String description) {
        super(registryName, itemName, model, rarity, ResourceCategory.CONSUMABLE, description);
        this.saturation = saturation;
        this.foodRestorationValue = foodRestorationValue;
    }

    @Override
    public ItemStack toItemStack() {
        var item = super.toItemStack();
        ItemUtil.setTag(item, NBT_GAIA_FOOD, "yes");
        ItemUtil.setTag(item, NBT_GAIA_FOOD_SATURATION, saturation);
        ItemUtil.setTag(item, NBT_GAIA_FOOD_VALUE, foodRestorationValue);
        return item;
    }

    @Override
    public boolean isValid() {
        boolean valid = false;
        if(!foodBaseItems.contains(getBaseItem())){
            try {
                throw new InvalidFoodItemBaseException(this);
            } catch (InvalidFoodItemBaseException e) {
                e.printStackTrace();
            }
        }else{
            valid = true;
        }
        return valid;
    }
}
