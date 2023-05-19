package com.dndcraft.craftchat.util;

import com.dndcraft.craftchat.storage.CraftStorage;
import com.dndcraft.craftcodex.api.account.Account;


public class InfluenceUtil {

    public static int calculateCurrentRenown(Account account){
        var influence = CraftStorage.getInfluence(account);
        int renown = 0;
        for(var i : influence){
            renown += i.getRenown();
        }
        return renown;
    }

}
