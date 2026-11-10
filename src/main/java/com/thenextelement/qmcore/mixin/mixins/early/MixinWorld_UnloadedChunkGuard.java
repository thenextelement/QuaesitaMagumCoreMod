package com.thenextelement.qmcore.mixin.mixins.early;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.thenextelement.qmcore.common.utils.UnloadedChunkGuard;

/**
 * Applies {@link UnloadedChunkGuard} to the world accessors a guarded generator uses. Every hook is a no-op unless a
 * guarded generator is running, so vanilla and other mods' world access is unchanged.
 */
@Mixin(value = World.class, remap = false)
public abstract class MixinWorld_UnloadedChunkGuard {

    @Inject(method = "func_147439_a", at = @At("HEAD"), cancellable = true, remap = false)
    private void qmcore$getBlock(int x, int y, int z, CallbackInfoReturnable<Block> cir) {
        if (UnloadedChunkGuard.isUnloadedAccess((World) (Object) this, x, z)) cir.setReturnValue(Blocks.air);
    }

    @Inject(method = "func_147437_c", at = @At("HEAD"), cancellable = true, remap = false)
    private void qmcore$isAirBlock(int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (UnloadedChunkGuard.isUnloadedAccess((World) (Object) this, x, z)) cir.setReturnValue(true);
    }

    @Inject(method = "func_72805_g", at = @At("HEAD"), cancellable = true, remap = false)
    private void qmcore$getMetadata(int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
        if (UnloadedChunkGuard.isUnloadedAccess((World) (Object) this, x, z)) cir.setReturnValue(0);
    }

    @Inject(method = "func_147438_o", at = @At("HEAD"), cancellable = true, remap = false)
    private void qmcore$getTileEntity(int x, int y, int z, CallbackInfoReturnable<TileEntity> cir) {
        if (UnloadedChunkGuard.isUnloadedAccess((World) (Object) this, x, z)) cir.setReturnValue(null);
    }

    @Inject(method = "func_147449_b", at = @At("HEAD"), cancellable = true, remap = false)
    private void qmcore$setBlock(int x, int y, int z, Block block, CallbackInfoReturnable<Boolean> cir) {
        if (UnloadedChunkGuard.isUnloadedAccess((World) (Object) this, x, z)) cir.setReturnValue(false);
    }

    @Inject(method = "func_147465_d", at = @At("HEAD"), cancellable = true, remap = false)
    private void qmcore$setBlockMeta(int x, int y, int z, Block block, int meta, int flags,
        CallbackInfoReturnable<Boolean> cir) {
        if (UnloadedChunkGuard.isUnloadedAccess((World) (Object) this, x, z)) cir.setReturnValue(false);
    }
}
