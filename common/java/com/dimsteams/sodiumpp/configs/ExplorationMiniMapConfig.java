package com.dimsteams.sodiumpp.configs;

import com.dimsteams.sodiumpp.utils.MathUtils;

public class ExplorationMiniMapConfig extends ModuleConfig implements Sanitizable {
    public Integer scanFromY;

    @Override
    public void sanitize() {
        if (scanFromY != null) {
            scanFromY = MathUtils.clamp(scanFromY, -1000, 1000);
        }
    }
}