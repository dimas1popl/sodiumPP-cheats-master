package com.dimsteams.sodiumpp.configs;

import com.dimsteams.sodiumpp.utils.MathUtils;

public class AutoEatConfig extends ModuleConfig implements Sanitizable {

    public boolean isHungerLimitEnabled;
    public int hungerLimit;

    public AutoEatConfig() {
        hungerLimit = 10;
    }

    @Override
    public void sanitize() {
        hungerLimit = MathUtils.clamp(hungerLimit, 0, 100);
    }
}