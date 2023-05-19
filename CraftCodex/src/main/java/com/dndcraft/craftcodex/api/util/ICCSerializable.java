package com.dndcraft.craftcodex.api.util;

import org.bson.Document;
import org.json.simple.JSONObject;

public interface ICCSerializable {

    //MongoDB
    Document toDocument();

    void fromDocument(Document var1);

    JSONObject toJson();

    void fromJson(String jsonifiedString);

}
