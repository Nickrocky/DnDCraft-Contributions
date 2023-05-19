package com.dndcraft.craftcodex.manager;

import com.dndcraft.craftcodex.account.Account;
import com.dndcraft.craftcodex.storage.CraftStorage;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class AccountManager implements com.dndcraft.craftcodex.api.manager.AccountManager {

    private static HashMap<UUID, Account> accountHashMap;

    public AccountManager(){
        accountHashMap = new HashMap<>();
    }

    public void loadOrCreate(Player player){
        Account account;
        if(!CraftStorage.existsAccount(player.getUniqueId())){
            account = new Account(player);
        }
        account = CraftStorage.getAccount(player.getUniqueId());

        accountHashMap.put(player.getUniqueId(), account);
    }

    /**
     * Gets the account
     * @param minecraftUUID the minecraft account associated
     * @return the account if set, or null if not
     * */
    @Override
    public Account getAccount(UUID minecraftUUID){
        return accountHashMap.getOrDefault(minecraftUUID, null);
    }

    /**
     * Checks if the account exists
     * @param minecraftUUID the minecraft account associated
     * @return the state of the account
     * */
    @Override
    public boolean exists(UUID minecraftUUID){
        return accountHashMap.containsKey(minecraftUUID);
    }

}
