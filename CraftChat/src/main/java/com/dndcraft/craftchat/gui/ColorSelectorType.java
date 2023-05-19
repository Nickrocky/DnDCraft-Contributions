package com.dndcraft.craftchat.gui;

import lombok.Getter;

@Getter
public enum ColorSelectorType {
    NAME("Chat Name"),
    MSG("Message");

    private String typeString;

    ColorSelectorType(String typeString){
        this.typeString = typeString;
    }
}
