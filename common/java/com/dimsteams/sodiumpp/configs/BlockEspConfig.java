package com.dimsteams.sodiumpp.configs;

import com.dimsteams.sodiumpp.collections.ImmutableList;
import com.dimsteams.sodiumpp.configs.adapters.GsonSkip;
import com.dimsteams.sodiumpp.scripting.events.BlockEspConsumer;
import net.minecraft.world.level.block.Block;

import java.awt.*;

public class BlockEspConfig extends EspConfigBase {

    // array is sorted by block id during json deserialization
    public ImmutableList<Block> blocks;

    public boolean drawOverlay;
    public Color overlayColor;

    public boolean scriptEnabled;
    public String code;

    @GsonSkip
    public BlockEspConsumer script;

    public void copyFrom(BlockEspConfig jsonConfig) {
        copyFromJsonTracerConfigBase(jsonConfig);

        drawOverlay = jsonConfig.drawOverlay;
        overlayColor = jsonConfig.overlayColor;

        scriptEnabled = jsonConfig.scriptEnabled;
        code = jsonConfig.code;
    }

    public static BlockEspConfig createDefault(ImmutableList<Block> blocks) {
        BlockEspConfig config = new BlockEspConfig();
        config.blocks = blocks;
        config.enabled = false;

        config.drawTracers = true;
        config.tracerColor = Color.WHITE;

        config.drawOutline = true;
        config.outlineColor = Color.WHITE;

        config.drawOverlay = false;
        config.overlayColor = new Color(0x80FFFFFF, true);

        config.maxDistance = DefaultMaxDistance;
        return config;
    }
}