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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class GrowthCrystalBlock extends Block {
    private static final int TICK_RATE = 20;
    private final int tier;
    private final int rangeHorizontal;

    public GrowthCrystalBlock(Properties properties, int tier) {
        super(properties);
        this.tier = tier;
        this.rangeHorizontal = 4;
    }

    private float getGrowthChance() {
        return switch (tier) {
            case 2 -> (float) Config.getGrowthCrystalTier2Rate();
            case 3 -> (float) Config.getGrowthCrystalTier3Rate();
            default -> (float) Config.getGrowthCrystalTier1Rate();
        };
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

        float growthChance = getGrowthChance();

        for (int x = -rangeHorizontal; x <= rangeHorizontal; x++) {
            for (int z = -rangeHorizontal; z <= rangeHorizontal; z++) {
                for (int y = -1; y <= 1; y++) {
                    BlockPos checkPos = pos.offset(x, y, z);
                    BlockState checkState = level.getBlockState(checkPos);

                    if (checkState.getBlock() instanceof CropBlock) {
                        int guaranteedTicks = (int) growthChance;
                        float remainingChance = growthChance - guaranteedTicks;

                        for (int i = 0; i < guaranteedTicks; i++) {
                            checkState.randomTick(level, checkPos, random);
                        }
                        if (remainingChance > 0 && random.nextFloat() < remainingChance) {
                            checkState.randomTick(level, checkPos, random);
                        }
                    }
                }
            }
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        String key = "tooltip.smallprogressions.growth_crystal_tier_" + tier;
        tooltipComponents.add(Component.translatable(key + ".line1").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Component.translatable(key + ".line2").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Component.translatable(key + ".line3").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}