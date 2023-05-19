package com.dndcraft.craftcodex.character;

import com.dndcraft.craftcodex.api.character.attribute.BodyParts;
import com.dndcraft.craftcodex.api.character.attribute.HealthState;
import com.dndcraft.craftcodex.api.character.capability.ICharacterTag;
import com.dndcraft.craftcodex.api.character.data.CraftTags;
import com.dndcraft.craftcodex.api.util.ManaHolderType;
import com.dndcraft.craftcodex.api.util.transaction.ManaTransaction;
import org.bson.Document;
import org.bukkit.NamespacedKey;

import java.util.HashMap;

public class Character implements com.dndcraft.craftcodex.api.character.Character {
    @Override
    public Document toDocument() {
        return null;
    }

    @Override
    public void fromDocument(Document document) {

    }

    /**
     * Gets a particular part's health
     *
     * @param part The part you are inquiring about
     * @return the current value of the body part
     */
    @Override
    public int getPartHealth(BodyParts part) {
        return 0;
    }

    /**
     * Sets a particular part's health
     *
     * @param part  The part you are trying to effect
     * @param value the new value of the part
     * @return the new state of the body part
     * @Apinote DO NOT USE ME
     */
    @Override
    public HealthState setPartHealth(BodyParts part, int value) {
        return null;
    }

    /**
     * Injures a particular body part, this is the generic use method
     *
     * @param part  The body part you want to injure
     * @param value the amount you want to hurt it
     * @return The new state of the body part
     */
    @Override
    public HealthState injure(BodyParts part, int value) {
        return null;
    }

    /**
     * Heals a body part
     *
     * @param part  the part you are healing
     * @param value the amount you are healing it
     * @return the new state of the body part
     */
    @Override
    public HealthState heal(BodyParts part, int value) {
        return null;
    }

    /**
     * Gets the current Head body part HP
     */
    @Override
    public int getHeadHealth() {
        return 0;
    }

    /**
     * Gets the current Head body part HP
     */
    @Override
    public int getChestHealth() {
        return 0;
    }

    /**
     * Gets the current Chest body part HP
     */
    @Override
    public int getLeftArmHealth() {
        return 0;
    }

    /**
     * Gets the current Right Arm body part HP
     */
    @Override
    public int getRightArmHealth() {
        return 0;
    }

    /**
     * Gets the current Left Leg body part HP
     */
    @Override
    public int getLeftLegHealth() {
        return 0;
    }

    /**
     * Gets the current Right Leg body part HP
     */
    @Override
    public int getRightLegHealth() {
        return 0;
    }

    /**
     * Gets the current Waist body part HP
     */
    @Override
    public int getWaistHealth() {
        return 0;
    }

    /**
     * Gets the current Right Hand body part HP
     */
    @Override
    public int getRightHandHealth() {
        return 0;
    }

    /**
     * Gets the current Left Hand body part HP
     */
    @Override
    public int getLeftHandHealth() {
        return 0;
    }

    /**
     * Gets the current Head body part Health State
     */
    @Override
    public HealthState getHeadHealthState() {
        return null;
    }

    /**
     * Gets the current Head body part Health State
     */
    @Override
    public HealthState getChestHealthState() {
        return null;
    }

    /**
     * Gets the current Chest body part Health State
     */
    @Override
    public HealthState getLeftArmHealthState() {
        return null;
    }

    /**
     * Gets the current Right Arm body part Health State
     */
    @Override
    public HealthState getRightArmHealthState() {
        return null;
    }

    /**
     * Gets the current Left Leg body part Health State
     */
    @Override
    public HealthState getLeftLegHealthState() {
        return null;
    }

    /**
     * Gets the current Right Leg body part Health State
     */
    @Override
    public HealthState getRightLegHealthState() {
        return null;
    }

    /**
     * Gets the current Waist body part Health State
     */
    @Override
    public HealthState getWaistHealthState() {
        return null;
    }

    /**
     * Gets the current Right-Hand Health State
     */
    @Override
    public HealthState getRightHandHealthState() {
        return null;
    }

    /**
     * Gets the current Left-Hand Health State
     */
    @Override
    public HealthState getLeftHandHealthState() {
        return null;
    }

    /**
     * Gets the current Health state of a specified body part
     *
     * @param part The body part you are inquiring about
     */
    @Override
    public HealthState getBodyPartHealthState(BodyParts part) {
        return null;
    }

    /**
     * Gets the plugin specific data from this particular character
     *
     * @return HashMap of plugin specific data
     */
    @Override
    public HashMap<NamespacedKey, ICharacterTag> getPluginSpecificData() {
        return null;
    }

    /**
     * Gets the Atlas tag data from this particular character
     *
     * @return HashMap of general data (Atlas)
     */
    @Override
    public HashMap<NamespacedKey, ICharacterTag> getAtlasData() {
        return null;
    }

    /**
     * Sees if the Character container has a particular general tag
     *
     * @param identifier Identifier the tag was stored with
     */
    @Override
    public boolean hasAtlasTag(String identifier) {
        return false;
    }

    /**
     * Sees if the Character container has a particular plugin specific tag
     *
     * @param identifier Namespaced key the plugin made and stored this data with
     */
    @Override
    public boolean hasPluginTag(NamespacedKey identifier) {
        return false;
    }

    /**
     * Gets an AtlasTag given an identifier
     *
     * @param identifier the actual referencable tag name for the tag
     * @return a generic of ICharacterTag this will need to be casted to actually be usable.
     */
    @Override
    public <T extends ICharacterTag> T getAtlasTag(String identifier) {
        return null;
    }

    /**
     * Gets a plugin tag given an namespaced key
     *
     * @param key the actual referencable namespaced key for the tag
     * @return a generic of ICharacterTag this will need to be casted to actually be usable.
     */
    @Override
    public <T extends ICharacterTag> T getPluginTag(NamespacedKey key) {
        return null;
    }

    /**
     * Gets an AtlasTag type of String given an identifier
     *
     * @param identifier the key you stored the value under
     * @return stored string
     */
    @Override
    public String getAtlasTagString(String identifier) {
        return null;
    }

    /**
     * Gets a plugin tag given a namespaced Key
     *
     * @param key the actual referencable namespaced key for the tag
     * @return a String stored at that Key
     */
    @Override
    public String getPluginTagString(NamespacedKey key) {
        return null;
    }

    @Override
    public int getAtlasTagInteger(String identifier) {
        return 0;
    }

    @Override
    public int getPluginTagInteger(NamespacedKey key) {
        return 0;
    }

    @Override
    public <T extends ICharacterTag> boolean isOfType(T tag, String tagType) {
        return false;
    }

    @Override
    public <T extends ICharacterTag> boolean isOfType(T tag, CraftTags craftTag) {
        return false;
    }

    @Override
    public <T extends ICharacterTag> void setAtlasTag(String identifier, T value) {

    }

    @Override
    public <T extends ICharacterTag> void setPluginTag(NamespacedKey identifier, T value) {

    }

    /**
     * Gets the mana holder's type
     *
     * @return the type of this mana container
     */
    @Override
    public ManaHolderType getType() {
        return null;
    }

    /**
     * Gets the raw amount of mana a Mana entity has
     *
     * @return the current mana amount stored in the container
     */
    @Override
    public int getMana() {
        return 0;
    }

    /**
     * Changes the amount of mana an entity has
     *
     * @param manaTransaction the actual transaction data, this object should be made for your particular plugin
     */
    @Override
    public void modifyMana(ManaTransaction manaTransaction) {

    }

    /**
     * Changes the amount of mana an entity has
     *
     * @param manaTransaction the actual transaction data, this object should be made for your particular plugin
     * @param timeInSeconds
     */
    @Override
    public void modifyMana(ManaTransaction manaTransaction, int timeInSeconds) {

    }

    /**
     * Changes the max Mana a particular container can hold.
     *
     * @param value mana amount you want to add to the mana pool
     */
    @Override
    public void increaseManaPoolSize(int value) {

    }

    /**
     * Decreases the max Mana a particular container can hold.
     *
     * @param value mana amount you want to shrink the mana pool by
     */
    @Override
    public void decreaseManaPoolSize(int value) {

    }
}
