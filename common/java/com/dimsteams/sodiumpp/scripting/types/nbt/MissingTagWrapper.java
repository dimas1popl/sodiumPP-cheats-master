package com.dimsteams.sodiumpp.scripting.types.nbt;

import com.dimsteams.scripting.type.CustomType;

@CustomType(name = "MissingTag")
public class MissingTagWrapper extends TagWrapper {

    public static final TagWrapper instance = new MissingTagWrapper();

    private MissingTagWrapper() {}
}