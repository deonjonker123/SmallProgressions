package com.misterd.smallprogressions.compat.jade;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum WirelessRedstoneReceiverClientProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        int channel = data.getIntOr("channel", -1);

        if (channel == -1) {
            tooltip.add(Component.translatable("jade.smallprogressions.no_channel").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.translatable("jade.smallprogressions.channel", channel).withStyle(ChatFormatting.YELLOW));
        }
    }

    @Override
    public Identifier getUid() {
        return WirelessRedstoneReceiverProvider.UID;
    }
}