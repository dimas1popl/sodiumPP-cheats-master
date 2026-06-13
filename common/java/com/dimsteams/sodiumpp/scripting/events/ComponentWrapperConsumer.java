package com.dimsteams.sodiumpp.scripting.events;

import com.dimsteams.sodiumpp.scripting.types.ComponentWrapper;

@FunctionalInterface
public interface ComponentWrapperConsumer {
    void accept(ComponentWrapper text);
}
