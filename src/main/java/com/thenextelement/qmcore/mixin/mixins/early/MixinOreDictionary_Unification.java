package com.thenextelement.qmcore.mixin.mixins.early;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.thenextelement.qmcore.common.unification.OreUnification;

/**
 * Keeps unified-away items out of the ore dictionary entirely: their registration is skipped, so no ore recipe ever
 * accepts or picks them. Every registerOre overload funnels through registerOreImpl.
 */
@Mixin(value = OreDictionary.class, remap = false)
public class MixinOreDictionary_Unification {

    @Inject(method = "registerOreImpl", at = @At("HEAD"), cancellable = true)
    private static void qmcore$skipUnifiedItems(String name, ItemStack ore, CallbackInfo ci) {
        if (OreUnification.shouldBlockRegistration(name, ore)) ci.cancel();
    }
}
