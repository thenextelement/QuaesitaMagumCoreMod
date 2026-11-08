package com.thenextelement.qmcore.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.common.logic.CraftingStationLogic;

/** Crafting output backed by slot 0 of the station's logic. */
public class InventoryCraftingStationResult extends InventoryCraftResult {

    private final CraftingStationLogic logic;

    public InventoryCraftingStationResult(CraftingStationLogic logic) {
        this.logic = logic;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return logic.getStackInSlot(0);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = logic.getStackInSlot(0);
        if (stack == null) return null;
        logic.setInventorySlotContents(0, null);
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        logic.setInventorySlotContents(0, stack);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {}

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }
}
