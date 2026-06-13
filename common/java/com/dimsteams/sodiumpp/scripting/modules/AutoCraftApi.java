package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoCraftConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoCraftApi extends ModuleApi<AutoCraftConfig> {

    @Override
    protected AutoCraftConfig getConfig() {
        return ConfigStore.instance.getConfig().autoCraftConfig;
    }
}