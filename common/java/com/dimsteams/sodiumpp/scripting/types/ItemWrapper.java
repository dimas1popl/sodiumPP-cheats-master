package com.dimsteams.sodiumpp.scripting.types;

import com.dimsteams.sodiumpp.common.Registries;
import com.dimsteams.scripting.Getter;
import com.dimsteams.scripting.Lazy;
import com.dimsteams.scripting.type.CustomType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
@CustomType(name = "Item")
public class ItemWrapper {

    private final Item inner;
    private final Lazy<String[]> tags;

    public ItemWrapper(Item item) {
        this.inner = item;
        this.tags = new Lazy<>(this::getTagsInternal);
    }

    @Getter(name = "id")
    public String getId() {
        return Registries.ITEMS.getKey(inner).toString();
    }

    @Getter(name = "name")
    public String getName() {
        return inner.getName(inner.getDefaultInstance()).getString();
    }

    @Getter(name = "stackSize")
    public int getStackSize() {
        return inner.getDefaultMaxStackSize();
    }

    @Getter(name = "tags")
    public String[] getTags() {
        return tags.value();
    }

    public boolean hasTag(String tag) {
        Identifier location = Identifier.tryParse(tag);
        if (location == null) {
            return false;
        }
        return inner.builtInRegistryHolder().tags().anyMatch(t -> t.location().equals(location));
    }

    private String[] getTagsInternal() {
        return inner.builtInRegistryHolder().tags().map(t -> t.location().toString()).toArray(String[]::new);
    }
}