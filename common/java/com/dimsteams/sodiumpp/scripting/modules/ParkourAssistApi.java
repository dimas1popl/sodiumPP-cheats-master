package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.modules.automation.ParkourAssist;
import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;

public class ParkourAssistApi {

    public boolean isEnabled() {
        return ParkourAssist.instance.isEnabled();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void enable() {
        ParkourAssist.instance.enable();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void disable() {
        ParkourAssist.instance.disable();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void toggle() {
        if (ParkourAssist.instance.isEnabled()) {
            ParkourAssist.instance.disable();
        } else {
            ParkourAssist.instance.enable();
        }
    }

    @ApiVisibility(ApiType.UPDATE)
    public void setEnabled(boolean enabled) {
        if (enabled) {
            ParkourAssist.instance.enable();
        } else {
            ParkourAssist.instance.disable();
        }
    }
}