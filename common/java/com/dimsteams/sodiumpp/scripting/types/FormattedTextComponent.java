package com.dimsteams.sodiumpp.scripting.types;

import com.dimsteams.scripting.Getter;
import com.dimsteams.scripting.type.CustomType;
import net.minecraft.network.chat.Style;

@CustomType(name = "FormattedTextComponent")
public class FormattedTextComponent {

    private final String text;
    private final Style style;

    public FormattedTextComponent(String text, Style style) {
        this.text = text;
        this.style = style;
    }

    @Getter(name = "text")
    public String getText() {
        return text;
    }

    @Getter(name = "style")
    public StyleWrapper getStyle() {
        return new StyleWrapper(style);
    }
}