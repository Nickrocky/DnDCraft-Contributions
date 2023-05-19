package com.dndcraft.craftcodex.account;

import com.dndcraft.atlas.util.objects.AtlasLocation;

import com.dndcraft.craftcodex.CraftCodex;
import com.dndcraft.craftcodex.storage.CraftStorage;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@Setter
public class Account implements com.dndcraft.craftcodex.api.account.Account {

    private int account_id; //SQL-ID
    private UUID discordUUID;//Make a bot for this
    private UUID minecraftUUID;
    private UUID forumUUID;
    private Session currentSession;
    private String dndcraftName;


    //Logging in for the first time
    public Account(Player player){
        this.minecraftUUID = player.getUniqueId();
        this.discordUUID = null;
        this.forumUUID = null;
        this.setDndcraftName(CraftCodex.getNameManager().generateDnDCraftName());
        currentSession = new Session(account_id, new AtlasLocation(player.getLocation()));
    }

    public Account(int account_id, UUID minecraftUUID, UUID forumUUID, UUID discordUUID, String dndcraftName){
        this.minecraftUUID = minecraftUUID;
        this.account_id = account_id;
        this.discordUUID = discordUUID;
        this.forumUUID = forumUUID;
        this.dndcraftName = dndcraftName;
    }

    public void changeDNDCraftName(){
        CraftCodex.getNameManager().updateDnDCraftName(this);
    }

    /**
     * Gets the account's internal id
     *
     * @return the account ID
     * @apiNote This will not, and can not change
     */
    @Override
    public int getAccountID() {
        return account_id;
    }

    /**
     * Gets this account's current session
     *
     * @Return the current account session
     * @Apinote This is the same instance throughout the server for this account
     */
    @Override
    public Session getSession() {
        return currentSession;
    }

    @Override
    public String getDNDCraftName() {
        return dndcraftName;
    }

    /**
     * Gets the current discord UUID connected to this account
     *
     * @Return DiscordUUID (this is made by us not discord)
     */
    @Override
    public UUID getDiscordUUID() {
        return discordUUID;
    }

    /**
     * Gets the current minecraft UUID associated with this account
     */
    @Override
    public UUID getMinecraftUUID() {
        return minecraftUUID;
    }

    /**
     * Gets the current list of used DNDCraft Usernames this account has had
     */
    @Override
    public List<String> getDNDCraftNameHistory() {
        return CraftStorage.getDnDCraftNameHistory(this);
    }
}
