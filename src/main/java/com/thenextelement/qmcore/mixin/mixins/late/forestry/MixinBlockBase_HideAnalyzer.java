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

import forestry.core.blocks.BlockBase;
import forestry.core.blocks.BlockCore;

/**
 * Leaves the Analyzer out of the creative tab and NEI. BlockCore inherits getSubBlocks from BlockBase, which many other
 * Forestry blocks share, so the filter only acts on the BlockCore instance (whose other entry, the Escritoire, stays).
 */
@Mixin(value = BlockBase.class, remap = false)
public abstract class MixinBlockBase_HideAnalyzer {

    @Inject(
        // SRG name in the pack, MCP name in the dev environment; the method has no overloads
        method = { "func_149666_a", "getSubBlocks" },
        at = @At("RETURN"),
        remap = false,
        require = 1)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void qmcore$hideAnalyzer(Item item, CreativeTabs tab, List list, CallbackInfo ci) {
        if (!((Object) this instanceof BlockCore)) return;
        ((List<Object>) list).removeIf(
            o -> o instanceof ItemStack stack && stack.getItemDamage() == DisabledTech.FORESTRY_CORE_ANALYZER);
    }
}
