package com.dimsteams.sodiumpp.configs.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.dimsteams.sodiumpp.configs.KillAuraConfig;

public class KillAuraConfig$PriorityEntryTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (KillAuraConfig.PriorityEntry.class.isAssignableFrom(type.getRawType())) {
            return (TypeAdapter<T>) new KillAuraConfig$PriorityEntryTypeAdapter();
        }
        return null;
    }
}
