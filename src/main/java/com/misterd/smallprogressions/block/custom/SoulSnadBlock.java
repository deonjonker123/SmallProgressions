package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.config.Config;
import com.misterd.smallprogressions.util.SnadTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TriState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.SoulSandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SoulSnadBlock extends SoulSandBlock {

    public SoulSnadBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
        if (plant.is(SnadTags.SNAD_GROWABLES) || plant.is(SnadTags.SOUL_SNAD_GROWABLES)) {
            return TriState.TRUE;
        }
        return TriState.DEFAULT;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (entity instanceof LivingEntity) {
            Vec3 movement = entity.getDeltaMovement();
            entity.setDeltaMovement(
                    movement.x * Config.getSoulSnadMovementSpeed(),
                    movement.y,
                    movement.z * Config.getSoulSnadMovementSpeed()
            );
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        tick(state, level, pos, random);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        var blockAbove = level.getBlockState(pos.above());
        if (blockAbove.is(SnadTags.SOUL_SNAD_GROWABLES) || blockAbove.is(SnadTags.SNAD_GROWABLES)) {
            for (int u = 0; u < Config.getAdditionalGrowthTicks(); u++) {
                blockAbove.randomTick(level, pos.above(), random);
            }
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
        scheduledTickAccess.scheduleTick(currentPos, this, 2);
        return super.updateShape(state, level, scheduledTickAccess, currentPos, facing, facingPos, facingState, random);
    }
}