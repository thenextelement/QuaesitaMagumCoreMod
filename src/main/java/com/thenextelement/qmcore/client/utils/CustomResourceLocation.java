package com.thenextelement.qmcore.client.utils;

import net.minecraft.util.ResourceLocation;

import com.thenextelement.qmcore.client.handler.ResourceHandler;
import com.thenextelement.qmcore.common.lib.References;

public class CustomResourceLocation extends ResourceLocation {

    private int width;
    private int height;

    public CustomResourceLocation(String texturePath, int width, int height) {
        super(References.MODID.toLowerCase(), "textures/gui/manualimages/" + texturePath);
        this.width = width;
        this.height = height;
    }

    public void bind() {
        ResourceHandler.bindTexture(this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
