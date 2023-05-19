package com.nickrocky.xcal.util.cmd;

public class CMDMessageBuilder {

    private String message;
    private String base;
    private String prefix;
    private CMDColorType type;

    public CMDMessageBuilder(){
        this("", "");
    }

    public CMDMessageBuilder(String msg){
        this(msg, "");
    }

    public CMDMessageBuilder(String msg, String prefix){
        this(msg, prefix, CMDColorType.CMD_COLOR_8);
    }

    public CMDMessageBuilder(String msg, String prefix, CMDColorType type){
        base = "\u001B["; //This is just the escape character and a bracket as that is how ANSI sequences start
        message = msg;
        this.prefix = prefix;
        this.type = type;
    }

    public CMDMessageBuilder content(String msg){
        message += msg;
        return getHandle();
    }

    public CMDMessageBuilder color(ConsoleTextColor color){
        base+=color.getCode(type);
        return getHandle();
    }

    public CMDMessageBuilder backgroundColor(ConsoleTextBackgroundColor color){
        base+=color.getCode(type);
        return getHandle();
    }

    public CMDMessageBuilder format(ConsoleTextFormatting... formatting){
        for(ConsoleTextFormatting format : formatting){
            base+=format.getCode();
        }
        return getHandle();
    }

    public CMDMessageBuilder bold(){
        base+=ConsoleTextFormatting.BOLD.getCode();
        return getHandle();
    }

    public CMDMessageBuilder reset(){
        base+=ConsoleTextFormatting.RESET.getCode();
        return getHandle();
    }

    public CMDMessageBuilder appendBracketed(ConsoleTextColor bracketColor, ConsoleTextColor insideColor, String insideString){
        return bold().color(bracketColor).content("[").reset().color(insideColor).content(insideString).reset().bold().color(bracketColor).content("]").reset();
    }

    public CMDMessageBuilder appendBracketed(ConsoleTextBackgroundColor backgroundColor, ConsoleTextColor bracketColor, ConsoleTextColor insideColor, String insideString){
        return bold()
                .color(bracketColor)
                .backgroundColor(backgroundColor)
                .content("[")
                .reset()
                .backgroundColor(backgroundColor)
                .color(insideColor)
                .content(insideString)
                .reset()
                .backgroundColor(backgroundColor)
                .bold()
                .color(bracketColor)
                .content("]")
                .reset();
    }

    private CMDMessageBuilder getHandle(){
        return this;
    }

    public String build(){
        base+="m"; //all ANSI sequences end with 'm'
        return base + prefix + message;
    }

}
