package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;
import static com.thenextelement.qmcore.common.scripts.RecipeRemover.removeRecipeByOutput;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.api.enums.Mods;

/** Small fixes that each need one mod. From MoreRecipefixes.zs. */
public class ScriptMiscFixes {

    private ScriptMiscFixes() {}

    /** The multiblock hologram projector: lapis + paper -> 2. */
    public static class StructureLib implements IScriptLoader {

        @Override
        public String getScriptName() {
            return "StructureLib";
        }

        @Override
        public List<Mods> getDependencies() {
            return Collections.singletonList(Mods.StructureLib);
        }

        @Override
        public void loadRecipes() {
            addShapedRecipe(
                getModItem(Mods.StructureLib.ID, "item.structurelib.constructableTrigger", 2, 0),
                new ItemStack(Items.dye, 1, 4),
                Items.paper,
                null);
        }
    }

    /** Hammerz' void metal block is unified into Thaumic Bases'; its recipe goes. */
    public static class Hammerz implements IScriptLoader {

        @Override
        public String getScriptName() {
            return "Hammerz";
        }

        @Override
        public List<Mods> getDependencies() {
            return Collections.singletonList(Mods.Hammerz);
        }

        @Override
        public void removeRecipes() {
            removeRecipeByOutput(getModItem(Mods.Hammerz.ID, "Storage", 1, 0));
        }

        @Override
        public void loadRecipes() {}
    }

    /** Paneer from lemonade and fresh milk. */
    public static class HarvestCraft implements IScriptLoader {

        @Override
        public String getScriptName() {
            return "Pam's HarvestCraft";
        }

        @Override
        public List<Mods> getDependencies() {
            return Collections.singletonList(Mods.PamsHarvestCraft);
        }

        @Override
        public void removeRecipes() {
            // HarvestCraft's own paneer needs foodLemonjuice, which nothing in the pack provides, and its tikka masala
            // has 10 ingredients, one more than a crafting grid holds. Both are replaced below.
            removeRecipeByOutput(getModItem(Mods.PamsHarvestCraft.ID, "paneertikkamasalaItem", 1, 0));
            removeRecipeByOutput(getModItem(Mods.PamsHarvestCraft.ID, "paneerItem", 1, 0));
        }

        @Override
        public void loadRecipes() {
            addShapelessRecipe(
                getModItem(Mods.PamsHarvestCraft.ID, "paneerItem", 8, 0),
                getModItem(Mods.PamsHarvestCraft.ID, "lemonaideItem", 1, 0),
                getModItem(Mods.PamsHarvestCraft.ID, "freshmilkItem", 1, 0));

            // HarvestCraft's recipe minus foodCurrypowder (garam masala already covers the spices): 9 ingredients
            // spotless:off
            addShapelessRecipe(
                getModItem(Mods.PamsHarvestCraft.ID, "paneertikkamasalaItem", 1, 0),
                "toolSaucepan", "foodPaneer", "foodGarammasala",
                "foodCoconutcream", "cropOnion", "cropBellpepper",
                "cropTomato", "cropGinger", "cropGarlic");
            // spotless:on
        }
    }

    /** Shulker shells from purpur. */
    public static class EtFuturum implements IScriptLoader {

        @Override
        public String getScriptName() {
            return "Et Futurum Requiem";
        }

        @Override
        public List<Mods> getDependencies() {
            return Collections.singletonList(Mods.EtFuturumRequiem);
        }

        @Override
        public void loadRecipes() {
            ItemStack purpur = getModItem(Mods.EtFuturumRequiem.ID, "purpur_block", 1, 0);
            // spotless:off
            addShapedRecipe(
                getModItem(Mods.EtFuturumRequiem.ID, "shulker_shell", 2, 0),
                purpur, purpur, purpur,
                purpur, null, purpur);
            // spotless:on
        }
    }

    /** Natura cotton: seeds, HarvestCraft woven cotton, and Backpack bound leather. */
    public static class Cotton implements IScriptLoader {

        @Override
        public String getScriptName() {
            return "Cotton and Leather";
        }

        @Override
        public List<Mods> getDependencies() {
            return Arrays.asList(Mods.Natura, Mods.PamsHarvestCraft, Mods.Backpack);
        }

        @Override
        public void removeRecipes() {
            removeRecipeByOutput(getModItem(Mods.PamsHarvestCraft.ID, "wovencottonItem", 1, 0));
            removeRecipeByOutput(getModItem(Mods.Backpack.ID, "boundLeather", 1, 0));
        }

        @Override
        public void loadRecipes() {
            ItemStack cotton = getModItem(Mods.Natura.ID, "barleyFood", 1, 3);
            ItemStack wovenCotton = getModItem(Mods.PamsHarvestCraft.ID, "wovencottonItem", 1, 0);
            ItemStack string = new ItemStack(Items.string);

            addShapelessRecipe(getModItem(Mods.Natura.ID, "barley.seed", 2, 1), cotton, cotton);
            // spotless:off
            addShapedRecipe(
                getModItem(Mods.PamsHarvestCraft.ID, "wovencottonItem", 3, 0),
                cotton, string, cotton,
                string, cotton, string,
                cotton, string, cotton);
            // spotless:on

            // spotless:off
            addShapedRecipe(
                getModItem(Mods.Backpack.ID, "boundLeather", 4, 0),
                string, wovenCotton, string,
                Items.leather, string, Items.leather,
                string, wovenCotton, string);
            // spotless:on
        }
    }

    /** Blood Magic's chemistry set needs a Brewing-API brewing stand and any blood orb. */
    public static class BloodMagic implements IScriptLoader {

        @Override
        public String getScriptName() {
            return "Blood Magic";
        }

        @Override
        public List<Mods> getDependencies() {
            return Arrays.asList(Mods.BloodMagic, Mods.BrewingAPI);
        }

        @Override
        public void removeRecipes() {
            removeRecipeByOutput(getModItem(Mods.BloodMagic.ID, "blockWritingTable", 1, 0));
        }

        @Override
        public void loadRecipes() {
            // spotless:off
            addShapedRecipe(
                getModItem(Mods.BloodMagic.ID, "blockWritingTable", 1, 0),
                null, getModItem(Mods.BrewingAPI.ID, "brewingapi_brewingStand2", 1, 0), null,
                Blocks.obsidian, "bloodOrb", Blocks.obsidian);
            // spotless:on
        }
    }

    /** Botania's alchemy catalyst and botanical brewery use the Brewing-API brewing stand. */
    public static class Botania implements IScriptLoader {

        @Override
        public String getScriptName() {
            return "Botania";
        }

        @Override
        public List<Mods> getDependencies() {
            return Arrays.asList(Mods.Botania, Mods.BrewingAPI);
        }

        @Override
        public void removeRecipes() {
            removeRecipeByOutput(getModItem(Mods.Botania.ID, "alchemyCatalyst", 1, 0));
            removeRecipeByOutput(getModItem(Mods.Botania.ID, "brewery", 1, 0));
        }

        @Override
        public void loadRecipes() {
            ItemStack livingrock = getModItem(Mods.Botania.ID, "livingrock", 1, 0);
            ItemStack brewStand = getModItem(Mods.BrewingAPI.ID, "brewingapi_brewingStand2", 1, 0);
            ItemStack gold = new ItemStack(Items.gold_ingot);

            // spotless:off
            addShapedRecipe(
                getModItem(Mods.Botania.ID, "alchemyCatalyst", 1, 0),
                livingrock, gold, livingrock,
                brewStand, getModItem(Mods.Botania.ID, "manaResource", 1, 1), brewStand,
                livingrock, gold, livingrock);
            // spotless:on

            // spotless:off
            addShapedRecipe(
                getModItem(Mods.Botania.ID, "brewery", 1, 0),
                livingrock, gold, livingrock,
                livingrock, getModItem(Mods.Botania.ID, "rune", 1, 8), livingrock,
                livingrock, getModItem(Mods.Botania.ID, "storage", 1, 0), livingrock);
            // spotless:on
        }
    }
}
