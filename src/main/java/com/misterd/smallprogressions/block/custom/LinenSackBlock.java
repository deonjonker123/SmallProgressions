package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.blockentity.custom.LinenSackBlockEntity;
import com.misterd.smallprogressions.gui.custom.LinenSackMenu;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LinenSackBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    public static final MapCodec<LinenSackBlock> CODEC = simpleCodec(LinenSackBlock::new);

    public LinenSackBlock(Properties properties) {
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
        return new LinenSackBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (level.getBlockEntity(pos) instanceof LinenSackBlockEntity sack) {
            sack.loadFromItem(stack);
        }
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> drops = new ArrayList<>();

        BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof LinenSackBlockEntity sack) {
            ItemStack stack = new ItemStack(this);
            sack.saveToItem(stack);
            drops.add(stack);
        } else {
            drops.add(new ItemStack(this));
        }

        return drops;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof LinenSackBlockEntity sack) {
                serverPlayer.openMenu(new SimpleMenuProvider(
                        (containerId, playerInventory, p) -> new LinenSackMenu(containerId, playerInventory, sack), Component.translatable("gui.smallprogressions.linen_sack")
                ), pos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}