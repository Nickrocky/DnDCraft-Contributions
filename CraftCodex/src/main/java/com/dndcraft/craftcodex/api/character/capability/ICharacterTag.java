package com.dndcraft.craftcodex.api.character.capability;


import com.dndcraft.atlas.io.mongodb.interfaces.ISerializable;

public interface ICharacterTag extends ISerializable {

    /**
     * The type of the tag Ex. String, Int, Double, etc
     * */
    String getTagType();

}

