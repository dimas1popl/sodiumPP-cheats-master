package com.dimsteams.sodiumpp.webui;

import com.dimsteams.sodiumpp.modules.esp.BlockFinder;

public class RescanChunksApi extends ApiBase {

    @Override
    public String getRoute() {
        return "rescan-chunks";
    }

    @Override
    public String post(String body) {
        BlockFinder.instance.rescan();
        return "{}";
    }
}