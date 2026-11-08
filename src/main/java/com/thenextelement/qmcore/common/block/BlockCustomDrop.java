package com.thenextelement.qmcore.common.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.collect.Lists;
import com.thenextelement.qmcore.common.utils.InventoryUtils;

public abstract class BlockCustomDrop extends BlockContainerQM {

    public BlockCustomDrop(final Material material) {
        super(material);
    }

    protected abstract boolean dropInventory();

    protected abstract boolean hasCustomDrops();

    protected abstract void getCustomTileEntityDrops(TileEntity te, List<ItemStack> drops);

    private void getCustomDrops(TileEntity te, List<ItemStack> drops) {
        if (te == null) return;
        if (hasCustomDrops()) {
            getCustomTileEntityDrops(te, drops);
        }
        if (dropInventory() && te instanceof IInventory) {
            drops.addAll(InventoryUtils.getInventoryContents((IInventory) te));
            for (int i = 0; i < ((IInventory) te).getSizeInventory(); i++)
                ((IInventory) te).setInventorySlotContents(i, null);
        }
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        if (willHarvest) {
            TileEntity te = world.getTileEntity(x, y, z);

            boolean result = super.removedByPlayer(world, player, x, y, z, willHarvest);

            if (result) {
                List<ItemStack> teDrops = Lists.newArrayList();
                getCustomDrops(te, teDrops);
                for (ItemStack drop : teDrops) dropBlockAsItem(world, x, y, z, drop);
            }

            return result;
        }

        return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> result = Lists.newArrayList();
        if (!hasCustomDrops()) result.addAll(super.getDrops(world, x, y, z, metadata, fortune));
        if (hasCustomDrops() || dropInventory()) {
            TileEntity te = world.getTileEntity(x, y, z);
            getCustomDrops(te, result);
        }

        return result;
    }

}
