package com.misterd.smallprogressions.block;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.custom.*;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.item.custom.TankBlockItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class SPBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SmallProgressions.MODID);

    // Functional blocks
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

    // Storage Barrels
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

    // Fluid Tanks
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

    // Linen Sack
    public static final DeferredBlock<Block> LINEN_SACK = registerBlock("linen_sack",
            id -> new LinenSackBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(2F, 1F)
                    .noOcclusion()
                    .noLootTable()
                    .sound(SoundType.CHERRY_LEAVES)));


    ////////////////////////////////// DataGen Blocks //////////////////////////////////////////////////////////
    /// Functional Blocks ///
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

    /// Storage Blocks ///
    public static final DeferredBlock<Block> CHARCOAL_BLOCK = registerBlock("charcoal_block",
            id -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, id))
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE_TILES)));

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

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        SPItems.ITEMS.register(name, id -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
