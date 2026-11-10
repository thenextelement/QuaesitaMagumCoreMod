package com.thenextelement.qmcore.mixin.mixins.late.forestry;

import net.minecraftforge.common.util.ForgeDirection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import forestry.farming.tiles.TileFarmGearbox;

/**
 * The Multifarm's Gearbox is its RF input. It refuses energy and never drives the farm, even with energy stored from an
 * old save. The Farming module stays on (its other blocks, farm logic and circuits are left alone), but with no
 * gearbox a Multifarm does no work.
 */
@Mixin(value = TileFarmGearbox.class, remap = false)
public abstract class MixinTileFarmGearbox_NoRF {

    @Inject(method = "updateServer", at = @At("HEAD"), cancellable = true, remap = false, require = 1)
    private void qmcore$noWork(int tickCount, CallbackInfo ci) {
        ci.cancel();
    }

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
