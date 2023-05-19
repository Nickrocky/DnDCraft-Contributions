package com.dndcraft.prometheus.manager;

import com.dndcraft.prometheus.api.block.CustomBlocks;
import com.dndcraft.prometheus.block.CustomBlock;
import com.dndcraft.prometheus.block.CustomNoteblockBlock;
import com.dndcraft.prometheus.exception.AlreadyRegisteredException;
import com.dndcraft.prometheus.item.ResourceCategory;
import lombok.SneakyThrows;

import java.util.HashMap;

public class PrometheusBlockRegistry {

    private static HashMap<CustomBlocks, CustomBlock> blockRegistry;

    public PrometheusBlockRegistry(){
        blockRegistry = new HashMap<>();
        registerNoteBlocks();
    }

    public CustomBlock getBlock(CustomBlocks block){
        if(!blockRegistry.containsKey(block)) return null;
        return blockRegistry.get(block);
    }

    //This is here just to sanity check
    @SneakyThrows
    private void registerNoteBlock(CustomBlock block){
        if(blockRegistry.containsKey(block.getRegistryKey())) throw new AlreadyRegisteredException(block);
        blockRegistry.put(block.getRegistryKey(), block);
    }

    private void registerNoteBlocks(){
        registerNoteBlock(new CustomBlock(CustomBlocks.MYTHRIL, CustomNoteblockBlock.AA, 999999, ResourceCategory.ROCKS_N_MINERALS,"Mythril Ore", "A small chunk of pale blue metal said to have been made at the dawn of the world."));
    }

}
