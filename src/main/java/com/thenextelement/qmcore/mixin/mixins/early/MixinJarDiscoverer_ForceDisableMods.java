package com.thenextelement.qmcore.mixin.mixins.early;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.thenextelement.qmcore.coremod.QMLoadingPlugin;

import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.discovery.ASMDataTable;
import cpw.mods.fml.common.discovery.JarDiscoverer;
import cpw.mods.fml.common.discovery.ModCandidate;

/**
 * Drops the mods listed in {@code forceDisabledMods} as FML discovers each jar, before duplicates are checked or
 * anything is constructed. Their classes stay on the classpath, but FML never loads them and
 * {@code Loader.isModLoaded} returns false, so other mods' compat code skips them cleanly.
 * <p>
 * Targets JarDiscoverer (as Hodgepodge and lwjgl3ify do): Loader and ModDiscoverer are already loaded by the time
 * early mixins apply.
 */
@Mixin(value = JarDiscoverer.class, remap = false)
public class MixinJarDiscoverer_ForceDisableMods {

    @Inject(method = "discover", at = @At("RETURN"))
    private void qmcore$removeDisabledMods(ModCandidate candidate, ASMDataTable table,
        CallbackInfoReturnable<List<ModContainer>> cir) {
        if (QMLoadingPlugin.forceDisabledMods.isEmpty()) return;
        cir.getReturnValue()
            .removeIf(mod -> {
                if (!QMLoadingPlugin.forceDisabledMods.contains(mod.getModId())) return false;
                QMLoadingPlugin.LOG.info("Force-disabling mod {} ({})", mod.getModId(), mod.getName());
                return true;
            });
    }
}
