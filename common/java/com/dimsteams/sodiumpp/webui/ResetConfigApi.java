package com.dimsteams.sodiumpp.webui;

import com.dimsteams.sodiumpp.modules.utilities.Profiles;

public class ResetConfigApi extends ApiBase {

    @Override
    public String getRoute() {
        return "reset-config";
    }

    @Override
    public String post(String body) throws Throwable {
        String[] errors = Profiles.instance.reset();
        return gson.toJson(errors);
    }
}