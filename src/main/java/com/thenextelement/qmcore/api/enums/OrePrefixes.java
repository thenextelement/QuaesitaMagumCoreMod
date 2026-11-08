package com.thenextelement.qmcore.api.enums;

import java.util.List;
import java.util.Locale;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Ore dictionary prefixes used in the pack. An entry name is the prefix followed by a material, e.g. {@code ingot} +
 * {@code Copper} = {@code ingotCopper}. A standalone, much smaller take on GT5-Unofficial's {@code OrePrefixes}: names
 * and lookups only, no items, icons or recipes.
 */
public enum OrePrefixes {

    // ores
    ore,
    oreNether,
    oreEnd,
    denseore,
    /** Thaumcraft native clusters (and mods that copy them). */
    cluster,

    // dusts
    dust,
    dustSmall,
    dustTiny,

    // metals
    ingot,
    nugget,

    // gems
    gem,

    // storage blocks
    block,

    // parts
    plate,
    gear,
    stick;

    /** The ore dictionary name for this prefix and material, e.g. {@code ingotCopper}. */
    public String get(Materials material) {
        return name() + material.name();
    }

    /** The ore dictionary name for this prefix and a raw material name, e.g. {@code get("Copper")}. */
    public String get(String materialName) {
        return name() + materialName;
    }

    /** Every stack registered under this prefix and material. The list is live; don't modify it. */
    public List<ItemStack> getOres(Materials material) {
        return OreDictionary.getOres(get(material));
    }

    /** True if anything is registered under this prefix and material. */
    public boolean exists(Materials material) {
        return !getOres(material).isEmpty();
    }

    /** The first registered stack (a copy, size 1), or null. */
    public ItemStack getFirst(Materials material) {
        List<ItemStack> ores = getOres(material);
        if (ores.isEmpty()) return null;
        ItemStack copy = ores.get(0)
            .copy();
        copy.stackSize = 1;
        return copy;
    }

    /** The prefix an ore dictionary name starts with (longest match wins), or null. */
    public static OrePrefixes getPrefix(String oreName) {
        OrePrefixes best = null;
        for (OrePrefixes p : values()) {
            String n = p.name();
            if (oreName.startsWith(n) && oreName.length() > n.length()
                && Character.isUpperCase(oreName.charAt(n.length()))
                && (best == null || n.length() > best.name()
                    .length())) {
                best = p;
            }
        }
        return best;
    }

    /** The material part of an ore dictionary name, e.g. {@code Copper} for {@code ingotCopper}, or null. */
    public static String getMaterialName(String oreName) {
        OrePrefixes p = getPrefix(oreName);
        return p == null ? null
            : oreName.substring(
                p.name()
                    .length());
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
