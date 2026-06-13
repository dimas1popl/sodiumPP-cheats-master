package com.dimsteams.sodiumpp.configs;

import com.dimsteams.sodiumpp.utils.MathUtils;

public class AutoToolConfig extends ModuleConfig implements Sanitizable {

    public static final String MODE_HOTBAR = "HOTBAR";
    public static final String MODE_INVENTORY = "INVENTORY";

    public String mode;
    public int slot;
    public int minDurability;

    public AutoToolConfig() {
        mode = MODE_HOTBAR;
        minDurability = 10;
    }

    @Override
    public void sanitize() {
        if (!mode.equals(MODE_HOTBAR) && !mode.equals(MODE_INVENTORY)) {
            mode = MODE_HOTBAR;
        }
        slot = MathUtils.clamp(slot, 1, 9);
        minDurability = MathUtils.clamp(minDurability, 0, 1000);
    }
}