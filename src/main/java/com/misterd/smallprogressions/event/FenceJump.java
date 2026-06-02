package com.misterd.smallprogressions.event;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FenceJump {
    public static void onJump(LivingEntity entity) {
        if (entity instanceof LocalPlayer player && player.input.keyPresses.jump() && isNextToFence(player)) {
            player.setDeltaMovement(player.getDeltaMovement().add(0.0D, 0.05D, 0.0D));
        }
    }

    private static boolean isNextToFence(Player player) {
        BlockPos pos = player.blockPosition();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockState state = player.level().getBlockState(pos.offset(x, 0, z));
                Block block = state.getBlock();
                if (block instanceof FenceBlock || block instanceof WallBlock || block instanceof FenceGateBlock
                        || state.is(BlockTags.FENCES) || state.is(BlockTags.WALLS) || state.is(BlockTags.FENCE_GATES)) {
                    return true;
                }
            }
        }
        return false;
    }
}
