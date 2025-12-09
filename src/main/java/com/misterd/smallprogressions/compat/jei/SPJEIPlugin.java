package com.misterd.smallprogressions.compat.jei;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.config.Config;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

@JeiPlugin
public class SPJEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new CobbleGeneratorCategory(registration.getJeiHelpers().getGuiHelper()),
                new LavaInfusedStoneCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(CobbleGeneratorCategory.RECIPE_TYPE, List.of(
                new CobbleGenRecipe(
                        new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get()),
                        new ItemStack(Blocks.COBBLESTONE),
                        Config.getCobblestoneGenTier1Ticks() + " ticks",
                        String.format("%.2f seconds", Config.getCobblestoneGenTier1Ticks() / 20.0)
                ),
                new CobbleGenRecipe(
                        new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_2.get()),
                        new ItemStack(Blocks.COBBLESTONE),
                        Config.getCobblestoneGenTier2Ticks() + " ticks",
                        String.format("%.2f seconds", Config.getCobblestoneGenTier2Ticks() / 20.0)
                ),
                new CobbleGenRecipe(
                        new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get()),
                        new ItemStack(Blocks.COBBLESTONE),
                        Config.getCobblestoneGenTier3Ticks() + " ticks",
                        String.format("%.2f seconds", Config.getCobblestoneGenTier3Ticks() / 20.0)
                ),
                new CobbleGenRecipe(
                        new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get()),
                        new ItemStack(Blocks.COBBLESTONE),
                        Config.getCobblestoneGenTier4Ticks() + " ticks",
                        String.format("%.2f seconds", Config.getCobblestoneGenTier4Ticks() / 20.0)
                ),
                new CobbleGenRecipe(
                        new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get()),
                        new ItemStack(Blocks.COBBLESTONE),
                        Config.getCobblestoneGenTier5Ticks() + " ticks",
                        String.format("%.2f seconds", Config.getCobblestoneGenTier5Ticks() / 20.0)
                )
        ));

        // Lava Infused Stone recipe
        registration.addRecipes(LavaInfusedStoneCategory.RECIPE_TYPE, List.of(
                new LavaInfusedRecipe(
                        new ItemStack(SPBlocks.LAVA_INFUSED_STONE.get()),
                        new ItemStack(Items.WATER_BUCKET),
                        new ItemStack(Blocks.OBSIDIAN)
                )
        ));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        // Cobblestone Generators
        registration.addRecipeCatalyst(
                new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get()),
                CobbleGeneratorCategory.RECIPE_TYPE
        );
        registration.addRecipeCatalyst(
                new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_2.get()),
                CobbleGeneratorCategory.RECIPE_TYPE
        );
        registration.addRecipeCatalyst(
                new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get()),
                CobbleGeneratorCategory.RECIPE_TYPE
        );
        registration.addRecipeCatalyst(
                new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get()),
                CobbleGeneratorCategory.RECIPE_TYPE
        );
        registration.addRecipeCatalyst(
                new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get()),
                CobbleGeneratorCategory.RECIPE_TYPE
        );

        // Lava Infused Stone
        registration.addRecipeCatalyst(
                new ItemStack(SPBlocks.LAVA_INFUSED_STONE.get()),
                LavaInfusedStoneCategory.RECIPE_TYPE
        );
    }
}