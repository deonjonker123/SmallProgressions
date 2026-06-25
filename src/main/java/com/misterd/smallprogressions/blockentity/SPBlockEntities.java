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
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SPBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SmallProgressions.MODID);

    public static final Supplier<BlockEntityType<BrickFurnaceBlockEntity>> BRICK_FURNACE_BE =
            BLOCK_ENTITIES.register("brick_furnace_be", () -> new BlockEntityType<>(
                    BrickFurnaceBlockEntity::new, SPBlocks.BRICK_FURNACE.get()));

    public static final Supplier<BlockEntityType<CopperBarrelBlockEntity>> COPPER_BARREL_BE =
            BLOCK_ENTITIES.register("copper_barrel_be", () -> new BlockEntityType<>(
                    CopperBarrelBlockEntity::new, SPBlocks.COPPER_BARREL.get()));

    public static final Supplier<BlockEntityType<IronBarrelBlockEntity>> IRON_BARREL_BE =
            BLOCK_ENTITIES.register("iron_barrel_be", () -> new BlockEntityType<>(
                    IronBarrelBlockEntity::new, SPBlocks.IRON_BARREL.get()));

    public static final Supplier<BlockEntityType<GoldBarrelBlockEntity>> GOLD_BARREL_BE =
            BLOCK_ENTITIES.register("gold_barrel_be", () -> new BlockEntityType<>(
                    GoldBarrelBlockEntity::new, SPBlocks.GOLD_BARREL.get()));

    public static final Supplier<BlockEntityType<DiamondBarrelBlockEntity>> DIAMOND_BARREL_BE =
            BLOCK_ENTITIES.register("diamond_barrel_be", () -> new BlockEntityType<>(
                    DiamondBarrelBlockEntity::new, SPBlocks.DIAMOND_BARREL.get()));

    public static final Supplier<BlockEntityType<SimpleItemCollectorBlockEntity>> SIMPLE_ITEM_COLLECTOR_BE =
            BLOCK_ENTITIES.register("simple_item_collector_be", () -> new BlockEntityType<>(
                    SimpleItemCollectorBlockEntity::new, SPBlocks.SIMPLE_ITEM_COLLECTOR.get()));

    public static final Supplier<BlockEntityType<AdvancedItemCollectorBlockEntity>> ADVANCED_ITEM_COLLECTOR_BE =
            BLOCK_ENTITIES.register("advanced_item_collector_be", () -> new BlockEntityType<>(
                    AdvancedItemCollectorBlockEntity::new, SPBlocks.ADVANCED_ITEM_COLLECTOR.get()));

    public static final Supplier<BlockEntityType<CobblestoneGeneratorBlockEntity>> COBBLESTONE_GENERATOR_BE =
            BLOCK_ENTITIES.register("cobblestone_generator_be", () -> new BlockEntityType<>(
                    CobblestoneGeneratorBlockEntity::new,
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get(),
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_2.get(),
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get(),
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get(),
                    SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get()
            ));

    public static final Supplier<BlockEntityType<LinenSackBlockEntity>> LINEN_SACK_BE =
            BLOCK_ENTITIES.register("linen_sack_be", () -> new BlockEntityType<>(
                    LinenSackBlockEntity::new, SPBlocks.LINEN_SACK.get()));

    public static final Supplier<BlockEntityType<WaterReservoirBlockEntity>> WATER_RESERVOIR_BE =
            BLOCK_ENTITIES.register("water_reservoir_be", () -> new BlockEntityType<>(
                    WaterReservoirBlockEntity::new, SPBlocks.WATER_RESERVOIR.get()));

    public static final Supplier<BlockEntityType<CopperTankBlockEntity>> COPPER_TANK_BE =
            BLOCK_ENTITIES.register("copper_tank_be", () -> new BlockEntityType<>(
                    CopperTankBlockEntity::new, SPBlocks.COPPER_TANK.get()));

    public static final Supplier<BlockEntityType<IronTankBlockEntity>> IRON_TANK_BE =
            BLOCK_ENTITIES.register("iron_tank_be", () -> new BlockEntityType<>(
                    IronTankBlockEntity::new, SPBlocks.IRON_TANK.get()));

    public static final Supplier<BlockEntityType<GoldTankBlockEntity>> GOLD_TANK_BE =
            BLOCK_ENTITIES.register("gold_tank_be", () -> new BlockEntityType<>(
                    GoldTankBlockEntity::new, SPBlocks.GOLD_TANK.get()));

    public static final Supplier<BlockEntityType<DiamondTankBlockEntity>> DIAMOND_TANK_BE =
            BLOCK_ENTITIES.register("diamond_tank_be", () -> new BlockEntityType<>(
                    DiamondTankBlockEntity::new, SPBlocks.DIAMOND_TANK.get()));

    public static final Supplier<BlockEntityType<LavaGeneratorBlockEntity>> LAVA_GENERATOR_BE =
            BLOCK_ENTITIES.register("lava_generator_be", () -> new BlockEntityType<>(
                    LavaGeneratorBlockEntity::new, SPBlocks.LAVA_GENERATOR.get()));

    public static final Supplier<BlockEntityType<HarvesterBlockEntity>> HARVESTER_BE =
            BLOCK_ENTITIES.register("harvester_be", () -> new BlockEntityType<>(
                    HarvesterBlockEntity::new, SPBlocks.HARVESTER.get()));

    public static final Supplier<BlockEntityType<WirelessRedstoneTransmitterBlockEntity>> WIRELESS_REDSTONE_TRANSMITTER_BE =
            BLOCK_ENTITIES.register("wireless_redstone_transmitter_be", () -> new BlockEntityType<>(
                    WirelessRedstoneTransmitterBlockEntity::new, SPBlocks.WIRELESS_REDSTONE_TRANSMITTER.get()));

    public static final Supplier<BlockEntityType<WirelessRedstoneReceiverBlockEntity>> WIRELESS_REDSTONE_RECEIVER_BE =
            BLOCK_ENTITIES.register("wireless_redstone_receiver_be", () -> new BlockEntityType<>(
                    WirelessRedstoneReceiverBlockEntity::new, SPBlocks.WIRELESS_REDSTONE_RECEIVER.get()));

    public static final Supplier<BlockEntityType<TimerBlockEntity>> TIMER_BE =
            BLOCK_ENTITIES.register("timer_be", () -> new BlockEntityType<>(
                    TimerBlockEntity::new, SPBlocks.TIMER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_BE =
            BLOCK_ENTITIES.register("solar_panel_be", () -> new BlockEntityType<>(
                    SolarPanelBlockEntity::new,
                    SPBlocks.BASIC_SOLAR_PANEL.get(),
                    SPBlocks.HARDENED_SOLAR_PANEL.get(),
                    SPBlocks.ADVANCED_SOLAR_PANEL.get(),
                    SPBlocks.ELITE_SOLAR_PANEL.get(),
                    SPBlocks.ULTIMATE_SOLAR_PANEL.get()
            ));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BatteryBlockEntity>> BATTERY_BE =
            BLOCK_ENTITIES.register("battery_be", () -> new BlockEntityType<>(
                    BatteryBlockEntity::new,
                    SPBlocks.BASIC_BATTERY.get(),
                    SPBlocks.HARDENED_BATTERY.get(),
                    SPBlocks.ADVANCED_BATTERY.get(),
                    SPBlocks.ELITE_BATTERY.get(),
                    SPBlocks.ULTIMATE_BATTERY.get()
            ));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnergyTransmitterBlockEntity>> ENERGY_TRANSMITTER_BE =
            BLOCK_ENTITIES.register("energy_transmitter_be", () -> new BlockEntityType<>(
                    EnergyTransmitterBlockEntity::new,
                    SPBlocks.ENERGY_TRANSMITTER.get()
            ));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnergyReceiverBlockEntity>> ENERGY_RECEIVER_BE =
            BLOCK_ENTITIES.register("energy_receiver_be", () -> new BlockEntityType<>(
                    EnergyReceiverBlockEntity::new,
                    SPBlocks.BASIC_ENERGY_RECEIVER.get(),
                    SPBlocks.HARDENED_ENERGY_RECEIVER.get(),
                    SPBlocks.ADVANCED_ENERGY_RECEIVER.get(),
                    SPBlocks.ELITE_ENERGY_RECEIVER.get(),
                    SPBlocks.ULTIMATE_ENERGY_RECEIVER.get()
            ));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LogisticsSenderBlockEntity>> LOGISTICS_SENDER_BE =
            BLOCK_ENTITIES.register("logistics_sender_be", () -> new BlockEntityType<>(
                    LogisticsSenderBlockEntity::new, SPBlocks.LOGISTICS_SENDER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LogisticsReceiverBlockEntity>> LOGISTICS_RECEIVER_BE =
            BLOCK_ENTITIES.register("logistics_receiver_be", () -> new BlockEntityType<>(
                    LogisticsReceiverBlockEntity::new, SPBlocks.LOGISTICS_RECEIVER.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LanternBracketBlockEntity>> LANTERN_BRACKET_BE =
            BLOCK_ENTITIES.register("lantern_bracket_be", () -> new BlockEntityType<>(
                    LanternBracketBlockEntity::new, SPBlocks.LANTERN_BRACKET.get()));

    private static void registerCapabilities(RegisterCapabilitiesEvent event) {

        event.registerBlockEntity(Capabilities.Item.BLOCK, BRICK_FURNACE_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof BrickFurnaceBlockEntity brickFurnaceBlockEntity) {
                        return brickFurnaceBlockEntity.getItemHandler(direction);
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Item.BLOCK, COPPER_BARREL_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof CopperBarrelBlockEntity copperBarrelBlockEntity) {
                        return copperBarrelBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Item.BLOCK, IRON_BARREL_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof IronBarrelBlockEntity ironBarrelBlockEntity) {
                        return ironBarrelBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Item.BLOCK, GOLD_BARREL_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof GoldBarrelBlockEntity goldBarrelBlockEntity) {
                        return goldBarrelBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Item.BLOCK, DIAMOND_BARREL_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof DiamondBarrelBlockEntity diamondBarrelBlockEntity) {
                        return diamondBarrelBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Item.BLOCK, SIMPLE_ITEM_COLLECTOR_BE.get(),
                (blockEntity, direction) -> {
                    if (direction == Direction.DOWN && blockEntity instanceof SimpleItemCollectorBlockEntity simpleItemCollectorBlockEntity) {
                        return simpleItemCollectorBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Item.BLOCK, ADVANCED_ITEM_COLLECTOR_BE.get(),
                (blockEntity, direction) -> {
                    if (direction == Direction.DOWN && blockEntity instanceof AdvancedItemCollectorBlockEntity advancedItemCollectorBlockEntity) {
                        return advancedItemCollectorBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Item.BLOCK, COBBLESTONE_GENERATOR_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof CobblestoneGeneratorBlockEntity cobblestoneGeneratorBlockEntity) {
                        return cobblestoneGeneratorBlockEntity.inventory;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Fluid.BLOCK, WATER_RESERVOIR_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof WaterReservoirBlockEntity waterReservoir) {
                        return waterReservoir.getFluidHandler();
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Fluid.BLOCK, COPPER_TANK_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof CopperTankBlockEntity copperTank) {
                        return copperTank.tank;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Fluid.BLOCK, IRON_TANK_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof IronTankBlockEntity ironTank) {
                        return ironTank.tank;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Fluid.BLOCK, GOLD_TANK_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof GoldTankBlockEntity goldTank) {
                        return goldTank.tank;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Fluid.BLOCK, DIAMOND_TANK_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof DiamondTankBlockEntity diamondTank) {
                        return diamondTank.tank;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Fluid.BLOCK, LAVA_GENERATOR_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof LavaGeneratorBlockEntity lavaGenerator) {
                        return lavaGenerator.tank;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Energy.BLOCK, BATTERY_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof BatteryBlockEntity battery) {
                        return battery.energyHandler;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Energy.BLOCK, ENERGY_TRANSMITTER_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof EnergyTransmitterBlockEntity transmitter) {
                        return transmitter.energyHandler;
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Energy.BLOCK, SOLAR_PANEL_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof SolarPanelBlockEntity solar && direction == Direction.DOWN) {
                        return solar.getEnergyHandler();
                    }
                    return null;
                });

        event.registerBlockEntity(Capabilities.Item.BLOCK, LOGISTICS_SENDER_BE.get(),
                (blockEntity, direction) -> {
                    if (blockEntity instanceof LogisticsSenderBlockEntity sender) {
                        return sender.upgradeInventory;
                    }
                    return null;
                });
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
        eventBus.addListener(SPBlockEntities::registerCapabilities);
    }
}