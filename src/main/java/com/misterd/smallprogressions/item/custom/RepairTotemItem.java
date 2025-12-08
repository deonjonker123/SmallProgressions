package com.misterd.smallprogressions.item.custom;

import com.misterd.smallprogressions.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class RepairTotemItem extends Item {

    public RepairTotemItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        int durabilityPerTotem = Config.getRepairTotemDurabilityPerTotem();
        int tickInterval = Config.getRepairTotemTickInterval();
        double secondsInterval = tickInterval / 20.0;

        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.repair_totem.line1")
                .withStyle(ChatFormatting.AQUA));

        if (secondsInterval >= 1.0) {
            String intervalStr;
            if (secondsInterval % 1.0 == 0) {
                int seconds = (int)secondsInterval;
                String secondsKey = seconds != 1
                        ? "tooltip.smallprogressions.repair_totem.seconds"
                        : "tooltip.smallprogressions.repair_totem.second";
                intervalStr = seconds + " " + Component.translatable(secondsKey).getString();
            } else {
                intervalStr = String.format("%.1f ", secondsInterval) +
                        Component.translatable("tooltip.smallprogressions.repair_totem.seconds").getString();
            }

            tooltipComponents.add(Component.translatable("tooltip.smallprogressions.repair_totem.line2.seconds",
                            durabilityPerTotem, intervalStr)
                    .withStyle(ChatFormatting.GRAY));
        } else {
            String tickSuffix = tickInterval != 1 ? "s" : "";
            tooltipComponents.add(Component.translatable("tooltip.smallprogressions.repair_totem.line2.ticks",
                            durabilityPerTotem, tickInterval, tickSuffix)
                    .withStyle(ChatFormatting.GRAY));
        }

        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.repair_totem.line3")
                .withStyle(ChatFormatting.GOLD));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}