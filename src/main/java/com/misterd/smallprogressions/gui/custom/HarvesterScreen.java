package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.network.ConfigPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class HarvesterScreen extends AbstractContainerScreen<HarvesterMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath("smallprogressions", "textures/gui/harvester_gui.png");

    private boolean requiresRedstone = false;

    public HarvesterScreen(HarvesterMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 135;
        this.imageWidth = 176;
        this.inventoryLabelY = this.imageHeight - 94;
        this.syncFromBlockEntity();
    }

    private void syncFromBlockEntity() {
        this.requiresRedstone = this.menu.requiresRedstone();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        if (mouseX >= x + 154 && mouseX <= x + 166 && mouseY >= y + 21 && mouseY <= y + 33) {
            this.requiresRedstone = !this.requiresRedstone;
            PacketDistributor.sendToServer(
                    new ConfigPacket(
                            ConfigPacket.ConfigTarget.HARVESTER,
                            this.menu.blockEntity.getBlockPos(),
                            ConfigPacket.ConfigType.HARVESTER_REDSTONE_MODE,
                            0,
                            this.requiresRedstone
                    )
            );
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        this.renderRedstoneToggle(guiGraphics, x, y);
    }

    private void renderRedstoneToggle(GuiGraphics guiGraphics, int x, int y) {
        ResourceLocation sprite = this.requiresRedstone
                ? ResourceLocation.fromNamespaceAndPath("smallprogressions", "redstone_required_btn")
                : ResourceLocation.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn");

        guiGraphics.blitSprite(sprite, x + 154, y + 21, 12, 12);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.syncFromBlockEntity();
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        this.renderCustomTooltips(guiGraphics, mouseX, mouseY);
    }

    private void renderCustomTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        if (mouseX >= x + 154 && mouseX <= x + 166 && mouseY >= y + 21 && mouseY <= y + 33) {
            Component tooltipText = this.requiresRedstone
                    ? Component.translatable("tooltip.smallprogressions.advanced_item_collector.redstone_required")
                    : Component.translatable("tooltip.smallprogressions.advanced_item_collector.redstone_not_required");
            guiGraphics.renderTooltip(this.font, tooltipText, mouseX, mouseY);
        }
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.syncFromBlockEntity();
    }
}