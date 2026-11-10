package com.thenextelement.qmcore.mixin.mixins.late.etfuturum;


import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.thenextelement.qmcore.common.utils.UnloadedChunkGuard;

/** Keeps Et Futurum's geode generator from loading neighbouring chunks during population. See UnloadedChunkGuard. */
@Mixin(
    targets = {
        "ganymedes01.etfuturum.world.generate.feature.WorldGenGeode"
    },
    remap = false)
public abstract class MixinWorldGenGeode_UnloadedChunkScope {

    @Inject(method = "func_76484_a", at = @At("HEAD"), remap = false)
    private void qmcore$enterGuard(net.minecraft.world.World world, Random rand, int x, int y, int z,
        CallbackInfoReturnable<Boolean> cir) {
        UnloadedChunkGuard.enter();
    }

    @Inject(method = "func_76484_a", at = @At("RETURN"), remap = false)
    private void qmcore$exitGuard(net.minecraft.world.World world, Random rand, int x, int y, int z,
        CallbackInfoReturnable<Boolean> cir) {
        UnloadedChunkGuard.exit();
    }
}
