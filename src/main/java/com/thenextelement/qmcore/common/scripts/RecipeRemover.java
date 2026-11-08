package com.thenextelement.qmcore.common.scripts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

import com.thenextelement.qmcore.common.utils.LogHelper;
import com.thenextelement.qmcore.common.utils.StackUtils;

/**
 * Buffered recipe removal, the Java form of MineTweaker's {@code recipes.remove(output)} and
 * {@code furnace.remove(output)}. Modelled on NewHorizonsCoreMod's RecipeRemover: removals are collected while scripts
 * run, then applied in a single pass over the crafting list instead of one scan per removal, before any script adds
 * recipes.
 */
public final class RecipeRemover {

    private static final List<ItemStack> craftingOutputs = new ArrayList<>();
    private static final Set<Item> craftingOutputItems = new HashSet<>();
    private static final List<ItemStack> furnaceOutputs = new ArrayList<>();

    private RecipeRemover() {}

    /** {@code recipes.remove(output)}: every crafting recipe whose output matches (wildcard meta allowed). */
    public static void removeRecipeByOutput(ItemStack output) {
        if (IngredientFactory.isMissing(output)) return;
        craftingOutputs.add(output);
        craftingOutputItems.add(output.getItem());
    }

    /** {@code furnace.remove(output)}: every smelting recipe that produces this. */
    public static void removeFurnaceRecipeByOutput(ItemStack output) {
        if (IngredientFactory.isMissing(output)) return;
        furnaceOutputs.add(output);
    }

    /** Applies the buffered removals. Called by {@link ScriptLoader} after every script's removeRecipes(). */
    @SuppressWarnings("unchecked")
    static void flush() {
        int crafting = 0, furnace = 0;
        if (!craftingOutputs.isEmpty()) {
            List<IRecipe> list = CraftingManager.getInstance()
                .getRecipeList();
            int before = list.size();
            list.removeIf(recipe -> {
                ItemStack out = recipe.getRecipeOutput();
                if (out == null || !craftingOutputItems.contains(out.getItem())) return false;
                for (ItemStack target : craftingOutputs) {
                    if (StackUtils.areStacksEqual(target, out, true)) return true;
                }
                return false;
            });
            crafting = before - list.size();
        }
        if (!furnaceOutputs.isEmpty()) {
            Map<ItemStack, ItemStack> smelting = FurnaceRecipes.smelting()
                .getSmeltingList();
            int before = smelting.size();
            smelting.entrySet()
                .removeIf(e -> {
                    for (ItemStack target : furnaceOutputs) {
                        if (StackUtils.areStacksEqual(target, e.getValue(), true)) return true;
                    }
                    return false;
                });
            furnace = before - smelting.size();
        }
        LogHelper.info("Recipe remover: removed " + crafting + " crafting and " + furnace + " smelting recipes.");
        craftingOutputs.clear();
        craftingOutputItems.clear();
        furnaceOutputs.clear();
    }

    /** Convenience for wildcard removals: {@code <modId:name:*>}. */
    public static ItemStack anyMeta(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setItemDamage(OreDictionary.WILDCARD_VALUE);
        return copy;
    }
}
