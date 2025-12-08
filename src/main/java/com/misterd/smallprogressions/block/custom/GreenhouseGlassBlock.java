package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class GreenhouseGlassBlock extends Block {
    private static final int TICK_RATE = 20;

    public GreenhouseGlassBlock(Properties properties) {
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

        if (!isInDirectSunlight(level, pos)) {
            return;
        }

        float growthBoost = (float) Config.getGreenhouseGlassGrowthBoost();
        int height = Config.getGreenhouseGlassRange();

        for (int h = 1; h <= height; h++) {
            BlockPos checkPos = pos.below(h);
            BlockState checkState = level.getBlockState(checkPos);

            if (checkState.getBlock() instanceof CropBlock) {
                int guaranteedTicks = (int) growthBoost;
                float remainingChance = growthBoost - guaranteedTicks;

                for (int i = 0; i < guaranteedTicks; i++) {
                    checkState.randomTick(level, checkPos, random);
                }

                if (remainingChance > 0 && random.nextFloat() < remainingChance) {
                    checkState.randomTick(level, checkPos, random);
                }
            }
        }
    }

    private boolean isInDirectSunlight(ServerLevel level, BlockPos pos) {
        if (!canBlockSeeSky(level, pos)) {
            return false;
        }

        if (!level.isDay()) {
            return false;
        }

        if (level.isThundering()) {
            return false;
        }

        int skyBrightness = level.getBrightness(LightLayer.SKY, pos);
        return skyBrightness >= 8;
    }

    private boolean canBlockSeeSky(Level level, BlockPos pos) {
        if (level.canSeeSky(pos)) {
            return true;
        }

        for (BlockPos checkPos = pos.above(); checkPos.getY() < level.getMaxBuildHeight(); checkPos = checkPos.above()) {
            if (level.isOutsideBuildHeight(checkPos.getY())) {
                continue;
            }

            BlockState blockState = level.getBlockState(checkPos);
            int opacity = blockState.getLightBlock(level, checkPos);

            if (opacity > 0 && !blockState.liquid()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        double growthBoostPercent = Config.getGreenhouseGlassGrowthBoost() * 100.0;
        int height = Config.getGreenhouseGlassRange();

        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.greenhouse_glass.line1",
                String.format("%.0f%%", growthBoostPercent)).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.greenhouse_glass.line2",
                height).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}