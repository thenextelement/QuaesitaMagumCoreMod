package com.thenextelement.qmcore.common.tileentities;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

import com.thenextelement.qmcore.common.utils.LogHelper;

public class TilePlacedItem extends TileEntity {

    public ItemStack stack;
    public float rotation = 0f;
    private boolean hasUpdated = false;
    private AxisAlignedBB renderBoundingBox;

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        if (renderBoundingBox == null) {
            // the original had identical min/max corners, so the tile was always frustum-culled
            renderBoundingBox = AxisAlignedBB
                .getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
        }
        return renderBoundingBox;
    }

    @Override
    public void updateEntity() {
        if (!hasUpdated && stack != null && !worldObj.isRemote) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            hasUpdated = true;
        }
    }

    // ==========================================SYNCHRONIZATION==============================================//

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
        // the block may already be drawn without the stack, so redraw now that it has arrived
        if (worldObj != null) worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    // ==============================================DATA=====================================================//

    public void setStack(ItemStack stack) {
        this.stack = stack;
        markDirty();
        if (worldObj != null) worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public ItemStack getStack() {
        return stack;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (stack != null) {
            NBTTagCompound itemTag = new NBTTagCompound();
            stack.writeToNBT(itemTag);
            compound.setTag("Item0", itemTag);
        }
        compound.setFloat("Rotation", rotation);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        rotation = compound.getFloat("Rotation");

        if (!compound.hasKey("Item0")) {
            stack = null;
            return;
        }

        stack = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Item0"));
        if (stack == null) {
            // the item was probably removed between mod updates
            stack = new ItemStack(Blocks.stone);
            this.invalidate();
            LogHelper.error(
                "Cannot load the Placed Item at location "
                    + Vec3.createVectorHelper(this.xCoord, this.yCoord, this.zCoord)
                    + " because the associated item is null, it will be removed.");
        }
    }

}
