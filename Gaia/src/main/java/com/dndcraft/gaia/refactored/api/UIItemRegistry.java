package com.dndcraft.gaia.refactored.api;

import net.kyori.adventure.text.Component;

public enum UIItemRegistry {

    /*BANJO("Banjo", ModelMap.BANJO),
    BASE_DRUM("Base Drum", ModelMap.BASE_DRUM),
    BASS_GUITAR("Bass Guitar", ModelMap.BASS_GUITAR),
    BELL("Bell", ModelMap.BELL),
    BIT("BIT", ModelMap.BIT),
    CHIME("Chime", ModelMap.CHIME),
    COW_BELL("Cow Bell", ModelMap.COW_BELL),
    DIDGERIDOO("Didgeridoo", ModelMap.DIDGERIDOO),
    FLUTE("Flute", ModelMap.FLUTE),
    GUITAR("Guitar", ModelMap.GUITAR),
    HARP("Harp", ModelMap.HARP),
    PLAY_BUTTON("Play Button", ModelMap.PLAY_BUTTON),
    PAUSE_BUTTON("Pause Button", ModelMap.PAUSE_BUTTON),
    NULL_ITEM("Null Interface Item", ModelMap.NULL_ITEM),

    NOTE_FSHARP_1("F#", ModelMap.NOTE_FSHARP_1),
    NOTE_G_1("G", ModelMap.NOTE_G_1),
    NOTE_GSHARP_1("G#", ModelMap.NOTE_GSHARP_1),
    NOTE_A_1("A", ModelMap.NOTE_A_1),
    NOTE_ASHARP_1("A#", ModelMap.NOTE_ASHARP_1),
    NOTE_B_1("B", ModelMap.NOTE_B_1),
    NOTE_C_1("C", ModelMap.NOTE_C_1),
    NOTE_CSHARP_1("C#", ModelMap.NOTE_CSHARP_1),
    NOTE_D_1("D", ModelMap.NOTE_D_1),
    NOTE_DSHARP_1("D#", ModelMap.NOTE_DSHARP_1),
    NOTE_E_1("E", ModelMap.NOTE_E_1),
    NOTE_F_1("F", ModelMap.NOTE_F_1),
    NOTE_FSHARP_2("F#", ModelMap.NOTE_FSHARP_2),
    NOTE_G_2("G", ModelMap.NOTE_G_2),
    NOTE_GSHARP_2("G#", ModelMap.NOTE_GSHARP_2),
    NOTE_A_2("A", ModelMap.NOTE_A_2),
    NOTE_ASHARP_2("A#", ModelMap.NOTE_ASHARP_2),
    NOTE_B_2("B", ModelMap.NOTE_B_2),
    NOTE_C_2("C", ModelMap.NOTE_C_2),
    NOTE_CSHARP_2("C#", ModelMap.NOTE_CSHARP_2),
    NOTE_D_2("D", ModelMap.NOTE_D_2),
    NOTE_DSHARP_2("D#", ModelMap.NOTE_DSHARP_2),
    NOTE_E_2("E", ModelMap.NOTE_E_2),
    NOTE_F_2("F", ModelMap.NOTE_F_2),
    NOTE_FSHARP_3("F#", ModelMap.NOTE_FSHARP_3),
    ;

    private final String keyString;
    private final Component name;
    private final ModelMap map;

    UIItemRegistry(String name, ModelMap map){
        this.keyString = name();
        this.name = Component.text(name);
        this.map = map;
    }

    public static GaiaInterfaceItem get(String registryKey){
        UIItemRegistry regItem = null;
        if(UIItemRegistry.valueOf(registryKey) != null){
            regItem = UIItemRegistry.valueOf(registryKey);
        }else{
            for(UIItemRegistry i : UIItemRegistry.values()){
                if(i.keyString.equalsIgnoreCase(registryKey)){
                    regItem = i;
                }
            }
        }
        if(regItem != null){
            return regItem.toInterfaceItem();
        }else{
            return NULL_ITEM.toInterfaceItem();
        }
    }

    public GaiaInterfaceItem toInterfaceItem(){
        return new GaiaInterfaceItem(keyString, name, map);
    }*/
}
