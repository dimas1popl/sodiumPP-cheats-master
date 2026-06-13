package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.modules.scripting.Debugging;

public class DebugApi {
    public void write(String message) {
        Debugging.instance.addMessage(message);
    }
}