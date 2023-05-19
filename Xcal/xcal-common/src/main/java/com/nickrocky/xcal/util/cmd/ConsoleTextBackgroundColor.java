package com.nickrocky.xcal.util.cmd;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConsoleTextBackgroundColor {

    BLACK("40","0"),
    RED("41", "160"),
    GREEN("42", "46"),
    YELLOW("43", "190"),
    BLUE("44", "21"),
    MAGENTA("45", "165"),
    CYAN("46", "36"),
    WHITE("47", "37"),
    GRAY("-1", "240"),
    LIGHT_GRAY("-1", "247"),
    ;

    private final String code_8;
    private final String code_256;
    private static final String BACKGROUND_SEQUENCE = "48;";
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
