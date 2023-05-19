package com.dndcraft.craftchat.manager;

import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftchat.storage.CraftStorage;

import java.util.HashMap;

public class ChatProfileManager {

    private HashMap<Integer, ChatProfile> profileHashMap;

    public ChatProfileManager(){
        profileHashMap = new HashMap<>();
    }

    public ChatProfile getChatProfileByAccountID(int account_id){
        return profileHashMap.getOrDefault(account_id, null);
    }

    public boolean exists(int account_id){
        return profileHashMap.containsKey(account_id);
    }

    public void createChatProfile(int account_id){
        ChatProfile profile = new ChatProfile(account_id);
        profileHashMap.put(account_id, profile);
        CraftStorage.saveChatProfile(profile);
    }

    public void loadChatProfile(int account_id){
        ChatProfile profile = CraftStorage.getChatProfile(account_id);
        profileHashMap.put(account_id, profile);
    }

}
