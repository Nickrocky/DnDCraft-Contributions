package com.dndcraft.craftcodex.storage;

public class StorageException extends Exception{
    public StorageException(String methodName, int sizeReturned){
        super("The method " + methodName + " returned " + sizeReturned + " results in the SQL query, that is not good.");
    }
}
