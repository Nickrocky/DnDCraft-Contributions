package com.dndcraft.craftchat.chat;

import com.dndcraft.atlas.util.AtlasColor;
import com.dndcraft.atlas.util.BukkitComponentBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;

@Getter
@RequiredArgsConstructor
public enum Channels {

    GLOBAL_OOC('ꌂ', true, -1, "Global Out of Character"),
    GLOBAL_EVENT_BROADCAST('ꌍ', false, -1, "Global Event Broadcast"),
    GLOBAL_BROADCAST('ꌆ', false, -1, "Global Broadcast"),
    YELL('ꌏ', false, 48, "Yell"),
    SPEAK('ꌎ', false, 18, "Speak"),
    QUIET('ꌌ', false, 12, "Quiet"),
    WHISPER('ꌋ', false, 2, "Whisper"),
    DEVELOPER_CHAT('ꐇ', false, -1, "Developer"),
    RULESMITH_CHAT('ꌈ', false, -1, "Moderation"),
    LORESMITH_CHAT('ꌇ', false, -1, "Lore"),
    BUILDER_CHAT('ꌅ', false, -1, "Builder"),
    HELP_CHAT('ꌉ', false, -1, "Help"),
    ;

    final char channelIdentifier;
    final boolean optIn;
    final int range;
    final String name;

    public Component getChannelComponent(){
        var channelComponent = new BukkitComponentBuilder();
        return channelComponent.append(channelIdentifier).build();
    }

    public Component getIntroductionComponent(){
        return new BukkitComponentBuilder().append("You have switched to ", AtlasColor.GRAY).append(getChannelIdentifier()).append(" chat!", AtlasColor.GRAY).build();
    }

    public Component getJoinComponent(){
        return new BukkitComponentBuilder().append("You have joined ", AtlasColor.GRAY).append(channelIdentifier).append(" chat!").build();
    }

    public Component getMutedComponent(){
        return new BukkitComponentBuilder().append("You have been muted in ", AtlasColor.GRAY).append(channelIdentifier).append(" chat and will be unable to speak until a moderator lifts your mute.", AtlasColor.GRAY).build();
    }

    public Component getLeaveComponent(){
        return new BukkitComponentBuilder().append("You have left ", AtlasColor.GRAY).append(channelIdentifier).append(" chat!", AtlasColor.GRAY).build();
    }
}
