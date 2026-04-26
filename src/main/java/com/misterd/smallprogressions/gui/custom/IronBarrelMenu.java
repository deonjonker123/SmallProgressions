package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.IronBarrelBlockEntity;
import com.misterd.smallprogressions.gui.SPMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class IronBarrelMenu extends AbstractContainerMenu {
    public final IronBarrelBlockEntity blockEntity;
    private final Level level;

    private static final int TE_SLOTS = 54;
    private static final int TE_FIRST = 0;
    private static final int PLAYER_FIRST = TE_SLOTS;
    private static final int PLAYER_SLOTS = 36;

    public IronBarrelMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public IronBarrelMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(SPMenuTypes.IRON_BARREL_MENU.get(), containerId);
        this.blockEntity = (IronBarrelBlockEntity) blockEntity;
        this.level = inv.player.level();

        for (int row = 0; row < 6; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new BESlot(this.blockEntity, col + row * 9, 8 + col * 18, 19 + row * 18));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot source = slots.get(index);
        if (source == null || !source.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = source.getItem();
        ItemStack copy = stack.copy();

        if (index < TE_SLOTS) {
            // Moving from barrel to player — vanilla slots handle this fine
            if (!moveItemStackTo(stack, PLAYER_FIRST, PLAYER_FIRST + PLAYER_SLOTS, true))
                return ItemStack.EMPTY;
        } else {
            // Moving from player into barrel — do it manually
            try (Transaction tx = Transaction.openRoot()) {
                int inserted = blockEntity.inventory.insert(ItemResource.of(stack), stack.getCount(), tx);
                if (inserted == 0) return ItemStack.EMPTY;
                tx.commit();
                stack.shrink(inserted);
            }
        }

        if (stack.isEmpty()) source.set(ItemStack.EMPTY);
        else source.setChanged();

        source.onTake(player, stack);
        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, SPBlocks.IRON_BARREL.get());
    }

    private void addPlayerInventory(Inventory inv) {
        for (int i = 0; i < 3; i++)
            for (int l = 0; l < 9; l++)
                addSlot(new Slot(inv, l + i * 9 + 9, 8 + l * 18, 142 + i * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int i = 0; i < 9; i++)
            addSlot(new Slot(inv, i, 8 + i * 18, 201));
    }

    private static class BESlot extends Slot {
        private final IronBarrelBlockEntity be;
        private final int index;

        BESlot(IronBarrelBlockEntity be, int index, int x, int y) {
            super(new SimpleContainer(be.inventory.size()), index, x, y);
            this.be = be;
            this.index = index;
        }

        @Override
        public ItemStack getItem() {
            ItemResource res = be.inventory.getResource(index);
            if (res.isEmpty()) return ItemStack.EMPTY;
            return res.toStack(be.inventory.getAmountAsInt(index));
        }

        @Override
        public void set(ItemStack stack) {
            try (Transaction tx = Transaction.openRoot()) {
                ItemStack existing = getItem();
                if (!existing.isEmpty())
                    be.inventory.extract(index, ItemResource.of(existing), existing.getCount(), tx);
                if (!stack.isEmpty())
                    be.inventory.insert(index, ItemResource.of(stack), stack.getCount(), tx);
                tx.commit();
            }
            setChanged();
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return be.inventory.isValid(index, ItemResource.of(stack));
        }

        @Override
        public ItemStack remove(int amount) {
            ItemStack existing = getItem();
            if (existing.isEmpty()) return ItemStack.EMPTY;
            int toExtract = Math.min(amount, existing.getCount());
            try (Transaction tx = Transaction.openRoot()) {
                int extracted = be.inventory.extract(index, ItemResource.of(existing), toExtract, tx);
                tx.commit();
                return existing.copyWithCount(extracted);
            }
        }
    }
}