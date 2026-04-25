package com.misterd.smallprogressions.block;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.custom.*;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.item.custom.TankBlockItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
import java.util.function.Supplier;

public class SPBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SmallProgressions.MODID);

    // Functional blocks
    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_1 = registerBlock("cobblestone_generator_tier_1",
            () -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 1));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_2 = registerBlock("cobblestone_generator_tier_2",
            () -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 2));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_3 = registerBlock("cobblestone_generator_tier_3",
            () -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 3));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_4 = registerBlock("cobblestone_generator_tier_4",
            () -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 4));

    public static final DeferredBlock<Block> COBBLESTONE_GENERATOR_TIER_5 = registerBlock("cobblestone_generator_tier_5",
            () -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), 5));

    public static final DeferredBlock<Block> SIMPLE_ITEM_COLLECTOR = registerBlock("simple_item_collector",
            () -> new SimpleItemCollectorBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ADVANCED_ITEM_COLLECTOR = registerBlock("advanced_item_collector",
            () -> new AdvancedItemCollectorBlock(BlockBehaviour.Properties.of()
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
            () -> new LavaGeneratorBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> WATER_RESERVOIR = registerBlock("water_reservoir",
            () -> new WaterReservoirBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> HARVESTER = registerBlock("harvester",
            () -> new HarvesterBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    // Storage Barrels
    public static final DeferredBlock<Block> COPPER_BARREL = registerBlock("copper_barrel",
            () -> new CopperBarrelBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> IRON_BARREL = registerBlock("iron_barrel",
            () -> new IronBarrelBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> GOLD_BARREL = registerBlock("gold_barrel",
            () -> new GoldBarrelBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> DIAMOND_BARREL = registerBlock("diamond_barrel",
            () -> new DiamondBarrelBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    // Fluid Tanks
    public static final DeferredBlock<Block> COPPER_TANK = BLOCKS.register("copper_tank",
            () -> new CopperTankBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> IRON_TANK = BLOCKS.register("iron_tank",
            () -> new IronTankBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> GOLD_TANK = BLOCKS.register("gold_tank",
            () -> new GoldTankBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DIAMOND_TANK = BLOCKS.register("diamond_tank",
            () -> new DiamondTankBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    // Linen Sack
    public static final DeferredBlock<Block> LINEN_SACK = registerBlock("linen_sack",
            () -> new LinenSackBlock(BlockBehaviour.Properties.of()
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
                    .noOcclusion()
                    .lightLevel(state -> 15)
                    .sound(SoundType.STONE), 1));

    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_2 = registerBlock("growth_crystal_tier_2",
            () -> new GrowthCrystalBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(state -> 15)
                    .sound(SoundType.STONE), 2));

    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_3 = registerBlock("growth_crystal_tier_3",
            () -> new GrowthCrystalBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(state -> 15)
                    .sound(SoundType.STONE), 3));

    public static final DeferredBlock<Block> GREENHOUSE_GLASS  = registerBlock("greenhouse_glass",
            () -> new GreenhouseGlassBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3.0F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LAVA_INFUSED_STONE = registerBlock("lava_infused_stone",
            () -> new LavaInfusedStoneBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 2000.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MCFLOATY_BLOCK = BLOCKS.register("mcfloaty_block",
            () -> new McFloatyBlock(BlockBehaviour.Properties.of()
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

    public static final DeferredBlock<Block> REINFORCED_OBSIDIAN = registerBlock("reinforced_obsidian",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(50F, 2000.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("block.smallprogressions.reinforced_obsidian.subtitle").withStyle(ChatFormatting.AQUA));
                }
            });

    public static final DeferredBlock<Block> REINFORCED_GLASS = registerBlock("reinforced_glass",
            () -> new TintedGlassBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 2000.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.GLASS))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("block.smallprogressions.reinforced_glass.subtitle").withStyle(ChatFormatting.AQUA));
                }
            });

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

    static {
        SPItems.ITEMS.register("copper_tank",
                () -> new TankBlockItem(COPPER_TANK.get(), new Item.Properties(), 16000));
        SPItems.ITEMS.register("iron_tank",
                () -> new TankBlockItem(IRON_TANK.get(), new Item.Properties(), 32000));
        SPItems.ITEMS.register("gold_tank",
                () -> new TankBlockItem(GOLD_TANK.get(), new Item.Properties(), 64000));
        SPItems.ITEMS.register("diamond_tank",
                () -> new TankBlockItem(DIAMOND_TANK.get(), new Item.Properties(), 128000));
    }

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
