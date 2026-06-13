package com.dimsteams.sodiumpp.scripting.events;

@FunctionalInterface
public interface EntityIdConsumer {
    void accept(int id);
}