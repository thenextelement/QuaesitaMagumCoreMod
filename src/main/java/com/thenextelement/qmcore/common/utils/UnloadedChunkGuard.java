package com.thenextelement.qmcore.common.utils;

import net.minecraft.world.World;

/**
 * Lets a worldgen decorator run without loading neighbouring chunks.
 * <p>
 * Tree and geode generators read and write blocks past their own chunk. Touching a chunk that is not loaded makes the
 * chunk provider load and populate it on the spot, which is the cascade ArchaicFix logs. While a guarded generator
 * runs, reads from unloaded chunks return air, writes into them are dropped and tile entities are absent, so nothing
 * is loaded. Reads and writes inside loaded chunks are untouched.
 * <p>
 * The depth counter is per thread, so the server thread's generators never affect another thread's world access.
 */
public final class UnloadedChunkGuard {

    private static final ThreadLocal<int[]> DEPTH = ThreadLocal.withInitial(() -> new int[1]);

    private UnloadedChunkGuard() {}

    public static void enter() {
        DEPTH.get()[0]++;
    }

    public static void exit() {
        int[] depth = DEPTH.get();
        if (depth[0] > 0) depth[0]--;
    }

    /** True when a guarded generator is running and the block at (x, z) lies in a chunk that is not loaded. */
    public static boolean isUnloadedAccess(World world, int x, int z) {
        if (world == null || world.isRemote || DEPTH.get()[0] == 0) return false;
        return !world.getChunkProvider()
            .chunkExists(x >> 4, z >> 4);
    }
}
