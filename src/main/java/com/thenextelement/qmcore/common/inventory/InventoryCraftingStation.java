package com.thenextelement.qmcore.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.common.logic.CraftingStationLogic;

/** 3x3 crafting matrix backed by slots 1-9 of the station's logic (slot 0 is the output). */
public class InventoryCraftingStation extends InventoryCrafting {

    private final int inventoryWidth;
    private final Container eventHandler;
    private final CraftingStationLogic logic;

    public InventoryCraftingStation(Container container, int width, int height, CraftingStationLogic logic) {
        super(container, width, height);
        this.eventHandler = container;
        this.inventoryWidth = width;
        this.logic = logic;
    }

    @Override
    public int getSizeInventory() {
        return 9;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot >= this.getSizeInventory() ? null : logic.getStackInSlot(slot + 1);
    }

    @Override
    public ItemStack getStackInRowAndColumn(int row, int column) {
        if (row >= 0 && row < this.inventoryWidth) {
            return this.getStackInSlot(row + column * this.inventoryWidth);
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = logic.getStackInSlot(slot + 1);
        if (stack == null) return null;

        ItemStack removed;
        if (stack.stackSize <= amount) {
            removed = stack.copy();
            logic.setInventorySlotContents(slot + 1, null);
        } else {
            removed = stack.splitStack(amount);
            logic.markDirty();
        }
        this.eventHandler.onCraftMatrixChanged(this);
        return removed;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        logic.setInventorySlotContents(slot + 1, stack);
        this.eventHandler.onCraftMatrixChanged(this);
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
