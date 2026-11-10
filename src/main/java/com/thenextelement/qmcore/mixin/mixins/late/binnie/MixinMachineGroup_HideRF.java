package com.thenextelement.qmcore.mixin.mixins.late.binnie;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.thenextelement.qmcore.common.tech.DisabledTech;

import binnie.core.machines.MachineGroup;
import binnie.core.machines.MachinePackage;

/**
 * Marks Binnie's RF machine packages (and the Genetics Laboratory) inactive as each machine group is built. An inactive
 * package keeps its block meta and tile entity, so saves and Binnie's cross-mod lookups are unaffected, but
 * BlockMachine leaves it out of the creative tab and NEI.
 * <p>
 * The MachineGroup constructor calls {@code pack.setActive(true)} just before {@code addPackage(pack)}, so this runs
 * after that call and wins.
 */
@Mixin(value = MachineGroup.class, remap = false)
public abstract class MixinMachineGroup_HideRF {

    @Inject(method = "addPackage", at = @At("HEAD"), remap = false, require = 1)
    private void qmcore$deactivateRF(MachinePackage pack, CallbackInfo ci) {
        if (DisabledTech.BINNIE_HIDDEN_PACKAGES.contains(
            pack.getClass()
                .getName())) {
            pack.setActive(false);
        }
    }
}
