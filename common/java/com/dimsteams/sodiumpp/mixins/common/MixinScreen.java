package com.dimsteams.sodiumpp.mixins.common;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.common.events.ScreenRenderEvent;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class MixinScreen {

    @Inject(method = "extractRenderStateWithTooltipAndSubtitles", at = @At("TAIL"))
    private void onAfterRender(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks, CallbackInfo info) {
        Events.AfterScreenRendered.trigger(new ScreenRenderEvent(graphics, mouseX, mouseY));
    }
}