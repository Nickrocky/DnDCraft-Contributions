package com.dndcraft.craftcodex.account;

import com.dndcraft.atlas.util.objects.AtlasLocation;
import lombok.SneakyThrows;
import org.apache.commons.lang.NotImplementedException;
import org.bson.Document;
import org.json.simple.JSONObject;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Author: Nickrocky213 Date: 5/26/2022
 * This is the main implementation of the Session Class if you are looking for the exposed interface look under the api package
 * */
public class Session implements com.dndcraft.craftcodex.api.account.Session {

    private int account_id; //SQL
    private long login, logout;
    private AtlasLocation loginLocation, logoutLocation;
    private int timePlayed;

    public Session(int account_id, AtlasLocation loginLocation){
        this.account_id = account_id;
        this.loginLocation = loginLocation;
        this.logoutLocation = null; //Unset
        this.login = System.currentTimeMillis();
        timePlayed = 0;
        logout = -1; //Unset
    }

    /**
     * Gets the logout time of the player in milliseconds
     *
     * @return Millis
     * */
    @Override
    public long getLogoutTime() {
        if(logout == -1) throw new NullPointerException();
        return logout;
    }

    /**
     * Gets the login time of the player in milliseconds
     *
     * @return millis
     */
    @Override
    public long getLoginTime() {
        return login;
    }

    /**
     * Gets the login time of the player in terms of localdatetime object
     *
     * @return a new localdatetime object with the login time
     */
    @Override
    public LocalDateTime getLoginDateTime() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(login), ZoneId.systemDefault());
    }

    @SneakyThrows
    @Override
    public LocalDateTime getLogoutDateTime() {
        if(logout == -1) throw new TimeNotSetException();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(logout), ZoneId.systemDefault());
    }

    /**
     * Gets the login location of the player
     *
     * @return location player logged in
     */
    @Override
    public AtlasLocation getLoginLocation() {
        return loginLocation;
    }

    @SneakyThrows
    @Override
    public AtlasLocation getLogoutLocation() {
        if(logoutLocation == null) throw new LocationNotSetException();
        return logoutLocation;
    }

    public void setLogoutLocation(AtlasLocation logoutLocation){
        this.logoutLocation = logoutLocation;
    }

    public void setLogoutTime(){
        logout = System.currentTimeMillis();
        Duration duration = Duration.between(getLoginDateTime(), getLogoutDateTime());
        timePlayed = (int) (duration.getSeconds()/60);
    }

    /**
     * Gets the time the player has played in this session in minutes
     *
     * @return minutes played during this session
     */
    @Override
    public int getTimePlayed() {
        return timePlayed;
    }
}
