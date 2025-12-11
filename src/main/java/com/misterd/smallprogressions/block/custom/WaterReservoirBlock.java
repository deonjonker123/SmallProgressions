package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.blockentity.custom.WaterReservoirBlockEntity;
import com.misterd.smallprogressions.config.Config;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WaterReservoirBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<WaterReservoirBlock> CODEC = simpleCodec(WaterReservoirBlock::new);

    public WaterReservoirBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WaterReservoirBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return super.getFluidState(state);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return ItemInteractionResult.SUCCESS;
        }

        if (level.getBlockEntity(pos) instanceof WaterReservoirBlockEntity reservoir) {
            if (stack.isEmpty()) {
                if (Config.isWaterReservoirInfinite()) {
                    player.displayClientMessage(
                            Component.literal("Infinite Water Source")
                                    .withStyle(ChatFormatting.AQUA),
                            true
                    );
                } else {
                    int current = reservoir.getWaterAmount();
                    int max = reservoir.getMaxCapacity();
                    int buckets = current / 1000;
                    int maxBuckets = max / 1000;

                    player.displayClientMessage(
                            Component.literal(String.format("Water: %d / %d Buckets", buckets, maxBuckets))
                                    .withStyle(ChatFormatting.AQUA),
                            true
                    );
                }
                return ItemInteractionResult.SUCCESS;
            }

            if (stack.is(Items.BUCKET)) {
                if (Config.isWaterReservoirInfinite()) {
                    if (!player.isCreative()) {
                        stack.shrink(1);
                        ItemStack waterBucket = new ItemStack(Items.WATER_BUCKET);
                        if (!player.getInventory().add(waterBucket)) {
                            player.drop(waterBucket, false);
                        }
                    }
                    level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return ItemInteractionResult.SUCCESS;
                } else {
                    if (reservoir.canFillBucket()) {
                        reservoir.fillBucket();
                        if (!player.isCreative()) {
                            stack.shrink(1);
                            ItemStack waterBucket = new ItemStack(Items.WATER_BUCKET);
                            if (!player.getInventory().add(waterBucket)) {
                                player.drop(waterBucket, false);
                            }
                        }
                        level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return ItemInteractionResult.SUCCESS;
                    } else {
                        player.displayClientMessage(Component.translatable("message.smallprogressions.water_reservoir.empty").withStyle(ChatFormatting.RED), true);
                        return ItemInteractionResult.FAIL;
                    }
                }
            } else if (stack.is(Items.WATER_BUCKET)) {
                if (Config.isWaterReservoirInfinite()) {
                    player.displayClientMessage(Component.translatable("message.smallprogressions.water_reservoir.already_infinite").withStyle(ChatFormatting.YELLOW), true);
                    return ItemInteractionResult.FAIL;
                } else {
                    if (reservoir.canDrainBucket()) {
                        reservoir.drainBucket();
                        if (!player.isCreative()) {
                            stack.shrink(1);
                            ItemStack emptyBucket = new ItemStack(Items.BUCKET);
                            if (!player.getInventory().add(emptyBucket)) {
                                player.drop(emptyBucket, false);
                            }
                        }
                        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return ItemInteractionResult.SUCCESS;
                    } else {
                        player.displayClientMessage(Component.translatable("message.smallprogressions.water_reservoir.full").withStyle(ChatFormatting.RED), true);
                        return ItemInteractionResult.FAIL;
                    }
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Config.isWaterReservoirInfinite()) {
            tooltipComponents.add(Component.translatable("tooltip.smallprogressions.water_reservoir.line1_infinite").withStyle(ChatFormatting.AQUA));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.smallprogressions.water_reservoir.line1_tank").withStyle(ChatFormatting.AQUA));
            tooltipComponents.add(Component.translatable("tooltip.smallprogressions.water_reservoir.line2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}