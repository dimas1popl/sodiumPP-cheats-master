package com.dimsteams.sodiumpp.scripting.events;

import com.dimsteams.sodiumpp.scripting.modules.PacketEvent;

@FunctionalInterface
public interface PacketEventConsumer {
    void accept(PacketEvent event);
}