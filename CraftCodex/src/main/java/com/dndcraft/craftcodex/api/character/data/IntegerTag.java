package com.dndcraft.craftcodex.api.character.data;

import com.dndcraft.craftcodex.api.character.capability.ICharacterTag;
import com.dndcraft.atlas.AtlasPaper;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.NamespacedKey;

public class IntegerTag implements ICharacterTag {

    @Getter private int value;
    private NamespacedKey namespacedKey;

    public IntegerTag(){} //For reading from document

    public IntegerTag(NamespacedKey namespacedKey, int value){
        this.value = value;
        this.namespacedKey = namespacedKey;
    }

    public IntegerTag(String key, int value){
        this(new NamespacedKey(AtlasPaper.get(), key), value);
    }

    @Override
    public Document toDocument() {
        return new Document()
                .append("_id", namespacedKey.toString())
                .append("value", value)
                .append("type",getTagType());
    }

    @Override
    public void fromDocument(Document document) {
        value = document.getInteger("value");
        namespacedKey = NamespacedKey.fromString(document.getString("_id"));
    }

    @Override
    public String getTagType() {
        return CraftTags.INTEGER.name();
    }
}
