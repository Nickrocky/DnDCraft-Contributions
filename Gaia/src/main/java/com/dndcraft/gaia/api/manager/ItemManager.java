package com.dndcraft.gaia.api.manager;

import com.dndcraft.gaia.api.item.GaiaItem;
import com.dndcraft.gaia.api.item.InterfaceItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public interface ItemManager {

    /**
     * Registers the desired Gaia Item with the ItemManager
     * @param gaiaItemToRegister any object that extends GaiaItem and needs to be registered
     * */
    <T extends GaiaItem> void register(T gaiaItemToRegister, JavaPlugin registeringPlugin);

    /**
     * Registered the desired Interface Item with the ItemManager
     * @param interfaceItemToRegister any object that extends InterfaceItem and needs to be registered
     * */
    <T extends InterfaceItem> void register(T interfaceItemToRegister, JavaPlugin registeringPlugin);

    /**
     * Checks to see if that gaia item was already registered with the ItemManager
     * @param gaiaItemToCheck any object that extends GaiaItem that you want to check for
     * */
    <T extends GaiaItem> boolean isRegistered(T gaiaItemToCheck);

    /**
     * Checks to see if that gaia item was already registered with the ItemManager
     * @param interfaceItem any object that extends InterfaceItem that you want to check for
     * */
    <T extends InterfaceItem> boolean isRegistered(T interfaceItem);

    /**
     * Checks to see if that gaia item was already registered with the ItemManager
     * @param registryName the registryName of the item you want to search for
     * */
    boolean has(String registryName);

    /**
     * Checks to see if that gaia item was already registered with the ItemManager
     * @param registryName the registryName of the interface item you want to search for
     * */
    boolean hasInterfaceItem(String registryName);

    /**
     * Lists all registryNames for items that have been registered with the ItemManager
     * @apiNote It will only show items that have item discovery enabled, which is on by default when an item is created
     * @return the list of registrynames of registered items
     * */
    List<String> listRegisteredItems();

    /**
     * Gets a new instance of that particular gaia item from the item registry
     * @param registryName the registry name of the item you want
     * @return the new Itemstack of that particular item
     * */
    ItemStack getItem(String registryName);

    /**
     * Gets a new instance of that particular interface item from the item registry
     * @param registryName the registry name of the item you want
     * @return the new Itemstack of that particular item
     * */
    ItemStack getInterfaceItem(String registryName);

}
