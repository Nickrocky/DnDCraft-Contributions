package com.dndcraft.prometheus.manager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.dndcraft.prometheus.Prometheus;
import com.dndcraft.prometheus.api.block.CustomBlocks;
import com.dndcraft.prometheus.api.item.BorderColor;
import com.dndcraft.prometheus.api.manager.ProItemUtil;
import com.dndcraft.prometheus.block.CustomBlock;
import com.dndcraft.prometheus.exception.StringLengthException;
import com.dndcraft.prometheus.item.CustomToolTipBorders;
import com.dndcraft.prometheus.item.Rarity;
import com.dndcraft.prometheus.item.ResourceCategory;
import com.dndcraft.atlas.util.ItemUtil;
import com.dndcraft.atlas.util.TextUtil;
import com.dndcraft.prometheus.item.NegativeSpaces;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PProItemUtil implements ProItemUtil {
    
    @Override
    public ItemStack applyAndReturnCopy(ItemStack itemStack, BorderColor borderColor) {
        var border = CustomToolTipBorders.valueOf(borderColor.name());
        ItemStack newStack = itemStack.clone();
        ItemMeta meta = newStack.getItemMeta();
        meta.displayName(Component.text(TOP).append(itemStack.getItemMeta().displayName()));
        var lore = meta.lore();
        List<Component> processedLore = new ArrayList<>();
        int i = 0;
        for(Component c : lore){
            Component finalComponent;
            if(lore.size() == (i-1)){
                finalComponent = Component.text(""+ NegativeSpaces.negativeShift(1)+BOTTOM+ NegativeSpaces.negativeShift(7));
            }else{
                finalComponent = Component.text(""+ NegativeSpaces.negativeShift(1)+MIDDLE+ NegativeSpaces.negativeShift(7));
            }
            finalComponent.append(c);
            processedLore.add(finalComponent);
            i++;
        }
        meta.lore(processedLore);
        newStack.setItemMeta(meta);
        return newStack;
    }


    @Override
    public void sendMessage(Player player, Component component) {
        int slotOfHeld = player.getInventory().getHeldItemSlot();
        ItemStack itemInQuestion = player.getInventory().getItem(slotOfHeld);
        if(itemInQuestion == null) return;
        Prometheus.get().getLogger().info(slotOfHeld+"");
        ItemStack nameWeWantToShow = new ItemStack(itemInQuestion.getType());
        ItemMeta meta = nameWeWantToShow.getItemMeta();
        meta.displayName(component);
        meta.setCustomModelData(itemInQuestion.getItemMeta().getCustomModelData());
        nameWeWantToShow.setItemMeta(meta);

        PacketContainer setSlotPacket = new PacketContainer(PacketType.Play.Server.SET_SLOT);
        setSlotPacket.getIntegers().write(0, 0); //window id = 0 which is player inv;
        setSlotPacket.getIntegers().write(2, slotOfHeld+36); //slot to 'update'
        setSlotPacket.getItemModifier().write(0, nameWeWantToShow); //Slot Data
        try{
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, setSlotPacket);
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
        Prometheus.get().getServer().getScheduler().runTaskLater(Prometheus.get(), () -> {
            try {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, setSlotPacket);
            } catch (InvocationTargetException e) {
                //ignore if it sent once itll send a again ffs
            }
        }, 1);
    }

    //Simplify and clean up later

    private final char TOP = 'ꌐ';
    private final char MIDDLE = 'ꌑ';
    private final char BOTTOM = 'ꌒ';
    private final char BOTTOM_WEIGHT = 'ꌓ';

    public ItemStack makeCustomBuildingBlockItem(CustomBlocks block) {
        var pBorder = CustomToolTipBorders.WHITE;
        CustomBlock customBlock = Prometheus.getProBlockRegistry().getBlock(block);
        if(customBlock == null) throw new NullPointerException();
        ItemStack item = customBlock.getBaseItem();
        var meta = item.getItemMeta();

        try {
            meta.displayName(Component.text(customBlock.getItemName()).decoration(TextDecoration.ITALIC, false));
            List<String> rawLore = TextUtil.descriptionToListString(customBlock.getDescription());
            List<Component> processedLore = new ArrayList<>();

            processedLore.add(NegativeSpaces.createShiftedSubTitleComponent(customBlock.getItemName(), customBlock.getCategory().getResourceCatChar(), pBorder.getColor()));
            processedLore.add(NegativeSpaces.createShiftedComponent(Rarity.COMMON.getRarityComponent(), MIDDLE, pBorder.getColor()));
            for(int i = 0; i < rawLore.size(); i++){
                processedLore.add(NegativeSpaces.createShiftedComponent(rawLore.get(i), MIDDLE, pBorder.getColor()));
            }
            processedLore.add(NegativeSpaces.createShiftedComponent(" ", BOTTOM, pBorder.getColor()));
            meta.lore(processedLore);
        }catch (StringLengthException e){
            e.printStackTrace();
        }
        item.setItemMeta(meta);
        ItemUtil.setTag(item, Prometheus.NBT_PROMETHEUS_BUILDING_BLOCK, customBlock.getRegistryKey().name().toLowerCase());
        return item;
    }

    @Override
    public ItemStack makeGaiaBorderedItem(Material material, BorderColor borderColor, Component rarityComponent, String category, double weight, String displayName, String lore) {
        var pBorder = CustomToolTipBorders.valueOf(borderColor.name());
        ItemStack itemStack = new ItemStack(material);

        ItemMeta meta = itemStack.getItemMeta();
        try {
            meta.displayName(Component.text(displayName).decoration(TextDecoration.ITALIC, false));
            List<String> rawLore = TextUtil.descriptionToListString(lore);
            List<Component> processedLore = new ArrayList<>();

            processedLore.add(NegativeSpaces.createShiftedSubTitleComponent(displayName, ResourceCategory.valueOf(category).getResourceCatChar(), pBorder.getColor()));
            processedLore.add(NegativeSpaces.createShiftedComponent(rarityComponent, MIDDLE, pBorder.getColor()));
            for(int i = 0; i < rawLore.size(); i++){
                processedLore.add(NegativeSpaces.createShiftedComponent(rawLore.get(i), MIDDLE, pBorder.getColor()));
            }
            if(weight <= 0){
                processedLore.add(NegativeSpaces.createShiftedComponent(" ", BOTTOM, pBorder.getColor()));
            }else{
                processedLore.add(NegativeSpaces.createShiftedComponent(weight+"", BOTTOM_WEIGHT, pBorder.getColor()));
            }
            meta.lore(processedLore);
        }catch (StringLengthException e){
            e.printStackTrace();
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     * Makes an alchemy item for Gaia because they are *special* kms
     *
     * @param material        item base material
     * @param borderColor     the color of the border
     * @param rarityComponent the rarity component for the item in the lore
     * @param registryName    the registry name of the item in gaia
     * @param displayName     the display name of the item
     * @param lore            the lore of a particular item
     */
    @Override
    public ItemStack makeGaiaAlchemyReagentItem(Material material, BorderColor borderColor, Component rarityComponent, String registryName, String displayName, String lore) {
        var pBorder = CustomToolTipBorders.valueOf(borderColor.name());
        ItemStack itemStack = new ItemStack(material);

        ItemMeta meta = itemStack.getItemMeta();
        try {
            meta.displayName(Component.text(displayName).decoration(TextDecoration.ITALIC, false));
            List<String> rawLore = TextUtil.descriptionToListString(lore);
            List<Component> processedLore = new ArrayList<>();

            processedLore.add(NegativeSpaces.createShiftedSubTitleComponent(displayName, ResourceCategory.REAGENT.getResourceCatChar(), pBorder.getColor()));
            //processedLore.add(createShiftedComponent(rarityComponent, ALCH_MIDDLE, pBorder.getColor()));
            for(int i = 0; i < rawLore.size(); i++){
            //    processedLore.add(createShiftedComponent(rawLore.get(i), ALCH_MIDDLE, pBorder.getColor()));
            }
            //processedLore.add(createShiftedComponent(weight+"", ALCH_BOTTOM_WEIGHT, pBorder.getColor()));
            meta.lore(processedLore);
        }catch (StringLengthException e){
            e.printStackTrace();
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public ItemStack makeBorderedItem(Material material, BorderColor borderColor, String displayName, String lore) {
        var pBorder = CustomToolTipBorders.valueOf(borderColor.name());
        ItemStack itemStack = new ItemStack(material);

        ItemMeta meta = itemStack.getItemMeta();
        try {
            meta.displayName(NegativeSpaces.createShiftedComponent(displayName, TOP, pBorder.getColor()));
            List<String> rawLore = TextUtil.descriptionToListString(lore);
            List<Component> processedLore = new ArrayList<>();
            for(int i = 0; i < processedLore.size(); i++){
                if(i == processedLore.size()){
                    processedLore.add(NegativeSpaces.createShiftedComponent(rawLore.get(i), BOTTOM, pBorder.getColor()));
                }else{
                    processedLore.add(NegativeSpaces.createShiftedComponent(rawLore.get(i), MIDDLE, pBorder.getColor()));

                }
            }
            meta.lore(processedLore);
        }catch (StringLengthException e){
            e.printStackTrace();
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public void apply(ItemStack itemStack, BorderColor borderColor) {
        //var border = CustomToolTipBorders.valueOf(borderColor.name()).getCustomToolTipBorder();
        //ItemMeta meta = itemStack.getItemMeta();

    }

    /**
     * meta.displayName(Component.text(border.getTop()).append(itemStack.getItemMeta().displayName()));
     *         var lore = meta.lore();
     *         List<Component> processedLore = new ArrayList<>();
     *         int i = 0;
     *         for(Component c : lore){
     *             Component finalComponent;
     *             if(lore.size() == (i-1)){
     *                 finalComponent = Component.text(""+negativeShift(1)+border.getLower()+negativeShift(7));
     *             }else{
     *                 finalComponent = Component.text(""+negativeShift(1)+border.getMiddle()+negativeShift(7));
     *             }
     *             finalComponent.append(c);
     *             processedLore.add(finalComponent);
     *             i++;
     *         }
     *         meta.lore(processedLore);
     *         itemStack.setItemMeta(meta);
     * */

}
