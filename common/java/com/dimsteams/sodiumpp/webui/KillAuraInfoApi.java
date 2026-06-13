package com.dimsteams.sodiumpp.webui;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.KillAuraConfig;

import java.util.ArrayList;
import java.util.List;

public class KillAuraInfoApi extends ApiBase {

    @Override
    public String getRoute() {
        return "kill-aura-info";
    }

    @Override
    public String get() {
        List<KillAuraConfig.PriorityEntry> entries = new ArrayList<>(KillAuraConfig.PredefinedPriorityEntry.entries.values());
        for (KillAuraConfig.PriorityEntry entry: ConfigStore.instance.getConfig().killAuraConfig.customEntries) {
            entries.add(entry);
        }
        return gson.toJson(entries);
    }
}