package com.thenextelement.qmcore.mixin.mixins.late.binnie;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import binnie.genetics.nei.NEIGeneticsConfig;

/**
 * Skips Genetics' NEI recipe tabs (Isolator, Sequencer, Polymeriser, Inoculator, Analyser, Incubator, Genepool,
 * Acclimatiser, Splicer, gene database). Every machine they describe is disabled and hidden.
 */
@Mixin(value = NEIGeneticsConfig.class, remap = false)
public abstract class MixinNEIGeneticsConfig_Disable {

    @Inject(method = "loadConfig", at = @At("HEAD"), cancellable = true, remap = false, require = 1)
    private void qmcore$skipHandlers(CallbackInfo ci) {
        ci.cancel();
    }
}
