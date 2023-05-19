package com.dndcraft.craftcodex.api.character.capability;


import com.dndcraft.craftcodex.api.character.attribute.BodyParts;
import com.dndcraft.craftcodex.api.character.attribute.HealthState;

/**
 * A couple ground rules for this interface,
 * 1. there is no passive regen in our world, go to a doctor damnit
 * 2. you should only ever be using getters or IBody#injure/IBody#heal thats about it
 * 3. For the love of all things do NOT use setPartHealth I swear ill end it all
 * @Author Nickrocky213
 * @Date 1/4/2021
 * */

public interface IBody {

    /**
     * Gets a particular part's health
     * @param part The part you are inquiring about
     * @return the current value of the body part
     * */
    int getPartHealth(BodyParts part);

    /**
     * Sets a particular part's health
     * @param part The part you are trying to effect
     * @param value the new value of the part
     * @return the new state of the body part
     * @Apinote DO NOT USE ME
     * */
    HealthState setPartHealth(BodyParts part, int value);

    /**
     * Injures a particular body part, this is the generic use method
     * @param part The body part you want to injure
     * @param value the amount you want to hurt it
     * @return The new state of the body part
     * */
    HealthState injure(BodyParts part, int value);

    /**
     * Heals a body part
     * @param part the part you are healing
     * @param value the amount you are healing it
     * @return the new state of the body part
     * */
    HealthState heal(BodyParts part, int value);

    /**
     * Gets the current Head body part HP
     * */
    int getHeadHealth();

    /**
     * Gets the current Head body part HP
     * */
    int getChestHealth();

    /**
     * Gets the current Chest body part HP
     * */
    int getLeftArmHealth();

    /**
     * Gets the current Right Arm body part HP
     * */
    int getRightArmHealth();

    /**
     * Gets the current Left Leg body part HP
     * */
    int getLeftLegHealth();

    /**
     * Gets the current Right Leg body part HP
     * */
    int getRightLegHealth();

    /**
     * Gets the current Waist body part HP
     * */
    int getWaistHealth();

    /**
     * Gets the current Right Hand body part HP
     * */
    int getRightHandHealth();

    /**
     * Gets the current Left Hand body part HP
     * */
    int getLeftHandHealth();

    /**
     * Gets the current Head body part Health State
     * */
    HealthState getHeadHealthState();

    /**
     * Gets the current Head body part Health State
     * */
    HealthState getChestHealthState();

    /**
     * Gets the current Chest body part Health State
     * */
    HealthState getLeftArmHealthState();

    /**
     * Gets the current Right Arm body part Health State
     * */
    HealthState getRightArmHealthState();

    /**
     * Gets the current Left Leg body part Health State
     * */
    HealthState getLeftLegHealthState();

    /**
     * Gets the current Right Leg body part Health State
     * */
    HealthState getRightLegHealthState();

    /**
     * Gets the current Waist body part Health State
     * */
    HealthState getWaistHealthState();

    /**
     * Gets the current Right-Hand Health State
     * */
    HealthState getRightHandHealthState();

    /**
     * Gets the current Left-Hand Health State
     * */
    HealthState getLeftHandHealthState();

    /**
     * Gets the current Health state of a specified body part
     * @param part The body part you are inquiring about
     * */
    HealthState getBodyPartHealthState(BodyParts part);
}
