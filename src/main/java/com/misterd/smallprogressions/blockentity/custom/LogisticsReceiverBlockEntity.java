package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LogisticsReceiverBlockEntity extends BlockEntity {
    @Nullable private UUID ownerUUID = null;
    private final List<BlockPos> connectedSenders = new ArrayList<>();

    public LogisticsReceiverBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.LOGISTICS_RECEIVER_BE.get(), pos, state);
    }

    public void setOwner(UUID uuid) { this.ownerUUID = uuid; setChanged(); }
    @Nullable public UUID getOwnerUUID() { return ownerUUID; }

    public List<BlockPos> getConnectedSenders() { return connectedSenders; }

    public void addSender(BlockPos pos) {
        if (!connectedSenders.contains(pos)) {
            connectedSenders.add(pos);
            setChanged();
        }
    }

    public void removeSender(BlockPos pos) {
        connectedSenders.remove(pos);
        setChanged();
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (level == null || level.isClientSide()) return;
        for (BlockPos senderPos : List.copyOf(connectedSenders)) {
            if (level.getBlockEntity(senderPos) instanceof LogisticsSenderBlockEntity sender) {
                sender.removeReceiver(worldPosition);
            }
        }
        connectedSenders.clear();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (ownerUUID != null) output.putString("Owner", ownerUUID.toString());
        var list = output.childrenList("Senders");
        for (BlockPos pos : connectedSenders) {
            list.addChild().putLong("Pos", pos.asLong());
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ownerUUID = input.getString("Owner").map(UUID::fromString).orElse(null);
        connectedSenders.clear();
        input.childrenList("Senders").ifPresent(list ->
                list.stream().forEach(child -> connectedSenders.add(BlockPos.of(child.getLongOr("Pos", 0L)))));
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveCustomOnly(registries);
    }
}