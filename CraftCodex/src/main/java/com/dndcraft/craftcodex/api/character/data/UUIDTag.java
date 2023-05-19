package com.dndcraft.craftcodex.api.character.data;

import com.dndcraft.atlas.AtlasPaper;
import com.dndcraft.craftcodex.api.character.capability.ICharacterTag;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.NamespacedKey;

import java.util.UUID;

@Getter
public class UUIDTag implements ICharacterTag {

    @Getter private UUID uuid;
    private NamespacedKey namespacedKey;

    public UUIDTag(){} //For reading from document

    public UUIDTag(NamespacedKey namespacedKey, UUID uuid){
        this.uuid = uuid;
        this.namespacedKey = namespacedKey;
    }

    public UUIDTag(String key, UUID uuid){
        this(new NamespacedKey(AtlasPaper.get(), key), uuid);
    }

    @Override
    public Document toDocument() {
        return new Document()
                .append("_id", namespacedKey.toString())
                .append("uuid", uuid+"") //I prefer the string storage of UUID's rather than MongoDB's default way of handling them
            .append("type",getTagType());
    }

    @Override
    public void fromDocument(Document document) {
        uuid = UUID.fromString(document.getString("uuid"));
        namespacedKey = NamespacedKey.fromString(document.getString("_id"));
    }

    @Override
    public String getTagType() {
        return CraftTags.UUID.name();
    }
}
