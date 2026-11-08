package com.thenextelement.qmcore.common.block.machine;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.client.gui.GuiHandler;
import com.thenextelement.qmcore.common.ModBlocks;
import com.thenextelement.qmcore.common.block.BlockContainerQM;
import com.thenextelement.qmcore.common.lib.References;
import com.thenextelement.qmcore.common.lib.Strings;
import com.thenextelement.qmcore.common.logic.CraftingStationLogic;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCraftingStation extends BlockContainerQM {

    /** Table shape: top slab, then four legs. Each entry is {minX, minY, minZ, maxX, maxY, maxZ}. */
    public static final float[][] TABLE_PARTS = { { 0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F },
        { 0.0F, 0.0F, 0.0F, 0.25F, 0.75F, 0.25F }, { 0.75F, 0.0F, 0.0F, 1.0F, 0.75F, 0.25F },
        { 0.0F, 0.0F, 0.75F, 0.25F, 0.75F, 1.0F }, { 0.75F, 0.0F, 0.75F, 1.0F, 0.75F, 1.0F } };

    /** Set by the client proxy once the table renderer is registered; unused on a dedicated server. */
    public static int renderId = 0;

    @SideOnly(Side.CLIENT)
    private IIcon top;

    @SideOnly(Side.CLIENT)
    private IIcon bottom;

    public BlockCraftingStation() {
        super(Material.wood);
        this.setBlockName(Strings.craftingStationName);
        this.setCreativeTab(QuaesitaMagum.tabQuaesitaMagum);
        this.setHardness(2f);
        this.setStepSound(soundTypeWood);
        ModBlocks.register(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + "craftingstation_side");
        top = iconRegister.registerIcon(References.RESOURCESPREFIX + "craftingstation_top");
        bottom = iconRegister.registerIcon(References.RESOURCESPREFIX + "craftingstation_bottom");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 0 ? bottom : (side == 1 ? top : blockIcon);
    }

    // ==========================================SHAPE========================================================//

    @Override
    public int getRenderType() {
        return renderId;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    /** Collide with each part separately, so items and small mobs can pass between the legs. */
    @Override
    @SuppressWarnings("rawtypes")
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list,
        Entity entity) {
        for (float[] b : TABLE_PARTS) {
            setBlockBounds(b[0], b[1], b[2], b[3], b[4], b[5]);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Only the slab and legs can be clicked. A ray through the gaps misses this block and reaches whatever is
     * behind or below it.
     */
    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
        MovingObjectPosition closest = null;
        double closestDist = Double.MAX_VALUE;

        for (float[] b : TABLE_PARTS) {
            setBlockBounds(b[0], b[1], b[2], b[3], b[4], b[5]);
            MovingObjectPosition hit = super.collisionRayTrace(world, x, y, z, start, end);
            if (hit != null) {
                double dist = hit.hitVec.squareDistanceTo(start);
                if (dist < closestDist) {
                    closestDist = dist;
                    closest = hit;
                }
            }
        }
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        return closest;
    }

    // ==========================================BEHAVIOUR====================================================//

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CraftingStationLogic();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        if (!world.isRemote) {
            FMLNetworkHandler.openGui(player, QuaesitaMagum.instance, GuiHandler.GUIID_CRAFTINGSTATION, world, x, y, z);
        }
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof CraftingStationLogic logic) {
            for (int i = 0; i < logic.getSizeInventory(); i++) {
                ItemStack stack = logic.getStackInSlot(i);
                if (stack == null || !logic.canDropInventorySlot(i)) continue;

                float ox = world.rand.nextFloat() * 0.8F + 0.1F;
                float oy = world.rand.nextFloat() * 0.8F + 0.1F;
                float oz = world.rand.nextFloat() * 0.8F + 0.1F;
                EntityItem item = new EntityItem(world, x + ox, y + oy, z + oz, stack.copy());
                item.motionX = world.rand.nextGaussian() * 0.05F;
                item.motionY = world.rand.nextGaussian() * 0.05F + 0.2F;
                item.motionZ = world.rand.nextGaussian() * 0.05F;
                world.spawnEntityInWorld(item);
                logic.setInventorySlotContents(i, null);
            }
        }
        super.breakBlock(world, x, y, z, block, meta);
    }
}
