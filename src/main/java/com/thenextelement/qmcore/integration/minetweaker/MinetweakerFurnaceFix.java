package com.thenextelement.qmcore.integration.minetweaker;

import net.minecraftforge.event.FuelBurnTimeEvent;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Makes MineTweaker's {@code furnace.setFuel(...)} work for every item. Ported from NewHorizonsCoreMod.
 * <p>
 * MineTweaker changes fuel values through an IFuelHandler, but the vanilla furnace checks its own hardcoded fuels
 * (including anything made of wood) before asking fuel handlers, so those can't be changed. Forge fires
 * FuelBurnTimeEvent before that hardcoded list; answering it with GameRegistry's value lets MineTweaker win.
 */
public class MinetweakerFurnaceFix {

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public void onFuelBurnTime(FuelBurnTimeEvent event) {
        int burnTime = GameRegistry.getFuelValue(event.fuel);
        if (burnTime > 0) {
            event.burnTime = burnTime;
            event.setResult(Event.Result.ALLOW);
        }
    }
}
