package com.dndcraft.craftcodex.party;

import com.dndcraft.craftcodex.api.account.Account;

import java.util.List;

/**
 * A party is a small temporary group that is never persistently stored
 * */
public class Party {

    private Account owner;
    private List<Integer> moderator;
    private List<Integer> member;

    private PartyRules partyRules;

    public Party(Account owner, Account firstMember){
        this.owner = owner;
        member.add(firstMember.getAccountID());
    }

    public boolean promote(Account member){
        if(!moderator.contains(member)){
            moderator.add(member.getAccountID());
            return true;
        }
        return false;
    }

    public boolean demote(Account member){
        if(moderator.contains(member.getAccountID())){
            int index = 0;
            for(int i : moderator){
                if(i == member.getAccountID()) break;
                index++;
            }
            moderator.remove(index);
            return true;
        }
        return false;
    }

    public void transferOwnership(Account member){
        owner = member;
    }

    public void disband(){
        owner = null;
        member.clear();
        moderator.clear();
    }

    public void modifyRule(PartyRules partyRules){
        this.partyRules = partyRules;
    }

}
