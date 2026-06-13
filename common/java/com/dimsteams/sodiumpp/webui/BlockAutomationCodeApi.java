package com.dimsteams.sodiumpp.webui;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.controllers.ScriptsController;
import com.dimsteams.sodiumpp.modules.scripting.BlockAutomation;
import com.dimsteams.sodiumpp.scripting.events.BlockPosConsumer;
import com.dimsteams.scripting.compiler.CompilationResult;

public class BlockAutomationCodeApi extends CodeApiBase<BlockPosConsumer> {

    @Override
    public String getRoute() {
        return "block-automation-code";
    }

    @Override
    protected CompilationResult compile(String code) {
        return ScriptsController.instance.compileBlockAutomation(code);
    }

    @Override
    protected void setCode(String code) {
        ConfigStore.instance.getConfig().blockAutomationConfig.code = code;
    }

    @Override
    protected void setProgram(BlockPosConsumer program) {
        BlockAutomation.instance.setScript(program);
    }
}