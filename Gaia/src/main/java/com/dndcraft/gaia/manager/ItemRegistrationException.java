package com.dndcraft.gaia.manager;

import com.dndcraft.gaia.Gaia;
import com.dndcraft.gaia.api.item.GaiaItem;

public class ItemRegistrationException extends Exception{

    public ItemRegistrationException(GaiaItem gaiaItem){
        super("The item you were attempting to register wasn't able to be registered for the following reasons: \n" +
                (gaiaItem.isValid() ? "Is Valid: YES\n" : "Is Valid: NO \n") +
                (Gaia.getItemManager().listRegisteredItems().contains(gaiaItem.getRegistryName()) ? "Is already registered: YES\n" : "Is already registered: NO\n") +
                ((Gaia.getRealItemManager().isItemModelRegistered(gaiaItem)) ? "Is ItemModel already registered: YES\n" : "Is ItemModel already registered: NO\n"));
    }
}
