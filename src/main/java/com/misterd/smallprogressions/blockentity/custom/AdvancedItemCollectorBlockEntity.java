package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.gui.custom.AdvancedItemCollectorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import javax.annotation.Nullable;
import java.util.List;

public class AdvancedItemCollectorBlockEntity extends BlockEntity implements MenuProvider {
    private static final int COLLECTION_INTERVAL = 20;
    private static final int COLLECTION_RADIUS = 4;
    private static final int BUFFER_SIZE = 1;
    private static final int FILTER_SIZE = 9;

    private int tickCounter = 0;

    private int downUpOffset = 0;
    private int northSouthOffset = 0;
    private int eastWestOffset = 0;

    private boolean requiresRedstone = false;
    private boolean isAllowMode = true;
    private boolean wireframeEnabled = false;

    private final ItemStack[] ghostFilterSlots = new ItemStack[FILTER_SIZE];

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(BUFFER_SIZE) {
        @Override
        protected void onContentsChanged(int slot, ItemStack stack) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public AdvancedItemCollectorBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.ADVANCED_ITEM_COLLECTOR_BE.get(), pos, state);
        for (int i = 0; i < FILTER_SIZE; i++) ghostFilterSlots[i] = ItemStack.EMPTY;
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;

        if (requiresRedstone && !level.hasNeighborSignal(worldPosition)) return;

        if (++tickCounter >= COLLECTION_INTERVAL) {
            tickCounter = 0;
            collectItems();
        }

        if (!getBufferStack().isEmpty()) {
            pushToInventoryBelow();
        }
    }

    private void collectItems() {
        BlockPos belowPos = worldPosition.below();
        var targetInventory = level.getCapability(Capabilities.Item.BLOCK, belowPos, Direction.UP);
        if (targetInventory == null) return;

        AABB area = new AABB(
                worldPosition.getX() - COLLECTION_RADIUS + eastWestOffset,
                worldPosition.getY() - COLLECTION_RADIUS + downUpOffset,
                worldPosition.getZ() - COLLECTION_RADIUS + northSouthOffset,
                worldPosition.getX() + COLLECTION_RADIUS + 1 + eastWestOffset,
                worldPosition.getY() + COLLECTION_RADIUS + 1 + downUpOffset,
                worldPosition.getZ() + COLLECTION_RADIUS + 1 + northSouthOffset
        );

        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, area);

        for (ItemEntity itemEntity : items) {
            if (!itemEntity.isAlive() || itemEntity.getItem().isEmpty()) continue;

            ItemStack stack = itemEntity.getItem().copy();
            if (!passesFilter(stack)) continue;

            ItemStack remaining = insertItem(targetInventory, stack);

            if (!remaining.isEmpty() && getBufferStack().isEmpty()) {
                setBufferStack(remaining);
                itemEntity.discard();
            } else if (remaining.isEmpty()) {
                itemEntity.discard();
            } else if (remaining.getCount() < stack.getCount()) {
                itemEntity.setItem(remaining);
            }
        }
    }

    private void pushToInventoryBelow() {
        var targetInventory = level.getCapability(Capabilities.Item.BLOCK, worldPosition.below(), Direction.UP);
        if (targetInventory == null) return;

        ItemStack bufferStack = getBufferStack();
        if (bufferStack.isEmpty()) return;

        setBufferStack(insertItem(targetInventory, bufferStack));
    }

    private ItemStack insertItem(net.neoforged.neoforge.transfer.ResourceHandler<ItemResource> handler, ItemStack stack) {
        ItemResource res = ItemResource.of(stack);
        int remaining = stack.getCount();
        for (int slot = 0; slot < handler.size() && remaining > 0; slot++) {
            try (Transaction tx = Transaction.openRoot()) {
                int inserted = handler.insert(slot, res, remaining, tx);
                tx.commit();
                remaining -= inserted;
            }
        }
        return remaining == 0 ? ItemStack.EMPTY : stack.copyWithCount(remaining);
    }

    private boolean passesFilter(ItemStack stack) {
        boolean hasFilter = false;
        for (ItemStack filter : ghostFilterSlots) {
            if (!filter.isEmpty()) { hasFilter = true; break; }
        }
        if (!hasFilter) return true;

        for (ItemStack filter : ghostFilterSlots) {
            if (!filter.isEmpty() && ItemStack.isSameItemSameComponents(stack, filter)) {
                return isAllowMode;
            }
        }
        return !isAllowMode;
    }

    public ItemStack getBufferStack() {
        ItemResource res = inventory.getResource(0);
        if (res.isEmpty()) return ItemStack.EMPTY;
        return res.toStack(inventory.getAmountAsInt(0));
    }

    public void setBufferStack(ItemStack stack) {
        try (Transaction tx = Transaction.openRoot()) {
            ItemResource existing = inventory.getResource(0);
            int existingAmount = inventory.getAmountAsInt(0);
            if (!existing.isEmpty() && existingAmount > 0) {
                inventory.extract(0, existing, existingAmount, tx);
            }
            if (!stack.isEmpty()) {
                inventory.insert(0, ItemResource.of(stack), stack.getCount(), tx);
            }
            tx.commit();
        }
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (level == null) return;
        SimpleContainer drop = new SimpleContainer(1);
        drop.setItem(0, getBufferStack());
        Containers.dropContents(level, worldPosition, drop);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        inventory.serialize(output);
        output.putInt("TickCounter", tickCounter);
        output.putInt("DownUpOffset", downUpOffset);
        output.putInt("NorthSouthOffset", northSouthOffset);
        output.putInt("EastWestOffset", eastWestOffset);
        output.putBoolean("RequiresRedstone", requiresRedstone);
        output.putBoolean("IsAllowMode", isAllowMode);
        output.putBoolean("WireframeEnabled", wireframeEnabled);

        for (int i = 0; i < FILTER_SIZE; i++) {
            if (!ghostFilterSlots[i].isEmpty()) {
                output.store("GhostSlot" + i, ItemStack.CODEC, ghostFilterSlots[i]);
            }
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        inventory.deserialize(input);
        tickCounter = input.getIntOr("TickCounter", 0);
        downUpOffset = input.getIntOr("DownUpOffset", 0);
        northSouthOffset = input.getIntOr("NorthSouthOffset", 0);
        eastWestOffset = input.getIntOr("EastWestOffset", 0);
        requiresRedstone = input.getBooleanOr("RequiresRedstone", false);
        isAllowMode = input.getBooleanOr("IsAllowMode", true);
        wireframeEnabled = input.getBooleanOr("WireframeEnabled", false);

        for (int i = 0; i < FILTER_SIZE; i++) {
            ghostFilterSlots[i] = input.read("GhostSlot" + i, ItemStack.CODEC).orElse(ItemStack.EMPTY);
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveCustomOnly(registries);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("menu.smallprogressions.advanced_item_collector");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new AdvancedItemCollectorMenu(id, inv, this);
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> createTicker() {
        return (level, pos, state, be) -> {
            if (be instanceof AdvancedItemCollectorBlockEntity collector) collector.tick();
        };
    }

    public int getDownUpOffset() {
        return downUpOffset;
    }

    public int getNorthSouthOffset() {
        return northSouthOffset;
    }

    public int getEastWestOffset() {
        return eastWestOffset;
    }

    public boolean requiresRedstone() {
        return requiresRedstone;
    }

    public boolean isAllowMode() {
        return isAllowMode;
    }

    public boolean isWireframeEnabled() {
        return wireframeEnabled;
    }

    public ItemStack[] getGhostFilterSlots() {
        return ghostFilterSlots;
    }

    public void setDownUpOffset(int v) {
        downUpOffset = Math.max(-10, Math.min(10, v)); setChanged();
    }

    public void setNorthSouthOffset(int v) {
        northSouthOffset = Math.max(-10, Math.min(10, v)); setChanged();
    }

    public void setEastWestOffset(int v) {
        eastWestOffset = Math.max(-10, Math.min(10, v)); setChanged();
    }

    public void setRequiresRedstone(boolean v) {
        requiresRedstone = v; setChanged();
    }

    public void setFilterMode(boolean v) {
        isAllowMode = v; setChanged();
    }

    public void setWireframeEnabled(boolean v) {
        wireframeEnabled = v; setChanged();
    }

    public void setGhostFilterSlot(int slot, ItemStack stack) {
        if (slot >= 0 && slot < FILTER_SIZE) {
            ghostFilterSlots[slot] = stack.copy();
            setChanged();
        }
    }
}