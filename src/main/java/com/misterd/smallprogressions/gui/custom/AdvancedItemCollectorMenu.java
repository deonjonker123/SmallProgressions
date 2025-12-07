package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.AdvancedItemCollectorBlockEntity;
import com.misterd.smallprogressions.gui.SPMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AdvancedItemCollectorMenu extends AbstractContainerMenu {
    public final AdvancedItemCollectorBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    private static final int DATA_DOWN_UP_OFFSET = 0;
    private static final int DATA_NORTH_SOUTH_OFFSET = 1;
    private static final int DATA_EAST_WEST_OFFSET = 2;
    private static final int DATA_REQUIRES_REDSTONE = 3;
    private static final int DATA_IS_ALLOW_MODE = 4;
    private static final int DATA_WIREFRAME_ENABLED = 5;
    private static final int DATA_COUNT = 6;

    public AdvancedItemCollectorMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(DATA_COUNT));
    }

    public AdvancedItemCollectorMenu(int containerId, Inventory inv, BlockEntity blockEntity, ContainerData data) {
        super(SPMenuTypes.ADVANCED_ITEM_COLLECTOR_MENU.get(), containerId);
        this.blockEntity = ((AdvancedItemCollectorBlockEntity) blockEntity);
        this.level = inv.player.level();
        this.data = data;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.addSlot(new GhostSlot(this.blockEntity, col + row * 3, 8 + col * 18, 19 + row * 18));
            }
        }

        addPlayerInventory(inv);

        addPlayerHotbar(inv);

        addDataSlots(this.data);
    }

    private static class GhostSlot extends Slot {
        private final AdvancedItemCollectorBlockEntity blockEntity;
        private final int ghostSlotIndex;

        public GhostSlot(AdvancedItemCollectorBlockEntity blockEntity, int index, int x, int y) {
            super(new SimpleContainer(1), index, x, y);
            this.blockEntity = blockEntity;
            this.ghostSlotIndex = index;
        }

        @Override
        public ItemStack getItem() {
            return blockEntity.getGhostFilterSlots().get(ghostSlotIndex).copy();
        }

        @Override
        public void set(ItemStack stack) {
            blockEntity.setGhostFilterSlot(ghostSlotIndex, stack.isEmpty() ? ItemStack.EMPTY : stack.copyWithCount(1));
        }

        @Override
        public void onQuickCraft(ItemStack oldStack, ItemStack newStack) {

        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public int getMaxStackSize(ItemStack stack) {
            return 1;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return true;
        }

        @Override
        public ItemStack remove(int amount) {
            blockEntity.setGhostFilterSlot(ghostSlotIndex, ItemStack.EMPTY);
            return ItemStack.EMPTY;
        }

        @Override
        public boolean mayPickup(Player player) {
            return false;
        }
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int GHOST_SLOT_COUNT = 9;
    private static final int GHOST_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    @Override
    public void clicked(int slotId, int dragType, ClickType clickType, Player player) {
        if (slotId >= 0 && slotId < GHOST_SLOT_COUNT) {
            ItemStack carried = this.getCarried();
            if (!carried.isEmpty()) {
                blockEntity.setGhostFilterSlot(slotId, carried.copyWithCount(1));
            } else {
                blockEntity.setGhostFilterSlot(slotId, ItemStack.EMPTY);
            }
            return;
        }

        super.clicked(slotId, dragType, clickType, player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, SPBlocks.ADVANCED_ITEM_COLLECTOR.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 88 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 147));
        }
    }

    public int getDownUpOffset() {
        return this.data.get(DATA_DOWN_UP_OFFSET);
    }

    public int getNorthSouthOffset() {
        return this.data.get(DATA_NORTH_SOUTH_OFFSET);
    }

    public int getEastWestOffset() {
        return this.data.get(DATA_EAST_WEST_OFFSET);
    }

    public boolean requiresRedstone() {
        return this.data.get(DATA_REQUIRES_REDSTONE) == 1;
    }

    public boolean isAllowMode() {
        return this.data.get(DATA_IS_ALLOW_MODE) == 1;
    }

    public boolean isWireframeEnabled() {
        return this.data.get(DATA_WIREFRAME_ENABLED) == 1;
    }
}