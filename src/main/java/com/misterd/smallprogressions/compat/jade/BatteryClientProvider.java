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

public enum BatteryClientProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        long energyStored = data.getLongOr("energyStored", 0);
        long capacity = data.getLongOr("capacity", 0);
        long transferRate = data.getLongOr("transferRate", 0);

        NumberFormat fmt = NumberFormat.getNumberInstance(Locale.US);
        double pct = capacity > 0 ? (double) energyStored * 100.0D / (double) capacity : 0.0D;

        tooltip.add(Component.literal(fmt.format(energyStored) + " / " + fmt.format(capacity) + " RF").withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.literal(String.format("%.1f%%", pct)).withStyle(ChatFormatting.GREEN));
        tooltip.add(Component.literal(fmt.format(transferRate) + " RF/t transfer rate").withStyle(ChatFormatting.AQUA));
    }

    @Override
    public Identifier getUid() { return BatteryProvider.UID; }
}