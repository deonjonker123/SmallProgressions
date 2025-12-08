package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.List;

public class AdvancedItemCollectorBlockEntity extends BlockEntity {
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

    private NonNullList<ItemStack> ghostFilterSlots = NonNullList.withSize(FILTER_SIZE, ItemStack.EMPTY);

    public final ItemStackHandler inventory = new ItemStackHandler(BUFFER_SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> downUpOffset;
                case 1 -> northSouthOffset;
                case 2 -> eastWestOffset;
                case 3 -> requiresRedstone ? 1 : 0;
                case 4 -> isAllowMode ? 1 : 0;
                case 5 -> wireframeEnabled ? 1 : 0;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> downUpOffset = value;
                case 1 -> northSouthOffset = value;
                case 2 -> eastWestOffset = value;
                case 3 -> requiresRedstone = value == 1;
                case 4 -> isAllowMode = value == 1;
                case 5 -> wireframeEnabled = value == 1;
            }
        }

        @Override
        public int getCount() {
            return 6;
        }
    };

    public AdvancedItemCollectorBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.ADVANCED_ITEM_COLLECTOR_BE.get(), pos, state);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            return;
        }

        if (requiresRedstone && !level.hasNeighborSignal(pos)) {
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
                pos.getX() - COLLECTION_RADIUS + eastWestOffset,
                pos.getY() - COLLECTION_RADIUS + downUpOffset,
                pos.getZ() - COLLECTION_RADIUS + northSouthOffset,
                pos.getX() + COLLECTION_RADIUS + 1 + eastWestOffset,
                pos.getY() + COLLECTION_RADIUS + 1 + downUpOffset,
                pos.getZ() + COLLECTION_RADIUS + 1 + northSouthOffset
        );

        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, collectionArea);

        for (ItemEntity itemEntity : items) {
            if (!itemEntity.isAlive() || itemEntity.getItem().isEmpty()) {
                continue;
            }

            ItemStack stack = itemEntity.getItem().copy();

            if (!passesFilter(stack)) {
                continue;
            }

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

    private boolean passesFilter(ItemStack stack) {
        boolean hasFilter = false;
        for (ItemStack filterStack : ghostFilterSlots) {
            if (!filterStack.isEmpty()) {
                hasFilter = true;
                break;
            }
        }

        if (!hasFilter) {
            return true;
        }

        boolean matchesFilter = false;
        for (ItemStack filterStack : ghostFilterSlots) {
            if (!filterStack.isEmpty() && ItemStack.isSameItemSameComponents(stack, filterStack)) {
                matchesFilter = true;
                break;
            }
        }

        return isAllowMode ? matchesFilter : !matchesFilter;
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

    public NonNullList<ItemStack> getGhostFilterSlots() {
        return ghostFilterSlots;
    }

    public void setDownUpOffset(int offset) {
        this.downUpOffset = Math.max(-10, Math.min(10, offset));
        setChanged();
    }

    public void setNorthSouthOffset(int offset) {
        this.northSouthOffset = Math.max(-10, Math.min(10, offset));
        setChanged();
    }

    public void setEastWestOffset(int offset) {
        this.eastWestOffset = Math.max(-10, Math.min(10, offset));
        setChanged();
    }

    public void setRequiresRedstone(boolean requiresRedstone) {
        this.requiresRedstone = requiresRedstone;
        setChanged();
    }

    public void setFilterMode(boolean isAllowMode) {
        this.isAllowMode = isAllowMode;
        setChanged();
    }

    public void setWireframeEnabled(boolean enabled) {
        this.wireframeEnabled = enabled;
        setChanged();
    }

    public void setGhostFilterSlot(int slot, ItemStack stack) {
        if (slot >= 0 && slot < FILTER_SIZE) {
            ghostFilterSlots.set(slot, stack.copy());
            setChanged();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("TickCounter", tickCounter);
        tag.putInt("DownUpOffset", downUpOffset);
        tag.putInt("NorthSouthOffset", northSouthOffset);
        tag.putInt("EastWestOffset", eastWestOffset);
        tag.putBoolean("RequiresRedstone", requiresRedstone);
        tag.putBoolean("IsAllowMode", isAllowMode);
        tag.putBoolean("WireframeEnabled", wireframeEnabled);
        tag.put("Inventory", inventory.serializeNBT(registries));

        CompoundTag ghostSlotsTag = new CompoundTag();
        for (int i = 0; i < ghostFilterSlots.size(); i++) {
            if (!ghostFilterSlots.get(i).isEmpty()) {
                CompoundTag slotTag = new CompoundTag();
                ghostFilterSlots.get(i).save(registries, slotTag);
                ghostSlotsTag.put("Slot" + i, slotTag);
            }
        }
        tag.put("GhostFilterSlots", ghostSlotsTag);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        tickCounter = tag.getInt("TickCounter");
        downUpOffset = tag.getInt("DownUpOffset");
        northSouthOffset = tag.getInt("NorthSouthOffset");
        eastWestOffset = tag.getInt("EastWestOffset");
        requiresRedstone = tag.getBoolean("RequiresRedstone");
        isAllowMode = tag.getBoolean("IsAllowMode");
        wireframeEnabled = tag.getBoolean("WireframeEnabled");
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));

        CompoundTag ghostSlotsTag = tag.getCompound("GhostFilterSlots");
        for (int i = 0; i < ghostFilterSlots.size(); i++) {
            if (ghostSlotsTag.contains("Slot" + i)) {
                ghostFilterSlots.set(i, ItemStack.parse(registries, ghostSlotsTag.getCompound("Slot" + i)).orElse(ItemStack.EMPTY));
            } else {
                ghostFilterSlots.set(i, ItemStack.EMPTY);
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}