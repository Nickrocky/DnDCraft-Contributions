package com.dndcraft.gaia.command;

import com.dndcraft.atlas.command.Commands;
import com.dndcraft.atlas.command.annotations.Arg;
import com.dndcraft.atlas.command.annotations.Cmd;
import com.dndcraft.atlas.command.annotations.Default;
import com.dndcraft.atlas.command.annotations.Range;
import com.dndcraft.gaia.api.GaiaAPI;
import com.dndcraft.gaia.api.item.GaiaItem;
import com.dndcraft.gaia.refactored.api.GaiaItems;
import com.dndcraft.atlas.util.InventoryUtil;
import com.dndcraft.gaia.Gaia;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GaiaCommand extends BaseCommand{

    public GaiaCommand(){
        Commands.defineArgumentType(GaiaKeyRegistry.class)
                .completer((sender, input) ->  sender.hasPermission("gaia.admin") ? getGaiaKeyPrompt(input) : Lists.newArrayList())
                .defaultError("Unknown Gaia Item Entered!")
                .defaultName("gaia_item")
                .mapper((input) -> GaiaAPI.getItemManager().listRegisteredItems().contains(input.toLowerCase())
                        ? new GaiaKeyRegistry(GaiaAPI.getItemManager().getItem(input.toLowerCase())) : null)
                .register();
    }

    public void invoke(){
        getSender().sendMessage(Gaia.VERSION);
    }

    @Cmd(value = "Give command", permission = "gaia.admin")
    public void give(Player player, @Arg(value = "gaia_key", description = "The item you want to spawn")GaiaKeyRegistry gaiaKeyRegistry, @Arg(value = "number", description = "The amount to spawn in")@Default(value = "1") @Range(min = 1, max = 64) int spawnNumber){
        var item = gaiaKeyRegistry.getItem();
        item.setAmount(spawnNumber);
        InventoryUtil.addItem(player.getInventory(), item);
    }

    @Cmd(value = "registered models command", permission = "gaia.admin")
    public void getModels(CommandSender sender){
        sender.sendMessage(GaiaAPI.getItemManager().listRegisteredItems()+"");
    }

    @Cmd(value = "registered item command", permission = "gaia.admin")
    public void getregisteringplugin(Player player, @Arg(value = "registryName", description = "the registry name of the item") String registryName){
        if(Gaia.getRealItemManager().has(registryName)){
            ItemStack item =  Gaia.getItemManager().getItem(registryName);
            player.sendMessage("Item: " + registryName + "was registered by plugin: " + Gaia.getRealItemManager().modelMap.get(item.getType().name()).get(item.getItemMeta().getCustomModelData()));
        }
    }

    @Cmd(value = "give override", permission = "gaia.admin")
    public void giveoverride(Player player, @Arg(value = "registryName", description = "the registry name of the item") String registryName){
        if(Gaia.getRealItemManager().has(registryName)){
            ItemStack item =  Gaia.getItemManager().getItem(registryName);
            player.sendMessage("Item: " + registryName + "was registered by plugin: " + Gaia.getRealItemManager().modelMap.get(item.getType().name()).get(item.getItemMeta().getCustomModelData()));
        }
    }

    @RequiredArgsConstructor
    public class GaiaKeyRegistry{
        @Getter private final ItemStack item;
    }

    public List<String> getGaiaKeyPrompt(String input){
        final String lowerCaseGaiaKey = input.toLowerCase();
        List<String> gaiaKeyStrings = new ArrayList<>();
        for(String item : GaiaAPI.getItemManager().listRegisteredItems()){
            if(item.toLowerCase().startsWith(lowerCaseGaiaKey)){
                if(!gaiaKeyStrings.contains(item.toLowerCase())){
                    gaiaKeyStrings.add(item.toLowerCase());
                }
            }
        }
        return gaiaKeyStrings;
    }
}
