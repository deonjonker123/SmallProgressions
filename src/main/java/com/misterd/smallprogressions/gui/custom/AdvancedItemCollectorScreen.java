package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.client.renderer.AdvancedItemCollectorWireframeRenderer;
import com.misterd.smallprogressions.network.ConfigPacket;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.network.PacketDistributor;

public class AdvancedItemCollectorScreen extends AbstractContainerScreen<AdvancedItemCollectorMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath("smallprogressions", "textures/gui/advanced_item_collector_gui.png");

    private static final WidgetSprites REDUCE_OFFSET_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "reduce_offset_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "reduce_offset_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "reduce_offset_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "reduce_offset_btn")
    );
    private static final WidgetSprites INCREASE_OFFSET_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "increase_offset_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "increase_offset_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "increase_offset_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "increase_offset_btn")
    );
    private static final WidgetSprites TOGGLE_WIREFRAME_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "toggle_zone_wireframe_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "toggle_zone_wireframe_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "toggle_zone_wireframe_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "toggle_zone_wireframe_btn")
    );
    private static final WidgetSprites RESET_OFFSET_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "collection_zone_offset_reset_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "collection_zone_offset_reset_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "collection_zone_offset_reset_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "collection_zone_offset_reset_btn")
    );

    private int downUpOffset = 0;
    private int northSouthOffset = 0;
    private int eastWestOffset = 0;
    private boolean requiresRedstone = false;
    private boolean isAllowMode = true;
    private boolean wireframeEnabled = false;

    public AdvancedItemCollectorScreen(AdvancedItemCollectorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 171);
        this.inventoryLabelY = 171 - 94;
        syncFromMenu();
    }

    private void syncFromMenu() {
        this.downUpOffset = this.menu.getDownUpOffset();
        this.northSouthOffset = this.menu.getNorthSouthOffset();
        this.eastWestOffset = this.menu.getEastWestOffset();
        this.requiresRedstone = this.menu.requiresRedstone();
        this.isAllowMode = this.menu.isAllowMode();
        this.wireframeEnabled = this.menu.isWireframeEnabled();
    }

    @Override
    protected void init() {
        super.init();
        clearWidgets();
        addOffsetButtons();
        addWireframeButton();
        addResetButton();
    }

    private void addOffsetButtons() {
        addButton("downUp", -1, 83, 18, REDUCE_OFFSET_SPRITES, "tooltip.smallprogressions.advanced_item_collector.offset.down_up.decrease");
        addButton("downUp",  1, 119, 18, INCREASE_OFFSET_SPRITES, "tooltip.smallprogressions.advanced_item_collector.offset.down_up.increase");
        addButton("northSouth", -1, 83, 40, REDUCE_OFFSET_SPRITES, "tooltip.smallprogressions.advanced_item_collector.offset.north_south.decrease");
        addButton("northSouth",  1, 119, 40, INCREASE_OFFSET_SPRITES, "tooltip.smallprogressions.advanced_item_collector.offset.north_south.increase");
        addButton("eastWest", -1, 83, 62, REDUCE_OFFSET_SPRITES, "tooltip.smallprogressions.advanced_item_collector.offset.east_west.decrease");
        addButton("eastWest",  1, 119, 62, INCREASE_OFFSET_SPRITES, "tooltip.smallprogressions.advanced_item_collector.offset.east_west.increase");
    }

    private void addButton(String axis, int delta, int bx, int by, WidgetSprites sprites, String tooltipKey) {
        ImageButton btn = new ImageButton(this.leftPos + bx, this.topPos + by, 10, 10, sprites, b -> adjustOffset(axis, delta));
        btn.setTooltip(Tooltip.create(Component.translatable(tooltipKey)));
        addRenderableWidget(btn);
    }

    private void addWireframeButton() {
        ImageButton btn = new ImageButton(this.leftPos + 101, this.topPos + 74, 10, 10, TOGGLE_WIREFRAME_SPRITES, b -> toggleWireframe());
        btn.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.wireframe_toggle")));
        addRenderableWidget(btn);
    }

    private void addResetButton() {
        ImageButton btn = new ImageButton(this.leftPos + 154, this.topPos + 39, 12, 12, RESET_OFFSET_SPRITES, b -> resetAllOffsets());
        btn.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.offset.reset_all")));
        addRenderableWidget(btn);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (event.button() == 0) {
            int mx = (int) event.x();
            int my = (int) event.y();
            if (isOver(154, 17, 12, 12, mx, my)) {
                this.requiresRedstone = !this.requiresRedstone;
                ClientPacketDistributor.sendToServer(new ConfigPacket(
                        ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR,
                        this.menu.blockEntity.getBlockPos(),
                        ConfigPacket.ConfigType.ADVANCED_COLLECTOR_REDSTONE_MODE,
                        0, this.requiresRedstone));
                return true;
            }
            if (isOver(154, 61, 12, 12, mx, my)) {
                this.isAllowMode = !this.isAllowMode;
                ClientPacketDistributor.sendToServer(new ConfigPacket(
                        ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR,
                        this.menu.blockEntity.getBlockPos(),
                        ConfigPacket.ConfigType.ADVANCED_COLLECTOR_FILTER_MODE,
                        0, this.isAllowMode));
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

        Identifier redstoneSprite = this.requiresRedstone
                ? Identifier.fromNamespaceAndPath("smallprogressions", "redstone_required_btn")
                : Identifier.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn");
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, redstoneSprite, this.leftPos + 154, this.topPos + 17, 12, 12);

        Identifier filterSprite = this.isAllowMode
                ? Identifier.fromNamespaceAndPath("smallprogressions", "allow_btn")
                : Identifier.fromNamespaceAndPath("smallprogressions", "block_btn");
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, filterSprite, this.leftPos + 154, this.topPos + 61, 12, 12);

        renderOffsetValues(graphics);

        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }

    private void renderOffsetValues(GuiGraphicsExtractor graphics) {
        float scale = 0.65F;
        graphics.pose().pushMatrix();
        graphics.pose().scale(scale, scale);

        String duText = (downUpOffset >= 0 ? "+" : "") + downUpOffset;
        graphics.text(this.font, duText, (int) ((leftPos + 100) / scale), (int) ((topPos + 21) / scale), 0xFF000000, false);

        String nsText = (northSouthOffset >= 0 ? "+" : "") + northSouthOffset;
        graphics.text(this.font, nsText, (int) ((leftPos + 100) / scale), (int) ((topPos + 43) / scale), 0xFF000000, false);

        String ewText = (eastWestOffset >= 0 ? "+" : "") + eastWestOffset;
        graphics.text(this.font, ewText, (int) ((leftPos + 100) / scale), (int) ((topPos + 65) / scale), 0xFF000000, false);

        graphics.text(this.font, Component.translatable("gui.smallprogressions.advanced_item_collector.offset.down_up"),
                (int) ((leftPos + 90) / scale), (int) ((topPos + 12) / scale), 0xFF000000, false);
        graphics.text(this.font, Component.translatable("gui.smallprogressions.advanced_item_collector.offset.north_south"),
                (int) ((leftPos + 90) / scale), (int) ((topPos + 34) / scale), 0xFF000000, false);
        graphics.text(this.font, Component.translatable("gui.smallprogressions.advanced_item_collector.offset.east_west"),
                (int) ((leftPos + 90) / scale), (int) ((topPos + 56) / scale), 0xFF000000, false);

        graphics.pose().popMatrix();
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        if (isOver(154, 17, 12, 12, mouseX, mouseY)) {
            Component tooltip = this.requiresRedstone
                    ? Component.translatable("tooltip.smallprogressions.advanced_item_collector.redstone_required")
                    : Component.translatable("tooltip.smallprogressions.advanced_item_collector.redstone_not_required");
            graphics.setTooltipForNextFrame(this.font, tooltip, mouseX, mouseY);
            return;
        }
        if (isOver(154, 61, 12, 12, mouseX, mouseY)) {
            Component tooltip = this.isAllowMode
                    ? Component.translatable("tooltip.smallprogressions.advanced_item_collector.allow_mode")
                    : Component.translatable("tooltip.smallprogressions.advanced_item_collector.block_mode");
            graphics.setTooltipForNextFrame(this.font, tooltip, mouseX, mouseY);
            return;
        }
        super.extractTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        syncFromMenu();
    }

    private void adjustOffset(String axis, int delta) {
        ConfigPacket.ConfigType type;
        int newValue;
        switch (axis) {
            case "downUp" -> { type = ConfigPacket.ConfigType.ADVANCED_COLLECTOR_DOWN_UP_OFFSET; newValue = Math.max(-10, Math.min(10, downUpOffset + delta)); downUpOffset = newValue; }
            case "northSouth" -> { type = ConfigPacket.ConfigType.ADVANCED_COLLECTOR_NORTH_SOUTH_OFFSET; newValue = Math.max(-10, Math.min(10, northSouthOffset + delta)); northSouthOffset = newValue; }
            case "eastWest" -> { type = ConfigPacket.ConfigType.ADVANCED_COLLECTOR_EAST_WEST_OFFSET; newValue = Math.max(-10, Math.min(10, eastWestOffset + delta)); eastWestOffset = newValue; }
            default -> { return; }
        }
        ClientPacketDistributor.sendToServer(new ConfigPacket(ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR, this.menu.blockEntity.getBlockPos(), type, newValue, false));
    }

    private void toggleWireframe() {
        this.wireframeEnabled = !this.wireframeEnabled;
        AdvancedItemCollectorWireframeRenderer.toggleWireframe(this.menu.blockEntity.getBlockPos());
        init();
    }

    private void resetAllOffsets() {
        downUpOffset = northSouthOffset = eastWestOffset = 0;
        ClientPacketDistributor.sendToServer(new ConfigPacket(ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR, this.menu.blockEntity.getBlockPos(), ConfigPacket.ConfigType.ADVANCED_COLLECTOR_DOWN_UP_OFFSET, 0, false));
        ClientPacketDistributor.sendToServer(new ConfigPacket(ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR, this.menu.blockEntity.getBlockPos(), ConfigPacket.ConfigType.ADVANCED_COLLECTOR_NORTH_SOUTH_OFFSET, 0, false));
        ClientPacketDistributor.sendToServer(new ConfigPacket(ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR, this.menu.blockEntity.getBlockPos(), ConfigPacket.ConfigType.ADVANCED_COLLECTOR_EAST_WEST_OFFSET, 0, false));
    }

    private boolean isOver(int wx, int wy, int ww, int wh, int mx, int my) {
        return mx >= this.leftPos + wx && mx <= this.leftPos + wx + ww
                && my >= this.topPos + wy && my <= this.topPos + wy + wh;
    }
}