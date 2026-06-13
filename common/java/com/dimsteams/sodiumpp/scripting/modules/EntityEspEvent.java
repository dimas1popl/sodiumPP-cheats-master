package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.font.StylizedText;
import com.dimsteams.sodiumpp.modules.esp.EntityEsp;
import com.dimsteams.sodiumpp.utils.ColorUtils;
import com.dimsteams.scripting.MethodDescription;
import com.dimsteams.scripting.type.CustomType;

@SuppressWarnings("unused")
@CustomType(name = "EntityEspEvent")
public class EntityEspEvent {

    private final EntityEsp.EntityScriptResult result;

    public EntityEspEvent(EntityEsp.EntityScriptResult result) {
        this.result = result;
    }

    @MethodDescription("""
            Overrides tracer color for current entity
            """)
    public void setTracerColor(String color) {
        result.tracerColorOverride = ColorUtils.parseColor(color);
    }

    @MethodDescription("""
            Disables tracer for current entity
            """)
    public void disableTracer() {
        result.tracerDisabled = true;
    }

    @MethodDescription("""
            Disables outline for current entity
            """)
    public void disableOutline() {
        result.outlineDisabled = true;
    }

    @MethodDescription("""
            Disables overlay for current entity
            """)
    public void disableOverlay() {
        result.overlayDisabled = true;
    }

    @MethodDescription("""
            Disables collision box for current entity
            """)
    public void disableCollisionBox() {
        result.collisionBoxDisabled = true;
    }

    @MethodDescription("""
            Overrides title displayed above the entity. Works only with sodiumpp title system
            """)
    public void setTitle(String title) {
        result.title = StylizedText.of(title);
    }

    @MethodDescription("""
            Overrides title displayed above the entity. Works only with sodiumpp title system.
            Array length must be divisible by 2. Example: [color1, text1, color2, text2]
            """)
    public void setTitle(String[] parameters) {
        result.title = StylizedText.createSafe(parameters);
    }
}