package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.block.custom.LogisticsReceiverBlock;
import com.misterd.smallprogressions.block.custom.LogisticsSenderBlock;
import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.gui.custom.LogisticsSenderMenu;
import com.misterd.smallprogressions.item.SPItems;
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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import javax.annotation.Nullable;
import java.util.*;

public class LogisticsSenderBlockEntity extends BlockEntity implements MenuProvider {

    public static final int BASE_INTERVAL = 65;
    public static final int BASE_ITEMS = 16;
    public static final int BASE_RECEIVERS = 4;
    public static final int BASE_RANGE = 8;

    public static final int SPEED_REDUCTION = 20;
    public static final int MIN_INTERVAL = 5;
    public static final int STACK_BONUS = 16;
    public static final int MAX_ITEMS = 64;
    public static final int NODE_BONUS = 2;
    public static final int MAX_RECEIVERS = 10;
    public static final int RANGE_BONUS = 8;
    public static final int MAX_RANGE = 32;

    public static final int UPGRADE_SLOTS = 4;
    public static final int FILTER_SLOTS = 18;

    public static final int SLOT_SPEED = 0;
    public static final int SLOT_STACK = 1;
    public static final int SLOT_NODE = 2;
    public static final int SLOT_RANGE = 3;

    @Nullable private UUID ownerUUID = null;
    private boolean redstoneActive = true;
    private boolean roundRobin = true;
    private boolean filterAllow = true;
    private int roundRobinIndex = 0;
    private int tickCounter = 0;

    private final List<BlockPos> connectedReceivers = new ArrayList<>();
    private final List<ItemStack> filterStacks = new ArrayList<>(Collections.nCopies(FILTER_SLOTS, ItemStack.EMPTY));

    public final ItemStacksResourceHandler upgradeInventory = new ItemStacksResourceHandler(UPGRADE_SLOTS) {
        @Override
        protected void onContentsChanged(int slot, ItemStack previous) {
            setChanged();
        }

        @Override
        public boolean isValid(int slot, ItemResource resource) {
            return isUpgradeItem(resource.toStack(), slot);
        }
    };

    public LogisticsSenderBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.LOGISTICS_SENDER_BE.get(), pos, state);
    }

    public void setOwner(UUID uuid) { this.ownerUUID = uuid; setChanged(); }
    @Nullable public UUID getOwnerUUID() { return ownerUUID; }

    public boolean isRedstoneActive() { return redstoneActive; }
    public void setRedstoneActive(boolean v) { redstoneActive = v; setChanged(); }

    public boolean isRoundRobin() { return roundRobin; }
    public void setRoundRobin(boolean v) { roundRobin = v; setChanged(); }

    public boolean isFilterAllow() { return filterAllow; }
    public void setFilterAllow(boolean v) { filterAllow = v; setChanged(); }
    public List<ItemStack> getFilterStacks() { return filterStacks; }

    public void setFilterSlot(int slot, ItemStack stack) {
        if (slot >= 0 && slot < FILTER_SLOTS) {
            filterStacks.set(slot, stack.copyWithCount(1));
            setChanged();
        }
    }

    public List<BlockPos> getConnectedReceivers() { return connectedReceivers; }

    public boolean addReceiver(BlockPos pos) {
        if (connectedReceivers.contains(pos)) return false;
        if (connectedReceivers.size() >= getMaxReceivers()) return false;
        if (pos.distSqr(worldPosition) > (double) getRange() * getRange()) return false;
        connectedReceivers.add(pos);
        setChanged();
        if (level != null && !level.isClientSide())
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        return true;
    }

    public void removeReceiver(BlockPos pos) {
        connectedReceivers.remove(pos);
        if (roundRobinIndex >= connectedReceivers.size()) roundRobinIndex = 0;
        setChanged();
        if (level != null && !level.isClientSide())
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    private int getUpgradeCount(int slot) {
        ItemResource res = upgradeInventory.getResource(slot);
        if (res.isEmpty()) return 0;
        return upgradeInventory.getAmountAsInt(slot);
    }

    public int getInterval() {
        int speedCount = getUpgradeCount(SLOT_SPEED);
        return Math.max(MIN_INTERVAL, BASE_INTERVAL - speedCount * SPEED_REDUCTION);
    }

    public int getItemsPerTransfer() {
        int stackCount = getUpgradeCount(SLOT_STACK);
        return Math.min(MAX_ITEMS, BASE_ITEMS + stackCount * STACK_BONUS);
    }

    public int getMaxReceivers() {
        int nodeCount = getUpgradeCount(SLOT_NODE);
        return Math.min(MAX_RECEIVERS, BASE_RECEIVERS + nodeCount * NODE_BONUS);
    }

    public int getRange() {
        int rangeCount = getUpgradeCount(SLOT_RANGE);
        return Math.min(MAX_RANGE, BASE_RANGE + rangeCount * RANGE_BONUS);
    }

    private boolean isUpgradeItem(ItemStack stack, int slot) {
        return switch (slot) {
            case SLOT_SPEED -> stack.is(SPItems.SPEED_UPGRADE.get());
            case SLOT_STACK -> stack.is(SPItems.STACK_UPGRADE.get());
            case SLOT_NODE -> stack.is(SPItems.NODE_UPGRADE.get());
            case SLOT_RANGE -> stack.is(SPItems.RANGE_UPGRADE.get());
            default -> false;
        };
    }

    private boolean passesFilter(ItemStack stack) {
        boolean empty = filterStacks.stream().allMatch(ItemStack::isEmpty);
        if (empty) return true;
        boolean listed = filterStacks.stream().anyMatch(f -> !f.isEmpty() && ItemStack.isSameItem(f, stack));
        return filterAllow == listed;
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;
        if (redstoneActive && !level.hasNeighborSignal(worldPosition)) return;
        if (connectedReceivers.isEmpty()) return;

        if (++tickCounter < getInterval()) return;
        tickCounter = 0;

        Direction facing = getBlockState().getValue(LogisticsSenderBlock.FACING);
        BlockPos sourcePos = worldPosition.relative(facing.getOpposite());
        ResourceHandler<ItemResource> source = level.getCapability(Capabilities.Item.BLOCK, sourcePos, facing);
        if (source == null) return;

        List<BlockPos> targets = getOrderedReceivers();
        if (targets.isEmpty()) return;

        int toSend = getItemsPerTransfer();
        int sent = 0;

        outer:
        for (BlockPos receiverPos : targets) {
            if (sent >= toSend) break;
            if (level.getBlockEntity(receiverPos) instanceof LogisticsReceiverBlockEntity receiver) {
                Direction recFacing = level.getBlockState(receiverPos).getValue(LogisticsReceiverBlock.FACING);
                BlockPos destPos = receiverPos.relative(recFacing.getOpposite());
                ResourceHandler<ItemResource> dest = level.getCapability(Capabilities.Item.BLOCK, destPos, recFacing);
                if (dest == null) continue;

                for (int srcSlot = 0; srcSlot < source.size(); srcSlot++) {
                    if (sent >= toSend) break outer;
                    ItemResource res = source.getResource(srcSlot);
                    if (res.isEmpty()) continue;
                    if (!passesFilter(res.toStack())) continue;

                    int available = source.getAmountAsInt(srcSlot);
                    int canSend = Math.min(toSend - sent, available);
                    if (canSend <= 0) continue;

                    try (Transaction tx = Transaction.openRoot()) {
                        int extracted = source.extract(srcSlot, res, canSend, tx);
                        if (extracted <= 0) continue;
                        int inserted = dest.insert(res, extracted, tx);
                        if (inserted <= 0) continue;
                        if (inserted < extracted) {
                            source.insert(srcSlot, res, extracted - inserted, tx);
                        }
                        tx.commit();
                        sent += inserted;
                    }
                }
            }
        }

        if (roundRobin && !connectedReceivers.isEmpty()) {
            roundRobinIndex = (roundRobinIndex + 1) % connectedReceivers.size();
        }
    }

    private List<BlockPos> getOrderedReceivers() {
        connectedReceivers.removeIf(pos -> {
            if (level == null) return true;
            if (!(level.getBlockEntity(pos) instanceof LogisticsReceiverBlockEntity)) {
                return true;
            }
            return pos.distSqr(worldPosition) > (double) getRange() * getRange();
        });

        if (connectedReceivers.isEmpty()) return List.of();

        if (roundRobin) {
            if (roundRobinIndex >= connectedReceivers.size()) roundRobinIndex = 0;
            List<BlockPos> ordered = new ArrayList<>();
            int size = connectedReceivers.size();
            for (int i = 0; i < size; i++) {
                ordered.add(connectedReceivers.get((roundRobinIndex + i) % size));
            }
            return ordered;
        } else {
            List<BlockPos> sorted = new ArrayList<>(connectedReceivers);
            sorted.sort(Comparator.comparingDouble(p -> p.distSqr(worldPosition)));
            return sorted;
        }
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (level == null) return;
        for (int i = 0; i < UPGRADE_SLOTS; i++) {
            ItemResource res = upgradeInventory.getResource(i);
            if (!res.isEmpty()) {
                ItemStack drop = res.toStack(upgradeInventory.getAmountAsInt(i));
                Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), drop);
            }
        }
        for (BlockPos receiverPos : List.copyOf(connectedReceivers)) {
            if (level.getBlockEntity(receiverPos) instanceof LogisticsReceiverBlockEntity receiver) {
                receiver.removeSender(worldPosition);
            }
        }
        connectedReceivers.clear();
    }
    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (ownerUUID != null) output.putString("Owner", ownerUUID.toString());
        output.putBoolean("RedstoneActive", redstoneActive);
        output.putBoolean("RoundRobin", roundRobin);
        output.putBoolean("FilterAllow", filterAllow);
        output.putInt("RoundRobinIndex", roundRobinIndex);
        output.putInt("TickCounter", tickCounter);
        upgradeInventory.serialize(output);

        var receiverList = output.childrenList("Receivers");
        for (BlockPos pos : connectedReceivers) {
            var child = receiverList.addChild();
            child.putLong("Pos", pos.asLong());
        }

        var filterList = output.list("Filter", ItemStack.OPTIONAL_CODEC);
        for (ItemStack stack : filterStacks) {
            filterList.add(stack);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ownerUUID = input.getString("Owner").map(UUID::fromString).orElse(null);
        redstoneActive = input.getBooleanOr("RedstoneActive", true);
        roundRobin = input.getBooleanOr("RoundRobin", true);
        filterAllow = input.getBooleanOr("FilterAllow", true);
        roundRobinIndex = input.getIntOr("RoundRobinIndex", 0);
        tickCounter = input.getIntOr("TickCounter", 0);
        upgradeInventory.deserialize(input);

        connectedReceivers.clear();
        input.childrenList("Receivers").ifPresent(list ->
                list.stream().forEach(child -> connectedReceivers.add(BlockPos.of(child.getLongOr("Pos", 0L)))));

        filterStacks.clear();
        for (int i = 0; i < FILTER_SLOTS; i++) filterStacks.add(ItemStack.EMPTY);
        input.listOrEmpty("Filter", ItemStack.OPTIONAL_CODEC).stream()
                .limit(FILTER_SLOTS)
                .forEachOrdered(stack -> filterStacks.set(filterStacks.indexOf(ItemStack.EMPTY), stack));
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveCustomOnly(registries);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.smallprogressions.logistics_sender");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new LogisticsSenderMenu(containerId, playerInventory, this);
    }
}