package com.dndcraft.gaia.api.item.exception;

public class InvalidGaiaItemException extends Exception{

    public InvalidGaiaItemException(String registryKey){
        super("The registry key " + registryKey + "could not be found. Perhaps it isn't registered in the ItemRegistry.");
    }
}
