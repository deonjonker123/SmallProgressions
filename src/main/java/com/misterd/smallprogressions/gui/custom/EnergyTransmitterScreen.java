package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.network.TransmitterTogglePacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EnergyTransmitterScreen extends AbstractContainerScreen<EnergyTransmitterMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath("smallprogressions", "textures/gui/transmitter_gui.png");

    private static final int TOGGLE_PRIVATE_X = 153;
    private static final int TOGGLE_PUBLIC_X = 161;
    private static final int TOGGLE_Y = 71;
    private static final int TOGGLE_W = 6;
    private static final int TOGGLE_H = 9;
    private static final int TOGGLE_U = 135;
    private static final int TOGGLE_V = 167;
    private static final int CHARGE_PRIVATE_X = 117;
    private static final int CHARGE_PUBLIC_X = 125;
    private static final int CHARGE_Y = 71;

    public EnergyTransmitterScreen(EnergyTransmitterMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 167);
        this.inventoryLabelY = 167 - 96;
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor gfx, int mouseX, int mouseY, float partialTick) {
        gfx.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, leftPos, topPos, 0.0F, 0.0F, imageWidth, imageHeight, 256, 256);

        long poolStored = menu.getPoolStored();
        long maxPool = menu.getMaxPool();
        if (maxPool > 0 && poolStored > 0) {
            int fillWidth = (int)(135.0D * (double) poolStored / (double) maxPool);
            if (fillWidth > 0) {
                gfx.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, leftPos + 8, topPos + 20, 0.0F, 167.0F, fillWidth, 47, 256, 256);
            }
        }

        boolean isPublic = menu.isPublic();
        int toggleX = leftPos + (isPublic ? TOGGLE_PUBLIC_X : TOGGLE_PRIVATE_X);
        gfx.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, toggleX, topPos + TOGGLE_Y, (float) TOGGLE_U, (float) TOGGLE_V, TOGGLE_W, TOGGLE_H, 256, 256);

        boolean chargeInventory = menu.isChargeInventory();
        int chargeToggleX = leftPos + (chargeInventory ? CHARGE_PUBLIC_X : CHARGE_PRIVATE_X);
        gfx.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, chargeToggleX, topPos + CHARGE_Y, (float) TOGGLE_U, (float) TOGGLE_V, TOGGLE_W, TOGGLE_H, 256, 256);

        gfx.text(font, title, titleLabelX, titleLabelY, 0x404040, false);

        Component rfText = Component.literal(formatRF(poolStored) + " / " + formatRF(maxPool) + " RF");
        int rfTextWidth = font.width(rfText);
        gfx.text(font, rfText, leftPos + 73 - rfTextWidth / 2, topPos + 32, 0xFFFFFF, true);

        if (maxPool > 0) {
            double pct = (double) poolStored * 100.0D / (double) maxPool;
            Component pctText = Component.literal(String.format("%.1f%%", pct));
            int pctWidth = font.width(pctText);
            gfx.text(font, pctText, leftPos + 73 - pctWidth / 2, topPos + 44, 0xAAAAAA, true);
        }

        super.extractContents(gfx, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (event.button() == 0) {
            int mx = (int) event.x();
            int my = (int) event.y();

            int toggleLeft = leftPos + TOGGLE_PRIVATE_X;
            int toggleRight = leftPos + TOGGLE_PUBLIC_X + TOGGLE_W;
            int toggleTop = topPos + TOGGLE_Y;
            int toggleBottom = topPos + TOGGLE_Y + TOGGLE_H;

            if (mx >= toggleLeft && mx <= toggleRight && my >= toggleTop && my <= toggleBottom) {
                boolean newPublic = !menu.isPublic();
                menu.setPublic(newPublic);
                ClientPacketDistributor.sendToServer(new TransmitterTogglePacket(menu.blockEntity.getBlockPos(), newPublic, menu.isChargeInventory()));
                return true;
            }

            int chargeLeft = leftPos + CHARGE_PRIVATE_X;
            int chargeRight = leftPos + CHARGE_PUBLIC_X + TOGGLE_W;
            int chargeTop = topPos + CHARGE_Y;
            int chargeBottom = topPos + CHARGE_Y + TOGGLE_H;

            if (mx >= chargeLeft && mx <= chargeRight && my >= chargeTop && my <= chargeBottom) {
                boolean newCharge = !menu.isChargeInventory();
                menu.setChargeInventory(newCharge);
                ClientPacketDistributor.sendToServer(new TransmitterTogglePacket(menu.blockEntity.getBlockPos(), menu.isPublic(), newCharge));
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor gfx, int mouseX, int mouseY) {
        super.extractTooltip(gfx, mouseX, mouseY);

        long poolStored = menu.getPoolStored();
        long maxPool = menu.getMaxPool();
        NumberFormat fmt = NumberFormat.getNumberInstance(Locale.US);

        if (mouseX >= leftPos + 6 && mouseX <= leftPos + 143 && mouseY >= topPos + 19 && mouseY <= topPos + 67) {
            double pct = maxPool > 0 ? (double) poolStored * 100.0D / (double) maxPool : 0.0D;
            List<Component> tooltip = new ArrayList<>();
            tooltip.add(Component.translatable("gui.smallprogressions.transmitter_pool_title").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(fmt.format(poolStored) + " / " + fmt.format(maxPool) + " RF").withStyle(ChatFormatting.WHITE));
            tooltip.add(Component.literal(String.format("%.1f%%", pct)).withStyle(ChatFormatting.GRAY));
            gfx.setTooltipForNextFrame(font, tooltip.stream().map(Component::getVisualOrderText).toList(), mouseX, mouseY);
            return;
        }

        int toggleLeft = leftPos + TOGGLE_PRIVATE_X;
        int toggleRight = leftPos + TOGGLE_PUBLIC_X + TOGGLE_W;
        int toggleTop = topPos + TOGGLE_Y;
        int toggleBottom = topPos + TOGGLE_Y + TOGGLE_H;

        if (mouseX >= toggleLeft && mouseX <= toggleRight && mouseY >= toggleTop && mouseY <= toggleBottom) {
            List<Component> tooltip = new ArrayList<>();
            if (menu.isPublic()) {
                tooltip.add(Component.translatable("gui.smallprogressions.transmitter_toggle_public").withStyle(ChatFormatting.GREEN));
                tooltip.add(Component.translatable("gui.smallprogressions.transmitter_toggle_public_desc").withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.add(Component.translatable("gui.smallprogressions.transmitter_toggle_private").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable("gui.smallprogressions.transmitter_toggle_private_desc").withStyle(ChatFormatting.GRAY));
            }
            gfx.setTooltipForNextFrame(font, tooltip.stream().map(Component::getVisualOrderText).toList(), mouseX, mouseY);
            return;
        }

        int chargeLeft = leftPos + CHARGE_PRIVATE_X;
        int chargeRight = leftPos + CHARGE_PUBLIC_X + TOGGLE_W;
        int chargeTop = topPos + CHARGE_Y;
        int chargeBottom = topPos + CHARGE_Y + TOGGLE_H;

        if (mouseX >= chargeLeft && mouseX <= chargeRight && mouseY >= chargeTop && mouseY <= chargeBottom) {
            List<Component> tooltip = new ArrayList<>();
            if (menu.isChargeInventory()) {
                tooltip.add(Component.translatable("gui.smallprogressions.transmitter_charge_inventory_on").withStyle(ChatFormatting.GOLD));
                tooltip.add(Component.translatable("gui.smallprogressions.transmitter_charge_inventory_on_desc").withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.add(Component.translatable("gui.smallprogressions.transmitter_charge_inventory_off").withStyle(ChatFormatting.GOLD));
            }
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