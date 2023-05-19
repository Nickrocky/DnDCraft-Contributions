package com.dndcraft.craftcodex.account;

public class LocationNotSetException extends Exception{

    public LocationNotSetException(){
        super("The given player has not ended their session yet! Please wait for them to log out before calling for the logout location!");
    }
}
