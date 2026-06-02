package com.misterd.smallprogressions.item.custom;

import com.misterd.smallprogressions.component.SPDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.function.Consumer;

public class MagnetItem extends Item implements ICurioItem {

    public MagnetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            toggle(player, stack);
        }
        return InteractionResult.SUCCESS;
    }

    public static void toggle(Player player, ItemStack stack) {
        boolean current = stack.getOrDefault(SPDataComponents.MAGNET_ACTIVE.get(), false);
        boolean next = !current;
        stack.set(SPDataComponents.MAGNET_ACTIVE.get(), next);
        player.sendOverlayMessage(
                Component.translatable("item.smallprogressions.magnet").withStyle(ChatFormatting.GOLD)
                        .append(Component.literal(": ").withStyle(ChatFormatting.GOLD))
                        .append(Component.translatable(next ? "item.smallprogressions.magnet.on" : "item.smallprogressions.magnet.off")
                                .withStyle(next ? ChatFormatting.GREEN : ChatFormatting.RED))
        );
        player.level().playSound(null, player.blockPosition(), next ? SoundEvents.NOTE_BLOCK_BELL.value() : SoundEvents.NOTE_BLOCK_BELL.value(), SoundSource.PLAYERS, 1.0F, next ? 1.5F : 0.8F);
    }

    public static boolean isActive(ItemStack stack) {
        return stack.getOrDefault(SPDataComponents.MAGNET_ACTIVE.get(), false);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.getOrDefault(SPDataComponents.MAGNET_ACTIVE.get(), false);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
        boolean active = isActive(stack);
        adder.accept(
                Component.translatable("item.smallprogressions.magnet").withStyle(ChatFormatting.GOLD)
                        .append(Component.literal(": ").withStyle(ChatFormatting.GOLD))
                        .append(Component.translatable(active ? "item.smallprogressions.magnet.on" : "item.smallprogressions.magnet.off")
                                .withStyle(active ? ChatFormatting.GREEN : ChatFormatting.RED))
        );
    }
}