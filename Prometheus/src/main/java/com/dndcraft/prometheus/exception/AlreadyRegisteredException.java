package com.dndcraft.prometheus.exception;

import com.dndcraft.prometheus.block.CustomBlock;

public class AlreadyRegisteredException extends Exception{

    public AlreadyRegisteredException(CustomBlock theRegisteredOne){
        super("The block with the name " + theRegisteredOne.getRegistryKey().name() + " was already registered under the id" + theRegisteredOne.getBlockstateData().name());
    }
}
