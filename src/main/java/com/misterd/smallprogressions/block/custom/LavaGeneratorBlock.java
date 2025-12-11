package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.blockentity.custom.LavaGeneratorBlockEntity;
import com.misterd.smallprogressions.config.Config;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LavaGeneratorBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
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
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return ItemInteractionResult.SUCCESS;
        }

        if (level.getBlockEntity(pos) instanceof LavaGeneratorBlockEntity generatorEntity) {
            if (stack.isEmpty()) {
                int current = generatorEntity.getFluidAmount();
                int max = generatorEntity.getMaxCapacity();
                int genRate = Config.getLavaGeneratorMbPerTick();

                player.displayClientMessage(
                        Component.literal(String.format("Lava: %,d / %,d mB (%d mB/tick)", current, max, genRate))
                                .withStyle(ChatFormatting.GOLD),
                        true
                );
                return ItemInteractionResult.SUCCESS;
            }

            if (stack.is(Items.BUCKET)) {
                if (generatorEntity.tank.getFluidAmount() >= 1000) {
                    FluidStack drained = generatorEntity.tank.drain(1000, IFluidHandler.FluidAction.EXECUTE);
                    if (!drained.isEmpty()) {
                        if (!player.isCreative()) {
                            stack.shrink(1);
                            ItemStack lavaBucket = new ItemStack(Items.LAVA_BUCKET);
                            if (!player.getInventory().add(lavaBucket)) {
                                player.drop(lavaBucket, false);
                            }
                        }
                        level.playSound(null, pos, SoundEvents.BUCKET_FILL_LAVA, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return ItemInteractionResult.SUCCESS;
                    }
                } else {
                    player.displayClientMessage(
                            Component.literal("Not enough lava! (Need 1,000 mB)")
                                    .withStyle(ChatFormatting.RED),
                            true
                    );
                    return ItemInteractionResult.FAIL;
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, SPBlockEntities.LAVA_GENERATOR_BE.get(), LavaGeneratorBlockEntity::tick);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.lava_generator.line1").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.lava_generator.line2").withStyle(ChatFormatting.GOLD));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}