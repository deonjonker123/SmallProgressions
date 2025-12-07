package com.misterd.smallprogressions.blockentity;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.BrickFurnaceBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SPBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SmallProgressions.MODID);

    public static final Supplier<BlockEntityType<BrickFurnaceBlockEntity>> BRICK_FURNACE_BE =
            BLOCK_ENTITIES.register("brick_furnace_be", () -> BlockEntityType.Builder.of(
                    BrickFurnaceBlockEntity::new, SPBlocks.BRICK_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
