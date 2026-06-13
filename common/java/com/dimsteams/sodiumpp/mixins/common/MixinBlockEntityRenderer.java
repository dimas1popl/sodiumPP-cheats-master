package com.dimsteams.sodiumpp.mixins.common;

import com.dimsteams.sodiumpp.modules.visuals.BlockEntityDistance;
import com.dimsteams.mixin.ReplaceMethod;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockEntityRenderer.class)
public interface MixinBlockEntityRenderer {

    @ReplaceMethod(at = @At("HEAD"), method = "getViewDistance")
    default int getViewDistance() {
        return BlockEntityDistance.VIEW_DISTANCE_CACHED;
    }
}