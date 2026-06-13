package com.dimsteams.sodiumpp.configs;

import com.dimsteams.sodiumpp.utils.MathUtils;

public class AirPlaceConfig implements Sanitizable {

    public double minRange;
    public double maxRange;

    public AirPlaceConfig() {
        minRange = 1;
        maxRange = 5;
    }

    @Override
    public void sanitize() {
        minRange = MathUtils.clamp(minRange, 0.5, 5);
        maxRange = MathUtils.clamp(maxRange, minRange, 10);
    }
}