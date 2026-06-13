package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoBucketConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoBucketApi extends ModuleApi<AutoBucketConfig> {

    @Override
    protected AutoBucketConfig getConfig() {
        return ConfigStore.instance.getConfig().autoBucketConfig;
    }
}