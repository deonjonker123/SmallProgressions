package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.List;

public class SimpleItemCollectorBlockEntity extends BlockEntity {
    private static final int COLLECTION_INTERVAL = 20;
    private static final int COLLECTION_RADIUS = 1;
    private static final int BUFFER_SIZE = 1;

    private int tickCounter = 0;

    public final ItemStackHandler inventory = new ItemStackHandler(BUFFER_SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public SimpleItemCollectorBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.SIMPLE_ITEM_COLLECTOR_BE.get(), pos, state);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            return;
        }

        tickCounter++;
        if (tickCounter >= COLLECTION_INTERVAL) {
            tickCounter = 0;
            collectItems(level, pos);
        }

        if (!inventory.getStackInSlot(0).isEmpty()) {
            pushToInventoryBelow(level, pos);
        }
    }

    private void collectItems(Level level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        IItemHandler targetInventory = level.getCapability(Capabilities.ItemHandler.BLOCK, belowPos, Direction.UP);

        if (targetInventory == null) {
            return;
        }

        AABB collectionArea = new AABB(
                pos.getX() - COLLECTION_RADIUS,
                pos.getY() - COLLECTION_RADIUS,
                pos.getZ() - COLLECTION_RADIUS,
                pos.getX() + COLLECTION_RADIUS + 1,
                pos.getY() + COLLECTION_RADIUS + 1,
                pos.getZ() + COLLECTION_RADIUS + 1
        );

        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, collectionArea);

        for (ItemEntity itemEntity : items) {
            if (!itemEntity.isAlive() || itemEntity.getItem().isEmpty()) {
                continue;
            }

            ItemStack stack = itemEntity.getItem().copy();

            ItemStack remaining = insertItem(targetInventory, stack);

            if (!remaining.isEmpty() && inventory.getStackInSlot(0).isEmpty()) {
                inventory.setStackInSlot(0, remaining);
                itemEntity.discard();
            } else if (remaining.isEmpty()) {
                itemEntity.discard();
            } else if (remaining.getCount() < stack.getCount()) {
                itemEntity.setItem(remaining);
            }
        }
    }

    private void pushToInventoryBelow(Level level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        IItemHandler targetInventory = level.getCapability(Capabilities.ItemHandler.BLOCK, belowPos, Direction.UP);

        if (targetInventory == null) {
            return;
        }

        ItemStack bufferStack = inventory.getStackInSlot(0);
        if (bufferStack.isEmpty()) {
            return;
        }

        ItemStack remaining = insertItem(targetInventory, bufferStack);
        inventory.setStackInSlot(0, remaining);
    }

    private ItemStack insertItem(IItemHandler inventory, ItemStack stack) {
        ItemStack remaining = stack.copy();

        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            remaining = inventory.insertItem(slot, remaining, false);

            if (remaining.isEmpty()) {
                break;
            }
        }

        return remaining;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("TickCounter", tickCounter);
        tag.put("Inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        tickCounter = tag.getInt("TickCounter");
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
    }
}