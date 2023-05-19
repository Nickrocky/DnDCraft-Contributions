package com.dndcraft.craftcodex.account;

public class TimeNotSetException extends Exception{

    public TimeNotSetException(){
        super("The given player has not ended their session yet! Please wait for them to log out before calling for the logout time!");
    }
}
