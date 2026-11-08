package com.thenextelement.qmcore.common.block.machine;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.client.gui.GuiHandler;
import com.thenextelement.qmcore.common.ModBlocks;
import com.thenextelement.qmcore.common.block.BlockCustomDrop;
import com.thenextelement.qmcore.common.lib.References;
import com.thenextelement.qmcore.common.lib.Strings;
import com.thenextelement.qmcore.common.tileentities.TileDisenchanter;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DisEnchanter extends BlockCustomDrop {

    IIcon top;

    IIcon bottom;

    public DisEnchanter() {
        super(Material.iron);
        this.setBlockName(Strings.disEnchanterName);
        this.setCreativeTab(QuaesitaMagum.tabQuaesitaMagum);
        this.setStepSound(soundTypeStone);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        ModBlocks.register(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + "disEnchanter_side");
        top = iconRegister.registerIcon(References.RESOURCESPREFIX + "disEnchanter_top");
        bottom = iconRegister.registerIcon(References.RESOURCESPREFIX + "disEnchanter_bottom");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 0 ? bottom : (side == 1 ? top : blockIcon);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileDisenchanter();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float prx,
        float pry, float prz) {
        if (!world.isRemote) {
            FMLNetworkHandler.openGui(player, QuaesitaMagum.instance, GuiHandler.GUIID_DISENCHANTER, world, x, y, z);
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return super.getRenderType();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    protected boolean dropInventory() {
        return true;
    }

    @Override
    protected boolean hasCustomDrops() {
        return false;
    }

    @Override
    protected void getCustomTileEntityDrops(TileEntity te, List<ItemStack> drops) {}

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        boolean powered = worldIn.isBlockIndirectlyGettingPowered(x, y, z)
            || worldIn.isBlockIndirectlyGettingPowered(x, y - 1, z);
        int meta = worldIn.getBlockMetadata(x, y, z);
        boolean metaUnpowered = (meta & 8) == 0;

        if (powered && metaUnpowered) {
            if (worldIn.getTileEntity(x, y, z) instanceof TileDisenchanter tde) tde.buttonClick(null);
            worldIn.setBlockMetadataWithNotify(x, y, z, meta | 8, 4);
            return;
        }
        if (powered || metaUnpowered) return;
        worldIn.setBlockMetadataWithNotify(x, y, z, meta & -9, 4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        super.randomDisplayTick(world, x, y, z, rand);

        for (int x1 = x - 2; x1 <= x + 2; ++x1) {
            for (int z1 = z - 2; z1 <= z + 2; ++z1) {
                if (x1 > x - 2 && x1 < x + 2 && z1 == z - 1) {
                    z1 = z + 2;
                }

                if (rand.nextInt(16) == 0) {
                    for (int y1 = y; y1 <= y + 1; ++y1) {
                        if (world.getBlock(x1, y1, z1) == Blocks.bookshelf) {
                            if (!world.isAirBlock((x1 - x) / 2 + x, y1, (z1 - z) / 2 + z)) {
                                break;
                            }

                            // world.spawnParticle("enchantmenttable", x + 0.5D, y + 2.0D, z + 0.5D, l - x +
                            // rand.nextFloat() - 0.5D, j1 - y - rand.nextFloat() - 1.0F, i1 - z + rand.nextFloat() -
                            // 0.5D);
                            world.spawnParticle(
                                "enchantmenttable",
                                x1 + 0.4 + (rand.nextFloat() * 0.2),
                                y1 + 0.8,
                                z1 + 0.4 + (rand.nextFloat() * 0.2),
                                x - x1 + rand.nextFloat() - 0.5,
                                y - y1 + rand.nextFloat(),
                                z - z1 + rand.nextFloat() - 0.5);
                        }
                    }
                }
            }
        }
    }
}
