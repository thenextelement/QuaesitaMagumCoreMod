package com.thenextelement.qmcore.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/** Item stack comparisons used by recipe removal and Thaumcraft helpers (replaces GT's GTUtility.areStacksEqual). */
public final class StackUtils {

    private StackUtils() {}

    /** Same item, same damage (or either is the wildcard) and equal NBT. */
    public static boolean areStacksEqual(ItemStack a, ItemStack b) {
        return areStacksEqual(a, b, false);
    }

    /** Same item and same damage (or either is the wildcard); NBT is compared unless {@code ignoreNBT}. */
    public static boolean areStacksEqual(ItemStack a, ItemStack b, boolean ignoreNBT) {
        if (a == null || b == null || a.getItem() == null || a.getItem() != b.getItem()) return false;
        int da = a.getItemDamage(), db = b.getItemDamage();
        if (da != db && da != OreDictionary.WILDCARD_VALUE && db != OreDictionary.WILDCARD_VALUE) return false;
        return ignoreNBT || ItemStack.areItemStackTagsEqual(a, b);
    }
}
