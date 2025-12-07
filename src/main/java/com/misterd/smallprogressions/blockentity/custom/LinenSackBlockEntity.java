package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.gui.custom.LinenSackMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

public class LinenSackBlockEntity extends BlockEntity implements MenuProvider {
    private static final int INVENTORY_SIZE = 9;

    public final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public LinenSackBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.LINEN_SACK_BE.get(), pos, state);
    }

    public void loadFromItem(ItemStack stack) {
        ItemContainerContents contents =
                stack.getOrDefault(DataComponents.CONTAINER,
                        ItemContainerContents.EMPTY);

        List<ItemStack> stackList = contents.stream().toList();
        for (int i = 0; i < stackList.size() && i < INVENTORY_SIZE; i++) {
            inventory.setStackInSlot(i, stackList.get(i).copy());
        }
    }

    public void saveToItem(ItemStack stack) {
        if (!isEmpty()) {
            List<ItemStack> stackList = new java.util.ArrayList<>();
            for (int i = 0; i < INVENTORY_SIZE; i++) {
                stackList.add(inventory.getStackInSlot(i).copy());
            }

            ItemContainerContents contents =
                    ItemContainerContents.fromItems(stackList);

            stack.set(DataComponents.CONTAINER, contents);
        }
    }

    private boolean isEmpty() {
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.smallprogressions.linen_sack");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new LinenSackMenu(containerId, playerInventory, this);
    }
}