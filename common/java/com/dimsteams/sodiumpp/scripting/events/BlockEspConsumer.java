package com.dimsteams.sodiumpp.scripting.events;

import com.dimsteams.sodiumpp.scripting.modules.BlockEspEvent;
import com.dimsteams.sodiumpp.scripting.types.BlockPosWrapper;

@FunctionalInterface
public interface BlockEspConsumer {
    void accept(BlockPosWrapper pos, BlockEspEvent event);
}