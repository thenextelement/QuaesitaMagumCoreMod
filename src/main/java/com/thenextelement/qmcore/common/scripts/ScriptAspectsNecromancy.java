package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;

import java.util.Arrays;
import java.util.List;

import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.integration.thaumcraft.TCAspects;

/**
 * Thaumcraft aspects for Necromancy. Converted from "TC4 - NecromancyAspects.zs" by daforsyth.
 * References Thaumcraft classes: register behind a Thaumcraft check.
 */
public class ScriptAspectsNecromancy implements IScriptLoader {

    @Override
    public String getScriptName() {
        return "Necromancy Aspects";
    }

    @Override
    public List<Mods> getDependencies() {
        return Arrays.asList(Mods.Thaumcraft, Mods.Necromancy, Mods.ForbiddenMagic);
    }

    @Override
    public void loadRecipes() {

        // Brain
        TCAspects.set(getModItem(Mods.Necromancy.ID, "Organs", 1, 0), "cognitio 4, corpus 2");

        // Heart
        TCAspects.set(getModItem(Mods.Necromancy.ID, "Organs", 1, 1), "victus 4, corpus 2");

        // Muscle
        TCAspects.set(getModItem(Mods.Necromancy.ID, "Organs", 1, 2), "motus 3, corpus 2");

        // Lungs
        TCAspects.set(getModItem(Mods.Necromancy.ID, "Organs", 1, 3), "aer 3, corpus 2");

        // Skin
        TCAspects.set(getModItem(Mods.Necromancy.ID, "Organs", 1, 4), "tutamen 1, corpus 2");

        // Blood bottle
        TCAspects.set(getModItem(Mods.Necromancy.ID, "ItemNecromancy", 1, 2), "victus 1");

        // Soul in a jar
        TCAspects.set(getModItem(Mods.Necromancy.ID, "ItemNecromancy", 1, 1), "spiritus 1");

        // Bone needle
        TCAspects.set(getModItem(Mods.Necromancy.ID, "ItemNecromancy", 1, 0), "sensus 1");

        // bucket of blood
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BucketBlood", 1, 0), "metallum 8, victus 4, vacuos 1");

        // Flowing Blood
        // mods.thaumcraft.Aspects.set(<necromancy:FlowingBlood>, "victus 4");

        // Scythe
        TCAspects.set(getModItem(Mods.Necromancy.ID, "ItemScythe", 1, 0), "meto 4, telum 3, mortuus 2");

        // Bone Scythe
        TCAspects.set(getModItem(Mods.Necromancy.ID, "ItemScytheBone", 1, 0), "meto 6, telum 5, mortuus 5, lucrum 3");

        // Summong altar
        TCAspects.set(
            getModItem(Mods.Necromancy.ID, "Summoning Altar", 1, 0),
            "terra 6, exanimis 5, potentia 4, spiritus 5");

        // Necronomicon
        TCAspects.set(getModItem(Mods.Necromancy.ID, "Necronomicon", 1, 0), "cognitio 8, pannus 2, mortuus 2");

        // Cow
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 0), "bestia 2, terra 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 1), "bestia 2, terra 1, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 2), "bestia 2, terra 1, corpus 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 3), "bestia 2, terra 1, corpus 2, motus 2");

        // Creeper
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 4), "ira 2, ignis 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 5), "ira 2, ignis 1, corpus 2, motus 2");

        // Endermen
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 6), "alienis 2, iter 1, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 7), "alienis 2, iter 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 8), "alienis 2, iter 1, corpus 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 9), "alienis 2, iter 1, corpus 2, motus 2");

        // pig
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 10), "bestia 2, gula 1, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 11), "bestia 2, gula 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 12), "bestia 2, gula 1, corpus 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 13), "bestia 2, gula 1, corpus 2, motus 2");

        // pigmen
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 14), "exanimis 2, ignis 1, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 15), "exanimis 2, ignis 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 16), "exanimis 2, ignis 1, corpus 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 17), "exanimis 2, ignis 1, corpus 2, motus 2");

        // skeleton
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 18), "exanimis 2, mortuus 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 19), "exanimis 2, mortuus 1, corpus 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 20), "exanimis 2, mortuus 1, corpus 2, motus 2");

        // spider
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 21), "bestia 2, perditio 1, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 22), "bestia 2, perditio 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 23), "bestia 2, perditio 1, corpus 2, motus 2");

        // skeleton
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 24), "exanimis 2, humanus 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 25), "exanimis 2, humanus 1, corpus 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 26), "exanimis 2, humanus 1, corpus 2, motus 2");

        // Chicken
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 27), "bestia 2, aer 1, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 28), "bestia 2, aer 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 29), "bestia 2, aer 1, corpus 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 30), "bestia 2, aer 1, corpus 2, motus 2");

        // Villager
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 31), "humanus 3, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 32), "humanus 3, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 33), "humanus 3, corpus 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 34), "humanus 3, corpus 2, motus 2");

        // Witch
        TCAspects
            .set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 35), "humanus 3, praecantatio 1, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 36), "humanus 3, praecantatio 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 37), "humanus 3, praecantatio 1, corpus 1");
        TCAspects
            .set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 38), "humanus 3, praecantatio 1, corpus 2, motus 2");

        // squid
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 39), "bestia 2, aqua 2, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 40), "bestia 2, aqua 2, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 41), "bestia 2, aqua 2, corpus 2, motus 2");

        // cave spider
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 42), "bestia 2, venenum 1, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 43), "bestia 2, venenum 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 44), "bestia 2, venenum 1, corpus 2, motus 2");

        // sheep
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 45), "bestia 2, pannus 1, corpus 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 46), "bestia 2, pannus 1, corpus 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 47), "bestia 2, pannus 1, corpus 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 48), "bestia 2, pannus 1, corpus 2, motus 2");

        // Iron golem
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 49), "metallum 3, humanus 1, machina 2, sensus 2");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 50), "metallum 3, humanus 1, machina 4");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 51), "metallum 3, humanus 1, machina 1");
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 52), "metallum 3, humanus 1, machina 2, motus 2");

        // Wolf
        TCAspects.set(getModItem(Mods.Necromancy.ID, "BodyParts", 1, 53), "bestia 3, corpus 2");

        // Isaac Head
        TCAspects.set(getModItem(Mods.Necromancy.ID, "ItemIsaacsHead", 1, 0), "corpus 3, humanus 4, ira 4, aqua 4");

        // Isaac Mob
        TCAspects.addEntity("IsaacNormal", "humanus 3, aqua 3");
        TCAspects.addEntity("IsaacBlood", "humanus 3, aqua 3, ira 4");
        TCAspects.addEntity("IsaacHead", "humanus 1, sensus 1, aqua 2");
        TCAspects.addEntity("IsaacBody", "humanus 3");

        // Other mobs
        TCAspects.addEntity("minionNecro", "corpus 4, victus 2, mortuus 2, spiritus 2");
        TCAspects.addEntity("NightCrawler", "exanimis 3, tenebrae 2, alienis 2");
        TCAspects.addEntity("teddyNecro", "bestia 1, infernus 3");
    }
}
