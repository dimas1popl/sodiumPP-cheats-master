package com.dimsteams.sodiumpp.configs;

import com.dimsteams.sodiumpp.utils.MathUtils;

public class ScaffoldConfig extends ModuleConfig implements Sanitizable {
    public double distance;
    public boolean replaceBlocksFromInventory;
    public boolean attachToAir;
    public boolean keepSelectedSlot;
    //public boolean useSlabs;

    @Override
    public void sanitize() {
        distance = MathUtils.clamp(distance, 0, 0.5);
    }
}