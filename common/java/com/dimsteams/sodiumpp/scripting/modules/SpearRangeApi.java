package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.SpearRangeConfig;

public class SpearRangeApi extends ModuleApi<SpearRangeConfig> {


    @Override
    protected SpearRangeConfig getConfig() {
        return ConfigStore.instance.getConfig().spearRangeConfig;
    }
}