package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.common.tech.DisabledTech;

/**
 * Binnie's RF tech (see DisabledTech). The mixins in {@code mixin.mixins.late.binnie} cut the machines' power and keep
 * them out of the creative tabs. This removes the recipes and hides:
 * <ul>
 * <li>all of Genetics. Its machines all take RF, and its handheld items (Analyst, Registry, Master Registry, gene
 * database) and the Laboratory are crafted from Integrated Circuits or Laboratory Casings, both made from removed
 * Forestry parts. The mod stays loaded, because BinnieCore's fluid containers and analysis code reference its
 * classes;</li>
 * <li>Binnie Core's Field Kit and the Genetics glass cylinders that Binnie Core registers;</li>
 * <li>the Extra Bees Stimulator and Transmission alveary parts;</li>
 * <li>the Extra Trees Lumbermill.</li>
 * </ul>
 * Kept: compartments, the Extra Trees designer machines and the unpowered Extra Bees alveary parts.
 */
public class ScriptBinnie implements IScriptLoader {

    private static final String[] GENETICS = { //
        "machine", // Isolator, Sequencer, Polymeriser, Inoculator
        "labMachine", // Laboratory, Analyser, Incubator, Genepool, Acclimatiser
        "advMachine", // Splicer
        "misc", // casings, dyes, enzyme, growth medium, empty vials, cylinder, integrated circuits
        "serum", "serumArray", "sequence", "database", "analyst", "registry", "masterRegistry" };

    @Override
    public String getScriptName() {
        return "Binnie RF tech";
    }

    @Override
    public List<Mods> getDependencies() {
        return Collections.singletonList(Mods.BinnieCore);
    }

    @Override
    public boolean isScriptLoadable() {
        return DisabledTech.isEnabled() && IScriptLoader.super.isScriptLoadable();
    }

    @Override
    public void removeRecipes() {
        for (ItemStack stack : techItems()) {
            RecipeRemover.removeRecipeByOutput(stack);
            RecipeRemover.removeFurnaceRecipeByOutput(stack); // Genetics smelts used serums back into empty vials
        }
    }

    @Override
    public void loadRecipes() {
        for (ItemStack stack : techItems()) hideItem(stack);
    }

    private static List<ItemStack> techItems() {
        List<ItemStack> list = new ArrayList<>();
        list.add(getModItem(Mods.BinnieCore.ID, "fieldKit", 1, wildcard));
        list.add(getModItem(Mods.BinnieCore.ID, "containerCylinder", 1, wildcard));
        if (Mods.Genetics.isModLoaded()) {
            for (String name : GENETICS) list.add(getModItem(Mods.Genetics.ID, name, 1, wildcard));
        }
        if (Mods.ExtraBees.isModLoaded()) {
            list.add(getModItem(Mods.ExtraBees.ID, "alveary", 1, DisabledTech.EXTRABEES_ALVEARY_STIMULATOR));
            list.add(getModItem(Mods.ExtraBees.ID, "alveary", 1, DisabledTech.EXTRABEES_ALVEARY_TRANSMISSION));
        }
        if (Mods.ExtraTrees.isModLoaded()) {
            list.add(getModItem(Mods.ExtraTrees.ID, "machine", 1, DisabledTech.EXTRATREES_LUMBERMILL));
        }
        return list;
    }
}
