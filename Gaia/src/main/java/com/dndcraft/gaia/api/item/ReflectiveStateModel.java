package com.dndcraft.gaia.api.item;

import java.util.List;

/**
 *  This is for items that have multiple states such as the consumption of a food item with multiple portions.
 * */
public interface ReflectiveStateModel {

    /**
     * The number of states for the item not counting its base model.
     * @return the number of states the item has not counting its base model.
     * */
    int getNumberOfStates();

    /**
     * The model states for the item
     * @ApiNote: this does NOT count the base state.
     * @return the model states for the item
     * */
    List<ItemModel> getStates();

}
