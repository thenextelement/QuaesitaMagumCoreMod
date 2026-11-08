package com.thenextelement.qmcore.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import com.thenextelement.qmcore.common.block.machine.BlockCraftingStation;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/** Renders a block as a table: a 4px top slab on four 4x4 legs (ported from TiCon's TableRender). */
public class RenderTableBlock implements ISimpleBlockRenderingHandler {

    private final int renderId;

    public RenderTableBlock(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        IIcon[] icons = new IIcon[6];
        for (int side = 0; side < 6; side++) {
            icons[side] = renderer.getBlockIconFromSideAndMetadata(block, side, metadata);
        }

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        for (float[] b : BlockCraftingStation.TABLE_PARTS) {
            renderer.setRenderBounds(b[0], b[1], b[2], b[3], b[4], b[5]);
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icons[0]);
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icons[1]);
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icons[2]);
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icons[3]);
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icons[4]);
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icons[5]);
        }
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        for (float[] b : BlockCraftingStation.TABLE_PARTS) {
            renderer.setRenderBounds(b[0], b[1], b[2], b[3], b[4], b[5]);
            renderer.renderStandardBlock(block, x, y, z);
        }
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }
}
