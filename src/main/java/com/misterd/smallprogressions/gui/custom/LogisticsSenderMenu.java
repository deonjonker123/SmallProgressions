package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.LogisticsSenderBlockEntity;
import com.misterd.smallprogressions.gui.SPMenuTypes;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class LogisticsSenderMenu extends AbstractContainerMenu {
    public final LogisticsSenderBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    private static final int DATA_REDSTONE_ACTIVE = 0;
    private static final int DATA_ROUND_ROBIN = 1;
    private static final int DATA_FILTER_ALLOW = 2;
    private static final int DATA_COUNT = 3;

    private static final int FILTER_SLOTS = 18;
    private static final int UPGRADE_SLOTS = 4;
    private static final int FILTER_FIRST = 0;
    private static final int UPGRADE_FIRST = FILTER_SLOTS;
    private static final int PLAYER_FIRST = FILTER_SLOTS + UPGRADE_SLOTS;
    private static final int PLAYER_SLOTS = 36;

    public LogisticsSenderMenu(int containerId, Inventory inv, FriendlyByteBuf buf) {
        this(containerId, inv, (LogisticsSenderBlockEntity) inv.player.level().getBlockEntity(buf.readBlockPos()));
    }

    public LogisticsSenderMenu(int containerId, Inventory inv, LogisticsSenderBlockEntity blockEntity) {
        super(SPMenuTypes.LOGISTICS_SENDER_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.level = inv.player.level();

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case DATA_REDSTONE_ACTIVE -> blockEntity.isRedstoneActive() ? 1 : 0;
                    case DATA_ROUND_ROBIN -> blockEntity.isRoundRobin() ? 1 : 0;
                    case DATA_FILTER_ALLOW -> blockEntity.isFilterAllow() ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case DATA_REDSTONE_ACTIVE -> blockEntity.setRedstoneActive(value == 1);
                    case DATA_ROUND_ROBIN -> blockEntity.setRoundRobin(value == 1);
                    case DATA_FILTER_ALLOW -> blockEntity.setFilterAllow(value == 1);
                }
            }

            @Override
            public int getCount() { return DATA_COUNT; }
        };

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new GhostFilterSlot(blockEntity, col + row * 9, 8 + col * 18, 19 + row * 18));
            }
        }

        addSlot(new UpgradeSlot(blockEntity, LogisticsSenderBlockEntity.SLOT_SPEED, SPItems.SPEED_UPGRADE, 8, 71));
        addSlot(new UpgradeSlot(blockEntity, LogisticsSenderBlockEntity.SLOT_STACK, SPItems.STACK_UPGRADE, 26, 71));
        addSlot(new UpgradeSlot(blockEntity, LogisticsSenderBlockEntity.SLOT_RANGE, SPItems.RANGE_UPGRADE, 44, 71));
        addSlot(new UpgradeSlot(blockEntity, LogisticsSenderBlockEntity.SLOT_NODE, SPItems.NODE_UPGRADE, 62, 71));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 104 + row * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(inv, i, 8 + i * 18, 163));
        }

        addDataSlots(this.data);
    }

    @Override
    public void clicked(int slotId, int dragType, ContainerInput clickType, Player player) {
        if (slotId >= FILTER_FIRST && slotId < FILTER_FIRST + FILTER_SLOTS) {
            ItemStack carried = getCarried();
            blockEntity.setFilterSlot(slotId - FILTER_FIRST, carried.isEmpty() ? ItemStack.EMPTY : carried.copyWithCount(1));
            return;
        }
        super.clicked(slotId, dragType, clickType, player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (index >= FILTER_FIRST && index < FILTER_FIRST + FILTER_SLOTS) return ItemStack.EMPTY;

        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        if (index >= UPGRADE_FIRST && index < UPGRADE_FIRST + UPGRADE_SLOTS) {
            if (!moveItemStackTo(stack, PLAYER_FIRST, PLAYER_FIRST + PLAYER_SLOTS, true))
                return ItemStack.EMPTY;
        } else {
            if (!moveItemStackTo(stack, UPGRADE_FIRST, UPGRADE_FIRST + UPGRADE_SLOTS, false))
                return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        slot.onTake(player, stack);
        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, SPBlocks.LOGISTICS_SENDER.get());
    }

    public boolean isRedstoneActive() { return data.get(DATA_REDSTONE_ACTIVE) == 1; }
    public boolean isRoundRobin() { return data.get(DATA_ROUND_ROBIN) == 1; }
    public boolean isFilterAllow() { return data.get(DATA_FILTER_ALLOW) == 1; }
    public int getInterval() { return blockEntity.getInterval(); }
    public int getItemsPerTransfer() { return blockEntity.getItemsPerTransfer(); }
    public int getMaxReceivers() { return blockEntity.getMaxReceivers(); }
    public int getRange() { return blockEntity.getRange(); }

    private static class GhostFilterSlot extends Slot {
        private final LogisticsSenderBlockEntity be;
        private final int index;

        GhostFilterSlot(LogisticsSenderBlockEntity be, int index, int x, int y) {
            super(new SimpleContainer(1), index, x, y);
            this.be = be;
            this.index = index;
        }

        @Override public ItemStack getItem() { return be.getFilterStacks().get(index).copy(); }
        @Override public void set(ItemStack stack) { be.setFilterSlot(index, stack.isEmpty() ? ItemStack.EMPTY : stack.copyWithCount(1)); }
        @Override public ItemStack remove(int amount) { be.setFilterSlot(index, ItemStack.EMPTY); return ItemStack.EMPTY; }
        @Override public boolean mayPlace(ItemStack stack) { return true; }
        @Override public boolean mayPickup(Player player) { return false; }
        @Override public int getMaxStackSize() { return 1; }
        @Override public int getMaxStackSize(ItemStack stack) { return 1; }
    }

    private static class UpgradeSlot extends Slot {
        private final LogisticsSenderBlockEntity be;
        private final int index;
        private final net.neoforged.neoforge.registries.DeferredItem<?> acceptedItem;

        UpgradeSlot(LogisticsSenderBlockEntity be, int index, net.neoforged.neoforge.registries.DeferredItem<?> acceptedItem, int x, int y) {
            super(new SimpleContainer(be.upgradeInventory.size()), index, x, y);
            this.be = be;
            this.index = index;
            this.acceptedItem = acceptedItem;
        }

        @Override
        public ItemStack getItem() {
            ItemResource res = be.upgradeInventory.getResource(index);
            if (res.isEmpty()) return ItemStack.EMPTY;
            return res.toStack(be.upgradeInventory.getAmountAsInt(index));
        }

        @Override
        public void set(ItemStack stack) {
            if (stack.isEmpty()) {
                be.upgradeInventory.set(index, ItemResource.EMPTY, 0);
            } else {
                be.upgradeInventory.set(index, ItemResource.of(stack), stack.getCount());
            }
            setChanged();
        }

        @Override
        public ItemStack remove(int amount) {
            ItemStack existing = getItem();
            if (existing.isEmpty()) return ItemStack.EMPTY;
            try (Transaction tx = Transaction.openRoot()) {
                int extracted = be.upgradeInventory.extract(index, ItemResource.of(existing), Math.min(amount, existing.getCount()), tx);
                tx.commit();
                return existing.copyWithCount(extracted);
            }
        }

        @Override public boolean mayPlace(ItemStack stack) { return stack.is(acceptedItem.get()); }
        @Override public int getMaxStackSize() { return 3; }
        @Override public int getMaxStackSize(ItemStack stack) { return 3; }
    }
}