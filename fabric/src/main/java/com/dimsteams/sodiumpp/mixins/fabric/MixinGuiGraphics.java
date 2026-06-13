package com.dimsteams.sodiumpp.mixins.fabric;

import com.dimsteams.sodiumpp.collections.TaggedArrayList;
import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.common.events.PreRenderTooltipEvent;
import com.dimsteams.mixin.ModifyMethodReturnValue;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2ic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiGraphicsExtractor.class)
public abstract class MixinGuiGraphics {

    @Inject(
            at = @At("HEAD"),
            method = "tooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;Lnet/minecraft/resources/Identifier;)V",
            cancellable = true)
    private void onBeforeRenderTooltip(
            Font font,
            List<ClientTooltipComponent> lines,
            int x, int y,
            ClientTooltipPositioner positioner,
            @Nullable Identifier style,
            CallbackInfo info
    ) {
        if (lines.isEmpty()) {
            return;
        }

        if (lines instanceof TaggedArrayList<?,?>) {
            ItemStack itemStack = ((TaggedArrayList<ClientTooltipComponent, ItemStack>) lines).getTag();
            if (Events.PreRenderTooltip.trigger(new PreRenderTooltipEvent((GuiGraphicsExtractor) (Object) this, itemStack, x, y))) {
                info.cancel();
            }
        }
    }

    @ModifyMethodReturnValue(
            method = "tooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;Lnet/minecraft/resources/Identifier;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;positionTooltip(IIIIII)Lorg/joml/Vector2ic;"))
    private static Vector2ic onTooltipPositioned(Vector2ic position) {
        Events.TooltipPositioned.trigger(position);
        return position;
    }

    @Inject(
            at = @At("TAIL"),
            method = "tooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;Lnet/minecraft/resources/Identifier;)V")
    private void onAfterRenderTooltipInternal(
            Font font,
            List<ClientTooltipComponent> lines,
            int x, int y,
            ClientTooltipPositioner positioner,
            @Nullable Identifier style,
            CallbackInfo info
    ) {
        if (lines.isEmpty()) {
            return;
        }

        if (lines instanceof TaggedArrayList<?,?>) {
            Events.PostRenderTooltip.trigger();
        }
    }
}