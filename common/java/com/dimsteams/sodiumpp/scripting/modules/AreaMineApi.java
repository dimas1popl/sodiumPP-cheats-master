package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AreaMineConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AreaMineApi extends ModuleApi<AreaMineConfig> {

    @Override
    protected AreaMineConfig getConfig() {
        return ConfigStore.instance.getConfig().areaMineConfig;
    }
}