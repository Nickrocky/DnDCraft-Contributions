package com.dndcraft.vulcan.command;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.MinecraftKey;
import com.dndcraft.atlas.command.annotations.Cmd;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;

public class TestCommand extends BaseCommand{

    public void invoke(){
        getSender().sendMessage("Nickrocky's autism command to test random shit he thinks of");
    }

    @SneakyThrows
    @Cmd(value = "Force open advancements", permission = "vulcan.admin")
    public void adv(Player player){
        PacketContainer advancementPacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SELECT_ADVANCEMENT_TAB);
        advancementPacket.getMinecraftKeys().write(0, new MinecraftKey("dndcraft", "lolfuck"));
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, advancementPacket);
    }

}
