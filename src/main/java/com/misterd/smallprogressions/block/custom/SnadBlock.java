package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.config.Config;
import com.misterd.smallprogressions.util.SnadTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TriState;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.GrowingPlantBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.CommonHooks;

import java.lang.reflect.Method;
import java.util.Comparator;

public class SnadBlock extends ColoredFallingBlock {

    public SnadBlock(ColorRGBA dustColor, Properties properties) {
        super(dustColor, properties);
    }

    private static Block getHeadBlock(GrowingPlantBlock block) {
        try {
            Method m = GrowingPlantBlock.class.getDeclaredMethod("getHeadBlock");
            m.setAccessible(true);
            return (Block) m.invoke(block);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get head block", e);
        }
    }

    private static Block getBodyBlock(GrowingPlantBlock block) {
        try {
            Method m = GrowingPlantBlock.class.getDeclaredMethod("getBodyBlock");
            m.setAccessible(true);
            return (Block) m.invoke(block);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get body block", e);
        }
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
        if (plant.is(SnadTags.SNAD_GROWABLES)) {
            if (plant.is(SnadTags.SNAD_GROWABLES_REQUIRES_WATER)) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockState blockstate = level.getBlockState(soilPosition.relative(direction));
                    FluidState fluidstate = level.getFluidState(soilPosition.relative(direction));
                    if (state.canBeHydrated(level, soilPosition.above(), fluidstate, soilPosition.relative(direction)) || blockstate.is(Blocks.FROSTED_ICE)) {
                        return TriState.TRUE;
                    }
                }
                return TriState.FALSE;
            }
            return TriState.TRUE;
        }
        return TriState.FALSE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        tick(state, level, pos, random);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);

        var plantBlock = level.getBlockState(pos.above());

        if (plantBlock.is(SnadTags.SNAD_GROWABLES)) {
            var headBlock = plantBlock.getBlock() instanceof GrowingPlantBlock growBlock ? getHeadBlock(growBlock) : plantBlock.getBlock();
            var bodyBlock = plantBlock.getBlock() instanceof GrowingPlantBlock growBlock ? getBodyBlock(growBlock) : plantBlock.getBlock();

            int i;
            for (i = 2; level.getBlockState(pos.above(i)).is(headBlock) || level.getBlockState(pos.above(i)).is(plantBlock.getBlock()); ++i) {}

            var topState = level.getBlockState(pos.above(i - 1));
            var ageProperty = topState.getBlock() instanceof GrowingPlantHeadBlock ? BlockStateProperties.AGE_25 : BlockStateProperties.AGE_15;
            int maxAge = ageProperty.getAllValues().max(Comparator.comparingInt(Property.Value::value)).get().value();

            if (topState.hasProperty(ageProperty) &&
                    i < 4 + Config.getSnadAdditionalHeight() &&
                    !level.isOutsideBuildHeight(pos.above(i)) &&
                    level.getBlockState(pos.above(i)).canBeReplaced()) {

                if (topState.getValue(ageProperty) == maxAge) {
                    if (CommonHooks.canCropGrow(level, pos, state, true)) {
                        level.setBlockAndUpdate(pos.above(i), headBlock.defaultBlockState());
                        var grownState = bodyBlock.defaultBlockState();
                        level.setBlock(pos.above(i - 1), grownState.hasProperty(ageProperty) ? grownState.setValue(ageProperty, 0) : grownState, Block.UPDATE_CLIENTS);
                        CommonHooks.fireCropGrowPost(level, pos.above(), plantBlock.getBlock().defaultBlockState());
                    }
                } else {
                    level.setBlock(pos.above(i - 1), topState.setValue(ageProperty, Math.min(maxAge, topState.getValue(ageProperty) + Config.getAdditionalGrowthTicks())), Block.UPDATE_INVISIBLE);
                }
            } else {
                for (int u = 0; u < Config.getAdditionalGrowthTicks(); u++) {
                    if (topState.isRandomlyTicking()) {
                        topState.randomTick(level, pos.above(i - 1), random);
                    }
                }
            }
        }
    }
}