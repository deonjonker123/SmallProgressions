package com.misterd.smallprogressions.compat.jade;

import com.misterd.smallprogressions.blockentity.custom.EnergyTransmitterBlockEntity;
import com.misterd.smallprogressions.network.WirelessNetwork;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IServerDataProvider;

import java.util.UUID;

public enum EnergyTransmitterProvider implements IServerDataProvider<BlockAccessor> {
    INSTANCE;

    static final Identifier UID = Identifier.fromNamespaceAndPath("smallprogressions", "energy_transmitter_info");

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof EnergyTransmitterBlockEntity transmitter && accessor.getLevel() instanceof ServerLevel serverLevel) {
            UUID ownerUUID = transmitter.getOwnerUUID();
            long poolStored = ownerUUID != null ? WirelessNetwork.get(serverLevel).getPool(ownerUUID) : 0L;
            data.putLong("poolStored", poolStored);
            data.putLong("maxPool", WirelessNetwork.MAX_POOL);
            data.putBoolean("isPublic", transmitter.isPublic());
            data.putBoolean("chargeInventory", transmitter.isChargeInventory());
        }
    }

    @Override
    public Identifier getUid() { return UID; }
}