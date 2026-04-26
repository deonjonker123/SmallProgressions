package com.misterd.smallprogressions.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class BasicSolarPanelBlock extends SolarPanelBlock {

    public BasicSolarPanelBlock(Properties properties) {
        super(128, properties);
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
        adder.accept(Component.translatable("tooltip.smallprogressions.basic_solar_panel.subtitle").withStyle(ChatFormatting.GOLD));
    }
}