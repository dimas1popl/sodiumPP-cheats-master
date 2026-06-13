package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoToolConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoToolApi extends ModuleApi<AutoToolConfig> {

    @Override
    protected AutoToolConfig getConfig() {
        return ConfigStore.instance.getConfig().autoTool;
    }
}