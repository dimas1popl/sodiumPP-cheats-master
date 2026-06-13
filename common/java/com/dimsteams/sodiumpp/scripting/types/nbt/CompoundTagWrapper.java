package com.dimsteams.sodiumpp.scripting.types.nbt;

import com.dimsteams.scripting.Getter;
import com.dimsteams.scripting.IndexGetter;
import com.dimsteams.scripting.type.CustomType;
import net.minecraft.nbt.CompoundTag;

@CustomType(name = "CompoundTag")
public class CompoundTagWrapper extends TagWrapper {

    private final CompoundTag inner;

    public CompoundTagWrapper(CompoundTag tag) {
        this.inner = tag;
    }

    @Getter(name = "size")
    public int getSize() {
        return inner.size();
    }

    @IndexGetter
    public TagWrapper indexer(String key) {
        return get(key);
    }

    public boolean contains(String key) {
        return inner.contains(key);
    }

    public TagWrapper get(String key) {
        return TagWrapper.from(inner.get(key));
    }

    public String toString() {
        return inner.toString();
    }
}