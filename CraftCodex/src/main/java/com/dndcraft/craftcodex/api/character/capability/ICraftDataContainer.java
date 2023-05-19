package com.dndcraft.craftcodex.api.character.capability;

import com.dndcraft.atlas.io.mongodb.interfaces.ISerializable;
import com.dndcraft.craftcodex.api.character.data.CraftTags;
import org.bukkit.NamespacedKey;

import java.util.HashMap;

/**
 * @Author nickrocky213
 * The intention of PluginTags are that they are used ONLY by that plugin, and AtlasTags
 * are used by all plugins, and are used in interactions between plugins
 * */
public interface ICraftDataContainer extends ISerializable {

    /**
     * Gets the plugin specific data from this particular character
     * @return HashMap of plugin specific data
     * */
    HashMap<NamespacedKey, ICharacterTag> getPluginSpecificData();

    /**
     * Gets the Atlas tag data from this particular character
     * @return HashMap of general data (Atlas)
     * */
    HashMap<NamespacedKey, ICharacterTag> getAtlasData();

    /**
     * Sees if the Character container has a particular general tag
     * @param identifier Identifier the tag was stored with
     * */
    boolean hasAtlasTag(String identifier);

    /**
     * Sees if the Character container has a particular plugin specific tag
     * @param identifier Namespaced key the plugin made and stored this data with
     * */
    boolean hasPluginTag(NamespacedKey identifier);

    /**
     * Gets an AtlasTag given an identifier
     * @param identifier the actual referencable tag name for the tag
     * @return a generic of ICharacterTag this will need to be casted to actually be usable.
     * */
    <T extends ICharacterTag> T getAtlasTag(String identifier);

    /**
     * Gets a plugin tag given an namespaced key
     * @param key the actual referencable namespaced key for the tag
     * @return a generic of ICharacterTag this will need to be casted to actually be usable.
     * */
    <T extends ICharacterTag> T getPluginTag(NamespacedKey key);

    /**
     * Gets an AtlasTag type of String given an identifier
     * @param identifier the key you stored the value under
     * @return stored string
     * */
    String getAtlasTagString(String identifier);

    /**
     * Gets a plugin tag given a namespaced Key
     * @param key the actual referencable namespaced key for the tag
     * @return a String stored at that Key
     * */
    String getPluginTagString(NamespacedKey key);

    /**
     * Gets an atlas tag of type integer given an identifier
     * @param identifier the key you stored the value under
     * @return The integer stored at the identifier location
     * */
    int getAtlasTagInteger(String identifier);

    /**
     * Gets a plugin tag of type integer given an identifier
     * @param key the actual referencable namespaced key for the tag
     * @return the integer stored at the identifier location
     * */
    int getPluginTagInteger(NamespacedKey key);

    /**
     * Checks the type of the stored data at on a given tag
     * @param tag the tag on the container you want to compare to a type
     * @param tagType the type of tag you want to compare your tag to
     * @return if the tag is of that type or not
     * */
    <T extends ICharacterTag> boolean isOfType(T tag, String tagType);

    /**
     * Checks the type of the stored data at on a given tag
     * @param tag the tag on the container you want to compare to a type
     * @param craftTag the type of tag you want to compare your tag to
     * @return if the tag is of that type or not
     * */
    <T extends ICharacterTag> boolean isOfType(T tag, CraftTags craftTag);

    /**
     * Sets a given atlas tag to the container
     * @param identifier the key you are storing the value under
     * @param value the value you want to store
     * */
    <T extends ICharacterTag> void setAtlasTag(String identifier, T value);

    /**
     * Sets a given plugin tag to the container
     * @param identifier The identifier you are storing the tag under
     * @param value the value you want to store
     * */
    <T extends ICharacterTag> void setPluginTag(NamespacedKey identifier, T value);

}
