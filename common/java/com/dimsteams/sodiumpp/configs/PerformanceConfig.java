package com.dimsteams.sodiumpp.configs;

import com.dimsteams.sodiumpp.utils.MathUtils;

public class PerformanceConfig implements Sanitizable, ModuleStateProvider {

    public boolean limitBackgroundWindowFps;
    public int backgroundWindowFps;

    public PerformanceConfig() {
        backgroundWindowFps = 20;
    }

    @Override
    public void sanitize() {
        backgroundWindowFps = MathUtils.clamp(backgroundWindowFps, 1, 120);
    }

    @Override
    public boolean isEnabled() {
        return limitBackgroundWindowFps;
    }
}