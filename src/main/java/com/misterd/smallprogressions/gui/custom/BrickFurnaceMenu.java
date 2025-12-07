package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.BrickFurnaceBlockEntity;
import com.misterd.smallprogressions.gui.SPMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;

public class BrickFurnaceMenu extends AbstractContainerMenu {
    public final BrickFurnaceBlockEntity blockEntity;
    private final Level level;

    public BrickFurnaceMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public BrickFurnaceMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(SPMenuTypes.BRICK_FURNACE_MENU.get(), containerId);
        this.blockEntity = ((BrickFurnaceBlockEntity) blockEntity);
        this.level = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 62, 19)); //Input slot
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 1, 62, 55)); //Fuel Slot
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 2, 116, 37)); //Output Slot

        addDataSlots(this.blockEntity.data);
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 3;
    @Override
    public ItemStack quickMoveStack(Player player, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            boolean moved = false;

            if (level.getRecipeManager().getRecipeFor(RecipeType.SMELTING,
                    new SingleRecipeInput(sourceStack), level).isPresent()) {
                moved = moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + 1, false);
            }

            if (!moved && sourceStack.getBurnTime(RecipeType.SMELTING) > 0) {
                moved = moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + 1, TE_INVENTORY_FIRST_SLOT_INDEX + 2, false);
            }

            if (!moved) {
                return ItemStack.EMPTY;
            }
        }
        else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, true)) {
                return ItemStack.EMPTY;
            }
        }
        else {
            System.out.println("Invalid slotIndex:" + pIndex);
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
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, SPBlocks.BRICK_FURNACE.get());
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

    public int getProgress() {
        return this.blockEntity.data.get(0);
    }

    public int getMaxProgress() {
        return this.blockEntity.data.get(1);
    }

    public int getFuelTime() {
        return this.blockEntity.data.get(2);
    }

    public int getMaxFuelTime() {
        return this.blockEntity.data.get(3);
    }

    public boolean isBurning() {
        return getFuelTime() > 0;
    }
}
