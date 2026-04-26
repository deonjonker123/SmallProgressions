package com.misterd.smallprogressions.compat.jade;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.text.NumberFormat;
import java.util.Locale;

public enum SolarPanelClientProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        long rfPerTick = data.getLongOr("rfPerTick", 0);
        boolean powered = data.getBooleanOr("powered", false);

        NumberFormat fmt = NumberFormat.getNumberInstance(Locale.US);
        tooltip.add(Component.literal(fmt.format(rfPerTick) + " RF/t").withStyle(ChatFormatting.YELLOW));
        tooltip.add(powered
                ? Component.literal("Generating").withStyle(ChatFormatting.GREEN)
                : Component.literal("Not Generating").withStyle(ChatFormatting.RED));
    }

    @Override
    public Identifier getUid() { return SolarPanelProvider.UID; }
}