package com.nickrocky.xcal.debug;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.logging.Level;

/**
 * This enum is here to allow for easy case switching in XcalLogger
 * */

@Getter
@RequiredArgsConstructor
public enum LogLevel {

    OFF(Level.OFF),
    SEVERE(Level.SEVERE),
    WARNING(Level.WARNING),
    INFO(Level.INFO),
    DEBUG(XcalLevel.DEBUG),
    CONFIG(Level.CONFIG),
    FINE(Level.FINE),
    FINER(Level.FINER),
    FINEST(Level.FINEST),
    ALL(Level.ALL),
    ;

    private final Level level;
}
