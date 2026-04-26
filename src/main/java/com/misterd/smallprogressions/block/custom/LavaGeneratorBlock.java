package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.blockentity.custom.LavaGeneratorBlockEntity;
import com.misterd.smallprogressions.config.Config;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LavaGeneratorBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<LavaGeneratorBlock> CODEC = simpleCodec(LavaGeneratorBlock::new);

    public LavaGeneratorBlock(Properties properties) {
        super(properties);
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
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LavaGeneratorBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (level.getBlockEntity(pos) instanceof LavaGeneratorBlockEntity generatorEntity) {
            if (stack.isEmpty()) {
                int current = generatorEntity.getFluidAmount();
                int max = generatorEntity.getMaxCapacity();
                int genRate = Config.getLavaGeneratorMbPerTick();

                player.sendOverlayMessage(
                        Component.literal(String.format("Lava: %,d / %,d mB (%d mB/tick)", current, max, genRate))
                                .withStyle(ChatFormatting.GOLD)
                );
                return InteractionResult.SUCCESS;
            }

            if (stack.is(Items.BUCKET)) {
                if (generatorEntity.getFluidAmount() >= 1000) {
                    try (var tx = Transaction.openRoot()) {
                        int extracted = generatorEntity.tank.extract(0, FluidResource.of(Fluids.LAVA), 1000, tx);
                        if (extracted == 1000) {
                            tx.commit();
                            if (!player.isCreative()) {
                                stack.shrink(1);
                                ItemStack lavaBucket = new ItemStack(Items.LAVA_BUCKET);
                                if (!player.getInventory().add(lavaBucket)) {
                                    player.drop(lavaBucket, false);
                                }
                            }
                            level.playSound(null, pos, SoundEvents.BUCKET_FILL_LAVA, SoundSource.BLOCKS, 1.0F, 1.0F);
                            return InteractionResult.SUCCESS;
                        }
                    }
                } else {
                    player.sendOverlayMessage(
                            Component.literal("Not enough lava! (Need 1,000 mB)")
                                    .withStyle(ChatFormatting.RED)
                    );
                    return InteractionResult.FAIL;
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, SPBlockEntities.LAVA_GENERATOR_BE.get(),
                (level1, pos, state1, blockEntity) -> blockEntity.tick());
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.lava_generator.line1").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.lava_generator.line2").withStyle(ChatFormatting.GOLD));
    }
}