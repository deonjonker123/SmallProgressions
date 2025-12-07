package com.misterd.smallprogressions.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class LavaInfusedStoneBlock extends Block {
    private static final int TICK_RATE = 5;

    public LavaInfusedStoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if (!level.isClientSide) {
            level.scheduleTick(pos, this, TICK_RATE);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.scheduleTick(pos, this, TICK_RATE);
        checkForWater(level, pos);
    }

    private void checkForWater(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = pos.relative(direction);
            FluidState adjacentFluid = level.getFluidState(adjacentPos);
            BlockState adjacentState = level.getBlockState(adjacentPos);

            if (adjacentFluid.is(Fluids.WATER) || adjacentFluid.is(Fluids.FLOWING_WATER)) {
                if (adjacentState.is(Blocks.WATER)) {
                    level.setBlock(adjacentPos, Blocks.OBSIDIAN.defaultBlockState(), 3);
                } else if (adjacentFluid.is(Fluids.FLOWING_WATER)) {
                    level.setBlock(adjacentPos, Blocks.COBBLESTONE.defaultBlockState(), 3);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.lava_infused_stone.line1").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.lava_infused_stone.line2").withStyle(ChatFormatting.DARK_PURPLE));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}