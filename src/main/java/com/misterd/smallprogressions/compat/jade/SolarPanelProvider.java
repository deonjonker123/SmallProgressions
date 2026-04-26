package com.misterd.smallprogressions.compat.jade;

import com.misterd.smallprogressions.block.custom.SolarPanelBlock;
import com.misterd.smallprogressions.blockentity.custom.SolarPanelBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IServerDataProvider;

public enum SolarPanelProvider implements IServerDataProvider<BlockAccessor> {
    INSTANCE;

    static final Identifier UID = Identifier.fromNamespaceAndPath("smallprogressions", "solar_panel_info");

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof SolarPanelBlockEntity) {
            data.putLong("rfPerTick", ((SolarPanelBlock) accessor.getBlockState().getBlock()).getRfPerTick());
            data.putBoolean("powered", accessor.getBlockState().getValue(SolarPanelBlock.POWERED));
        }
    }

    @Override
    public Identifier getUid() { return UID; }
}