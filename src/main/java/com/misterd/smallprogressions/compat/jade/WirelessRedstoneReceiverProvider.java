package com.misterd.smallprogressions.compat.jade;

import com.misterd.smallprogressions.blockentity.custom.WirelessRedstoneReceiverBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IServerDataProvider;

public enum WirelessRedstoneReceiverProvider implements IServerDataProvider<BlockAccessor> {
    INSTANCE;

    static final Identifier UID = Identifier.fromNamespaceAndPath("smallprogressions", "wireless_redstone_receiver_info");

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof WirelessRedstoneReceiverBlockEntity receiver) {
            data.putInt("channel", receiver.getChannel());
        }
    }

    @Override
    public Identifier getUid() {
        return UID;
    }
}