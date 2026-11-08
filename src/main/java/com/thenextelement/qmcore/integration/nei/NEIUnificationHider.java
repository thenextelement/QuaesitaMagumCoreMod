package com.thenextelement.qmcore.integration.nei;

import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.common.scripts.ItemHider;
import com.thenextelement.qmcore.common.unification.OreUnification;

import codechicken.nei.api.API;

/** Hides unified-away items and items scripts removed in NEI. Only touch this class when NEI is loaded. */
public final class NEIUnificationHider {

    private NEIUnificationHider() {}

    public static void hideItems() {
        for (ItemStack loser : OreUnification.getLosers()) API.hideItem(loser);
        for (ItemStack removed : ItemHider.getHidden()) API.hideItem(removed);
    }
}
