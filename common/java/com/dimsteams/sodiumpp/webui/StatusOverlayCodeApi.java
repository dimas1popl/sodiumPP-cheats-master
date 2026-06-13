package com.dimsteams.sodiumpp.webui;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.modules.scripting.StatusOverlay;
import com.dimsteams.sodiumpp.controllers.ScriptsController;
import com.dimsteams.scripting.compiler.CompilationResult;

public class StatusOverlayCodeApi extends CodeApiBase<Runnable> {

    @Override
    public String getRoute() {
        return "status-overlay-code";
    }

    @Override
    protected CompilationResult compile(String code) {
        return ScriptsController.instance.compileOverlay(code);
    }

    @Override
    protected void setCode(String code) {
        ConfigStore.instance.getConfig().statusOverlayConfig.code = code;
    }

    @Override
    protected void setProgram(Runnable program) {
        StatusOverlay.instance.setScript(program);
    }
}