package com.misterd.smallprogressions.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Consumer;

public class BasicBatteryBlock extends BatteryBlock {

    public BasicBatteryBlock(BlockBehaviour.Properties properties) {
        super(2_000_000, 1_024, properties);
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
        adder.accept(Component.translatable("tooltip.smallprogressions.basic_battery.subtitle").withStyle(ChatFormatting.GOLD));
    }
}