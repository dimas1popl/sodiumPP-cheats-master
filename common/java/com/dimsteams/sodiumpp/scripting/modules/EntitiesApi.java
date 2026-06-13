package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.collections.ImmutableList;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.EntityEspConfig;
import com.dimsteams.sodiumpp.modules.esp.EntityEsp;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;
import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.sodiumpp.utils.ColorUtils;
import com.dimsteams.scripting.MethodDescription;

import java.awt.*;

@SuppressWarnings("unused")
public class EntitiesApi {

    @MethodDescription("""
            Checks if Entity ESP rendering is enabled
            """)
    public boolean isEnabled() {
        return EntityEsp.instance.isEnabled();
    }

    @MethodDescription("""
            Toggles Entity ESP rendering
            """)
    @ApiVisibility(ApiType.UPDATE)
    public void toggle() {
        EntityEsp.instance.toggle();
    }

    @MethodDescription("""
            Returns all entity class names you have configured for Entity ESP. Does not skip disabled ones.
            """)
    public String[] getEntries() {
        return ConfigStore.instance.getConfig().entities.configs.stream()
                .map(c -> c.clazz.getName())
                .toArray(String[]::new);
    }

    @MethodDescription("""
            Checks if Entity ESP is enabled for specified entity class
            """)
    public boolean isEnabled(String className) {
        EntityEspConfig config = getConfig(className);
        if (config == null) {
            return false;
        }
        return config.enabled;
    }

    @MethodDescription("""
            Toggles enabled state for specified entity class
            """)
    @ApiVisibility(ApiType.UPDATE)
    public void toggle(String className) {
        EntityEspConfig config = getConfig(className);
        if (config == null) {
            return;
        }
        config.enabled = !config.enabled;
        ConfigStore.instance.requestWrite();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void setTracerColor(String className, String color) {
        Color colorValue = ColorUtils.parseColor2(color);
        if (colorValue == null) {
            return;
        }

        EntityEspConfig config = getConfig(className);
        if (config == null) {
            return;
        }

        config.tracerColor = colorValue;
        ConfigStore.instance.requestWrite();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void setOutlineColor(String className, String color) {
        Color colorValue = ColorUtils.parseColor2(color);
        if (colorValue == null) {
            return;
        }

        EntityEspConfig config = getConfig(className);
        if (config == null) {
            return;
        }

        config.glowColor = colorValue;
        ConfigStore.instance.requestWrite();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void setCollisionBoxColor(String className, String color) {
        Color colorValue = ColorUtils.parseColor2(color);
        if (colorValue == null) {
            return;
        }

        EntityEspConfig config = getConfig(className);
        if (config == null) {
            return;
        }

        config.outlineColor = colorValue;
        ConfigStore.instance.requestWrite();
    }

    @ApiVisibility(ApiType.UPDATE)
    public void setOverlayColor(String className, String color) {
        Color colorValue = ColorUtils.parseColor2(color);
        if (colorValue == null) {
            return;
        }

        EntityEspConfig config = getConfig(className);
        if (config == null) {
            return;
        }

        config.overlayColor = colorValue;
        ConfigStore.instance.requestWrite();
    }

    private EntityEspConfig getConfig(String className) {
        ImmutableList<EntityEspConfig> list = ConfigStore.instance.getConfig().entities.configs;
        return list.stream()
                .filter(c -> c.clazz.getName().equals(className))
                .findFirst()
                .orElse(null);
    }
}