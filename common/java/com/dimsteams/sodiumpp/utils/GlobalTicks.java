package com.dimsteams.sodiumpp.utils;

import com.dimsteams.sodiumpp.mixins.common.accessors.MinecraftAccessor;
import net.minecraft.client.Minecraft;

public class GlobalTicks {
    public static long get() {
        return ((MinecraftAccessor) Minecraft.getInstance()).getClientTickCount_CU();
    }
}