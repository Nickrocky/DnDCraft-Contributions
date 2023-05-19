package com.dndcraft.craftcodex.api.character.capability;


import com.dndcraft.atlas.io.mongodb.interfaces.ISerializable;
import com.dndcraft.craftcodex.api.util.ManaHolderType;
import com.dndcraft.craftcodex.api.util.transaction.ManaTransaction;

/**
 * Written on 1/26/2022
 * @Author nickrocky213
 * General information regarding ManaContainers
 * All mana containers start with a default size of 100
 * No mana container can store less than 0 mana
 * All mana containers have a type
 * */
public interface IManaContainer extends ISerializable {
    /**
     * Gets the mana holder's type
     * @return the type of this mana container
     * */
    ManaHolderType getType();

    /**
     * Gets the raw amount of mana a Mana entity has
     * @return the current mana amount stored in the container
     * */
    int getMana();

    /**
     * Changes the amount of mana an entity has
     * @param manaTransaction the actual transaction data, this object should be made for your particular plugin
     * */
    void modifyMana(ManaTransaction manaTransaction);

    /**
     * Changes the amount of mana an entity has
     * @param manaTransaction the actual transaction data, this object should be made for your particular plugin
     * */
    void modifyMana(ManaTransaction manaTransaction, int timeInSeconds);

    /**
     * Changes the max Mana a particular container can hold.
     * @param value mana amount you want to add to the mana pool
     * */
    void increaseManaPoolSize(int value);

    /**
     * Decreases the max Mana a particular container can hold.
     * @param value mana amount you want to shrink the mana pool by
     * */
    void decreaseManaPoolSize(int value);
}