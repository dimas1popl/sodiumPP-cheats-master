package com.dimsteams.sodiumpp.configs;

public class AdvancedTooltipsConfig implements ModuleStateProvider {

    public boolean beeContainer;
    public boolean repairCost;

    @Override
    public boolean isEnabled() {
        return beeContainer || repairCost;
    }
}
