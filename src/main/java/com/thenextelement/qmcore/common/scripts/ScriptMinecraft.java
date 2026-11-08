package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.RecipeRemover.removeRecipeByOutput;

import java.util.Collections;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.api.enums.Mods;

/** Vanilla recipe changes. From MoreRecipefixes.zs. */
public class ScriptMinecraft implements IScriptLoader {

    @Override
    public String getScriptName() {
        return "Minecraft";
    }

    @Override
    public List<Mods> getDependencies() {
        return Collections.emptyList();
    }

    @Override
    public void removeRecipes() {
        removeRecipeByOutput(new ItemStack(Blocks.wooden_slab, 1, 0)); // oak slabs
        removeRecipeByOutput(new ItemStack(Blocks.trapdoor));
    }

    @Override
    public void loadRecipes() {
        // 2 logs -> 16 sticks. In the arcane worktable this used to beat the Greatwood wand core (2 greatwood logs
        // on a diagonal); that recipe now takes 3 logs, see ScriptThaumcraft.
        addShapelessRecipe(new ItemStack(Items.stick, 16), "logWood", "logWood");

        // 8 logs -> 4 chests
        // spotless:off
        addShapedRecipe(
            new ItemStack(Blocks.chest, 4),
            "logWood", "logWood", "logWood",
            "logWood", null, "logWood",
            "logWood", "logWood", "logWood");
        // spotless:on

        // wheat -> seeds
        addShapedRecipe(new ItemStack(Items.wheat_seeds, 2), Items.wheat, Items.wheat, null);

        // oak slabs: 3 planks -> 3, 3 logs -> 12
        ItemStack plankOak = new ItemStack(Blocks.planks, 1, 0), logOak = new ItemStack(Blocks.log, 1, 0);
        addShapedRecipe(new ItemStack(Blocks.wooden_slab, 3, 0), plankOak, plankOak, plankOak);
        addShapedRecipe(new ItemStack(Blocks.wooden_slab, 12, 0), logOak, logOak, logOak);

        // oak trapdoors: 6 planks -> 2, 6 logs -> 8
        // spotless:off
        addShapedRecipe(
            new ItemStack(Blocks.trapdoor, 2),
            plankOak, plankOak, plankOak,
            plankOak, plankOak, plankOak);
        // spotless:on

        // spotless:off
        addShapedRecipe(
            new ItemStack(Blocks.trapdoor, 8),
            logOak, logOak, logOak,
            logOak, logOak, logOak);
        // spotless:on
    }
}
