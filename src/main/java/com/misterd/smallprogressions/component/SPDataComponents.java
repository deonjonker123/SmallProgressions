package com.misterd.smallprogressions.component;

import com.misterd.smallprogressions.SmallProgressions;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SPDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, SmallProgressions.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Long>> ENERGY_STORED =
            DATA_COMPONENTS.register("energy_stored", () -> DataComponentType.<Long>builder()
                    .persistent(Codec.LONG)
                    .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> WRENCH_SENDER_POS =
            DATA_COMPONENTS.register("wrench_sender_pos", () -> DataComponentType.<BlockPos>builder()
                    .persistent(BlockPos.CODEC)
                    .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> MAGNET_ACTIVE =
            DATA_COMPONENTS.register("magnet_active", () -> DataComponentType.<Boolean>builder()
                    .persistent(Codec.BOOL)
                    .build());

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}