package com.dndcraft.craftchat.storage;

import com.dndcraft.atlas.Atlas;
import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.craftchat.chat.Channels;
import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftchat.chat.FriendRequest;
import com.dndcraft.craftchat.chat.Influence;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import com.dndcraft.craftcodex.api.account.Account;
import lombok.SneakyThrows;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class CraftStorage {

    private static String CREATE_INFLUENCE_TABLE = "CREATE TABLE IF NOT EXISTS craftchat_influence (player_account_id INT UNSIGNED, renowned INT UNSIGNED, crowns INT UNSIGNED, occurrence INT UNSIGNED, FOREIGN KEY (account_id) REFERENCES craftcodex_accounts (account_id));";
    private static String CREATE_FRIEND_TABLE = "CREATE TABLE IF NOT EXISTS craftchat_friends (player_account_id INT UNSIGNED, friend_account_id INT UNSIGNED, FOREIGN KEY (player_account_id) REFERENCES craftcodex_accounts (account_id), FOREIGN KEY (friend_account_id) REFERENCES craftcodex_accounts (account_id));";
    private static String CREATE_BLOCKED_TABLE = "CREATE TABLE IF NOT EXISTS craftchat_blocked (player_account_id INT UNSIGNED, blocked_account_id INT UNSIGNED, FOREIGN KEY (player_account_id) REFERENCES craftcodex_accounts (account_id), FOREIGN KEY (blocked_account_id) REFERENCES craftcodex_accounts (account_id));";
    private static String CREATE_CHAT_PROFILE_TABLE = "CREATE TABLE IF NOT EXISTS craftchat_chatprofiles (player_account_id INT UNSIGNED, chat_name_color VARCHAR(32), chat_msg_color VARCHAR(32), channel VARCHAR(32), FOREIGN KEY (player_account_id) REFERENCES craftcodex_accounts (account_id));";
    private static String CREATE_REQUEST_TABLE = "CREATE TABLE IF NOT EXISTS craftchat_requests (player_account_id INT UNSIGNED, requested_friend_account_id INT UNSIGNED, context TEXT, FOREIGN KEY (player_account_id) REFERENCES craftcodex_accounts (account_id), FOREIGN KEY (requested_friend_account_id) REFERENCES craftcodex_accounts (account_id));";

    @SneakyThrows
    public static void start(){
        Atlas.getSQLHandler().database().executeUpdate(CREATE_INFLUENCE_TABLE);
        Atlas.getSQLHandler().database().executeUpdate(CREATE_FRIEND_TABLE);
        Atlas.getSQLHandler().database().executeUpdate(CREATE_BLOCKED_TABLE);
        Atlas.getSQLHandler().database().executeUpdate(CREATE_CHAT_PROFILE_TABLE);
        Atlas.getSQLHandler().database().executeUpdate(CREATE_REQUEST_TABLE);
    }

    /**
     * ---------------
     * Friends List
     * ---------------
     * */

    @SneakyThrows
    public static void addFriend(int playerAccount, int personToAdd){
        String sqlCommand = "INSERT INTO craftchat_friends (player_account_id, friend_account_id) VALUES ("+playerAccount+","+personToAdd+");";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    @SneakyThrows
    public static void removeFriend(int playerAccount, int personToRemove){
        String sqlCommand = "DELETE FROM craftchat_friends WHERE player_account_id ="+playerAccount+" AND friend_account_id ="+personToRemove+";";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    @SneakyThrows
    public static List<Account> getFriends(Account playerAccount){
        List<Account> accounts = new ArrayList<>();
        for(int i : getFriends(playerAccount.getAccountID())){
            accounts.add(CraftCodexAPI.getApi().getAccountManager().getAccount(i));
        }
        return accounts;
    }

    @SneakyThrows
    public static List<Integer> getFriends(int account_id){
        String sqlCommand = "SELECT friend_account_id FROM craftchat_friends WHERE player_account_id="+account_id+";";
        var SQL_RESULT = Atlas.getSQLHandler().database().getResults(sqlCommand);
        List<Integer> friends = new ArrayList<>();
        SQL_RESULT.forEach((sql) -> friends.add(sql.getInt("friend_account_id")));
        return friends;
    }

    /**
     * ----------------
     * Friend Requests
     * ----------------
     * */

    @SneakyThrows
    public static void addFriendRequest(FriendRequest request){
        String sqlCommand = "INSERT INTO craftchat_requests (player_account_id, requested_friend_account_id, context) VALUES ("+request.getAccountIDOfSender()+","+request.getAccountIDOfRecipient()+",'"+request.getContext()+"');";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    @SneakyThrows
    public static void removeFriendRequest(FriendRequest requestToRemove){
        String sqlCommand = "DELETE FROM craftchat_requests WHERE player_account_id ="+requestToRemove.getAccountIDOfSender()+" AND requested_friend_account_id="+requestToRemove.getAccountIDOfRecipient()+";";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    @SneakyThrows
    public static List<FriendRequest> getAllFriendRequests(int account){
        List<FriendRequest> requests = new ArrayList<>();
        String sqlCommand = "SELECT * FROM craftchat_requests WHERE player_account_id ="+account+";";
        var SQL_RESULT = Atlas.getSQLHandler().database().getResults(sqlCommand);
        SQL_RESULT.forEach(sql -> requests.add(new FriendRequest(sql.getInt("player_account_id"), sql.getInt("requested_friend_account_id"), sql.getString("context"))));
        return requests;
    }

    /**
     * ----------
     * Influence
     * ----------
     * */

    @SneakyThrows
    public static void addRenown(Account playerAccount, int renown){
        String sqlCommand = "INSERT INTO craftchat_influence (account_id, renowned, occurrence) VALUES ("+playerAccount.getAccountID()+","+renown+","+LocalDate.now().atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli()+");";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    @SneakyThrows
    public static List<Influence> getInfluence(Account playerAccount){
        LocalDate date = LocalDate.now().minusDays(30);
        String sqlCommand = "SELECT renowned, occurrence FROM craftchat_influence WHERE account_id ="+playerAccount.getAccountID()+" AND occurrence >= "+date.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli()+";";
        var SQL_RESULT = Atlas.getSQLHandler().database().getResults(sqlCommand);
        List<Influence> influences = new ArrayList<>();
        SQL_RESULT.forEach((sql) -> {
            LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochMilli(sql.getInt("occurrence")), ZoneId.systemDefault());
            Influence influence = new Influence(playerAccount, sql.getInt("renowned"), localDate);
            influences.add(influence);
        });
        return influences;
    }

    /**
     * ----------------
     * Blocked Players
     * ----------------
     * */

    @SneakyThrows
    public static void addBlockedPlayer(Account playerAccount, Account playerToBlock){
        String sqlCommand = "INSERT INTO craftchat_blocked (player_account_id, blocked_account_id) VALUES ("+playerAccount.getAccountID()+","+playerToBlock.getAccountID()+");";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    @SneakyThrows
    public static void removeBlockedPlayer(Account playerAccount, Account playerToRemoveBlock){
        String sqlCommand = "DELETE FROM craftchat_blocked WHERE player_account_id ="+playerAccount.getAccountID()+" AND blocked_account_id="+playerToRemoveBlock.getAccountID()+";";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    @SneakyThrows
    public static List<Account> getBlockedPlayers(Account playerAccount){
        List<Account> accounts = new ArrayList<>();
        for(int i : getBlockedPlayers(playerAccount.getAccountID())){
            accounts.add(CraftCodexAPI.getApi().getAccountManager().getAccount(i));
        }
        return accounts;
    }

    @SneakyThrows
    public static List<Integer> getBlockedPlayers(int account_id){
        String sqlCommand = "SELECT blocked_account_id FROM craftchat_blocked WHERE player_account_id="+account_id+";";
        var SQL_RESULT = Atlas.getSQLHandler().database().getResults(sqlCommand);
        List<Integer> blocked = new ArrayList<>();
        SQL_RESULT.forEach((sql) -> {
            blocked.add(sql.getInt("blocked_account_id"));
        });
        return blocked;
    }

    /**
     * --------------
     * Chat Profiles
     * --------------
     * */
    @SneakyThrows
    public static ChatProfile getChatProfile(int account_id){
        String sqlCommand = "SELECT * FROM craftchat_chatprofiles WHERE player_account_id = "+account_id+";";
        String sqlCommand2 = "SELECT * FROM craftchat_influence WHERE player_account_id = "+account_id+";";
        var SQL_RESULT = Atlas.getSQLHandler().database().getResults(sqlCommand);
        //var SQL_RESULT2 = Atlas.getSQLHandler().database().getResults(sqlCommand2); //todo implement influence

        AtlasColor name_color = AtlasColor.valueOf(SQL_RESULT.get(0).getString("chat_name_color"));
        AtlasColor msg_color = AtlasColor.valueOf(SQL_RESULT.get(0).getString("chat_msg_color"));
        Channels channel = Channels.valueOf(SQL_RESULT.get(0).getString("channel"));
        int renown = 0;
        int crowns = 0;
        long occurrence;
        List<Integer> friends = getFriends(account_id);
        List<Integer> blocked = getBlockedPlayers(account_id);
        List<FriendRequest> requests = getAllFriendRequests(account_id);

        return new ChatProfile(account_id, renown, crowns, true, true, name_color, msg_color, channel, blocked, friends, requests);//todo fix
    }

    @SneakyThrows
    public static void saveChatProfile(ChatProfile profile){
        String sqlCommand = "INSERT INTO craftchat_chatprofiles (player_account_id, chat_name_color, chat_msg_color, channel) VALUES ("+profile.getPlayerAccount().getAccountID()+",'"+profile.getChatNameColor().name()+"','"+profile.getMsgColor().name()+"','"+profile.getChannel().name()+"');";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    public static void updateChatProfile(ChatProfile profile){

    }

}
