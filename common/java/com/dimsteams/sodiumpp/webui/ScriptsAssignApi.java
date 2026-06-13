package com.dimsteams.sodiumpp.webui;

import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.controllers.KeyBindingsController;

public class ScriptsAssignApi extends ApiBase {

    @Override
    public String getRoute() {
        return "keybinding-scripts-assign";
    }

    @Override
    public String put(String id, String body) {
        int index = gson.fromJson(body, int.class);
        KeyBindingsController.instance.assign(index, id);
        ConfigStore.instance.requestWrite();
        return "true";
    }
}