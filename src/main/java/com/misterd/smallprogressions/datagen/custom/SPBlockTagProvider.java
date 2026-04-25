package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.util.SPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SPBlockTagProvider extends BlockTagsProvider {
    public SPBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SmallProgressions.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get())
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_2.get())
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get())
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get())
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get())

                .add(SPBlocks.HARVESTER.get())

                .add(SPBlocks.SIMPLE_ITEM_COLLECTOR.get())
                .add(SPBlocks.ADVANCED_ITEM_COLLECTOR.get())

                .add(SPBlocks.BRICK_FURNACE.get())

                .add(SPBlocks.LAVA_GENERATOR.get())
                .add(SPBlocks.WATER_RESERVOIR.get())

                .add(SPBlocks.COPPER_TANK.get())
                .add(SPBlocks.IRON_TANK.get())
                .add(SPBlocks.GOLD_TANK.get())
                .add(SPBlocks.DIAMOND_TANK.get())

                .add(SPBlocks.GROWTH_CRYSTAL_TIER_1.get())
                .add(SPBlocks.GROWTH_CRYSTAL_TIER_2.get())
                .add(SPBlocks.GROWTH_CRYSTAL_TIER_3.get())
                .add(SPBlocks.GREENHOUSE_GLASS.get())

                .add(SPBlocks.LAVA_INFUSED_STONE.get())
                .add(SPBlocks.MCFLOATY_BLOCK.get())

                .add(SPBlocks.CHARCOAL_BLOCK.get())
                .add(SPBlocks.STEEL_BLOCK.get())

                .add(SPBlocks.REINFORCED_OBSIDIAN.get())
                .add(SPBlocks.REINFORCED_GLASS.get())

                .add(SPBlocks.HARDENED_STONE.get())
                .add(SPBlocks.HARDENED_STONE_BRICKS.get())
                .add(SPBlocks.HARDENED_STONE_STAIRS.get())
                .add(SPBlocks.HARDENED_STONE_BRICK_STAIRS.get())
                .add(SPBlocks.HARDENED_STONE_SLAB.get())
                .add(SPBlocks.HARDENED_STONE_BRICK_SLAB.get())
                .add(SPBlocks.HARDENED_STONE_BUTTON.get())
                .add(SPBlocks.HARDENED_STONE_PRESSURE_PLATE.get())
                .add(SPBlocks.HARDENED_STONE_WALL.get())
                .add(SPBlocks.HARDENED_STONE_BRICK_WALL.get())

                .add(SPBlocks.STONE_ENDER_ORE.get())
                .add(SPBlocks.DEEPSLATE_ENDER_ORE.get())
                .add(SPBlocks.NETHERRACK_ENDER_ORE.get())
                .add(SPBlocks.ENDSTONE_ENDER_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(SPBlocks.STONE_ENDER_ORE.get())
                .add(SPBlocks.DEEPSLATE_ENDER_ORE.get())
                .add(SPBlocks.NETHERRACK_ENDER_ORE.get())
                .add(SPBlocks.ENDSTONE_ENDER_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(SPBlocks.REINFORCED_OBSIDIAN.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(SPBlocks.COPPER_BARREL.get())
                .add(SPBlocks.IRON_BARREL.get())
                .add(SPBlocks.GOLD_BARREL.get())
                .add(SPBlocks.DIAMOND_BARREL.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(SPBlocks.LINEN_SACK.get())

                .add(SPBlocks.THATCH_BLOCK.get())
                .add(SPBlocks.THATCH_STAIRS.get())
                .add(SPBlocks.THATCH_SLAB.get());

        tag(SPTags.Blocks.SMALL_PROGRESSIONS_ORE_BLOCKS)
                .add(SPBlocks.STONE_ENDER_ORE.get())
                .add(SPBlocks.DEEPSLATE_ENDER_ORE.get())
                .add(SPBlocks.NETHERRACK_ENDER_ORE.get())
                .add(SPBlocks.ENDSTONE_ENDER_ORE.get());

        tag(BlockTags.WALLS)
                .add(SPBlocks.HARDENED_STONE_WALL.get())
                .add(SPBlocks.HARDENED_STONE_BRICK_WALL.get());

        tag(SPTags.Blocks.INCORRECT_FOR_STEEL_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);

        tag(SPTags.Blocks.INCORRECT_FOR_EMERALD_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);

        tag(SPTags.Blocks.INCORRECT_FOR_WITHER_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);

        tag(SPTags.Blocks.INCORRECT_FOR_DRAGON_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);

        tag(BlockTags.WITHER_IMMUNE)
                .add(SPBlocks.REINFORCED_OBSIDIAN.get())
                .add(SPBlocks.REINFORCED_GLASS.get());
    }
}
