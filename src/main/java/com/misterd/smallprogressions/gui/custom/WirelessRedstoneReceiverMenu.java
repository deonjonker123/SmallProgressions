package com.misterd.smallprogressions.gui.custom;

import com.misterd.smallprogressions.blockentity.custom.WirelessRedstoneReceiverBlockEntity;
import com.misterd.smallprogressions.gui.SPMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WirelessRedstoneReceiverMenu extends AbstractContainerMenu {

    private final WirelessRedstoneReceiverBlockEntity blockEntity;
    private final ContainerLevelAccess access;

    public WirelessRedstoneReceiverMenu(int containerId, Inventory inventory, WirelessRedstoneReceiverBlockEntity blockEntity) {
        super(SPMenuTypes.WIRELESS_REDSTONE_RECEIVER_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.access = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
    }

    public WirelessRedstoneReceiverMenu(int containerId, Inventory inventory, FriendlyByteBuf buf) {
        this(containerId, inventory, getBlockEntity(inventory, buf));
    }

    private static WirelessRedstoneReceiverBlockEntity getBlockEntity(Inventory inventory, FriendlyByteBuf buf) {
        BlockEntity be = inventory.player.level().getBlockEntity(buf.readBlockPos());
        if (be instanceof WirelessRedstoneReceiverBlockEntity receiver) return receiver;
        throw new IllegalStateException("Block entity is not a WirelessReceiverBlockEntity");
    }

    public int getChannel() {
        return blockEntity.getChannel();
    }

    public void setChannel(int channel) {
        blockEntity.setChannel(channel);
    }

    public void clearChannel() {
        blockEntity.clearChannel();
    }

    public boolean hasChannel() {
        return blockEntity.hasChannel();
    }

    public BlockPos getBlockPos() {
        return blockEntity.getBlockPos();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, blockEntity.getBlockState().getBlock());
    }
}