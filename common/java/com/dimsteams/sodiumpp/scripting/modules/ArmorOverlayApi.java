package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ArmorOverlayConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class ArmorOverlayApi extends ModuleApi<ArmorOverlayConfig> {

    @Override
    protected ArmorOverlayConfig getConfig() {
        return ConfigStore.instance.getConfig().armorOverlayConfig;
    }
}