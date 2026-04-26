package com.misterd.smallprogressions.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class HardenedBatteryBlock extends BatteryBlock {

    public HardenedBatteryBlock(Properties properties) {
        super(8_000_000, 4_096, properties);
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
        adder.accept(Component.translatable("tooltip.smallprogressions.hardened_battery.subtitle").withStyle(ChatFormatting.GOLD));
    }
}