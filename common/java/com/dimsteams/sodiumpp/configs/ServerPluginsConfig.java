package com.dimsteams.sodiumpp.configs;

import com.dimsteams.sodiumpp.utils.MathUtils;

public class ServerPluginsConfig implements Sanitizable {

    public int waitTicks;
    public boolean autoPrint;

    public ServerPluginsConfig() {
        waitTicks = 20;
    }

    @Override
    public void sanitize() {
        waitTicks = MathUtils.clamp(waitTicks, 0, 60 * 20);
    }
}