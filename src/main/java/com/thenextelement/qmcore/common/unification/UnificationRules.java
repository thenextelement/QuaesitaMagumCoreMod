package com.thenextelement.qmcore.common.unification;

import static com.thenextelement.qmcore.api.enums.Mods.ModIDs.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The pack's ore dictionary unification table: for every metal, gem and dust that more than one mod adds, which item
 * stays (the winner) and which are unified away (the losers).
 * <p>
 * Built from the MQ 2.7.1 {@code /minetweaker oredict} dump. Policy: Forestry and ProjectRed lose every duplicate
 * (their unique items such as apatite, bronze, silver, red alloy and electrotine stay); tin is ProjectRed's, copper is
 * Et Futurum's, ruby/sapphire/peridot are Biomes O' Plenty's, amber and metal nuggets are Thaumcraft's. Tinkers' and
 * Draconic entries are out of scope and left alone.
 * <p>
 * Items are written as {@code modId, registryName, meta}, exactly as in the dump ({@code <modId:registryName:meta>}).
 */
public final class UnificationRules {

    private UnificationRules() {}

    /** A loser item and the winner it becomes. */
    public static final class Rule {

        public final ItemRef loser, winner;

        Rule(ItemRef loser, ItemRef winner) {
            this.loser = loser;
            this.winner = winner;
        }
    }

    /** An item that keeps existing but is taken out of one ore dictionary name (no replacement). */
    public static final class Delist {

        public final String oreName;
        public final ItemRef item;

        Delist(String oreName, ItemRef item) {
            this.oreName = oreName;
            this.item = item;
        }
    }

    public static final class ItemRef {

        public final String modId, name;
        public final int meta;

        ItemRef(String modId, String name, int meta) {
            this.modId = modId;
            this.name = name;
            this.meta = meta;
        }

        public String key() {
            return modId + ":" + name + ":" + meta;
        }

        @Override
        public String toString() {
            return "<" + key() + ">";
        }
    }

    public static final List<Rule> RULES;
    public static final List<Delist> DELISTS;

    private static final List<Rule> rules = new ArrayList<>();
    private static final List<Delist> delists = new ArrayList<>();

    private static ItemRef item(String modId, String name, int meta) {
        return new ItemRef(modId, name, meta);
    }

    /** Every {@code losers} item becomes {@code winner}. */
    private static void unify(ItemRef winner, ItemRef... losers) {
        for (ItemRef loser : losers) rules.add(new Rule(loser, winner));
    }

    static {
        final String PR_CORE = PROJECT_RED_CORE, PR_EXPL = PROJECT_RED_EXPLORATION;

        // ==========================================COPPER: Et
        // Futurum================================================//
        unify(
            item(ET_FUTURUM_REQUIEM, "copper_ingot", 0),
            item(FORESTRY, "ingotCopper", 0),
            item(PR_CORE, "projectred.core.part", 52));
        unify(
            item(ET_FUTURUM_REQUIEM, "copper_block", 0),
            item(FORESTRY, "resourceStorage", 1),
            item(PR_EXPL, "projectred.exploration.stone", 8));
        unify(
            item(ET_FUTURUM_REQUIEM, "copper_ore", 0),
            item(FORESTRY, "resources", 1),
            item(PR_EXPL, "projectred.exploration.ore", 3));

        // ==========================================TIN:
        // ProjectRed===================================================//
        unify(item(PR_CORE, "projectred.core.part", 53), item(FORESTRY, "ingotTin", 0));
        unify(item(PR_EXPL, "projectred.exploration.stone", 9), item(FORESTRY, "resourceStorage", 2));
        unify(item(PR_EXPL, "projectred.exploration.ore", 4), item(FORESTRY, "resources", 2));

        // ==========================================RUBY / SAPPHIRE / PERIDOT: Biomes O'
        // Plenty=======================//
        unify(item(BIOMES_O_PLENTY, "gems", 1), item(PR_CORE, "projectred.core.part", 37)); // ruby
        unify(item(BIOMES_O_PLENTY, "gems", 6), item(PR_CORE, "projectred.core.part", 38)); // sapphire
        unify(item(BIOMES_O_PLENTY, "gems", 2), item(PR_CORE, "projectred.core.part", 39)); // peridot
        unify(item(BIOMES_O_PLENTY, "gemOre", 2), item(PR_EXPL, "projectred.exploration.ore", 0)); // ruby ore
        unify(item(BIOMES_O_PLENTY, "gemOre", 12), item(PR_EXPL, "projectred.exploration.ore", 1)); // sapphire ore
        unify(item(BIOMES_O_PLENTY, "gemOre", 4), item(PR_EXPL, "projectred.exploration.ore", 2)); // peridot ore
        unify(item(BIOMES_O_PLENTY, "gemOre", 3), item(PR_EXPL, "projectred.exploration.stone", 5)); // ruby block
        unify(item(BIOMES_O_PLENTY, "gemOre", 13), item(PR_EXPL, "projectred.exploration.stone", 6)); // sapphire block
        unify(item(BIOMES_O_PLENTY, "gemOre", 5), item(PR_EXPL, "projectred.exploration.stone", 7)); // peridot block

        // ==========================================AMBER:
        // Thaumcraft=================================================//
        unify(item(THAUMCRAFT, "ItemResource", 6), item(BIOMES_O_PLENTY, "gems", 7));
        unify(item(THAUMCRAFT, "blockCustomOre", 7), item(BIOMES_O_PLENTY, "gemOre", 14));

        // ==========================================NUGGETS===========================================================//
        unify(
            item(THAUMCRAFT, "ItemNugget", 0), // iron
            item(GARDEN_STUFF, "iron_nugget", 0),
            item(MAGIC_BEES, "beeNugget", 0),
            item(ET_FUTURUM_REQUIEM, "nugget_iron", 0),
            item(NETHERLICIOUS, "Nugget", 0),
            item(TOTEMIC, "subItems", 0));
        unify(item(THAUMCRAFT, "ItemNugget", 1), item(MAGIC_BEES, "beeNugget", 1)); // copper
        unify(item(THAUMCRAFT, "ItemNugget", 2), item(MAGIC_BEES, "beeNugget", 2)); // tin
        unify(item(THAUMCRAFT, "ItemNugget", 3), item(MAGIC_BEES, "beeNugget", 3)); // silver
        unify(item(THAUMCRAFT, "ItemNugget", 4), item(MAGIC_BEES, "beeNugget", 4)); // lead
        unify(item(MINECRAFT, "gold_nugget", 0), item(THAUMCRAFT, "ItemResource", 18)); // gold: vanilla
        unify(item(BOTANIA, "manaResource", 17), item(FORBIDDEN_MAGIC, "FMResource", 2)); // manasteel
        unify(item(BOTANIA, "manaResource", 19), item(FORBIDDEN_MAGIC, "FMResource", 4)); // elementium
        unify(item(MAGIC_BEES, "beeNugget", 6), item(FORBIDDEN_MAGIC, "FMResource", 0)); // emerald
        unify(item(MAGIC_BEES, "beeNugget", 5), item(TRANSLOCATOR, "diamondNugget", 0)); // diamond

        // ==========================================OTHER=============================================================//
        unify(
            item(THAUMIC_BASES, "voidBlock", 0),
            item(WITCHING_GADGETS, "WG_MetalDevice", 1),
            item(HAMMERZ, "Storage", 0));
        unify(item(PAMS_HARVEST_CRAFT, "saltItem", 0), item(GROWTHCRAFT, "grccore.salt", 0));
        unify(item(WITCHING_GADGETS, "item.WG_Cluster", 2001), item(ANOTHER_ONE_BITES_THE_DUST, "clusterCobalt", 0));
        unify(item(WITCHING_GADGETS, "item.WG_Cluster", 2002), item(ANOTHER_ONE_BITES_THE_DUST, "clusterArdite", 0));
        unify(item(WITCHING_GADGETS, "item.WG_Cluster", 2033), item(ANOTHER_ONE_BITES_THE_DUST, "clusterIridium", 0));

        // Thaumic Bases' decorative "old" blocks: kept, but no longer storage blocks
        delists.add(new Delist("blockGold", item(THAUMIC_BASES, "oldGold", 0)));
        delists.add(new Delist("blockIron", item(THAUMIC_BASES, "oldIron", 0)));
        delists.add(new Delist("blockLapis", item(THAUMIC_BASES, "oldLapis", 0)));
        delists.add(new Delist("blockDiamond", item(THAUMIC_BASES, "oldDiamond", 0)));

        RULES = Collections.unmodifiableList(rules);
        DELISTS = Collections.unmodifiableList(delists);
    }
}
