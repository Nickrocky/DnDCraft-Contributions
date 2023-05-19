package com.nickrocky.xcal.debug;

import com.nickrocky.xcal.util.cmd.CMDColorType;
import com.nickrocky.xcal.util.cmd.CMDMessageBuilder;
import com.nickrocky.xcal.util.cmd.ConsoleTextColor;
import com.nickrocky.xcal.util.cmd.ConsoleTextFormatting;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The default level of logging for Java Logger is INFO, since DEBUG is lower than INFO
 * so, you wont see debug statements unless we lower the logging level
 * */
public class XcalLogger {

    private Logger internalLogger;
    private boolean isDebugEnabled = false;
    private CMDColorType type;
    private boolean isTrueColorEnabled = false; //todo support trueColor properly
    private static String XCAL_PREFIX = new CMDMessageBuilder().appendBracketed(ConsoleTextColor.LIGHT_GRAY, ConsoleTextColor.DANDELION, "Xcal").build();
    private static String XCAL_DEBUG_PREFIX = new CMDMessageBuilder().appendBracketed(ConsoleTextColor.LIGHT_GRAY, ConsoleTextColor.PURPLE, "Xcal-Debug").build();
    private static String XCAL_ERROR_PREFIX = new CMDMessageBuilder().appendBracketed(ConsoleTextColor.LIGHT_GRAY, ConsoleTextColor.CHERRY, "Xcal-Error").build();

    public XcalLogger(Logger internalLogger, CMDColorType type){
        this.internalLogger = internalLogger;
        this.type = type;
    }

    public void log(String msg){
        internalLogger.log(Level.INFO, msg);
    }

    public void log(String msg, LogLevel level){
        String modifiedMessage = new CMDMessageBuilder().color(ConsoleTextColor.DANDELION).content(msg).build();
        internalLogger.log(level.getLevel(), XCAL_PREFIX + " " + modifiedMessage);
    }

    public void debug(String msg){
        String modifiedMessage = new CMDMessageBuilder().color(ConsoleTextColor.PURPLE).content(msg).build();
        log(XCAL_DEBUG_PREFIX + " " + modifiedMessage, LogLevel.DEBUG);
    }

    public void error(String msg){
        String modifiedMessage = new CMDMessageBuilder().color(ConsoleTextColor.CHERRY).content(msg).build();
        internalLogger.severe(XCAL_ERROR_PREFIX + " " + modifiedMessage);
    }

    public void toggleDebugging(){
        if(isDebugEnabled){
            internalLogger.setLevel(LogLevel.DEBUG.getLevel());
            log("Logging has been enabled!");
        }else{
            internalLogger.setLevel(LogLevel.INFO.getLevel());
            log("Logging has been disabled!");
        }
    }

}
