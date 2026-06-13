package com.dimsteams.sodiumpp.webui;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.controllers.ScriptsController;
import com.dimsteams.sodiumpp.modules.automation.VillagerRoller;
import com.dimsteams.scripting.compiler.CompilationResult;

public class VillagerRollerCodeApi extends CodeApiBase<Runnable> {

    @Override
    public String getRoute() {
        return "villager-roller-code";
    }

    @Override
    protected CompilationResult compile(String code) {
        return ScriptsController.instance.compileVillagerRoller(code);
    }

    @Override
    protected void setCode(String code) {
        ConfigStore.instance.getConfig().villagerRollerConfig.code = code;
    }

    @Override
    protected void setProgram(Runnable program) {
        VillagerRoller.instance.setScript(program);
    }
}