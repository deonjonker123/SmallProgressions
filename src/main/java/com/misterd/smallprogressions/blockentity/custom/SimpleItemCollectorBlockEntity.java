package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import java.util.List;

public class SimpleItemCollectorBlockEntity extends BlockEntity {
    private static final int COLLECTION_INTERVAL = 20;
    private static final int COLLECTION_RADIUS = 1;
    private static final int BUFFER_SIZE = 1;

    private int tickCounter = 0;

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(BUFFER_SIZE) {
        @Override
        protected void onContentsChanged(int slot, ItemStack previous) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public SimpleItemCollectorBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.SIMPLE_ITEM_COLLECTOR_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;

        if (++tickCounter >= COLLECTION_INTERVAL) {
            tickCounter = 0;
            collectItems();
        }

        if (!getBufferStack().isEmpty()) {
            pushToInventoryBelow();
        }
    }

    private void collectItems() {
        var targetInventory = level.getCapability(Capabilities.Item.BLOCK, worldPosition.below(), Direction.UP);
        if (targetInventory == null) return;

        AABB area = new AABB(
                worldPosition.getX() - COLLECTION_RADIUS,
                worldPosition.getY() - COLLECTION_RADIUS,
                worldPosition.getZ() - COLLECTION_RADIUS,
                worldPosition.getX() + COLLECTION_RADIUS + 1,
                worldPosition.getY() + COLLECTION_RADIUS + 1,
                worldPosition.getZ() + COLLECTION_RADIUS + 1
        );

        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, area);

        for (ItemEntity itemEntity : items) {
            if (!itemEntity.isAlive() || itemEntity.getItem().isEmpty()) continue;

            ItemStack stack = itemEntity.getItem().copy();
            ItemResource res = ItemResource.of(stack);
            int remaining = stack.getCount();

            for (int slot = 0; slot < targetInventory.size() && remaining > 0; slot++) {
                try (Transaction tx = Transaction.openRoot()) {
                    int inserted = targetInventory.insert(slot, res, remaining, tx);
                    tx.commit();
                    remaining -= inserted;
                }
            }

            if (remaining < stack.getCount()) {
                if (remaining == 0) itemEntity.discard();
                else itemEntity.setItem(stack.copyWithCount(remaining));
            } else if (remaining > 0 && getBufferStack().isEmpty()) {
                setBufferStack(stack.copyWithCount(remaining));
                itemEntity.discard();
            }
        }
    }

    private void pushToInventoryBelow() {
        var targetInventory = level.getCapability(Capabilities.Item.BLOCK, worldPosition.below(), Direction.UP);
        if (targetInventory == null) return;

        ItemStack bufferStack = getBufferStack();
        if (bufferStack.isEmpty()) return;

        ItemResource res = ItemResource.of(bufferStack);
        int remaining = bufferStack.getCount();

        for (int slot = 0; slot < targetInventory.size() && remaining > 0; slot++) {
            try (Transaction tx = Transaction.openRoot()) {
                int inserted = targetInventory.insert(slot, res, remaining, tx);
                tx.commit();
                remaining -= inserted;
            }
        }

        setBufferStack(remaining == 0 ? ItemStack.EMPTY : bufferStack.copyWithCount(remaining));
    }

    public ItemStack getBufferStack() {
        ItemResource res = inventory.getResource(0);
        if (res.isEmpty()) return ItemStack.EMPTY;
        return res.toStack(inventory.getAmountAsInt(0));
    }

    public void setBufferStack(ItemStack stack) {
        try (Transaction tx = Transaction.openRoot()) {
            inventory.extract(0, inventory.getResource(0), inventory.getAmountAsInt(0), tx);
            if (!stack.isEmpty()) inventory.insert(0, ItemResource.of(stack), stack.getCount(), tx);
            tx.commit();
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("TickCounter", tickCounter);
        inventory.serialize(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        tickCounter = input.getIntOr("TickCounter", 0);
        inventory.deserialize(input);
    }
}