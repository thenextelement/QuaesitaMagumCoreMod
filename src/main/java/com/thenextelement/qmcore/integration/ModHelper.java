package com.thenextelement.qmcore.integration;

import cpw.mods.fml.common.Loader;

public class ModHelper {

    public static boolean isGTNHLibLoaded;
    public static boolean isHodgepodgeLoaded;
    public static boolean isBackhandLoaded;

    static {
        isGTNHLibLoaded = Loader.isModLoaded("gtnhlib");
        isHodgepodgeLoaded = Loader.isModLoaded("hodgepodge");
        isBackhandLoaded = Loader.isModLoaded("backhand");
    }

    // public static float applyModDamageAdjustments(ArmorSummary summary, LivingAttackEvent event) {
    // if (summary == null) return event.ammount;
    // EntityPlayer attacker = event.source.getEntity() instanceof EntityPlayer
    // ? (EntityPlayer) event.source.getEntity()
    // : null;
    //
    // if (attacker == null) {
    // return event.ammount;
    // }

}
