package com.dndcraft.craftchat.command;

import com.dndcraft.craftcodex.api.account.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Used to make sure autocomplete works
 * */
@Getter
@RequiredArgsConstructor
public class AccountKey {
    private final Account DnDCraftAccount;
}
