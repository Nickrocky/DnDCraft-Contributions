package com.dndcraft.craftchat.chat;

import com.dndcraft.craftcodex.api.account.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class Influence {

    private final Account account;
    private final int renown;
    //private final int crown;
    private final LocalDate dateGranted;

}
