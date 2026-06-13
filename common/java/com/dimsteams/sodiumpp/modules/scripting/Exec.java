package com.dimsteams.sodiumpp.modules.scripting;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.common.events.SendChatEvent;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.controllers.ScriptsController;
import com.dimsteams.sodiumpp.modules.Module;
import com.dimsteams.sodiumpp.scripting.AsyncRunnable;
import com.dimsteams.scripting.DiagnosticMessage;
import com.dimsteams.scripting.compiler.CompilationResult;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public class Exec implements Module {

    public static final Exec instance = new Exec();

    private Exec() {
        Events.SendChat.add(this::onSendChat);
    }

    private void onSendChat(SendChatEvent event) {
        if (!ConfigStore.instance.getConfig().execConfig.enabled) {
            return;
        }

        if (event.getMessage().startsWith(".")) {
            try {
                CompilationResult result = ScriptsController.instance.compileKeys(event.getMessage().substring(1));
                if (result.getProgram() != null) {
                    result.<AsyncRunnable>getProgram().run();
                    systemMessage("OK", 0xFF80FF80);
                } else {
                    for (DiagnosticMessage message : result.getDiagnostics()) {
                        systemMessage(message.message, 0xFFFF8080);
                    }
                }
            }
            catch (Throwable e) {
                systemMessage(e.getMessage(), 0xFFFF8080);
            }

            event.cancel();
        }
    }

    private void systemMessage(String message, int color) {
        Minecraft.getInstance().getChatListener().handleSystemMessage(Component.literal(message).withStyle(Style.EMPTY.withColor(color)), false);
    }
}