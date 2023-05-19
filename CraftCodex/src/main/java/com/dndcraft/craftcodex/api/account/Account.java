package com.dndcraft.craftcodex.api.account;

import java.util.List;
import java.util.UUID;

public interface Account {


    /**
     * Gets the account's internal id
     * @return the account ID
     * @apiNote This will not, and can not change
     * */
    int getAccountID();

    /**
     * Gets this account's current session
     * @Return the current account session
     * @Apinote This is the same instance throughout the server for this account
     * */
    Session getSession();

    /**
     * Gets this account's DNDCraft Username
     * */
    String getDNDCraftName();

    /**
     * Gets the current discord UUID connected to this account
     * @Return DiscordUUID (this is made by us not discord)
     * */
    UUID getDiscordUUID();

    /**
     * Gets the current minecraft UUID associated with this account
     * @Return MinecraftUUID (this is made by mojang)
     * */
    UUID getMinecraftUUID();

    /**
     * Gets the current forum UUID associated with this account
     * */
    //UUID getForumUUID();

    /**
     * Gets the current list of used DNDCraft Usernames this account has had
     * */
    List<String> getDNDCraftNameHistory();
}
