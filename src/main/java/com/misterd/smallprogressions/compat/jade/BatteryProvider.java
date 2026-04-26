package com.misterd.smallprogressions.compat.jade;

import com.misterd.smallprogressions.blockentity.custom.BatteryBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IServerDataProvider;

public enum BatteryProvider implements IServerDataProvider<BlockAccessor> {
    INSTANCE;

    static final Identifier UID = Identifier.fromNamespaceAndPath("smallprogressions", "battery_info");

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof BatteryBlockEntity battery) {
            data.putLong("energyStored", battery.getEnergyStoredLong());
            data.putLong("capacity", battery.getCapacity());
            data.putLong("transferRate", battery.getTransferRate());
        }
    }

    @Override
    public Identifier getUid() { return UID; }
}