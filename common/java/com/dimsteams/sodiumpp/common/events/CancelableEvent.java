package com.dimsteams.sodiumpp.common.events;

public interface CancelableEvent {
    void cancel();
    boolean isCanceled();
}