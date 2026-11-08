package com.thenextelement.qmcore.common.network;

import net.minecraft.inventory.Container;

import com.thenextelement.qmcore.common.container.ContainerDisenchanter;
import com.thenextelement.qmcore.common.tileentities.TileDisenchanter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class ButtonPacket implements IMessage {

    public static final byte ID_DISENCHANTER = 0;
    byte buttonId = 0;
    boolean state = false;

    public ButtonPacket() {}

    public ButtonPacket(byte buttonId, boolean state) {
        this.buttonId = buttonId;
        this.state = state;
    }

    @Override
    public void fromBytes(ByteBuf bytes) {
        this.buttonId = bytes.readByte();
        this.state = bytes.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf bytes) {
        bytes.writeByte(buttonId);
        bytes.writeBoolean(state);
    }

    public static class Handler implements IMessageHandler<ButtonPacket, IMessage> {

        @Override
        public IMessage onMessage(ButtonPacket message, MessageContext ctx) {
            switch (message.buttonId) {
                case ID_DISENCHANTER: {
                    Container container = ctx.getServerHandler().playerEntity.openContainer;
                    if (container != null && container instanceof ContainerDisenchanter) {
                        TileDisenchanter tile = ((ContainerDisenchanter) container).getTile();
                        tile.buttonClick(ctx.getServerHandler().playerEntity);
                    }
                    break;
                }

                default:
                    break;
            }
            return null;
        }
    }
}
