package com.thenextelement.qmcore.common.item.tools.baseclasses;
// spotless:off
import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.common.item.ItemQM;
import com.thenextelement.qmcore.common.utils.ItemNBTHelper;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class QMItemBase extends ItemQM {


    public QMItemBase() {
        this.setMaxStackSize(1);
        this.setCreativeTab(QuaesitaMagum.tabQuaesitaMagum);
    }
}
//spotless:on
