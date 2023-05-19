package com.dndcraft.prometheus.exception;

public class StringLengthException extends Exception{

    public StringLengthException(String text){
        super("The items name (" +text+") is too long for this operation it is " +text.length() + " characters long. (Max 25)");
    }

}
