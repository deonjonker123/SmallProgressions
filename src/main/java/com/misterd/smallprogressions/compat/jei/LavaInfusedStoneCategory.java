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
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.AtlasIds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class LavaInfusedStoneCategory implements IRecipeCategory<LavaInfusedRecipe> {

    public static final RecipeType<LavaInfusedRecipe> RECIPE_TYPE =
            RecipeType.create(SmallProgressions.MODID, "lava_infused_stone", LavaInfusedRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrowAnimated;

    public LavaInfusedStoneCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(
                Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/lavastone_jei_gui.png"),
                0, 0, 118, 60
        ).build();
        this.icon = helper.createDrawableItemStack(new ItemStack(SPBlocks.LAVA_INFUSED_STONE.get()));
        this.arrowAnimated = helper.drawableBuilder(
                Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/lavastone_jei_gui.png"),
                118, 0, 22, 15
        ).buildAnimated(20, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<LavaInfusedRecipe> getRecipeType() { return RECIPE_TYPE; }

    @Override
    public Component getTitle() { return Component.translatable("jei.smallprogressions.lava_infused_stone"); }

    @Override
    public IDrawable getIcon() { return icon; }

    @Override
    public int getWidth() { return 118; }

    @Override
    public int getHeight() { return 60; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, LavaInfusedRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 29, 21).addItemStack(recipe.catalyst());
        builder.addInvisibleIngredients(RecipeIngredientRole.INPUT).addItemStack(recipe.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 21).addItemStack(recipe.output());
    }

    @Override
    public void draw(LavaInfusedRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        drawWaterBlock(guiGraphics, 11, 21, 18, 18);
        arrowAnimated.draw(guiGraphics, 57, 23);
        var font = Minecraft.getInstance().font;
        Component line1 = Component.translatable("jei.smallprogressions.lava_infused_stone.line1");
        Component line2 = Component.translatable("jei.smallprogressions.lava_infused_stone.line2");
        guiGraphics.text(font, line1, 59 - font.width(line1) / 2, 45, 0x606060, false);
        guiGraphics.text(font, line2, 59 - font.width(line2) / 2, 54, 0x606060, false);
    }

    private void drawWaterBlock(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height) {
        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasManager()
                .getAtlasOrThrow(AtlasIds.BLOCKS)
                .getSprite(Identifier.withDefaultNamespace("block/water_still"));
        guiGraphics.blitSprite(net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED, sprite, x, y, width, height, 0xFF3F76E4);
    }
}