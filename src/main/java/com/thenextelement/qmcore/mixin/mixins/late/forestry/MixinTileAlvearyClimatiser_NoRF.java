package com.thenextelement.qmcore.mixin.mixins.late.forestry;

import net.minecraftforge.common.util.ForgeDirection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import forestry.api.core.IClimateControlled;
import forestry.apiculture.multiblock.TileAlvearyClimatiser;

/**
 * The RF Alveary parts, the Fan and the Heater (the only TileAlvearyClimatiser subclasses). They refuse energy and
 * never change the alveary's climate, even with energy stored from an old save. The other Alveary parts take no RF and
 * are untouched.
 */
@Mixin(value = TileAlvearyClimatiser.class, remap = false)
public abstract class MixinTileAlvearyClimatiser_NoRF {

    @Shadow(remap = false)
    public abstract void setActive(boolean active);

    @Inject(method = "changeClimate", at = @At("HEAD"), cancellable = true, remap = false, require = 1)
    private void qmcore$noClimateChange(int tick, IClimateControlled climateControlled, CallbackInfo ci) {
        setActive(false);
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
