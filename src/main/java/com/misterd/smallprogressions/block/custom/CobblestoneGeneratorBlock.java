package com.misterd.smallprogressions.block.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.blockentity.custom.CobblestoneGeneratorBlockEntity;
import com.misterd.smallprogressions.config.Config;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

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
    protected MapCodec<? extends BaseEntityBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CobblestoneGeneratorBlockEntity(pos, state, tier);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;

        if (level.getBlockEntity(pos) instanceof CobblestoneGeneratorBlockEntity gen && stack.isEmpty()) {
            ItemStack buffer = gen.getSlot0();
            if (!buffer.isEmpty()) {
                if (!player.getInventory().add(buffer.copy())) player.drop(buffer.copy(), false);
                gen.setSlot0(ItemStack.EMPTY);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, SPBlockEntities.COBBLESTONE_GENERATOR_BE.get(), (l, p, s, be) -> be.tick());
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
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
        adder.accept(Component.translatable("tooltip.smallprogressions.cobblestone_generator.line1", timeStr).withStyle(ChatFormatting.AQUA));
        adder.accept(Component.translatable("tooltip.smallprogressions.cobblestone_generator.line2").withStyle(ChatFormatting.GOLD));
        adder.accept(Component.translatable("tooltip.smallprogressions.cobblestone_generator.line3").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }
}