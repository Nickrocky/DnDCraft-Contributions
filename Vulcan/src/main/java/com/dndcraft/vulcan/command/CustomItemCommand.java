package com.dndcraft.vulcan.command;

import com.dndcraft.atlas.command.Commands;
import com.dndcraft.atlas.command.annotations.Arg;
import com.dndcraft.atlas.util.InventoryUtil;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItemCommand extends BaseCommand{

    public CustomItemCommand(){
        Commands.defineArgumentType(MaterialKey.class)
                .completer((sender, input) ->  sender.hasPermission("vulcan.admin") ? getKeyPrompt(input) : Lists.newArrayList())
                .defaultError("Unknown Item Entered!")
                .defaultName("item")
                .mapper((input) -> {
                    for(Material material : Material.values()){
                        if(material.name().toLowerCase().equals(input)) return new MaterialKey(material);
                    }
                    return null;
                })
                .register();
    }

    public void invoke(Player player, @Arg(value = "minecraft_item")MaterialKey minecraftItem, @Arg(value = "custommodeldata")int customModelData){
        ItemStack itemStack = new ItemStack(minecraftItem.getMaterial());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setCustomModelData(customModelData);
        InventoryUtil.addOrDropItem(player, itemStack);
    }

    @RequiredArgsConstructor
    public static class MaterialKey{
        @Getter private final Material material;
    }

    public List<String> getKeyPrompt(String input){
        final String lowerCaseKey = input.toLowerCase();
        List<String> keyStrings = new ArrayList<>();
        for(Material material : Material.values()){
            if(material.name().toLowerCase().startsWith(lowerCaseKey)){
                if(!keyStrings.contains(material.name().toLowerCase())){
                    keyStrings.add(material.name().toLowerCase());
                }
            }
        }
        return keyStrings;
    }

}
