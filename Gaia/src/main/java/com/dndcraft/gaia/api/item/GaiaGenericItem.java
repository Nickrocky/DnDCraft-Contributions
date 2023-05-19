package com.dndcraft.gaia.api.item;

public class GaiaGenericItem extends GaiaItem{


    public GaiaGenericItem(String registryName, String itemName, ItemModel model, GaiaRarity rarity, ResourceCategory category, String description) {
        super(registryName, itemName, model, rarity, category, description);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
