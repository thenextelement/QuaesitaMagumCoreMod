package com.thenextelement.qmcore.mixin.mixins.late.etfuturum;

import java.util.Random;

import com.thenextelement.qmcore.common.utils.EfrChunkEdge;

import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Stops Et Futurum's cave vines from reading across a chunk border during population. See {@link EfrChunkEdge}. */
@Mixin(
    targets = "ganymedes01.etfuturum.world.generate.decorate.WorldGenCaveVines",
    remap = false)
public abstract class MixinWorldGenCaveVines_ChunkEdge {

    @Inject(method = "func_76484_a", at = @At("HEAD"), cancellable = true, remap = false)
    private void qmcore$skipChunkBorder(World world, Random rand, int x, int y, int z,
        CallbackInfoReturnable<Boolean> cir) {
        if (EfrChunkEdge.onChunkBorder(x, z)) cir.setReturnValue(false);
    }
}
