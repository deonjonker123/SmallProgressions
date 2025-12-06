package com.misterd.smallprogressions.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class SPTags {
    public static class Blocks {
        public static final TagKey<Block> SMALL_PROGRESSIONS_ORE_BLOCKS = createTag("ores/mfu");
        public static final TagKey<Block> INCORRECT_FOR_STEEL_TOOL = createTag("incorrect_for_steel_tool");
        public static final TagKey<Block> INCORRECT_FOR_EMERALD_TOOL = createTag("incorrect_for_emerald_tool");
        public static final TagKey<Block> INCORRECT_FOR_WITHER_TOOL = createTag("incorrect_for_wither_tool");
        public static final TagKey<Block> INCORRECT_FOR_DRAGON_TOOL = createTag("incorrect_for_dragon_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("small_progressions", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SMALL_PROGRESSIONS_ORES = createTag("ores/small_progressions");
        public static final TagKey<Item> SMALL_PROGRESSIONS_INGOTS = createTag("ingots/small_progressions");
        public static final TagKey<Item> SMALL_PROGRESSIONS_NUGGETS = createTag("nuggets/small_progressions");
        public static final TagKey<Item> SMALL_PROGRESSIONS_RAW_MATERIALS = createTag("raw_materials/small_progressions");
        public static final TagKey<Item> SMALL_PROGRESSIONS_TOOLS = createTag("small_progressions_tools");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("small_progressions", name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> HAS_BERRY_BUSHES = createTag("has_berry_bushes");

        private static TagKey<Biome> createTag(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("small_progressions", name));
        }
    }
}