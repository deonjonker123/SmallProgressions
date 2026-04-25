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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get())
                .pattern("SSS")
                .pattern("LGW")
                .pattern("SSS")
                .define('S', Tags.Items.STONES)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .unlockedBy("has_lava", has(Tags.Items.BUCKETS_LAVA))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_2)
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.IRON_INGOT)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_1)
                .unlockedBy("has_cobblestone_generator_tier_1", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_1))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get())
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.DIAMOND)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_2)
                .unlockedBy("has_cobblestone_generator_tier_2", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_2))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get())
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.EMERALD)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_3)
                .unlockedBy("has_cobblestone_generator_tier_3", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_3))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get())
                .pattern("III")
                .pattern("I#I")
                .pattern("III")
                .define('I', Items.NETHERITE_INGOT)
                .define('#', SPBlocks.COBBLESTONE_GENERATOR_TIER_4)
                .unlockedBy("has_cobblestone_generator_tier_4", has(SPBlocks.COBBLESTONE_GENERATOR_TIER_4))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.GROWTH_CRYSTAL_TIER_1.get())
                .pattern("LLL")
                .pattern("BGB")
                .pattern("#W#")
                .define('#', Items.IRON_INGOT)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('B', Items.BONE_MEAL)
                .define('G', Items.GLOWSTONE)
                .define('L', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.GROWTH_CRYSTAL_TIER_2.get())
                .pattern("LLL")
                .pattern("BGB")
                .pattern("#W#")
                .define('#', Items.DIAMOND)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('B', Items.BONE_MEAL)
                .define('G', SPBlocks.GROWTH_CRYSTAL_TIER_1)
                .define('L', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_growth_crystal_tier_1", has(SPBlocks.GROWTH_CRYSTAL_TIER_1))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.GROWTH_CRYSTAL_TIER_3.get())
                .pattern("LLL")
                .pattern("BGB")
                .pattern("#W#")
                .define('#', Items.NETHERITE_INGOT)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('B', Items.BONE_MEAL)
                .define('G', SPBlocks.GROWTH_CRYSTAL_TIER_2)
                .define('L', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_growth_crystal_tier_2", has(SPBlocks.GROWTH_CRYSTAL_TIER_2))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.GREENHOUSE_GLASS.get(), 4)
                .pattern("#G#")
                .pattern("GBG")
                .pattern("#G#")
                .define('#', Items.IRON_INGOT)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('B', Items.GLOWSTONE)
                .unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS))
                .save(recipeOutput);

        // Lava Infused Stone
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.LAVA_INFUSED_STONE.get())
                .pattern("SOS")
                .pattern("OLO")
                .pattern("SOS")
                .define('S', Items.SMOOTH_STONE)
                .define('O', Tags.Items.OBSIDIANS)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .unlockedBy("has_smooth_stone", has(Items.SMOOTH_STONE))
                .save(recipeOutput);

        // McFloaty Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.MCFLOATY_BLOCK.get(), 2)
                .pattern("SOS")
                .pattern("OWO")
                .pattern("SOS")
                .define('S', Items.IRON_INGOT)
                .define('O', Tags.Items.FEATHERS)
                .define('W', ItemTags.WOOL)
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(recipeOutput);

        // Item Collectors
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.SIMPLE_ITEM_COLLECTOR.get())
                .pattern("GGG")
                .pattern("GEG")
                .pattern("SSS")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('E', Tags.Items.ENDER_PEARLS)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_ender_pearl", has(Tags.Items.ENDER_PEARLS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.ADVANCED_ITEM_COLLECTOR.get())
                .pattern("GGG")
                .pattern("GEG")
                .pattern("SSS")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('E', Items.ENDER_EYE)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.HARVESTER.get())
                .pattern("SHS")
                .pattern("H#H")
                .pattern("SHS")
                .define('S', Items.IRON_INGOT)
                .define('#', Items.DIAMOND_HOE)
                .define('H', Items.SMOOTH_STONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        // Brick Furnace
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.BRICK_FURNACE.get())
                .pattern("C#C")
                .pattern("#F#")
                .pattern("SSS")
                .define('F', Items.BLAST_FURNACE)
                .define('C', Items.BRICKS)
                .define('#', Items.IRON_INGOT)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_furnace", has(Items.FURNACE))
                .save(recipeOutput);

        // Lava, Water Gen Res
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.LAVA_GENERATOR.get())
                .pattern("B#B")
                .pattern("#L#")
                .pattern("SSS")
                .define('B', Items.IRON_BLOCK)
                .define('L', Tags.Items.BUCKETS_LAVA)
                .define('#', Items.IRON_INGOT)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.WATER_RESERVOIR.get())
                .pattern("B#B")
                .pattern("#L#")
                .pattern("SSS")
                .define('B', Items.IRON_BLOCK)
                .define('L', Tags.Items.BUCKETS_WATER)
                .define('#', Items.IRON_INGOT)
                .define('S', Items.SMOOTH_STONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        // Storage barrels
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.COPPER_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.COPPER_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.IRON_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.GOLD_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Items.BARREL)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.DIAMOND_BARREL.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.DIAMOND)
                .define('B', Items.BARREL)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        // Tanks
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.COPPER_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.COPPER_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.IRON_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.GOLD_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.DIAMOND_TANK.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.DIAMOND)
                .define('B', Items.BUCKET)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        // Linen Sack
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.LINEN_SACK.get())
                .pattern("FSF")
                .pattern("F F")
                .pattern("FFF")
                .define('S', Tags.Items.STRINGS)
                .define('F', Items.HAY_BLOCK)
                .unlockedBy("has_hay_block", has(Items.HAY_BLOCK))
                .save(recipeOutput);

        // Charcoal Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPBlocks.CHARCOAL_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', Items.CHARCOAL)
                .unlockedBy("has_charcoal", has(Items.CHARCOAL))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,  Items.CHARCOAL, 9)
                .requires(SPBlocks.CHARCOAL_BLOCK)
                .unlockedBy("has_charcoal_block", has(SPBlocks.CHARCOAL_BLOCK))
                .save(recipeOutput, "small_progressions:charcoal_from_charcoal_block");
        
        // Storage barrels upgrades
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.IRON_BARREL_UPGRADE.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.GOLD_BARREL_UPGRADE.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.DIAMOND_BARREL_UPGRADE.get())
                .pattern("P#P")
                .pattern("PBP")
                .pattern("P#P")
                .define('P', ItemTags.PLANKS)
                .define('#', Items.DIAMOND)
                .define('B', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        // Tanks upgrades
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.IRON_TANK_UPGRADE.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.IRON_INGOT)
                .define('B', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.GOLD_TANK_UPGRADE.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.GOLD_INGOT)
                .define('B', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItems.DIAMOND_TANK_UPGRADE.get())
                .pattern("#G#")
                .pattern("#B#")
                .pattern("###")
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('#', Items.DIAMOND)
                .define('B', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.BIG_BUCKET.get())
                .pattern("RWR")
                .pattern("WRW")
                .define('W', Items.BUCKET)
                .define('R', Items.OBSIDIAN)
                .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SPItems.BIG_POUCH.get())
                .pattern("LSL")
                .pattern("SBS")
                .pattern("LLL")
                .define('L', Items.LEATHER)
                .define('S', Items.IRON_INGOT)
                .define('B', SPBlocks.DIAMOND_BARREL)
                .unlockedBy("has_diamond_barrel", has(SPBlocks.DIAMOND_BARREL))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.TINY_COAL, 8)
                .requires(Items.COAL)
                .unlockedBy("has_coal", has(Items.COAL))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItems.TINY_CHARCOAL, 8)
                .requires(Items.CHARCOAL)
                .unlockedBy("has_charcoal", has(Items.CHARCOAL))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.COAL)
                .requires(SPItems.TINY_COAL, 8)
                .unlockedBy("has_tiny_coal", has(SPItems.TINY_COAL))
                .save(recipeOutput, "smallprogressions:coal_from_tiny_coal");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CHARCOAL)
                .requires(SPItems.TINY_CHARCOAL, 8)
                .unlockedBy("has_tiny_charcoal", has(SPItems.TINY_CHARCOAL))
                .save(recipeOutput, "smallprogressions:charcoal_from_tiny_charcoal");
    }
}
