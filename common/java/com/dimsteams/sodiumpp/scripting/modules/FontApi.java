package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.scripting.MethodDescription;
import net.minecraft.client.Minecraft;

@SuppressWarnings("unused")
public class FontApi {

    @MethodDescription("""
            Returns width of string when rendered with default Minecraft font
            """)
    public int getStringWidth(String value) {
        return Minecraft.getInstance().font.width(value);
    }

    @MethodDescription("""
            Returns line height for default Minecraft font
            """)
    public int getLineHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }
}