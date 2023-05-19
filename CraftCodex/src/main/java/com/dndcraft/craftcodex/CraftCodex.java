package com.dndcraft.craftcodex;

import com.dndcraft.craftcodex.manager.AccountManager;
import com.dndcraft.craftcodex.manager.AccountNameManager;

public class CraftCodex extends com.dndcraft.craftcodex.api.CraftCodex {

    private static CraftCodex instance;
    public static CraftCodex get(){
        return instance;
    }

    private static AccountNameManager nameManager;
    public static AccountNameManager getNameManager(){
        return nameManager;
    }

    private static AccountManager ccAccountManager;
    public static AccountManager getCCAccountManager(){
        return ccAccountManager;
    }

    @Override
    public void onEnable() {
        api = this;
        instance = this;
        nameManager = new AccountNameManager();
        ccAccountManager = new AccountManager();
        accountManager = new AccountManager();
    }

    @Override
    public void onDisable() {

    }
}
