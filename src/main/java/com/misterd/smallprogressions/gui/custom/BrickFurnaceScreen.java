package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BrickFurnaceScreen extends AbstractContainerScreen<BrickFurnaceMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/brick_furnace_gui.png");

    private static final int ARROW_X = 83;
    private static final int ARROW_Y = 37;
    private static final int ARROW_WIDTH = 22;
    private static final int ARROW_HEIGHT = 15;

    private static final int FLAME_X = 63;
    private static final int FLAME_Y = 38;
    private static final int FLAME_WIDTH = 14;
    private static final int FLAME_HEIGHT = 14;

    public BrickFurnaceScreen(BrickFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 171;
        this.inventoryLabelY = this.imageHeight - 96;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderBurnProgress(guiGraphics, x, y);

        renderSmeltingProgress(guiGraphics, x, y);
    }

    private void renderBurnProgress(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isBurning()) {
            int fuelTime = menu.getFuelTime();
            int maxFuelTime = menu.getMaxFuelTime();

            if (maxFuelTime > 0) {
                int flameHeight = (fuelTime * FLAME_HEIGHT) / maxFuelTime;

                int yOffset = FLAME_HEIGHT - flameHeight;

                guiGraphics.blit(GUI_TEXTURE,
                        x + FLAME_X, y + FLAME_Y + yOffset,
                        176, yOffset,
                        FLAME_WIDTH, flameHeight);
            }
        }
    }

    private void renderSmeltingProgress(GuiGraphics guiGraphics, int x, int y) {
        int progress = menu.getProgress();
        int maxProgress = menu.getMaxProgress();

        if (progress > 0 && maxProgress > 0) {
            int arrowWidth = (progress * ARROW_WIDTH) / maxProgress;

            guiGraphics.blit(GUI_TEXTURE,
                    x + ARROW_X, y + ARROW_Y,
                    176, 14,
                    arrowWidth, ARROW_HEIGHT);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}