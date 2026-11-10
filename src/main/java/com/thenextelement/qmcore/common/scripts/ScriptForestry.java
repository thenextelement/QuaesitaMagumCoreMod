package com.thenextelement.qmcore.common.scripts;

import static com.thenextelement.qmcore.common.scripts.IngredientFactory.getModItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.common.tech.DisabledTech;

/**
 * Forestry's RF tech (see DisabledTech). The mixins in {@code mixin.mixins.late.forestry} make the blocks inert and
 * keep
 * them out of the creative tabs. This removes their recipes and hides them, together with the parts that only build
 * machines: circuit boards, the soldering iron, electron tubes and the three machine casings.
 * <p>
 * The items stay registered: Binnie's mods look several of them up by name at startup and throw if one is missing.
 * The impregnated casing, tubes, circuit boards and hardened casing already have no recipe in the pack (they come from
 * the disabled Carpenter and Thermionic Fabricator), so hiding them only makes that official. The non-RF Alveary parts,
 * the Bee House, the Apiary and the rest of the Farming module stay.
 */
public class ScriptForestry implements IScriptLoader {

    private static final String[] PARTS = { //
        "sturdyMachine", // sturdy casing
        "hardenedMachine", // hardened casing
        "impregnatedCasing", "chipsets", // circuit boards
        "thermionicTubes", // electron tubes
        "solderingIron" };

    @Override
    public String getScriptName() {
        return "Forestry RF tech";
    }

    @Override
    public List<Mods> getDependencies() {
        return Collections.singletonList(Mods.Forestry);
    }

    @Override
    public boolean isScriptLoadable() {
        return DisabledTech.isEnabled() && IScriptLoader.super.isScriptLoadable();
    }

    @Override
    public void removeRecipes() {
        for (ItemStack stack : techItems()) RecipeRemover.removeRecipeByOutput(stack);
    }

    @Override
    public void loadRecipes() {
        for (ItemStack stack : techItems()) hideItem(stack);
    }

    private static List<ItemStack> techItems() {
        String id = Mods.Forestry.ID;
        List<ItemStack> list = new ArrayList<>();
        for (String part : PARTS) list.add(getModItem(id, part, 1, wildcard));
        list.add(getModItem(id, "core", 1, DisabledTech.FORESTRY_CORE_ANALYZER));
        list.add(getModItem(id, "alveary", 1, DisabledTech.FORESTRY_ALVEARY_FAN));
        list.add(getModItem(id, "alveary", 1, DisabledTech.FORESTRY_ALVEARY_HEATER));
        list.add(getModItem(id, "ffarm", 1, DisabledTech.FORESTRY_FARM_GEARBOX));
        return list;
    }
}
