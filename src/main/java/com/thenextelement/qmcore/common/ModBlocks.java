package com.thenextelement.qmcore.common;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import com.thenextelement.qmcore.common.block.BlockQM;
import com.thenextelement.qmcore.common.block.PlacedItem;
import com.thenextelement.qmcore.common.block.machine.BlockCraftingStation;
import com.thenextelement.qmcore.common.block.machine.DisEnchanter;
import com.thenextelement.qmcore.common.lib.References;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(References.MODID)
public class ModBlocks {

    public static BlockQM disEnchanter;
    public static BlockQM placedItem;
    public static BlockQM craftingStation;

    public static void init() {
        disEnchanter = new DisEnchanter();
        placedItem = new PlacedItem();
        craftingStation = new BlockCraftingStation();
    }

    public static void register(BlockQM block) {
        register(block, ItemBlock.class);
    }

    public static void register(BlockQM block, Class<? extends ItemBlock> item) {

        String full = block.getUnlocalizedName();
        String name = full.substring(full.indexOf(':') + 1);
        GameRegistry.registerBlock(block, item, name);
    }

    public static void registerOther(Block block) {
        String name = block.getUnlocalizedName()
            .substring(
                block.getUnlocalizedName()
                    .indexOf(".") + 1);
    }

}
