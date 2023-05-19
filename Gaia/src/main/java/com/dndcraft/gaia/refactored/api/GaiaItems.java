package com.dndcraft.gaia.refactored.api;

import com.dndcraft.gaia.api.item.GaiaRarity;
import com.dndcraft.gaia.api.item.ResourceCategory;
import lombok.RequiredArgsConstructor;

import static com.dndcraft.gaia.api.item.GaiaRarity.*;

@RequiredArgsConstructor
public enum GaiaItems {

    /*DEER_PELT("DEER_PELT", "Deer Pelt", ModelMap.DEER_PELT, COMMON, ResourceCategory.CRAFTING_MATERIAL, "A dun colored plush hide with decent warming potential"),
    WOLF_PELT("WOLF_PELT","Wolf Pelt", ModelMap.WOLF_PELT, UNCOMMON, ResourceCategory.CRAFTING_MATERIAL,"The dense fair colored pelt of a fine wolf."),
    RABBIT_PELT("RABBIT_PELT","Rabbit Pelt", ModelMap.RABBIT_PELT, UNCOMMON, ResourceCategory.CRAFTING_MATERIAL,"The fair light warm pelt of a sizable rabbit."),

    COPPER_ORE("COPPER_ORE","Copper Ore", ModelMap.COPPER_ORE, COMMON, ResourceCategory.ROCKS_N_MINERALS, ""),
    TIN_ORE("TIN_ORE","Tin Ore", ModelMap.TIN_ORE, COMMON, ResourceCategory.ROCKS_N_MINERALS,""),
    IRON_ORE("IRON_ORE","Iron Ore", ModelMap.IRON_ORE, UNCOMMON, ResourceCategory.ROCKS_N_MINERALS, ""),
    MYTHRIL_ORE("MYTHRIL_ORE", "Mythril Ore", ModelMap.MYTHRIL_ORE, LEGENDARY, ResourceCategory.ROCKS_N_MINERALS,"Said to be born of the tears of titans, now here it rests in your arms."),
    ECHO_SHARD("ECHO_SHARD", "Echo Shard", ModelMap.ECHO_SHARD, RARE, ResourceCategory.ROCKS_N_MINERALS,"The gentle hum of a chord can be heard, as the crystal glows a faint baby blue."),
    PETRIFIED_DUSKBARK("PETRIFIED_DUSKBARK", "Petrified Duskbark", ModelMap.PETRIFIED_DUSKBARK, EPIC, ResourceCategory.CRAFTING_MATERIAL,"The rather ashy complexion of this bark seems oddly familiar."),
    ORB("ORB", "Orb", ModelMap.ORB, RELIC, ResourceCategory.MISCELLANEOUS,"With a such a concentrated source of magic comes great responsibility... and reckless fun."),
    //Apollo
    //todo add apollo items for players to have
    //Noctis
    BASIC_LOCK("BASIC_LOCK","Basic Lock", ModelMap.BASIC_LOCK, COMMON, ResourceCategory.MISCELLANEOUS,"Lock em' up"),
    ADVANCED_LOCK("ADVANCED_LOCK","Advanced Lock", ModelMap.ADVANCED_LOCK, COMMON, ResourceCategory.MISCELLANEOUS,"Lock em' up x2"),
    BASIC_KEY_BLANK("BASIC_KEY_BLANK","Basic Key Blank", ModelMap.BASIC_KEY_BLANK, COMMON, ResourceCategory.MISCELLANEOUS,"Lock em' up Blank"),
    ADVANCED_KEY_BLANK("ADVANCED_KEY_BLANK","Advanced Key Blank", ModelMap.ADVANCED_KEY_BLANK, COMMON, ResourceCategory.MISCELLANEOUS,"Lock em' up Blank (Advanced)"),
    BASIC_KEY("BASIC_KEY","Basic Key", ModelMap.BASIC_KEY, COMMON, ResourceCategory.MISCELLANEOUS,"A small fine toothed copper key, for every key there is a lock. Lets hope you know where that lock may be."),
    ADVANCED_KEY("ADVANCED_KEY","Advanced Key", ModelMap.ADVANCED_KEY, COMMON, ResourceCategory.MISCELLANEOUS,"Open em' up x2"),
    KEYRING("KEYRING","Keyring", ModelMap.KEYRING, COMMON, ResourceCategory.MISCELLANEOUS,"Oh master of keys"),
    NULL_ITEM("OH_SHIT","Null Item", ModelMap.NULL_ITEM, COMMON, ResourceCategory.MISCELLANEOUS,""),
    ;

    private final String registryKey;
    private final String name;
    private final ModelMap modelMap;
    private final GaiaRarity rarity;
    private final ResourceCategory category;
    private final String description;


    /*public static GaiaItem get(String registryKey){
        GaiaItems regItem = null;
        if(GaiaItems.valueOf(registryKey) != null){
            regItem = GaiaItems.valueOf(registryKey);
        }else{
            for(GaiaItems i : GaiaItems.values()){
                if(i.registryKey.equalsIgnoreCase(registryKey)){
                    regItem = i;
                }
            }
        }
        if(regItem != null){
            return regItem.toGaiaItemStack();
        }else{
            return NULL_ITEM.toGaiaItemStack();
        }
    }

    public GaiaItem toGaiaItemStack(){
        return new GaiaItem(registryKey, name, modelMap, rarity, category, description);
    }*/
}
