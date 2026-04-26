package com.misterd.smallprogressions.compat.jade;

import com.misterd.smallprogressions.blockentity.custom.TimerBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IServerDataProvider;

public enum TimerProvider implements IServerDataProvider<BlockAccessor> {
    INSTANCE;

    static final Identifier UID = Identifier.fromNamespaceAndPath("smallprogressions", "timer_info");

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof TimerBlockEntity timer) {
            data.putInt("interval", timer.getInterval());
            data.putBoolean("running", timer.isRunning());
        }
    }

    @Override
    public Identifier getUid() {
        return UID;
    }
}