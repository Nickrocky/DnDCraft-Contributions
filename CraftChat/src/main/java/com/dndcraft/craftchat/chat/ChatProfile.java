package com.dndcraft.craftchat.chat;

import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import com.dndcraft.craftcodex.api.account.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChatProfile {

    private Channels channel;
    private Account playerAccount;
    private int playerCCAccountID; //Account ID
    private List<Integer> friendsList; //List of Account IDs
    private List<Integer> blockedList; //List of Blocked IDs
    private List<FriendRequest> requests;

    private int renown; //This is like reputation - This resets
    private int crown; //This is for ppl that go above and beyond - This does NOT reset  - Reddit awards

    private AtlasColor chatNameColor;
    private AtlasColor msgColor;

    private boolean opt_GLOBAL_OOC, opt_HELP_CHAT;

    public ChatProfile(int playerCCAccountID){
        playerAccount = CraftCodexAPI.getApi().getAccountManager().getAccount(playerCCAccountID);
        this.playerCCAccountID = playerCCAccountID;
        friendsList = new ArrayList<>();
        blockedList = new ArrayList<>();
        requests = new ArrayList<>();
        opt_GLOBAL_OOC = true;
        opt_HELP_CHAT = true;
        chatNameColor = AtlasColor.DARK_GRAY;
        msgColor = AtlasColor.GRAY;
        renown = 0;
        crown = 0;
        channel = Channels.GLOBAL_OOC;
    }

    //Ouch
    public ChatProfile(int playerCCAccountID, int renown, int crown, boolean opt_GLOBAL_OOC, boolean opt_HELP_CHAT, AtlasColor chatNameColor, AtlasColor msgColor, Channels channel, List<Integer> blockedList, List<Integer> friendsList, List<FriendRequest> requests){
        this.playerCCAccountID = playerCCAccountID;
        this.playerAccount = CraftCodexAPI.getApi().getAccountManager().getAccount(playerCCAccountID);
        this.renown = renown;
        this.crown = crown;
        this.opt_GLOBAL_OOC = opt_GLOBAL_OOC;
        this.opt_HELP_CHAT = opt_HELP_CHAT;
        this.chatNameColor = chatNameColor;
        this.msgColor = msgColor;
        this.blockedList = blockedList;
        this.friendsList = friendsList;
        this.requests = requests;
        this.channel = channel;
    }

}
