package com.dndcraft.craftchat.chat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendRequest {
    private final int accountIDOfSender;
    private final int accountIDOfRecipient;
    private final String context;
}
