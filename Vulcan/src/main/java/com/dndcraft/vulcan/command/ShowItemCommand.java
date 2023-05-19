package com.dndcraft.vulcan.command;

import com.dndcraft.atlas.command.annotations.Arg;
import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import com.dndcraft.atlas.util.ItemUtil;
import com.dndcraft.atlas.util.LocationUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShowItemCommand extends BaseCommand {

    private static int RANGE = 20;

    public void invoke(Player player, @Arg(value = "destinationPlayer", description = "Player to send item to")Player destinationPlayer) {
        validate(!player.equals(destinationPlayer),"Cannot do that to yourself!");
        validate(LocationUtil.isClose(player,destinationPlayer,RANGE),"Player must be with in "+RANGE+" block radius!");
        ItemStack item = player.getInventory().getItemInMainHand();
        validate(ItemUtil.exists(item),"Must be an item!");
        var sender = new BukkitComponentBuilder();
        var reciever = new BukkitComponentBuilder();
        var subItemComponentS = new BukkitComponentBuilder();
        var subItemComponentR = new BukkitComponentBuilder();
        sender.appendBracketed("!", AtlasColor.DANDELION)
                .append(" You showing off ", AtlasColor.DANDELION)
                .append((item.getItemMeta().hasDisplayName()) ?
                        subItemComponentS.appendBracketed(item.getItemMeta().displayName(), AtlasColor.GRAY)
                                .hoverShowItem(item).build() :
                        subItemComponentS.appendBracketed(item.getItemMeta().getLocalizedName(), AtlasColor.GRAY, AtlasColor.GREEN)
                                .hoverShowItem(item).build())
                .append(" to ", AtlasColor.DANDELION)
                .append(destinationPlayer.displayName());

        reciever.appendBracketed("!", AtlasColor.DANDELION)
                .append(" "+player.displayName())
                .append(" is showing you ", AtlasColor.DANDELION)
                .append((item.getItemMeta().hasDisplayName()) ?
                        subItemComponentR.appendBracketed(item.getItemMeta().displayName(), AtlasColor.GRAY)
                                .hoverShowItem(item).build() :
                        subItemComponentR.appendBracketed(item.getItemMeta().getLocalizedName(), AtlasColor.GRAY, AtlasColor.GREEN)
                                .hoverShowItem(item).build());
        player.sendMessage(sender.build());
        destinationPlayer.sendMessage(reciever.build());
    }
}
