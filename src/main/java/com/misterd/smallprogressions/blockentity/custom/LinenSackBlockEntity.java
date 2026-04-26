package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.gui.custom.LinenSackMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LinenSackBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SIZE = 9;

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot, ItemStack previous) {
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
        List<ItemStack> stackList = stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).allItemsCopyStream().toList();
        for (int i = 0; i < stackList.size() && i < SIZE; i++) {
            ItemStack s = stackList.get(i).copy();
            try (Transaction tx = Transaction.openRoot()) {
                inventory.insert(i, ItemResource.of(s), s.getCount(), tx);
                tx.commit();
            }
        }
    }

    public void saveToItem(ItemStack stack) {
        if (isEmpty()) return;
        List<ItemStack> stackList = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            ItemResource res = inventory.getResource(i);
            stackList.add(res.isEmpty() ? ItemStack.EMPTY : res.toStack(inventory.getAmountAsInt(i)));
        }
        stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(stackList));
    }

    private boolean isEmpty() {
        for (int i = 0; i < SIZE; i++) {
            if (!inventory.getResource(i).isEmpty()) return false;
        }
        return true;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        inventory.serialize(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        inventory.deserialize(input);
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