package com.thenextelement.qmcore;

import net.minecraft.creativetab.CreativeTabs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thenextelement.qmcore.client.creativetab.QMTab;
import com.thenextelement.qmcore.common.CommonProxy;
import com.thenextelement.qmcore.common.lib.References;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(
    modid = QuaesitaMagum.MODID,
    version = Tags.VERSION,
    name = "QuaesitaMagum",
    acceptedMinecraftVersions = "[1.7.10]")

public class QuaesitaMagum {

    public static final String MODID = "quaesitamagum";
    public static final String MODNAME = "Quaesita Magum";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @Mod.Instance(References.MODID)
    public static QuaesitaMagum instance;

    @SidedProxy(
        clientSide = "com.thenextelement.qmcore.client.ClientProxy",
        serverSide = "com.thenextelement.qmcore.common.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabQuaesitaMagum = new QMTab(
        CreativeTabs.getNextID(),
        References.MODID,
        "QuaesitaMagum",
        0);

    public static final String networkChannelName = "QmagumNC";
    public static SimpleNetworkWrapper network;
    public static final boolean isAutomagyLoaded = Loader.isModLoaded("Automagy");

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
    public void loadComplete(FMLLoadCompleteEvent event) {
        proxy.loadComplete(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

}
