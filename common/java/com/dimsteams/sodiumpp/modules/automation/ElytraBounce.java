package com.dimsteams.sodiumpp.modules.automation;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.configs.ElytraBounceConfig;
import com.dimsteams.sodiumpp.modules.Module;
import net.minecraft.client.Minecraft;

public class ElytraBounce implements Module {

    public static final ElytraBounce instance = new ElytraBounce();

    private final Minecraft mc = Minecraft.getInstance();
    private boolean enabled;
    private boolean wasFallFlying;
    private State state;
    private int remainingTicks;

    private ElytraBounce() {
        state = State.NONE;

        Events.BeforePlayerAiStep.add(this::onPlayerAiStart);
        Events.ModifyPlayerInput.add(this::onModifyPlayerInput);
        Events.AfterPlayerAiStep.add(this::onPlayerAiEnd);
        Events.ClientPlayerLoggingOut.add(this::onLoggingOut);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    private void onLoggingOut() {
        state = State.NONE;
    }

    private void onPlayerAiStart() {
        assert mc.player != null;

        wasFallFlying = mc.player.isFallFlying();
    }

    private void onModifyPlayerInput() {
        if (mc.player == null || !enabled) {
            return;
        }

        switch (state) {
            case FIRST_JUMP -> {
                mc.player.input.makeJump();
                if (--remainingTicks == 0) {
                    state = State.JUMP_DELAY;
                    remainingTicks = getConfig().betweenJumpsTicks;
                }
            }
            case JUMP_DELAY -> {
                if (--remainingTicks == 0) {
                    state = State.SECOND_JUMP;
                    remainingTicks = getConfig().secondJumpTicks;
                }
            }
            case SECOND_JUMP -> {
                mc.player.input.makeJump();
                if (--remainingTicks == 0) {
                    state = State.NONE;
                }
            }
        }
    }

    private void onPlayerAiEnd() {
        if (!enabled) {
            state = State.NONE;
            return;
        }

        assert mc.player != null;

        if (state == State.NONE && wasFallFlying && mc.player.onGround()) {
            state = State.FIRST_JUMP;
            remainingTicks = getConfig().firstJumpTicks;
        }
    }

    private ElytraBounceConfig getConfig() {
        return ConfigStore.instance.getConfig().elytraBounceConfig;
    }

    private enum State {
        NONE,
        FIRST_JUMP,
        JUMP_DELAY,
        SECOND_JUMP,
    }
}