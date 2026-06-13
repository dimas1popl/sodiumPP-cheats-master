package com.dimsteams.sodiumpp.webui;

import com.dimsteams.sodiumpp.utils.EntityUtils;

public class EntityInfoApi extends ApiBase {

    @Override
    public String getRoute() {
        return "entity-info";
    }

    @Override
    public String get() {
        return gson.toJson(EntityUtils.getEntityClasses());
    }
}