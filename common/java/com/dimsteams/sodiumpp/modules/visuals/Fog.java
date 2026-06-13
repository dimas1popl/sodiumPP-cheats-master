package com.dimsteams.sodiumpp.modules.visuals;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.mixins.common.accessors.FogRendererAccessor;
import com.dimsteams.sodiumpp.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.fog.FogRenderer;

public class Fog implements Module {

    public static final Fog instance = new Fog();

    private Fog() {
        Events.ClientTickStart.add(this::onClientTickStart);
    }

    public void onClientTickStart() {
        if (Minecraft.getInstance().level == null) {
            return;
        }
        if (ConfigStore.instance.getConfig().fogConfig.enabled) {
            if (FogRendererAccessor.isFogEnabled_CU()) {
                FogRenderer.toggleFog();
            }
        } else {
            if (!FogRendererAccessor.isFogEnabled_CU()) {
                FogRenderer.toggleFog();
            }
        }
    }
}