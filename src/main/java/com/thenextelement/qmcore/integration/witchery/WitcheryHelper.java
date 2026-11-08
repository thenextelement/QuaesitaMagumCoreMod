package com.thenextelement.qmcore.integration.witchery;

import static com.thenextelement.qmcore.integration.witchery.WitcheryBrewRegistryAccessor.getRecipes;
import static com.thenextelement.qmcore.integration.witchery.WitcheryBrewRegistryAccessor.ingredient;
import static com.thenextelement.qmcore.integration.witchery.WitcheryBrewRegistryAccessor.isCauldronRecipeMatch;
import static com.thenextelement.qmcore.integration.witchery.WitcheryBrewRegistryAccessor.modifyBrewRecipe;
import static com.thenextelement.qmcore.integration.witchery.WitcheryBrewRegistryAccessor.registerBrewAction;
import static com.thenextelement.qmcore.integration.witchery.WitcheryBrewRegistryAccessor.removeAction;

import java.util.Arrays;

import net.minecraft.item.ItemStack;

import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.brewing.action.BrewAction;
import com.emoniph.witchery.brewing.action.BrewActionModifier;
import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe;
import com.emoniph.witchery.crafting.KettleRecipes;
import com.thenextelement.qmcore.QuaesitaMagum;

/**
 * Cauldron (brew) and kettle recipe helpers for scripts, ported from NewHorizonsCoreMod's WitcheryPlugin. Only
 * reference this class when Witchery is loaded (register Witchery scripts behind a Witchery check).
 */
public final class WitcheryHelper {

    private WitcheryHelper() {}

    /**
     * Makes an item accepted by the cauldron: registers a brew action that only consumes altar power, if the item has
     * none yet.
     *
     * @param power altar power consumed, can be 0
     */
    public static void ensureItemHasBrewAction(ItemStack item, int power) {
        if (item != null && WitcheryBrewRegistry.INSTANCE.getActionForItemStack(item) == null) {
            registerBrewAction(new EmptyBrewActionModifier(item, power));
        }
    }

    /**
     * Adds a cauldron recipe.
     *
     * @param power    altar power used
     * @param lastItem the item thrown in last, which completes the brew
     * @param items    the other ingredients, in any order; does not include lastItem
     */
    public static void addBrewRecipe(int power, ItemStack result, ItemStack lastItem, ItemStack... items) {
        if (ingredient == null) return;
        final BrewItemKey key = BrewItemKey.fromStack(lastItem);
        BrewAction action = ingredient.get(key);
        if (action instanceof EmptyBrewActionModifier) action = null;
        for (ItemStack item : items) ensureItemHasBrewAction(item, 0);

        if (action == null) {
            registerBrewAction(
                new BrewActionRitualRecipe(
                    key,
                    new AltarPower(power),
                    new BrewActionRitualRecipe.Recipe(result, items)));
        } else if (action instanceof BrewActionRitualRecipe ritualRecipe) {
            final BrewActionRitualRecipe.Recipe[] old = getRecipes(ritualRecipe);
            final BrewActionRitualRecipe.Recipe[] recipes = Arrays.copyOf(old, old.length + 1);
            recipes[recipes.length - 1] = new BrewActionRitualRecipe.Recipe(result, items);
            modifyBrewRecipe(ritualRecipe, recipes);
        } else {
            QuaesitaMagum.LOG.warn("Conflicting brew recipe! key: {}", lastItem);
        }
    }

    /** Removes every cauldron recipe completed by {@code lastItem}. */
    public static void removeAllBrewRecipes(ItemStack lastItem) {
        if (ingredient == null) return;
        final BrewAction action = ingredient.get(BrewItemKey.fromStack(lastItem));
        if (action instanceof BrewActionRitualRecipe ritualRecipe) {
            removeAction(ritualRecipe);
        } else {
            QuaesitaMagum.LOG.warn("There is no cauldron recipe using {} as last item", lastItem);
        }
    }

    /**
     * Removes the cauldron recipe with exactly these ingredients, keeping other recipes that share the last item.
     *
     * @param lastItem the item thrown in last
     * @param items    the other ingredients, in any order; does not include lastItem
     */
    public static void removeBrewRecipe(ItemStack lastItem, ItemStack... items) {
        if (ingredient == null) return;
        final BrewAction action = ingredient.get(BrewItemKey.fromStack(lastItem));
        if (action instanceof BrewActionRitualRecipe ritualRecipe) {
            for (BrewActionRitualRecipe.Recipe recipe : ritualRecipe.getExpandedRecipes()) {
                if (!isCauldronRecipeMatch(recipe, lastItem, items)) continue;
                if (ritualRecipe.getExpandedRecipes()
                    .size() > 1) {
                    modifyBrewRecipe(
                        ritualRecipe,
                        ritualRecipe.getExpandedRecipes()
                            .stream()
                            .filter(r -> r != recipe)
                            .map(
                                r -> new BrewActionRitualRecipe.Recipe(
                                    r.result,
                                    Arrays.copyOf(r.ingredients, r.ingredients.length - 1)))
                            .toArray(BrewActionRitualRecipe.Recipe[]::new));
                } else {
                    removeAction(ritualRecipe);
                }
                return;
            }
        }
        QuaesitaMagum.LOG
            .warn("There is no cauldron recipe matching last: {}, rest: {}", lastItem, Arrays.toString(items));
    }

    /** Removes every kettle recipe that makes {@code output}. */
    public static void removeAllKettleRecipes(ItemStack output) {
        KettleRecipes.instance().recipes.removeIf(recipe -> recipe.output.isItemEqual(output));
    }

    /** A brew action that does nothing but consume altar power, so an item can be thrown into the cauldron. */
    private static class EmptyBrewActionModifier extends BrewActionModifier {

        EmptyBrewActionModifier(ItemStack item, int power) {
            super(BrewItemKey.fromStack(item), null, new AltarPower(power));
        }
    }
}
