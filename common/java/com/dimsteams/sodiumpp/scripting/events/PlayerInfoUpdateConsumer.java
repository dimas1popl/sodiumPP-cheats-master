package com.dimsteams.sodiumpp.scripting.events;

import com.dimsteams.sodiumpp.scripting.types.PlayerInfoWrapper;

@FunctionalInterface
public interface PlayerInfoUpdateConsumer {
    void accept(PlayerInfoWrapper info, String type);
}