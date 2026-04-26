package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.blockentity.custom.AdvancedItemCollectorBlockEntity;
import com.misterd.smallprogressions.gui.custom.AdvancedItemCollectorMenu;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AdvancedItemCollectorBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(3, 1, 3, 13, 3, 13),
            Block.box(2, 3, 2, 14, 4, 14),

            Block.box(4, 4, 3.5, 12, 11, 4),
            Block.box(4, 4, 12, 12, 11, 12.5),
            Block.box(3.5, 4, 4, 4, 11, 12),
            Block.box(12, 4, 4, 12.5, 11, 12),
            Block.box(4, 11, 4, 12, 11.5, 12),

            Block.box(3, 4, 3, 4, 12, 4),
            Block.box(12, 4, 3, 13, 12, 4),
            Block.box(3, 4, 12, 4, 12, 13),
            Block.box(12, 4, 12, 13, 12, 13),
            Block.box(4, 11, 3, 12, 12, 4),
            Block.box(4, 11, 12, 12, 12, 13),
            Block.box(3, 11, 4, 4, 12, 12),
            Block.box(12, 11, 4, 13, 12, 12)
    );
    public static final MapCodec<AdvancedItemCollectorBlock> CODEC = simpleCodec(AdvancedItemCollectorBlock::new);

    public AdvancedItemCollectorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AdvancedItemCollectorBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) return null;

        return createTickerHelper(blockEntityType,
                SPBlockEntities.ADVANCED_ITEM_COLLECTOR_BE.get(),
                (level1, pos, state1, blockEntity) -> blockEntity.tick());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AdvancedItemCollectorBlockEntity advancedCollector) {
                serverPlayer.openMenu(new SimpleMenuProvider(
                        (containerId, playerInventory, p) ->
                                new AdvancedItemCollectorMenu(
                                        containerId,
                                        playerInventory,
                                        advancedCollector
                                ),
                        Component.translatable("gui.smallprogressions.advanced_item_collector")
                ), pos);
            }
        }
        return InteractionResult.SUCCESS;
    }
}