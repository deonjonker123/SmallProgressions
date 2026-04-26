package com.misterd.smallprogressions.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.function.Consumer;

public class TankBlockItem extends BlockItem {
    private final int maxCapacity;

    public TankBlockItem(Block block, Properties properties, int maxCapacity) {
        super(block, properties);
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, adder, flag);

        if (!stack.has(DataComponents.CUSTOM_DATA)) {
            adder.accept(Component.translatable("tooltip.smallprogressions.tank.empty").withStyle(ChatFormatting.RED));
            return;
        }

        CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        if (!tag.contains("Tank")) {
            adder.accept(Component.translatable("tooltip.smallprogressions.tank.empty").withStyle(ChatFormatting.RED));
            return;
        }

        var ops = context.registries().createSerializationContext(NbtOps.INSTANCE);
        FluidStack.OPTIONAL_CODEC.parse(ops, tag.get("Tank")).result()
                .ifPresentOrElse(fs -> {
                    if (!fs.isEmpty()) {
                        adder.accept(Component.translatable("tooltip.smallprogressions.tank.contains")
                                .append(fs.getHoverName())
                                .append(": ")
                                .append(String.format("%,dmB", fs.getAmount()))
                                .withStyle(ChatFormatting.RED));
                    } else {
                        adder.accept(Component.translatable("tooltip.smallprogressions.tank.empty").withStyle(ChatFormatting.RED));
                    }
                }, () -> adder.accept(Component.translatable("tooltip.smallprogressions.tank.empty").withStyle(ChatFormatting.RED)));
    }
}