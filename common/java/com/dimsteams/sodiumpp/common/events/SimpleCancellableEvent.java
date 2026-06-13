package com.dimsteams.sodiumpp.common.events;

public class SimpleCancellableEvent implements CancelableEvent {

    private boolean canceled;

    @Override
    public void cancel() {
        this.canceled = true;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }
}