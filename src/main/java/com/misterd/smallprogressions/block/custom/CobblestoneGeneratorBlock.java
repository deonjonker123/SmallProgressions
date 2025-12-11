package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.blockentity.custom.CobblestoneGeneratorBlockEntity;
import com.misterd.smallprogressions.config.Config;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CobblestoneGeneratorBlock extends BaseEntityBlock {
    private final int tier;

    public static final MapCodec<CobblestoneGeneratorBlock> CODEC = simpleCodec(CobblestoneGeneratorBlock::new);

    public CobblestoneGeneratorBlock(Properties properties, int tier) {
        super(properties);
        this.tier = tier;
    }

    public CobblestoneGeneratorBlock(Properties properties) {
        this(properties, 1);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CobblestoneGeneratorBlockEntity(pos, state, tier);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            if (level.getBlockEntity(pos) instanceof CobblestoneGeneratorBlockEntity genEntity) {
                genEntity.drops();
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return ItemInteractionResult.SUCCESS;
        }

        if (level.getBlockEntity(pos) instanceof CobblestoneGeneratorBlockEntity genEntity) {
            if (stack.isEmpty()) {
                ItemStack buffer = genEntity.inventory.getStackInSlot(0);
                if (!buffer.isEmpty()) {
                    if (!player.getInventory().add(buffer.copy())) {
                        player.drop(buffer.copy(), false);
                    }
                    genEntity.inventory.setStackInSlot(0, ItemStack.EMPTY);
                    return ItemInteractionResult.SUCCESS;
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

        return createTickerHelper(blockEntityType,
                SPBlockEntities.COBBLESTONE_GENERATOR_BE.get(),
                (level1, pos, state1, blockEntity) -> blockEntity.tick(level1, pos, state1));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        int ticks = switch (tier) {
            case 1 -> Config.getCobblestoneGenTier1Ticks();
            case 2 -> Config.getCobblestoneGenTier2Ticks();
            case 3 -> Config.getCobblestoneGenTier3Ticks();
            case 4 -> Config.getCobblestoneGenTier4Ticks();
            case 5 -> Config.getCobblestoneGenTier5Ticks();
            default -> 40;
        };

        double seconds = ticks / 20.0;
        String timeStr = seconds >= 1 ? String.format("%.1f seconds", seconds) : String.format("%d ticks", ticks);

        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.cobblestone_generator.line1", timeStr).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.cobblestone_generator.line2").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.cobblestone_generator.line3").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}