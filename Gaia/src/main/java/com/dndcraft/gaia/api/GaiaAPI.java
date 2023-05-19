package com.dndcraft.gaia.api;

import com.dndcraft.gaia.api.manager.ItemManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public class GaiaAPI extends JavaPlugin {

    public static final String NBT_GAIA_REGISTRY_NAME = "gaia_registry";
    public static final String NBT_GAIA_ITEM_VERSION = "gaia_item_version";
    public static final String NBT_GAIA_FOOD = "gaia_food";
    public static final String NBT_GAIA_FOOD_VALUE = "gaia_food_value";
    public static final String NBT_GAIA_FOOD_SATURATION = "gaia_food_saturation";
    public static final String NBT_GAIA_FOOD_PORTIONS = "gaia_food_portions";
    public static final String NBT_GAIA_FOOD_COMPLEX = "gaia_food_complex";
    public static final int CURRENT_ITEM_VERSION = 1;

    @Getter protected static GaiaAPI api;

    @Setter @Getter protected static ItemManager itemManager;

}
