package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.modules.esp.EspGlobal;
import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;
import com.dimsteams.scripting.MethodDescription;

public class EspApi {

    @MethodDescription("""
            Checks if ESPs rendering is enabled
            """)
    public boolean isEnabled() {
        return EspGlobal.enabled;
    }

    @MethodDescription("""
            Enables/disables rendering of all ESP modules
            """)
    @ApiVisibility(ApiType.UPDATE)
    public void toggle() {
        EspGlobal.enabled = !EspGlobal.enabled;
    }
}