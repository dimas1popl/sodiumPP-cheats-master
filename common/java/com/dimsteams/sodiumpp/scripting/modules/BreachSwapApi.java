package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.BreachSwapConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.modules.automation.BreachSwap;
import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;

public class BreachSwapApi extends ModuleApi<BreachSwapConfig> {

    @Override
    protected BreachSwapConfig getConfig() {
        return ConfigStore.instance.getConfig().breachSwapConfig;
    }
}