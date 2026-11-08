package com.thenextelement.qmcore.common.handler;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.thenextelement.qmcore.common.ModBlocks;

import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingHandler {

    public static void init() {
        // Disenchanter
        addOre(
            ModBlocks.disEnchanter,
            "PIP",
            "ETE",
            "CBC",
            'P',
            Items.ender_eye,
            'I',
            Items.enchanted_book,
            'E',
            "gemEmerald",
            'T',
            Blocks.enchanting_table,
            'C',
            "gemDiamond", // placeholder for DE's draconic core, swap for your own item later
            'B',
            Items.book);
    }

    // ---------------------------------------------------------------------------------------------
    // Shaped, ore-dictionary aware (strings like "gemEmerald" allowed)
    // ---------------------------------------------------------------------------------------------

    private static void addOre(Block result, Object... recipe) {
        addOre(new ItemStack(result), recipe);
    }

    private static void addOre(Item result, Object... recipe) {
        addOre(new ItemStack(result), recipe);
    }

    private static void addOre(ItemStack result, Object... recipe) {
        if (result == null || hasNull(recipe)) return;
        GameRegistry.addRecipe(new ShapedOreRecipe(result, recipe));
    }

    // ---------------------------------------------------------------------------------------------
    // Shapeless, ore-dictionary aware
    // ---------------------------------------------------------------------------------------------

    private static void addShapelessOre(ItemStack result, Object... recipe) {
        if (result == null || hasNull(recipe)) return;
        GameRegistry.addRecipe(new ShapelessOreRecipe(result, recipe));
    }

    // ---------------------------------------------------------------------------------------------
    // Plain shaped (no ore dictionary)
    // ---------------------------------------------------------------------------------------------

    private static void add(Block result, Object... recipe) {
        add(new ItemStack(result), recipe);
    }

    private static void add(Item result, Object... recipe) {
        add(new ItemStack(result), recipe);
    }

    private static void add(ItemStack result, Object... recipe) {
        if (result == null || hasNull(recipe)) return;
        GameRegistry.addRecipe(result, recipe);
    }

    private static boolean hasNull(Object[] recipe) {
        for (Object o : recipe) {
            if (o == null) return true;
        }
        return false;
    }
}
