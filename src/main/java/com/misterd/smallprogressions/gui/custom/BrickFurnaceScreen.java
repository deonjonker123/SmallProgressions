package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.SmallProgressions;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class BrickFurnaceScreen extends AbstractContainerScreen<BrickFurnaceMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/brick_furnace_gui.png");

    private static final int ARROW_X = 83;
    private static final int ARROW_Y = 37;
    private static final int ARROW_WIDTH = 22;
    private static final int ARROW_HEIGHT = 15;
    private static final int FLAME_X = 63;
    private static final int FLAME_Y = 38;
    private static final int FLAME_WIDTH = 14;
    private static final int FLAME_HEIGHT = 14;

    public BrickFurnaceScreen(BrickFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 171);
        this.inventoryLabelY = 171 - 96;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                this.leftPos, this.topPos, 0.0F, 0.0F,
                this.imageWidth, this.imageHeight, 256, 256);

        if (menu.isBurning()) {
            int fuelTime = menu.getFuelTime();
            int maxFuelTime = menu.getMaxFuelTime();
            if (maxFuelTime > 0) {
                int flameHeight = (fuelTime * FLAME_HEIGHT) / maxFuelTime;
                int yOffset = FLAME_HEIGHT - flameHeight;
                graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                        this.leftPos + FLAME_X, this.topPos + FLAME_Y + yOffset,
                        176.0F, (float) yOffset, FLAME_WIDTH, flameHeight, 256, 256);
            }
        }

        int progress = menu.getProgress();
        int maxProgress = menu.getMaxProgress();
        if (progress > 0 && maxProgress > 0) {
            int arrowWidth = (progress * ARROW_WIDTH) / maxProgress;
            graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                    this.leftPos + ARROW_X, this.topPos + ARROW_Y,
                    176.0F, 14.0F, arrowWidth, ARROW_HEIGHT, 256, 256);
        }

        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }
}