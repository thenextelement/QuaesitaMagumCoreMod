package com.thenextelement.qmcore.common.unification;

import java.util.ListIterator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** Blocks that still drop unified-away items (for example an ore placed before unification) drop the winner. */
public class UnificationEventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.drops == null) return;
        ListIterator<ItemStack> it = event.drops.listIterator();
        while (it.hasNext()) {
            ItemStack winner = OreUnification.getWinner(it.next());
            if (winner != null) it.set(winner);
        }
    }
}
