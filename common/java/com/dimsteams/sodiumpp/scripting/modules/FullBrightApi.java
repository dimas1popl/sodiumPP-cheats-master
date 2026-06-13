package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.FullBrightConfig;

public class FullBrightApi extends ModuleApi<FullBrightConfig> {

    @Override
    protected FullBrightConfig getConfig() {
        return ConfigStore.instance.getConfig().fullBrightConfig;
    }
}