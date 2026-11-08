package com.thenextelement.qmcore.common.scripts;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.common.utils.LogHelper;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * One script: the Java form of a MineTweaker .zs file, usually one per mod. Ported from NewHorizonsCoreMod, without
 * GregTech. Implementations are listed in {@link ScriptLoader}; start from {@link ScriptTemplate}.
 */
public interface IScriptLoader {

    /** MineTweaker's {@code :*} meta. */
    int wildcard = OreDictionary.WILDCARD_VALUE;

    /** Shown in the log. */
    String getScriptName();

    /** The script only runs when every one of these is loaded, so no manual isModLoaded checks are needed. */
    List<Mods> getDependencies();

    /**
     * Removals ({@link RecipeRemover}). Every script's removals run and are applied before any script's
     * {@link #loadRecipes()}, so a recipe can be removed and re-added with the same output.
     */
    default void removeRecipes() {}

    /** Additions and other tweaks. */
    void loadRecipes();

    /**
     * {@code recipes.addShaped(output, [[a, b, c], [d, e, f], [g, h, i]])}.
     *
     * @param inputs up to 9 ingredients, row by row: ItemStack, Item, Block, String (ore dictionary) or null (empty)
     */
    default boolean addShapedRecipe(ItemStack output, Object... inputs) {
        if (IngredientFactory.isMissing(output)) {
            LogHelper.error(getScriptName() + ": skipped a shaped recipe with a missing output");
            return false;
        }
        try {
            GameRegistry.addRecipe(new ShapedOreRecipe(output, toShapedArgs(inputs)));
            return true;
        } catch (Exception e) {
            LogHelper.error(getScriptName() + ": a shaped recipe for " + output + " went wrong");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * {@code recipes.addShapeless(output, [a, b, ...])}.
     *
     * @param inputs ItemStack, Item, Block or String (ore dictionary); nulls are ignored
     */
    default boolean addShapelessRecipe(ItemStack output, Object... inputs) {
        if (IngredientFactory.isMissing(output)) {
            LogHelper.error(getScriptName() + ": skipped a shapeless recipe with a missing output");
            return false;
        }
        try {
            List<Object> list = new ArrayList<>();
            for (Object o : inputs) if (o != null) list.add(o);
            GameRegistry.addRecipe(new ShapelessOreRecipe(output, list.toArray()));
            return true;
        } catch (Exception e) {
            LogHelper.error(getScriptName() + ": a shapeless recipe for " + output + " went wrong");
            e.printStackTrace();
            return false;
        }
    }

    /** {@code <ore:name>.add(stack)}; a missing item is skipped instead of registering the placeholder. */
    default void addOreDict(String name, ItemStack stack) {
        if (IngredientFactory.isMissing(stack)) {
            LogHelper.warn(getScriptName() + ": skipped adding a missing item to " + name);
            return;
        }
        OreDictionary.registerOre(name, stack);
    }

    /** Hides an item from NEI, for content that is removed from the pack. */
    default void hideItem(ItemStack stack) {
        ItemHider.hide(stack);
    }

    /** True when every dependency is loaded. */
    default boolean isScriptLoadable() {
        for (Mods dep : getDependencies()) {
            if (!dep.isModLoaded()) return false;
        }
        return true;
    }

    /**
     * Turns a row-by-row grid (3, 6 or 9 entries; nulls are empty slots) into ShapedOreRecipe arguments: a pattern
     * trimmed to the used rows and columns, then one key per distinct slot.
     */
    static Object[] toShapedArgs(Object[] grid) {
        if (grid.length == 0 || grid.length > 9) throw new IllegalArgumentException("1-9 inputs expected");
        int width = grid.length <= 3 ? grid.length : 3;
        int height = (grid.length + width - 1) / width;

        int minRow = height, maxRow = -1, minCol = width, maxCol = -1;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i] == null) continue;
            int r = i / width, c = i % width;
            minRow = Math.min(minRow, r);
            maxRow = Math.max(maxRow, r);
            minCol = Math.min(minCol, c);
            maxCol = Math.max(maxCol, c);
        }
        if (maxRow < 0) throw new IllegalArgumentException("recipe has no ingredients");

        List<Object> args = new ArrayList<>();
        List<Object> keys = new ArrayList<>();
        for (int r = minRow; r <= maxRow; r++) {
            StringBuilder row = new StringBuilder();
            for (int c = minCol; c <= maxCol; c++) {
                int i = r * width + c;
                Object o = i < grid.length ? grid[i] : null;
                if (o == null) {
                    row.append(' ');
                    continue;
                }
                char key = (char) ('A' + keys.size());
                keys.add(key);
                keys.add(o);
                row.append(key);
            }
            args.add(row.toString());
        }
        args.addAll(keys);
        return args.toArray();
    }
}
