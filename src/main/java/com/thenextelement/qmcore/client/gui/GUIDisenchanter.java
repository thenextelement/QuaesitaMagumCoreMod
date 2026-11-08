package com.thenextelement.qmcore.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.common.container.ContainerDisenchanter;
import com.thenextelement.qmcore.common.lib.References;
import com.thenextelement.qmcore.common.network.ButtonPacket;
import com.thenextelement.qmcore.common.tileentities.TileDisenchanter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUIDisenchanter extends GuiContainer {

    public EntityPlayer player;
    private TileDisenchanter tile;
    private boolean cachRecipeValid = false;
    private int cachCost = 0;

    public GUIDisenchanter(InventoryPlayer invPlayer, TileDisenchanter tile) {
        super(new ContainerDisenchanter(invPlayer, tile));

        xSize = 176;
        ySize = 142;

        this.tile = tile;
        this.player = invPlayer.player;
    }

    private static final ResourceLocation texture = new ResourceLocation(
        References.MODID.toLowerCase(),
        "textures/gui/DisEnchanter.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        drawCenteredString(
            fontRendererObj,
            StatCollector.translateToLocal("gui.qm.disEnchanter.main.title"),
            88,
            4,
            0x00FFFF);

        fontRendererObj.drawString(StatCollector.translateToLocal("gui.qm.disEnchanter.itemDamage1"), 5, 40, 0x0000ff);
        fontRendererObj.drawString(
            StatCollector.translateToLocalFormatted("gui.qm.disEnchanter.itemDamage2", (40 - tile.bookPower) + "%"),
            5,
            49,
            0x0000ff);
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        int posX = (this.width - xSize) / 2;
        int posY = (this.height - ySize) / 2;
        buttonList.add(new GuiButtonAHeight(0, posX + 108, posY + 45, 60, 12, ""));
        updateButtonState();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            QuaesitaMagum.network.sendToServer(new ButtonPacket(ButtonPacket.ID_DISENCHANTER, true));
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (cachRecipeValid != tile.isValidRecipe) {
            cachRecipeValid = tile.isValidRecipe;
            updateButtonState();
        }
        if (cachCost != tile.disenchantCost) {
            cachCost = tile.disenchantCost;
            updateButtonState();
        }
    }

    private void updateButtonState() {
        boolean flag = cachRecipeValid;
        if (flag && player.experienceLevel < tile.disenchantCost && !player.capabilities.isCreativeMode) flag = false;
        ((GuiButtonAHeight) buttonList.get(0)).enabled = flag;
        ((GuiButtonAHeight) buttonList.get(0)).packedFGColour = cachRecipeValid ? 0x000000 : 0xdf0000;
        ((GuiButtonAHeight) buttonList.get(0)).displayString = StatCollector
            .translateToLocalFormatted("gui.qm.disEnchanter.cost", tile.disenchantCost);
    }

}
