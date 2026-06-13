package com.dimsteams.sodiumpp.scripting;

@FunctionalInterface
public interface HitboxSizeFunction {
    boolean shouldApply(int id);
}