package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.chunkoverlays.ExplorationMiniMapChunkOverlay;
import com.dimsteams.sodiumpp.controllers.ChunkOverlayController;
import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;

public class ExplorationMiniMapApi {

    @ApiVisibility(ApiType.UPDATE)
    public void addMarker() {
        ChunkOverlayController.instance.ofType(ExplorationMiniMapChunkOverlay.class).addMarker();
    }
}