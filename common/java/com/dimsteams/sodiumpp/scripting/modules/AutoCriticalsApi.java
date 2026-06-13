package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoCriticalsConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoCriticalsApi extends ModuleApi<AutoCriticalsConfig> {

    @Override
    protected AutoCriticalsConfig getConfig() {
        return ConfigStore.instance.getConfig().autoCriticalsConfig;
    }
}