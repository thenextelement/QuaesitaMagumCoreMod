package com.thenextelement.qmcore.mixin.mixins.late.binnie;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import binnie.core.machines.power.ComponentPowerReceptor;
import binnie.core.machines.power.PowerInterface;

/**
 * Binnie's single RF input component, used by exactly the RF machines in DisabledTech.BINNIE_RF_PACKAGES (Genetics'
 * machines, the Extra Bees Stimulator and Transmission, the Extra Trees Lumbermill). It refuses energy, conduits do not
 * connect, and energy saved in an old world is emptied on load, so none of those machines can run. Non-RF Binnie
 * machines (compartments, the Extra Trees designer machines, the other Extra Bees alveary parts) never create one.
 * <p>
 * IC2's injectEnergy is left alone: IC2 is not in the pack, and FML strips that @Optional method without it.
 */
@Mixin(value = ComponentPowerReceptor.class, remap = false)
public abstract class MixinComponentPowerReceptor_NoRF {

    @Shadow(remap = false)
    @Final
    private PowerInterface container;

    @Inject(method = "receiveEnergy", at = @At("HEAD"), cancellable = true, remap = false, require = 1)
    private void qmcore$refuseEnergy(ForgeDirection from, int maxReceive, boolean simulate,
        CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }

    @Inject(method = "canConnectEnergy", at = @At("HEAD"), cancellable = true, remap = false, require = 1)
    private void qmcore$noConnection(ForgeDirection from, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(method = "readFromNBT", at = @At("TAIL"), remap = false, require = 1)
    private void qmcore$drainSavedEnergy(NBTTagCompound nbt, CallbackInfo ci) {
        // Binnie's flag is inverted: useEnergy(amount, true) is the call that actually removes the energy
        container.useEnergy(container.getEnergy(), true);
    }
}
