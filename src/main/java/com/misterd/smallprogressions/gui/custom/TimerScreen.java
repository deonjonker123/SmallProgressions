package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.blockentity.custom.TimerBlockEntity;
import com.misterd.smallprogressions.network.SetIntervalPacket;
import com.misterd.smallprogressions.network.SetRunningPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class TimerScreen extends AbstractContainerScreen<TimerMenu> {
    private static final Identifier BG_TEXTURE = Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "textures/gui/channel_gui.png");

    private static final WidgetSprites SPRITES_RUNNING = new WidgetSprites(
            Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "off_btn"),
            Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "off_btn"),
            Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "off_btn"),
            Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "off_btn")
    );

    private static final WidgetSprites SPRITES_STOPPED = new WidgetSprites(
            Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "on_btn"),
            Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "on_btn"),
            Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "on_btn"),
            Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "on_btn")
    );

    private static final int BG_W = 172;
    private static final int BG_H = 92;
    private static final int EB_X = 8;
    private static final int EB_Y = 48;
    private static final int EB_W = 69;
    private static final int EB_H = 10;
    private static final int BTN_H = 20;
    private static final int BTN_GUTTER = 2;
    private static final int INC_X = 7;
    private static final int INC_Y = 22;
    private static final int DEC_X = 7;
    private static final int DEC_Y = 62;
    private static final int RSC_X = 101;
    private static final int RSC_Y = 22;
    private static final int TOGGLE_X = 154;
    private static final int TOGGLE_Y = 74;
    private static final int TOGGLE_W = 12;
    private static final int TOGGLE_H = 12;

    private EditBox intervalBox;
    private boolean running;
    private ImageButton toggleBtn;

    public TimerScreen(TimerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, BG_W, BG_H);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = Integer.MAX_VALUE;

        if (intervalBox == null) running = menu.isRunning();

        intervalBox = new EditBox(font, leftPos + EB_X, topPos + EB_Y, EB_W, EB_H, Component.translatable("gui.smallprogressions.timer.interval_box"));
        intervalBox.setFilter(s -> s.isEmpty() || s.matches("\\d+"));
        intervalBox.setMaxLength(5);
        intervalBox.setValue(String.valueOf(menu.getInterval()));
        intervalBox.setBordered(false);
        addRenderableWidget(intervalBox);

        int incX = leftPos + INC_X;
        for (int delta : new int[]{1, 5, 10}) {
            final int d = delta;
            Component label = Component.literal("+" + delta);
            int w = font.width(label) + 4;
            addRenderableWidget(new SPButton(incX, topPos + INC_Y, w, BTN_H, label, btn -> adjustInterval(d)));
            incX += w + BTN_GUTTER;
        }

        int decX = leftPos + DEC_X;
        for (int delta : new int[]{1, 5, 10}) {
            final int d = delta;
            Component label = Component.literal("-" + delta);
            int w = font.width(label) + 4;
            addRenderableWidget(new SPButton(decX, topPos + DEC_Y, w, BTN_H, label, btn -> adjustInterval(-d)));
            decX += w + BTN_GUTTER;
        }

        Component resetLabel = Component.translatable("gui.smallprogressions.timer.reset");
        Component setLabel = Component.translatable("gui.smallprogressions.timer.set");
        Component cancelLabel = Component.translatable("gui.smallprogressions.timer.cancel");
        int rscW = font.width(cancelLabel) + 4;
        int rscX = leftPos + RSC_X;
        int rscY = topPos + RSC_Y;

        addRenderableWidget(new SPButton(rscX, rscY, rscW, BTN_H, resetLabel, btn -> onReset()));
        addRenderableWidget(new SPButton(rscX, rscY + BTN_H + BTN_GUTTER, rscW, BTN_H, setLabel, btn -> onSet()));
        addRenderableWidget(new SPButton(rscX, rscY + (BTN_H + BTN_GUTTER) * 2, rscW, BTN_H, cancelLabel, btn -> onCancel()));

        toggleBtn = new ImageButton(leftPos + TOGGLE_X, topPos + TOGGLE_Y, TOGGLE_W, TOGGLE_H, running ? SPRITES_STOPPED : SPRITES_RUNNING, btn -> onToggle());
        addRenderableWidget(toggleBtn);
    }

    private void adjustInterval(int delta) {
        intervalBox.setValue(String.valueOf(Math.clamp(parseBox() + delta, TimerBlockEntity.MIN_INTERVAL, TimerBlockEntity.MAX_INTERVAL)));
    }

    private void onReset() { intervalBox.setValue(String.valueOf(TimerBlockEntity.DEFAULT_INTERVAL)); }
    private void onCancel() { this.onClose(); }

    private void onSet() {
        ClientPacketDistributor.sendToServer(new SetIntervalPacket(menu.getBlockPos(), parseBox()));
        this.onClose();
    }

    private void onToggle() {
        running = !running;
        ClientPacketDistributor.sendToServer(new SetRunningPacket(menu.getBlockPos(), running));
        init();
    }

    private int parseBox() {
        try { return Math.clamp(Integer.parseInt(intervalBox.getValue()), TimerBlockEntity.MIN_INTERVAL, TimerBlockEntity.MAX_INTERVAL); }
        catch (NumberFormatException e) { return TimerBlockEntity.DEFAULT_INTERVAL; }
    }

    @Override
    public void extractContents(GuiGraphicsExtractor gfx, int mouseX, int mouseY, float partialTick) {
        gfx.blit(RenderPipelines.GUI_TEXTURED, BG_TEXTURE, leftPos, topPos, 0.0F, 0.0F, BG_W, BG_H, 256, 256);
        super.extractContents(gfx, mouseX, mouseY, partialTick);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor gfx, int mouseX, int mouseY) {
        if (toggleBtn != null && toggleBtn.isHoveredOrFocused()) {
            gfx.setTooltipForNextFrame(font, Component.translatable(running ? "gui.smallprogressions.timer.running" : "gui.smallprogressions.timer.stopped"), mouseX, mouseY);
        }
        if (intervalBox != null && intervalBox.isHoveredOrFocused()) {
            gfx.setTooltipForNextFrame(font, Component.translatable("gui.smallprogressions.timer.interval_box.tooltip").withStyle(ChatFormatting.GOLD), mouseX, mouseY);
        }
        super.extractTooltip(gfx, mouseX, mouseY);
    }
}