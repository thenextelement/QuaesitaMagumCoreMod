package com.thenextelement.qmcore.mixin.mixins.late.forestry;

import net.minecraftforge.common.util.ForgeDirection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import forestry.core.tiles.TilePowered;

/**
 * Forestry's RF machine base: with Factory off, the Analyzer is the only one left in the pack, but this also covers a
 * Factory machine carried over in an old save. Energy is refused and conduits do not connect.
 */
@Mixin(value = TilePowered.class, remap = false)
public abstract class MixinTilePowered_NoRF {

    @Inject(method = "receiveEnergy", at = @At("HEAD"), cancellable = true, remap = false, require = 1)
    private void qmcore$refuseEnergy(ForgeDirection from, int maxReceive, boolean simulate,
        CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }

    @Inject(method = "canConnectEnergy", at = @At("HEAD"), cancellable = true, remap = false, require = 1)
    private void qmcore$noConnection(ForgeDirection from, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
