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
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;

public class LavaInfusedStoneCategory implements IRecipeCategory<LavaInfusedRecipe> {

    public static final RecipeType<LavaInfusedRecipe> RECIPE_TYPE =
            RecipeType.create(SmallProgressions.MODID, "lava_infused_stone", LavaInfusedRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrowAnimated;

    public LavaInfusedStoneCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/lavastone_jei_gui.png"),
                0, 0, 118, 60
        ).build();

        this.icon = helper.createDrawableItemStack(
                new ItemStack(SPBlocks.LAVA_INFUSED_STONE.get())
        );

        this.arrowAnimated = helper.drawableBuilder(
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/lavastone_jei_gui.png"),
                118, 0, 22, 15
        ).buildAnimated(20, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<LavaInfusedRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.smallprogressions.lava_infused_stone");
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
    public void setRecipe(IRecipeLayoutBuilder builder, LavaInfusedRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.CATALYST, 29, 21)
                .addItemStack(recipe.catalyst());

        builder.addInvisibleIngredients(RecipeIngredientRole.INPUT)
                .addItemStack(recipe.input());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 21)
                .addItemStack(recipe.output());
    }

    @Override
    public void draw(LavaInfusedRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        drawWaterBlock(guiGraphics, 11, 21, 18, 18);

        arrowAnimated.draw(guiGraphics, 57, 23);

        var font = Minecraft.getInstance().font;
        Component line1 = Component.translatable("jei.smallprogressions.lava_infused_stone.line1");
        Component line2 = Component.translatable("jei.smallprogressions.lava_infused_stone.line2");

        int y1 = 45;
        int y2 = 54;

        guiGraphics.drawString(font, line1,
                59 - font.width(line1) / 2, y1, 0x606060, false);
        guiGraphics.drawString(font, line2,
                59 - font.width(line2) / 2, y2, 0x606060, false);
    }

    private void drawWaterBlock(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        Minecraft minecraft = Minecraft.getInstance();

        TextureAtlasSprite waterSprite = minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(ResourceLocation.withDefaultNamespace("block/water_still"));

        int waterColor = 0xFF3F76E4;

        int alpha = (waterColor >> 24) & 0xFF;
        int red = (waterColor >> 16) & 0xFF;
        int green = (waterColor >> 8) & 0xFF;
        int blue = waterColor & 0xFF;

        guiGraphics.blit(x, y, 0, width, height, waterSprite, red / 255f, green / 255f, blue / 255f, alpha / 255f);
    }
}