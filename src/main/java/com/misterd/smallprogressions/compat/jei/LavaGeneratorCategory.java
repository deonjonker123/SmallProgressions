package com.misterd.smallprogressions.compat.jei;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class LavaGeneratorCategory implements IRecipeCategory<LavaGenRecipe> {

    public static final RecipeType<LavaGenRecipe> RECIPE_TYPE =
            RecipeType.create(SmallProgressions.MODID, "lava_generation", LavaGenRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrowAnimated;

    public LavaGeneratorCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/cobblegen_jei_gui.png"),
                0, 0, 118, 60
        ).build();

        this.icon = helper.createDrawableItemStack(new ItemStack(SPBlocks.LAVA_GENERATOR));

        this.arrowAnimated = helper.drawableBuilder(
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/cobblegen_jei_gui.png"),
                118, 0, 22, 15
        ).buildAnimated(20, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<LavaGenRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.smallprogressions.lava_generation");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, LavaGenRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 11, 21)
                .addItemStack(recipe.generator());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 21)
                .addItemStack(recipe.output());
    }

    @Override
    public void draw(LavaGenRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrowAnimated.draw(guiGraphics, 48, 23);

        var font = Minecraft.getInstance().font;
        Component rateText = Component.translatable("jei.smallprogressions.lava_generation.rate", recipe.displayRate());
        Component mbText = Component.literal("(" + recipe.mbPerTick() + ")");

        int centerX = 59;
        guiGraphics.drawString(font, rateText,
                centerX - font.width(rateText) / 2, 45, 0x808080, false);
        guiGraphics.drawString(font, mbText,
                centerX - font.width(mbText) / 2, 54, 0x606060, false);
    }
}