package com.dndcraft.craftcodex.party;

import java.util.Arrays;
import java.util.List;

public class PartyRules {

    //Loot Rules - This only applies to hunts and large scale bosses
    private LootingType lootingRule;

    //Party Intent - These are attributes for a party
    private List<PartyIntentType> partyIntents;

    public PartyRules(){
        lootingRule = LootingType.ROLLING;
        partyIntents = Arrays.asList(PartyIntentType.UNSET);
    }

}
