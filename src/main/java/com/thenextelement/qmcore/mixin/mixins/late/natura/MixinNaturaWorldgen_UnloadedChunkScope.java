package com.thenextelement.qmcore.mixin.mixins.late.natura;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.thenextelement.qmcore.common.utils.UnloadedChunkGuard;

import mods.natura.worldgen.BerryBushGen;
import mods.natura.worldgen.BloodTreeLargeGen;
import mods.natura.worldgen.BushTreeGen;
import mods.natura.worldgen.DarkwoodGen;
import mods.natura.worldgen.EucalyptusTreeGenShort;
import mods.natura.worldgen.FlowerGen;
import mods.natura.worldgen.FusewoodGen;
import mods.natura.worldgen.GlowshroomGenBlueGreen;
import mods.natura.worldgen.GlowshroomGenPurple;
import mods.natura.worldgen.NetherBerryBushGen;
import mods.natura.worldgen.RareTreeGen;
import mods.natura.worldgen.RedwoodTreeGen;
import mods.natura.worldgen.SaguaroGen;
import mods.natura.worldgen.SakuraTreeGen;
import mods.natura.worldgen.WhiteTreeGen;
import mods.natura.worldgen.WillowGen;

/** Keeps Natura's tree, bush and plant generators from loading neighbouring chunks during population. See UnloadedChunkGuard. */
@Mixin(
    value = { BerryBushGen.class, BloodTreeLargeGen.class, BushTreeGen.class, DarkwoodGen.class, EucalyptusTreeGenShort.class, FlowerGen.class, FusewoodGen.class, GlowshroomGenBlueGreen.class, GlowshroomGenPurple.class, NetherBerryBushGen.class, RareTreeGen.class, RedwoodTreeGen.class, SaguaroGen.class, SakuraTreeGen.class, WhiteTreeGen.class, WillowGen.class },
    remap = false)
public abstract class MixinNaturaWorldgen_UnloadedChunkScope {

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
