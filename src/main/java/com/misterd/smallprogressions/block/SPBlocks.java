package com.misterd.smallprogressions.block;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SPBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SmallProgressions.MODID);

    // Functional blocks
    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_1 = registerBlock("growth_crystal_tier_1",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_2 = registerBlock("growth_crystal_tier_2",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> GROWTH_CRYSTAL_TIER_3 = registerBlock("growth_crystal_tier_3",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

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
            () -> new Block(BlockBehaviour.Properties.of()
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
                    .sound(SoundType.BAMBOO)));

    // Bulk storage
    public static final DeferredBlock<Block> CHARCOAL_BLOCK = registerBlock("charcoal_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE_TILES)));

    // Deco Blocks
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

    public static final DeferredBlock<Block> THATCH_BLOCK = registerBlock("thatch_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1F, 1F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> ASPHALT = registerBlock("asphalt",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 6F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LAVA_INFUSED_STONE = registerBlock("lava_infused_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 30000000F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    // Glowstone Blocks
    public static final DeferredBlock<Block> BLACK_GLOWSTONE  = registerBlock("black_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> BLUE_GLOWSTONE  = registerBlock("blue_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> BROWN_GLOWSTONE  = registerBlock("brown_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> CYAN_GLOWSTONE  = registerBlock("cyan_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> GRAY_GLOWSTONE  = registerBlock("gray_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> GREEN_GLOWSTONE  = registerBlock("green_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LIGHT_BLUE_GLOWSTONE  = registerBlock("light_blue_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LIGHT_GRAY_GLOWSTONE  = registerBlock("light_gray_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> LIME_GLOWSTONE  = registerBlock("lime_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> MAGENTA_GLOWSTONE  = registerBlock("magenta_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> ORANGE_GLOWSTONE  = registerBlock("orange_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> PINK_GLOWSTONE  = registerBlock("pink_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> PURPLE_GLOWSTONE  = registerBlock("purple_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> RED_GLOWSTONE  = registerBlock("red_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> WHITE_GLOWSTONE  = registerBlock("white_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> YELLOW_GLOWSTONE  = registerBlock("yellow_glowstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3.0F)
                    .sound(SoundType.GLASS)));

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
