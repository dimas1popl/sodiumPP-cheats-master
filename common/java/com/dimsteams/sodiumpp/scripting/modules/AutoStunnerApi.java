package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoStunnerConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoStunnerApi extends ModuleApi<AutoStunnerConfig> {

    @Override
    protected AutoStunnerConfig getConfig() {
        return ConfigStore.instance.getConfig().autoStunnerConfig;
    }
}