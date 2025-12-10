package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class GrowthCrystalBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final int TICK_RATE = 20;
    private final int tier;
    private final int rangeHorizontal;

    public GrowthCrystalBlock(Properties properties, int tier) {
        super(properties);
        this.tier = tier;
        this.rangeHorizontal = 4;
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                     LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
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
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        String key = "tooltip.smallprogressions.growth_crystal_tier_" + tier;
        tooltipComponents.add(Component.translatable(key + ".line1").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Component.translatable(key + ".line2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}