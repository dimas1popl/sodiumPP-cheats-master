package com.dimsteams.sodiumpp.mixins.common;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.modules.automation.VillagerRoller;
import com.dimsteams.sodiumpp.modules.esp.EntityEsp;
import com.dimsteams.sodiumpp.modules.scripting.BlockAutomation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Shadow
    public LocalPlayer player;

    @Shadow
    public ClientLevel level;

    @Shadow
    protected abstract void continueAttack(boolean p_91387_);

    @Shadow
    public abstract boolean isGameLoadFinished();

    //Start Attack method, event triggers ==================================
    @Inject(at = @At("HEAD"), method = "startAttack")
    private void onBeforeStartAttack(CallbackInfoReturnable<Boolean> cir) {
        Events.BeforeStartAttack.trigger();
    }

    @Inject(at = @At("RETURN"), method = "startAttack")
    private void onAfterStartAttack(CallbackInfoReturnable<Boolean> cir) {
        Events.AfterStartAttack.trigger();
    }
    //========================================================================

    @Inject(at = @At("HEAD"), method = "shouldEntityAppearGlowing(Lnet/minecraft/world/entity/Entity;)Z", cancellable = true)
    public void onShouldEntityAppearGlowing(Entity entity, CallbackInfoReturnable<Boolean> info) {
        if (EntityEsp.instance.shouldEntityGlow(entity)) {
            info.setReturnValue(true);
            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "close()V")
    private void onClose(CallbackInfo info) {
        Events.Close.trigger();
    }

    @Inject(at = @At("HEAD"), method = "handleKeybinds()V")
    private void onBeforeHandleKeyBindings(CallbackInfo info) {
        Events.BeforeHandleKeyBindings.trigger();
    }

    @Inject(at = @At("TAIL"), method = "handleKeybinds()V")
    private void onAfterHandleKeyBindings(CallbackInfo info) {
        Events.AfterHandleKeyBindings.trigger();
    }

    @Inject(at = @At("RETURN"), method = "createTitle()Ljava/lang/String;", cancellable = true)
    private void onCreateTitle(CallbackInfoReturnable<String> info) {
        if (ConfigStore.instance.getConfig().userNameConfig.showNameInTitle) {
            info.setReturnValue(Minecraft.getInstance().getUser().getName() + " - " + info.getReturnValue());
        }
    }


    @Inject(at = @At("HEAD"), method = "tick()V")
    private void onBeforeTick(CallbackInfo info) {
        if (this.isGameLoadFinished()) {
            Events.ClientTickStart.trigger();
        }
    }

    @Inject(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;tickEntities()V"))
    private void onBeforeTickEntities(CallbackInfo info) {
        if (this.player != null) {
            Events.BeforeEntitiesTick.trigger();
        }
    }

    @Inject(at = @At("TAIL"), method = "tick()V")
    private void onAfterTick(CallbackInfo info) {
        if (this.isGameLoadFinished()) {
            Events.ClientTickEnd.trigger();
        }
    }

    @Inject(
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;resetData()V", shift = At.Shift.AFTER),
            method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;ZZ)V")
    private void onPlayerLoggingOut(Screen screen, boolean b1, boolean b2, CallbackInfo info) {
        Events.ClientPlayerLoggingOut.trigger();
    }

    @Unique
    private boolean triggerDimensionChange_CU;

    @Inject(at = @At("HEAD"), method = "setLevel")
    private void onBeforeSetLevel(ClientLevel level, CallbackInfo info) {
        if (this.level != null) {
            Events.LevelUnload.trigger();
            triggerDimensionChange_CU = true;
        }
    }

    @Inject(at = @At("TAIL"), method = "setLevel")
    private void onAfterSetLevel(ClientLevel level, CallbackInfo info) {
        if (triggerDimensionChange_CU) {
            Events.DimensionChange.trigger();
        }
    }

    @Inject(
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;onDisconnected()V", shift = At.Shift.AFTER),
            method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;ZZ)V")
    private void onClearDisconnect(Screen screen, boolean keepResourcePacks, boolean stopSounds, CallbackInfo info) {
        if (this.level != null) {
            Events.LevelUnload.trigger();
        }
    }

    @Inject(
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;onDisconnected()V"),
            method = "clearClientLevel")
    private void onClearClientLevel(Screen screen, CallbackInfo info) {
        if (this.level != null) {
            Events.LevelUnload.trigger();
        }
    }

    @Redirect(
            method = "handleKeybinds",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;continueAttack(Z)V"))
    private void onShouldContinueAttack(Minecraft instance, boolean value) {
        if (VillagerRoller.instance.isBreakingBlock()) {
            return;
        }

        if (BlockAutomation.instance.isBreakingBlock()) {
            return;
        }

        this.continueAttack(value);
    }





    @Inject(at = @At(value = "TAIL"), method = "resizeGui()V")
    private void onResize(CallbackInfo info) {
        Events.WindowResize.trigger();
    }


    @Inject(method = "pick", at = @At("HEAD"))
    private void onBeforePick(float partialTicks, CallbackInfo info) {
        Events.OnBeforePick.trigger();
    }

    @Inject(method = "pick", at = @At("TAIL"))
    private void onAfterPick(float partialTicks, CallbackInfo info) {
        Events.OnAfterPick.trigger();
    }
}