package com.dndcraft.gaia;

import com.dndcraft.prometheus.api.PrometheusAPI;
import com.dndcraft.gaia.api.GaiaAPI;
import com.dndcraft.atlas.command.Commands;
import com.dndcraft.gaia.command.GaiaCommand;
import com.dndcraft.gaia.command.ItemCommand;
import com.dndcraft.gaia.manager.ItemManager;
import lombok.Getter;
import lombok.SneakyThrows;


public class Gaia extends GaiaAPI {

    public static final String VERSION = "0.4.2";

    private static Gaia instance;
    public static Gaia get(){return instance;}

    @Getter private static ItemManager realItemManager;

    @Override
    public void onLoad() {
        api = this;
        setItemManager(new ItemManager());
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;

        realItemManager = new ItemManager();
        Commands.build(getCommand("gaia"), () -> new GaiaCommand());
        Commands.build(getCommand("i"), () -> new ItemCommand());
        getLogger().severe("PrometheusAPI " + PrometheusAPI.getApi().getName());
        getLogger().severe("Promethus ProItemUtil " + (PrometheusAPI.getApi().getProItemUtil()!=null));
        //getLogger().severe("Promethus CustomBlocks " + (PrometheusAPI.getApi().getCustomBlockManager()!=null));

    }

    @Override
    public void onDisable() {

    }
}
