package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.network.ConfigPacket;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.network.PacketDistributor;

public class HarvesterScreen extends AbstractContainerScreen<HarvesterMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath("smallprogressions", "textures/gui/harvester_gui.png");

    private boolean requiresRedstone = false;

    public HarvesterScreen(HarvesterMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 135);
        this.inventoryLabelY = 135 - 94;
        this.requiresRedstone = menu.requiresRedstone();
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (event.button() == 0) {
            int mx = (int) event.x();
            int my = (int) event.y();
            if (isOver(154, 21, 12, 12, mx, my)) {
                this.requiresRedstone = !this.requiresRedstone;
                ClientPacketDistributor.sendToServer(new ConfigPacket(
                        ConfigPacket.ConfigTarget.HARVESTER,
                        this.menu.blockEntity.getBlockPos(),
                        ConfigPacket.ConfigType.HARVESTER_REDSTONE_MODE,
                        0,
                        this.requiresRedstone
                ));
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                this.leftPos, this.topPos, 0.0F, 0.0F,
                this.imageWidth, this.imageHeight, 256, 256);

        Identifier sprite = this.requiresRedstone
                ? Identifier.fromNamespaceAndPath("smallprogressions", "redstone_required_btn")
                : Identifier.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn");
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, this.leftPos + 154, this.topPos + 21, 12, 12);

        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        if (isOver(154, 21, 12, 12, mouseX, mouseY)) {
            Component tooltip = this.requiresRedstone
                    ? Component.translatable("tooltip.smallprogressions.advanced_item_collector.redstone_required")
                    : Component.translatable("tooltip.smallprogressions.advanced_item_collector.redstone_not_required");
            graphics.setTooltipForNextFrame(this.font, tooltip, mouseX, mouseY);
            return;
        }
        super.extractTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.requiresRedstone = this.menu.requiresRedstone();
    }

    private boolean isOver(int wx, int wy, int ww, int wh, int mx, int my) {
        return mx >= this.leftPos + wx && mx <= this.leftPos + wx + ww
                && my >= this.topPos + wy && my <= this.topPos + wy + wh;
    }
}