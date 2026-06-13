package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.NoFallConfig;

public class NoFallApi extends ModuleApi<NoFallConfig> {

    @Override
    protected NoFallConfig getConfig() {
        return ConfigStore.instance.getConfig().noFallConfig;
    }
}