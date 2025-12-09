package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.item.equipment.ScytheItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.items.ItemStackHandler;

public class HarvesterBlockEntity extends BlockEntity {
    private static final int HARVEST_INTERVAL = 20;
    private static final int SCYTHE_SLOT = 0;

    private int tickCounter = 0;
    private boolean requiresRedstone = false;

    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return stack.getItem() instanceof ScytheItem;
        }
    };

    public final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return requiresRedstone ? 1 : 0;
        }

        @Override
        public void set(int index, int value) {
            requiresRedstone = value == 1;
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    public HarvesterBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.HARVESTER_BE.get(), pos, state);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            return;
        }

        if (requiresRedstone && !level.hasNeighborSignal(pos)) {
            return;
        }

        ItemStack scytheStack = inventory.getStackInSlot(SCYTHE_SLOT);
        if (scytheStack.isEmpty() || !(scytheStack.getItem() instanceof ScytheItem scytheItem)) {
            return;
        }

        tickCounter++;
        if (tickCounter >= HARVEST_INTERVAL) {
            tickCounter = 0;
            harvestCrops(level, pos, scytheItem);
        }
    }

    private void harvestCrops(Level level, BlockPos harvesterPos, ScytheItem scytheItem) {
        int radius = getScytheRadius(scytheItem);
        int range = (radius - 1) / 2;

        for (int x = -range; x <= range; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos pos = harvesterPos.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();

                    if (block instanceof CropBlock cropBlock) {
                        try {
                            IntegerProperty ageProperty = null;
                            for (var property : state.getProperties()) {
                                if (property instanceof IntegerProperty intProp && property.getName().equals("age")) {
                                    ageProperty = intProp;
                                    break;
                                }
                            }

                            if (ageProperty != null) {
                                int currentAge = state.getValue(ageProperty);
                                int maxAge = cropBlock.getMaxAge();

                                if (currentAge >= maxAge) {
                                    Block.dropResources(state, level, pos);

                                    level.setBlock(pos, state.setValue(ageProperty, 0), 2);
                                }
                            }
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
    }

    private int getScytheRadius(ScytheItem scytheItem) {
        return scytheItem.getRadius();
    }

    public boolean requiresRedstone() {
        return requiresRedstone;
    }

    public void setRequiresRedstone(boolean requiresRedstone) {
        this.requiresRedstone = requiresRedstone;
        setChanged();
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("TickCounter", tickCounter);
        tag.putBoolean("RequiresRedstone", requiresRedstone);
        tag.put("Inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        tickCounter = tag.getInt("TickCounter");
        requiresRedstone = tag.getBoolean("RequiresRedstone");
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
}