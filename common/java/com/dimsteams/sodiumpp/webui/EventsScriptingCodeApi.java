package com.dimsteams.sodiumpp.webui;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.controllers.ScriptsController;
import com.dimsteams.sodiumpp.modules.scripting.EventsScripting;
import com.dimsteams.scripting.compiler.CompilationResult;

public class EventsScriptingCodeApi extends CodeApiBase<Runnable> {

    @Override
    public String getRoute() {
        return "events-scripting-code";
    }

    @Override
    protected CompilationResult compile(String code) {
        return ScriptsController.instance.compileEvents(code);
    }

    @Override
    protected void setCode(String code) {
        ConfigStore.instance.getConfig().eventsScriptingConfig.code = code;
    }

    @Override
    protected void setProgram(Runnable program) {
        EventsScripting.instance.setScript(program);
    }
}