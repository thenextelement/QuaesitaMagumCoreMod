package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;

import java.util.Arrays;
import java.util.List;

import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.integration.thaumcraft.TCAspects;

/**
 * Thaumcraft aspects for Reliquary. Converted from "TC4 - ReliquaryAspects.zs" by daforsyth.
 * References Thaumcraft classes: register behind a Thaumcraft check.
 */
public class ScriptAspectsReliquary implements IScriptLoader {

    @Override
    public String getScriptName() {
        return "Reliquary Aspects";
    }

    @Override
    public List<Mods> getDependencies() {
        return Arrays.asList(Mods.Thaumcraft, Mods.Reliquary, Mods.ForbiddenMagic);
    }

    @Override
    public void loadRecipes() {

        // Phoenix Down
        TCAspects.set(getModItem(Mods.Reliquary.ID, "phoenix_down", 1, 0), "sano 8, volatus 6, ignis 20, permutatio 6");
        TCAspects.set(getModItem(Mods.Reliquary.ID, "angelic_feather", 1, 0), "lux 8, volatus 6, aer 6, tutamen 3");

        TCAspects
            .set(getModItem(Mods.Reliquary.ID, "serpent_staff", 1, 0), "aqua 8, limus 6, venenum 6, praecantatio 4");

        TCAspects
            .set(getModItem(Mods.Reliquary.ID, "ender_staff", 1, 0), "iter 8, alienis 6, praecantatio 4, invidia 4");

        TCAspects
            .set(getModItem(Mods.Reliquary.ID, "ice_magus_rod", 1, 0), "gelum 8, vitreus 6, praecantatio 4, ira 4");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "mercy_cross", 1, 0), "lucrum 8, lux 20, metallum 9");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "magicbane", 1, 0), "lucrum 3, telum 3, praecantatio 4, invidia 2");

        TCAspects
            .set(getModItem(Mods.Reliquary.ID, "destruction_catalyst", 1, 0), "lucrum 3, ira 8 , perditio 6, ignis 6");

        TCAspects
            .set(getModItem(Mods.Reliquary.ID, "emperor_chalice", 1, 0), "aqua 20, vacuos 10, lucrum 13, metallum 8");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "midas_touchstone", 1, 0),
            "lucrum 3, fabrico 4, metallum 3, permutatio 3");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "witherless_rose", 1, 0),
            "ordo 24, herba 6, tutamen 20, sano 20, lux 36");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "twilight_cloak", 1, 0),
            "pannus 56, tenebrae 8, sensus 12, praecantatio 10");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "claws_of_the_firedrinker", 1, 0),
            "ignis 64, bestia 12, infernus 12, tutamen 12");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "kraken_shell", 1, 0), "aqua 64, bestia 12, tutamen 12, aer 12");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "lantern_of_paranoia", 1, 0),
            "lux 12, ignis 8, metallum 14, sensus 8, praecantatio 4");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "fortune_coin", 1, 0),
            "lucrum 20, vacuos 10, metallum 9, iter 9, praecantatio 3");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "sojourner_staff", 1, 0), "ignis 7, metallum 6, lux 6, desidia 3");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "salamander_eye", 1, 0), "ignis 13, sensus 10, tutamen 12, ira 6");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "dragon_claws", 1, 0), "ignis 12, bestia 6, tutamen 3");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "hero_medallion", 1, 0),
            "metallum 12, cognitio 4, superbia 3, vacuos 3");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "kraken_shell_fragment", 1, 0), "aqua 12, bestia 6, tutamen 6");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "squid_beak", 1, 0), "aqua 3, bestia 3");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "dragon_talon", 1, 0), "ignis 4, bestia 4");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "crimson_cloth", 1, 0), "pannus 4, tenebrae 4, sensus 4");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "witch_hat", 1, 0), "tutamen 1, praecantatio 3");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "interdiction_torch", 1, 0), "lux 4, ordo 4, tutamen 1");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "lilypad", 1, 0), "herba 4, aqua 3, victus 6");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "altar", 1, 0),
            "lux 21, potentia 18, machina 9, terra 6, ignis 6, sensus 6");

        // potions
        TCAspects.set(getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 0), "aqua 3, perditio 1, praecantatio 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 1),
            "aqua 3, victus 3, luxuria 3, perditio 1, praecantatio 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 2),
            "aqua 3, venenum 3, perditio 1, praecantatio 1");

        TCAspects
            .set(getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 3), "aqua 3, lux 3, perditio 7, praecantatio 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 4),
            "aqua 3, sensus 3, perditio 4, praecantatio 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 5),
            "aqua 3, vinculum 3, perditio 1, praecantatio 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 6),
            "aqua 3, desidia 3, perditio 1, praecantatio 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 8),
            "aqua 3, tenebrae 3, perditio 1, praecantatio 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 9),
            "aqua 3, desidia 3, vinculum 3, perditio 1, praecantatio 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 10),
            "aqua 3, victus 3, perditio 1, praecantatio 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 10),
            "aqua 3, victus 3, perditio 1, praecantatio 1");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 11), "vitreus 1");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 12), "vitreus 1, aqua 1, praecantatio 1");

        TCAspects.set(getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 29), "vitreus 1, aqua 1");

        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 13),
            "vitreus 1, aqua 1, motus 3, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 14),
            "vitreus 1, aqua 1, perfodio 3, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 15),
            "vitreus 1, aqua 1, potentia 3, praecantatio 1");
        TCAspects
            .set(getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 16), "vitreus 1, aqua 1, sano 3, praecantatio 1");
        TCAspects
            .set(getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 17), "vitreus 1, aqua 1, aer 3, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 18),
            "vitreus 1, aqua 1, sano 2, victus 1, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 19),
            "vitreus 1, aqua 1, tutamen 3, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 20),
            "vitreus 1, aqua 1, ignis 3, tutamen 1, praecantatio 1");
        TCAspects.set(getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 21), "vitreus 1, aqua 4, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 22),
            "vitreus 1, aqua 3, sensus 3, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 23),
            "vitreus 1, aqua 3, sensus 3, lux 3, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 24),
            "vitreus 1, aqua 3, ignis 3, tutamen 4, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 25),
            "vitreus 1, aqua 3, potentia 3, perfodio 3, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 26),
            "vitreus 1, aqua 3, motus 3, aer 3, praecantatio 1");
        TCAspects
            .set(getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 27), "vitreus 1, aqua 3, sano 6, praecantatio 1");
        TCAspects.set(
            getModItem(Mods.Reliquary.ID, "condensed_potion", 1, 28),
            "vitreus 1, aqua 3, sensus 6, tenebrae 3, praecantatio 1");
        TCAspects.set(getModItem(Mods.Reliquary.ID, "glowing_water", 1, 0), "vitreus 1, aqua 3, lux 4");
        TCAspects.set(getModItem(Mods.Reliquary.ID, "angelheart_vial", 1, 0), "vitreus 1, aqua 3, lux 4, sano 4");
    }
}
