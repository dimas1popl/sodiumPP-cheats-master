package com.dimsteams.sodiumpp.scripting.types.nbt;

import com.dimsteams.scripting.Getter;
import com.dimsteams.scripting.type.CustomType;
import net.minecraft.nbt.StringTag;

@CustomType(name = "StringTag")
public class StringTagWrapper extends TagWrapper {

    private final StringTag inner;

    StringTagWrapper(StringTag tag) {
        this.inner = tag;
    }

    @Getter(name = "value")
    public String getValue() {
        return inner.value();
    }

    @Override
    public String getStringOr(String defaultValue) {
        return inner.value();
    }

    @Override
    public String toString() {
        return inner.toString();
    }
}