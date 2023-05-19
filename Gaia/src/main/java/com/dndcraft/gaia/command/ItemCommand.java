package com.dndcraft.gaia.command;

import com.dndcraft.atlas.command.Commands;
import com.dndcraft.atlas.command.annotations.Arg;
import com.dndcraft.atlas.command.annotations.Default;
import com.dndcraft.atlas.command.annotations.Range;

import com.dndcraft.gaia.refactored.api.GaiaItems;
import com.dndcraft.atlas.util.InventoryUtil;
import com.google.common.collect.Lists;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemCommand extends BaseCommand{

   /* public ItemCommand(){
        Commands.defineArgumentType(ItemKeyRegistry.class)
                .completer((sender, input) ->  sender.hasPermission("gaia.admin") ? getKeyPrompt(input) : Lists.newArrayList())
                .defaultError("Unknown Item Entered!")
                .defaultName("item")
                .mapper((input) -> {
                    for(GaiaItems item : GaiaItems.values()){
                        if(item.name().toLowerCase().equals(input)) return new ItemKeyRegistry(item);
                    }
                    for(CustomBlockItems blockItems : CustomBlockItems.values()){
                        if(blockItems.name().toLowerCase().equals(input)) return new ItemKeyRegistry(blockItems);
                    }
                    for(Material material : Material.values()){
                        if(material.name().toLowerCase().equals(input)) return new ItemKeyRegistry(new ItemStack(material));
                    }
                    return null;
                })
                .register();
    }

    public void invoke(Player player, @Arg(value = "registry_key", description = "Items registry key") ItemKeyRegistry itemKeys, @Arg(value = "amount", description = "Amount you want") @Default(value = "1") @Range(min = 1, max = 64) int amount){
        ItemStack i = null;
        switch (itemKeys.getType()){
            case GAIA -> i = itemKeys.getGaia_item().toGaiaItemStack().toItemStack().clone();
            case VANILLA -> i = itemKeys.getVanilla_item().clone();
            case PROMETHEUS -> i = itemKeys.getPrometheus_item().getItemStack().clone();
        }
        i.setAmount(amount);
        InventoryUtil.addOrDropItem(player, i);
        player.sendMessage(Component.text(player.getName() + " was given " + amount+"x ").append(i.displayName()));
    }


    public enum ItemKeyType{
        VANILLA, GAIA, PROMETHEUS
    }

    @Getter
    public class ItemKeyRegistry{
        private final GaiaItems gaia_item;
        private final ItemStack vanilla_item;
        private final CustomBlockItems prometheus_item;
        private ItemKeyType type;
        public ItemKeyRegistry(CustomBlockItems prometheus_item){
            gaia_item = null;
            vanilla_item = null;
            this.prometheus_item = prometheus_item;
            type = ItemKeyType.PROMETHEUS;
        }
        public ItemKeyRegistry(GaiaItems gaia_item){
            this.gaia_item = gaia_item;
            vanilla_item = null;
            prometheus_item = null;
            type = ItemKeyType.GAIA;
        }
        public ItemKeyRegistry(ItemStack vanilla_item){
            gaia_item = null;
            this.vanilla_item = vanilla_item;
            prometheus_item = null;
            type = ItemKeyType.VANILLA;
        }
    }

    public List<String> getKeyPrompt(String input){
        final String lowerCaseKey = input.toLowerCase();
        List<String> keyStrings = new ArrayList<>();
        for(GaiaItems item : GaiaItems.values()){
            if(item.name().toLowerCase().startsWith(lowerCaseKey)){
                if(!keyStrings.contains(item.name().toLowerCase())){
                    keyStrings.add(item.name().toLowerCase());
                }
            }
        }
        for(CustomBlockItems blockItems : CustomBlockItems.values()){
            if(blockItems.name().toLowerCase().startsWith(lowerCaseKey)){
                if(!keyStrings.contains(blockItems.name().toLowerCase())){
                    keyStrings.add(blockItems.name().toLowerCase());
                }
            }
        }
        for(Material material : Material.values()){
            if(material.name().toLowerCase().startsWith(lowerCaseKey)){
                if(!keyStrings.contains(material.name().toLowerCase())){
                    keyStrings.add(material.name().toLowerCase());
                }
            }
        }
        return keyStrings;
    }
*/
}
