package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.network.LogisticsSenderConfigPacket;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class LogisticsSenderScreen extends AbstractContainerScreen<LogisticsSenderMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath("smallprogressions", "textures/gui/sender_gui.png");

    private static final WidgetSprites REDSTONE_ACTIVE_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "redstone_required_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "redstone_required_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "redstone_required_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "redstone_required_btn")
    );
    private static final WidgetSprites REDSTONE_INACTIVE_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "no_redstone_required_btn")
    );
    private static final WidgetSprites ROUND_ROBIN_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "round_robin_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "round_robin_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "round_robin_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "round_robin_btn")
    );
    private static final WidgetSprites NEAREST_FIRST_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "near_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "near_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "near_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "near_btn")
    );
    private static final WidgetSprites ALLOW_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "allow_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "allow_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "allow_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "allow_btn")
    );
    private static final WidgetSprites BLOCK_SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("smallprogressions", "block_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "block_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "block_btn"),
            Identifier.fromNamespaceAndPath("smallprogressions", "block_btn")
    );

    private boolean redstoneActive;
    private boolean roundRobin;
    private boolean filterAllow;

    public LogisticsSenderScreen(LogisticsSenderMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 189);
        this.inventoryLabelY = 189 - 94;
        syncFromMenu();
    }

    private void syncFromMenu() {
        this.redstoneActive = menu.isRedstoneActive();
        this.roundRobin = menu.isRoundRobin();
        this.filterAllow = menu.isFilterAllow();
    }

    @Override
    protected void init() {
        super.init();
        clearWidgets();

        addRenderableWidget(new ImageButton(
                leftPos + 118, topPos + 73, 12, 12,
                redstoneActive ? REDSTONE_ACTIVE_SPRITES : REDSTONE_INACTIVE_SPRITES,
                btn -> toggleRedstone()
        )).setTooltip(Tooltip.create(Component.translatable(
                redstoneActive ? "tooltip.smallprogressions.logistics_sender.redstone_active"
                        : "tooltip.smallprogressions.logistics_sender.redstone_inactive")));

        addRenderableWidget(new ImageButton(
                leftPos + 136, topPos + 73, 12, 12,
                roundRobin ? ROUND_ROBIN_SPRITES : NEAREST_FIRST_SPRITES,
                btn -> toggleDistro()
        )).setTooltip(Tooltip.create(Component.translatable(
                roundRobin ? "tooltip.smallprogressions.logistics_sender.round_robin"
                        : "tooltip.smallprogressions.logistics_sender.nearest_first")));

        addRenderableWidget(new ImageButton(
                leftPos + 154, topPos + 73, 12, 12,
                filterAllow ? ALLOW_SPRITES : BLOCK_SPRITES,
                btn -> toggleFilter()
        )).setTooltip(Tooltip.create(Component.translatable(
                filterAllow ? "tooltip.smallprogressions.logistics_sender.filter_allow"
                        : "tooltip.smallprogressions.logistics_sender.filter_block")));
    }

    private void toggleRedstone() {
        redstoneActive = !redstoneActive;
        ClientPacketDistributor.sendToServer(new LogisticsSenderConfigPacket(
                menu.blockEntity.getBlockPos(),
                LogisticsSenderConfigPacket.ConfigType.REDSTONE_ACTIVE,
                redstoneActive));
        init();
    }

    private void toggleDistro() {
        roundRobin = !roundRobin;
        ClientPacketDistributor.sendToServer(new LogisticsSenderConfigPacket(
                menu.blockEntity.getBlockPos(),
                LogisticsSenderConfigPacket.ConfigType.ROUND_ROBIN,
                roundRobin));
        init();
    }

    private void toggleFilter() {
        filterAllow = !filterAllow;
        ClientPacketDistributor.sendToServer(new LogisticsSenderConfigPacket(
                menu.blockEntity.getBlockPos(),
                LogisticsSenderConfigPacket.ConfigType.FILTER_ALLOW,
                filterAllow));
        init();
    }

    @Override
    public void extractContents(GuiGraphicsExtractor gfx, int mouseX, int mouseY, float partialTick) {
        gfx.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, leftPos, topPos, 0.0F, 0.0F, imageWidth, imageHeight, 256, 256);
        super.extractContents(gfx, mouseX, mouseY, partialTick);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor gfx, int mouseX, int mouseY) {
        super.extractTooltip(gfx, mouseX, mouseY);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        syncFromMenu();
    }
}