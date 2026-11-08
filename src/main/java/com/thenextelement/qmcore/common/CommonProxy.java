package com.thenextelement.qmcore.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

import com.thenextelement.qmcore.Config;
import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.client.creativetab.QMTab;
import com.thenextelement.qmcore.client.gui.GuiHandler;
import com.thenextelement.qmcore.common.achievements.Achievements;
import com.thenextelement.qmcore.common.handler.ConfigHandler;
import com.thenextelement.qmcore.common.handler.CraftingHandler;
import com.thenextelement.qmcore.common.handler.FMLEventHandler;
import com.thenextelement.qmcore.common.handler.MinecraftForgeEventHandler;
import com.thenextelement.qmcore.common.lib.References;
import com.thenextelement.qmcore.common.logic.CraftingStationLogic;
import com.thenextelement.qmcore.common.network.ButtonPacket;
import com.thenextelement.qmcore.common.network.PlacedItemPacket;
import com.thenextelement.qmcore.common.network.TileObjectPacket;
import com.thenextelement.qmcore.common.scripts.ScriptLoader;
import com.thenextelement.qmcore.common.tileentities.TileDisenchanter;
import com.thenextelement.qmcore.common.tileentities.TilePlacedItem;
import com.thenextelement.qmcore.common.unification.OreUnification;
import com.thenextelement.qmcore.common.unification.UnificationEventHandler;
import com.thenextelement.qmcore.common.utils.LogHelper;
import com.thenextelement.qmcore.common.utils.handlers.FileHandler;
import com.thenextelement.qmcore.common.utils.handlers.ProcessHandler;
import com.thenextelement.qmcore.coremod.QMLoadingPlugin;
import com.thenextelement.qmcore.integration.minetweaker.MinetweakerFurnaceFix;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

    private Achievements achievements;

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public CommonProxy() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void preInit(FMLPreInitializationEvent event) {

        FileHandler.init(event);
        ProcessHandler.init();
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new MinecraftForgeEventHandler());
        this.achievements = new Achievements();
        MinecraftForge.EVENT_BUS.register(this.achievements);
        FMLCommonHandler.instance()
            .bus()
            .register(this.achievements);
        FMLCommonHandler.instance()
            .bus()
            .register(new FMLEventHandler());
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        QMTab.initialize();
        ModBlocks.init();
        // ModItems.init();
        registerTileEntities();
        initializeNetwork();
        LogHelper.info("Finished PreInitialization");
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        registerGuiHandler();
        CraftingHandler.init();
        if (Mods.CraftTweaker.isModLoaded()) MinecraftForge.EVENT_BUS.register(new MinetweakerFurnaceFix());
        if (QMLoadingPlugin.enableOreUnification) MinecraftForge.EVENT_BUS.register(new UnificationEventHandler());
        LogHelper.info("Finished Initialization");

    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {

        LogHelper.info("Finished PostInitialization");

    }

    // every mod has registered its recipes: run the pack scripts
    public void loadComplete(FMLLoadCompleteEvent event) {
        if (QMLoadingPlugin.enableOreUnification) OreUnification.apply();
        ScriptLoader.run();
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {

    }

    private void initializeNetwork() {
        // spotless:off
        QuaesitaMagum.network = NetworkRegistry.INSTANCE.newSimpleChannel(QuaesitaMagum.networkChannelName);
        QuaesitaMagum.network.registerMessage(ButtonPacket.Handler.class, ButtonPacket.class, 0, Side.SERVER);
        QuaesitaMagum.network.registerMessage(PlacedItemPacket.Handler.class, PlacedItemPacket.class, 1, Side.SERVER);
        QuaesitaMagum.network.registerMessage(TileObjectPacket.Handler.class, TileObjectPacket.class, 4, Side.SERVER);


        //spotless:on
    }

    private void registerTileEntities() {
        // spotless:off
        GameRegistry.registerTileEntity(TileDisenchanter.class, References.RESOURCESPREFIX + "TileEntityDisEnchanter");
        GameRegistry.registerTileEntity(TilePlacedItem.class, References.RESOURCESPREFIX + "TilePlacedItem");
        GameRegistry.registerTileEntity(CraftingStationLogic.class, References.RESOURCESPREFIX + "CraftingStation");
    //spotless:on
    }

    public void registerGuiHandler() {
        new GuiHandler();
    }

    public boolean isDedicatedServer() {
        return true;
    }

    public MinecraftServer getMCServer() {
        return FMLCommonHandler.instance()
            .getMinecraftServerInstance();
    }

    public World getClientWorld() {
        return null;
    }

    public boolean isSpaceDown() {
        return false;
    }

    public boolean isCtrlDown() {
        return false;
    }

    public boolean isShiftDown() {
        return false;
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public boolean isClient() {
        return false;
    }

}
