package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.integration.thaumcraft.TCHelper;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;

/** Thaumcraft changes. From MoreRecipefixes.zs. References Thaumcraft classes: register behind a Thaumcraft check. */
public class ScriptThaumcraft implements IScriptLoader {

    @Override
    public String getScriptName() {
        return "Thaumcraft";
    }

    @Override
    public List<Mods> getDependencies() {
        return Collections.singletonList(Mods.Thaumcraft);
    }

    @Override
    public void loadRecipes() {
        ItemStack greatwoodLog = getModItem(Mods.Thaumcraft.ID, "blockMagicalLog", 1, 0);

        // 3 greatwood logs -> 12 greatwood slabs
        addShapedRecipe(
            getModItem(Mods.Thaumcraft.ID, "blockCosmeticSlabWood", 12, 0),
            greatwoodLog,
            greatwoodLog,
            greatwoodLog);

        // Greatwood wand core: 3 logs on a diagonal instead of 2. The arcane worktable checks normal crafting first,
        // so 2 logs (any logWood) matched the 2 logs -> 16 sticks recipe in ScriptMinecraft instead.
        ItemStack rod = getModItem(Mods.Thaumcraft.ID, "WandRod", 1, 0);
        TCHelper.removeArcaneRecipe(rod);
        AspectList cost = new AspectList().add(
            Aspect.ENTROPY,
            WandRod.rods.get("greatwood")
                .getCraftCost());
        // spotless:off
        ThaumcraftApi.addArcaneCraftingRecipe("ROD_greatwood", rod, cost,
            "  G",
            " G ",
            "G  ",
            'G', greatwoodLog);
        // spotless:on
        TCHelper.refreshResearchPages("ROD_greatwood");
    }

    /** Thaumic Darkness: the flight and transmutation foci and their research are removed. */
    public static class ThaumicDarkness implements IScriptLoader {

        @Override
        public String getScriptName() {
            return "Thaumic Darkness";
        }

        @Override
        public List<Mods> getDependencies() {
            return java.util.Arrays.asList(Mods.Thaumcraft, Mods.ThaumicDarkness);
        }

        @Override
        public void loadRecipes() {
            TCHelper.removeArcaneRecipe(getModItem(Mods.ThaumicDarkness.ID, "FocusFlight", 1, 0));
            TCHelper.orphanResearch("tc.research_name.FOCUS_FLIGHT");
            TCHelper.removeResearch("tc.research_name.FOCUS_FLIGHT");

            TCHelper.removeInfusionRecipe(getModItem(Mods.ThaumicDarkness.ID, "focusTrans", 1, 0));
            TCHelper.orphanResearch("FOCUS_TRANS");
            TCHelper.removeResearch("FOCUS_TRANS");
        }
    }
}
