package com.dimsteams.sodiumpp.mixins.common.schematics;

import com.dimsteams.sodiumpp.modules.automation.Schematica;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.renderer.SectionOcclusionGraph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SectionOcclusionGraph.class)
public abstract class MixinSectionOcclusionGraph {

    @Redirect(
            method = "runUpdates",
            at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/longs/LongOpenHashSet;contains(J)Z"))
    private boolean onCheckIfNodeBelongToEmptySectionsSet(LongOpenHashSet set, long index) {
        if (!set.contains(index)) {
            return false;
        }
        return !Schematica.instance.hasBlocksAtSection(index);
    }
}