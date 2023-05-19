package com.dndcraft.craftcodex.api.account;

import com.dndcraft.atlas.util.objects.AtlasLocation;
import com.dndcraft.craftcodex.api.util.ICCSerializable;

import java.time.LocalDateTime;

public interface Session {

    /**
     * Gets the logout time of the player in milliseconds
     * @return millis
     * */
    long getLogoutTime();

    /**
     * Gets the login time of the player in milliseconds
     * @return millis
     * */
    long getLoginTime();

    /**
     * Gets the login time of the player in terms of localdatetime object
     * @return a new localdatetime object with the login time
     * */
    LocalDateTime getLoginDateTime();

    /**
     * Gets the time a given player logs out
     * @return a new localdatetime object with the logout time
     * */
    LocalDateTime getLogoutDateTime();

    /**
     * Gets the login location of the player
     * @return location player logged in
     * */
    AtlasLocation getLoginLocation();

    /**
     * Gets the logout location of the player
     * @return the location the player logged out
     * */
    AtlasLocation getLogoutLocation();

    /**
     * Gets the time the player has played in this session in minutes
     * @return minutes played during this session
     * */
    int getTimePlayed();

}
