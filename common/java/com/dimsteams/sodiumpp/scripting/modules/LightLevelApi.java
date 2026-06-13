package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.LightLevelConfig;
import com.dimsteams.sodiumpp.modules.esp.LightLevel;

public class LightLevelApi extends ModuleApi<LightLevelConfig> {

    @Override
    protected LightLevelConfig getConfig() {
        return ConfigStore.instance.getConfig().lightLevelConfig;
    }

    @Override
    protected void onEnableChanged() {
        LightLevel.instance.onChanged();
    }
}