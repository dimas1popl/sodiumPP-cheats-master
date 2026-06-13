package com.dimsteams.sodiumpp.mixins.common;

import com.dimsteams.sodiumpp.extensions.GuiRenderStateExtension;
import net.minecraft.client.renderer.state.gui.GuiElementRenderState;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiRenderState.class)
public abstract class MixinGuiRenderState implements GuiRenderStateExtension {

    @Shadow
    private GuiRenderState.Node current;

    @Override
    public void addGuiElement_CU(GuiElementRenderState element) {
        this.current.addGuiElement(element);
    }
}