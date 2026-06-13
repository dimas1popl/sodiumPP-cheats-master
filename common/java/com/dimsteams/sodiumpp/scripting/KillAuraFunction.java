package com.dimsteams.sodiumpp.scripting;

@FunctionalInterface
public interface KillAuraFunction {
    boolean shouldAttack(int id);
}