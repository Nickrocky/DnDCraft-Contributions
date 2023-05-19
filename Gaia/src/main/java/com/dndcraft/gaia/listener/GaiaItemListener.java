package com.dndcraft.gaia.listener;

import com.dndcraft.atlas.util.ItemUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import static com.dndcraft.gaia.api.GaiaAPI.*;

public class GaiaItemListener implements Listener {

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e){
        ItemStack item = e.getItem();
        if(ItemUtil.hasTag(item, NBT_GAIA_FOOD)){
            double saturation = ItemUtil.getDoubleTag(item, NBT_GAIA_FOOD_SATURATION);
            double foodRestorationValue = ItemUtil.getDoubleTag(item, NBT_GAIA_FOOD_VALUE);
        }
    }

}
