package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.block.custom.EnergyTransmitterBlock;
import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.gui.custom.EnergyTransmitterMenu;
import com.misterd.smallprogressions.network.WirelessNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnergyTransmitterBlockEntity extends BlockEntity implements MenuProvider {
    private static final String TAG_OWNER = "Owner";
    private static final String TAG_PUBLIC = "IsPublic";
    private static final String TAG_CHARGE_INVENTORY = "ChargeInventory";

    private static final int SLOT_DRAIN = 0;
    private static final int SLOT_CHARGE = 1;

    @Nullable
    private UUID ownerUUID = null;
    private boolean isPublic = false;
    private boolean chargeInventory = false;

    private final SimpleContainer inventory = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            EnergyTransmitterBlockEntity.this.setChanged();
        }
    };

    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int index) {
            if (level instanceof ServerLevel serverLevel && ownerUUID != null) {
                long pool = WirelessNetwork.get(serverLevel).getPool(ownerUUID);
                return switch (index) {
                    case 0 -> (int)(pool >> 32);
                    case 1 -> (int)(pool & 0xFFFFFFFFL);
                    case 2 -> (int)(WirelessNetwork.MAX_POOL >> 32);
                    case 3 -> (int)(WirelessNetwork.MAX_POOL & 0xFFFFFFFFL);
                    case 4 -> isPublic ? 1 : 0;
                    case 5 -> chargeInventory ? 1 : 0;
                    default -> 0;
                };
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            if (index == 4) { isPublic = value == 1; setChanged(); updatePublicState(); }
            else if (index == 5) { chargeInventory = value == 1; setChanged(); }
        }

        @Override
        public int getCount() { return 6; }
    };

    public final EnergyHandler energyHandler = new EnergyHandler() {
        @Override
        public long getAmountAsLong() {
            if (level instanceof ServerLevel sl && ownerUUID != null)
                return WirelessNetwork.get(sl).getPool(ownerUUID);
            return 0;
        }

        @Override
        public long getCapacityAsLong() { return WirelessNetwork.MAX_POOL; }

        @Override
        public int insert(int amount, net.neoforged.neoforge.transfer.transaction.TransactionContext tx) {
            if (!(level instanceof ServerLevel sl) || ownerUUID == null) return 0;
            WirelessNetwork network = WirelessNetwork.get(sl);
            long canReceive = Math.min(amount, WirelessNetwork.MAX_POOL - network.getPool(ownerUUID));
            if (canReceive <= 0) return 0;
            network.addToPool(ownerUUID, canReceive);
            setChanged();
            updatePoweredState();
            return (int) canReceive;
        }

        @Override
        public int extract(int amount, net.neoforged.neoforge.transfer.transaction.TransactionContext tx) {
            if (!(level instanceof ServerLevel sl) || ownerUUID == null) return 0;
            WirelessNetwork network = WirelessNetwork.get(sl);
            long canExtract = Math.min(amount, network.getPool(ownerUUID));
            if (canExtract <= 0) return 0;
            network.removeFromPool(ownerUUID, canExtract);
            setChanged();
            updatePoweredState();
            return (int) canExtract;
        }
    };

    public EnergyTransmitterBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.ENERGY_TRANSMITTER_BE.get(), pos, state);
    }

    public EnergyHandler getEnergyStorage() { return energyHandler; }
    public SimpleContainer getInventory() { return inventory; }
    public ContainerData getContainerData() { return containerData; }
    @Nullable public UUID getOwnerUUID() { return ownerUUID; }
    public boolean isPublic() { return isPublic; }
    public boolean isChargeInventory() { return chargeInventory; }
    public void setOwner(UUID uuid) { this.ownerUUID = uuid; setChanged(); }
    public void setPublic(boolean pub) { this.isPublic = pub; setChanged(); updatePublicState(); }
    public void setChargeInventory(boolean b) { this.chargeInventory = b; setChanged(); }

    private void updatePoweredState() {
        if (level == null || ownerUUID == null) return;
        long pool = (level instanceof ServerLevel sl) ? WirelessNetwork.get(sl).getPool(ownerUUID) : 0;
        boolean powered = pool > 0;
        BlockState state = getBlockState();
        if (state.getValue(EnergyTransmitterBlock.POWERED) != powered) {
            level.setBlockAndUpdate(worldPosition, state.setValue(EnergyTransmitterBlock.POWERED, powered));
        }
    }

    private void updatePublicState() {
        if (level instanceof ServerLevel sl && ownerUUID != null) {
            WirelessNetwork.get(sl).setPublic(ownerUUID, isPublic);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, EnergyTransmitterBlockEntity be) {
        if (!(level instanceof ServerLevel serverLevel) || be.ownerUUID == null) return;

        WirelessNetwork network = WirelessNetwork.get(serverLevel);
        long pool = network.getPool(be.ownerUUID);
        boolean powered = pool > 0;
        if (state.getValue(EnergyTransmitterBlock.POWERED) != powered) {
            level.setBlockAndUpdate(pos, state.setValue(EnergyTransmitterBlock.POWERED, powered));
        }

        ItemStack drainStack = be.inventory.getItem(SLOT_DRAIN);
        if (!drainStack.isEmpty()) {
            EnergyHandler itemEnergy = Capabilities.Energy.ITEM.getCapability(drainStack, ItemAccess.forStack(drainStack));
            if (itemEnergy != null) {
                long canReceive = WirelessNetwork.MAX_POOL - network.getPool(be.ownerUUID);
                int toExtract = (int) Math.min(itemEnergy.getAmountAsInt(), canReceive);
                if (toExtract > 0) {
                    try (Transaction tx = Transaction.openRoot()) {
                        int extracted = itemEnergy.extract(toExtract, tx);
                        if (extracted > 0) { network.addToPool(be.ownerUUID, extracted); tx.commit(); be.setChanged(); }
                    }
                }
            }
        }

        ItemStack chargeStack = be.inventory.getItem(SLOT_CHARGE);
        if (!chargeStack.isEmpty()) {
            EnergyHandler itemEnergy = Capabilities.Energy.ITEM.getCapability(chargeStack, ItemAccess.forStack(chargeStack));
            if (itemEnergy != null) {
                int toInsert = (int) Math.min(network.getPool(be.ownerUUID), itemEnergy.getCapacityAsLong() - itemEnergy.getAmountAsLong());
                if (toInsert > 0) {
                    try (Transaction tx = Transaction.openRoot()) {
                        int accepted = itemEnergy.insert(toInsert, tx);
                        if (accepted > 0) { network.removeFromPool(be.ownerUUID, accepted); tx.commit(); be.setChanged(); }
                    }
                }
            }
        }

        if (be.chargeInventory) {
            Player owner = serverLevel.getPlayerByUUID(be.ownerUUID);
            if (owner != null) {
                List<ItemStack> targets = new ArrayList<>();
                for (int i = 0; i < 9; i++) targets.add(owner.getInventory().getItem(i));
                targets.add(owner.getInventory().getItem(Inventory.SLOT_OFFHAND));

                if (ModList.get().isLoaded("curios")) {
                    CuriosApi.getCuriosInventory(owner).ifPresent(curios ->
                            curios.getCurios().values().forEach(handler -> {
                                var stacks = handler.getStacks();
                                for (int i = 0; i < stacks.getSlots(); i++) targets.add(stacks.getStackInSlot(i));
                            })
                    );
                }

                for (ItemStack stack : targets) {
                    if (stack.isEmpty()) continue;
                    EnergyHandler itemEnergy = Capabilities.Energy.ITEM.getCapability(stack, ItemAccess.forStack(stack));
                    if (itemEnergy == null) continue;
                    long poolNow = network.getPool(be.ownerUUID);
                    if (poolNow <= 0) break;
                    int toInsert = (int) Math.min(poolNow, itemEnergy.getCapacityAsLong() - itemEnergy.getAmountAsLong());
                    if (toInsert <= 0) continue;
                    try (Transaction tx = Transaction.openRoot()) {
                        int accepted = itemEnergy.insert(toInsert, tx);
                        if (accepted > 0) { network.removeFromPool(be.ownerUUID, accepted); tx.commit(); be.setChanged(); }
                    }
                }
            }
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (ownerUUID != null) output.putString(TAG_OWNER, ownerUUID.toString());
        output.putBoolean(TAG_PUBLIC, isPublic);
        output.putBoolean(TAG_CHARGE_INVENTORY, chargeInventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ownerUUID = input.getString(TAG_OWNER).map(UUID::fromString).orElse(null);
        isPublic = input.getBooleanOr(TAG_PUBLIC, false);
        chargeInventory = input.getBooleanOr(TAG_CHARGE_INVENTORY, false);
    }

    @Override
    public Component getDisplayName() {
        return getBlockState().getBlock().getName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new EnergyTransmitterMenu(containerId, playerInventory, this, containerData);
    }
}