package com.misterd.smallprogressions.gui.custom;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class LinenSackScreen extends AbstractContainerScreen<LinenSackMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath("smallprogressions", "textures/gui/linen_sack_gui.png");

    public LinenSackScreen(LinenSackMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 171);
        this.inventoryLabelY = 171 - 94;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                this.leftPos, this.topPos, 0.0F, 0.0F,
                this.imageWidth, this.imageHeight, 256, 256);
        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }
}