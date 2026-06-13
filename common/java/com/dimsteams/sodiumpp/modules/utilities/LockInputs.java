package com.dimsteams.sodiumpp.modules.utilities;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.common.events.PlayerTurnByMouseEvent;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class LockInputs {

    public static final LockInputs instance = new LockInputs();

    private LockInputs() {
        Events.PlayerTurnByMouse.add(this::onPlayerTurnByMouse);
    }

    private void onPlayerTurnByMouse(PlayerTurnByMouseEvent event) {
        if (ConfigStore.instance.getConfig().lockInputsConfig.mouseInputDisabled) {
            event.cancel();
        }
    }
}