package com.thenextelement.qmcore.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.world.BlockEvent;

import com.thenextelement.qmcore.common.ModBlocks;
import com.thenextelement.qmcore.common.tileentities.TilePlacedItem;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PlacedItemPacket implements IMessage {

    byte side = 0;
    int blockX = 0;
    int blockY = 0;
    int blockZ = 0;

    public PlacedItemPacket() {}

    public PlacedItemPacket(byte side, int x, int y, int z) {
        this.side = side;
        this.blockX = x;
        this.blockY = y;
        this.blockZ = z;
    }

    @Override
    public void toBytes(ByteBuf bytes) {
        bytes.writeByte(side);
        bytes.writeInt(blockX);
        bytes.writeInt(blockY);
        bytes.writeInt(blockZ);
    }

    @Override
    public void fromBytes(ByteBuf bytes) {
        this.side = bytes.readByte();
        this.blockX = bytes.readInt();
        this.blockY = bytes.readInt();
        this.blockZ = bytes.readInt();
    }

    public static class Handler implements IMessageHandler<PlacedItemPacket, IMessage> {

        @Override
        public IMessage onMessage(PlacedItemPacket message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            World world = player.worldObj;
            ItemStack held = player.getHeldItem();
            if (held == null) return null;

            ForgeDirection dir = ForgeDirection.getOrientation(message.side);
            int x = message.blockX + dir.offsetX;
            int y = message.blockY + dir.offsetY;
            int z = message.blockZ + dir.offsetZ;

            // client-supplied coordinates are untrusted
            if (!world.blockExists(x, y, z) || !world.isAirBlock(x, y, z)) return null;
            if (player.getDistanceSq(x + 0.5D, y + 0.5D, z + 0.5D) > 64.0D) return null;

            BlockEvent.PlaceEvent event = new BlockEvent.PlaceEvent(
                new BlockSnapshot(world, x, y, z, ModBlocks.placedItem, 0),
                world.getBlock(message.blockX, message.blockY, message.blockZ),
                player);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) return null;

            world.setBlock(x, y, z, ModBlocks.placedItem, message.side, 2);
            if (!(world.getTileEntity(x, y, z) instanceof TilePlacedItem tile)) {
                world.setBlockToAir(x, y, z);
                return null;
            }

            tile.setStack(held.copy());
            if (!player.capabilities.isCreativeMode) {
                player.destroyCurrentEquippedItem();
            }
            return null;
        }
    }
}
