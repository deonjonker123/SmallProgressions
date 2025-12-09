package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.util.SPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SPRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public SPRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Functional Blocks
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get())
                .pattern("SSS")
                .pattern("LGW")
                .pattern("SSS")
                .define('S', Tags.Items.STONES)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .unlockedBy("has_lava", has(Tags.Items.BUCKETS_LAVA))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.COBBLESTONE_GENERATOR_TIER_2)
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.IRON_INGOT)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_1)
                .unlockedBy("has_cobblestone_generator_tier_1", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_1))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get())
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.DIAMOND)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_2)
                .unlockedBy("has_cobblestone_generator_tier_2", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_2))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get())
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.EMERALD)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_3)
                .unlockedBy("has_cobblestone_generator_tier_3", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_3))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get())
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.NETHERITE_INGOT)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_4)
                .unlockedBy("has_cobblestone_generator_tier_4", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_4))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.GROWTH_CRYSTAL_TIER_1.get())
                .pattern("#W#")
                .pattern("BGB")
                .pattern("#W#")
                .define('#', SPItems.STEEL_INGOT)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('B', Items.BONE_MEAL)
                .define('G', SPTags.Items.SMALL_PROGRESSIONS_GLOWSTONE_BLOCKS)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.GROWTH_CRYSTAL_TIER_2.get())
                .pattern("#W#")
                .pattern("BGB")
                .pattern("#W#")
                .define('#', SPItems.STEEL_INGOT)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('B', Items.BONE_MEAL)
                .define('G', SPBlocks.GROWTH_CRYSTAL_TIER_1)
                .unlockedBy("has_growth_crystal_tier_1", has(SPBlocks.GROWTH_CRYSTAL_TIER_1))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.GROWTH_CRYSTAL_TIER_3.get())
                .pattern("#W#")
                .pattern("BGB")
                .pattern("#W#")
                .define('#', SPItems.STEEL_INGOT)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('B', Items.BONE_MEAL)
                .define('G', SPBlocks.GROWTH_CRYSTAL_TIER_2)
                .unlockedBy("has_growth_crystal_tier_2", has(SPBlocks.GROWTH_CRYSTAL_TIER_2))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.GREENHOUSE_GLASS.get(), 4)
                .pattern("#G#")
                .pattern("GBG")
                .pattern("#G#")
                .define('#', SPItems.STEEL_INGOT)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('B', SPTags.Items.SMALL_PROGRESSIONS_GLOWSTONE_BLOCKS)
                .unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS))
                .save(recipeOutput);

        // Lava Infused Stone
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.LAVA_INFUSED_STONE.get())
                .pattern("SOS")
                .pattern("OLO")
                .pattern("SOS")
                .define('S', SPBlocks.HARDENED_STONE)
                .define('O', Tags.Items.OBSIDIANS)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .unlockedBy("has_hardened_stone", has(SPBlocks.HARDENED_STONE))
                .save(recipeOutput);

        // McFloaty Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.MCFLOATY_BLOCK.get(), 2)
                .pattern("SOS")
                .pattern("OWO")
                .pattern("SOS")
                .define('S', SPItems.STEEL_INGOT)
                .define('O', Tags.Items.FEATHERS)
                .define('W', ItemTags.WOOL)
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(recipeOutput);

        // Item Collectors
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.SIMPLE_ITEM_COLLECTOR.get())
                .pattern("GGG")
                .pattern("GEG")
                .pattern("SSS")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('E', Tags.Items.ENDER_PEARLS)
                .define('S', SPBlocks.HARDENED_STONE)
                .unlockedBy("has_ender_pearl", has(Tags.Items.ENDER_PEARLS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.ADVANCED_ITEM_COLLECTOR.get())
                .pattern("GGG")
                .pattern("GEG")
                .pattern("SSS")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('E', Items.ENDER_EYE)
                .define('S', SPBlocks.HARDENED_STONE)
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.HARVESTER.get())
                .pattern("SHS")
                .pattern("H#H")
                .pattern("SHS")
                .define('S', SPItems.STEEL_INGOT)
                .define('#', Items.DIAMOND_HOE)
                .define('H', SPBlocks.HARDENED_STONE)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        // Brick Furnace
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.BRICK_FURNACE.get())
                .pattern("C#C")
                .pattern("#F#")
                .pattern("SSS")
                .define('F', Items.FURNACE)
                .define('C', Items.BRICKS)
                .define('#', SPItems.STEEL_INGOT)
                .define('S', SPBlocks.HARDENED_STONE)
                .unlockedBy("has_furnace", has(Items.FURNACE))
                .save(recipeOutput);

        // Lava, Water Gen Res
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.LAVA_GENERATOR.get())
                .pattern("B#B")
                .pattern("#L#")
                .pattern("SSS")
                .define('B', SPBlocks.STEEL_BLOCK)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .define('#', SPItems.STEEL_INGOT)
                .define('S', SPBlocks.HARDENED_STONE)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.WATER_RESERVOIR.get())
                .pattern("B#B")
                .pattern("#L#")
                .pattern("SSS")
                .define('B', SPBlocks.STEEL_BLOCK)
                .define('L', Tags.Items.BUCKETS_WATER)
                .define('#', SPItems.STEEL_INGOT)
                .define('S', SPBlocks.HARDENED_STONE)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        // Storage barrels
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.COPPER_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.COPPER_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.IRON_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.GOLD_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.DIAMOND_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.DIAMOND)
                .define('B', Items.BARREL)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        // Tanks
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.COPPER_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.COPPER_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.IRON_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.GOLD_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.DIAMOND_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.DIAMOND)
                .define('B', Items.BUCKET)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        // Linen Sack
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.LINEN_SACK.get())
                .pattern("FSF")
                .pattern("F F")
                .pattern("FFF")
                .define('S', Tags.Items.STRINGS)
                .define('F', SPItems.FLAX)
                .unlockedBy("has_flax", has(SPItems.FLAX))
                .save(recipeOutput);

        // Charcoal Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.CHARCOAL_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', Items.CHARCOAL)
                .unlockedBy("has_charcoal", has(Items.CHARCOAL))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,  Items.CHARCOAL, 9)
                .requires(SPBlocks.CHARCOAL_BLOCK)
                .unlockedBy("has_charcoal_block", has(SPBlocks.CHARCOAL_BLOCK))
                .save(recipeOutput);

        // Steel Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.STEEL_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', SPItems.STEEL_INGOT)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,  SPItems.STEEL_INGOT, 9)
                .requires(SPBlocks.STEEL_BLOCK)
                .unlockedBy("has_steel_block", has(SPBlocks.STEEL_BLOCK))
                .save(recipeOutput, "smallprogressions:steel_ingot_from_steel_block");

        //Marble
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.MARBLE_BRICKS.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', SPBlocks.MARBLE)
                .unlockedBy("has_marble", has(SPBlocks.MARBLE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.MARBLE_PILLAR.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', SPBlocks.MARBLE_BRICKS)
                .unlockedBy("has_marble_bricks", has(SPBlocks.MARBLE_BRICKS))
                .save(recipeOutput);

        stairBuilder(SPBlocks.MARBLE_STAIRS.get(), Ingredient.of(SPBlocks.MARBLE)).group("marble")
                        .unlockedBy("has_marble", has(SPBlocks.MARBLE)).save(recipeOutput);
        stairBuilder(SPBlocks.MARBLE_BRICK_STAIRS.get(), Ingredient.of(SPBlocks.MARBLE_BRICKS)).group("marble")
                .unlockedBy("has_marble_bricks", has(SPBlocks.MARBLE_BRICKS)).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.MARBLE_SLAB.get(), SPBlocks.MARBLE.get());
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.MARBLE_BRICK_SLAB.get(), SPBlocks.MARBLE_BRICKS.get());

        buttonBuilder(SPBlocks.MARBLE_BUTTON.get(), Ingredient.of(SPBlocks.MARBLE.get())).group("marble")
                .unlockedBy("has_marble", has(SPBlocks.MARBLE)).save(recipeOutput);

        pressurePlate(recipeOutput, SPBlocks.MARBLE_PRESSURE_PLATE.get(), SPBlocks.MARBLE.get());

        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.MARBLE_WALL.get(), SPBlocks.MARBLE.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.MARBLE_BRICK_WALL.get(), SPBlocks.MARBLE_BRICKS.get());

        // Slate
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.SLATE_BRICKS.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', SPBlocks.SLATE)
                .unlockedBy("has_slate", has(SPBlocks.SLATE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.SLATE_PILLAR.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', SPBlocks.SLATE_BRICKS)
                .unlockedBy("has_slate_bricks", has(SPBlocks.SLATE_BRICKS))
                .save(recipeOutput);

        stairBuilder(SPBlocks.SLATE_STAIRS.get(), Ingredient.of(SPBlocks.SLATE)).group("slate")
                .unlockedBy("has_slate", has(SPBlocks.SLATE)).save(recipeOutput);
        stairBuilder(SPBlocks.SLATE_BRICK_STAIRS.get(), Ingredient.of(SPBlocks.SLATE_BRICKS)).group("slate")
                .unlockedBy("has_slate_bricks", has(SPBlocks.SLATE_BRICKS)).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.SLATE_SLAB.get(), SPBlocks.SLATE.get());
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.SLATE_BRICK_SLAB.get(), SPBlocks.SLATE_BRICKS.get());

        buttonBuilder(SPBlocks.SLATE_BUTTON.get(), Ingredient.of(SPBlocks.SLATE.get())).group("slate")
                .unlockedBy("has_slate", has(SPBlocks.SLATE)).save(recipeOutput);

        pressurePlate(recipeOutput, SPBlocks.SLATE_PRESSURE_PLATE.get(), SPBlocks.SLATE.get());

        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.SLATE_WALL.get(), SPBlocks.SLATE.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.SLATE_BRICK_WALL.get(), SPBlocks.SLATE_BRICKS.get());

        // Hardened Stone
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.HARDENED_STONE_BRICKS.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', SPBlocks.HARDENED_STONE)
                .unlockedBy("has_hardened_stone", has(SPBlocks.HARDENED_STONE))
                .save(recipeOutput);

        stairBuilder(SPBlocks.HARDENED_STONE_STAIRS.get(), Ingredient.of(SPBlocks.HARDENED_STONE)).group("hardened_stone")
                .unlockedBy("has_hardened_stone", has(SPBlocks.HARDENED_STONE)).save(recipeOutput);
        stairBuilder(SPBlocks.HARDENED_STONE_BRICK_STAIRS.get(), Ingredient.of(SPBlocks.HARDENED_STONE_BRICKS)).group("hardened_stone")
                .unlockedBy("has_hardened_stone_bricks", has(SPBlocks.HARDENED_STONE_BRICKS)).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.HARDENED_STONE_SLAB.get(), SPBlocks.HARDENED_STONE.get());
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.HARDENED_STONE_BRICK_SLAB.get(), SPBlocks.HARDENED_STONE_BRICKS.get());

        buttonBuilder(SPBlocks.HARDENED_STONE_BUTTON.get(), Ingredient.of(SPBlocks.HARDENED_STONE.get())).group("hardened_stone")
                .unlockedBy("has_hardened_stone", has(SPBlocks.HARDENED_STONE)).save(recipeOutput);

        pressurePlate(recipeOutput, SPBlocks.HARDENED_STONE_PRESSURE_PLATE.get(), SPBlocks.HARDENED_STONE.get());

        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.HARDENED_STONE_WALL.get(), SPBlocks.HARDENED_STONE.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.HARDENED_STONE_BRICK_WALL.get(), SPBlocks.HARDENED_STONE_BRICKS.get());

        // Soul Sandstone
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.SOUL_SANDSTONE.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', Items.SOUL_SAND)
                .unlockedBy("has_soul_sand", has(Items.SOUL_SAND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.SOUL_SANDSTONE_BRICKS.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', SPBlocks.SOUL_SANDSTONE)
                .unlockedBy("has_soul_sandstone", has(SPBlocks.SOUL_SANDSTONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.CUT_SOUL_SANDSTONE.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', SPBlocks.SOUL_SANDSTONE_BRICKS)
                .unlockedBy("has_soul_sandstone_bricks", has(SPBlocks.SOUL_SANDSTONE_BRICKS))
                .save(recipeOutput);

        stairBuilder(SPBlocks.SOUL_SANDSTONE_STAIRS.get(), Ingredient.of(SPBlocks.SOUL_SANDSTONE)).group("soul_sandstone")
                .unlockedBy("has_soul_sandstone", has(SPBlocks.HARDENED_STONE)).save(recipeOutput);
        stairBuilder(SPBlocks.SOUL_SANDSTONE_BRICK_STAIRS.get(), Ingredient.of(SPBlocks.SOUL_SANDSTONE_BRICKS)).group("soul_sandstone")
                .unlockedBy("has_soul_sandstone_bricks", has(SPBlocks.HARDENED_STONE_BRICKS)).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.SOUL_SANDSTONE_SLAB.get(), SPBlocks.SOUL_SANDSTONE.get());
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.SOUL_SANDSTONE_BRICK_SLAB.get(), SPBlocks.SOUL_SANDSTONE_BRICKS.get());

        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.SOUL_SANDSTONE_WALL.get(), SPBlocks.SOUL_SANDSTONE.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.SOUL_SANDSTONE_BRICK_WALL.get(), SPBlocks.SOUL_SANDSTONE_BRICKS.get());

        // Thatch Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.THATCH_BLOCK.get(), 2)
                .pattern("SS")
                .pattern("SS")
                .define('S', SPItems.STRAW)
                .unlockedBy("has_straw", has(SPItems.STRAW))
                .save(recipeOutput);

        stairBuilder(SPBlocks.THATCH_STAIRS.get(), Ingredient.of(SPBlocks.THATCH_BLOCK)).group("hardened_stone")
                .unlockedBy("has_thatch_block", has(SPBlocks.THATCH_BLOCK)).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.THATCH_SLAB.get(), SPBlocks.THATCH_BLOCK.get());

        // Asphalt
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.ASPHALT.get(), 8)
                .pattern("CGC")
                .pattern("SLS")
                .pattern("CGC")
                .define('S', Tags.Items.SANDS)
                .define('G', Tags.Items.GRAVELS)
                .define('C', Items.CLAY)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .unlockedBy("has_clay", has(Items.CLAY))
                .save(recipeOutput);

        stairBuilder(SPBlocks.ASPHALT_STAIRS.get(), Ingredient.of(SPBlocks.ASPHALT)).group("asphalt")
                .unlockedBy("has_asphalt", has(SPBlocks.ASPHALT)).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.ASPHALT_SLAB.get(), SPBlocks.ASPHALT.get());

        buttonBuilder(SPBlocks.ASPHALT_BUTTON.get(), Ingredient.of(SPBlocks.ASPHALT.get())).group("asphalt")
                .unlockedBy("has_asphalt", has(SPBlocks.ASPHALT)).save(recipeOutput);

        pressurePlate(recipeOutput, SPBlocks.ASPHALT_PRESSURE_PLATE.get(), SPBlocks.ASPHALT.get());

        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SPBlocks.ASPHALT_WALL.get(), SPBlocks.ASPHALT.get());

        // Reinforced Obsidian
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.REINFORCED_OBSIDIAN.get(), 4)
                .pattern("IOI")
                .pattern("OIO")
                .pattern("IOI")
                .define('I', Items.IRON_BARS)
                .define('O', Tags.Items.OBSIDIANS)
                .unlockedBy("has_obsidian", has(Tags.Items.OBSIDIANS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.REINFORCED_OBSIDIAN.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', SPItems.REINFORCED_OBSIDIAN_INGOT)
                .unlockedBy("has_reinforced_ingot", has(SPItems.REINFORCED_OBSIDIAN_INGOT))
                .save(recipeOutput, "smallprogressions:reinforced_obsidian_from_ingots");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,  SPItems.REINFORCED_OBSIDIAN_INGOT, 9)
                .requires(SPBlocks.REINFORCED_OBSIDIAN)
                .unlockedBy("has_reinforced_obsidian", has(SPBlocks.REINFORCED_OBSIDIAN))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPBlocks.REINFORCED_GLASS.get(), 4)
                .pattern("IOI")
                .pattern("OCO")
                .pattern("IOI")
                .define('I', Items.IRON_BARS)
                .define('O', Tags.Items.GLASS_BLOCKS)
                .define('C', ItemTags.COALS)
                .unlockedBy("has_coal", has(ItemTags.COALS))
                .save(recipeOutput);

        // Glowstone Blocks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.BLACK_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.BLACK_GLOWSTONE_DUST)
                .unlockedBy("has_black_glowstone_dust", has(SPItems.BLACK_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.BLUE_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.BLUE_GLOWSTONE_DUST)
                .unlockedBy("has_blue_glowstone_dust", has(SPItems.BLUE_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.BROWN_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.BROWN_GLOWSTONE_DUST)
                .unlockedBy("has_brown_glowstone_dust", has(SPItems.BROWN_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.CYAN_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.CYAN_GLOWSTONE_DUST)
                .unlockedBy("has_cyan_glowstone_dust", has(SPItems.CYAN_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.GRAY_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.GRAY_GLOWSTONE_DUST)
                .unlockedBy("has_gray_glowstone_dust", has(SPItems.GRAY_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.GREEN_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.GREEN_GLOWSTONE_DUST)
                .unlockedBy("has_green_glowstone_dust", has(SPItems.GREEN_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.LIGHT_BLUE_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.LIGHT_BLUE_GLOWSTONE_DUST)
                .unlockedBy("has_light_blue_glowstone_dust", has(SPItems.LIGHT_BLUE_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.LIGHT_GRAY_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.LIGHT_GRAY_GLOWSTONE_DUST)
                .unlockedBy("has_light_gray_glowstone_dust", has(SPItems.LIGHT_GRAY_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.LIME_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.LIME_GLOWSTONE_DUST)
                .unlockedBy("has_lime_glowstone_dust", has(SPItems.LIME_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.MAGENTA_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.MAGENTA_GLOWSTONE_DUST)
                .unlockedBy("has_magenta_glowstone_dust", has(SPItems.MAGENTA_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.ORANGE_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.ORANGE_GLOWSTONE_DUST)
                .unlockedBy("has_orange_glowstone_dust", has(SPItems.ORANGE_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.PINK_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.PINK_GLOWSTONE_DUST)
                .unlockedBy("has_pink_glowstone_dust", has(SPItems.PINK_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.PURPLE_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.PURPLE_GLOWSTONE_DUST)
                .unlockedBy("has_purple_glowstone_dust", has(SPItems.PURPLE_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.RED_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.RED_GLOWSTONE_DUST)
                .unlockedBy("has_red_glowstone_dust", has(SPItems.RED_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.WHITE_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.WHITE_GLOWSTONE_DUST)
                .unlockedBy("has_white_glowstone_dust", has(SPItems.WHITE_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.YELLOW_GLOWSTONE.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.YELLOW_GLOWSTONE_DUST)
                .unlockedBy("has_yellow_glowstone_dust", has(SPItems.YELLOW_GLOWSTONE_DUST))
                .save(recipeOutput);

        // Glowstone Glass Blocks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.BLACK_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.BLACK_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_black_glowstone_dust", has(SPItems.BLACK_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.RED_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.RED_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_red_glowstone_dust", has(SPItems.RED_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.GREEN_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.GREEN_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_green_glowstone_dust", has(SPItems.GREEN_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.BROWN_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.BROWN_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_brown_glowstone_dust", has(SPItems.BROWN_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.BLUE_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.BLUE_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_blue_glowstone_dust", has(SPItems.BLUE_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.PURPLE_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.PURPLE_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_purple_glowstone_dust", has(SPItems.PURPLE_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.CYAN_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.CYAN_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_cyan_glowstone_dust", has(SPItems.CYAN_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.LIGHT_GRAY_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.LIGHT_GRAY_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_light_gray_glowstone_dust", has(SPItems.LIGHT_GRAY_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.GRAY_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.GRAY_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_gray_glowstone_dust", has(SPItems.GRAY_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.PINK_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.PINK_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_pink_glowstone_dust", has(SPItems.PINK_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.LIME_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.LIME_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_lime_glowstone_dust", has(SPItems.LIME_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.YELLOW_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.YELLOW_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_yellow_glowstone_dust", has(SPItems.YELLOW_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.LIGHT_BLUE_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.LIGHT_BLUE_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_light_blue_glowstone_dust", has(SPItems.LIGHT_BLUE_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.MAGENTA_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.MAGENTA_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_magenta_glowstone_dust", has(SPItems.MAGENTA_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.ORANGE_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.ORANGE_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_orange_glowstone_dust", has(SPItems.ORANGE_GLOWSTONE_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SPBlocks.WHITE_GLOWSTONE_GLASS.get(), 4)
                .pattern(" D ")
                .pattern("D#D")
                .pattern(" D ")
                .define('D', SPItems.WHITE_GLOWSTONE_DUST)
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_white_glowstone_dust", has(SPItems.WHITE_GLOWSTONE_DUST))
                .save(recipeOutput);

        // Colored Glowstone from Dye + Glowstone (Shapeless)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.BLACK_GLOWSTONE.get())
                .requires(Tags.Items.DYES_BLACK)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:black_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.BLUE_GLOWSTONE.get())
                .requires(Tags.Items.DYES_BLUE)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:blue_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.BROWN_GLOWSTONE.get())
                .requires(Tags.Items.DYES_BROWN)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:brown_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.CYAN_GLOWSTONE.get())
                .requires(Tags.Items.DYES_CYAN)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:cyan_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.GRAY_GLOWSTONE.get())
                .requires(Tags.Items.DYES_GRAY)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:gray_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.GREEN_GLOWSTONE.get())
                .requires(Tags.Items.DYES_GREEN)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:green_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.LIGHT_BLUE_GLOWSTONE.get())
                .requires(Tags.Items.DYES_LIGHT_BLUE)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:light_blue_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.LIGHT_GRAY_GLOWSTONE.get())
                .requires(Tags.Items.DYES_LIGHT_GRAY)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:light_gray_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.LIME_GLOWSTONE.get())
                .requires(Tags.Items.DYES_LIME)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:lime_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.MAGENTA_GLOWSTONE.get())
                .requires(Tags.Items.DYES_MAGENTA)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:magenta_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.ORANGE_GLOWSTONE.get())
                .requires(Tags.Items.DYES_ORANGE)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:orange_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.PINK_GLOWSTONE.get())
                .requires(Tags.Items.DYES_PINK)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:pink_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.PURPLE_GLOWSTONE.get())
                .requires(Tags.Items.DYES_PURPLE)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:purple_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.RED_GLOWSTONE.get())
                .requires(Tags.Items.DYES_RED)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:red_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.WHITE_GLOWSTONE.get())
                .requires(Tags.Items.DYES_WHITE)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:white_glowstone_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SPBlocks.YELLOW_GLOWSTONE.get())
                .requires(Tags.Items.DYES_YELLOW)
                .requires(Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, "smallprogressions:yellow_glowstone_from_dye");

        // Furnace cooking Recipes
        // Bacon
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(SPItems.RAW_BACON), RecipeCategory.FOOD,
                        SPItems.COOKED_BACON, 0.1F, 200)
                .unlockedBy("has_raw_bacon", has(SPItems.RAW_BACON))
                .save(recipeOutput, "smallprogressions:cooked_bacon_from_smelting");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(SPItems.RAW_BACON), RecipeCategory.FOOD,
                        SPItems.COOKED_BACON, 0.1F, 100)
                .unlockedBy("has_raw_bacon", has(SPItems.RAW_BACON))
                .save(recipeOutput, "smallprogressions:cooked_bacon_from_smoking");

        // Fried Egg
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.EGG), RecipeCategory.FOOD,
                        SPItems.FRIED_EGG, 0.1F, 200)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput, "smallprogressions:fried_egg_from_smelting");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(Items.EGG), RecipeCategory.FOOD,
                        SPItems.FRIED_EGG, 0.1F, 100)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput, "smallprogressions:fried_egg_from_smoking");

        // Cooked Apple
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.APPLE), RecipeCategory.FOOD,
                        SPItems.COOKED_APPLE, 0.1F, 200)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput, "smallprogressions:cooked_apple_from_smelting");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(Items.APPLE), RecipeCategory.FOOD,
                        SPItems.COOKED_APPLE, 0.1F, 100)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput, "smallprogressions:cooked_apple_from_smoking");

        // Toast
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(SPItems.SLICED_BREAD), RecipeCategory.FOOD,
                        SPItems.TOAST, 0.1F, 200)
                .unlockedBy("has_sliced_bread", has(SPItems.SLICED_BREAD))
                .save(recipeOutput, "smallprogressions:toast_from_smelting");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(Items.BREAD), RecipeCategory.FOOD,
                        SPItems.TOAST, 0.1F, 100)
                .unlockedBy("has_bread", has(Items.BREAD))
                .save(recipeOutput, "smallprogressions:toast_from_smoking");

        // Hardened Stone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.SMOOTH_STONE), RecipeCategory.MISC,
                        SPBlocks.HARDENED_STONE, 0.25F, 200)
                .unlockedBy("has_smooth_stone", has(Items.SMOOTH_STONE))
                .save(recipeOutput, "smallprogressions:hardened_stone_from_smelting");

        // Glowstone Dusts
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.BLACK_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.BLACK_GLOWSTONE)
                .unlockedBy("has_black_glowstone", has(SPBlocks.BLACK_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.BLUE_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.BLUE_GLOWSTONE)
                .unlockedBy("has_blue_glowstone", has(SPBlocks.BLUE_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.BROWN_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.BROWN_GLOWSTONE)
                .unlockedBy("has_brown_glowstone", has(SPBlocks.BROWN_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.CYAN_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.CYAN_GLOWSTONE)
                .unlockedBy("has_cyan_glowstone", has(SPBlocks.CYAN_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.GRAY_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.GRAY_GLOWSTONE)
                .unlockedBy("has_gray_glowstone", has(SPBlocks.GRAY_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.GREEN_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.GREEN_GLOWSTONE)
                .unlockedBy("has_green_glowstone", has(SPBlocks.GREEN_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.LIGHT_BLUE_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.LIGHT_BLUE_GLOWSTONE)
                .unlockedBy("has_light_blue_glowstone", has(SPBlocks.LIGHT_BLUE_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.LIGHT_GRAY_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.LIGHT_GRAY_GLOWSTONE)
                .unlockedBy("has_light_gray_glowstone", has(SPBlocks.LIGHT_GRAY_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.LIME_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.LIME_GLOWSTONE)
                .unlockedBy("has_lime_glowstone", has(SPBlocks.LIME_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.MAGENTA_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.MAGENTA_GLOWSTONE)
                .unlockedBy("has_magenta_glowstone", has(SPBlocks.MAGENTA_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.ORANGE_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.ORANGE_GLOWSTONE)
                .unlockedBy("has_orange_glowstone", has(SPBlocks.ORANGE_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.PINK_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.PINK_GLOWSTONE)
                .unlockedBy("has_pink_glowstone", has(SPBlocks.PINK_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.PURPLE_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.PURPLE_GLOWSTONE)
                .unlockedBy("has_purple_glowstone", has(SPBlocks.PURPLE_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.RED_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.RED_GLOWSTONE)
                .unlockedBy("has_red_glowstone", has(SPBlocks.RED_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.WHITE_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.WHITE_GLOWSTONE)
                .unlockedBy("has_white_glowstone", has(SPBlocks.WHITE_GLOWSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.YELLOW_GLOWSTONE_DUST, 4)
                .requires(SPBlocks.YELLOW_GLOWSTONE)
                .unlockedBy("has_yellow_glowstone", has(SPBlocks.YELLOW_GLOWSTONE))
                .save(recipeOutput);

        // Ender Pearl from Ender Dust
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.ENDER_PEARL)
                .pattern("DD")
                .pattern("DD")
                .define('D', SPItems.ENDER_DUST)
                .unlockedBy("has_ender_dust", has(SPItems.ENDER_DUST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.STEEL_INGOT.get(), 2)
                .pattern("CCC")
                .pattern("IHI")
                .pattern("CCC")
                .define('C', Items.COAL)
                .define('I', Items.IRON_INGOT)
                .define('H', SPItems.HAMMER)
                .unlockedBy("has_hammer", has(SPItems.HAMMER))
                .save(recipeOutput);

        // Juicer
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.JUICER)
                .pattern(" # ")
                .pattern("SSS")
                .define('S', Tags.Items.STONES)
                .define('#', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // Hammer
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.HAMMER)
                .pattern("HSH")
                .pattern("S#S")
                .pattern(" # ")
                .define('S', Tags.Items.STRINGS)
                .define('#', Items.STICK)
                .define('H', SPBlocks.HARDENED_STONE)
                .unlockedBy("has_hardened_stone", has(SPBlocks.HARDENED_STONE))
                .save(recipeOutput);

        // Sickle
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.SICKLE)
                .pattern("SS#")
                .pattern("S# ")
                .pattern("#  ")
                .define('S', SPItems.STEEL_INGOT)
                .define('#', Items.STICK)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        // Repair Totem
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.REPAIR_TOTEM)
                .pattern("HNH")
                .pattern("S#S")
                .pattern("HSH")
                .define('S', SPItems.STEEL_INGOT)
                .define('#', Items.NETHER_STAR)
                .define('H', SPBlocks.HARDENED_STONE)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                .save(recipeOutput);

        // Storage barrels upgrades
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.IRON_BARREL_UPGRADE.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.GOLD_BARREL_UPGRADE.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.DIAMOND_BARREL_UPGRADE.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.DIAMOND)
                .define('B', Items.BARREL)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        // Tanks upgrades
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.IRON_TANK_UPGRADE.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.GOLD_TANK_UPGRADE.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.DIAMOND_TANK_UPGRADE.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.DIAMOND)
                .define('B', Items.BUCKET)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        // Foods
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, SPItems.RAW_BACON, 4)
                .requires(Items.PORKCHOP)
                .unlockedBy("has_porkchop", has(Items.PORKCHOP))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, SPItems.SLICED_BREAD, 4)
                .requires(Items.BREAD)
                .unlockedBy("has_bread", has(Items.BREAD))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.APPLE_JUICE.get())
                .requires(SPItems.JUICER)
                .requires(Items.APPLE)
                .unlockedBy("has_juicer", has(SPItems.JUICER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.BLACKBERRY_JUICE.get())
                .requires(SPItems.JUICER)
                .requires(SPItems.BLACKBERRIES)
                .unlockedBy("has_juicer", has(SPItems.JUICER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.BLUEBERRY_JUICE.get())
                .requires(SPItems.JUICER)
                .requires(SPItems.BLUEBERRIES)
                .unlockedBy("has_juicer", has(SPItems.JUICER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.MALOBERRY_JUICE.get())
                .requires(SPItems.JUICER)
                .requires(SPItems.MALOBERRIES)
                .unlockedBy("has_juicer", has(SPItems.JUICER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.RASPBERRY_JUICE.get())
                .requires(SPItems.JUICER)
                .requires(SPItems.RASPBERRIES)
                .unlockedBy("has_juicer", has(SPItems.JUICER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STRING)
                .requires(SPItems.FLAX)
                .unlockedBy("has_flax", has(SPItems.FLAX))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.WHITE_WOOL)
                .requires(SPItems.COTTON_BOLLS, 4)
                .unlockedBy("has_cotton_bolls", has(SPItems.COTTON_BOLLS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.PIZZA_SLICE.get())
                .pattern("SSS")
                .pattern("TTT")
                .define('T', SPItems.TOAST)
                .define('S', Items.COOKED_BEEF)
                .unlockedBy("has_toast", has(SPItems.TOAST))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.BACON_EGG_SANDWICH.get())
                .pattern(" B ")
                .pattern("#E#")
                .pattern(" B ")
                .define('E', SPItems.FRIED_EGG)
                .define('#', SPItems.COOKED_BACON)
                .define('B', SPItems.SLICED_BREAD)
                .unlockedBy("has_fried_egg", has(SPItems.FRIED_EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.IRON_APPLE.get())
                .pattern("III")
                .pattern("IAI")
                .pattern("III")
                .define('A', Items.APPLE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.REDSTONE_APPLE.get())
                .pattern("III")
                .pattern("IAI")
                .pattern("III")
                .define('A', Items.APPLE)
                .define('I', Items.REDSTONE)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.EMERALD_APPLE.get())
                .pattern("III")
                .pattern("IAI")
                .pattern("III")
                .define('A', Items.APPLE)
                .define('I', Items.EMERALD)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,  SPItems.DIAMOND_APPLE.get())
                .pattern("III")
                .pattern("IAI")
                .pattern("III")
                .define('A', Items.APPLE)
                .define('I', Items.DIAMOND)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput);

        // Steel Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.STEEL_SWORD.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('I', SPItems.STEEL_INGOT)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.STEEL_PICKAXE.get())
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', SPItems.STEEL_INGOT)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.STEEL_AXE.get())
                .pattern("II ")
                .pattern("IS ")
                .pattern(" S ")
                .define('I', SPItems.STEEL_INGOT)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.STEEL_SHOVEL.get())
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', SPItems.STEEL_INGOT)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.STEEL_HOE.get())
                .pattern("II ")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', SPItems.STEEL_INGOT)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.STEEL_PAXEL.get())
                .pattern("PVA")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', SPItems.STEEL_PICKAXE)
                .define('V', SPItems.STEEL_SHOVEL)
                .define('A', SPItems.STEEL_AXE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_steel_pickaxe", has(SPItems.STEEL_PICKAXE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.STEEL_SCYTHE.get())
                .pattern("III")
                .pattern(" II")
                .pattern("S  ")
                .define('I', SPItems.STEEL_INGOT.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT.get()))
                .save(recipeOutput);

        // Steel Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.STEEL_HELMET.get())
                .pattern("III")
                .pattern("I I")
                .pattern("   ")
                .define('I', SPItems.STEEL_INGOT)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.STEEL_CHESTPLATE.get())
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .define('I', SPItems.STEEL_INGOT)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.STEEL_LEGGINGS.get())
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .define('I', SPItems.STEEL_INGOT)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.STEEL_BOOTS.get())
                .pattern("   ")
                .pattern("I I")
                .pattern("I I")
                .define('I', SPItems.STEEL_INGOT)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        // Emerald Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.EMERALD_SWORD.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('I', Items.EMERALD)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.EMERALD_PICKAXE.get())
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', Items.EMERALD)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.EMERALD_AXE.get())
                .pattern("II ")
                .pattern("IS ")
                .pattern(" S ")
                .define('I', Items.EMERALD)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.EMERALD_SHOVEL.get())
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', Items.EMERALD)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.EMERALD_HOE.get())
                .pattern("II ")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', Items.EMERALD)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.EMERALD_PAXEL.get())
                .pattern("PVA")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', SPItems.EMERALD_PICKAXE)
                .define('V', SPItems.EMERALD_SHOVEL)
                .define('A', SPItems.EMERALD_AXE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_emerald_pickaxe", has(SPItems.EMERALD_PICKAXE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.EMERALD_SCYTHE.get())
                .pattern("III")
                .pattern(" II")
                .pattern("S  ")
                .define('I', Items.EMERALD)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        // Emerald Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.EMERALD_HELMET.get())
                .pattern("III")
                .pattern("I I")
                .pattern("   ")
                .define('I', Items.EMERALD)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.EMERALD_CHESTPLATE.get())
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .define('I', Items.EMERALD)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.EMERALD_LEGGINGS.get())
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .define('I', Items.EMERALD)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.EMERALD_BOOTS.get())
                .pattern("   ")
                .pattern("I I")
                .pattern("I I")
                .define('I', Items.EMERALD)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        // Wither Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WITHER_SWORD.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('I', SPItems.WITHER_RIB)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WITHER_PICKAXE.get())
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', SPItems.WITHER_RIB)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WITHER_AXE.get())
                .pattern("II ")
                .pattern("IS ")
                .pattern(" S ")
                .define('I', SPItems.WITHER_RIB)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WITHER_SHOVEL.get())
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', SPItems.WITHER_RIB)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WITHER_HOE.get())
                .pattern("II ")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', SPItems.WITHER_RIB)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WITHER_PAXEL.get())
                .pattern("PVA")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', SPItems.WITHER_PICKAXE)
                .define('V', SPItems.WITHER_SHOVEL)
                .define('A', SPItems.WITHER_AXE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wither_pickaxe", has(SPItems.WITHER_PICKAXE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WITHER_SCYTHE.get())
                .pattern("III")
                .pattern(" II")
                .pattern("S  ")
                .define('I', SPItems.WITHER_RIB)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        // Wither Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.WITHER_HELMET.get())
                .pattern("INI")
                .pattern("I I")
                .pattern("   ")
                .define('I', SPItems.WITHER_RIB)
                .define('N', Items.NETHERITE_HELMET)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.WITHER_CHESTPLATE.get())
                .pattern("I I")
                .pattern("INI")
                .pattern("III")
                .define('I', SPItems.WITHER_RIB)
                .define('N', Items.NETHERITE_CHESTPLATE)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.WITHER_LEGGINGS.get())
                .pattern("INI")
                .pattern("I I")
                .pattern("I I")
                .define('I', SPItems.WITHER_RIB)
                .define('N', Items.NETHERITE_LEGGINGS)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.WITHER_BOOTS.get())
                .pattern("   ")
                .pattern("I I")
                .pattern("INI")
                .define('I', SPItems.WITHER_RIB)
                .define('N', Items.NETHERITE_BOOTS)
                .unlockedBy("has_wither_rib", has(SPItems.WITHER_RIB))
                .save(recipeOutput);

        // Dragon Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.DRAGON_SWORD.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('I', SPItems.DRAGON_SCALE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.DRAGON_PICKAXE.get())
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', SPItems.DRAGON_SCALE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.DRAGON_AXE.get())
                .pattern("II ")
                .pattern("IS ")
                .pattern(" S ")
                .define('I', SPItems.DRAGON_SCALE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.DRAGON_SHOVEL.get())
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', SPItems.DRAGON_SCALE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.DRAGON_HOE.get())
                .pattern("II ")
                .pattern(" S ")
                .pattern(" S ")
                .define('I', SPItems.DRAGON_SCALE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.DRAGON_PAXEL.get())
                .pattern("PVA")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', SPItems.DRAGON_PICKAXE)
                .define('V', SPItems.DRAGON_SHOVEL)
                .define('A', SPItems.DRAGON_AXE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_dragon_pickaxe", has(SPItems.DRAGON_PICKAXE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.DRAGON_SCYTHE.get())
                .pattern("III")
                .pattern(" II")
                .pattern("S  ")
                .define('I', SPItems.DRAGON_SCALE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        // Dragon Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.DRAGON_HELMET.get())
                .pattern("INI")
                .pattern("I I")
                .pattern("   ")
                .define('I', SPItems.DRAGON_SCALE)
                .define('N', Items.NETHERITE_HELMET)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.DRAGON_CHESTPLATE.get())
                .pattern("I I")
                .pattern("INI")
                .pattern("III")
                .define('I', SPItems.DRAGON_SCALE)
                .define('N', Items.NETHERITE_CHESTPLATE)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.DRAGON_LEGGINGS.get())
                .pattern("INI")
                .pattern("I I")
                .pattern("I I")
                .define('I', SPItems.DRAGON_SCALE)
                .define('N', Items.NETHERITE_LEGGINGS)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPItems.DRAGON_BOOTS.get())
                .pattern("   ")
                .pattern("I I")
                .pattern("INI")
                .define('I', SPItems.DRAGON_SCALE)
                .define('N', Items.NETHERITE_BOOTS)
                .unlockedBy("has_dragon_scale", has(SPItems.DRAGON_SCALE))
                .save(recipeOutput);

        // Vanilla Variant Paxels
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.STONE_PAXEL.get())
                .pattern("PVA")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', Items.STONE_PICKAXE)
                .define('V', Items.STONE_SHOVEL)
                .define('A', Items.STONE_AXE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_stone_pickaxe", has(Items.STONE_PICKAXE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.IRON_PAXEL.get())
                .pattern("PVA")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', Items.IRON_PICKAXE)
                .define('V', Items.IRON_SHOVEL)
                .define('A', Items.IRON_AXE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_iron_pickaxe", has(Items.IRON_PICKAXE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.DIAMOND_PAXEL.get())
                .pattern("PVA")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', Items.DIAMOND_PICKAXE)
                .define('V', Items.DIAMOND_SHOVEL)
                .define('A', Items.DIAMOND_AXE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_diamond_pickaxe", has(Items.DIAMOND_PICKAXE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.DIAMOND_SCYTHE.get())
                .pattern(" II")
                .pattern(" I ")
                .pattern("S  ")
                .define('I', Items.DIAMOND)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WATERING_CAN_TIER_1.get())
                .pattern("SB ")
                .pattern("SWS")
                .pattern(" S ")
                .define('B', Items.BONE_MEAL)
                .define('S', SPItems.STEEL_INGOT)
                .define('W', Tags.Items.BUCKETS_WATER)
                .unlockedBy("has_steel_ingot", has(SPItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WATERING_CAN_TIER_2.get())
                .pattern("SB ")
                .pattern("SWS")
                .pattern(" S ")
                .define('B', Items.BONE_MEAL)
                .define('S', Items.EMERALD)
                .define('W', SPItems.WATERING_CAN_TIER_1)
                .unlockedBy("has_watering_can_tier_1", has(SPItems.WATERING_CAN_TIER_1))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.WATERING_CAN_TIER_3.get())
                .pattern("SB ")
                .pattern("SWS")
                .pattern(" S ")
                .define('B', Items.BONE_MEAL)
                .define('S', Items.NETHERITE_INGOT)
                .define('W', SPItems.WATERING_CAN_TIER_2)
                .unlockedBy("has_watering_can_tier_2", has(SPItems.WATERING_CAN_TIER_2))
                .save(recipeOutput);
    }

    protected static void smelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE,
                SmeltingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }

    protected static void smoking(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMOKING_RECIPE,
                SmokingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_smoking");
    }
}
