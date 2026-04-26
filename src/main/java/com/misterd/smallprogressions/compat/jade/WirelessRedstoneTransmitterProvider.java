package com.misterd.smallprogressions.compat.jade;

import com.misterd.smallprogressions.blockentity.custom.WirelessRedstoneTransmitterBlockEntity;
import com.misterd.smallprogressions.network.ChannelManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IServerDataProvider;

public enum WirelessRedstoneTransmitterProvider implements IServerDataProvider<BlockAccessor> {
    INSTANCE;

    static final Identifier UID = Identifier.fromNamespaceAndPath("smallprogressions", "wireless_redstone_transmitter_info");

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof WirelessRedstoneTransmitterBlockEntity transmitter
                && accessor.getLevel() instanceof ServerLevel serverLevel) {
            int channel = transmitter.getChannel();
            data.putInt("channel", channel);

            int receiverCount = 0;
            if (channel != -1 && transmitter.getOwnerUUID() != null) {
                ChannelManager mgr = ChannelManager.get(serverLevel);
                var byOwner = mgr.getReceivers().get(transmitter.getOwnerUUID());
                if (byOwner != null) {
                    var positions = byOwner.get(channel);
                    if (positions != null) receiverCount = positions.size();
                }
            }
            data.putInt("receivers", receiverCount);
        }
    }

    @Override
    public Identifier getUid() {
        return UID;
    }
}