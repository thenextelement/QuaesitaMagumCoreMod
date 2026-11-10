package com.thenextelement.qmcore.mixin.mixins.late.forestry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import forestry.core.tiles.TileAnalyzer;

/**
 * The Analyzer never has work, so it spends no energy left over from an old save and analyses nothing. Players use the
 * handheld Beealyzer/Treealyzer/Flutterlyzer instead.
 */
@Mixin(value = TileAnalyzer.class, remap = false)
public abstract class MixinTileAnalyzer_NoRF {

    @Inject(method = "hasWork", at = @At("HEAD"), cancellable = true, remap = false, require = 1)
    private void qmcore$noWork(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
