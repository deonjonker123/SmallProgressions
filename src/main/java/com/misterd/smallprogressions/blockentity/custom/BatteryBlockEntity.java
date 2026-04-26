package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.block.custom.BatteryBlock;
import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.component.SPDataComponents;
import com.misterd.smallprogressions.gui.custom.BatteryBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BatteryBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SLOT_DRAIN = 0;
    private static final int SLOT_CHARGE = 1;

    private long energyStored = 0;

    private final SimpleContainer inventory = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            BatteryBlockEntity.this.setChanged();
        }
    };

    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> (int)(energyStored >> 32);
                case 1 -> (int)(energyStored & 0xFFFFFFFFL);
                case 2 -> (int)(getCapacity() >> 32);
                case 3 -> (int)(getCapacity() & 0xFFFFFFFFL);
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> energyStored = ((long) value << 32) | (energyStored & 0xFFFFFFFFL);
                case 1 -> energyStored = (energyStored & 0xFFFFFFFF00000000L) | (value & 0xFFFFFFFFL);
                case 2, 3 -> {}
            }
        }

        @Override
        public int getCount() { return 4; }
    };

    public final EnergyHandler energyHandler = new EnergyHandler() {
        @Override
        public long getAmountAsLong() { return energyStored; }

        @Override
        public long getCapacityAsLong() { return getCapacity(); }

        @Override
        public int insert(int amount, net.neoforged.neoforge.transfer.transaction.TransactionContext tx) {
            long canReceive = Math.min(amount, Math.min(getTransferRate(), getCapacity() - energyStored));
            if (canReceive <= 0) return 0;
            energyStored += canReceive;
            setChanged();
            updatePoweredState();
            return (int) canReceive;
        }

        @Override
        public int extract(int amount, net.neoforged.neoforge.transfer.transaction.TransactionContext tx) {
            long canExtract = Math.min(amount, Math.min(getTransferRate(), energyStored));
            if (canExtract <= 0) return 0;
            energyStored -= canExtract;
            setChanged();
            updatePoweredState();
            return (int) canExtract;
        }
    };

    public BatteryBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.BATTERY_BE.get(), pos, state);
    }

    public EnergyHandler getEnergyStorage() { return energyHandler; }
    public SimpleContainer getInventory() { return inventory; }
    public ContainerData getContainerData() { return containerData; }
    public long getCapacity() { return ((BatteryBlock) getBlockState().getBlock()).getCapacity(); }
    public long getTransferRate() { return ((BatteryBlock) getBlockState().getBlock()).getTransferRate(); }
    public long getEnergyStoredLong() { return energyStored; }

    private void updatePoweredState() {
        if (level == null) return;
        boolean powered = energyStored > 0;
        BlockState state = getBlockState();
        if (state.getValue(BatteryBlock.POWERED) != powered) {
            level.setBlockAndUpdate(worldPosition, state.setValue(BatteryBlock.POWERED, powered));
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BatteryBlockEntity be) {
        if (level.isClientSide()) return;

        ItemStack drainStack = be.inventory.getItem(SLOT_DRAIN);
        if (!drainStack.isEmpty()) {
            EnergyHandler itemEnergy = Capabilities.Energy.ITEM.getCapability(drainStack, ItemAccess.forStack(drainStack));
            if (itemEnergy != null) {
                int toExtract = (int) Math.min(be.getTransferRate(), be.getCapacity() - be.energyStored);
                if (toExtract > 0) {
                    try (Transaction tx = Transaction.openRoot()) {
                        int extracted = itemEnergy.extract(toExtract, tx);
                        if (extracted > 0) {
                            be.energyStored += extracted;
                            tx.commit();
                            be.setChanged();
                            be.updatePoweredState();
                        }
                    }
                }
            }
        }

        ItemStack chargeStack = be.inventory.getItem(SLOT_CHARGE);
        if (!chargeStack.isEmpty()) {
            EnergyHandler itemEnergy = Capabilities.Energy.ITEM.getCapability(chargeStack, ItemAccess.forStack(chargeStack));
            if (itemEnergy != null) {
                int toInsert = (int) Math.min(be.getTransferRate(), be.energyStored);
                if (toInsert > 0) {
                    try (Transaction tx = Transaction.openRoot()) {
                        int accepted = itemEnergy.insert(toInsert, tx);
                        if (accepted > 0) {
                            be.energyStored -= accepted;
                            tx.commit();
                            be.setChanged();
                            be.updatePoweredState();
                        }
                    }
                }
            }
        }

        if (be.energyStored > 0) {
            List<EnergyHandler> receivers = new ArrayList<>();
            for (Direction side : Direction.values()) {
                EnergyHandler neighbor = level.getCapability(Capabilities.Energy.BLOCK, pos.relative(side), side.getOpposite());
                if (neighbor != null) receivers.add(neighbor);
            }

            if (!receivers.isEmpty()) {
                int perSide = (int) Math.min(be.getTransferRate() / receivers.size(), be.energyStored);
                for (EnergyHandler receiver : receivers) {
                    if (perSide <= 0) break;
                    try (Transaction tx = Transaction.openRoot()) {
                        int accepted = receiver.insert(perSide, tx);
                        if (accepted > 0) {
                            be.energyStored -= accepted;
                            tx.commit();
                            be.setChanged();
                            be.updatePoweredState();
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putLong("Energy", energyStored);
        // inventory serialization — check if SimpleContainer has a ValueOutput method in your version
        // if not, keep using CompoundTag via saveAdditional(CompoundTag, HolderLookup.Provider)
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        energyStored = input.getLongOr("Energy", 0L);
    }

    public void saveEnergyToItem(ItemStack stack) {
        if (energyStored > 0) {
            stack.set(SPDataComponents.ENERGY_STORED.get(), energyStored);
        }
    }

    public void loadEnergyFromItem(ItemStack stack) {
        Long stored = stack.get(SPDataComponents.ENERGY_STORED.get());
        if (stored != null) {
            energyStored = stored;
            updatePoweredState();
        }
    }

    @Override
    public Component getDisplayName() {
        return getBlockState().getBlock().getName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new BatteryBlockMenu(containerId, playerInventory, this, containerData);
    }
}