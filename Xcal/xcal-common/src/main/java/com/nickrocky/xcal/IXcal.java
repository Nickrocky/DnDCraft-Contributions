package com.nickrocky.xcal;

import com.nickrocky.xcal.debug.XcalLogger;

import java.util.logging.Logger;

public interface IXcal {

    /**
     * Gets the global instance
     * @return the global instance for Xcal
     * @apiNote This changes widely based on platform, Spigot/Paper -> Plugin
     * @apiNote Minestom -> Extension
     * @apiNote Bungee/Velocity -> Plugin
     * */
    IXcal get();

    /**
     * This logger is ONLY for passing through the Logger from the platform to XcalLogger
     * It should NEVER be exposed to public space, and by nature will be a singleton as passed from the plugin/extension
     * @return Plugin/Extension Logger instance
     * */
    Logger getInternalLogger();

    /**
     * This logger is for general use within Xcal, please do not use Xcal's logger for logging your own plugin projects
     * */
    XcalLogger getXcalLogger();

}
