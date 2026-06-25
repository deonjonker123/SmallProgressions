package com.misterd.smallprogressions.block;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.custom.*;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.item.custom.BatteryBlockItem;
import com.misterd.smallprogressions.item.custom.TankBlockItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class SPBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SmallProgressions.MODID);

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_1 = registerBlock("cobblestone_generator_tier_1",
            id -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 1));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_2 = registerBlock("cobblestone_generator_tier_2",
            id -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 2));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_3 = registerBlock("cobblestone_generator_tier_3",
            id -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 3));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_4 = registerBlock("cobblestone_generator_tier_4",
            id -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 4));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_5 = registerBlock("cobblestone_generator_tier_5",
            id -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 5));

    public static final DeferredBlock<Block> SIMPLE_ITEM_COLLECTOR = registerBlock("simple_item_collector",
            id -> new SimpleItemCollectorBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ADVANCED_ITEM_COLLECTOR = registerBlock("advanced_item_collector",
            id -> new AdvancedItemCollectorBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BRICK_FURNACE = registerBlock("brick_furnace",
            id -> new BrickFurnaceBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LAVA_GENERATOR = registerBlock("lava_generator",
            id -> new LavaGeneratorBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> WATER_RESERVOIR = registerBlock("water_reservoir",
            id -> new WaterReservoirBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> HARVESTER = registerBlock("harvester",
            id -> new HarvesterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> COPPER_BARREL = registerBlock("copper_barrel",
            id -> new CopperBarrelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> IRON_BARREL = registerBlock("iron_barrel",
            id -> new IronBarrelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> GOLD_BARREL = registerBlock("gold_barrel",
            id -> new GoldBarrelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> DIAMOND_BARREL = registerBlock("diamond_barrel",
            id -> new DiamondBarrelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> COPPER_TANK = BLOCKS.register("copper_tank",
            id -> new CopperTankBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> IRON_TANK = BLOCKS.register("iron_tank",
            id -> new IronTankBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> GOLD_TANK = BLOCKS.register("gold_tank",
            id -> new GoldTankBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DIAMOND_TANK = BLOCKS.register("diamond_tank",
            id -> new DiamondTankBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LINEN_SACK = registerBlock("linen_sack",
            id -> new LinenSackBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 1F)
                    .noOcclusion()
                    .noLootTable()
                    .sound(SoundType.CHERRY_LEAVES)));

    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_1 = registerBlock("growth_crystal_tier_1",
            id -> new GrowthCrystalBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(state -> 15)
                    .sound(SoundType.STONE), 1));

    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_2 = registerBlock("growth_crystal_tier_2",
            id -> new GrowthCrystalBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(state -> 15)
                    .sound(SoundType.STONE), 2));

    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_3 = registerBlock("growth_crystal_tier_3",
            id -> new GrowthCrystalBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(state -> 15)
                    .sound(SoundType.STONE), 3));

    public static final DeferredBlock<Block> GREENHOUSE_GLASS  = registerBlock("greenhouse_glass",
            id -> new GreenhouseGlassBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LAVA_INFUSED_STONE = registerBlock("lava_infused_stone",
            id -> new LavaInfusedStoneBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 2000.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MCFLOATY_BLOCK = BLOCKS.register("mcfloaty_block",
            id -> new McFloatyBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 2F)
                    .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> WIRELESS_REDSTONE_RECEIVER = registerBlock("wireless_redstone_receiver",
            id -> new WirelessRedstoneReceiverBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> WIRELESS_REDSTONE_TRANSMITTER = registerBlock("wireless_redstone_transmitter",
            id -> new WirelessRedstoneTransmitterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> TIMER = registerBlock("timer",
            id -> new TimerBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BASIC_SOLAR_PANEL = registerBlock("basic_solar_panel",
            id -> new BasicSolarPanelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> HARDENED_SOLAR_PANEL = registerBlock("hardened_solar_panel",
            id -> new HardenedSolarPanelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ADVANCED_SOLAR_PANEL = registerBlock("advanced_solar_panel",
            id -> new AdvancedSolarPanelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ELITE_SOLAR_PANEL = registerBlock("elite_solar_panel",
            id -> new EliteSolarPanelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ULTIMATE_SOLAR_PANEL = registerBlock("ultimate_solar_panel",
            id -> new UltimateSolarPanelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BASIC_BATTERY = registerBatteryBlock("basic_battery",
            id -> new BasicBatteryBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> HARDENED_BATTERY = registerBatteryBlock("hardened_battery",
            id -> new HardenedBatteryBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .noLootTable()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ADVANCED_BATTERY = registerBatteryBlock("advanced_battery",
            id -> new AdvancedBatteryBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .noLootTable()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ELITE_BATTERY = registerBatteryBlock("elite_battery",
            id -> new EliteBatteryBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .noLootTable()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ULTIMATE_BATTERY = registerBatteryBlock("ultimate_battery",
            id -> new UltimateBatteryBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .noLootTable()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BASIC_ENERGY_RECEIVER = registerBlock("basic_energy_receiver",
            id -> new BasicEnergyReceiverBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> HARDENED_ENERGY_RECEIVER = registerBlock("hardened_energy_receiver",
            id -> new HardenedEnergyReceiverBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ADVANCED_ENERGY_RECEIVER = registerBlock("advanced_energy_receiver",
            id -> new AdvancedEnergyReceiverBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ELITE_ENERGY_RECEIVER = registerBlock("elite_energy_receiver",
            id -> new EliteEnergyReceiverBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ULTIMATE_ENERGY_RECEIVER = registerBlock("ultimate_energy_receiver",
            id -> new UltimateEnergyReceiverBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ENERGY_TRANSMITTER = registerBlock("energy_transmitter",
            id -> new EnergyTransmitterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(1F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LOGISTICS_SENDER = registerBlock("logistics_sender",
            id -> new LogisticsSenderBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LOGISTICS_RECEIVER = registerBlock("logistics_receiver",
            id -> new LogisticsReceiverBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LANTERN_BRACKET = registerBlock("lantern_bracket",
            id -> new LanternBracketBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 6F)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(LanternBracketBlock.LIGHT_LEVEL))
                    .noOcclusion()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> CHARCOAL_BLOCK = registerBlock("charcoal_block",
            id -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE_TILES)));

    public static final DeferredBlock<Block> SNAD_BLOCK = registerBlock("snad",
            id -> new SnadBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)
                    .randomTicks()
                    .setId(ResourceKey.create(Registries.BLOCK, id))));

    public static final DeferredBlock<Block> RED_SNAD_BLOCK = registerBlock("red_snad",
            id -> new SnadBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)
                    .randomTicks()
                    .setId(ResourceKey.create(Registries.BLOCK, id))));

    public static final DeferredBlock<Block> SOUL_SNAD_BLOCK = registerBlock("soul_snad",
            id -> new SoulSnadBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_SAND)
                    .randomTicks()
                    .setId(ResourceKey.create(Registries.BLOCK, id))));

    static {
        SPItems.ITEMS.register("copper_tank",
                id -> new TankBlockItem(COPPER_TANK.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id)), 16000));
        SPItems.ITEMS.register("iron_tank",
                id -> new TankBlockItem(IRON_TANK.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id)), 32000));
        SPItems.ITEMS.register("gold_tank",
                id -> new TankBlockItem(GOLD_TANK.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id)), 64000));
        SPItems.ITEMS.register("diamond_tank",
                id -> new TankBlockItem(DIAMOND_TANK.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id)), 128000));
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<Identifier, T> factory) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, factory);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBatteryBlock(String name, Function<Identifier, T> factory) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, factory);
        SPItems.ITEMS.register(name, id -> new BatteryBlockItem(toReturn.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        SPItems.ITEMS.register(name, id -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
