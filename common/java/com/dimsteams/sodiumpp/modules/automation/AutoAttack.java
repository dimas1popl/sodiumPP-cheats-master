package com.dimsteams.sodiumpp.modules.automation;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.configs.AutoAttackConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.mixins.common.accessors.MinecraftAccessor;
import com.dimsteams.sodiumpp.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.HitResult;

public class AutoAttack implements Module {

    public static final AutoAttack instance = new AutoAttack();

    private final Minecraft mc = Minecraft.getInstance();
    private int nextExtraTicks = Integer.MIN_VALUE;

    private AutoAttack() {
        Events.ClientTickEnd.add(this::onClientTickEnd);
    }

    private void onClientTickEnd() {
        if (mc.player == null || mc.gameMode == null) {
            return;
        }

        AutoAttackConfig config = ConfigStore.instance.getConfig().autoAttackConfig;
        if (!config.enabled) {
            return;
        }

        if (!mc.options.keyAttack.isDown()) {
            return;
        }

        if (mc.hitResult == null) {
            return;
        }

        if (mc.hitResult.getType() != HitResult.Type.ENTITY) {
            return;
        }

        calculateNextExtraTicksIfRequired(config);

        if (mc.player.getAttackStrengthScale(-nextExtraTicks) != 1) {
            return;
        }

        if (config.limitRange && mc.hitResult.getLocation().distanceToSqr(mc.player.getEyePosition()) > config.maxRange * config.maxRange) {
            return;
        }

        nextExtraTicks = Integer.MIN_VALUE;

        ((MinecraftAccessor) mc).startAttack_CU();
    }

    private void calculateNextExtraTicksIfRequired(AutoAttackConfig config) {
        if (nextExtraTicks == Integer.MIN_VALUE) {
            nextExtraTicks = config.extraTicksMin + (int) Math.floor(Math.random() * (config.extraTicksMax - config.extraTicksMin + 1));
        }
    }
}