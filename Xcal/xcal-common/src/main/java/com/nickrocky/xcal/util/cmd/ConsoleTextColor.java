package com.nickrocky.xcal.util.cmd;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ConsoleTextColor {
    BLACK("30", "0"),
    RED("31", "160"),
    GREEN("32", "46"),
    YELLOW("33", "190"),
    BLUE("34", "21"),
    MAGENTA("35", "165"),
    CYAN("36", "37"),
    WHITE("37", "231"),
    GRAY("-1", "240"),
    ORANGE("-1", "202"),
    DANDELION("-1", "3"),
    PURPLE("-1", "129"),
    CHERRY("-1", "196"),
    LIGHT_GRAY("-1", "247"),

    ;

    private final String code_8;
    private final String code_256;
    private static final String FOREGROUND_COLOR = "38;";
    //private final String code_true;

    public String getCode(CMDColorType type){
        switch (type){
            case CMD_COLOR_256 -> {return this.getCode256();}
            default -> {return this.getCode8();} //Most consoles have at least the 8 text colors
        }
    }

    String getCode8(){
        return this.code_8;
    }

    String getCode256(){
        return this.code_256;
    }


}
