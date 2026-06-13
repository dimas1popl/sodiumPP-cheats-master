package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.StepUpConfig;

public class StepUpApi extends ModuleApi<StepUpConfig> {

    @Override
    protected StepUpConfig getConfig() {
        return ConfigStore.instance.getConfig().stepUp;
    }
}
