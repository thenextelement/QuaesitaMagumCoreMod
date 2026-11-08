package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;
import static com.thenextelement.qmcore.common.scripts.RecipeRemover.removeRecipeByOutput;

import java.util.Arrays;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.thenextelement.qmcore.api.enums.Materials;
import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.api.enums.OrePrefixes;

/**
 * Template for converting a MineTweaker .zs file into Java. Copy this class, rename it Script&lt;ModName&gt;, and add
 * it in {@link ScriptLoader#getScripts()}. This template itself is not registered.
 * <p>
 * MineTweaker to Java:
 *
 * <pre>
 * &lt;witchery:ingredient:20&gt;                getModItem(Mods.Witchery.ID, "ingredient", 1, 20)
 * &lt;witchery:ingredient:*&gt;                 getModItem(Mods.Witchery.ID, "ingredient", 1, wildcard)
 * &lt;minecraft:stick&gt; * 4                  new ItemStack(Items.stick, 4)
 * &lt;ore:ingotCopper&gt;                       "ingotCopper"  or  OrePrefixes.ingot.get(Materials.Copper)
 * recipes.remove(x);                       removeRecipeByOutput(x);         (in removeRecipes())
 * furnace.remove(x);                       RecipeRemover.removeFurnaceRecipeByOutput(x);
 * recipes.addShaped(out, [[a,b,c],[d,e,f],[g,h,i]]);   addShapedRecipe(out, a, b, c, d, e, f, g, h, i);
 * recipes.addShapeless(out, [a, b]);       addShapelessRecipe(out, a, b);
 * &lt;ore:gemAmber&gt;.add(x);                  OreDictionary.registerOre("gemAmber", x);
 * mods.thaumcraft.Research.*, Arcane.*,    TCHelper.* and ThaumcraftApi.* (integration.thaumcraft)
 *   Infusion.*, Crucible.*, Aspects.*
 * </pre>
 *
 * Shaped inputs are read row by row, 3 per row (pass 9, or 3/6 for 1-2 rows); empty slots are {@code null}. A 2x2
 * recipe is {@code a, b, null, c, d, null}. The pattern is shrunk to the used area, so it also works in the 2x2
 * inventory grid and in any position.
 */
public class ScriptTemplate implements IScriptLoader {

    @Override
    public String getScriptName() {
        return "Template";
    }

    @Override
    public List<Mods> getDependencies() {
        // The script is skipped unless all of these are loaded.
        return Arrays.asList(Mods.Witchery, Mods.Thaumcraft);
    }

    @Override
    public void removeRecipes() {
        // recipes.remove(<witchery:witchesovenidle>);
        removeRecipeByOutput(getModItem(Mods.Witchery.ID, "witchesovenidle", 1, 0));
    }

    @Override
    public void loadRecipes() {
        // recipes.addShaped(<witchery:witchesovenidle>, [[<ore:ingotIron>, <minecraft:iron_bars>, <ore:ingotIron>],
        // [<ore:ingotIron>, <minecraft:furnace>, <ore:ingotIron>], [<ore:ingotIron>, <ore:ingotIron>,
        // <ore:ingotIron>]]);
        String iron = OrePrefixes.ingot.get(Materials.Iron);
        // spotless:off
        addShapedRecipe(
            getModItem(Mods.Witchery.ID, "witchesovenidle", 1, 0),
            iron, Blocks.iron_bars, iron,
            iron, Blocks.furnace, iron,
            iron, iron, iron);
        // spotless:on

        // recipes.addShapeless(<minecraft:string> * 4, [<ore:blockWool>]);
        addShapelessRecipe(new ItemStack(Items.string, 4), "blockWool");

        // <ore:gemAmber>.add(<Thaumcraft:ItemResource:6>);
        OreDictionary.registerOre("gemAmber", getModItem(Mods.Thaumcraft.ID, "ItemResource", 1, 6));
    }
}
