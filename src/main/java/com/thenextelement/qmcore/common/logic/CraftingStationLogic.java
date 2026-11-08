package com.thenextelement.qmcore.common.logic;

import java.lang.ref.WeakReference;
import java.util.stream.IntStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.thenextelement.qmcore.common.container.ContainerCraftingStation;
import com.thenextelement.qmcore.common.handler.ConfigHandler;

import mantle.blocks.abstracts.InventoryLogic;

public class CraftingStationLogic extends InventoryLogic implements ISidedInventory {

    private static final int[] NO_SLOTS = new int[0];

    public ForgeDirection chestDirection = ForgeDirection.UNKNOWN;
    public int chestSize;
    public WeakReference<IInventory> chest;
    public WeakReference<IInventory> doubleChest;
    public WeakReference<IInventory> furnace;
    public boolean doubleFirst;

    /** Server-selected shift-click behavior, synchronized before opening the GUI. */
    public boolean shiftClickToGrid;

    public int invRows, invColumns, slotCount;

    private int[] firstInventorySlots = NO_SLOTS;
    private int[] secondInventorySlots = NO_SLOTS;

    /** Cached result of {@link #getInventories()}; rebuilt when the adjacent inventories are rescanned. */
    @SuppressWarnings("rawtypes")
    private WeakReference[] inventories = new WeakReference[3];

    public CraftingStationLogic() {
        super(10); // 9 for crafting, 1 for output
    }

    // ==========================================INVENTORY====================================================//

    @Override
    public boolean canDropInventorySlot(int slot) {
        return slot != 0;
    }

    @Override
    public ItemStack decrStackSize(int slot, int quantity) {
        if (slot == 0) {
            for (int i = 1; i < getSizeInventory(); i++) decrStackSize(i, 1);
        }
        return super.decrStackSize(slot, quantity);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return isUseableByPlayer(player, this.getInventories()) && super.isUseableByPlayer(player);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static boolean isUseableByPlayer(EntityPlayer player, WeakReference[] inventories) {
        for (WeakReference<IInventory> ref : inventories) {
            if (ref != null) {
                IInventory inv = ref.get();
                if (inv != null && !inv.isUseableByPlayer(player)) return false;
            }
        }
        return true;
    }

    @Override
    protected String getDefaultName() {
        return "crafters.CraftingStation";
    }

    @Override
    public String getInventoryName() {
        return getDefaultName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean canUpdate() {
        return false;
    }

    // The station itself exposes no slots to hoppers or pipes.
    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return NO_SLOTS;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return false;
    }

    // ==========================================ADJACENT INVENTORIES=========================================//

    @Override
    public Container getGuiContainer(InventoryPlayer inventoryplayer, World world, int x, int y, int z) {
        if (world.isRemote) return new ContainerCraftingStation(inventoryplayer, this, x, y, z);

        shiftClickToGrid = ConfigHandler.craftingStationShiftClickToGrid;
        chest = null;
        chestSize = 0;
        slotCount = 0;
        chestDirection = ForgeDirection.UNKNOWN;
        doubleChest = null;
        doubleFirst = false;
        furnace = null;

        for (final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            final TileEntity tile = world.getTileEntity(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
            if (!(tile instanceof IInventory inv) || tile instanceof CraftingStationLogic) continue;

            if (furnace == null && tile instanceof TileEntityFurnace) {
                furnace = new WeakReference<>(inv);
                continue;
            }

            if (tile instanceof ISidedInventory sidedIvn && sidedIvn.getAccessibleSlotsFromSide(
                dir.getOpposite()
                    .ordinal()).length
                == 0) continue;

            // Any adjacent inventory (not just chests) can claim the "chest" slot; the first usable one wins.
            if (chest == null && inv.isUseableByPlayer(inventoryplayer.player)) {
                chest = new WeakReference<>(inv);
                chestDirection = dir;

                if (tile instanceof TileEntityChest tileChest) {
                    if (tileChest.adjacentChestZPos != null) {
                        doubleChest = new WeakReference<>(tileChest.adjacentChestZPos);
                        doubleFirst = false;
                    } else if (tileChest.adjacentChestZNeg != null) {
                        doubleChest = new WeakReference<>(tileChest.adjacentChestZNeg);
                        doubleFirst = true;
                    } else if (tileChest.adjacentChestXPos != null) {
                        doubleChest = new WeakReference<>(tileChest.adjacentChestXPos);
                        doubleFirst = false;
                    } else if (tileChest.adjacentChestXNeg != null) {
                        doubleChest = new WeakReference<>(tileChest.adjacentChestXNeg);
                        doubleFirst = true;
                    }
                }
            }
        }

        this.inventories = new WeakReference[] { this.chest, this.doubleChest, this.furnace };
        firstInventorySlots = getInventorySlots(getFirstInventory());
        secondInventorySlots = getInventorySlots(getSecondInventory());
        chestSize = doubleFirst ? secondInventorySlots.length : firstInventorySlots.length;
        slotCount = firstInventorySlots.length + secondInventorySlots.length;
        invRows = (slotCount + 5) / 6;
        invColumns = 6;

        // The client container is built from this packet, so it must be sent before the GUI opens.
        if (inventoryplayer.player instanceof EntityPlayerMP player) {
            NBTTagCompound data = new NBTTagCompound();
            data.setBoolean("CraftingStationGui", true);
            writeInventoryReference(data, "Chest", chest);
            writeInventoryReference(data, "DoubleChest", doubleChest);
            writeInventoryReference(data, "Furnace", furnace);
            data.setInteger("ChestDirection", chestDirection.ordinal());
            data.setBoolean("DoubleFirst", doubleFirst);
            data.setBoolean("ShiftClickToGrid", shiftClickToGrid);
            data.setIntArray("FirstSlots", firstInventorySlots);
            data.setIntArray("SecondSlots", secondInventorySlots);
            player.playerNetServerHandler.sendPacket(new S35PacketUpdateTileEntity(x, y, z, 0, data));
        }

        return new ContainerCraftingStation(inventoryplayer, this, x, y, z);
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound data = packet.func_148857_g();
        if (!data.getBoolean("CraftingStationGui")) {
            super.onDataPacket(manager, packet);
            return;
        }
        chest = readInventoryReference(data, "Chest");
        doubleChest = readInventoryReference(data, "DoubleChest");
        furnace = readInventoryReference(data, "Furnace");
        chestDirection = ForgeDirection.getOrientation(data.getInteger("ChestDirection"));
        doubleFirst = data.getBoolean("DoubleFirst");
        shiftClickToGrid = data.getBoolean("ShiftClickToGrid");
        firstInventorySlots = data.getIntArray("FirstSlots");
        secondInventorySlots = data.getIntArray("SecondSlots");
        chestSize = doubleFirst ? secondInventorySlots.length : firstInventorySlots.length;
        slotCount = firstInventorySlots.length + secondInventorySlots.length;
        invColumns = 6;
        invRows = (slotCount + 5) / 6;
        inventories = new WeakReference[] { chest, doubleChest, furnace };
    }

    private int[] getInventorySlots(IInventory inventory) {
        if (inventory == null) return NO_SLOTS;
        if (inventory instanceof ISidedInventory sided) {
            return sided.getAccessibleSlotsFromSide(
                chestDirection.getOpposite()
                    .ordinal())
                .clone();
        }
        return IntStream.range(0, inventory.getSizeInventory())
            .toArray();
    }

    private static void writeInventoryReference(NBTTagCompound data, String key, WeakReference<IInventory> ref) {
        if (ref != null && ref.get() instanceof TileEntity tile) {
            data.setIntArray(key, new int[] { tile.xCoord, tile.yCoord, tile.zCoord });
        }
    }

    private WeakReference<IInventory> readInventoryReference(NBTTagCompound data, String key) {
        int[] pos = data.getIntArray(key);
        if (pos.length == 3 && worldObj.getTileEntity(pos[0], pos[1], pos[2]) instanceof IInventory adjacent) {
            return new WeakReference<>(adjacent);
        }
        return null;
    }

    public int[] getFirstInventorySlots() {
        return firstInventorySlots;
    }

    public int[] getSecondInventorySlots() {
        return secondInventorySlots;
    }

    public boolean isDoubleChest() {
        return this.doubleChest != null;
    }

    public IInventory getFirstInventory() {
        if (doubleFirst && doubleChest != null) {
            return doubleChest.get();
        } else {
            return chest != null ? chest.get() : null;
        }
    }

    public IInventory getSecondInventory() {
        if (!isDoubleChest()) return null;

        if (doubleFirst) {
            return chest.get();
        } else {
            return doubleChest == null ? null : doubleChest.get();
        }
    }

    @SuppressWarnings("rawtypes")
    public WeakReference[] getInventories() {
        return this.inventories;
    }
}
