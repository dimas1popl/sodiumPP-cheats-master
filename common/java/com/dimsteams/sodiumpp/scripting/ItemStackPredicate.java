package com.dimsteams.sodiumpp.scripting;

import com.dimsteams.sodiumpp.scripting.types.ItemStackWrapper;

@FunctionalInterface
public interface ItemStackPredicate {
    boolean test(ItemStackWrapper itemStack);
}