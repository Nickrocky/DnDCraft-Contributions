package com.dndcraft.craftcodex.manager;

import com.dndcraft.craftcodex.account.Account;
import com.dndcraft.craftcodex.storage.CraftStorage;

import java.util.*;

public class AccountNameManager {

    /**
     * Gets a new unique DnDCraft Name
     * @apiNote DO NOT STORE THIS, IT IS ONLY CHECKED ONCE WHEN ITS MADE
     * @return returns a new unique DnDCraft Name
     * */
    public String generateDnDCraftName(){
        String name = generateName();
        if(CraftStorage.existsDnDCraftName(name)){
            return generateDnDCraftName(); //Keep looking for a new name
        }
        return name;
    }

    public void updateDnDCraftName(Account account){
        String name = generateDnDCraftName();
        account.setDndcraftName(name);
        CraftStorage.updateDnDCraftName(name, account.getAccountID());
    }

    private String generateName(){
        Random random = new Random();
        String randomAdj = adj.get(random.nextInt(adj.size()));
        String randomNoun = noun.get(random.nextInt(noun.size()));
        int randomNumber = random.nextInt(100);
        return ""+randomAdj+randomNoun+randomNumber;
    }

    private static final List<String> noun = Arrays.asList(
            "actor", "animal", "apple", "ballon", "banana",
            "breakfast", "candle", "caravan", "crayon",
            "diamond", "elephant", "eye", "fish", "forest",
            "ghost", "orange", "notebook", "night", "monkey",
            "machine", "lizard", "lion", "jelly", "jackal",
            "horse", "guitar", "parrot", "pencil", "rain",
            "rocket", "rose", "vulture", "whale", "axolotl",
            "yak", "zebra");

    private static final List<String> adj = Arrays.asList(
            "abundant", "adorable", "adventurous", "aloof",
            "ambitious", "attractive", "auspicious", "beautiful",
            "beneficial", "calm", "capable", "careful", "charming",
            "cheerful", "cute", "dauntless", "delicate", "dark",
            "excellent", "fabulous","famous", "fancy", "funny",
            "magical", "polite", "proud", "rare", "rich", "royal",
            "zealous"
    );

}
