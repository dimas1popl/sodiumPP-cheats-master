package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoAttackConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoAttackApi extends ModuleApi<AutoAttackConfig> {

    @Override
    protected AutoAttackConfig getConfig() {
        return ConfigStore.instance.getConfig().autoAttackConfig;
    }
}
