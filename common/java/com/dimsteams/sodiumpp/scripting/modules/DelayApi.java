package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.concurrent.TickEndExecutor;
import com.dimsteams.scripting.MethodDescription;

import java.util.concurrent.CompletableFuture;

public class DelayApi {

    @MethodDescription("""
            Stops script execution for specified amount of ticks.
            Continuation may run while no world is loaded
            """)
    public CompletableFuture<Void> ticks(int ticks) {
        if (ticks <= 0) {
            return CompletableFuture.completedFuture(null);
        }

        CompletableFuture<Void> future = new CompletableFuture<>();
        TickEndExecutor.instance.waitTicks(ticks, () -> future.complete(null));
        return future;
    }
}