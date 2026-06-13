package com.dimsteams.sodiumpp.scripting.events;

@FunctionalInterface
public interface ChatMessageConsumer {
    void accept(String text);
}