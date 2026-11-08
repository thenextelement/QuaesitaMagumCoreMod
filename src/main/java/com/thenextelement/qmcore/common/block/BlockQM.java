package com.thenextelement.qmcore.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.thenextelement.qmcore.common.lib.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockQM extends Block {

    public BlockQM(final Material material) {
        super(material);
        this.setHardness(5F);
        this.setResistance(10.0F);
    }

    public BlockQM() {
        super(Material.iron);
        this.setHardness(5F);
        this.setResistance(10.0F);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format(
            "tile.%s%s",
            References.MODID.toLowerCase() + ":",
            getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    public String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister
            .registerIcon(References.RESOURCESPREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
}
