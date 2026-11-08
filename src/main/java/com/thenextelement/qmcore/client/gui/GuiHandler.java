package com.thenextelement.qmcore.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.common.container.ContainerDisenchanter;
import com.thenextelement.qmcore.common.logic.CraftingStationLogic;
import com.thenextelement.qmcore.common.tileentities.TileDisenchanter;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {

    public static final int GUIID_DISENCHANTER = 0;
    public static final int GUIID_CRAFTINGSTATION = 1;

    public GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(QuaesitaMagum.instance, this);
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        switch (id) {
            case GUIID_DISENCHANTER -> {
                if (tile instanceof TileDisenchanter disenchanter) {
                    return new ContainerDisenchanter(player.inventory, disenchanter);
                }
            }
            case GUIID_CRAFTINGSTATION -> {
                if (tile instanceof CraftingStationLogic logic) {
                    return logic.getGuiContainer(player.inventory, world, x, y, z);
                }
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        switch (id) {
            case GUIID_DISENCHANTER -> {
                if (tile instanceof TileDisenchanter disenchanter) {
                    return new GUIDisenchanter(player.inventory, disenchanter);
                }
            }
            case GUIID_CRAFTINGSTATION -> {
                if (tile instanceof CraftingStationLogic logic) {
                    return new GUICraftingStation(player.inventory, logic, world, x, y, z);
                }
            }
        }

        return null;
    }

}
