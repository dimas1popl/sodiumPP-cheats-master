package com.dimsteams.sodiumpp.configs.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.dimsteams.sodiumpp.common.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.io.IOException;

public class BlockTypeAdapter extends TypeAdapter<Block> {

    @Override
    public void write(JsonWriter out, Block block) throws IOException {
        if (block == null) {
            out.nullValue();
        } else {
            out.value(Registries.BLOCKS.getKey(block).toString());
        }
    }

    @Override
    public Block read(JsonReader in) throws IOException {
        String value = in.nextString();
        if (value == null) {
            return null;
        } else {
            return Registries.BLOCKS.getValue(Identifier.parse(value));
        }
    }
}