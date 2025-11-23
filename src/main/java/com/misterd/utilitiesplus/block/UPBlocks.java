package com.misterd.utilitiesplus.block;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.item.UPItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class UPBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(UtilitiesPlus.MODID);

    // Automation blocks
    public static final DeferredBlock<Block> AUTO_FEEDER = registerBlock("auto_feeder",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> BLOCK_PLACER = registerBlock("block_placer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BLOCK_BREAKER = registerBlock("block_breaker",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> FUEL_RES = registerBlock("fuel_res",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> SIMPLE_ITEM_COLLECTOR = registerBlock("simple_item_collector",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ADVANCED_ITEM_COLLECTOR = registerBlock("advanced_item_collector",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    // Bulk storage
    public static final DeferredBlock<Block> CHARCOAL_BLOCK = registerBlock("charcoal_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE_TILES)));

    // Deco Blocks
    public static final DeferredBlock<Block> OBSIDIAN_INFUSED_STONE_BRICKS = registerBlock("obsidian_infused_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 10000000000F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> OBSIDIAN_INFUSED_STONE_TILES = registerBlock("obsidian_infused_stone_tiles",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 10000000000F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> CARVED_OBSIDIAN_INFUSED_STONE_BLOCK = registerBlock("carved_obsidian_infused_stone_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 10000000000F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> OBSIDIAN_INFUSED_STONE_PILLAR = registerBlock("obsidian_infused_stone_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(3F, 10000000000F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> OBSIDIAN_INFUSED_STONE_LAMP = registerBlock("obsidian_infused_stone_lamp",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F, 10000000000F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

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

    // Wood sets
    public static final DeferredBlock<Block> FIG_TREE_LOG = registerBlock("fig_tree_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> STRIPPED_FIG_TREE_LOG = registerBlock("stripped_fig_tree_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> FIG_TREE_WOOD = registerBlock("fig_tree_wood",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> STRIPPED_FIG_TREE_WOOD = registerBlock("stripped_fig_tree_wood",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> FIG_TREE_PLANKS = registerBlock("fig_tree_planks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> FIG_TREE_LEAVES = registerBlock("fig_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    /* Fig Tree Sapling
    public static final DeferredBlock<Block> FIG_TREE_SAPLING = registerBlock("fig_tree_sapling",
            () -> new SaplingBlock(UPTreeGrowers.FIG_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    */

    public static final DeferredBlock<Block> MULBERRY_TREE_LOG = registerBlock("mulberry_tree_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> STRIPPED_MULBERRY_TREE_LOG = registerBlock("stripped_mulberry_tree_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> MULBERRY_TREE_WOOD = registerBlock("mulberry_tree_wood",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> STRIPPED_MULBERRY_TREE_WOOD = registerBlock("stripped_mulberry_tree_wood",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> MULBERRY_PLANKS = registerBlock("mulberry_planks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> MULBERRY_LEAVES = registerBlock("mulberry_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    /* Fig Tree Sapling
    public static final DeferredBlock<Block> MULBERRY_TREE_SAPLING = registerBlock("mulberry_tree_sapling",
            () -> new SaplingBlock(UPTreeGrowers.MULBERRY_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    */

    public static final DeferredBlock<Block> BONETREE_LOG = registerBlock("bonetree_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> STRIPPED_BONETREE_LOG = registerBlock("stripped_bonetree_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> BONETREE_WOOD = registerBlock("bonetree_wood",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> STRIPPED_BONETREE_WOOD = registerBlock("stripped_bonetree_wood",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> BONETREE_PLANKS = registerBlock("bonetree_planks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> BONETREE_LEAVES = registerBlock("bonetree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of()
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)));

    /* Fig Tree Sapling
    public static final DeferredBlock<Block> BONETREE_SAPLING = registerBlock("mulberry_tree_sapling",
            () -> new SaplingBlock(UPTreeGrowers.BONETREE_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    */

    private static <T extends Block> DeferredBlock<T> registerBlock (String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem (String name, DeferredBlock<T> block) {
        UPItems.ITEMS.register (name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
