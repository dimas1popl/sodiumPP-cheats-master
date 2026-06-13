package com.dimsteams.sodiumpp.blocks;

import java.util.concurrent.CompletableFuture;

public abstract class BlockBreakPlan {
    public abstract CompletableFuture<Void> apply();
}