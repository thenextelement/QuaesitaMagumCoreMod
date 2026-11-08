package com.thenextelement.qmcore.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.thenextelement.qmcore.common.container.ContainerCraftingStation;
import com.thenextelement.qmcore.common.logic.CraftingStationLogic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUICraftingStation extends GuiContainer {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(
        "textures/gui/container/crafting_table.png");

    private static final int CRAFTING_WIDTH = 176;
    private static final int CRAFTING_HEIGHT = 166;
    private static final int COLUMNS = 6;
    private static final int SLOT_SIZE = 18;
    private static final int BORDER_X = 7;
    private static final int BORDER_Y = 8;
    private static final int HEADER_HEIGHT = 10;
    private static final int TITLE_COLOR = 0x404040;

    private final CraftingStationLogic logic;

    public GUICraftingStation(InventoryPlayer inventory, CraftingStationLogic logic, World world, int x, int y, int z) {
        super(logic.getGuiContainer(inventory, world, x, y, z));
        this.logic = logic;
    }

    private boolean hasChest() {
        return logic.chest != null;
    }

    private int getChestPanelHeight() {
        int rows = (logic.slotCount + COLUMNS - 1) / COLUMNS;
        return BORDER_Y * 2 + HEADER_HEIGHT + rows * SLOT_SIZE;
    }

    @Override
    public void initGui() {
        // size must be set before super.initGui(), which centres the GUI from xSize/ySize
        this.xSize = CRAFTING_WIDTH + (hasChest() ? ContainerCraftingStation.SIDE_PANEL_WIDTH : 0);
        this.ySize = hasChest() ? Math.max(CRAFTING_HEIGHT, getChestPanelHeight()) : CRAFTING_HEIGHT;
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int craftingX = guiLeft;

        if (hasChest()) {
            drawPanel(guiLeft, guiTop, ContainerCraftingStation.SIDE_PANEL_WIDTH, getChestPanelHeight());
            for (int i = 0; i < logic.slotCount; i++) {
                drawSlotBackground(
                    guiLeft + BORDER_X + i % COLUMNS * SLOT_SIZE,
                    guiTop + BORDER_Y + HEADER_HEIGHT + i / COLUMNS * SLOT_SIZE);
            }
            craftingX += ContainerCraftingStation.SIDE_PANEL_WIDTH;
        }

        // drawRect leaves the GL colour changed, so reset it before drawing the texture
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager()
            .bindTexture(BACKGROUND);
        this.drawTexturedModalRect(craftingX, guiTop, 0, 0, CRAFTING_WIDTH, CRAFTING_HEIGHT);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int craftingX = 0;

        if (hasChest()) {
            craftingX = ContainerCraftingStation.SIDE_PANEL_WIDTH;
            IInventory chest = logic.chest.get();

            // close the screen if the attached inventory has been removed
            if (chest instanceof TileEntity te && te.getWorldObj() != null
                && te.getWorldObj()
                    .getTileEntity(te.xCoord, te.yCoord, te.zCoord) == null) {
                mc.thePlayer.closeScreen();
                return;
            }

            if (chest != null) {
                this.fontRendererObj.drawString(
                    StatCollector.translateToLocal(chest.getInventoryName()),
                    BORDER_X + 1,
                    BORDER_Y + 1,
                    TITLE_COLOR);
            }
        }

        this.fontRendererObj
            .drawString(StatCollector.translateToLocal(logic.getInventoryName()), craftingX + 28, 6, TITLE_COLOR);
        this.fontRendererObj
            .drawString(StatCollector.translateToLocal("container.inventory"), craftingX + 8, 72, TITLE_COLOR);
    }

    /** Vanilla-style raised grey panel. */
    private void drawPanel(int x, int y, int w, int h) {
        drawRect(x, y, x + w, y + h, 0xFF555555);
        drawRect(x, y, x + w - 1, y + h - 1, 0xFFFFFFFF);
        drawRect(x + 1, y + 1, x + w - 1, y + h - 1, 0xFFC6C6C6);
    }

    /** Vanilla-style inset slot. */
    private void drawSlotBackground(int x, int y) {
        drawRect(x, y, x + SLOT_SIZE, y + SLOT_SIZE, 0xFF373737);
        drawRect(x + 1, y + 1, x + SLOT_SIZE, y + SLOT_SIZE, 0xFFFFFFFF);
        drawRect(x + 1, y + 1, x + SLOT_SIZE - 1, y + SLOT_SIZE - 1, 0xFF8B8B8B);
    }
}
