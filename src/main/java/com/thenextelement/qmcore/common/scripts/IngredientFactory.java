package com.thenextelement.qmcore.common.scripts;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import com.thenextelement.qmcore.common.utils.LogHelper;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Item lookups for scripts, ported from NewHorizonsCoreMod. The Java form of MineTweaker's {@code <modid:name:meta>}.
 * <p>
 * A missing item never returns null: it returns a fire block named after the missing item, so a broken recipe shows
 * up in NEI instead of crashing the game, and the miss is logged.
 */
public final class IngredientFactory {

    private IngredientFactory() {}

    /** {@code <modId:name>} */
    public static ItemStack getModItem(String modId, String name) {
        return getModItem(modId, name, 1, 0);
    }

    /** {@code <modId:name> * amount} */
    public static ItemStack getModItem(String modId, String name, int amount) {
        return getModItem(modId, name, amount, 0);
    }

    /** {@code <modId:name:meta> * amount}; use {@link IScriptLoader#wildcard} for {@code <modId:name:*>}. */
    public static ItemStack getModItem(String modId, String name, int amount, int meta) {
        Item item = GameRegistry.findItem(modId, name);
        if (item == null) return missing(modId, name);
        return new ItemStack(item, amount, meta);
    }

    /** {@code <modId:name:meta>.withTag({...}) * amount}, with the tag written in MineTweaker/JSON-NBT syntax. */
    public static ItemStack getModItem(String modId, String name, int amount, int meta, String nbt) {
        ItemStack stack = getModItem(modId, name, amount, meta);
        if (stack.getItem() == Item.getItemFromBlock(Blocks.fire) || nbt == null) return stack;
        try {
            NBTBase tag = JsonToNBT.func_150315_a(nbt);
            if (tag instanceof NBTTagCompound compound) stack.setTagCompound(compound);
        } catch (NBTException e) {
            LogHelper.error("Bad NBT for " + modId + ":" + name + ": " + nbt);
        }
        return stack;
    }

    private static ItemStack missing(String modId, String name) {
        LogHelper.warn("Script item not found: " + modId + ":" + name);
        ItemStack fire = new ItemStack(Blocks.fire);
        fire.setStackDisplayName(EnumChatFormatting.RED + "Missing Item: " + modId + ":" + name);
        return fire;
    }

    /** True if this stack is a placeholder from a failed lookup. */
    public static boolean isMissing(ItemStack stack) {
        return stack == null || stack.getItem() == Item.getItemFromBlock(Blocks.fire);
    }
}
