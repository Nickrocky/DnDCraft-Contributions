package com.dndcraft.craftchat.command;

import com.dndcraft.atlas.command.annotations.Arg;
import com.dndcraft.atlas.command.annotations.Cmd;
import com.dndcraft.atlas.command.annotations.Default;
import com.dndcraft.craftchat.CraftChat;
import com.dndcraft.craftchat.chat.Channels;
import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftchat.gui.PreferencesMenu;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import com.dndcraft.craftcodex.api.account.Account;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

public class ChatCommand extends BaseCommand{

    public void invoke(Player player){
        player.sendMessage("CraftChat Version v0.1.1");
    }

    @Cmd(value = "preferences", permission = "craftchat.preferences", aliases = {"pref", "prefs"})
    public void preferences(Player player){
        Account account = CraftCodexAPI.getApi().getAccountManager().getAccount(player.getUniqueId());
        ChatProfile profile = CraftChat.getChatProfileManager().getChatProfileByAccountID(account.getAccountID());
        PreferencesMenu preferencesMenu = new PreferencesMenu();
        preferencesMenu.openMenu(player, profile);
    }

    @Cmd(value = "ooc", permission = "craftchat.chat.ooc")
    public void ooc (Player player, @Arg(value = "content", description = "Anything you want to say") @Default("") String[] content){
        channelPassthrough(Channels.GLOBAL_OOC, player,content);
    }

    @Cmd(value = "dev", permission = "craftchat.chat.dev", aliases = {"dc"})
    public void dev(Player player, @Arg(value = "content", description = "Anything you want to say")@Default("") String[] content){
        channelPassthrough(Channels.DEVELOPER_CHAT, player,content);
    }

    @Cmd(value = "lore", permission = "craftchat.chat.lore", aliases = {"lc"})
    public void lore (Player player, @Arg(value = "content", description = "Anything you want to say") @Default("") String[] content){
        channelPassthrough(Channels.LORESMITH_CHAT, player,content);
    }

    @Cmd(value = "dev", permission = "craftchat.chat.mod", aliases = {"mc"})
    public void mod(Player player, @Arg(value = "content", description = "Anything you want to say")@Default("") String[] content){
        channelPassthrough(Channels.RULESMITH_CHAT, player,content);
    }

    @Cmd(value = "ooc", permission = "craftchat.chat.builder", aliases = {"bc"})
    public void build (Player player, @Arg(value = "content", description = "Anything you want to say") @Default("") String[] content){
        channelPassthrough(Channels.BUILDER_CHAT, player,content);
    }

    @Cmd(value = "dev", permission = "craftchat.chat.global_broadcast", aliases = {"gbc"})
    public void globalbroadcast(Player player, @Arg(value = "content", description = "Anything you want to say")@Default("") String[] content){
        channelPassthrough(Channels.GLOBAL_BROADCAST, player,content);
    }

    @Cmd(value = "ooc", permission = "craftchat.chat.event_broadcast", aliases = {"ebc"})
    public void globaleventbroadcast (Player player, @Arg(value = "content", description = "Anything you want to say") @Default("") String[] content){
        channelPassthrough(Channels.GLOBAL_EVENT_BROADCAST, player,content);
    }

    @Cmd(value = "ooc", permission = "craftchat.chat.help", aliases = {"hc"})
    public void help (Player player, @Arg(value = "content", description = "Anything you want to say") @Default("") String[] content){
        channelPassthrough(Channels.HELP_CHAT, player,content);
    }

    @Cmd(value = "dev", permission = "craftchat.chat.speak", aliases = {"s", "rp"})
    public void speak(Player player, @Arg(value = "content", description = "Anything you want to say")@Default("") String[] content){
        channelPassthrough(Channels.SPEAK, player,content);
    }

    @Cmd(value = "dev", permission = "craftchat.chat.whisper", aliases = {"w"})
    public void whisper(Player player, @Arg(value = "content", description = "Anything you want to say")@Default("") String[] content){
        channelPassthrough(Channels.WHISPER, player,content);
    }

    @Cmd(value = "ooc", permission = "craftchat.chat.yell", aliases = {"y"})
    public void yell (Player player, @Arg(value = "content", description = "Anything you want to say") @Default("") String[] content){
        channelPassthrough(Channels.YELL, player,content);
    }

    @Cmd(value = "dev", permission = "craftchat.chat.quiet", aliases = {"q"})
    public void quiet(Player player, @Arg(value = "content", description = "Anything you want to say")@Default("") String[] content){
        channelPassthrough(Channels.QUIET, player,content);
    }


    private void channelPassthrough(Channels type, Player player, String[] content){
        Account account = CraftCodexAPI.getApi().getAccountManager().getAccount(player.getUniqueId());
        ChatProfile profile = CraftChat.getChatProfileManager().getChatProfileByAccountID(account.getAccountID());
        String msg = StringUtils.join(content, " ");
        if(!msg.isEmpty()){
            switch (type){
                case GLOBAL_EVENT_BROADCAST:
                case GLOBAL_BROADCAST:
                    CraftChat.getChannelManager().routeBroadcast(type, msg);
                    break;
                default:
                    CraftChat.getChannelManager().routeChat(profile.getChannel(), player, msg);
                    break;
            }
        }else{
            player.sendMessage(type.getIntroductionComponent());
            if(!type.equals(Channels.GLOBAL_BROADCAST) && !type.equals(Channels.GLOBAL_EVENT_BROADCAST)){
                profile.setChannel(type);
            }
        }
    }

}
