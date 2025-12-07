package com.misterd.smallprogressions.block.custom;

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
    private final int tier;
    private final int rangeHorizontal;
    private final int rangeVertical;
    private final float growthChance;

    public GrowthCrystalBlock(Properties properties, int tier) {
        super(properties);
        this.tier = tier;

        switch (tier) {
            case 1:
                this.rangeHorizontal = 4;
                this.rangeVertical = 1;
                this.growthChance = 0.25f;
                break;
            case 2:
                this.rangeHorizontal = 4;
                this.rangeVertical = 1;
                this.growthChance = 0.50f;
                break;
            case 3:
                this.rangeHorizontal = 4;
                this.rangeVertical = 1;
                this.growthChance = 1.00f;
                break;
            default:
                this.rangeHorizontal = 4;
                this.rangeVertical = 1;
                this.growthChance = 0.25f;
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);

        for (int x = -rangeHorizontal; x <= rangeHorizontal; x++) {
            for (int y = -rangeVertical; y <= rangeVertical; y++) {
                for (int z = -rangeHorizontal; z <= rangeHorizontal; z++) {
                    BlockPos checkPos = pos.offset(x, y, z);
                    BlockState checkState = level.getBlockState(checkPos);

                    if (checkState.getBlock() instanceof CropBlock cropBlock) {
                        if (random.nextFloat() < growthChance) {
                            checkState.tick(level, checkPos, random);
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
        switch (tier) {
            case 1:
                tooltipComponents.add(Component.translatable("tooltip.smallprogressions.growth_crystal_tier_1.line1").withStyle(ChatFormatting.AQUA));
                tooltipComponents.add(Component.translatable("tooltip.smallprogressions.growth_crystal_tier_1.line2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
                break;
            case 2:
                tooltipComponents.add(Component.translatable("tooltip.smallprogressions.growth_crystal_tier_2.line1").withStyle(ChatFormatting.AQUA));
                tooltipComponents.add(Component.translatable("tooltip.smallprogressions.growth_crystal_tier_2.line2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
                break;
            case 3:
                tooltipComponents.add(Component.translatable("tooltip.smallprogressions.growth_crystal_tier_3.line1").withStyle(ChatFormatting.AQUA));
                tooltipComponents.add(Component.translatable("tooltip.smallprogressions.growth_crystal_tier_3.line2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
                break;
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}