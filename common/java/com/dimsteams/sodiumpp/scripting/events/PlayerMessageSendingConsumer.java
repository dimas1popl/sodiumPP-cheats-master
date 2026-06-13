package com.dimsteams.sodiumpp.scripting.events;

import com.dimsteams.sodiumpp.scripting.modules.PlayerMessageSendingEvent;

@FunctionalInterface
public interface PlayerMessageSendingConsumer {
    void consume(PlayerMessageSendingEvent event);
}