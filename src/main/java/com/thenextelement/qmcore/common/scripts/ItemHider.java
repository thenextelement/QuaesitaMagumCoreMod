package com.thenextelement.qmcore.common.scripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

/** Items scripts want hidden from NEI (removed content). The client passes them to NEI at load complete. */
public final class ItemHider {

    private static final List<ItemStack> hidden = new ArrayList<>();

    private ItemHider() {}

    public static void hide(ItemStack stack) {
        if (!IngredientFactory.isMissing(stack)) hidden.add(stack.copy());
    }

    public static List<ItemStack> getHidden() {
        return Collections.unmodifiableList(hidden);
    }
}
