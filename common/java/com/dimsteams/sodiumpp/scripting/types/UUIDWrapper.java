package com.dimsteams.sodiumpp.scripting.types;

import com.dimsteams.sodiumpp.scripting.HiddenMethod;
import com.dimsteams.scripting.type.CustomType;

import java.util.UUID;

@CustomType(name = "UUID")
public class UUIDWrapper {

    private final UUID id;

    public UUIDWrapper(UUID id) {
        this.id = id;
    }

    @HiddenMethod
    public UUID getRaw() {
        return id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}