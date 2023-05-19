package com.nickrocky.xcal;

import com.nickrocky.xcal.debug.XcalLogger;
import com.nickrocky.xcal.util.cmd.CMDColorType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Xcal extends JavaPlugin implements IXcal {

    private static XcalLogger xcalLogger;

    @Override
    public void onEnable() {
        xcalLogger = new XcalLogger(getLogger(), CMDColorType.CMD_COLOR_256);



        xcalLogger.log("e'Xcal'ibur has been enabled!");
    }

    @Override
    public void onDisable() {

    }


    /**
     * Gets the global instance
     *
     * @return the global instance for Xcal
     * @apiNote This changes widely based on platform, Spigot/Paper -> Plugin
     * @apiNote Minestom -> Extension
     * @apiNote Bungee/Velocity -> Plugin
     */
    @Override
    public IXcal get() {
        return (IXcal) JavaPlugin.getProvidingPlugin(Xcal.class);
    }

    /**
     * This logger is ONLY for passing through the Logger from the platform to XcalLogger
     * It should NEVER be exposed to public space, and by nature will be a singleton as passed from the plugin/extension
     *
     * @return Plugin/Extension Logger instance
     */
    @Override
    public Logger getInternalLogger() {
        return this.getLogger();
    }

    /**
     * This logger is for general use within Xcal, please do not use Xcal's logger for logging your own plugin projects
     */
    @Override
    public XcalLogger getXcalLogger() {
        return xcalLogger;
    }
}
