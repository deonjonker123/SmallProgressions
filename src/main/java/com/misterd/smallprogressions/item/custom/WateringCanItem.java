package com.misterd.smallprogressions.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class WateringCanItem extends Item {
    private final int radius;

    public WateringCanItem(Properties properties, int radius) {
        super(properties);
        this.radius = radius;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.watering_can.area", radius + "x" + radius).withStyle(ChatFormatting.AQUA));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);

            if (hitResult.getType() != HitResult.Type.BLOCK) {
                return InteractionResultHolder.fail(stack);
            }

            BlockPos centerPos = hitResult.getBlockPos();
            int watered = 0;

            int range = (radius - 1) / 2;

            for (int x = -range; x <= range; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -range; z <= range; z++) {
                        BlockPos pos = centerPos.offset(x, y, z);
                        BlockState state = level.getBlockState(pos);
                        Block block = state.getBlock();

                        if (level instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(
                                    ParticleTypes.SPLASH,
                                    pos.getX() + 0.5,
                                    pos.getY() + 1.0,
                                    pos.getZ() + 0.5,
                                    5,
                                    0.3, 0.3, 0.3,
                                    0.1
                            );
                        }

                        if (block instanceof CropBlock cropBlock) {
                            int currentAge = state.getValue(BlockStateProperties.AGE_7);
                            int maxAge = 7;

                            if (currentAge < maxAge && level.random.nextFloat() < 0.25F) {
                                level.setBlock(pos, state.setValue(BlockStateProperties.AGE_7, currentAge + 1), 2);
                                watered++;
                            }
                        }
                        else if (block instanceof SaplingBlock saplingBlock) {
                            if (level.random.nextFloat() < 0.05F) {
                                if (level instanceof ServerLevel serverLevel) {
                                    saplingBlock.advanceTree(serverLevel, pos, state, serverLevel.random);
                                    watered++;
                                }
                            }
                        }
                    }
                }
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}