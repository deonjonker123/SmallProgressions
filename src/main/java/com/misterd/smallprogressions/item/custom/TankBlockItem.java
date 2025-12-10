package com.misterd.smallprogressions.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.List;

public class TankBlockItem extends BlockItem {
    private final int maxCapacity;

    public TankBlockItem(Block block, Properties properties, int maxCapacity) {
        super(block, properties);
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (stack.has(DataComponents.CUSTOM_DATA)) {
            CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();

            if (tag.contains("Tank")) {
                CompoundTag tankTag = tag.getCompound("Tank");

                if (tankTag.contains("Fluid")) {
                    CompoundTag fluidTag = tankTag.getCompound("Fluid");

                    FluidStack fluidStack = FluidStack.parseOptional(context.registries(), fluidTag);

                    if (!fluidStack.isEmpty()) {
                        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.tank.contains")
                                .append(fluidStack.getHoverName())
                                .append(": ")
                                .append(String.format("%,dmB", fluidStack.getAmount(), maxCapacity))
                                .withStyle(ChatFormatting.RED));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.tank.empty")
                                .withStyle(ChatFormatting.RED));
                    }
                } else {
                    tooltipComponents.add(Component.translatable("tooltip.smallprogressions.tank.empty")
                            .withStyle(ChatFormatting.RED));
                }
            } else {
                tooltipComponents.add(Component.translatable("tooltip.smallprogressions.tank.empty")
                        .withStyle(ChatFormatting.RED));
            }
        } else {
            tooltipComponents.add(Component.translatable("tooltip.smallprogressions.tank.empty")
                    .withStyle(ChatFormatting.RED));
        }
    }
}