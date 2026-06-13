package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoTotemConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoTotemApi extends ModuleApi<AutoTotemConfig> {

    @Override
    protected AutoTotemConfig getConfig() {
        return ConfigStore.instance.getConfig().autoTotemConfig;
    }
}