package com.misterd.smallprogressions.compat.jade;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum WirelessRedstoneTransmitterClientProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        int channel = data.getIntOr("channel", -1);
        int receivers = data.getIntOr("receivers", 0);

        if (channel == -1) {
            tooltip.add(Component.translatable("jade.smallprogressions.no_channel").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.translatable("jade.smallprogressions.channel", channel).withStyle(ChatFormatting.YELLOW));
            tooltip.add(Component.translatable("jade.smallprogressions.connected_receivers", receivers).withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public Identifier getUid() {
        return WirelessRedstoneTransmitterProvider.UID;
    }
}