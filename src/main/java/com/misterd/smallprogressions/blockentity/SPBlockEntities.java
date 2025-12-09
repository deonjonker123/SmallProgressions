package com.misterd.smallprogressions.blockentity;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
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

    public static final Supplier<BlockEntityType<SimpleItemCollectorBlockEntity>> SIMPLE_ITEM_COLLECTOR_BE =
            BLOCK_ENTITIES.register("simple_item_collector_be", () -> BlockEntityType.Builder.of(
                    SimpleItemCollectorBlockEntity::new, SPBlocks.SIMPLE_ITEM_COLLECTOR.get()).build(null));

    public static final Supplier<BlockEntityType<AdvancedItemCollectorBlockEntity>> ADVANCED_ITEM_COLLECTOR_BE =
            BLOCK_ENTITIES.register("advanced_item_collector_be", () -> BlockEntityType.Builder.of(
                    AdvancedItemCollectorBlockEntity::new, SPBlocks.ADVANCED_ITEM_COLLECTOR.get()).build(null));

    public static final Supplier<BlockEntityType<CobblestoneGeneratorBlockEntity>> COBBLESTONE_GENERATOR_BE =
            BLOCK_ENTITIES.register("cobblestone_generator_be", () -> BlockEntityType.Builder.of(
                    CobblestoneGeneratorBlockEntity::new,
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get(),
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_2.get(),
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get(),
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get(),
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get()
            ).build(null));

    public static final Supplier<BlockEntityType<LinenSackBlockEntity>> LINEN_SACK_BE =
            BLOCK_ENTITIES.register("linen_sack_be", () -> BlockEntityType.Builder.of(
                    LinenSackBlockEntity::new, SPBlocks.LINEN_SACK.get()).build(null));

    public static final Supplier<BlockEntityType<WaterReservoirBlockEntity>> WATER_RESERVOIR_BE =
            BLOCK_ENTITIES.register("water_reservoir_be", () -> BlockEntityType.Builder.of(
                    WaterReservoirBlockEntity::new, SPBlocks.WATER_RESERVOIR.get()).build(null));

    public static final Supplier<BlockEntityType<CopperTankBlockEntity>> COPPER_TANK_BE =
            BLOCK_ENTITIES.register("copper_tank_be", () -> BlockEntityType.Builder.of(
                    CopperTankBlockEntity::new, SPBlocks.COPPER_TANK.get()).build(null));

    public static final Supplier<BlockEntityType<IronTankBlockEntity>> IRON_TANK_BE =
            BLOCK_ENTITIES.register("iron_tank_be", () -> BlockEntityType.Builder.of(
                    IronTankBlockEntity::new, SPBlocks.IRON_TANK.get()).build(null));

    public static final Supplier<BlockEntityType<GoldTankBlockEntity>> GOLD_TANK_BE =
            BLOCK_ENTITIES.register("gold_tank_be", () -> BlockEntityType.Builder.of(
                    GoldTankBlockEntity::new, SPBlocks.GOLD_TANK.get()).build(null));

    public static final Supplier<BlockEntityType<DiamondTankBlockEntity>> DIAMOND_TANK_BE =
            BLOCK_ENTITIES.register("diamond_tank_be", () -> BlockEntityType.Builder.of(
                    DiamondTankBlockEntity::new, SPBlocks.DIAMOND_TANK.get()).build(null));

    public static final Supplier<BlockEntityType<LavaGeneratorBlockEntity>> LAVA_GENERATOR_BE =
            BLOCK_ENTITIES.register("lava_generator_be", () -> BlockEntityType.Builder.of(
                    LavaGeneratorBlockEntity::new, SPBlocks.LAVA_GENERATOR.get()).build(null));

    public static final Supplier<BlockEntityType<HarvesterBlockEntity>> HARVESTER_BE =
            BLOCK_ENTITIES.register("harvester_be", () -> BlockEntityType.Builder.of(
                    HarvesterBlockEntity::new, SPBlocks.HARVESTER.get()).build(null));

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

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SIMPLE_ITEM_COLLECTOR_BE.get(),
                (blockEntity, direction) -> {
                    if (direction == Direction.DOWN && blockEntity instanceof SimpleItemCollectorBlockEntity simpleItemCollectorBlockEntity) {
                        return simpleItemCollectorBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ADVANCED_ITEM_COLLECTOR_BE.get(),
                (blockEntity, direction) -> {
                    if (direction == Direction.DOWN && blockEntity instanceof AdvancedItemCollectorBlockEntity advancedItemCollectorBlockEntity) {
                        return advancedItemCollectorBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COBBLESTONE_GENERATOR_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof CobblestoneGeneratorBlockEntity cobblestoneGeneratorBlockEntity) {
                        return cobblestoneGeneratorBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, WATER_RESERVOIR_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof WaterReservoirBlockEntity waterReservoir) {
                        return waterReservoir.getFluidHandler();
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, COPPER_TANK_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof CopperTankBlockEntity copperTank) {
                        return copperTank.tank;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, IRON_TANK_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof IronTankBlockEntity ironTank) {
                        return ironTank.tank;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, GOLD_TANK_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof GoldTankBlockEntity goldTank) {
                        return goldTank.tank;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, DIAMOND_TANK_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof DiamondTankBlockEntity diamondTank) {
                        return diamondTank.tank;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, LAVA_GENERATOR_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof LavaGeneratorBlockEntity lavaGenerator) {
                        return lavaGenerator.tank;
                    }
                    return null;
                });
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
        eventBus.addListener(SPBlockEntities::registerCapabilities);
    }
}