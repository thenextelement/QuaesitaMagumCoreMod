package com.thenextelement.qmcore.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.common.container.ContainerCraftingStation;

public class SlotCraftingStation extends SlotCrafting {

    private final ContainerCraftingStation container;

    public SlotCraftingStation(ContainerCraftingStation container, EntityPlayer player, IInventory matrix,
        IInventory result, int index, int x, int y) {
        super(player, matrix, result, index, x, y);
        this.container = container;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
        // consume all ingredients first, then do a single recipe lookup
        container.beginBatchCraftingUpdate();
        try {
            super.onPickupFromSlot(player, stack);
        } finally {
            container.endBatchCraftingUpdate();
        }
    }
}
