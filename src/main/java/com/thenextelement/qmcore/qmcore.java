package com.thenextelement.qmcore;

import cpw.mods.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;

@Mod(
    modid = QuaesitaMagum.MODID,
    version = Tags.VERSION,
    name = "QuaesitaMagum",
    acceptedMinecraftVersions = "[1.7.10]")
public class qmcore {

    public static final String MODID = "qmcore";
    public static final String MODNAME = "Quaesita Magum";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @Mod.Instance(MODID)
    public static QuaesitaMagum qmInstance;

    @SidedProxy(
        clientSide = "com.thenextelement.qmcore.ClientProxy",
        serverSide = "com.thenextelement.qmcore.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }


    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }




}
