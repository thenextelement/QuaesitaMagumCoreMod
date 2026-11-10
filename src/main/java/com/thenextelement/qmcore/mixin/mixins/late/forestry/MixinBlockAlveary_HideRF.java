package com.thenextelement.qmcore.mixin.mixins.late.forestry;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.thenextelement.qmcore.common.tech.DisabledTech;

import forestry.apiculture.blocks.BlockAlveary;

/** Leaves the Alveary Fan and Heater out of the creative tab and NEI's item list. */
@Mixin(value = BlockAlveary.class, remap = false)
public abstract class MixinBlockAlveary_HideRF {

    @Inject(
        // SRG name in the pack, MCP name in the dev environment; the method has no overloads
        method = { "func_149666_a", "getSubBlocks" },
        at = @At("RETURN"),
        remap = false,
        require = 1)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void qmcore$hideRF(Item item, CreativeTabs tab, List list, CallbackInfo ci) {
        ((List<Object>) list).removeIf(o -> {
            if (!(o instanceof ItemStack stack)) return false;
            int meta = stack.getItemDamage();
            return meta == DisabledTech.FORESTRY_ALVEARY_FAN || meta == DisabledTech.FORESTRY_ALVEARY_HEATER;
        });
    }
}
