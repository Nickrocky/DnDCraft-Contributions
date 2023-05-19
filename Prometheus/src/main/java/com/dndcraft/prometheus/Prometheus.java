package com.dndcraft.prometheus;

import com.dndcraft.prometheus.api.PrometheusAPI;
import com.dndcraft.atlas.AtlasPaper;
import com.dndcraft.atlas.command.Commands;
import com.dndcraft.prometheus.command.ProCommand;
import com.dndcraft.prometheus.listener.ChatListener;
import com.dndcraft.prometheus.manager.PProItemUtil;
import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.prometheus.manager.PrometheusBlockRegistry;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;

public class Prometheus extends PrometheusAPI {

    public static final String NBT_PROMETHEUS_BUILDING_BLOCK = "pbb";

    private static Prometheus instance;
    public static Prometheus get(){ return instance; }

    private static PProItemUtil pProItemUtil;
    public static PProItemUtil getPProItemUtil(){
        return pProItemUtil;
    }

    private static PrometheusBlockRegistry proBlockRegistry;
    public static PrometheusBlockRegistry getProBlockRegistry(){
        return proBlockRegistry;
    }

    public static final String VERSION = "0.3.1";

    @Override
    public void onLoad() {
        PrometheusAPI.api = this;
        updateAPIReferences();
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
        pProItemUtil = new PProItemUtil();
        proBlockRegistry = new PrometheusBlockRegistry();

        var init = AtlasPaper.get().componentBuilder().append("Prometheus v"+VERSION+" Has been Enabled!", AtlasColor.GREEN).build();
        //getServer().getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getServer().getConsoleSender().sendMessage(init); //Easier than serializing the component and sending it via Logger
        Commands.build(getCommand("prometheus"), () -> new ProCommand());
    }

    private void updateAPIReferences(){
        setProItemUtil(new PProItemUtil());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
