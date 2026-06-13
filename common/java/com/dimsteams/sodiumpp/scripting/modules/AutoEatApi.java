package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoEatConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoEatApi extends ModuleApi<AutoEatConfig> {

    @Override
    protected AutoEatConfig getConfig() {
        return ConfigStore.instance.getConfig().autoEatConfig;
    }
}