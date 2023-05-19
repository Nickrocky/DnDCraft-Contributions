package com.dndcraft.craftcodex.api.character.data;

import com.dndcraft.craftcodex.api.character.capability.ICharacterTag;
import com.dndcraft.atlas.AtlasPaper;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.NamespacedKey;

@Getter
public class StringTag implements ICharacterTag {

    @Getter private String string;
    private NamespacedKey namespacedKey;

    public StringTag(){} //For reading from document

    public StringTag(NamespacedKey namespacedKey, String string){
        this.string = string;
        this.namespacedKey = namespacedKey;
    }

    public StringTag(String key, String string){
        this(new NamespacedKey(AtlasPaper.get(), key), string);
    }

    @Override
    public Document toDocument() {
        return new Document()
                .append("_id", namespacedKey.toString())
                .append("value", string)
                .append("type",getTagType());
    }

    @Override
    public void fromDocument(Document document) {
        string = document.getString("value");
        namespacedKey = NamespacedKey.fromString(document.getString("_id"));
    }

    @Override
    public String getTagType() {
        return CraftTags.STRING.name();
    }
}
