package com.thenextelement.qmcore.common.handler;

import com.thenextelement.qmcore.common.lib.References;
import com.thenextelement.qmcore.common.utils.LogHelper;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class FMLEventHandler {

    private static boolean mmGiven = false;

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equalsIgnoreCase(References.MODID)) {
            ConfigHandler.syncConfig();
            LogHelper.info("Config Changed");
        }
    }

    @SubscribeEvent
    public void serverTickEvent(TickEvent event) {

    }

}
