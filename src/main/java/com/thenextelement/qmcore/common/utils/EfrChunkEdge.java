package com.thenextelement.qmcore.common.utils;

/**
 * Shared test for the Et Futurum decorators that read their neighbours with {@code World.getBlock}.
 * <p>
 * A read one block past a chunk border asks the chunk provider for the neighbouring chunk. During population that
 * loads and generates the chunk right away, which is the "cascading worldgen" ArchaicFix logs. Skipping the attempt
 * when its origin sits on a border column or row keeps every read inside the chunk being populated.
 */
public final class EfrChunkEdge {

    private EfrChunkEdge() {}

    public static boolean onChunkBorder(int x, int z) {
        int localX = x & 15;
        int localZ = z & 15;
        return localX == 0 || localX == 15 || localZ == 0 || localZ == 15;
    }
}
