package com.dndcraft.prometheus.command;

import com.dndcraft.atlas.command.annotations.Arg;
import com.dndcraft.atlas.command.annotations.Cmd;
import com.dndcraft.atlas.util.InventoryUtil;
import com.dndcraft.prometheus.Prometheus;
import com.dndcraft.prometheus.api.block.CustomBlocks;
import com.dndcraft.prometheus.manager.PProItemUtil;
import org.bukkit.entity.Player;

import java.util.Locale;

public class ProCommand extends BaseCommand{

    public void invoke(Player player){
        player.sendMessage(Prometheus.VERSION);
    }

    @Cmd(value = "Gets a custom building block", permission = "prometheus.give")
    public void give(Player player, @Arg(value = "item")String customBlock){
        var item = Prometheus.getPProItemUtil().makeCustomBuildingBlockItem(CustomBlocks.valueOf(customBlock.toUpperCase()));
        InventoryUtil.addOrDropItem(player, item);
    }
}
