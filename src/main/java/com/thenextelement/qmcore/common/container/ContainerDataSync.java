package com.thenextelement.qmcore.common.container;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.common.lib.References;
import com.thenextelement.qmcore.common.network.TileObjectPacket;
import com.thenextelement.qmcore.common.tileentities.TileObjectSync;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ContainerDataSync extends Container {

    public Object sendObjectToClient(TileObjectSync tile, int index, Object object) {
        for (Object p : crafters) {
            QuaesitaMagum.network
                .sendTo(new TileObjectPacket(tile, References.INT_ID, index, object), (EntityPlayerMP) p);
        }
        return object;
    }

    /**
     * Sends two ints to the server-side Container. Normally the first int identifies which variable to update, and the
     * second contains the new value. if tile != null the packet will be sent to the tile client tile instead of the
     * client container.
     */
    @SideOnly(Side.CLIENT)
    public Object sendObjectToServer(TileObjectSync tile, int index, Object object) {
        QuaesitaMagum.network.sendToServer(new TileObjectPacket(tile, References.INT_ID, index, object));
        return object;
    }

    /**
     * Called when a packet is received from ether the client or the server
     */
    public abstract void receiveSyncData(int index, int value);

}
