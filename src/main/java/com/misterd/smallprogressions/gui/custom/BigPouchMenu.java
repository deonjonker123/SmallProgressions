package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.gui.SPMenuTypes;
import com.misterd.smallprogressions.item.custom.BigPouchItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class BigPouchMenu extends AbstractContainerMenu {
    private final ItemStack pouchStack;
    private final Level level;
    private final ItemStackHandler inventory;

    public BigPouchMenu(int containerId, Inventory playerInv, FriendlyByteBuf extraData) {
        this(containerId, playerInv, playerInv.getSelected(), playerInv.player.level());
    }

    public BigPouchMenu(int containerId, Inventory playerInv, ItemStack pouchStack, Level level) {
        super(SPMenuTypes.BIG_POUCH_MENU.get(), containerId);
        this.pouchStack = pouchStack;
        this.level = level;
        this.inventory = BigPouchItem.getInventory(pouchStack, level);

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new SlotItemHandler(inventory, col + row * 9, 8 + col * 18, 19 + row * 18));
            }
        }

        addPlayerInventory(playerInv);
        addPlayerHotbar(playerInv);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 142 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 201));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            if (index < 54) {
                if (!this.moveItemStackTo(stack, 54, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(stack, 0, 54, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getInventory().contains(pouchStack);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!level.isClientSide()) {
            BigPouchItem.saveInventory(pouchStack, inventory, level);
        }
    }
}