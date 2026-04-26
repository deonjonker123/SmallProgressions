package com.misterd.smallprogressions.item.custom;

import com.misterd.smallprogressions.block.custom.LogisticsReceiverBlock;
import com.misterd.smallprogressions.block.custom.LogisticsSenderBlock;
import com.misterd.smallprogressions.blockentity.custom.LogisticsReceiverBlockEntity;
import com.misterd.smallprogressions.blockentity.custom.LogisticsSenderBlockEntity;
import com.misterd.smallprogressions.component.SPDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class ConnectionWrench extends Item {

    public ConnectionWrench(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        Player player = ctx.getPlayer();
        ItemStack stack = ctx.getItemInHand();
        if (player == null) return InteractionResult.PASS;

        if (level.isClientSide()) return InteractionResult.SUCCESS;

        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof LogisticsSenderBlock) {
            stack.set(SPDataComponents.WRENCH_SENDER_POS.get(), pos);
            player.sendOverlayMessage(Component.translatable("item.smallprogressions.connection_wrench.sender_stored",
                    pos.getX(), pos.getY(), pos.getZ()).withStyle(ChatFormatting.AQUA));
            return InteractionResult.SUCCESS;
        }

        if (state.getBlock() instanceof LogisticsReceiverBlock) {
            BlockPos senderPos = stack.get(SPDataComponents.WRENCH_SENDER_POS.get());
            if (senderPos == null) {
                player.sendOverlayMessage(Component.translatable("item.smallprogressions.connection_wrench.no_sender")
                        .withStyle(ChatFormatting.RED));
                return InteractionResult.FAIL;
            }

            if (!(level.getBlockEntity(senderPos) instanceof LogisticsSenderBlockEntity sender)) {
                player.sendOverlayMessage(Component.translatable("item.smallprogressions.connection_wrench.sender_missing")
                        .withStyle(ChatFormatting.RED));
                stack.remove(SPDataComponents.WRENCH_SENDER_POS.get());
                return InteractionResult.FAIL;
            }

            if (!(level.getBlockEntity(pos) instanceof LogisticsReceiverBlockEntity receiver)) {
                return InteractionResult.FAIL;
            }

            if (sender.getConnectedReceivers().contains(pos)) {
                sender.removeReceiver(pos);
                receiver.removeSender(senderPos);
                player.sendOverlayMessage(Component.translatable("item.smallprogressions.connection_wrench.disconnected")
                        .withStyle(ChatFormatting.GOLD));
            } else {
                if (!sender.addReceiver(pos)) {
                    player.sendOverlayMessage(Component.translatable("item.smallprogressions.connection_wrench.failed")
                            .withStyle(ChatFormatting.RED));
                    return InteractionResult.FAIL;
                }
                receiver.addSender(senderPos);
                player.sendOverlayMessage(Component.translatable("item.smallprogressions.connection_wrench.connected")
                        .withStyle(ChatFormatting.GREEN));
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            if (!level.isClientSide()) {
                stack.remove(SPDataComponents.WRENCH_SENDER_POS.get());
                player.sendOverlayMessage(Component.translatable("item.smallprogressions.connection_wrench.cleared")
                        .withStyle(ChatFormatting.GOLD));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
        BlockPos stored = stack.get(SPDataComponents.WRENCH_SENDER_POS.get());
        if (stored != null) {
            adder.accept(Component.translatable("item.smallprogressions.connection_wrench.stored",
                    stored.getX(), stored.getY(), stored.getZ()).withStyle(ChatFormatting.AQUA));
        } else {
            adder.accept(Component.translatable("item.smallprogressions.connection_wrench.empty")
                    .withStyle(ChatFormatting.GRAY));
        }
        adder.accept(Component.translatable("item.smallprogressions.connection_wrench.hint")
                .withStyle(ChatFormatting.DARK_GRAY));
    }
}