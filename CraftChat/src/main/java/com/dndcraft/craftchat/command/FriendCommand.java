package com.dndcraft.craftchat.command;

import com.dndcraft.atlas.command.Commands;
import com.dndcraft.atlas.command.annotations.Arg;
import com.dndcraft.atlas.command.annotations.Cmd;
import com.dndcraft.atlas.command.annotations.Default;
import com.dndcraft.craftchat.CraftChat;
import com.dndcraft.craftchat.chat.ChatProfile;
import com.dndcraft.craftchat.gui.FriendMenu;
import com.dndcraft.craftchat.gui.FriendRequestMenu;
import com.dndcraft.craftchat.util.FriendRequestUtil;
import com.dndcraft.craftcodex.api.CraftCodexAPI;
import com.dndcraft.craftcodex.api.account.Account;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FriendCommand extends BaseCommand {

    public FriendCommand(){
        Commands.defineArgumentType(AccountKey.class)
                .completer((sender, input) -> getAccountKeyPrompt(input))
                .defaultError("Invalid Account!")
                .defaultName("account")
                .mapper((input) -> CraftCodexAPI.getApi().getAccountManager().getAccount(input) != null ? new AccountKey(CraftCodexAPI.getApi().getAccountManager().getAccount(input)) : null)
                .register();
    }

    private Collection<String> getAccountKeyPrompt(String input){
        final String lowercaseInput = input.toLowerCase();
        var accounts  = CraftCodexAPI.getApi().getAccountManager().getOnlinePlayerAccounts().stream().filter(s -> s.getDNDCraftName().toLowerCase().startsWith(lowercaseInput)).collect(Collectors.toList());
        List<String> accountNamesForAutoComplete = new ArrayList<>();
        for(Account account : accounts){
            accountNamesForAutoComplete.add(account.getDNDCraftName());
        }
        return accountNamesForAutoComplete;
    }

    public void invoke(){

    }

    @Cmd(value = "Instance check", permission = "craftchat.member")
    public void instance(Player player){
        player.sendMessage(Component.text("Instance: " + (CraftCodexAPI.getApi() != null ? "TRUE" : "FALSE")));
    }

    @Cmd(value = "List friends",permission = "craftchat.friend.list")
    public void list(Player player){
        Account dndAccount = CraftCodexAPI.getApi().getAccountManager().getAccount(player.getUniqueId());
        ChatProfile profile = CraftChat.getChatProfileManager().getChatProfileByAccountID(dndAccount.getAccountID());
        FriendMenu menu = new FriendMenu();
        menu.openMenu(player, profile);
    }

    @Cmd(value = "open your requests", permission = "craftchat.friend.requests", aliases = {"requests", "reqs"})
    public void request(Player player){
        Account dndAccount = CraftCodexAPI.getApi().getAccountManager().getAccount(player.getUniqueId());
        ChatProfile profile = CraftChat.getChatProfileManager().getChatProfileByAccountID(dndAccount.getAccountID());
        FriendRequestMenu menu = new FriendRequestMenu();
        menu.openMenu(player, profile);
    }

    @Cmd(value = "Send a new friend request", permission = "craftchat.friend.add")
    public void add(Player player, AccountKey accountKey, @Arg(value = "context", description = "Any additional information you would like to send")@Default("") String... context){
        Account sender = CraftCodexAPI.getApi().getAccountManager().getAccount(player.getUniqueId());
        Account recipient = accountKey.getDnDCraftAccount();
        StringBuilder builder = new StringBuilder();
        for(String component : context){
            if(builder.length() > 0){
                builder.append(" ").append(component);
            } else {
                builder.append(component);
            }
        }
        FriendRequestUtil.sendRequest(sender, recipient, builder.toString());
    }

    @Cmd(value = "Remove someone from your friends list", permission = "craftchat.friend.remove")
    public void remove(){

    }

    @Cmd(value = "Block a particular player from being your friend")
    public void block(Player player, @Arg(value = "person to block", description = "The person you would like to block") AccountKey personToBlock){
        var playerAccount = CraftCodexAPI.getApi().getAccountManager().getAccount(player.getUniqueId());
        var otherPersonAccount = personToBlock.getDnDCraftAccount();

    }

    public void settings(){

    }

}
