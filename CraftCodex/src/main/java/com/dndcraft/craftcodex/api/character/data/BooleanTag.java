package com.dndcraft.craftcodex.api.character.data;

import com.dndcraft.atlas.AtlasPaper;
import com.dndcraft.craftcodex.api.character.capability.ICharacterTag;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.NamespacedKey;

public class BooleanTag implements ICharacterTag {

    @Getter private boolean value;
    private NamespacedKey namespacedKey;

    public BooleanTag(){} //For reading from document

    public BooleanTag(NamespacedKey namespacedKey, boolean value){
        this.value = value;
        this.namespacedKey = namespacedKey;
    }

    public BooleanTag(String key, boolean value){
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
        value = document.getBoolean("value");
        namespacedKey = NamespacedKey.fromString(document.getString("_id"));
    }

    @Override
    public String getTagType() {
        return CraftTags.BOOLEAN.name();
    }
}
