package com.dndcraft.craftcodex.api.character.data;


import com.dndcraft.atlas.AtlasPaper;
import com.dndcraft.craftcodex.api.character.capability.ICharacterTag;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.NamespacedKey;

public class DoubleTag implements ICharacterTag {

    @Getter private double value;
    private NamespacedKey namespacedKey;

    public DoubleTag(){} //For reading from document

    public DoubleTag(NamespacedKey namespacedKey, double value){
        this.value = value;
        this.namespacedKey = namespacedKey;
    }

    public DoubleTag(String key, double value){
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
        value = document.getDouble("value");
        namespacedKey = NamespacedKey.fromString(document.getString("_id"));
    }

    @Override
    public String getTagType() {
        return CraftTags.DOUBLE.name();
    }
}
