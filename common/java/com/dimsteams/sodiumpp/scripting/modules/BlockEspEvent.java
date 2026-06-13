package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.modules.esp.BlockEsp;
import com.dimsteams.scripting.type.CustomType;

@SuppressWarnings("unused")
@CustomType(name = "BlockEspEvent")
public class BlockEspEvent {

    private final BlockEsp.BlockScriptResult result;

    public BlockEspEvent(BlockEsp.BlockScriptResult result) {
        this.result = result;
    }

    public void setTracerStatus(boolean status) {
        result.tracer = status ? 1 : 0;
    }

    public void setOutlineStatus(boolean status) {
        result.outline = status ? 1 : 0;
    }

    public void setOverlayStatus(boolean status) {
        result.overlay = status ? 1 : 0;
    }
}