package com.thenextelement.qmcore.common.container;

import java.lang.ref.WeakReference;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.thenextelement.qmcore.common.ModBlocks;
import com.thenextelement.qmcore.common.inventory.ChestSlot;
import com.thenextelement.qmcore.common.inventory.InventoryCraftingStation;
import com.thenextelement.qmcore.common.inventory.InventoryCraftingStationResult;
import com.thenextelement.qmcore.common.inventory.SlotCraftingStation;
import com.thenextelement.qmcore.common.logic.CraftingStationLogic;

public class ContainerCraftingStation extends Container {

    /** Width of the attached-inventory panel (6 columns of 18px slots plus a 7px border each side). */
    public static final int SIDE_PANEL_WIDTH = 122;

    private static final int CRAFTING_RESULT_SLOT = 0;
    private static final int CRAFTING_GRID_FIRST_SLOT = 1;
    private static final int CRAFTING_GRID_END_SLOT = 10;
    private static final int PLAYER_INVENTORY_FIRST_SLOT = 10;
    private static final int PLAYER_INVENTORY_END_SLOT = 46;
    private static final int SIDE_INVENTORY_FIRST_SLOT = 46;

    private final World worldObj;
    private final int posX;
    private final int posY;
    private final int posZ;
    private final boolean shiftClickToGrid;

    @SuppressWarnings("rawtypes")
    public final WeakReference[] inventories;

    /** The crafting matrix inventory (3x3). */
    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    public CraftingStationLogic logic;

    /** Last matched recipe, tried first to avoid rescanning the whole recipe list on every matrix change. */
    public IRecipe lastRecipe;

    /** While true, matrix changes don't recompute the result. */
    public boolean suppressCraftingUpdates;

    public ContainerCraftingStation(InventoryPlayer inventoryplayer, CraftingStationLogic logic, int x, int y, int z) {
        this.worldObj = logic.getWorldObj();
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.logic = logic;
        this.shiftClickToGrid = logic.shiftClickToGrid;
        this.craftMatrix = new InventoryCraftingStation(this, 3, 3, logic);
        this.craftResult = new InventoryCraftingStationResult(logic);
        this.inventories = logic.getInventories();

        // With an attached inventory, the crafting panel and player inventory shift right to make room.
        final int bothOffset = logic.chest != null ? SIDE_PANEL_WIDTH : 0;
        final int craftingOffsetX = 30 + bothOffset;
        final int inventoryOffsetX = 8 + bothOffset;

        int row, col;

        // 0 - crafting result
        this.addSlotToContainer(
            new SlotCraftingStation(
                this,
                inventoryplayer.player,
                this.craftMatrix,
                this.craftResult,
                0,
                craftingOffsetX + 94,
                35));

        // 1-9 - crafting matrix
        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 3; ++col) {
                this.addSlotToContainer(
                    new Slot(this.craftMatrix, col + row * 3, craftingOffsetX + col * 18, 17 + row * 18));
            }
        }

        // 10-36 - player inventory
        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 9; ++col) {
                this.addSlotToContainer(
                    new Slot(inventoryplayer, col + row * 9 + 9, inventoryOffsetX + col * 18, 84 + row * 18));
            }
        }

        // 37-45 - hotbar
        for (col = 0; col < 9; ++col) {
            this.addSlotToContainer(new Slot(inventoryplayer, col, inventoryOffsetX + col * 18, 142));
        }

        // 46+ - attached inventory
        if (logic.chest != null) {
            IInventory inv = logic.getFirstInventory();
            IInventory secondInv = logic.getSecondInventory();

            final int accessSide = logic.chestDirection.getOpposite()
                .ordinal();
            int[] firstSlots = logic.getFirstInventorySlots();
            int[] secondSlots = logic.getSecondInventorySlots();
            for (int index = 0; index < firstSlots.length + secondSlots.length; index++) {
                boolean first = index < firstSlots.length;
                IInventory curInv = first ? inv : secondInv;
                int curIndex = first ? firstSlots[index] : secondSlots[index - firstSlots.length];
                this.addSlotToContainer(
                    new ChestSlot(
                        curInv,
                        curIndex,
                        index,
                        8 + index % logic.invColumns * 18,
                        19 + index / logic.invColumns * 18,
                        accessSide));
            }
        }

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    // ==========================================SHIFT-CLICK==================================================//

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int index) {
        Slot slot = this.inventorySlots.get(index);
        if (slot == null || !slot.getHasStack()) return null;

        ItemStack itemstack = slot.getStack();
        ItemStack ret = itemstack.copy();

        // each helper returns true when it moved nothing
        boolean nothingDone = true;

        if (index == CRAFTING_RESULT_SLOT) {
            // refill stacks already in the attached inventory first, then the player inventory
            nothingDone &= this.refillChest(itemstack);
            nothingDone &= moveToPlayerInventory(itemstack);
            slot.onSlotChange(itemstack, ret);
        } else if (isCraftingGridSlot(index)) {
            nothingDone &= moveToPlayerInventory(itemstack);
        } else if (index >= PLAYER_INVENTORY_FIRST_SLOT && index < PLAYER_INVENTORY_END_SLOT) {
            nothingDone &= moveToCraftingGrid(itemstack);
            nothingDone &= this.moveToChest(itemstack);
        } else { // from the attached inventory
            nothingDone &= moveToCraftingGrid(itemstack);
            nothingDone &= moveToPlayerInventory(itemstack);
        }

        if (nothingDone) return null;

        if (itemstack.stackSize == 0) {
            slot.putStack(null);
        } else {
            slot.onSlotChanged();
        }

        if (itemstack.stackSize == ret.stackSize) return null;

        slot.onPickupFromSlot(entityPlayer, itemstack);
        return ret;
    }

    protected boolean refillChest(ItemStack itemstack) {
        if (itemstack == null || itemstack.stackSize <= 0 || inventorySlots.size() == SIDE_INVENTORY_FIRST_SLOT)
            return true;

        return !this.mergeItemStackRefill(itemstack, SIDE_INVENTORY_FIRST_SLOT, inventorySlots.size(), false);
    }

    protected boolean moveToChest(ItemStack itemstack) {
        if (itemstack == null || itemstack.stackSize <= 0 || inventorySlots.size() == SIDE_INVENTORY_FIRST_SLOT)
            return true;

        return !this.mergeItemStack(itemstack, SIDE_INVENTORY_FIRST_SLOT, inventorySlots.size(), false);
    }

    protected boolean moveToPlayerInventory(ItemStack itemstack) {
        if (itemstack == null || itemstack.stackSize <= 0) return true;

        return !this.mergeItemStack(itemstack, PLAYER_INVENTORY_FIRST_SLOT, PLAYER_INVENTORY_END_SLOT, false);
    }

    protected boolean moveToCraftingGrid(ItemStack itemstack) {
        if (itemstack == null || itemstack.stackSize <= 0) return true;

        // with an attached inventory, shift-clicking into the grid is optional
        if (!shiftClickToGrid && inventorySlots.size() > SIDE_INVENTORY_FIRST_SLOT) return true;

        return !this.mergeItemStack(itemstack, CRAFTING_GRID_FIRST_SLOT, CRAFTING_GRID_END_SLOT, true);
    }

    public static boolean isCraftingGridSlot(int slot) {
        return slot >= CRAFTING_GRID_FIRST_SLOT && slot < CRAFTING_GRID_END_SLOT;
    }

    // ==========================================MERGING======================================================//

    /** Fills existing stacks first, then empty slots. */
    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
        boolean ret = mergeItemStackRefill(stack, startIndex, endIndex, useEndIndex);
        if (stack.stackSize > 0) {
            ret |= mergeItemStackMove(stack, startIndex, endIndex, useEndIndex);
        }
        return ret;
    }

    // only refills stacks that are already present
    protected boolean mergeItemStackRefill(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
        if (stack.stackSize <= 0) return false;

        boolean didSomething = false;
        int k = useEndIndex ? endIndex - 1 : startIndex;

        if (stack.isStackable()) {
            while (stack.stackSize > 0 && (!useEndIndex && k < endIndex || useEndIndex && k >= startIndex)) {
                Slot slot = this.inventorySlots.get(k);
                ItemStack existing = getNonEmptyStack(slot);

                if (existing != null && stacksCanMerge(stack, existing) && this.func_94530_a(stack, slot)) {
                    int space = getStorageLimit(slot, stack) - existing.stackSize;
                    int amount = Math.min(stack.stackSize, Math.max(0, space));
                    if (amount > 0) {
                        stack.stackSize -= amount;
                        existing.stackSize += amount;
                        slot.onSlotChanged();
                        didSomething = true;
                    }
                }

                if (useEndIndex) --k;
                else++k;
            }
        }

        return didSomething;
    }

    // only moves items into empty slots
    protected boolean mergeItemStackMove(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
        if (stack.stackSize <= 0) return false;

        boolean didSomething = false;
        int k = useEndIndex ? endIndex - 1 : startIndex;

        while (!useEndIndex && k < endIndex || useEndIndex && k >= startIndex) {
            Slot slot = this.inventorySlots.get(k);

            if (getNonEmptyStack(slot) == null && slot.isItemValid(stack) && this.func_94530_a(stack, slot)) {
                int amount = Math.min(stack.stackSize, getStorageLimit(slot, stack));
                if (amount > 0) {
                    ItemStack placed = stack.copy();
                    placed.stackSize = amount;
                    stack.stackSize -= amount;
                    slot.putStack(placed);
                    slot.onSlotChanged();
                    didSomething = true;
                }

                if (stack.stackSize <= 0) break;
            }

            if (useEndIndex) --k;
            else++k;
        }

        return didSomething;
    }

    private static ItemStack getNonEmptyStack(Slot slot) {
        if (slot == null) return null;

        ItemStack stack = slot.getStack();
        return stack == null || stack.stackSize <= 0 ? null : stack;
    }

    private static int getStorageLimit(Slot slot, ItemStack stack) {
        return Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
    }

    public static boolean stacksCanMerge(ItemStack first, ItemStack second) {
        return first != null && second != null
            && first.getItem() == second.getItem()
            && (!first.getHasSubtypes() || first.getItemDamage() == second.getItemDamage())
            && ItemStack.areItemStackTagsEqual(first, second);
    }

    /** Blocks drag-distribution (and the merges above) from touching the result slot. */
    @Override
    public boolean func_94530_a /* canMergeSlot */(ItemStack stack, Slot slot) {
        return slot.inventory != this.craftResult && super.func_94530_a(stack, slot);
    }

    // ==========================================CRAFTING=====================================================//

    @Override
    public void onCraftMatrixChanged(IInventory inventory) {
        if (suppressCraftingUpdates) return;

        this.craftResult.setInventorySlotContents(0, findMatchingRecipeCached());
    }

    /** Like {@link CraftingManager#findMatchingRecipe} but tries the last matched recipe first. */
    public ItemStack findMatchingRecipeCached() {
        // vanilla's two-item tool repair takes precedence over the recipe list
        if (isVanillaToolRepair()) {
            return CraftingManager.getInstance()
                .findMatchingRecipe(this.craftMatrix, this.worldObj);
        }

        IRecipe cached = this.lastRecipe;
        if (cached != null && cached.matches(this.craftMatrix, this.worldObj)) {
            return cached.getCraftingResult(this.craftMatrix);
        }
        this.lastRecipe = null;

        List<IRecipe> recipes = CraftingManager.getInstance()
            .getRecipeList();
        for (int i = 0; i < recipes.size(); i++) {
            IRecipe recipe = recipes.get(i);
            if (recipe.matches(this.craftMatrix, this.worldObj)) {
                this.lastRecipe = recipe;
                return recipe.getCraftingResult(this.craftMatrix);
            }
        }
        return null;
    }

    /** Mirrors the repair check in CraftingManager.findMatchingRecipe: two size-1 stacks of one repairable item. */
    public boolean isVanillaToolRepair() {
        ItemStack first = null;
        ItemStack second = null;
        int found = 0;

        for (int i = 0; i < this.craftMatrix.getSizeInventory(); i++) {
            ItemStack stack = this.craftMatrix.getStackInSlot(i);
            if (stack == null) continue;

            found++;
            if (found == 1) first = stack;
            else if (found == 2) second = stack;
            else return false;
        }

        return found == 2 && first.getItem() == second.getItem()
            && first.stackSize == 1
            && second.stackSize == 1
            && first.getItem()
                .isRepairable();
    }

    /** Suppresses result updates until {@link #endBatchCraftingUpdate}. */
    public void beginBatchCraftingUpdate() {
        suppressCraftingUpdates = true;
    }

    /** Re-enables result updates and recomputes once. */
    public void endBatchCraftingUpdate() {
        suppressCraftingUpdates = false;
        this.onCraftMatrixChanged(this.craftMatrix);
    }

    // ==========================================LIFECYCLE====================================================//

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        // the grid keeps its contents in the tile; only the cursor stack is handled by super
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        Block block = worldObj.getBlock(this.posX, this.posY, this.posZ);
        if (block != ModBlocks.craftingStation) return false;

        if (!this.logic.isUseableByPlayer(player) || !CraftingStationLogic.isUseableByPlayer(player, this.inventories))
            return false;

        return player.getDistanceSq(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D) <= 64.0D;
    }
}
