package com.dndcraft.craftcodex.api.manager;

import com.dndcraft.craftcodex.account.Account;

import java.util.UUID;

public interface AccountManager {

    /**
     * Gets the account
     * @param minecraftUUID the minecraft account associated
     * @return the account if set, or null if not
     * */
    Account getAccount(UUID minecraftUUID);


    /**
     * Checks if the account exists
     * @param minecraftUUID the minecraft account associated
     * @return the state of the account
     * */
    boolean exists(UUID minecraftUUID);

}
