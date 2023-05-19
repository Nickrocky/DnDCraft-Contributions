package com.nickrocky.xcal.util.cmd;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConsoleTextFormatting {

    RESET("0"),
    BOLD("1"),
    UNDERLINE("4"),
    STRIKETHROUGH("9"),
    DOUBLE_UNDERLINE("21"),

    ;

    private final String code;

}
