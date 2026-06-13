package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AntiHungerConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AntiHungerApi extends ModuleApi<AntiHungerConfig> {

    @Override
    protected AntiHungerConfig getConfig() {
        return ConfigStore.instance.getConfig().antiHungerConfig;
    }
}