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

public enum EnergyTransmitterClientProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        long poolStored = data.getLongOr("poolStored", 0);
        long maxPool = data.getLongOr("maxPool", 0);
        boolean isPublic = data.getBooleanOr("isPublic", false);
        boolean chargeInventory = data.getBooleanOr("chargeInventory", false);

        NumberFormat fmt = NumberFormat.getNumberInstance(Locale.US);
        double pct = maxPool > 0 ? (double) poolStored * 100.0D / (double) maxPool : 0.0D;

        tooltip.add(Component.literal(fmt.format(poolStored) + " / " + fmt.format(maxPool) + " RF").withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.literal(String.format("%.1f%%", pct)).withStyle(ChatFormatting.GRAY));
        tooltip.add(isPublic
                ? Component.translatable("gui.smallprogressions.transmitter_toggle_public").withStyle(ChatFormatting.GREEN)
                : Component.translatable("gui.smallprogressions.transmitter_toggle_private").withStyle(ChatFormatting.RED));
        tooltip.add(chargeInventory
                ? Component.translatable("jade.smallprogressions.transmitter_charging_on").withStyle(ChatFormatting.AQUA)
                : Component.translatable("jade.smallprogressions.transmitter_charging_off").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Identifier getUid() { return EnergyTransmitterProvider.UID; }
}