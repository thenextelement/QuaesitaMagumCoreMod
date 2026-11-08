package com.thenextelement.qmcore.mixin.mixins.early;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Util;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.thenextelement.qmcore.client.utils.IconLoader;
import com.thenextelement.qmcore.coremod.QMLoadingPlugin;

/** Sets the pack's window title and icon at startup (ported from NewHorizonsCoreMod). */
@Mixin(Minecraft.class)
public class MixinMinecraft_PackIcon {

    @Unique
    private boolean qmcore$loadedPackIcon;

    @ModifyArg(
        method = "startGame",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", remap = false))
    private String qmcore$changeWindowTitle(String original) {
        this.qmcore$loadedPackIcon = IconLoader.setCustomIcon("assets/PackIcon.png");
        return QMLoadingPlugin.getWindowTitle();
    }

    /**
     * Vanilla sets its own icon right after the title unless the OS is macOS. Once the pack icon is set, report macOS
     * here so vanilla skips that and doesn't overwrite it.
     */
    @ModifyExpressionValue(
        method = "startGame",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Util;getOSType()Lnet/minecraft/util/Util$EnumOS;"))
    private Util.EnumOS qmcore$skipVanillaIcon(Util.EnumOS osType) {
        return this.qmcore$loadedPackIcon ? Util.EnumOS.OSX : osType;
    }
}
