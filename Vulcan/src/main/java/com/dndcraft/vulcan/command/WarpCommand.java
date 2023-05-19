package com.dndcraft.vulcan.command;

import com.dndcraft.atlas.command.annotations.Arg;
import com.dndcraft.atlas.command.annotations.Cmd;
import com.dndcraft.atlas.command.annotations.Default;
import com.dndcraft.atlas.command.annotations.Range;
import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import com.dndcraft.atlas.util.Pages;
import com.dndcraft.vulcan.Vulcan;
import com.dndcraft.vulcan.managers.warp.WarpManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class WarpCommand extends BaseCommand {

    public void invoke(Player p, WarpManager.Warp warp){
        validate(p.hasPermission("vulcan.warp."+warp.getName()),"You seem to be lacking permission to access this warp");
        p.teleportAsync(warp.toLocation());
        var sender = new BukkitComponentBuilder();
        sender.append("Warped to ", AtlasColor.BLUE).append(warp.getName());
        p.sendMessage(sender.build());
    }

    @Cmd(value = "Create a warp",permission = "vulcan.command.warp.elevated")
    public void create(Player p, @Arg(value = "Warp Name") String name){
        validate(!Vulcan.get().getWarpManager().doesWarpExist(name),"Warp already exist!");
        Vulcan.get().getWarpManager().addWarp(name,p.getLocation());
        var sender = new BukkitComponentBuilder();
        sender.append("Warp ", AtlasColor.GREEN).append(name).append(" created!",AtlasColor.GREEN);
        p.sendMessage(sender.build());
    }
    @Cmd(value = "Delete a warp",permission = "vulcan.command.warp.elevated")
    public void delete(Player p, @Arg(value = "Warp Name") WarpManager.Warp warp){
        validate(p.hasPermission("vulcan.warp."+warp.getName()),"You seem to be lacking permission to delete this warp");
        Vulcan.get().getWarpManager().removeWarp(warp.getName());
        var sender = new BukkitComponentBuilder();
        sender.append("Warp ", AtlasColor.GREEN).append(warp.getName()).append(" deleted!",AtlasColor.GREEN);
        p.sendMessage(sender.build());
    }

    @Cmd(value = "list",permission = "vulcan.command.warp")
    public void list(Player p,@Default("1") @Arg("page") @Range(min=1) int page){
        validate(Vulcan.get().getWarpManager().getWarps().size()!=0,
                "There are no warps available!"+(p.hasPermission("vulcan.command.warp.elevated")?" Use /warp create <name>":""));
        var player_warps = Vulcan.get().getWarpManager().getWarpsFromPlayer(p);
        Set<String> warps = new HashSet<>();
        int pages = (int) Math.ceil(player_warps.size()/40d);
        validate(pages!=0,"There are no current warps available for you!");
        player_warps.forEach(w->{if (!warps.contains(w.getName())) warps.add(w.getName());});
        Component res = new Pages(warps,"Warps")
                .setClickableReturnType(Pages.ClickableReturnType.SUGGEST_COMMAND)
                .setCommandListing("warp list")
                .setCommandRunner("warp")
                .build(page);
        p.sendMessage(res);
    }
}
