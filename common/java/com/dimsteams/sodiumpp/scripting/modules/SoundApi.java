package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.sound.ExternalFileSoundInstance;
import com.dimsteams.sodiumpp.sound.SoundLibrary;
import com.dimsteams.sodiumpp.utils.MathUtils;
import com.dimsteams.scripting.MethodDescription;
import net.minecraft.client.Minecraft;

@SuppressWarnings("unused")
public class SoundApi {

    @MethodDescription("Only .ogg files are supported")
    public boolean play(String filename) {
        return play(filename, 1);
    }

    @MethodDescription("Only .ogg files are supported")
    public boolean play(String filename, double volume) {
        ExternalFileSoundInstance instance = SoundLibrary.get(filename);
        if (instance == null) {
            return false;
        }

        instance.setVolume((float) MathUtils.clamp(volume, 0, 1));
        Minecraft.getInstance().getSoundManager().play(instance);
        return true;
    }

    @MethodDescription("When sound.play(...) returns false, you can get error text by calling this method")
    public String getLastError() {
        String error = SoundLibrary.getLastError();
        return error == null ? "" : error;
    }
}