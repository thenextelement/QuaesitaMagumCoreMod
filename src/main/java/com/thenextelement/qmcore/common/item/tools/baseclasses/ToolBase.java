package com.thenextelement.qmcore.common.item.tools.baseclasses;

import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.QuaesitaMagum;

/**
 * Created by Brandon on 2/01/2015.
 */
public class ToolBase extends QMItemBase {

    private float efficiencyOnProperMaterial = 4.0F;

    public float damageVsEntity;

    protected Item.ToolMaterial toolMaterial;

    protected ToolBase(float baseDamage, Item.ToolMaterial material, Set blockOverrides) {
        this.toolMaterial = material;
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
        this.damageVsEntity = baseDamage + material.getDamageVsEntity();
        this.setCreativeTab(QuaesitaMagum.tabQuaesitaMagum);
    }

    public float getEfficiency(ItemStack stack) {
        return efficiencyOnProperMaterial;
    }

}
