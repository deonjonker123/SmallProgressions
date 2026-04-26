package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.CopperBarrelBlockEntity;
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

public class CopperBarrelMenu extends AbstractContainerMenu {
    public final CopperBarrelBlockEntity blockEntity;
    private final Level level;

    private static final int TE_SLOTS = 45;
    private static final int TE_FIRST = 0;
    private static final int PLAYER_FIRST = TE_SLOTS;
    private static final int PLAYER_SLOTS = 36;

    public CopperBarrelMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public CopperBarrelMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(SPMenuTypes.COPPER_BARREL_MENU.get(), containerId);
        this.blockEntity = (CopperBarrelBlockEntity) blockEntity;
        this.level = inv.player.level();

        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new BESlot(this.blockEntity, col + row * 9, 8 + col * 18, 19 + row * 18));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        if (index < TE_SLOTS) {
            if (!moveItemStackTo(stack, PLAYER_FIRST, PLAYER_FIRST + PLAYER_SLOTS, true))
                return ItemStack.EMPTY;
        } else if (index < PLAYER_FIRST + PLAYER_SLOTS) {
            if (!moveItemStackTo(stack, TE_FIRST, TE_SLOTS, false))
                return ItemStack.EMPTY;
        } else {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        slot.onTake(player, stack);
        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, SPBlocks.COPPER_BARREL.get());
    }

    private void addPlayerInventory(Inventory inv) {
        for (int i = 0; i < 3; i++)
            for (int l = 0; l < 9; l++)
                addSlot(new Slot(inv, l + i * 9 + 9, 8 + l * 18, 124 + i * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int i = 0; i < 9; i++)
            addSlot(new Slot(inv, i, 8 + i * 18, 183));
    }

    private static class BESlot extends Slot {
        private final CopperBarrelBlockEntity be;
        private final int index;

        BESlot(CopperBarrelBlockEntity be, int index, int x, int y) {
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