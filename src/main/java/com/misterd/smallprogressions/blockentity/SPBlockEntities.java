package com.misterd.smallprogressions.blockentity;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SPBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SmallProgressions.MODID);

    public static final Supplier<BlockEntityType<BrickFurnaceBlockEntity>> BRICK_FURNACE_BE =
            BLOCK_ENTITIES.register("brick_furnace_be", () -> BlockEntityType.Builder.of(
                    BrickFurnaceBlockEntity::new, SPBlocks.BRICK_FURNACE.get()).build(null));

    public static final Supplier<BlockEntityType<CopperBarrelBlockEntity>> COPPER_BARREL_BE =
            BLOCK_ENTITIES.register("copper_barrel_be", () -> BlockEntityType.Builder.of(
                    CopperBarrelBlockEntity::new, SPBlocks.COPPER_BARREL.get()).build(null));

    public static final Supplier<BlockEntityType<IronBarrelBlockEntity>> IRON_BARREL_BE =
            BLOCK_ENTITIES.register("iron_barrel_be", () -> BlockEntityType.Builder.of(
                    IronBarrelBlockEntity::new, SPBlocks.IRON_BARREL.get()).build(null));

    public static final Supplier<BlockEntityType<GoldBarrelBlockEntity>> GOLD_BARREL_BE =
            BLOCK_ENTITIES.register("gold_barrel_be", () -> BlockEntityType.Builder.of(
                    GoldBarrelBlockEntity::new, SPBlocks.GOLD_BARREL.get()).build(null));

    public static final Supplier<BlockEntityType<DiamondBarrelBlockEntity>> DIAMOND_BARREL_BE =
            BLOCK_ENTITIES.register("diamond_barrel_be", () -> BlockEntityType.Builder.of(
                    DiamondBarrelBlockEntity::new, SPBlocks.DIAMOND_BARREL.get()).build(null));

    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BRICK_FURNACE_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof BrickFurnaceBlockEntity brickFurnaceBlockEntity) {
                        return brickFurnaceBlockEntity.getCapabilityHandler();
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COPPER_BARREL_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof CopperBarrelBlockEntity copperBarrelBlockEntity) {
                        return copperBarrelBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, IRON_BARREL_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof IronBarrelBlockEntity ironBarrelBlockEntity) {
                        return ironBarrelBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GOLD_BARREL_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof GoldBarrelBlockEntity goldBarrelBlockEntity) {
                        return goldBarrelBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DIAMOND_BARREL_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof DiamondBarrelBlockEntity diamondBarrelBlockEntity) {
                        return diamondBarrelBlockEntity.inventory;
                    }
                    return null;
                });
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
        eventBus.addListener(SPBlockEntities::registerCapabilities);
    }
}
