package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.blockentity.custom.LogisticsSenderBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class LogisticsSenderBlock extends BaseEntityBlock {
    public static final MapCodec<LogisticsSenderBlock> CODEC = simpleCodec(LogisticsSenderBlock::new);
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

    // Base: 2-14 X/Z, 0-3 Y (relative to face)
    // Antenna base: up to Y 11, antenna tip to Y 13
    private static final VoxelShape SHAPE_UP = Shapes.or(
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(1, 1, 1, 15, 3, 15),
            Block.box(2, 3, 2, 14, 13, 14)
    );
    private static final VoxelShape SHAPE_DOWN = Shapes.or(
            Block.box(2, 15, 2, 14, 16, 14),
            Block.box(1, 13, 1, 15, 15, 15),
            Block.box(2, 3, 2, 14, 13, 14)
    );
    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(2, 2, 15, 14, 14, 16),
            Block.box(1, 1, 13, 15, 15, 15),
            Block.box(2, 2, 3, 14, 14, 13)
    );
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(2, 2, 0, 14, 14, 1),
            Block.box(1, 1, 1, 15, 15, 3),
            Block.box(2, 2, 3, 14, 14, 13)
    );
    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(0, 2, 2, 1, 14, 14),
            Block.box(1, 1, 1, 3, 15, 15),
            Block.box(3, 2, 2, 13, 14, 14)
    );
    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(15, 2, 2, 16, 14, 14),
            Block.box(13, 1, 1, 15, 15, 15),
            Block.box(3, 2, 2, 13, 14, 14)
    );

    public LogisticsSenderBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.UP));
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
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(FACING, ctx.getClickedFace());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (placer instanceof ServerPlayer player && level.getBlockEntity(pos) instanceof LogisticsSenderBlockEntity be) {
            be.setOwner(player.getUUID());
        }
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        Direction facing = state.getValue(FACING);
        if (direction == facing.getOpposite()) {
            if (!neighborState.isFaceSturdy(level, neighborPos, facing)) {
                return Blocks.AIR.defaultBlockState();
            }
        }
        return super.updateShape(state, level, tickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return switch (state.getValue(FACING)) {
            case DOWN -> SHAPE_DOWN;
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_UP;
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            if (level.getBlockEntity(pos) instanceof LogisticsSenderBlockEntity be) {
                serverPlayer.openMenu(be, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LogisticsSenderBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, SPBlockEntities.LOGISTICS_SENDER_BE.get(), (l, p, s, be) -> be.tick());
    }
}