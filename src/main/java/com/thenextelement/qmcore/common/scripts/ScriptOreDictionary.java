package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;

import java.util.Collections;
import java.util.List;

import com.thenextelement.qmcore.api.enums.Mods;

/** Ore dictionary additions. From Oredict.zs and MoreRecipefixes.zs. Missing items are skipped one by one. */
public class ScriptOreDictionary implements IScriptLoader {

    @Override
    public String getScriptName() {
        return "Ore Dictionary";
    }

    @Override
    public List<Mods> getDependencies() {
        return Collections.emptyList();
    }

    @Override
    public void loadRecipes() {
        // ---- meats and food (Oredict.zs)
        addOreDict("listAllmeatraw", getModItem(Mods.Witchery.ID, "ingredient", 1, 158));
        addOreDict("listAllmeatcooked", getModItem(Mods.Witchery.ID, "ingredient", 1, 159));
        addOreDict("listAllmeatraw", getModItem(Mods.TwilightForest.ID, "item.venisonRaw", 1, 0));
        addOreDict("listAllmeatcooked", getModItem(Mods.TwilightForest.ID, "item.venisonCooked", 1, 0));
        addOreDict("listAllmeatraw", getModItem(Mods.TwilightForest.ID, "item.meefRaw", 1, 0));
        addOreDict("listAllmeatcooked", getModItem(Mods.TwilightForest.ID, "item.meefSteak", 1, 0));
        addOreDict("foodBeetsoup", getModItem(Mods.EtFuturumRequiem.ID, "beetroot_soup", 1, 0));
        addOreDict("foodRawstew", getModItem(Mods.Witchery.ID, "stewraw", 1, 0));

        // ---- ender amethyst (Oredict.zs)
        addOreDict("oreEnderamethyst", getModItem(Mods.BiomesOPlenty.ID, "gemOre", 1, 0));
        addOreDict("blockEnderAmethyst", getModItem(Mods.BiomesOPlenty.ID, "gemOre", 1, 1));

        // ---- planks
        addOreDict("plankWood", getModItem(Mods.Natura.ID, "planks", 1, 12));
        for (String plank : new String[] { "skyroot_planks", "golden_oak_planks", "greatroot_planks", "wisproot_planks",
            "void_planks", "divine_oak_planks" }) {
            addOreDict("plankWood", getModItem(Mods.AetherLegacy.ID, plank, 1, 0));
        }
        addOreDict("plankWood", getModItem(Mods.ForbiddenMagic.ID, "TaintPlank", 1, 0));
        addOreDict("plankWood", getModItem(Mods.Botania.ID, "livingwood", 1, 1));
        addOreDict("plankWood", getModItem(Mods.Botania.ID, "livingwood", 1, 2));
        addOreDict("plankWood", getModItem(Mods.Botania.ID, "dreamwood", 1, 1));
        addOreDict("plankWood", getModItem(Mods.Botania.ID, "dreamwood", 1, 2));

        // ---- ingots
        addOreDict("ingotDarkness", getModItem(Mods.ThaumicDarkness.ID, "DarkIngot", 1, 0));
        addOreDict("ingotBloodIron", getModItem(Mods.BloodArsenal.ID, "blood_infused_iron", 1, 0));

        // ---- wooden trapdoors
        for (String wood : new String[] { "dark_oak", "birch", "acacia", "jungle", "spruce" }) {
            addOreDict("trapdoorWood", getModItem(Mods.MalisisDoors.ID, "trapdoor_" + wood, 1, 0));
        }
        for (String wood : new String[] { "eucalyptus", "darkwood", "willow", "tiger", "silverbell", "amaranth",
            "maple", "hopseed", "bloodwood", "redwood", "ghostwood", "sakura" }) {
            addOreDict("trapdoorWood", getModItem(Mods.Natura.ID, "trapdoor." + wood, 1, 0));
        }
        for (String wood : new String[] { "Twilight", "Canopy", "Mangrove", "Darkwood", "Time", "Trans", "Mine",
            "Sort" }) {
            addOreDict("trapdoorWood", getModItem(Mods.TwilightForest.ID, "tile.TFTrapDoor" + wood, 1, 0));
        }

        // ---- blood orbs
        for (String orb : new String[] { "weakBloodOrb", "apprenticeBloodOrb", "magicianBloodOrb", "masterBloodOrb",
            "archmageBloodOrb", "transcendentBloodOrb" }) {
            addOreDict("bloodOrb", getModItem(Mods.BloodMagic.ID, orb, 1, 0));
        }
        addOreDict("bloodOrb", getModItem(Mods.ForbiddenMagic.ID, "EldritchOrb", 1, 0));
        addOreDict("bloodOrb", getModItem(Mods.BloodArsenal.ID, "transparent_orb", 1, 0));
    }
}
