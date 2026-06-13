package com.dimsteams.sodiumpp.mixins.common;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.common.events.ContainerScreenCalculateHoveredSlotEvent;
import com.dimsteams.sodiumpp.common.events.ContainerScreenRenderEvent;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.ContainerButtonsConfig;
import com.dimsteams.sodiumpp.controllers.ContainerButtonsController;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class MixinAbstractContainerScreen<T extends AbstractContainerMenu> extends Screen {

    @Shadow
    protected int topPos;

    @Shadow
    protected int leftPos;

    @Final
    @Shadow
    protected int imageWidth;

    @Final
    @Shadow
    protected T menu;

    protected MixinAbstractContainerScreen(Component component) {
        super(component);
    }

    @Inject(at = @At("TAIL"), method = "init()V")
    private void onInit(CallbackInfo info) {
        Screen self = this;
        if (!ContainerButtonsController.instance.isValidScreen(self)) {
            return;
        }

        ContainerButtonsConfig config = ConfigStore.instance.getConfig().containerButtonsConfig;
        int cursor = this.leftPos + this.imageWidth;
        int space = 4;
        if (config.showTakeAll) {
            int btnWidth = 72;
            int btnHeight = 20;
            cursor -= btnWidth;
            addRenderableWidget(
                    new Button.Builder(Component.translatable("button.take.all"), this::onTakeAllPress_CU)
                            .bounds(cursor, this.topPos - btnHeight, btnWidth, btnHeight)
                            .build());
            cursor -= space;
        }
        if (config.showSmartPut) {
            int btnWidth = 72;
            int btnHeight = 20;
            cursor -= btnWidth;
            addRenderableWidget(
                    new Button.Builder(Component.translatable("button.smart.put"), this::onSmartPutPress_CU)
                            .bounds(cursor, this.topPos - btnHeight, btnWidth, btnHeight)
                            .build());
            cursor -= space;
        }
        if (config.showDropAll) {
            int btnWidth = 72;
            int btnHeight = 20;
            cursor -= btnWidth;
            addRenderableWidget(
                    new Button.Builder(Component.translatable("button.drop.all"), this::onDropAllPress_CU)
                            .bounds(cursor, this.topPos - btnHeight, btnWidth, btnHeight)
                            .build());
        }
    }

    @Inject(at = @At("TAIL"), method = "extractContents")
    private void onAfterRenderContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        Events.ContainerScreenAfterRenderContents.trigger(
                new ContainerScreenRenderEvent(
                        (AbstractContainerScreen<?>) (Object) this,
                        graphics,
                        leftPos,
                        topPos,
                        imageWidth));
    }

    @Unique
    private void onTakeAllPress_CU(Button button) {
        ContainerButtonsController.instance.takeAll(false);
    }

    @Unique
    private void onSmartPutPress_CU(Button button) {
        ContainerButtonsController.instance.smartPut();
    }

    @Unique
    private void onDropAllPress_CU(Button button) {
        ContainerButtonsController.instance.dropAll(false);
    }

    @Inject(at = @At("HEAD"), method = "getHoveredSlot", cancellable = true)
    private void onGetHoveredSlot(double x, double y, CallbackInfoReturnable<Slot> info) {
        ContainerScreenCalculateHoveredSlotEvent event = new ContainerScreenCalculateHoveredSlotEvent();
        if (Events.ContainerCalculateHoveredSlot.trigger(event)) {
            info.setReturnValue(event.getSlot());
        }
    }
}