package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.TargetEspConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class TargetEspApi extends ModuleApi<TargetEspConfig> {

    @Override
    protected TargetEspConfig getConfig() {
        return ConfigStore.instance.getConfig().targetEspConfig;
    }

    // Дополнительные API методы для скриптинга (без сохранения - оно автоматическое)
    public void setBoxColor(String hexColor) {
        getConfig().boxColor = hexColor;
        // ConfigStore автоматически сохраняет при изменении, не нужно вызывать save()
    }

    public void setOutlineColor(String hexColor) {
        getConfig().outlineColor = hexColor;
    }

    public void setMaxDistance(double distance) {
        getConfig().maxDistance = distance;
    }

    public boolean isTargetValid(String playerName) {
        // Логика проверки валидности цели
        return true;
    }

    // Метод для сброса всех настроек к дефолтным
    public void resetToDefault() {
        TargetEspConfig defaultConfig = new TargetEspConfig();
        TargetEspConfig current = getConfig();

        current.boxEnabled = defaultConfig.boxEnabled;
        current.outlineEnabled = defaultConfig.outlineEnabled;
        current.glowEnabled = defaultConfig.glowEnabled;
        current.tracerEnabled = defaultConfig.tracerEnabled;
        current.nameEnabled = defaultConfig.nameEnabled;
        current.healthEnabled = defaultConfig.healthEnabled;
        current.distanceEnabled = defaultConfig.distanceEnabled;
        current.armorEnabled = defaultConfig.armorEnabled;

        current.boxColor = defaultConfig.boxColor;
        current.outlineColor = defaultConfig.outlineColor;
        current.tracerColor = defaultConfig.tracerColor;
        current.nameColor = defaultConfig.nameColor;
        current.healthColor = defaultConfig.healthColor;

        current.boxThickness = defaultConfig.boxThickness;
        current.outlineThickness = defaultConfig.outlineThickness;
        current.tracerThickness = defaultConfig.tracerThickness;

        current.ignoreInvisible = defaultConfig.ignoreInvisible;
        current.ignoreTeammates = defaultConfig.ignoreTeammates;
        current.onlyTargetingMe = defaultConfig.onlyTargetingMe;
        current.maxDistance = defaultConfig.maxDistance;
        current.minHealth = defaultConfig.minHealth;

        current.chams = defaultConfig.chams;
        current.chamsBrightness = defaultConfig.chamsBrightness;
        current.throughWalls = defaultConfig.throughWalls;
        current.animate = defaultConfig.animate;
        current.animationSpeed = defaultConfig.animationSpeed;

        current.showSneakingIndicator = defaultConfig.showSneakingIndicator;
        current.showPotionEffects = defaultConfig.showPotionEffects;
    }
}