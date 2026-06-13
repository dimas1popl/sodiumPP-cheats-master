package com.dimsteams.sodiumpp.font;

import com.dimsteams.sodiumpp.utils.GlobalTicks;

public abstract class FontBackend {

    protected long lastUsed;

    protected FontBackend() {
        markUse();
    }

    public abstract FontRenderer createFontRenderer(FontRenderDetails details);

    protected boolean isStale() {
        return (GlobalTicks.get() - lastUsed) > 10 * 60 * 20; // 10 minutes
    }

    protected void markUse() {
        lastUsed = GlobalTicks.get();
    }
}