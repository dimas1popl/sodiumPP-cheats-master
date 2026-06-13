package com.dimsteams.sodiumpp.mixins.common;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.UserNameConfig;
import net.minecraft.client.User;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(User.class)
public abstract class MixinUser {

    @Inject(at = @At("HEAD"), method = "getName", cancellable = true)
    private void onGetName(CallbackInfoReturnable<String> info) {
        UserNameConfig config = ConfigStore.instance.getConfig().userNameConfig;
        if (config.enabled && config.name != null && !config.name.isEmpty()) {
            info.setReturnValue(config.name);
        }
    }
}