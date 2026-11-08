package com.thenextelement.qmcore.common.unification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.common.unification.UnificationRules.Delist;
import com.thenextelement.qmcore.common.unification.UnificationRules.ItemRef;
import com.thenextelement.qmcore.common.unification.UnificationRules.Rule;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 * Applies {@link UnificationRules}: one item per metal, gem and dust.
 * <ol>
 * <li>While mods load, losers are kept out of the ore dictionary (see MixinOreDictionary_Unification), so ore
 * recipes never accept or pick them. A loser is only blocked when its winner's mod is installed.</li>
 * <li>At load complete ({@link #apply()}): ore names only a loser had are given to its winner; crafting recipe outputs
 * and inputs and furnace outputs that are losers become winners; a 1:1 conversion recipe is added for each loser.</li>
 * <li>Losers that still drop from blocks become winners ({@link UnificationEventHandler}) and are hidden in NEI.</li>
 * </ol>
 */
public final class OreUnification {

    private OreUnification() {}

    /** loser key -> rule, for rules whose winner is installed. */
    private static Map<String, Rule> activeLosers;
    /** "oreName|itemKey" */
    private static Set<String> delisted;
    /** loser key -> ore names it tried to register (filled while mods load) */
    private static final Map<String, Set<String>> blockedNames = new HashMap<>();

    /** Resolved at load complete: loser key -> winner stack (size 1). */
    private static final Map<String, ItemStack> resolved = new LinkedHashMap<>();
    private static final Set<Item> loserItems = new HashSet<>();
    private static final List<ItemStack> loserStacks = new ArrayList<>();

    /**
     * Builds the active rule set once FML's mod list exists. Before that (vanilla's own ore dictionary entries are
     * registered very early), it stays unbuilt and nothing is blocked, so a rule never activates on a guess.
     */
    private static synchronized boolean init() {
        if (activeLosers != null) return true;
        if (!modListReady()) return false;
        Map<String, Rule> losers = new HashMap<>();
        for (Rule rule : UnificationRules.RULES) {
            if (isModLoaded(rule.winner.modId)) losers.put(rule.loser.key(), rule);
        }
        Set<String> d = new HashSet<>();
        for (Delist delist : UnificationRules.DELISTS) d.add(delist.oreName + "|" + delist.item.key());
        delisted = d;
        activeLosers = losers;
        return true;
    }

    private static boolean modListReady() {
        try {
            return !Loader.instance()
                .getIndexedModList()
                .isEmpty();
        } catch (RuntimeException e) {
            return false; // namedMods is still null before mod discovery finishes
        }
    }

    private static boolean isModLoaded(String modId) {
        return "minecraft".equals(modId) || Loader.isModLoaded(modId);
    }

    private static String keyOf(ItemStack stack) {
        if (stack == null || stack.getItem() == null) return null;
        GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(stack.getItem());
        if (id == null) return null;
        return id.modId + ":" + id.name + ":" + stack.getItemDamage();
    }

    // ==========================================REGISTRATION (mixin)============================================//

    /** Called for every ore dictionary registration. True means skip it. */
    public static boolean shouldBlockRegistration(String oreName, ItemStack stack) {
        if (oreName == null || stack == null || !init()) return false;
        String key = keyOf(stack);
        if (key == null) return false;
        if (delisted.contains(oreName + "|" + key)) return true;
        if (activeLosers.containsKey(key)) {
            synchronized (blockedNames) {
                blockedNames.computeIfAbsent(key, k -> new LinkedHashSet<>())
                    .add(oreName);
            }
            return true;
        }
        return false;
    }

    // ==========================================LOAD COMPLETE===================================================//

    public static void apply() {
        if (!init()) return;
        resolve();
        if (resolved.isEmpty()) return;

        int names = transferOreNames();
        int recipes = unifyCraftingRecipes();
        int smelting = unifyFurnaceRecipes();
        addConversionRecipes();
        QuaesitaMagum.LOG.info(
            "Ore unification: {} items unified, {} ore names moved to winners, {} crafting and {} smelting recipes updated.",
            resolved.size(),
            names,
            recipes,
            smelting);
    }

    private static void resolve() {
        for (Map.Entry<String, Rule> e : activeLosers.entrySet()) {
            ItemRef winner = e.getValue().winner, loser = e.getValue().loser;
            Item winnerItem = GameRegistry.findItem(winner.modId, winner.name);
            Item loserItem = GameRegistry.findItem(loser.modId, loser.name);
            if (winnerItem == null) {
                QuaesitaMagum.LOG.warn("Unification winner {} not found; {} left as it is", winner, loser);
                continue;
            }
            if (loserItem == null) continue; // that mod isn't installed
            resolved.put(e.getKey(), new ItemStack(winnerItem, 1, winner.meta));
            loserItems.add(loserItem);
            loserStacks.add(new ItemStack(loserItem, 1, loser.meta));
        }
    }

    /** The winner for a loser stack (a copy with the same size), or null if the stack isn't a loser. */
    public static ItemStack getWinner(ItemStack stack) {
        if (stack == null || !loserItems.contains(stack.getItem())) return null;
        ItemStack winner = resolved.get(keyOf(stack));
        if (winner == null) return null;
        ItemStack copy = winner.copy();
        copy.stackSize = stack.stackSize;
        return copy;
    }

    /** Every resolved loser as a stack (size 1). */
    public static List<ItemStack> getLosers() {
        List<ItemStack> list = new ArrayList<>(loserStacks.size());
        for (ItemStack stack : loserStacks) list.add(stack.copy());
        return list;
    }

    /** Ore names that only a loser registered are given to its winner, so recipes using them keep working. */
    private static int transferOreNames() {
        int count = 0;
        synchronized (blockedNames) {
            for (Map.Entry<String, Set<String>> e : blockedNames.entrySet()) {
                ItemStack winner = resolved.get(e.getKey());
                if (winner == null) continue;
                for (String name : e.getValue()) {
                    if (!containsItem(OreDictionary.getOres(name), winner)) {
                        OreDictionary.registerOre(name, winner.copy());
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean containsItem(List<ItemStack> ores, ItemStack stack) {
        for (ItemStack ore : ores) {
            if (ore.getItem() == stack.getItem() && (ore.getItemDamage() == stack.getItemDamage()
                || ore.getItemDamage() == OreDictionary.WILDCARD_VALUE)) return true;
        }
        return false;
    }

    // ==========================================RECIPES=========================================================//

    private static Field shapedOutput, shapelessOutput, shapedOreOutput, shapelessOreOutput;

    @SuppressWarnings("unchecked")
    private static int unifyCraftingRecipes() {
        try {
            shapedOutput = ReflectionHelper.findField(ShapedRecipes.class, "recipeOutput", "field_77575_e");
            shapelessOutput = ReflectionHelper.findField(ShapelessRecipes.class, "recipeOutput", "field_77580_a");
            shapedOreOutput = ReflectionHelper.findField(ShapedOreRecipe.class, "output");
            shapelessOreOutput = ReflectionHelper.findField(ShapelessOreRecipe.class, "output");
        } catch (RuntimeException e) {
            QuaesitaMagum.LOG.error("Ore unification: recipe fields not found, recipes left as they are", e);
            return 0;
        }

        int changed = 0, unsupported = 0;
        for (IRecipe recipe : (List<IRecipe>) CraftingManager.getInstance()
            .getRecipeList()) {
            try {
                boolean c = false;
                if (recipe instanceof ShapedRecipes r) {
                    c = replaceOutput(r, shapedOutput, r.getRecipeOutput());
                    c |= replaceAll(r.recipeItems);
                } else if (recipe instanceof ShapelessRecipes r) {
                    c = replaceOutput(r, shapelessOutput, r.getRecipeOutput());
                    c |= replaceAll((List<Object>) (List<?>) r.recipeItems);
                } else if (recipe instanceof ShapedOreRecipe r) {
                    c = replaceOutput(r, shapedOreOutput, r.getRecipeOutput());
                    c |= replaceAll(r.getInput());
                } else if (recipe instanceof ShapelessOreRecipe r) {
                    c = replaceOutput(r, shapelessOreOutput, r.getRecipeOutput());
                    c |= replaceAll(r.getInput());
                } else if (getWinner(recipe.getRecipeOutput()) != null) {
                    unsupported++;
                }
                if (c) changed++;
            } catch (ReflectiveOperationException | RuntimeException e) {
                QuaesitaMagum.LOG.warn("Ore unification: could not update recipe {}", recipe, e);
            }
        }
        if (unsupported > 0) QuaesitaMagum.LOG.info(
            "Ore unification: {} custom recipe classes still output unified-away items (converted by crafting).",
            unsupported);
        return changed;
    }

    private static boolean replaceOutput(IRecipe recipe, Field field, ItemStack output)
        throws ReflectiveOperationException {
        ItemStack winner = getWinner(output);
        if (winner == null) return false;
        if (output.hasTagCompound()) winner.setTagCompound(
            (net.minecraft.nbt.NBTTagCompound) output.getTagCompound()
                .copy());
        field.set(recipe, winner);
        return true;
    }

    private static boolean replaceAll(Object[] inputs) {
        boolean changed = false;
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] instanceof ItemStack stack) {
                ItemStack winner = getWinner(stack);
                if (winner != null) {
                    inputs[i] = winner;
                    changed = true;
                }
            }
        }
        return changed;
    }

    private static boolean replaceAll(List<Object> inputs) {
        boolean changed = false;
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i) instanceof ItemStack stack) {
                ItemStack winner = getWinner(stack);
                if (winner != null) {
                    inputs.set(i, winner);
                    changed = true;
                }
            }
        }
        return changed;
    }

    @SuppressWarnings("unchecked")
    private static int unifyFurnaceRecipes() {
        int changed = 0;
        for (Map.Entry<ItemStack, ItemStack> e : ((Map<ItemStack, ItemStack>) FurnaceRecipes.smelting()
            .getSmeltingList()).entrySet()) {
            ItemStack winner = getWinner(e.getValue());
            if (winner != null) {
                e.setValue(winner);
                changed++;
            }
        }
        return changed;
    }

    /** Lets players turn leftover loser items (old worlds, loot, machines) into winners 1:1. */
    private static void addConversionRecipes() {
        for (ItemStack loser : getLosers()) {
            ItemStack winner = getWinner(loser);
            if (winner != null) GameRegistry.addShapelessRecipe(winner, loser);
        }
    }
}
