package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.LinenSackBlockEntity;
import com.misterd.smallprogressions.gui.SPMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;

public class LinenSackMenu extends AbstractContainerMenu {
    public final LinenSackBlockEntity blockEntity;
    private final Level level;

    public LinenSackMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public LinenSackMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(SPMenuTypes.LINEN_SACK_MENU.get(), containerId);
        this.blockEntity = ((LinenSackBlockEntity) blockEntity);
        this.level = inv.player.level();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.addSlot(new SlotItemHandler(this.blockEntity.inventory, col + row * 3, 62 + col * 18, 19 + row * 18));
            }
        }

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    private static final int SACK_INVENTORY_SLOT_COUNT = 9;
    private static final int SACK_INVENTORY_FIRST_SLOT_INDEX = 0;
    private static final int VANILLA_FIRST_SLOT_INDEX = SACK_INVENTORY_SLOT_COUNT;

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < SACK_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, true)) {
                return ItemStack.EMPTY;
            }
        } else if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, SACK_INVENTORY_FIRST_SLOT_INDEX, SACK_INVENTORY_FIRST_SLOT_INDEX + SACK_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, SPBlocks.LINEN_SACK.get());
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
}