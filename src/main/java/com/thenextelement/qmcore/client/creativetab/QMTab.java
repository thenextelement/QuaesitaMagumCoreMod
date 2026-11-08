package com.thenextelement.qmcore.client.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class QMTab extends CreativeTabs {

    private String label;
    private int tab;

    static ItemStack iconStackStaff;

    public static void initialize() {

        iconStackStaff = new ItemStack(Items.stick);

    }

    public QMTab(int id, String modid, String label, int tab) {

        super(id, modid);
        this.label = label;
        this.tab = tab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {

        return new ItemStack(Items.ender_eye);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return getIconItemStack().getItem();
    }

    @Override
    public String getTabLabel() {
        return this.label;
    }

}
