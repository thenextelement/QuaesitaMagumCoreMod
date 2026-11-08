package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;

import java.util.Arrays;
import java.util.List;

import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.integration.thaumcraft.TCAspects;

/**
 * Thaumcraft aspects for Et Futurum. Converted from "TC4 - Et_FuturamAspects.zs" by daforsyth.
 * References Thaumcraft classes: register behind a Thaumcraft check.
 */
public class ScriptAspectsEtFuturum implements IScriptLoader {

    @Override
    public String getScriptName() {
        return "Et Futurum Aspects";
    }

    @Override
    public List<Mods> getDependencies() {
        return Arrays.asList(Mods.Thaumcraft, Mods.EtFuturumRequiem, Mods.ForbiddenMagic);
    }

    @Override
    public void loadRecipes() {

        // Bees
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "honeycomb", 1, 0), "ordo 2, fames 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "honey_bottle", 1, 0), "limus 1, fames 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bee_nest", 1, 0), "tutamen 3, vacuos 2, ordo 2");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "honey_block", 1, 0), "limus 13, fames 13, vinculum 5");

        // Lights
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "soul_torch", 1, 0), "lux 1, spiritus 1");
        TCAspects.add(getModItem(Mods.EtFuturumRequiem.ID, "soul_lantern", 1, 0), "lux 1, spiritus 1");
        TCAspects.add(getModItem(Mods.EtFuturumRequiem.ID, "lantern", 1, 0), "lux 1");

        // Plants
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bamboo", 1, 0), "herba 1, victus 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "sapling", 1, 1), "herba 2, arbor 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "pink_petals", 1, 0), "herba 1, sensus 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "glow_lichen", 1, 0), "herba 1, tenebrae 1, lux 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "glow_berries_item", 1, 0), "messis 1, lux 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "leaves", 1, 1), "herba 1");

        // Ores and metals

        // Iron Nugget
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "nugget_iron", 1, 0), "metallum 1");

        // Copper Ingot
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "copper_ingot", 1, 0), "metallum 3, permutatio 2");

        // Raw Copper Ore
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "raw_ore", 1, 0), "metallum 2, permutatio 1");

        // Raw Iron Ore
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "raw_ore", 1, 1), "metallum 2");

        // Raw Gold Ore
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "raw_ore", 1, 2), "metallum 2, lucrum 1");

        // Raw Copper Ore Block
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "raw_ore_block", 1, 0), "metallum 13, permutatio 9");

        // Raw Iron Ore Block
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "raw_ore_block", 1, 1), "metallum 20");

        // Raw Gold Ore Block
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "raw_ore_block", 1, 2), "metallum 13, lucrum 9");

        // Ancient Debris
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "ancient_debris", 1, 0), "infernus 4, metallum 3, alienis 1");

        // Netherite Scrap
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "netherite_scrap", 1, 0), "infernus 1, metallum 1, alienis 1");

        // Netherite Ingot
        TCAspects.add(getModItem(Mods.EtFuturumRequiem.ID, "netherite_ingot", 1, 0), "fabrico 3");

        // netherite Block
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_block", 1, 0),
            "metallum 40, lucrum 26, infernus 14, alienis 14, fabrico 14");

        // Copper Ore
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "copper_ore", 1, 0), "metallum 2, terra 1, permutatio 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "deepslate_copper_ore", 1, 0),
            "metallum 2, terra 1, permutatio 1");

        // Thaumcraft Ores
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_thaumcraft_ore", 1, 2), "ignis 3, vitreus 2, terra 1");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_thaumcraft_ore", 1, 5), "ordo 3, vitreus 2, terra 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "deepslate_thaumcraft_ore", 1, 6),
            "perditio 3, vitreus 2, terra 1");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_thaumcraft_ore", 1, 1), "aer 3, vitreus 2, terra 1");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_thaumcraft_ore", 1, 3), "aqua 3, vitreus 2, terra 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_thaumcraft_ore", 1, 4), "terra 4, vitreus 2");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "deepslate_thaumcraft_ore", 1, 0),
            "metallum 2, permutatio 2, terra 1, venenum 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "deepslate_thaumcraft_ore", 1, 7),
            "vinculum 3, vitreus 2, terra 1");

        // Ars Magica Ores
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_am2_ore", 1, 2), "potentia 2, vitreus 2, terra 1");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_am2_ore", 1, 1), "sensus 6, permutatio 3, terra 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_am2_ore", 1, 0), "praecantatio 4, terra 1");

        // Biomes O' Plenty Ores
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_bop_ore", 1, 0), "vitreus 5, lucrum 3, terra 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_bop_ore", 1, 1), "vitreus 5, lucrum 3, terra 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_bop_ore", 1, 2), "vitreus 5, lucrum 3, terra 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_bop_ore", 1, 3), "vitreus 5, lucrum 3, terra 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_bop_ore", 1, 4), "vitreus 5, lucrum 3, terra 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_bop_ore", 1, 5), "vitreus 5, lucrum 3, terra 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_bop_ore", 1, 6), "vitreus 5, lucrum 3, terra 3");

        // Netherite Items

        // Armor
        TCAspects.add(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_helmet", 1, 0),
            "vitreus 15, infernus 3, lucrum 21, metallum 12");
        TCAspects.add(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_chestplate", 1, 0),
            "vitreus 24, infernus 3, lucrum 30, metallum 12");
        TCAspects.add(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_leggings", 1, 0),
            "vitreus 21, infernus 3, lucrum 27, metallum 12");
        TCAspects.add(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_boots", 1, 0),
            "vitreus 12, infernus 3, lucrum 18, metallum 12");

        // Tools
        TCAspects.add(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_spade", 1, 0),
            "vitreus 3, infernus 3, lucrum 9, metallum 12");
        TCAspects.add(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_pickaxe", 1, 0),
            "vitreus 9, infernus 3, lucrum 15, metallum 12");
        TCAspects.add(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_axe", 1, 0),
            "vitreus 9, infernus 3, lucrum 15, metallum 12");
        TCAspects.add(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_hoe", 1, 0),
            "vitreus 6, infernus 3, lucrum 12, metallum 12");
        TCAspects.add(
            getModItem(Mods.EtFuturumRequiem.ID, "netherite_sword", 1, 0),
            "vitreus 6, infernus 3, lucrum 12, metallum 12");

        // Dyes

        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "dye", 1, 3), "sensus 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "dye", 1, 2), "sensus 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "dye", 1, 1), "sensus 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "dye", 1, 0), "sensus 1");

        // Blocks

        // red sandstone
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "smooth_red_sandstone", 1, 0), "terra 2, ordo 1");

        // bricks
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "end_bricks", 1, 0), "terra 1, tenebrae 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "mud", 1, 0), "terra 2, aqua 2, vinculum 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "packed_mud", 1, 1), "terra 2, messis 1");

        // tuff
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "tuff", 1, 1), "terra 2, perditio 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "tuff", 1, 2), "terra 2, perditio 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "tuff", 1, 3), "terra 2, perditio 1, ordo 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "tuff", 1, 4), "terra 2, perditio 1");

        // blackstone
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "blackstone", 1, 0), "terra 2, ignis 2");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "blackstone", 1, 1), "terra 2, ignis 2");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "blackstone", 1, 2), "terra 2, ignis 2");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "blackstone", 1, 3), "terra 2, ignis 2, perditio 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "blackstone", 1, 4), "terra 2, ignis 2, ordo 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "gilded_blackstone", 1, 0), "terra 2, ignis 2, lucrum 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "blackstone_slab", 1, 0), "terra 1, ignis 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "blackstone_slab", 1, 1), "terra 1, ignis 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "blackstone_slab", 1, 2), "terra 1, ignis 1");

        // basalt
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "basalt", 1, 0), "terra 2, ignis 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "basalt", 1, 1), "terra 2, ignis 3");

        // soul soil
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "soul_soil", 1, 0), "terra 4, vinculum 4, spiritus 4");

        // crying obby
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "crying_obsidian", 1, 0), "terra 2, ignis 2, tenebrae 1, ira 1");

        // Quartz
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "quartz_bricks", 1, 0), "vitreus 1, potentia 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "smooth_quartz", 1, 0), "vitreus 1, potentia 1, ordo 1");

        // terracotta
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "black_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "red_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "green_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "brown_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "blue_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "purple_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "cyan_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "light_gray_glazed_terracotta", 1, 0),
            "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "gray_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "pink_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "lime_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "yellow_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "light_blue_glazed_terracotta", 1, 0),
            "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "magenta_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "orange_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "white_glazed_terracotta", 1, 0), "terra 3, ignis 1, sensus 3");

        // Wood
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "cherry_log", 1, 0), "arbor 4");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bamboo_block", 1, 0), "herba 6, victus 6");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "wood_planks", 1, 4), "herba 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "wood_planks", 1, 3), "arbor 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "cherry_log", 1, 2), "arbor 4");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bamboo_block", 1, 1), "herba 6, victus 6");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bamboo_sign", 1, 0), "herba 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "cherry_sign", 1, 0), "arbor 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "cherry_chest_boat", 1, 0), "aqua 4, iter 4, arbor 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "cherry_boat", 1, 0), "aqua 4, iter 4, arbor 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bamboo_chest_raft", 1, 0), "aqua 4, iter 4, herba 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bamboo_raft", 1, 0), "aqua 4, iter 4, herba 3");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bamboo_mosaic", 1, 0), "herba 1, ordo 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bamboo_mosaic_stairs", 1, 0), "herba 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "wood_slab", 1, 4), "herba 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "bamboo_mosaic_slab", 1, 0), "herba 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "wood_slab", 1, 3), "arbor 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "cherry_stairs", 1, 0), "arbor 1");

        // Concrete
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "concrete", 1, wildcard), "terra 2");

        // Slabs
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_brick_slab", 1, 1), "terra 2, tenebrae 2");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "deepslate_brick_slab", 1, 0), "terra 2, tenebrae 2");

        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "stone_slab", 1, wildcard), "terra 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "tuff_slab", 1, wildcard), "terra 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "red_sandstone_slab", 1, 1), "terra 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "smooth_red_sandstone_slab", 1, 0), "terra 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "end_brick_slab", 1, 0), "terra 1");

        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "smooth_quartz_slab", 1, 0), "vitreus 1, potentia 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "mud_brick_slab", 1, 0), "terra 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "end_brick_wall", 1, 0), "terra 1");

        // Copper blocks

        // Copper Bulb
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "copper_bulb", 1, wildcard), "metallum 11, permutatio 7, lux 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "copper_bulb", 1, 1), "metallum 11, permutatio 7, lux 1");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "copper_bulb", 1, 0), "metallum 11, permutatio 7, lux 1");

        // Copper Grate
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "copper_grate", 1, wildcard), "metallum 15, permutatio 9");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "copper_grate", 1, 1), "metallum 15, permutatio 9");

        // Chisled Copper and Copper Block
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "copper_block", 1, wildcard), "metallum 20, permutatio 13");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "chiseled_copper", 1, wildcard), "metallum 7, permutatio 4");

        // Copper Door
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "copper_door", 1, wildcard),
            "metallum 4, permutatio 3, machina 1, motus 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "exposed_copper_door", 1, 0),
            "metallum 4, permutatio 3, machina 1, motus 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "weathered_copper_door", 1, 0),
            "metallum 4, permutatio 3, machina 1, motus 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "oxidized_copper_door", 1, 0),
            "metallum 4, permutatio 3, machina 1, motus 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "waxed_copper_door", 1, 0),
            "metallum 4, permutatio 3, machina 1, motus 1, ordo 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "waxed_exposed_copper_door", 1, 0),
            "metallum 4, permutatio 3, machina 1, motus 1, ordo 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "waxed_weathered_copper_door", 1, 0),
            "metallum 4, permutatio 3, machina 1, motus 1, ordo 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "waxed_oxidized_copper_door", 1, 0),
            "metallum 4, permutatio 3, machina 1, motus 1, ordo 1");

        // Copper Trapdoor
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "copper_trapdoor", 1, wildcard),
            "metallum 4, permutatio 3, motus 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "exposed_copper_trapdoor", 1, 0),
            "metallum 4, permutatio 3, motus 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "weathered_copper_trapdoor", 1, 0),
            "metallum 4, permutatio 3, motus 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "oxidized_copper_trapdoor", 1, 0),
            "metallum 4, permutatio 3, motus 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "waxed_copper_trapdoor", 1, 0),
            "metallum 4, permutatio 3, motus 1, ordo 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "waxed_exposed_copper_trapdoor", 1, 0),
            "metallum 4, permutatio 3, motus 1, ordo 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "waxed_weathered_copper_trapdoor", 1, 0),
            "metallum 4, permutatio 3, motus 1, ordo 1");
        TCAspects.set(
            getModItem(Mods.EtFuturumRequiem.ID, "waxed_oxidized_copper_trapdoor", 1, 0),
            "metallum 4, permutatio 3, motus 1, ordo 1");

        // Copper Slab and Stair
        TCAspects
            .set(getModItem(Mods.EtFuturumRequiem.ID, "cut_copper_stairs", 1, wildcard), "metallum 16, permutatio 10");
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "cut_copper_slab", 1, wildcard), "metallum 5, permutatio 3");

        // mobs

        // Shulker
        TCAspects.set(getModItem(Mods.EtFuturumRequiem.ID, "shulker_shell", 1, 0), "tutamen 4, alienis 2");
        TCAspects.add(getModItem(Mods.EtFuturumRequiem.ID, "shulker_box", 1, 0), "tutamen 8, alienis 4");
        TCAspects.addEntity("etfuturum.shulker", "alienis 3, tutamen 4, iter 2, praecantatio 2");

        // Other
        TCAspects.addEntity("etfuturum.bee", "bestia 2, volatus 2, aer 1");
        TCAspects.addEntity("etfuturum.fox", "bestia 3, cognitio 2, motus 2, terra 1");
    }
}
