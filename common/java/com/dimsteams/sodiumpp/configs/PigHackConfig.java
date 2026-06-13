package com.dimsteams.sodiumpp.configs;

public class PigHackConfig extends ModuleConfig {
    public boolean allowRideWithoutCarrot;
    public boolean overrideSteeringSpeed;
    public float steeringSpeed;

    public PigHackConfig() {
        enabled = false;
        allowRideWithoutCarrot = true;
        overrideSteeringSpeed = true;
        steeringSpeed = 0.1f;
    }
}
