package com.thenextelement.qmcore.api.enums;

import static com.thenextelement.qmcore.api.enums.Materials.Type.DUST;
import static com.thenextelement.qmcore.api.enums.Materials.Type.GEM;
import static com.thenextelement.qmcore.api.enums.Materials.Type.METAL;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Metals, gems and dusts found in the pack's ore dictionary. A standalone, much smaller take on GT5-Unofficial's
 * {@code Materials}: each entry is a name, a type and its alternate spellings, with no stats, colors or recipes.
 * <p>
 * The constant name is the ore dictionary suffix, so {@code OrePrefixes.ingot.get(Materials.Copper)} is
 * {@code ingotCopper}. The list comes from a scan of the MQ 2.7.1 jars; Tinkers' and Draconic materials are left out on
 * purpose. Which mod provides each material, and which variant wins, is decided in the unification step.
 */
public enum Materials {

    // ==========================================METALS: vanilla and common=========================================//
    Iron(METAL),
    Gold(METAL),
    Copper(METAL),
    Tin(METAL),
    Silver(METAL),
    Lead(METAL),
    Nickel(METAL),
    Platinum(METAL),
    Aluminum(METAL, "Aluminium"),
    Zinc(METAL),
    Cobalt(METAL),
    Osmium(METAL),
    Titanium(METAL),
    Tungsten(METAL),
    Uranium(METAL),
    Iridium(METAL),
    Mithril(METAL),
    Adamantium(METAL, "Adamantine"),

    // alloys
    Bronze(METAL),
    Brass(METAL),
    Steel(METAL),
    Electrum(METAL),
    Invar(METAL),
    WroughtIron(METAL),
    PigIron(METAL),
    Netherite(METAL),

    // ==========================================METALS: magic=====================================================//
    Thaumium(METAL),
    Void(METAL),
    Thauminite(METAL),
    Shadow(METAL),
    Manasteel(METAL),
    Terrasteel(METAL),
    ElvenElementium(METAL),
    HeeEndium(METAL),

    // Twilight Forest
    Ironwood(METAL),
    Knightmetal(METAL),
    Fiery(METAL),

    // Aether
    Zanite(GEM),
    Arkenium(METAL),
    Valkyrie(METAL),
    Divineral(METAL),
    Continuum(GEM),

    // Netherlicious
    Efrine(METAL),

    // ==========================================GEMS==============================================================//
    Diamond(GEM),
    Emerald(GEM),
    Lapis(GEM),
    Quartz(GEM),
    CertusQuartz(GEM),
    NetherStar(GEM),
    Prismarine(GEM),
    Ruby(GEM),
    Sapphire(GEM),
    Peridot(GEM),
    Olivine(GEM),
    Amber(GEM),
    Apatite(GEM),
    Amethyst(GEM),
    Topaz(GEM),
    BlueTopaz(GEM),
    Tanzanite(GEM),
    Malachite(GEM),
    Jade(GEM),
    Onyx(GEM),
    Moldavite(GEM),
    Bloodstone(GEM),
    Firestone(GEM),
    Dragonstone(GEM),

    // Thaumcraft
    Cinnabar(GEM),
    Quicksilver(GEM, "Mercury"),

    // Ars Magica 2
    Vinteum(GEM),
    Chimerite(GEM),
    Moonstone(GEM),
    Sunstone(GEM),

    // ==========================================DUSTS=============================================================//
    Redstone(DUST),
    Electrotine(DUST),
    Coal(DUST),
    Charcoal(DUST),
    Sulfur(DUST),
    Saltpeter(DUST),
    Salt(DUST),
    Silicon(DUST),
    Obsidian(DUST),
    Ash(DUST);

    public enum Type {
        /** ingot, nugget, block, dust, ore */
        METAL,
        /** gem, block, dust, ore */
        GEM,
        /** dust, block, ore */
        DUST
    }

    public final Type type;
    /** Other spellings of the same material seen in mods, e.g. "Aluminium" for Aluminum. */
    private final Set<String> aliases;

    private static final Map<String, Materials> BY_NAME = new HashMap<>();

    static {
        for (Materials m : values()) {
            BY_NAME.put(m.name(), m);
            for (String alias : m.aliases) BY_NAME.put(alias, m);
        }
    }

    Materials(Type type, String... aliases) {
        this.type = type;
        Set<String> set = new java.util.HashSet<>();
        Collections.addAll(set, aliases);
        this.aliases = Collections.unmodifiableSet(set);
    }

    public Set<String> getAliases() {
        return aliases;
    }

    /** The prefixes this material normally comes in. */
    public Set<OrePrefixes> getUsualPrefixes() {
        return switch (type) {
            case METAL -> EnumSet
                .of(OrePrefixes.ore, OrePrefixes.dust, OrePrefixes.ingot, OrePrefixes.nugget, OrePrefixes.block);
            case GEM -> EnumSet.of(OrePrefixes.ore, OrePrefixes.dust, OrePrefixes.gem, OrePrefixes.block);
            case DUST -> EnumSet.of(OrePrefixes.ore, OrePrefixes.dust, OrePrefixes.block);
        };
    }

    /** Looks up a material by its name or any alias (case-sensitive, as in the ore dictionary), or null. */
    public static Materials get(String name) {
        return BY_NAME.get(name);
    }

    /** The material of an ore dictionary name such as {@code ingotAluminium} (resolving aliases), or null. */
    public static Materials fromOreName(String oreName) {
        String material = OrePrefixes.getMaterialName(oreName);
        return material == null ? null : get(material);
    }
}
