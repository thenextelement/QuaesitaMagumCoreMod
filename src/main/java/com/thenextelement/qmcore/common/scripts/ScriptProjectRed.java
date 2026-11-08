package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.api.enums.Mods;

/**
 * ProjectRed Core content that only fed the tech modules (Transmission, Integration, Fabrication, Expansion,
 * Transportation), which are force-disabled. Recipes are removed and the items hidden. Kept: what Illumination and
 * Exploration still use (plate, conductive plate, cathode, illumars, woven cloth, iron coil) and the pack's metals
 * (tin, silver, red alloy, electrotine and their intermediates).
 */
public class ScriptProjectRed implements IScriptLoader {

    /** projectred.core.part metas, in PartDefs order. */
    private static final int[] TECH_PARTS = { //
        2, // wired plate
        3, // bundled plate
        4, // anode
        6, // pointer
        7, // silicon chip
        8, // energized silicon chip
        9, // platformed plate
        11, // silicon boule
        12, // silicon
        13, // infused silicon
        14, // energized silicon
        15, // motor
        16, // copper coil
        18, // gold coil
        36, // sail
        41, // sand coal compound
        42, // red silicon compound
        43, // glowing silicon compound
        44, // null chip
        45, // null upgrade
        46, 47, 48, 49, 50, 51, // upgrade chips LX..RZ
        58, // electrotine silicon compound
        59, // electro silicon
    };

    private static final String[] TECH_TOOLS = { "projectred.core.screwdriver", "projectred.core.wiredebugger",
        "projectred.core.drawplate", "projectred.core.datacard" };

    @Override
    public String getScriptName() {
        return "ProjectRed";
    }

    @Override
    public List<Mods> getDependencies() {
        return Collections.singletonList(Mods.ProjectRedCore);
    }

    @Override
    public void removeRecipes() {
        for (ItemStack stack : techItems()) {
            RecipeRemover.removeRecipeByOutput(stack);
            RecipeRemover.removeFurnaceRecipeByOutput(stack);
        }
    }

    @Override
    public void loadRecipes() {
        for (ItemStack stack : techItems()) hideItem(stack);
    }

    private static List<ItemStack> techItems() {
        List<ItemStack> list = new java.util.ArrayList<>();
        for (int meta : TECH_PARTS) list.add(getModItem(Mods.ProjectRedCore.ID, "projectred.core.part", 1, meta));
        for (String tool : TECH_TOOLS) list.add(getModItem(Mods.ProjectRedCore.ID, tool, 1, wildcard));
        return list;
    }
}
