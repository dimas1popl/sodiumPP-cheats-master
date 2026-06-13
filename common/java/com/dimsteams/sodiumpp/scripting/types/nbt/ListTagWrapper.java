package com.dimsteams.sodiumpp.scripting.types.nbt;

import com.dimsteams.scripting.Getter;
import com.dimsteams.scripting.IndexGetter;
import com.dimsteams.scripting.type.CustomType;
import net.minecraft.nbt.ListTag;

@CustomType(name = "ListTag")
public class ListTagWrapper extends TagWrapper {

    private final ListTag inner;

    ListTagWrapper(ListTag tag) {
        this.inner = tag;
    }

    @Getter(name = "size")
    public int getSize() {
        return inner.size();
    }

    @IndexGetter
    public TagWrapper indexer(int index) {
        return TagWrapper.from(inner.get(index));
    }

    @Override
    public String toString() {
        return inner.toString();
    }
}
