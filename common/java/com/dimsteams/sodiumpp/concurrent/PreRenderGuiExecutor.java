package com.dimsteams.sodiumpp.concurrent;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.common.events.RenderGuiEvent;

public class PreRenderGuiExecutor extends EventExecutor {

    public static final PreRenderGuiExecutor instance = new PreRenderGuiExecutor();

    private PreRenderGuiExecutor() {
        super(5000);
        Events.PreRenderGui.add(this::onPreRenderGui);
    }

    private void onPreRenderGui(RenderGuiEvent event) {
        processQueue();
    }
}