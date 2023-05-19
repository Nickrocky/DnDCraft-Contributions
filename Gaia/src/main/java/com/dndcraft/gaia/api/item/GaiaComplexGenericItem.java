package com.dndcraft.gaia.api.item;


public abstract class GaiaComplexGenericItem extends GaiaGenericItem implements ReflectiveStateModel{
    public GaiaComplexGenericItem(String registryName, String itemName, ItemModel model, GaiaRarity rarity, ResourceCategory category, String description) {
        super(registryName, itemName, model, rarity, category, description);
    }
}
