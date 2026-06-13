package com.dimsteams.sodiumpp.scripting.events;

@FunctionalInterface
public interface ServerInformationConsumer {
    void accept(ServerInformation info);
}