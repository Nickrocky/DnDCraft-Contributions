package com.dndcraft.vulcan;

import com.dndcraft.atlas.command.Commands;
import com.dndcraft.vulcan.command.*;
import com.dndcraft.vulcan.listener.TrampleListener;
import com.dndcraft.vulcan.managers.warp.WarpManager;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Vulcan extends JavaPlugin {

    private static Vulcan instance;
    public static Vulcan get(){
        return instance;
    }

    @Getter
    private WarpManager warpManager;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
        this.warpManager = new WarpManager();
        this.warpManager.load();
        registerVulcanCommandParameters();
        Commands.build(Vulcan.get().getCommand("warp"), () -> new WarpCommand());
        getServer().getPluginManager().registerEvents(new TrampleListener(this), this);
    }

    @Override
    public void onDisable() {}

    /**
     * Registers Command Custom Parameters To Atlas
     */
    private void registerVulcanCommandParameters(){
        Commands.defineArgumentType(WarpManager.Warp.class)
                .defaultName("Warp")
                .defaultError("Could not find warp by the name")
                .mapperWithSender((ignored,input)->this.warpManager.getWarp(input))
                .completer((sender,input)-> {
                    List<String> wa = new ArrayList<>();
                    this.getWarpManager().getWarps().forEach(w->{
                        if (sender.hasPermission("vulcan.warp."+w.getName()))
                            if (!wa.contains(w.getName()))
                                wa.add(w.getName());
                    });
                    return wa;
                })
                .register();
    }

}
