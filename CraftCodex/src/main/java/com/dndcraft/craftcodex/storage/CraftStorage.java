package com.dndcraft.craftcodex.storage;

import com.dndcraft.atlas.Atlas;
import com.dndcraft.craftcodex.account.Account;
import com.dndcraft.craftcodex.account.Session;
import com.dndcraft.craftcodex.character.Character;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CraftStorage {

    //Account storage, this is your uuid, discord uuid, and forum uuid
    private static String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS accounts (account_id INT UNSIGNED, minecraft_id VARCHAR(64), forum_id VARCHAR(64), discord_id VARCHAR(64), PRIMARY KEY(account_id));";

    //This is a table of all login sessions on the server
    private static String CREATE_ACCOUNT_SESSION_TABLE = "CREATE TABLE IF NOT EXISTS account_sessions (account_id INT UNSIGNED, character_id INT UNSIGNED, login LONG, logout LONG, world VARCHAR(32), x DOUBLE, y DOUBLE, z DOUBLE, time_played INT UNSIGNED, FOREIGN KEY (account_id) REFERENCES accounts (account_id), FOREIGN KEY (character_id) REFERENCES characters (character_id));";

    //This is all of the known ip's linked to a given UUID (minecraft)
    private static String CREATE_ACCOUNT_IPS = "CREATE TABLE IF NOT EXISTS account_ips (account_id INT UNSIGNED, ip_address VARCHAR(64) NOT NULL, PRIMARY KEY(account_id, ip_address), FOREIGN KEY (account_id) REFERENCES accounts (account_id));";

    //This is DnDCraft Names, it is stored different bc I dont want names to overlap, if someone discards a name it should b dead and not able to be reused.
    //The entire point of this system was to curb abuse so if someone gets an abusers name that would be not great
    private static String CREATE_ACCOUNT_DNDCRAFT_NAME_TABLE = "CREATE TABLE IF NOT EXISTS account_name (id INT UNSIGNED, account_id INT UNSIGNED, dndcraft_name VARCHAR(64), current_name BOOLEAN DEFAULT false, FOREIGN KEY (account_id) REFERENCES accounts (account_id), PRIMARY KEY (id));";

    //This is all of the known characters linked to a given account
    private static String CREATE_CHARACTER_TABLE = "CREATE TABLE IF NOT EXISTS characters (character_id INT UNSIGNED, account_id INT UNSIGNED, slot INT UNSIGNED NOT NULL, slot_type INT UNSIGNED DEFAULT 0, money DOUBLE DEFAULT 100.0, currently_selected BOOLEAN DEFAULT false, PRIMARY KEY (character_id), FOREIGN KEY(account_id) REFERENCES accounts (account_id));";

    //This is all of the data we need to properly deal with chat prefixes, names, etc
    private static String CREATE_CHARACTER_CHAT_TABLE = "CREATE TABLE IF NOT EXISTS character_chat (character_id INT UNSIGNED, name VARCHAR(32), prefix VARCHAR(32), FOREIGN KEY (character_id) REFERENCES characters (character_id));";

    //This is information regarding the creation of a players character, playtime etc
    private static String CREATE_CHARACTER_CREATION_TABLE = "CREATE TABLE IF NOT EXISTS character_creation (character_id INT UNSIGNED, date_created DATETIME(3));";

    //This is the data tags stored on a particular character
    private static String CREATE_CHARACTER_TAG_TABLE = "CREATE TABLE IF NOT EXISTS character_tag (character_id INT UNSIGNED, key VARCHAR(255) NOT NULL, value TEXT);";

    //This stores the vitality of parts, location of the character, hunger, and saturation
    private static String CREATE_CHARACTER_DATA_TABLE = "CREATE TABLE IF NOT EXISTS character_data (character_id INT UNSIGNED, energy DOUBLE DEFAULT 0.0, race INT UNSIGNED, birthdate INT DEFAULT 0, gender INT UNSIGNED, saturation FLOAT DEFAULT 0.0, hunger INT DEFAULT 20, world VARCHAR(32), x DOUBLE, y DOUBLE, z DOUBLE, inventory TEXT, modmail TEXT, PRIMARY KEY(character_id), FOREIGN KEY (character_id) REFERENCES characters (character_id));";

    private static String CREATE_CHARACTER_HEALTH_DATA_TABLE = "CREATE TABLE IF NOT EXISTS character_health (character_id INT UNSIGNED, head INT UNSIGNED, chest INT UNSIGNED, waist INT UNSIGNED, left_arm INT UNSIGNED, right_arm INT UNSIGNED, left_leg INT UNSIGNED, right_leg INT UNSIGNED, right_hand INT UNSIGNED, left_hand INT UNSIGNED, PRIMARY KEY(character_id), FOREIGN KEY(character_id) REFERENCES characters (character_id));";

    private static void setupNewCraftStorage() {
        try {
            Atlas.getSQLHandler().database().executeUpdate(CREATE_ACCOUNT_TABLE);
            Atlas.getSQLHandler().database().executeUpdate(CREATE_ACCOUNT_SESSION_TABLE);
            Atlas.getSQLHandler().database().executeUpdate(CREATE_ACCOUNT_DNDCRAFT_NAME_TABLE);
            Atlas.getSQLHandler().database().executeUpdate(CREATE_ACCOUNT_IPS);
            Atlas.getSQLHandler().database().executeUpdate(CREATE_CHARACTER_TABLE);
            Atlas.getSQLHandler().database().executeUpdate(CREATE_CHARACTER_CREATION_TABLE);
            Atlas.getSQLHandler().database().executeUpdate(CREATE_CHARACTER_CHAT_TABLE);
            Atlas.getSQLHandler().database().executeUpdate(CREATE_CHARACTER_TAG_TABLE);
            Atlas.getSQLHandler().database().executeUpdate(CREATE_CHARACTER_DATA_TABLE);
            Atlas.getSQLHandler().database().executeUpdate(CREATE_CHARACTER_HEALTH_DATA_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void start(){
        setupNewCraftStorage();
    }

    /**
     * Stores a new entry in the SQLite DB of an Account (This is usually only done on the first login)
     * @param account The account you want to save in the DB
     * @APINote: This will NOT save the current session the player is in, this is due to the fact that the session wont end till logout.
     * */
    @SneakyThrows
    public static void addEntry(Account account){
        String sqlCommand = "INSERT INTO accounts (minecraft_id, forum_id, discord_id) VALUES ("+account.getMinecraftUUID()+",dummyID,dummyID);";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    /**
     * Adds the new DnDCraft Name to the db
     * @param name The new name for the account
     * @param account_id the account this name is associated with
     * */
    @SneakyThrows
    public static void addDnDCraftNameEntry(String name, int account_id){
        String sqlCommand = "INSERT INTO account_name (account_id, dndcraft_name, current_name) VALUES ("+account_id+","+name+",true);";
        Atlas.getSQLHandler().database().executeUpdate(sqlCommand);
    }

    public static void addEntry(Session session){

    }

    public static void addEntry(Character character){

    }

    /**
     * Updates an Account's DnDCraft Name
     * @param name The new name for the account
     * @param account_id The id of the account you want to update
     * */
    @SneakyThrows
    public static void updateDnDCraftName(String name, int account_id){
        String sqlCmd = "UPDATE account_name SET current_name = false WHERE current_name = true AND WHERE account_id = "+account_id+";";
        Atlas.getSQLHandler().database().executeUpdate(sqlCmd);
        addDnDCraftNameEntry(name, account_id);
    }

    /**
     * Checks if a given DnDCraft name actually exists in the DB
     * @param name the name you are ensuring isnt already used
     * @return if a name exists or not
     * */
    @SneakyThrows
    public static boolean existsDnDCraftName(String name){
        String sqlCommand = "SELECT * FROM account_name WHERE current_name = '"+name+"';";
        var SQL_RESULT = Atlas.getSQLHandler().database().getResults(sqlCommand);
        if(SQL_RESULT.size() >= 1) return true;
        return false;
    }

    /**
     * Checks if a given Account exists in the DB
     * @param minecraftUUID the minecraft UUID you are looking to check for an account on
     * @return if the account exists or not
     * */
    @SneakyThrows
    public static boolean existsAccount(UUID minecraftUUID){
        String sqlCommand = "SELECT * FROM accounts WHERE minecraft_id = "+minecraftUUID.toString()+";";
        var results = Atlas.getSQLHandler().database().getResults(sqlCommand);
        if(results.size() >= 1){
            return true;
        }
        return false;
    }

    /**
     * Returns a list of the current and former dndcraft names
     * @param account The account you want name history for
     * @return a list with the current account name at index 0, and every subsequent name in order that has been used.
     * */
    @SneakyThrows
    public static List<String> getDnDCraftNameHistory(Account account){
        List<String> names = new ArrayList<>();
        names.add(account.getDNDCraftName());
        String sqlCommand = "SELECT id, account_id, dndcraft_name, current_name FROM account_name WHERE current_name = false AND account_id = " +account.getAccountID()+" ORDER BY id ASC;";
        var SQL_RESULT = Atlas.getSQLHandler().database().getResults(sqlCommand);
        SQL_RESULT.forEach(sql -> names.add(sql.getString("current_name")));
        return names;
    }

    /**
     * Gets a given DnDCraft Name from the DB
     * @param accountID the account you are querying for
     * @return the name string associated with the account
     * @Apinote This only applies
     * */
    @SneakyThrows
    public static String getDnDCraftName(int accountID){
        String sqlCommand = "SELECT * FROM account_name WHERE account_id = "+accountID+" AND WHERE current_name = TRUE;";
        var SQL_RESULT = Atlas.getSQLHandler().database().getResults(sqlCommand);
        if(SQL_RESULT.size() > 1) throw new StorageException("getDnDCraftName", SQL_RESULT.size());
        final String name = SQL_RESULT.get(0).getString("dndcraft_name");
        return name;
    }

    /**
     * Gets an account from the DB
     * @param minecraftID The minecraft UUID the player was assigned by mojang
     * @apiNote This should only be done once on login otherwise never EVER called
     * @return a new instance of the desired stored account
     * */
    @SneakyThrows
    public static Account getAccount(UUID minecraftID){
        String sqlCommand = "SELECT * FROM accounts WHERE minecraft_id = '"+minecraftID.toString()+"'";
        var SQL_RESULT = Atlas.getSQLHandler().database().getResults(sqlCommand);
        var row = SQL_RESULT.get(0);
        int accountNumber = row.getInt("account_id");
        String sqlCommand2 = "SELECT * FROM account_name WHERE account_id ="+accountNumber+" AND WHERE current_name = true;"; //Cant use a join bc this table stores multiple names under 1 account id also head hurt and cant b bothered
        var SQL_RESULT2 = Atlas.getSQLHandler().database().getResults(sqlCommand2);
        UUID minecraft_id = UUID.fromString(row.getString("minecraft_id"));
        UUID forum_id = UUID.randomUUID(); //todo REPLACE THIS WITH ACTUAL CODE FOR THE FORUM TAG
        UUID discord_id = UUID.randomUUID(); //todo REPLACE THIS WITH ACTUAL CODE FOR THE DISCORD TAG
        String name = SQL_RESULT2.get(0).getString("dndcraft_name");
        Account account = new Account(accountNumber, minecraft_id, forum_id, discord_id, name);
        return account;
    }
}
