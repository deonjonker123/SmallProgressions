package com.misterd.smallprogressions.block;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.custom.*;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SPBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SmallProgressions.MODID);

    // Functional blocks
    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_1 = registerBlock("cobblestone_generator_tier_1",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_2 = registerBlock("cobblestone_generator_tier_2",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_3 = registerBlock("cobblestone_generator_tier_3",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_4 = registerBlock("cobblestone_generator_tier_4",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_5 = registerBlock("cobblestone_generator_tier_5",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> FUEL_RESERVOIR = registerBlock("fuel_reservoir",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> SIMPLE_ITEM_COLLECTOR = registerBlock("simple_item_collector",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ADVANCED_ITEM_COLLECTOR = registerBlock("advanced_item_collector",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BRICK_FURNACE = registerBlock("brick_furnace",
            () -> new BrickFurnaceBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LAVA_GENERATOR = registerBlock("lava_generator",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> WATER_RESERVOIR = registerBlock("water_reservoir",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    // Storage Barrels
    public static final DeferredBlock<Block> COPPER_BARREL = registerBlock("copper_barrel",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> IRON_BARREL = registerBlock("iron_barrel",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> GOLD_BARREL = registerBlock("gold_barrel",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> DIAMOND_BARREL = registerBlock("diamond_barrel",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)));

    // Fluid Tanks
    public static final DeferredBlock<Block> COPPER_TANK = registerBlock("copper_tank",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> IRON_TANK = registerBlock("iron_tank",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> GOLD_TANK = registerBlock("gold_tank",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DIAMOND_TANK = registerBlock("diamond_tank",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    // Linen Sack
    public static final DeferredBlock<Block> LINEN_SACK = registerBlock("linen_sack",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 1F)
                    .noOcclusion()
                    .noLootTable()
                    .sound(SoundType.CHERRY_LEAVES)));


    ////////////////////////////////// DataGen Blocks //////////////////////////////////////////////////////////
    /// Functional Blocks ///
    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_1 = registerBlock("growth_crystal_tier_1",
            () -> new GrowthCrystalBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 1));

    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_2 = registerBlock("growth_crystal_tier_2",
            () -> new GrowthCrystalBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 2));

    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_3 = registerBlock("growth_crystal_tier_3",
            () -> new GrowthCrystalBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 3));

    public static final DeferredBlock<Block> GREENHOUSE_GLASS  = registerBlock("greenhouse_glass",
            () -> new GreenhouseGlassBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LAVA_INFUSED_STONE = registerBlock("lava_infused_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 2000.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MCFLOATY_BLOCK = registerBlock("mcfloaty_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1F, 2F)
                    .sound(SoundType.AMETHYST)));

    /// Storage Blocks ///
    public static final DeferredBlock<Block> CHARCOAL_BLOCK = registerBlock("charcoal_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE_TILES)));

    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    /// Deco Blocks ///
    /// Marble ///
    public static final DeferredBlock<Block> MARBLE = registerBlock("marble",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MARBLE_BRICKS = registerBlock("marble_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MARBLE_PILLAR = registerBlock("marble_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<StairBlock> MARBLE_STAIRS = registerBlock("marble_stairs",
            () -> new StairBlock(SPBlocks.MARBLE.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<StairBlock> MARBLE_BRICK_STAIRS = registerBlock("marble_brick_stairs",
            () -> new StairBlock(SPBlocks.MARBLE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<SlabBlock> MARBLE_SLAB = registerBlock("marble_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<SlabBlock> MARBLE_BRICK_SLAB = registerBlock("marble_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<ButtonBlock> MARBLE_BUTTON = registerBlock("marble_button",
            () -> new ButtonBlock(BlockSetType.IRON, 10, BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<PressurePlateBlock> MARBLE_PRESSURE_PLATE = registerBlock("marble_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> MARBLE_WALL = registerBlock("marble_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> MARBLE_BRICK_WALL = registerBlock("marble_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    /// Slate ///
    public static final DeferredBlock<Block> SLATE = registerBlock("slate",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> SLATE_BRICKS = registerBlock("slate_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> SLATE_PILLAR = registerBlock("slate_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<StairBlock> SLATE_STAIRS = registerBlock("slate_stairs",
            () -> new StairBlock(SPBlocks.SLATE.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<StairBlock> SLATE_BRICK_STAIRS = registerBlock("slate_brick_stairs",
            () -> new StairBlock(SPBlocks.SLATE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<SlabBlock> SLATE_SLAB = registerBlock("slate_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<SlabBlock> SLATE_BRICK_SLAB = registerBlock("slate_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<ButtonBlock> SLATE_BUTTON = registerBlock("slate_button",
            () -> new ButtonBlock(BlockSetType.IRON, 10, BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<PressurePlateBlock> SLATE_PRESSURE_PLATE = registerBlock("slate_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> SLATE_WALL = registerBlock("slate_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> SLATE_BRICK_WALL = registerBlock("slate_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    /// Thatch ///
    public static final DeferredBlock<Block> THATCH_BLOCK = registerBlock("thatch_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1F, 1F)
                    .sound(SoundType.GRASS)));

    public static final DeferredBlock<StairBlock> THATCH_STAIRS = registerBlock("thatch_stairs",
            () -> new StairBlock(SPBlocks.THATCH_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .sound(SoundType.GRASS)));


    public static final DeferredBlock<SlabBlock> THATCH_SLAB = registerBlock("thatch_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .sound(SoundType.GRASS)));

    /// Asphalt ///
    public static final DeferredBlock<Block> ASPHALT = registerBlock("asphalt",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<StairBlock> ASPHALT_STAIRS = registerBlock("asphalt_stairs",
            () -> new StairBlock(SPBlocks.ASPHALT.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<SlabBlock> ASPHALT_SLAB = registerBlock("asphalt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<ButtonBlock> ASPHALT_BUTTON = registerBlock("asphalt_button",
            () -> new ButtonBlock(BlockSetType.IRON, 10, BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<PressurePlateBlock> ASPHALT_PRESSURE_PLATE = registerBlock("asphalt_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> ASPHALT_WALL = registerBlock("asphalt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> REINFORCED_OBSIDIAN = registerBlock("reinforced_obsidian",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 2000.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> REINFORCED_GLASS = registerBlock("reinforced_glass",
            () -> new TintedGlassBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 2000.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    /// Hardened Stone ///
    public static final DeferredBlock<Block> HARDENED_STONE = registerBlock("hardened_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> HARDENED_STONE_BRICKS = registerBlock("hardened_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<StairBlock> HARDENED_STONE_STAIRS = registerBlock("hardened_stone_stairs",
            () -> new StairBlock(SPBlocks.HARDENED_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<StairBlock> HARDENED_STONE_BRICK_STAIRS = registerBlock("hardened_stone_brick_stairs",
            () -> new StairBlock(SPBlocks.HARDENED_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<SlabBlock> HARDENED_STONE_SLAB = registerBlock("hardened_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<SlabBlock> HARDENED_STONE_BRICK_SLAB = registerBlock("hardened_stone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<ButtonBlock> HARDENED_STONE_BUTTON = registerBlock("hardened_stone_button",
            () -> new ButtonBlock(BlockSetType.IRON, 10, BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<PressurePlateBlock> HARDENED_STONE_PRESSURE_PLATE = registerBlock("hardened_stone_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> HARDENED_STONE_WALL = registerBlock("hardened_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> HARDENED_STONE_BRICK_WALL = registerBlock("hardened_stone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()));

    /// Ore Blocks ///
    public static final DeferredBlock<Block> STONE_ENDER_ORE = registerBlock("stone_ender_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_ENDER_ORE = registerBlock("deepslate_ender_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> NETHERRACK_ENDER_ORE = registerBlock("netherrack_ender_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ENDSTONE_ENDER_ORE = registerBlock("endstone_ender_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    /// Glowstone Blocks ///
    public static final DeferredBlock<Block> BLACK_GLOWSTONE  = registerBlock("black_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> BLUE_GLOWSTONE  = registerBlock("blue_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> BROWN_GLOWSTONE  = registerBlock("brown_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> CYAN_GLOWSTONE  = registerBlock("cyan_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> GRAY_GLOWSTONE  = registerBlock("gray_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> GREEN_GLOWSTONE  = registerBlock("green_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LIGHT_BLUE_GLOWSTONE  = registerBlock("light_blue_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LIGHT_GRAY_GLOWSTONE  = registerBlock("light_gray_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LIME_GLOWSTONE  = registerBlock("lime_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> MAGENTA_GLOWSTONE  = registerBlock("magenta_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> ORANGE_GLOWSTONE  = registerBlock("orange_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> PINK_GLOWSTONE  = registerBlock("pink_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> PURPLE_GLOWSTONE  = registerBlock("purple_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> RED_GLOWSTONE  = registerBlock("red_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> WHITE_GLOWSTONE  = registerBlock("white_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> YELLOW_GLOWSTONE  = registerBlock("yellow_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)));

    /// Glowstone Glass Blocks ///
    public static final DeferredBlock<Block> BLACK_GLOWSTONE_GLASS  = registerBlock("black_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> BLUE_GLOWSTONE_GLASS  = registerBlock("blue_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> BROWN_GLOWSTONE_GLASS  = registerBlock("brown_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> CYAN_GLOWSTONE_GLASS  = registerBlock("cyan_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> GRAY_GLOWSTONE_GLASS  = registerBlock("gray_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> GREEN_GLOWSTONE_GLASS  = registerBlock("green_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LIGHT_BLUE_GLOWSTONE_GLASS  = registerBlock("light_blue_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LIGHT_GRAY_GLOWSTONE_GLASS  = registerBlock("light_gray_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LIME_GLOWSTONE_GLASS  = registerBlock("lime_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> MAGENTA_GLOWSTONE_GLASS  = registerBlock("magenta_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> ORANGE_GLOWSTONE_GLASS  = registerBlock("orange_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> PINK_GLOWSTONE_GLASS  = registerBlock("pink_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> PURPLE_GLOWSTONE_GLASS  = registerBlock("purple_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> RED_GLOWSTONE_GLASS  = registerBlock("red_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> WHITE_GLOWSTONE_GLASS  = registerBlock("white_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> YELLOW_GLOWSTONE_GLASS  = registerBlock("yellow_glowstone_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    /// Crops Blocks ///
    public static final DeferredBlock<Block> COTTON_CROP = BLOCKS.register("cotton_crop",
            () -> new CottonCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));

    public static final DeferredBlock<Block> FLAX_CROP = BLOCKS.register("flax_crop",
            () -> new FlaxCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));

    /// Bushes Blocks ///
    public static final DeferredBlock<Block> BLACKBERRY_BUSH = BLOCKS.register("blackberry_bush",
            () -> new BlackberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> BLUEBERRY_BUSH = BLOCKS.register("blueberry_bush",
            () -> new BlueberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> MALOBERRY_BUSH = BLOCKS.register("maloberry_bush",
            () -> new MaloberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> RASPBERRY_BUSH = BLOCKS.register("raspberry_bush",
            () -> new RaspberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    private static <T extends Block> DeferredBlock<T> registerBlock (String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem (String name, DeferredBlock<T> block) {
        SPItems.ITEMS.register (name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
