package com.thenextelement.qmcore.coremod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.common.tech.DisabledTech;

import cpw.mods.fml.relauncher.FMLLaunchHandler;

/**
 * Chooses the late mixins by which mods are loaded: the worldgen fixes, and the Forestry/Binnie RF-tech disabling (see
 * DisabledTech, switched by {@code disableRFTech}). Each group applies only when its target mod is present, so a pack
 * without one of them never sees a missing mixin target.
 * <p>
 * The World guard is an early mixin (see QMLoadingPlugin) because World is loaded before late mixins apply.
 */
@LateMixin
public class QMLateMixinLoader implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.qmcore.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        List<String> mixins = new ArrayList<>();
        boolean natura = loadedMods.contains(Mods.ModIDs.NATURA);
        boolean etFuturum = loadedMods.contains(Mods.ModIDs.ET_FUTURUM_REQUIEM);

        if (natura) mixins.add("natura.MixinNaturaWorldgen_UnloadedChunkScope");
        if (etFuturum) {
            mixins.add("etfuturum.MixinWorldGenGlowLichen_ChunkEdge");
            mixins.add("etfuturum.MixinWorldGenCaveVines_ChunkEdge");
            mixins.add("etfuturum.MixinWorldGenGeode_UnloadedChunkScope");
        }

        if (DisabledTech.isEnabled()) {
            boolean client = FMLLaunchHandler.side()
                .isClient();
            if (loadedMods.contains(Mods.ModIDs.FORESTRY)) {
                mixins.add("forestry.MixinTilePowered_NoRF");
                mixins.add("forestry.MixinTileAnalyzer_NoRF");
                mixins.add("forestry.MixinTileAlvearyClimatiser_NoRF");
                mixins.add("forestry.MixinTileFarmGearbox_NoRF");
                if (client) {
                    // getSubBlocks is @SideOnly(CLIENT): it doesn't exist on a dedicated server
                    mixins.add("forestry.MixinBlockAlveary_HideRF");
                    mixins.add("forestry.MixinBlockFarm_HideGearbox");
                    mixins.add("forestry.MixinBlockBase_HideAnalyzer");
                }
            }
            if (loadedMods.contains(Mods.ModIDs.BINNIE_CORE)) {
                mixins.add("binnie.MixinMachineGroup_HideRF");
                mixins.add("binnie.MixinComponentPowerReceptor_NoRF");
            }
            if (client && loadedMods.contains(Mods.ModIDs.GENETICS)
                && loadedMods.contains(Mods.ModIDs.NOT_ENOUGH_ITEMS)) {
                mixins.add("binnie.MixinNEIGeneticsConfig_Disable");
            }
        }
        return mixins;
    }
}
