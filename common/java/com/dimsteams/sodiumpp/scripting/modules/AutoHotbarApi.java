package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoHotbarConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoHotbarApi extends ModuleApi<AutoHotbarConfig> {

    @Override
    protected AutoHotbarConfig getConfig() {
        return ConfigStore.instance.getConfig().autoHotbarConfig;
    }
}