package com.dimsteams.sodiumpp.render;

import com.mojang.blaze3d.opengl.GlDevice;
import com.mojang.blaze3d.opengl.GlTexture;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.textures.GpuTexture;
import com.dimsteams.sodiumpp.mixins.common.accessors.GpuDeviceAccessor;

public class GlHelper {

    public static GlDevice getGlDevice(GpuDevice device) {
        return (GlDevice) ((GpuDeviceAccessor) device).getBackend_CU();
    }

    public static GlTexture getGlTexture(GpuTexture texture) {
        return (GlTexture) texture;
    }
}