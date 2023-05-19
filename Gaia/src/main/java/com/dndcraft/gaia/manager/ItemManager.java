package com.dndcraft.gaia.manager;

import com.dndcraft.gaia.Gaia;
import com.dndcraft.gaia.api.item.GaiaItem;
import com.dndcraft.gaia.api.item.InterfaceItem;
import com.dndcraft.gaia.api.item.ItemModel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager implements com.dndcraft.gaia.api.manager.ItemManager {

    public Map<String, GaiaItem> itemRegistry = new HashMap<>();
    public Map<String, InterfaceItem> interfaceItemRegistry = new HashMap<>();
    public Map<String, Map<Integer, String>> modelMap = new HashMap<>();

    @Override
    public <T extends GaiaItem> void register(T gaiaItemToRegister, JavaPlugin registeringPlugin) {
        Bukkit.getConsoleSender().sendMessage("Plugin: " + registeringPlugin.getName() + " is attempting to register " + gaiaItemToRegister.getRegistryName());
        if(!isRegistered(gaiaItemToRegister)){
            registerModel(new ItemModel(gaiaItemToRegister.getBaseItem(), gaiaItemToRegister.getCustomModelData()), gaiaItemToRegister.getRegistryName());
            itemRegistry.put(gaiaItemToRegister.getRegistryName(), gaiaItemToRegister);
            Bukkit.getConsoleSender().sendMessage("Plugin: " + registeringPlugin.getName() + " succeeded!");
        }else{
            try{
                Bukkit.getConsoleSender().sendMessage("Plugin: " + registeringPlugin.getName() + " failed!");
                throw new ItemRegistrationException(gaiaItemToRegister);
            }catch (ItemRegistrationException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public <T extends InterfaceItem> void register(T interfaceItemToRegister, JavaPlugin registeringPlugin) {
        if(!isRegistered(interfaceItemToRegister)){
            ItemModel model = new ItemModel(interfaceItemToRegister.getBaseItem(), interfaceItemToRegister.getCustomModelData());
            registerModel(model, interfaceItemToRegister.getRegistryName());
            interfaceItemRegistry.put(interfaceItemToRegister.getRegistryName(), interfaceItemToRegister);
        }else{
            //todo make the exception apply to interface items
        }
    }

    private boolean registerModel(ItemModel model, String registryName){
        if(!modelMap.containsKey(model.getBaseItemMaterial().name())){
            modelMap.put(model.getBaseItemMaterial().name(), new HashMap<>());
        }
        if(!hasModel(model)){
            modelMap.get(model.getBaseItemMaterial().name()).put(model.getCustomModelDataNumber(), registryName);
            return true;
        }else{
            return false;
        }
    }

    private boolean hasModel(ItemModel itemModel){
        if(modelMap.get(itemModel.getBaseItemMaterial().name()).containsKey(itemModel.getCustomModelDataNumber())){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public <T extends GaiaItem> boolean isRegistered(T gaiaItemToCheck) {
        return itemRegistry.containsKey(gaiaItemToCheck.getRegistryName());
    }

    @Override
    public <T extends InterfaceItem> boolean isRegistered(T interfaceItem) {
        return interfaceItemRegistry.containsKey(interfaceItem.getRegistryName());
    }

    @Override
    public boolean has(String registryName) {
        return itemRegistry.containsKey(registryName);
    }

    @Override
    public boolean hasInterfaceItem(String registryName) {
        return interfaceItemRegistry.containsKey(registryName);
    }

    @Override
    public List<String> listRegisteredItems() {
        List<String> registryKeys = new ArrayList<>();
        for(Map.Entry<String, GaiaItem> val : itemRegistry.entrySet()){
            if(val.getValue().isRegistryVisible()){
                Bukkit.getConsoleSender().sendMessage(val.getKey() + " IS registry visible.");
                registryKeys.add(val.getValue().getRegistryName());
            }else{
                Bukkit.getConsoleSender().sendMessage(val.getKey() + " ISN'T registry visible.");
            }
        }
        return registryKeys;
    }

    @Override
    public ItemStack getItem(String registryName) {
        if(has(registryName)){
            return itemRegistry.get(registryName).toItemStack().clone();
        }
        return new ItemStack(Material.AIR);
    }

    @Override
    public ItemStack getInterfaceItem(String registryName) {
        if(hasInterfaceItem(registryName)){
            return interfaceItemRegistry.get(registryName).toItemStack().clone();
        }
        return new ItemStack(Material.AIR);
    }

    public <T extends GaiaItem> boolean isItemModelRegistered(T item){
        return hasModel(new ItemModel(item.getBaseItem(), item.getCustomModelData()));
    }
}
