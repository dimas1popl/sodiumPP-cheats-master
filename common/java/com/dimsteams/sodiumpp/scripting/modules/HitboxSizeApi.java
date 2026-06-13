package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.HitboxSizeConfig;

public class HitboxSizeApi extends ModuleApi<HitboxSizeConfig> {

    @Override
    protected HitboxSizeConfig getConfig() {
        return ConfigStore.instance.getConfig().hitboxSizeConfig;
    }
}