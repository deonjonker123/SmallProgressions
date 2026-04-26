package com.misterd.smallprogressions.compat.jade;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum TimerClientProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        int interval = data.getIntOr("interval", 0);
        boolean running = data.getBooleanOr("running", false);

        tooltip.add(Component.translatable("jade.smallprogressions.timer.interval", interval).withStyle(ChatFormatting.YELLOW));
        tooltip.add(running
                ? Component.translatable("jade.smallprogressions.timer.running").withStyle(ChatFormatting.GREEN)
                : Component.translatable("jade.smallprogressions.timer.stopped").withStyle(ChatFormatting.RED));
    }

    @Override
    public Identifier getUid() {
        return TimerProvider.UID;
    }
}