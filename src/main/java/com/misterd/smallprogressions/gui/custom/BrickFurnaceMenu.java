package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.BrickFurnaceBlockEntity;
import com.misterd.smallprogressions.gui.SPMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
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
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class BrickFurnaceMenu extends AbstractContainerMenu {
    public final BrickFurnaceBlockEntity blockEntity;
    private final Level level;

    private static final int VANILLA_SLOTS = 36;
    private static final int TE_FIRST = VANILLA_SLOTS;
    private static final int TE_SLOTS = 3;

    public BrickFurnaceMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public BrickFurnaceMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(SPMenuTypes.BRICK_FURNACE_MENU.get(), containerId);
        this.blockEntity = (BrickFurnaceBlockEntity) blockEntity;
        this.level = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        addSlot(new BESlot(this.blockEntity, 0, 62, 19));
        addSlot(new BESlot(this.blockEntity, 1, 62, 55));

        BrickFurnaceBlockEntity be = this.blockEntity;
        addSlot(new BESlot(this.blockEntity, 2, 116, 37) {
            @Override
            public void onTake(Player player, ItemStack stack) {
                be.awardUsedRecipesAndPopExperience(player);
                super.onTake(player, stack);
            }
        });

        addDataSlots(this.blockEntity.data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        if (index < VANILLA_SLOTS) {
            try (Transaction tx = Transaction.openRoot()) {
                int inserted = 0;
                if (level instanceof ServerLevel sl &&
                        sl.recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(stack), sl).isPresent()) {
                    inserted = blockEntity.inventory.insert(0, ItemResource.of(stack), stack.getCount(), tx);
                }
                if (inserted == 0 && level.fuelValues().isFuel(stack)) {
                    inserted = blockEntity.inventory.insert(1, ItemResource.of(stack), stack.getCount(), tx);
                }
                if (inserted == 0) return ItemStack.EMPTY;
                tx.commit();
                stack.shrink(inserted);
            }
        } else if (index < TE_FIRST + TE_SLOTS) {
            if (!moveItemStackTo(stack, 0, VANILLA_SLOTS, true)) return ItemStack.EMPTY;
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
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, SPBlocks.BRICK_FURNACE.get());
    }

    private void addPlayerInventory(Inventory inv) {
        for (int i = 0; i < 3; i++)
            for (int l = 0; l < 9; l++)
                addSlot(new Slot(inv, l + i * 9 + 9, 8 + l * 18, 88 + i * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int i = 0; i < 9; i++)
            addSlot(new Slot(inv, i, 8 + i * 18, 147));
    }

    public int getProgress() { return this.blockEntity.data.get(0); }
    public int getMaxProgress() { return this.blockEntity.data.get(1); }
    public int getFuelTime() { return this.blockEntity.data.get(2); }
    public int getMaxFuelTime() { return this.blockEntity.data.get(3); }
    public boolean isBurning() { return getFuelTime() > 0; }

    private static class BESlot extends Slot {
        private final BrickFurnaceBlockEntity be;
        private final int index;

        BESlot(BrickFurnaceBlockEntity be, int index, int x, int y) {
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
            if (stack.isEmpty()) {
                be.inventory.set(index, ItemResource.EMPTY, 0);
            } else {
                be.inventory.set(index, ItemResource.of(stack), stack.getCount());
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