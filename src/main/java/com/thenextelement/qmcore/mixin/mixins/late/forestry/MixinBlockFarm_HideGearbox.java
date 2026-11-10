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

import forestry.farming.blocks.BlockFarm;

/**
 * Leaves every Farm Gearbox (one per farm texture, told apart by NBT) out of the creative tab and NEI's item list.
 * NEI's hideItem can miss NBT variants, so they are dropped here instead.
 */
@Mixin(value = BlockFarm.class, remap = false)
public abstract class MixinBlockFarm_HideGearbox {

    @Inject(
        // SRG name in the pack, MCP name in the dev environment; the method has no overloads
        method = { "func_149666_a", "getSubBlocks" },
        at = @At("RETURN"),
        remap = false,
        require = 1)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void qmcore$hideGearbox(Item item, CreativeTabs tab, List list, CallbackInfo ci) {
        ((List<Object>) list)
            .removeIf(o -> o instanceof ItemStack stack && stack.getItemDamage() == DisabledTech.FORESTRY_FARM_GEARBOX);
    }
}
