package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.ModuleConfig;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;
import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.scripting.MethodDescription;

public abstract class ModuleApi<T extends ModuleConfig> {

    @MethodDescription("""
            Checks if module is enabled
            """)
    public boolean isEnabled() {
        return getConfig().enabled;
    }

    @MethodDescription("""
            Enables module
            """)
    @ApiVisibility(ApiType.UPDATE)
    public void enable() {
        var config = getConfig();
        if (!config.enabled) {
            config.enabled = true;
            onEnableChanged();
            ConfigStore.instance.requestWrite();
        }
    }

    @MethodDescription("""
            Disables module
            """)
    @ApiVisibility(ApiType.UPDATE)
    public void disable() {
        var config = getConfig();
        if (config.enabled) {
            config.enabled = false;
            onEnableChanged();
            ConfigStore.instance.requestWrite();
        }
    }

    @MethodDescription("""
            Sets module enabled status
            """)
    @ApiVisibility(ApiType.UPDATE)
    public void setEnabled(boolean value) {
        var config = getConfig();
        if (config.enabled != value) {
            config.enabled = value;
            onEnableChanged();
            ConfigStore.instance.requestWrite();
        }
    }

    @MethodDescription("""
            Toggles module enabled state
            """)
    @ApiVisibility(ApiType.UPDATE)
    public void toggle() {
        var config = getConfig();
        config.enabled = !config.enabled;
        onEnableChanged();
        ConfigStore.instance.requestWrite();
    }

    protected void onEnableChanged() {

    }

    protected abstract T getConfig();
}