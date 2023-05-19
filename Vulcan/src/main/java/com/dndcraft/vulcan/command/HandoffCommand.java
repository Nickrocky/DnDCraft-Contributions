package com.dndcraft.vulcan.command;

import com.dndcraft.atlas.command.annotations.Arg;
import com.dndcraft.atlas.command.annotations.Default;
import com.dndcraft.atlas.command.annotations.Range;
import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import com.dndcraft.atlas.util.InventoryUtil;
import com.dndcraft.atlas.util.MessageUtil;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

public class HandoffCommand extends BaseCommand {

    public void invoke(Player player, @Arg(value = "destinationPlayer", description = "Player to send item to")Player destinationPlayer, @Arg("amount")@Default("1")@Range(min=1, max=64)int amount){
        if(destinationPlayer != null){
            ItemStack item = player.getInventory().getItemInMainHand();
            if(item.getAmount() < amount){
                var builder = new BukkitComponentBuilder();
                builder.append(MessageUtil.getErrorComponent("You do not have enough of this item", "Have more of the item you are trying to hand over in your hand"));
                player.sendMessage(builder.build());
                return;
            }
            var sender = new BukkitComponentBuilder();
            var reciever = new BukkitComponentBuilder();
            var subItemComponentS = new BukkitComponentBuilder();
            var subItemComponentR = new BukkitComponentBuilder();

            sender
                    .appendBracketed("!", AtlasColor.DANDELION)
                    .append(" You hand off ", AtlasColor.DANDELION)
                    .appendBracketed(subItemComponentS.hoverShowItem(item).build())
                    /*.append((item.getItemMeta().hasDisplayName()) ?
                            subItemComponentS.appendBracketed(item.getItemMeta().displayName(), AtlasColor.GRAY)
                                    .hoverShowItem(item).build() :
                            subItemComponentS.appendBracketed(item.getI18NDisplayName(), AtlasColor.GRAY, AtlasColor.GREEN)
                                    .hoverShowItem(item).build())*/
                    .append(" to ", AtlasColor.DANDELION)
                    .append(destinationPlayer.displayName());

            reciever
                    .appendBracketed("!", AtlasColor.DANDELION)
                    .append(" You have received ", AtlasColor.DANDELION)
                    .appendBracketed(subItemComponentR.hoverShowItem(item).build())
                    /*.append((item.getItemMeta().hasDisplayName()) ?
                            subItemComponentR.appendBracketed(item.getItemMeta().displayName(), AtlasColor.GRAY)
                                    .hoverShowItem(item).build() :
                            subItemComponentR.appendBracketed(item.getI18NDisplayName(), AtlasColor.GRAY, AtlasColor.GREEN)
                                    .hoverShowItem(item).build())*/
                    .append(" from ", AtlasColor.DANDELION)
                    .append(destinationPlayer.displayName());

            player.sendMessage(sender.build());
            destinationPlayer.sendMessage(reciever.build());
            ItemStack handedItem = item.clone();
            handedItem.setAmount(amount);
            InventoryUtil.addOrDropItem(destinationPlayer, handedItem);
            item.setAmount(item.getAmount()-amount);
        }else{
            var builder = new BukkitComponentBuilder();
            builder.append(MessageUtil.getErrorComponent("No valid players to hand this item to.", "Please either move closer to a player, or look at a player to hand the item to."));
            player.sendMessage(builder.build());
        }
    }

}
