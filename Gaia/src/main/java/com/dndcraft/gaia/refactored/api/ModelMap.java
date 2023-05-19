package com.dndcraft.gaia.refactored.api;

import lombok.Getter;
import org.bukkit.Material;

import static org.bukkit.Material.*;

@Getter
public enum ModelMap {

    /**
     * Leather, Pelts
     * */
    DEER_PELT("DEER_PELT", LEATHER, 1),
    WOLF_PELT("WOLF_PELT", RABBIT_HIDE, 1),
    RABBIT_PELT("RABBIT_PELT", RABBIT_HIDE, 2),

    COPPER_ORE("COPPER_ORE", Material.COPPER_ORE, 1),
    TIN_ORE("TIN_ORE", Material.IRON_ORE, 1),
    IRON_ORE("IRON_ORE", Material.IRON_ORE, 2),
    MYTHRIL_ORE("MYTHRIL_ORE", Material.IRON_ORE, 3),
    ECHO_SHARD("ECHO_SHARD", Material.IRON_ORE, 4),
    PETRIFIED_DUSKBARK("PETRIFIED_DUSKBARK", OAK_LOG, 5),
    NULL_ITEM("NULL_ITEM", DIRT, 1),
    ORB("ORB", RAW_IRON, 99),

    /**
     * Apollo Items
     * */
    BANJO("BANJO", GHAST_TEAR, 1),
    BASE_DRUM("BASE_DRUM", GHAST_TEAR, 2),
    BASS_GUITAR("BASS_GUITAR", GHAST_TEAR, 3),
    BELL("BELL", GHAST_TEAR, 4),
    BIT("BIT", GHAST_TEAR, 5),
    CHIME("CHIME", GHAST_TEAR, 6),
    COW_BELL("COW_BELL", GHAST_TEAR, 7),
    DIDGERIDOO("DIDGERIDOO", GHAST_TEAR, 8),
    FLUTE("FLUTE", GHAST_TEAR, 9),
    GUITAR("GUITAR", GHAST_TEAR, 10),
    HARP("HARP", GHAST_TEAR, 11),
    PLAY_BUTTON("PLAY_BUTTON", GHAST_TEAR, 12),
    PAUSE_BUTTON("PAUSE_BUTTON", GHAST_TEAR, 13),

    NOTE_ASHARP_1("ASHARP_1", GUNPOWDER, 1),
    NOTE_A_1("A_1", GUNPOWDER, 2),
    NOTE_B_1("B_1", GUNPOWDER, 3),
    NOTE_C_1("C_1", GUNPOWDER, 4),
    NOTE_CSHARP_1("CSHARP_1", GUNPOWDER, 5),
    NOTE_D_1("D_1", GUNPOWDER, 6),
    NOTE_DSHARP_1("DSHARP_1", GUNPOWDER, 7),
    NOTE_E_1("E_1", GUNPOWDER, 8),
    NOTE_F_1("F_1", GUNPOWDER, 9),
    NOTE_FSHARP_1("FSHARP_1", GUNPOWDER, 10),
    NOTE_G_1("G_1", GUNPOWDER, 11),
    NOTE_GSHARP_1("GSHARP_1", GUNPOWDER, 12),
    NOTE_ASHARP_2("ASHARP_2", GUNPOWDER, 13),
    NOTE_A_2("A_2", GUNPOWDER, 14),
    NOTE_B_2("B_2", GUNPOWDER, 15),
    NOTE_C_2("C_2", GUNPOWDER, 16),
    NOTE_CSHARP_2("CSHARP_2", GUNPOWDER, 17),
    NOTE_D_2("D_2", GUNPOWDER, 18),
    NOTE_DSHARP_2("DSHARP_2", GUNPOWDER, 19),
    NOTE_E_2("E_2", GUNPOWDER, 20),
    NOTE_F_2("F_2", GUNPOWDER, 21),
    NOTE_FSHARP_2("FSHARP_2", GUNPOWDER, 22),
    NOTE_G_2("G_2", GUNPOWDER, 23),
    NOTE_GSHARP_2("GSHARP_2", GUNPOWDER, 24),
    NOTE_FSHARP_3("FSHARP_3", GUNPOWDER, 25),

    /**
     * Noctis Items
     * */
    BASIC_LOCK("BASIC_LOCK", Material.RAW_COPPER, 1),
    ADVANCED_LOCK("ADVANCED_LOCK", RAW_COPPER, 2),
    BASIC_KEY("BASIC_KEY", Material.RAW_IRON, 1),
    ADVANCED_KEY("ADVANCED_KEY", RAW_IRON, 2),
    KEYRING("KEYRING", RAW_IRON, 3),
    BASIC_KEY_BLANK("BASIC_KEY_BLANK", RAW_IRON, 4),
    ADVANCED_KEY_BLANK("ADVANCED_KEY_BLANK", RAW_IRON, 5),

    /**
     * Prometheus Items
     * */



    ;

    private final String key;
    private final Material baseItem;
    private final int modelNumber;

    ModelMap(String key, Material baseItem, int modelNumber){
        this.key = key;
        this.baseItem = baseItem;
        this.modelNumber = modelNumber;
    }

    public static ModelMap get(String key){
        for(ModelMap m : ModelMap.values()){
            if(m.name().equals(key)) return m;
        }
        return null;
    }
}
