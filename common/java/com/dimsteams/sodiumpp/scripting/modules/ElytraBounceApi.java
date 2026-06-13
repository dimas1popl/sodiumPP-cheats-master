package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.modules.automation.ElytraBounce;
import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;

public class ElytraBounceApi {

    public boolean isEnabled() {
        return ElytraBounce.instance.isEnabled();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void enable() {
        ElytraBounce.instance.enable();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void disable() {
        ElytraBounce.instance.disable();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void toggle() {
        if (ElytraBounce.instance.isEnabled()) {
            ElytraBounce.instance.disable();
        } else {
            ElytraBounce.instance.enable();
        }
    }

    @ApiVisibility(ApiType.UPDATE)
    public void setEnabled(boolean enabled) {
        if (enabled) {
            ElytraBounce.instance.enable();
        } else {
            ElytraBounce.instance.disable();
        }
    }
}