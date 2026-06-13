package com.dimsteams.sodiumpp.font;

public record FontRenderDetails(
        float letterSpacing,
        float lineSpacing,
        boolean dropShadow,
        int shadowOffsetX,
        int shadowOffsetY,
        int scale
) {}