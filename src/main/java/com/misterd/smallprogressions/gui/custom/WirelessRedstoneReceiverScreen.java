package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.network.SetChannelPacket;
import com.misterd.smallprogressions.util.NineSliceButton;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.util.List;

public class WirelessRedstoneReceiverScreen extends AbstractContainerScreen<WirelessRedstoneReceiverMenu> {
    private static final Identifier BG_TEXTURE = Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/channel_gui.png");
    private static final Identifier BTN_TEXTURE = Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/btn_bg.png");

    private static final int BG_W = 172;
    private static final int BG_H = 92;
    private static final int BTN_TEX_W = 236;
    private static final int BTN_TEX_H = 24;
    private static final int CORNER = 4;
    private static final int EB_X = 8;
    private static final int EB_Y = 48;
    private static final int EB_W = 69;
    private static final int EB_H = 10;
    private static final int BTN_H = 18;
    private static final int BTN_GUTTER = 2;
    private static final int INC_X = 7;
    private static final int INC_Y = 22;
    private static final int DEC_X = 7;
    private static final int DEC_Y = 62;
    private static final int RSC_X = 101;
    private static final int RSC_Y = 22;

    private EditBox channelBox;

    public WirelessRedstoneReceiverScreen(WirelessRedstoneReceiverMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, BG_W, BG_H);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = Integer.MAX_VALUE;

        channelBox = new EditBox(font, leftPos + EB_X, topPos + EB_Y, EB_W, EB_H, Component.translatable("gui.smallprogressions.receiver.channel_box"));
        channelBox.setFilter(s -> s.isEmpty() || s.matches("\\d+"));
        channelBox.setMaxLength(10);
        int stored = menu.getChannel();
        channelBox.setValue(String.valueOf(stored == -1 ? 0 : stored));
        channelBox.setBordered(false);
        addRenderableWidget(channelBox);

        int incX = leftPos + INC_X;
        for (int delta : new int[]{1, 5, 10}) {
            final int d = delta;
            Component label = Component.literal("+" + delta);
            int w = font.width(label) + CORNER * 2;
            addRenderableWidget(new NineSliceButton(incX, topPos + INC_Y, w, BTN_H, label, btn -> adjustChannel(d), BTN_TEXTURE, BTN_TEX_W, BTN_TEX_H, CORNER));
            incX += w + BTN_GUTTER;
        }

        int decX = leftPos + DEC_X;
        for (int delta : new int[]{1, 5, 10}) {
            final int d = delta;
            Component label = Component.literal("-" + delta);
            int w = font.width(label) + CORNER * 2;
            addRenderableWidget(new NineSliceButton(decX, topPos + DEC_Y, w, BTN_H, label, btn -> adjustChannel(-d), BTN_TEXTURE, BTN_TEX_W, BTN_TEX_H, CORNER));
            decX += w + BTN_GUTTER;
        }

        Component resetLabel = Component.translatable("gui.smallprogressions.receiver.reset");
        Component setLabel = Component.translatable("gui.smallprogressions.receiver.set");
        Component cancelLabel = Component.translatable("gui.smallprogressions.receiver.cancel");
        int rscW = font.width(cancelLabel) + CORNER * 2;
        int rscX = leftPos + RSC_X;
        int rscY = topPos + RSC_Y;

        addRenderableWidget(new NineSliceButton(rscX, rscY, rscW, BTN_H, resetLabel, btn -> onReset(), BTN_TEXTURE, BTN_TEX_W, BTN_TEX_H, CORNER));
        addRenderableWidget(new NineSliceButton(rscX, rscY + BTN_H + BTN_GUTTER, rscW, BTN_H, setLabel, btn -> onSet(), BTN_TEXTURE, BTN_TEX_W, BTN_TEX_H, CORNER));
        addRenderableWidget(new NineSliceButton(rscX, rscY + (BTN_H + BTN_GUTTER) * 2, rscW, BTN_H, cancelLabel, btn -> onCancel(), BTN_TEXTURE, BTN_TEX_W, BTN_TEX_H, CORNER));
    }

    private void adjustChannel(int delta) {
        channelBox.setValue(String.valueOf(Math.max(0, parseBox() + delta)));
    }

    private void onReset() { channelBox.setValue("0"); }
    private void onCancel() { this.onClose(); }

    private void onSet() {
        ClientPacketDistributor.sendToServer(new SetChannelPacket(menu.getBlockPos(), parseBox()));
        this.onClose();
    }

    private int parseBox() {
        try { return Math.max(0, Integer.parseInt(channelBox.getValue())); }
        catch (NumberFormatException e) { return 0; }
    }

    @Override
    public void extractContents(GuiGraphicsExtractor gfx, int mouseX, int mouseY, float partialTick) {
        gfx.blit(RenderPipelines.GUI_TEXTURED, BG_TEXTURE, leftPos, topPos, 0.0F, 0.0F, BG_W, BG_H, 256, 256);
        super.extractContents(gfx, mouseX, mouseY, partialTick);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor gfx, int mouseX, int mouseY) {
        if (channelBox != null && channelBox.isHoveredOrFocused()) {
            gfx.setTooltipForNextFrame(font, Component.translatable("gui.smallprogressions.receiver.channel_box.tooltip.line1").withStyle(ChatFormatting.GOLD), mouseX, mouseY);
        }
        super.extractTooltip(gfx, mouseX, mouseY);
    }
}