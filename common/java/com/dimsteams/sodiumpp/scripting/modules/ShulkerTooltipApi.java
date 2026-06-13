package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.ShulkerTooltipConfig;

public class ShulkerTooltipApi extends ModuleApi<ShulkerTooltipConfig> {

    @Override
    protected ShulkerTooltipConfig getConfig() {
        return ConfigStore.instance.getConfig().shulkerTooltipConfig;
    }
}