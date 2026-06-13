package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.concurrent.TickEndExecutor;
import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;
import com.dimsteams.scripting.MethodDescription;

public class DelayedApi {

    @MethodDescription("""
            Runs actions after some amount of ticks passed.
            Action may run while no world is loaded
            """)
    @ApiVisibility(ApiType.ACTION)
    public void run(int ticks, Runnable action) {
        if (ticks <= 0) {
            return;
        }

        TickEndExecutor.instance.waitTicks(ticks, action);
    }
}