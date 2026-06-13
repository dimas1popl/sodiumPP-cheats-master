package com.dimsteams.sodiumpp.scripting.types.json;

import com.google.gson.JsonElement;
import com.dimsteams.scripting.InternalException;
import com.dimsteams.scripting.type.CustomType;

@CustomType(name = "JsonInvalid")
public class JsonInvalidWrapper extends JsonElementWrapper {

    public static final JsonElementWrapper INSTANCE = new JsonInvalidWrapper();

    private JsonInvalidWrapper() {}

    @Override
    protected JsonElement unwrap() {
        throw new InternalException();
    }
}