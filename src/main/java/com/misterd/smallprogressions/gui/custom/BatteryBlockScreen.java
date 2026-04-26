package com.misterd.smallprogressions.gui.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BatteryBlockScreen extends AbstractContainerScreen<BatteryBlockMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath("smallprogressions", "textures/gui/battery_gui.png");

    public BatteryBlockScreen(BatteryBlockMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 167);
        this.inventoryLabelY = 167 - 94;
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor gfx, int mouseX, int mouseY, float partialTick) {
        gfx.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, leftPos, topPos, 0.0F, 0.0F, imageWidth, imageHeight, 256, 256);

        long energyStored = menu.getEnergyStored();
        long maxEnergy = menu.getMaxEnergyStored();
        if (maxEnergy > 0 && energyStored > 0) {
            int fillWidth = (int)(135.0D * (double) energyStored / (double) maxEnergy);
            if (fillWidth > 0) {
                gfx.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, leftPos + 8, topPos + 20, 0.0F, 167.0F, fillWidth, 47, 256, 256);
            }
        }

        gfx.text(font, title, titleLabelX, titleLabelY, 0x404040, false);
        gfx.text(font, playerInventoryTitle, inventoryLabelX, inventoryLabelY, 0x404040, false);

        Component rfText = Component.literal(formatRF(energyStored) + " / " + formatRF(maxEnergy) + " RF");
        int rfTextWidth = font.width(rfText);
        gfx.text(font, rfText, leftPos + 75 - rfTextWidth / 2, topPos + 31, 0xFFFFFF, true);

        if (maxEnergy > 0) {
            double pct = (double) energyStored * 100.0D / (double) maxEnergy;
            Component pctText = Component.literal(String.format("%.1f%%", pct));
            int pctWidth = font.width(pctText);
            gfx.text(font, pctText, leftPos + 75 - pctWidth / 2, topPos + 43, 0xAAAAAA, true);
        }

        super.extractContents(gfx, mouseX, mouseY, partialTick);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor gfx, int mouseX, int mouseY) {
        super.extractTooltip(gfx, mouseX, mouseY);

        long energyStored = menu.getEnergyStored();
        long maxEnergy = menu.getMaxEnergyStored();
        NumberFormat fmt = NumberFormat.getNumberInstance(Locale.US);

        if (mouseX >= leftPos + 8 && mouseX <= leftPos + 143 && mouseY >= topPos + 20 && mouseY <= topPos + 67) {
            double pct = maxEnergy > 0 ? (double) energyStored * 100.0D / (double) maxEnergy : 0.0D;
            List<Component> tooltip = new ArrayList<>();
            tooltip.add(Component.translatable("gui.smallprogressions.battery_title").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(fmt.format(energyStored) + " / " + fmt.format(maxEnergy) + " RF").withStyle(ChatFormatting.WHITE));
            tooltip.add(Component.literal(String.format("%.1f%%", pct)).withStyle(ChatFormatting.GRAY));
            gfx.setTooltipForNextFrame(font, tooltip.stream().map(Component::getVisualOrderText).toList(), mouseX, mouseY);
        }
    }

    private static String formatRF(long value) {
        if (value >= 1_000_000_000L) return String.format("%.1fB", value / 1_000_000_000.0D);
        if (value >= 1_000_000L) return String.format("%.1fM", value / 1_000_000.0D);
        if (value >= 1_000L) return String.format("%.1fK", value / 1_000.0D);
        return String.valueOf(value);
    }
}