package com.dimsteams.sodiumpp.scripting;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface AsyncRunnable {
    CompletableFuture<?> run();
}