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

public enum EnergyReceiverClientProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        long transferRate = data.getLongOr("transferRate", 0);
        NumberFormat fmt = NumberFormat.getNumberInstance(Locale.US);
        tooltip.add(Component.literal(fmt.format(transferRate) + " RF/t").withStyle(ChatFormatting.YELLOW));
    }

    @Override
    public Identifier getUid() { return EnergyReceiverProvider.UID; }
}