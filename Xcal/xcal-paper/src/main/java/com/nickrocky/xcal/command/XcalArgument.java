package com.nickrocky.xcal.command;

import com.nickrocky.xcal.util.XcalColor;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.CustomArgument;
import dev.jorel.commandapi.arguments.StringArgument;

import java.util.Arrays;

public class XcalArgument {

    public static Argument<XcalColor> colorArgument(String nodeName){
        return new CustomArgument<XcalColor, String>(new StringArgument(nodeName), info ->
        {
            XcalColor color = null;
            try {
                color = XcalColor.valueOf(info.input());
            } catch (IllegalArgumentException e) {
                //ignored
            }

            if (color == null) {
                throw new CustomArgument.CustomArgumentException("Unknown Color");
            } else {
                return color;
            }

        }).replaceSuggestions(ArgumentSuggestions.strings(info ->
                Arrays.stream(XcalColor.values()).map(XcalColor::name).toArray(String[]::new)));
    }

}
