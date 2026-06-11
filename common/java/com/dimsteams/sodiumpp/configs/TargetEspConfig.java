package com.dimsteams.sodiumpp.configs;

public class TargetEspConfig extends ModuleConfig implements Sanitizable {
    // Основные настройки
    public boolean boxEnabled;
    public boolean outlineEnabled;
    public boolean glowEnabled;
    public boolean tracerEnabled;
    public boolean nameEnabled;
    public boolean healthEnabled;
    public boolean distanceEnabled;
    public boolean armorEnabled;

    // Цвета
    public String boxColor;
    public String outlineColor;
    public String tracerColor;
    public String nameColor;
    public String healthColor;

    // Толщины
    public float boxThickness;
    public float outlineThickness;
    public float tracerThickness;

    // Фильтры
    public boolean ignoreInvisible;
    public boolean ignoreTeammates;
    public boolean onlyTargetingMe;
    public double maxDistance;
    public double minHealth;
    public String fontColor;

    // Эффекты
    public boolean chams;
    public float chamsBrightness;
    public boolean throughWalls;
    public boolean animate;
    public float animationSpeed;

    // Дополнительно
    public boolean showSneakingIndicator;
    public boolean showPotionEffects;
    public String[] highlightColors;

    public TargetEspConfig() {
        // Значения по умолчанию
        boxEnabled = true;
        outlineEnabled = true;
        glowEnabled = false;
        tracerEnabled = false;
        nameEnabled = true;
        healthEnabled = true;
        distanceEnabled = true;
        armorEnabled = false;

        boxColor = "#FF0000";
        fontColor = "#FFFFFF";
        outlineColor = "#00FF00";
        tracerColor = "#FF00FF";
        nameColor = "#FFFFFF";
        healthColor = "#FF5555";

        boxThickness = 2.0f;
        outlineThickness = 1.5f;
        tracerThickness = 1.0f;

        ignoreInvisible = true;
        ignoreTeammates = false;
        onlyTargetingMe = false;
        maxDistance = 50.0;
        minHealth = 0.0;

        chams = false;
        chamsBrightness = 1.0f;
        throughWalls = true;
        animate = true;
        animationSpeed = 1.0f;

        showSneakingIndicator = true;
        showPotionEffects = false;
        highlightColors = new String[]{"#00FF00", "#FFFF00", "#FF0000"};
    }

    @Override
    public void sanitize() {
        if (boxThickness < 0.5f) boxThickness = 0.5f;
        if (boxThickness > 5.0f) boxThickness = 5.0f;

        if (outlineThickness < 0.5f) outlineThickness = 0.5f;
        if (outlineThickness > 3.0f) outlineThickness = 3.0f;

        if (tracerThickness < 0.5f) tracerThickness = 0.5f;
        if (tracerThickness > 3.0f) tracerThickness = 3.0f;

        if (maxDistance < 5.0) maxDistance = 5.0;
        if (maxDistance > 200.0) maxDistance = 200.0;

        if (minHealth < 0.0) minHealth = 0.0;
        if (minHealth > 40.0) minHealth = 40.0;

        if (animationSpeed < 0.1f) animationSpeed = 0.1f;
        if (animationSpeed > 3.0f) animationSpeed = 3.0f;

        if (chamsBrightness < 0.1f) chamsBrightness = 0.1f;
        if (chamsBrightness > 2.0f) chamsBrightness = 2.0f;

        if (!fontColor.matches("^#[0-9A-Fa-f]{6}$")) fontColor = "#FFFFFF";
        if (!boxColor.matches("^#[0-9A-Fa-f]{6}$")) boxColor = "#FF0000";
        if (!outlineColor.matches("^#[0-9A-Fa-f]{6}$")) outlineColor = "#00FF00";
        if (!tracerColor.matches("^#[0-9A-Fa-f]{6}$")) tracerColor = "#FF00FF";
        if (!nameColor.matches("^#[0-9A-Fa-f]{6}$")) nameColor = "#FFFFFF";
        if (!healthColor.matches("^#[0-9A-Fa-f]{6}$")) healthColor = "#FF5555";
    }
}