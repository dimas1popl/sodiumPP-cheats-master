package com.dimsteams.sodiumpp.scripting.events;

import com.dimsteams.sodiumpp.scripting.modules.EntityEspEvent;

@FunctionalInterface
public interface EntityEspConsumer {
    void accept(int id, EntityEspEvent event);
}