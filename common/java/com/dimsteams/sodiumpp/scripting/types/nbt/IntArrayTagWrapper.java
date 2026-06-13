package com.dimsteams.sodiumpp.scripting.types.nbt;

import com.dimsteams.sodiumpp.scripting.types.UUIDWrapper;
import com.dimsteams.scripting.Getter;
import com.dimsteams.scripting.IndexGetter;
import com.dimsteams.scripting.type.CustomType;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.IntArrayTag;

@CustomType(name = "IntArrayTag")
public class IntArrayTagWrapper extends TagWrapper {

    private final IntArrayTag inner;

    IntArrayTagWrapper(IntArrayTag tag) {
        this.inner = tag;
    }

    @Getter(name = "size")
    public int getSize() {
        return inner.size();
    }

    @IndexGetter
    public int indexer(int index) {
        return inner.getAsIntArray()[index];
    }

    public UUIDWrapper asUUID() {
        if (inner.size() != 4) {
            throw new IllegalArgumentException("Expected int-array of length 4, got " + inner.getAsIntArray().length + ".");
        }
        return new UUIDWrapper(UUIDUtil.uuidFromIntArray(inner.getAsIntArray()));
    }

    @Override
    public String toString() {
        return inner.toString();
    }
}