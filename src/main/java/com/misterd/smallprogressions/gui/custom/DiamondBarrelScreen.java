package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.SmallProgressions;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class DiamondBarrelScreen extends AbstractContainerScreen<DiamondBarrelMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/diamond_barrel_gui.png");

    public DiamondBarrelScreen(DiamondBarrelMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 256, 256);
        this.inventoryLabelY = 256 - 93;
        this.inventoryLabelX = 256 - 209;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                this.leftPos, this.topPos, 0.0F, 0.0F,
                this.imageWidth, this.imageHeight, 256, 256);
        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }
}