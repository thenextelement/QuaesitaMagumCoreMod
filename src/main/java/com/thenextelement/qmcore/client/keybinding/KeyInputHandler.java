package com.thenextelement.qmcore.client.keybinding;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovingObjectPosition;

import org.lwjgl.input.Keyboard;

import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.common.item.tools.baseclasses.ToolHandler;
import com.thenextelement.qmcore.common.lib.References;
import com.thenextelement.qmcore.common.network.PlacedItemPacket;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class KeyInputHandler {

    private final KeyBinding placeItem;

    public KeyInputHandler() {
        placeItem = new KeyBinding("key.placeItem", Keyboard.KEY_V, References.MODNAME);

        ClientRegistry.registerKeyBinding(placeItem);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

        if (this.placeItem.isPressed()) {
            handlePlaceItemKey();
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

        if (this.placeItem.isPressed()) {
            handlePlaceItemKey();
        }

    }

    private void handlePlaceItemKey() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null || mc.theWorld == null) return;
        MovingObjectPosition mop = ToolHandler.raytraceFromEntity(mc.theWorld, mc.thePlayer, 4.5D);
        if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            QuaesitaMagum.network
                .sendToServer(new PlacedItemPacket((byte) mop.sideHit, mop.blockX, mop.blockY, mop.blockZ));
        }
    }

}
