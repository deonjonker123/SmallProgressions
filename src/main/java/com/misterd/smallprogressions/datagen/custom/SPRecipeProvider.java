package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class SPRecipeProvider extends RecipeProvider {
    public SPRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new SPRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "My Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        // Functional Blocks
        shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get())
                .pattern("SSS")
                .pattern("LGW")
                .pattern("SSS")
                .define('S', Tags.Items.STONES)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .unlockedBy("has_lava", has(Tags.Items.BUCKETS_LAVA))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_2)
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.IRON_INGOT)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_1)
                .unlockedBy("has_cobblestone_generator_tier_1", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_1))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get())
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.DIAMOND)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_2)
                .unlockedBy("has_cobblestone_generator_tier_2", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_2))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get())
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.EMERALD)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_3)
                .unlockedBy("has_cobblestone_generator_tier_3", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_3))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get())
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.NETHERITE_INGOT)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_4)
                .unlockedBy("has_cobblestone_generator_tier_4", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_4))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.GROWTH_CRYSTAL_TIER_1.get())
                .pattern("LLL")
                .pattern("BGB")
                .pattern("#W#")
                .define('#', Items.IRON_INGOT)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('B', Items.BONE_MEAL)
                .define('G', Items.GLOWSTONE)
                .define('L', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.GROWTH_CRYSTAL_TIER_2.get())
                .pattern("LLL")
                .pattern("BGB")
                .pattern("#W#")
                .define('#', Items.DIAMOND)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('B', Items.BONE_MEAL)
                .define('G', SPBlocks.GROWTH_CRYSTAL_TIER_1)
                .define('L', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_growth_crystal_tier_1", has(SPBlocks.GROWTH_CRYSTAL_TIER_1))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.GROWTH_CRYSTAL_TIER_3.get())
                .pattern("LLL")
                .pattern("BGB")
                .pattern("#W#")
                .define('#', Items.NETHERITE_INGOT)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('B', Items.BONE_MEAL)
                .define('G', SPBlocks.GROWTH_CRYSTAL_TIER_2)
                .define('L', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_growth_crystal_tier_2", has(SPBlocks.GROWTH_CRYSTAL_TIER_2))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.GREENHOUSE_GLASS.get(), 4)
                .pattern("#G#")
                .pattern("GBG")
                .pattern("#G#")
                .define('#', Items.IRON_INGOT)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('B', Items.GLOWSTONE)
                .unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS))
                .save(output);

        // Lava Infused Stone
        shaped(RecipeCategory.MISC, SPBlocks.LAVA_INFUSED_STONE.get())
                .pattern("SOS")
                .pattern("OLO")
                .pattern("SOS")
                .define('S', Items.SMOOTH_STONE)
                .define('O', Tags.Items.OBSIDIANS)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .unlockedBy("has_smooth_stone", has(Items.SMOOTH_STONE))
                .save(output);

        // McFloaty Block
        shaped(RecipeCategory.MISC, SPBlocks.MCFLOATY_BLOCK.get(), 2)
                .pattern("SOS")
                .pattern("OWO")
                .pattern("SOS")
                .define('S', Items.IRON_INGOT)
                .define('O', Tags.Items.FEATHERS)
                .define('W', ItemTags.WOOL)
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(output);

        // Item Collectors
        shaped(RecipeCategory.MISC, SPBlocks.SIMPLE_ITEM_COLLECTOR.get())
                .pattern("GGG")
                .pattern("GEG")
                .pattern("SSS")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('E', Tags.Items.ENDER_PEARLS)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_ender_pearl", has(Tags.Items.ENDER_PEARLS))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.ADVANCED_ITEM_COLLECTOR.get())
                .pattern("GGG")
                .pattern("GEG")
                .pattern("SSS")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('E', Items.ENDER_EYE)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.HARVESTER.get())
                .pattern("SHS")
                .pattern("H#H")
                .pattern("SHS")
                .define('S', Items.IRON_INGOT)
                .define('#', Items.DIAMOND_HOE)
                .define('H', Items.SMOOTH_STONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);

        // Brick Furnace
        shaped(RecipeCategory.MISC, SPBlocks.BRICK_FURNACE.get())
                .pattern("C#C")
                .pattern("#F#")
                .pattern("SSS")
                .define('F', Items.BLAST_FURNACE)
                .define('C', Items.BRICKS)
                .define('#', Items.IRON_INGOT)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_furnace", has(Items.FURNACE))
                .save(output);

        // Lava, Water Gen Res
        shaped(RecipeCategory.MISC, SPBlocks.LAVA_GENERATOR.get())
                .pattern("B#B")
                .pattern("#L#")
                .pattern("SSS")
                .define('B', Items.IRON_BLOCK)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .define('#', Items.IRON_INGOT)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.WATER_RESERVOIR.get())
                .pattern("B#B")
                .pattern("#L#")
                .pattern("SSS")
                .define('B', Items.IRON_BLOCK)
                .define('L', Tags.Items.BUCKETS_WATER)
                .define('#', Items.IRON_INGOT)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);

        // Storage barrels
        shaped(RecipeCategory.MISC, SPBlocks.COPPER_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.COPPER_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.IRON_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.GOLD_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.DIAMOND_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.DIAMOND)
                .define('B', Items.BARREL)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(output);

        // Tanks
        shaped(RecipeCategory.MISC, SPBlocks.COPPER_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.COPPER_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.IRON_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.GOLD_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.DIAMOND_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.DIAMOND)
                .define('B', Items.BUCKET)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(output);

        // Linen Sack
        shaped(RecipeCategory.MISC, SPBlocks.LINEN_SACK.get())
                .pattern("FSF")
                .pattern("F F")
                .pattern("FFF")
                .define('S', Tags.Items.STRINGS)
                .define('F', Items.HAY_BLOCK)
                .unlockedBy("has_hay_block", has(Items.HAY_BLOCK))
                .save(output);

        // Charcoal Block
        shaped(RecipeCategory.MISC, SPBlocks.CHARCOAL_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', Items.CHARCOAL)
                .unlockedBy("has_charcoal", has(Items.CHARCOAL))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.WIRELESS_REDSTONE_TRANSMITTER.get())
                .pattern("TYT")
                .pattern("ESE")
                .pattern("RSR")
                .define('S', Tags.Items.STONES)
                .define('Y', Items.REPEATER)
                .define('E', Items.ENDER_PEARL)
                .define('R', Items.REDSTONE)
                .define('T', Items.REDSTONE_TORCH)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.WIRELESS_REDSTONE_RECEIVER.get())
                .pattern(" T ")
                .pattern("TET")
                .pattern("SRS")
                .define('S', Tags.Items.STONES)
                .define('E', Items.ENDER_PEARL)
                .define('R', Items.REDSTONE)
                .define('T', Items.REDSTONE_TORCH)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(output);

        shaped(RecipeCategory.MISC, SPBlocks.TIMER.get())
                .pattern(" T ")
                .pattern("RYR")
                .pattern("SSS")
                .define('S', Tags.Items.STONES)
                .define('Y', Items.COMPARATOR)
                .define('R', Items.REDSTONE)
                .define('T', Items.REDSTONE_TORCH)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(output);

        shapeless(RecipeCategory.MISC,  Items.CHARCOAL, 9)
                .requires(SPBlocks.CHARCOAL_BLOCK)
                .unlockedBy("has_charcoal_block", has(SPBlocks.CHARCOAL_BLOCK))
                .save(output, "small_progressions:charcoal_from_charcoal_block");

        shapeless(RecipeCategory.MISC, SPItems.TINY_COAL, 8)
                .requires(Items.COAL)
                .unlockedBy("has_coal", has(Items.COAL))
                .save(output);

        shapeless(RecipeCategory.MISC, SPItems.TINY_CHARCOAL, 8)
                .requires(Items.CHARCOAL)
                .unlockedBy("has_charcoal", has(Items.CHARCOAL))
                .save(output);

        shapeless(RecipeCategory.MISC, Items.COAL)
                .requires(SPItems.TINY_COAL, 8)
                .unlockedBy("has_tiny_coal", has(SPItems.TINY_COAL))
                .save(output, "smallprogressions:coal_from_tiny_coal");

        shapeless(RecipeCategory.MISC, Items.CHARCOAL)
                .requires(SPItems.TINY_CHARCOAL, 8)
                .unlockedBy("has_tiny_charcoal", has(SPItems.TINY_CHARCOAL))
                .save(output, "smallprogressions:charcoal_from_tiny_charcoal");
    }
}
