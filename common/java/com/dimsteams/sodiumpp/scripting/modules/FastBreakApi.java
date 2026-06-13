package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.FastBreakConfig;

public class FastBreakApi extends ModuleApi<FastBreakConfig> {

    @Override
    protected FastBreakConfig getConfig() {
        return ConfigStore.instance.getConfig().fastBreakConfig;
    }
}