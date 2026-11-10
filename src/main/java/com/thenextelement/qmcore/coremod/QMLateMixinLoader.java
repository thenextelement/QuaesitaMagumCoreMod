package com.thenextelement.qmcore.coremod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.thenextelement.qmcore.api.enums.Mods;

/**
 * Chooses the late worldgen mixins by which mods are loaded. Each group applies only when its target mod is present,
 * so a pack without Natura or Et Futurum never sees a missing mixin target.
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
        return mixins;
    }
}
