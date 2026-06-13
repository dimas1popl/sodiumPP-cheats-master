package com.dimsteams.sodiumpp.font;

public class VanillaFontBackend extends FontBackend {

    protected VanillaFontBackend() {}

    @Override
    public FontRenderer createFontRenderer(FontRenderDetails details) {
        return new VanillaFontRenderer(this, details.scale(), details.dropShadow());
    }
}