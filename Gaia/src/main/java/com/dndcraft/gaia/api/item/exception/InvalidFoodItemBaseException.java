package com.dndcraft.gaia.api.item.exception;

import com.dndcraft.gaia.api.item.food.GaiaFoodItem;

public class InvalidFoodItemBaseException extends Exception{

    public InvalidFoodItemBaseException(GaiaFoodItem item){
        super("Invalid item base: " + item.getBaseItem().name() + " is being used with the item: " + item.getRegistryName() + ". Foods must use a consumable item base!");
    }

}
