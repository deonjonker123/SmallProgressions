package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.client.renderer.AdvancedItemCollectorWireframeRenderer;
import com.misterd.smallprogressions.network.ConfigPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class AdvancedItemCollectorScreen extends AbstractContainerScreen<AdvancedItemCollectorMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath("smallprogressions", "textures/gui/advanced_item_collector_gui.png");

    private static final WidgetSprites REDUCE_OFFSET_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "reduce_offset_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "reduce_offset_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "reduce_offset_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "reduce_offset_btn")
    );

    private static final WidgetSprites INCREASE_OFFSET_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "increase_offset_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "increase_offset_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "increase_offset_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "increase_offset_btn")
    );

    private static final WidgetSprites TOGGLE_WIREFRAME_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "toggle_zone_wireframe_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "toggle_zone_wireframe_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "toggle_zone_wireframe_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "toggle_zone_wireframe_btn")
    );

    private static final WidgetSprites RESET_OFFSET_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "collection_zone_offset_reset_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "collection_zone_offset_reset_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "collection_zone_offset_reset_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "collection_zone_offset_reset_btn")
    );

    private static final WidgetSprites REDSTONE_NOT_REQUIRED_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn")
    );

    private static final WidgetSprites REDSTONE_REQUIRED_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "redstone_required_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "redstone_required_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "redstone_required_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "redstone_required_btn")
    );

    private static final WidgetSprites ALLOW_MODE_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "allow_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "allow_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "allow_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "allow_btn")
    );

    private static final WidgetSprites BLOCK_MODE_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "block_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "block_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "block_btn"),
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "block_btn")
    );

    private int downUpOffset = 0;
    private int northSouthOffset = 0;
    private int eastWestOffset = 0;
    private boolean requiresRedstone = false;
    private boolean isAllowMode = true;
    private boolean wireframeEnabled = false;

    public AdvancedItemCollectorScreen(AdvancedItemCollectorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 171;
        this.imageWidth = 176;
        this.inventoryLabelY = this.imageHeight - 94;
        this.syncFromBlockEntity();
    }

    private void syncFromBlockEntity() {
        this.downUpOffset = this.menu.getDownUpOffset();
        this.northSouthOffset = this.menu.getNorthSouthOffset();
        this.eastWestOffset = this.menu.getEastWestOffset();
        this.requiresRedstone = this.menu.requiresRedstone();
        this.isAllowMode = this.menu.isAllowMode();
        this.wireframeEnabled = this.menu.isWireframeEnabled();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        if (mouseX >= x + 154 && mouseX <= x + 166 && mouseY >= y + 17 && mouseY <= y + 29) {
            this.requiresRedstone = !this.requiresRedstone;
            PacketDistributor.sendToServer(
                    new ConfigPacket(
                            ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR,
                            this.menu.blockEntity.getBlockPos(),
                            ConfigPacket.ConfigType.ADVANCED_COLLECTOR_REDSTONE_MODE,
                            0,
                            this.requiresRedstone
                    )
            );
            return true;
        }

        if (mouseX >= x + 154 && mouseX <= x + 166 && mouseY >= y + 61 && mouseY <= y + 73) {
            this.isAllowMode = !this.isAllowMode;
            PacketDistributor.sendToServer(
                    new ConfigPacket(
                            ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR,
                            this.menu.blockEntity.getBlockPos(),
                            ConfigPacket.ConfigType.ADVANCED_COLLECTOR_FILTER_MODE,
                            0,
                            this.isAllowMode
                    )
            );
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void init() {
        super.init();
        int leftPos = (this.width - this.imageWidth) / 2;
        int topPos = (this.height - this.imageHeight) / 2;
        this.clearWidgets();

        this.addOffsetButtons(leftPos, topPos);
        this.addWireframeButton(leftPos, topPos);
        this.addResetButton(leftPos, topPos);
    }

    private void addOffsetButtons(int leftPos, int topPos) {
        ImageButton duDecreaseButton = new ImageButton(
                leftPos + 83, topPos + 18, 10, 10,
                REDUCE_OFFSET_SPRITES,
                button -> this.adjustOffset("downUp", -1)
        );
        duDecreaseButton.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.offset.down_up.decrease")));
        this.addRenderableWidget(duDecreaseButton);

        ImageButton duIncreaseButton = new ImageButton(
                leftPos + 119, topPos + 18, 10, 10,
                INCREASE_OFFSET_SPRITES,
                button -> this.adjustOffset("downUp", 1)
        );
        duIncreaseButton.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.offset.down_up.increase")));
        this.addRenderableWidget(duIncreaseButton);

        ImageButton nsDecreaseButton = new ImageButton(
                leftPos + 83, topPos + 40, 10, 10,
                REDUCE_OFFSET_SPRITES,
                button -> this.adjustOffset("northSouth", -1)
        );
        nsDecreaseButton.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.offset.north_south.decrease")));
        this.addRenderableWidget(nsDecreaseButton);

        ImageButton nsIncreaseButton = new ImageButton(
                leftPos + 119, topPos + 40, 10, 10,
                INCREASE_OFFSET_SPRITES,
                button -> this.adjustOffset("northSouth", 1)
        );
        nsIncreaseButton.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.offset.north_south.increase")));
        this.addRenderableWidget(nsIncreaseButton);

        ImageButton ewDecreaseButton = new ImageButton(
                leftPos + 83, topPos + 62, 10, 10,
                REDUCE_OFFSET_SPRITES,
                button -> this.adjustOffset("eastWest", -1)
        );
        ewDecreaseButton.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.offset.east_west.decrease")));
        this.addRenderableWidget(ewDecreaseButton);

        ImageButton ewIncreaseButton = new ImageButton(
                leftPos + 119, topPos + 62, 10, 10,
                INCREASE_OFFSET_SPRITES,
                button -> this.adjustOffset("eastWest", 1)
        );
        ewIncreaseButton.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.offset.east_west.increase")));
        this.addRenderableWidget(ewIncreaseButton);
    }

    private void addWireframeButton(int leftPos, int topPos) {
        ImageButton wireframeButton = new ImageButton(
                leftPos + 101, topPos + 74, 10, 10,
                TOGGLE_WIREFRAME_SPRITES,
                button -> this.toggleWireframe()
        );
        wireframeButton.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.wireframe_toggle")));
        this.addRenderableWidget(wireframeButton);
    }

    private void addResetButton(int leftPos, int topPos) {
        ImageButton resetButton = new ImageButton(
                leftPos + 154, topPos + 39, 12, 12,
                RESET_OFFSET_SPRITES,
                button -> this.resetAllOffsets()
        );
        resetButton.setTooltip(Tooltip.create(Component.translatable("tooltip.smallprogressions.advanced_item_collector.offset.reset_all")));
        this.addRenderableWidget(resetButton);
    }

    private void adjustOffset(String axis, int delta) {
        ConfigPacket.ConfigType configType;
        int newValue;

        switch (axis) {
            case "downUp" -> {
                configType = ConfigPacket.ConfigType.ADVANCED_COLLECTOR_DOWN_UP_OFFSET;
                newValue = Math.max(-10, Math.min(10, this.downUpOffset + delta));
                this.downUpOffset = newValue;
            }
            case "northSouth" -> {
                configType = ConfigPacket.ConfigType.ADVANCED_COLLECTOR_NORTH_SOUTH_OFFSET;
                newValue = Math.max(-10, Math.min(10, this.northSouthOffset + delta));
                this.northSouthOffset = newValue;
            }
            case "eastWest" -> {
                configType = ConfigPacket.ConfigType.ADVANCED_COLLECTOR_EAST_WEST_OFFSET;
                newValue = Math.max(-10, Math.min(10, this.eastWestOffset + delta));
                this.eastWestOffset = newValue;
            }
            default -> {
                return;
            }
        }

        PacketDistributor.sendToServer(
                new ConfigPacket(
                        ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR,
                        this.menu.blockEntity.getBlockPos(),
                        configType,
                        newValue,
                        false
                )
        );
    }

    private void toggleWireframe() {
        this.wireframeEnabled = !this.wireframeEnabled;
        AdvancedItemCollectorWireframeRenderer.toggleWireframe(this.menu.blockEntity.getBlockPos());
        this.init();
    }

    private void resetAllOffsets() {
        this.downUpOffset = 0;
        this.northSouthOffset = 0;
        this.eastWestOffset = 0;

        PacketDistributor.sendToServer(new ConfigPacket(
                ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR,
                this.menu.blockEntity.getBlockPos(),
                ConfigPacket.ConfigType.ADVANCED_COLLECTOR_DOWN_UP_OFFSET,
                0,
                false
        ));

        PacketDistributor.sendToServer(new ConfigPacket(
                ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR,
                this.menu.blockEntity.getBlockPos(),
                ConfigPacket.ConfigType.ADVANCED_COLLECTOR_NORTH_SOUTH_OFFSET,
                0,
                false
        ));

        PacketDistributor.sendToServer(new ConfigPacket(
                ConfigPacket.ConfigTarget.ADVANCED_ITEM_COLLECTOR,
                this.menu.blockEntity.getBlockPos(),
                ConfigPacket.ConfigType.ADVANCED_COLLECTOR_EAST_WEST_OFFSET,
                0,
                false
        ));
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        this.renderOffsetValues(guiGraphics, x, y);
        this.renderRedstoneToggle(guiGraphics, x, y);
        this.renderFilterModeToggle(guiGraphics, x, y);
    }

    private void renderRedstoneToggle(GuiGraphics guiGraphics, int x, int y) {
        ResourceLocation sprite = this.requiresRedstone
                ? ResourceLocation.fromNamespaceAndPath("smallprogressions", "redstone_required_btn")
                : ResourceLocation.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn");

        guiGraphics.blitSprite(sprite, x + 154, y + 17, 12, 12);
    }

    private void renderFilterModeToggle(GuiGraphics guiGraphics, int x, int y) {
        ResourceLocation sprite = this.isAllowMode
                ? ResourceLocation.fromNamespaceAndPath("smallprogressions", "allow_btn")
                : ResourceLocation.fromNamespaceAndPath("smallprogressions", "block_btn");

        guiGraphics.blitSprite(sprite, x + 154, y + 61, 12, 12);
    }

    private void renderOffsetValues(GuiGraphics guiGraphics, int x, int y) {
        PoseStack poseStack = guiGraphics.pose();
        float scale = 0.65F;

        poseStack.pushPose();
        poseStack.scale(scale, scale, 1.0F);

        String duText = (this.downUpOffset >= 0 ? "+" : "") + this.downUpOffset;
        guiGraphics.drawString(this.font, duText, (int) ((x + 100) / scale), (int) ((y + 21) / scale), 0x353535, false);

        String nsText = (this.northSouthOffset >= 0 ? "+" : "") + this.northSouthOffset;
        guiGraphics.drawString(this.font, nsText, (int) ((x + 100) / scale), (int) ((y + 43) / scale), 0x353535, false);

        String ewText = (this.eastWestOffset >= 0 ? "+" : "") + this.eastWestOffset;
        guiGraphics.drawString(this.font, ewText, (int) ((x + 100) / scale), (int) ((y + 65) / scale), 0x353535, false);

        guiGraphics.drawString(this.font, Component.translatable("gui.smallprogressions.advanced_item_collector.offset.down_up").getString(),
                (int) ((x + 90) / scale), (int) ((y + 12) / scale), 0x353535, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.smallprogressions.advanced_item_collector.offset.north_south").getString(),
                (int) ((x + 90) / scale), (int) ((y + 34) / scale), 0x353535, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.smallprogressions.advanced_item_collector.offset.east_west").getString(),
                (int) ((x + 90) / scale), (int) ((y + 56) / scale), 0x353535, false);

        poseStack.popPose();
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

        if (mouseX >= x + 154 && mouseX <= x + 166 && mouseY >= y + 17 && mouseY <= y + 29) {
            Component tooltipText = this.requiresRedstone
                    ? Component.translatable("tooltip.smallprogressions.advanced_item_collector.redstone_required")
                    : Component.translatable("tooltip.smallprogressions.advanced_item_collector.redstone_not_required");
            guiGraphics.renderTooltip(this.font, tooltipText, mouseX, mouseY);
        }

        if (mouseX >= x + 154 && mouseX <= x + 166 && mouseY >= y + 61 && mouseY <= y + 73) {
            Component tooltipText = this.isAllowMode
                    ? Component.translatable("tooltip.smallprogressions.advanced_item_collector.allow_mode")
                    : Component.translatable("tooltip.smallprogressions.advanced_item_collector.block_mode");
            guiGraphics.renderTooltip(this.font, tooltipText, mouseX, mouseY);
        }
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.syncFromBlockEntity();
    }
}