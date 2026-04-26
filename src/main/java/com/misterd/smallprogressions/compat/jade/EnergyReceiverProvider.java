package com.misterd.smallprogressions.compat.jade;

import com.misterd.smallprogressions.block.custom.EnergyReceiverBlock;
import com.misterd.smallprogressions.blockentity.custom.EnergyReceiverBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IServerDataProvider;

public enum EnergyReceiverProvider implements IServerDataProvider<BlockAccessor> {
    INSTANCE;

    static final Identifier UID = Identifier.fromNamespaceAndPath("smallprogressions", "energy_receiver_info");

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof EnergyReceiverBlockEntity) {
            data.putLong("transferRate", ((EnergyReceiverBlock) accessor.getBlockState().getBlock()).getTransferRate());
        }
    }

    @Override
    public Identifier getUid() { return UID; }
}