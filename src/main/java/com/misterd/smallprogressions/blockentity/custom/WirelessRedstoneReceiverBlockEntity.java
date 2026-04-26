package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.gui.custom.WirelessRedstoneReceiverMenu;
import com.misterd.smallprogressions.network.ChannelManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.UUID;

public class WirelessRedstoneReceiverBlockEntity extends BlockEntity implements MenuProvider {
    private static final String TAG_CHANNEL = "channel";
    private static final String TAG_OWNER = "owner";
    private static final int NO_CHANNEL = -1;

    private int channel = NO_CHANNEL;
    private UUID ownerUUID = null;

    public WirelessRedstoneReceiverBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.WIRELESS_REDSTONE_RECEIVER_BE.get(), pos, state);
    }

    public UUID getOwnerUUID() { return ownerUUID; }

    public void setOwnerUUID(UUID uuid) {
        this.ownerUUID = uuid;
        setChanged();
    }

    public int getChannel() { return channel; }

    public void setChannel(int newChannel) {
        if (level instanceof ServerLevel serverLevel && ownerUUID != null) {
            if (channel != NO_CHANNEL) {
                ChannelManager.get(serverLevel).unregisterReceiver(ownerUUID, channel, worldPosition);
            }
            ChannelManager.get(serverLevel).registerReceiver(ownerUUID, newChannel, worldPosition);
            ChannelManager.get(serverLevel).onReceiverChannelChanged(serverLevel, worldPosition, ownerUUID, newChannel);
        }
        this.channel = newChannel;
        setChanged();
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public void clearChannel() {
        if (level instanceof ServerLevel serverLevel && ownerUUID != null && channel != NO_CHANNEL) {
            ChannelManager.get(serverLevel).unregisterReceiver(ownerUUID, channel, worldPosition);
        }
        this.channel = NO_CHANNEL;
        setChanged();
    }

    public boolean hasChannel() { return channel != NO_CHANNEL; }

    public void registerWithChannelManager() {
        if (!(level instanceof ServerLevel serverLevel)) return;
        if (ownerUUID == null || channel == NO_CHANNEL) return;
        ChannelManager.get(serverLevel).registerReceiver(ownerUUID, channel, worldPosition);
    }

    public void unregisterFromChannelManager() {
        if (!(level instanceof ServerLevel serverLevel)) return;
        if (ownerUUID == null || channel == NO_CHANNEL) return;
        ChannelManager.get(serverLevel).unregisterReceiver(ownerUUID, channel, worldPosition);
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
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(TAG_CHANNEL, channel);
        if (ownerUUID != null) output.putString(TAG_OWNER, ownerUUID.toString());
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        channel = input.getIntOr(TAG_CHANNEL, NO_CHANNEL);
        ownerUUID = input.getString(TAG_OWNER).map(UUID::fromString).orElse(null);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.smallprogressions.wireless_redstone_receiver");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new WirelessRedstoneReceiverMenu(containerId, inventory, this);
    }
}