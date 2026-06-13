package com.dimsteams.sodiumpp.scripting.types.nbt;

import com.dimsteams.scripting.Getter;
import com.dimsteams.scripting.IndexGetter;
import com.dimsteams.scripting.type.CustomType;
import net.minecraft.nbt.ByteArrayTag;

@CustomType(name = "ByteArrayTag")
public class ByteArrayTagWrapper extends TagWrapper {

    private final ByteArrayTag inner;

    ByteArrayTagWrapper(ByteArrayTag tag) {
        this.inner = tag;
    }

    @Getter(name = "size")
    public int getSize() {
        return inner.size();
    }

    @IndexGetter
    public int indexer(int index) {
        return inner.getAsByteArray()[index];
    }

    @Override
    public String toString() {
        return inner.toString();
    }
}