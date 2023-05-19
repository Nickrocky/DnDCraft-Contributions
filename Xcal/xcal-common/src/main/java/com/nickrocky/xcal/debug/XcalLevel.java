package com.nickrocky.xcal.debug;

import java.util.logging.Level;

/**
 * This class is entirely because Java Level#new(String, int) is protected
 * */
public class XcalLevel extends Level {


    //This new level is here to allow for us to see our problems that occur in Xcal
    //Without listening to the various other verbose java output
    public static final Level DEBUG = new XcalLevel("Debug", 600);

    /**
     * Create a named Level with a given integer value.
     * <p>
     * Note that this constructor is "protected" to allow subclassing.
     * In general clients of logging should use one of the constant Level
     * objects such as SEVERE or FINEST.  However, if clients need to
     * add new logging levels, they may subclass Level and define new
     * constants.
     *
     * @param name  the name of the Level, for example "SEVERE".
     * @param value an integer value for the level.
     * @throws NullPointerException if the name is null
     */
    protected XcalLevel(String name, int value) {
        super(name, value);
    }
}
