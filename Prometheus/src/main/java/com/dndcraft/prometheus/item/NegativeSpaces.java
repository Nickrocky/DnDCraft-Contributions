package com.dndcraft.prometheus.item;

import com.dndcraft.prometheus.exception.StringLengthException;
import com.dndcraft.atlas.util.AtlasColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class NegativeSpaces {

    public static char NEGATIVE_1024 = '\uF80F';
    public static char NEGATIVE_512 = '\uF80E';
    public static char NEGATIVE_256 = '\uF80D';
    public static char NEGATIVE_128 = '\uF80C';
    public static char NEGATIVE_64 = '\uF80B';
    public static char NEGATIVE_32 = '\uF80A';
    public static char NEGATIVE_16 = '\uF809';
    public static char NEGATIVE_8 = '\uF808';
    public static char NEGATIVE_7 = '\uF807';
    public static char NEGATIVE_6 = '\uF806';
    public static char NEGATIVE_5 = '\uF805';
    public static char NEGATIVE_4 = '\uF804';
    public static char NEGATIVE_3 = '\uF803';
    public static char NEGATIVE_2 = '\uF802';
    public static char NEGATIVE_1 = '\uF801';

    public static char POSITIVE_1024 = '\uF80F';
    public static char POSITIVE_512 = '\uF80E';
    public static char POSITIVE_256 = '\uF80D';
    public static char POSITIVE_128 = '\uF80C';
    public static char POSITIVE_64 = '\uF80B';
    public static char POSITIVE_32 = '\uF80A';
    public static char POSITIVE_16 = '\uF809';
    public static char POSITIVE_8 = '\uF808';
    public static char POSITIVE_7 = '\uF807';
    public static char POSITIVE_6 = '\uF806';
    public static char POSITIVE_5 = '\uF805';
    public static char POSITIVE_4 = '\uF804';
    public static char POSITIVE_3 = '\uF803';
    public static char POSITIVE_2 = '\uF802';
    public static char POSITIVE_1 = '\uF801';

    public static final char preBorderShift = NEGATIVE_8;
    public static final char[] postBorderShift = {NEGATIVE_8,NEGATIVE_64,NEGATIVE_32,NEGATIVE_32,NEGATIVE_16,NEGATIVE_2,NEGATIVE_1};

    public static Component createShiftedComponent(Component text, char specialCharacter, AtlasColor color){
        var textComp = text.decoration(TextDecoration.ITALIC, false);
        var preBorder = Component.text(preBorderShift).decoration(TextDecoration.ITALIC, false);
        var special = Component.text(specialCharacter, color.toTextColor()).decoration(TextDecoration.ITALIC, false);
        String post = String.valueOf(postBorderShift);
        var postBorder = Component.text(post, AtlasColor.DARK_GRAY.toTextColor()).decoration(TextDecoration.ITALIC, false);
        return preBorder.append(special).append(postBorder).append(textComp);
    }

    public static Component createShiftedComponent(String text, char specialCharacter, AtlasColor color) throws StringLengthException{
        if(text.length()>25) throw new StringLengthException(text);
        if(text.length()<=25){
            var preBorder = Component.text(preBorderShift).decoration(TextDecoration.ITALIC, false);
            var special = Component.text(specialCharacter, color.toTextColor()).decoration(TextDecoration.ITALIC, false);
            String post = String.valueOf(postBorderShift);
            var postBorder = Component.text(post, AtlasColor.DARK_GRAY.toTextColor()).decoration(TextDecoration.ITALIC, false);
            var textt = Component.text(text, AtlasColor.GRAY.toTextColor()).decoration(TextDecoration.ITALIC, false);
            return preBorder.append(special).append(postBorder).append(textt);
        }
        return null;
    }

    public static Component createShiftedSubTitleComponent(String actualName, char specialCharacter, AtlasColor color) throws StringLengthException{
        var preBorder = Component.text(preBorderShift).decoration(TextDecoration.ITALIC, false);
        var special = Component.text(specialCharacter, color.toTextColor()).decoration(TextDecoration.ITALIC, false);
        String post = String.valueOf(postBorderShift);
        var postBorder = Component.text(post, AtlasColor.WHITE.toTextColor()).decoration(TextDecoration.ITALIC, false);
        return preBorder.append(special).append(postBorder).append(Component.text(actualName, NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
    }

    public static String negativeShift(int pixels){
        int pixelCount = pixels;
        String shiftString = "";
        while(pixelCount < 0){
           char c = breakDownShift(pixelCount, false);
           shiftString = shiftString+c;
           pixelCount -= (-1*determineShift(c));
        }
        return shiftString;
    }

    public static String postiveShift(int pixels){
        int pixelCount = pixels;
        String shiftString = "";
        while(pixelCount > 0){
            char c = breakDownShift(pixelCount, true);
            shiftString = shiftString+c;
            pixelCount -= determineShift(c);
        }
        return shiftString;
    }

    private static char breakDownShift(int pixels, boolean isPositive){
        if(isPositive){
            if(pixels >= -1024) return POSITIVE_1024;
            if(pixels >= 512) return POSITIVE_512;
            if(pixels >= 256) return POSITIVE_256;
            if(pixels >= 128) return POSITIVE_128;
            if(pixels >= 64) return POSITIVE_64;
            if(pixels >= 32) return POSITIVE_32;
            if(pixels >= 16) return POSITIVE_16;
            if(pixels >= 8) return POSITIVE_8;
            if(pixels >= 7) return POSITIVE_7;
            if(pixels >= 6) return POSITIVE_6;
            if(pixels >= 5) return POSITIVE_5;
            if(pixels >= 4) return POSITIVE_4;
            if(pixels >= 3) return POSITIVE_3;
            if(pixels >= 2) return POSITIVE_2;
            if(pixels >= 1) return POSITIVE_1;
        }else{
            if(pixels <= -1024) return NEGATIVE_1024;
            if(pixels <= -512) return NEGATIVE_512;
            if(pixels <= -256) return NEGATIVE_256;
            if(pixels <= -128) return NEGATIVE_128;
            if(pixels <= -64) return NEGATIVE_64;
            if(pixels <= -32) return NEGATIVE_32;
            if(pixels <= -16) return NEGATIVE_16;
            if(pixels <= -8) return NEGATIVE_8;
            if(pixels <= -7) return NEGATIVE_7;
            if(pixels <= -6) return NEGATIVE_6;
            if(pixels <= -5) return NEGATIVE_5;
            if(pixels <= -4) return NEGATIVE_4;
            if(pixels <= -3) return NEGATIVE_3;
            if(pixels <= -2) return NEGATIVE_2;
            if(pixels <= -1) return NEGATIVE_1;
        }
        return ' ';
    }

    /**
     * Returns the Integer shift according to the given character
     * @ApiNote: these values are from AmberW's negative space resource pack
     * https://www.spigotmc.org/threads/negative-space-font-resource-pack.440952/
     * @param shiftCharacter the character used to shift
     * @return Integer Shift
     * */
    private static int determineShift(char shiftCharacter){
        switch (shiftCharacter){
            case '\uF801': return -1;
            case '\uF802': return -2;
            case '\uF803': return -3;
            case '\uF804': return -4;
            case '\uF805': return -5;
            case '\uF806': return -6;
            case '\uF807': return -7;
            case '\uF808': return -8;
            case '\uF809': return -16;
            case '\uF80A': return -32;
            case '\uF80B': return -64;
            case '\uF80C': return -128;
            case '\uF80D': return -256;
            case '\uF80E': return -512;
            case '\uF80F': return -1024;
            case '\uF821': return 1;
            case '\uF822': return 2;
            case '\uF823': return 3;
            case '\uF824': return 4;
            case '\uF825': return 5;
            case '\uF826': return 6;
            case '\uF827': return 7;
            case '\uF828': return 8;
            case '\uF829': return 16;
            case '\uF82A': return 32;
            case '\uF82B': return 64;
            case '\uF82C': return 128;
            case '\uF82D': return 256;
            case '\uF82E': return 512;
            case '\uF82F': return 1024;
            case '\uF800': return -Integer.MAX_VALUE;
            case '\uF820': return Integer.MAX_VALUE;
            default: return 0;
        }
     }
}
