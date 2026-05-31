package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.blockentity.custom.WirelessRedstoneTransmitterBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class WirelessRedstoneTransmitterBlock extends BaseEntityBlock {
    public static final MapCodec<WirelessRedstoneTransmitterBlock> CODEC = simpleCodec(WirelessRedstoneTransmitterBlock::new);
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    private static final VoxelShape SHAPE_UP = Block.box(3,  0, 3, 13,  3, 13);
    private static final VoxelShape SHAPE_DOWN = Block.box(3, 13, 3, 13, 16, 13);
    private static final VoxelShape SHAPE_NORTH = Block.box(3,  3, 13, 13, 13, 16);
    private static final VoxelShape SHAPE_SOUTH = Block.box(3,  3, 0, 13, 13,  3);
    private static final VoxelShape SHAPE_EAST = Block.box(0,  3, 3,  3, 13, 13);
    private static final VoxelShape SHAPE_WEST = Block.box(13, 3, 3, 16, 13, 13);

    public WirelessRedstoneTransmitterBlock(Properties props) {
        super(props);
        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(POWERED, false));
    }

    @Override
    public MapCodec<WirelessRedstoneTransmitterBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction facing = ctx.getClickedFace();
        return defaultBlockState()
                .setValue(FACING, facing)
                .setValue(POWERED, false);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
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
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer instanceof Player player && level.getBlockEntity(pos) instanceof WirelessRedstoneTransmitterBlockEntity be) {
            be.setOwnerUUID(player.getUUID());
            be.registerWithChannelManager();
        }
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess,
                                  BlockPos pos, Direction direction, BlockPos neighborPos,
                                  BlockState neighborState, RandomSource random) {
        Direction facing = state.getValue(FACING);
        Direction attachedTo = facing.getOpposite(); // the wall behind the plate
        if (direction == attachedTo) {
            if (!neighborState.isFaceSturdy(level, neighborPos, facing)) {
                return Blocks.AIR.defaultBlockState();
            }
        }
        if (!level.isClientSide()) {
            tickAccess.scheduleTick(pos, this, 1);
        }
        return super.updateShape(state, level, tickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        updatePoweredState(level, pos, state);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (level.isClientSide()) return;
        updatePoweredState(level, pos, state);
    }

    private void updatePoweredState(Level level, BlockPos pos, BlockState state) {
        boolean powered = isReceivingRedstone(level, pos);
        boolean wasPowered = state.getValue(POWERED);
        if (powered != wasPowered) {
            level.setBlock(pos, state.setValue(POWERED, powered), Block.UPDATE_ALL);
            if (level.getBlockEntity(pos) instanceof WirelessRedstoneTransmitterBlockEntity be) {
                be.onPoweredChanged(powered);
            }
        }
    }

    private boolean isReceivingRedstone(Level level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            if (level.getSignal(pos.relative(dir), dir) > 0) return true;
        }
        return false;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof WirelessRedstoneTransmitterBlockEntity transmitter) {
                player.openMenu(transmitter, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WirelessRedstoneTransmitterBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}