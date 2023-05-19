package com.dndcraft.gaia.api.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

@Getter
@RequiredArgsConstructor
public class ItemModel {

    private final Material baseItemMaterial;
    private final int customModelDataNumber;

}
