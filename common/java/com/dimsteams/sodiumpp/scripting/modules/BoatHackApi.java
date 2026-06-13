package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.BoatHackConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;
import com.dimsteams.scripting.MethodDescription;

@SuppressWarnings("unused")
public class BoatHackApi {

    @MethodDescription("""
            If Boat Fly enabled
            """)
    public boolean isFlyEnabled() {
        return getConfig().fly;
    }

    @MethodDescription("""
            Toggles Boat Fly status
            """)
    @ApiVisibility(ApiType.UPDATE)
    public void toggleFly() {
        BoatHackConfig config = getConfig();
        config.fly = !config.fly;
        ConfigStore.instance.requestWrite();
    }

    private BoatHackConfig getConfig() {
        return ConfigStore.instance.getConfig().boatHackConfig;
    }
}