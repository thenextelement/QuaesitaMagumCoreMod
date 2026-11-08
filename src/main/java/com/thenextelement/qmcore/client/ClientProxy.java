package com.thenextelement.qmcore.client;

import static com.thenextelement.qmcore.integration.nei.IMCForNEI.IMCSender;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.client.handler.ClientEventHandler;
import com.thenextelement.qmcore.client.handler.ItemDisplayManager;
import com.thenextelement.qmcore.client.handler.ResourceHandler;
import com.thenextelement.qmcore.client.keybinding.KeyInputHandler;
import com.thenextelement.qmcore.client.render.block.RenderTableBlock;
import com.thenextelement.qmcore.client.render.tile.RenderTileDisEnchanter;
import com.thenextelement.qmcore.client.render.tile.RenderTilePlacedItem;
import com.thenextelement.qmcore.common.CommonProxy;
import com.thenextelement.qmcore.common.block.machine.BlockCraftingStation;
import com.thenextelement.qmcore.common.tileentities.TileDisenchanter;
import com.thenextelement.qmcore.common.tileentities.TilePlacedItem;
import com.thenextelement.qmcore.integration.nei.NEIUnificationHider;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;

public final class ClientProxy extends CommonProxy {

    // private static final boolean debug = DraconicEvolution.debug;

    private ClientEventHandler clientHandler;
    private KeyInputHandler keybindHandler;
    private ItemDisplayManager itemDisplay;
    // private ShieldRenderHandler shieldRenderer;

    public ClientProxy() {
        FMLCommonHandler.instance()
            .bus()
            .register(this);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // if (debug) System.out.println("on Client side");
        super.preInit(event);

        ResourceHandler.init(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        this.keybindHandler = new KeyInputHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(this.keybindHandler);
        this.clientHandler = new ClientEventHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(this.clientHandler);
        MinecraftForge.EVENT_BUS.register(this.clientHandler);
        // final HudHandler hudHandler = new HudHandler();
        // FMLCommonHandler.instance().bus().register(hudHandler);
        // MinecraftForge.EVENT_BUS.register(hudHandler);
        registerRenderIDs();
        registerRendering();
        ResourceHandler.instance.tick(null);
        IMCSender();
        this.itemDisplay = new ItemDisplayManager(60);
        FMLCommonHandler.instance()
            .bus()
            .register(this.itemDisplay);
        MinecraftForge.EVENT_BUS.register(this.itemDisplay);
        // this.shieldRenderer = new ShieldRenderHandler();
        // FMLCommonHandler.instance().bus().register(this.shieldRenderer);
        // MinecraftForge.EVENT_BUS.register(this.shieldRenderer);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ResourceHandler.instance.tick(null);
    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event) {
        super.loadComplete(event);
        if (Mods.NotEnoughItems.isModLoaded()) NEIUnificationHider.hideItems();
    }

    @SubscribeEvent
    public void onClientDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        // this.clientInhibitorsMap.clear();
        this.itemDisplay.startDrawing(null);
    }

    @Override
    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        // if (event.world.isRemote) {
        // this.clientInhibitorsMap.remove(event.world);
        // } else {
        super.onWorldUnload(event);

    }

    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.
    private void registerRendering() {
        // spotless:off
        // Item Rendering

        // TileEntitySpecialRenderer
        ClientRegistry.bindTileEntitySpecialRenderer(TilePlacedItem.class, new RenderTilePlacedItem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileDisenchanter.class, new RenderTileDisEnchanter());

    }

    private void registerRenderIDs() {
        BlockCraftingStation.renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderTableBlock(BlockCraftingStation.renderId));
    }

    @Override
    public boolean isDedicatedServer() {
        return false;
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public boolean isSpaceDown() {
        return Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed();
    }

    @Override
    public boolean isShiftDown() {
        return Minecraft.getMinecraft().gameSettings.keyBindSneak.getIsKeyPressed();
    }

    @Override
    public boolean isCtrlDown() {
        return Minecraft.getMinecraft().gameSettings.keyBindSprint.getIsKeyPressed();
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public boolean isClient() {
        return true;
    }

    public KeyInputHandler getKeybindHandler() {
        return keybindHandler;
    }

    public void displayItem(ItemStack stack) {
        this.itemDisplay.startDrawing(stack);
    }

    // public int getElapsedTicks() {
    // return this.clientHandler.getElapsedTicks();
    // }

}
