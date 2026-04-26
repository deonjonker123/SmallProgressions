package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

public class WaterReservoirBlockEntity extends BlockEntity {
    private static final int MAX_CAPACITY = 16000;
    private static final FluidResource WATER = FluidResource.of(Fluids.WATER);

    public final FluidStacksResourceHandler tank = new FluidStacksResourceHandler(1, MAX_CAPACITY) {
        @Override
        protected void onContentsChanged(int slot, FluidStack previous) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isValid(int slot, FluidResource resource) {
            return resource.getFluid() == Fluids.WATER;
        }
    };

    public final ResourceHandler<FluidResource> infiniteWaterHandler = new ResourceHandler<>() {
        @Override
        public int size() { return 1; }

        @Override
        public FluidResource getResource(int index) { return WATER; }

        @Override
        public long getAmountAsLong(int index) { return Integer.MAX_VALUE; }

        @Override
        public long getCapacityAsLong(int index, FluidResource resource) { return Integer.MAX_VALUE; }

        @Override
        public boolean isValid(int index, FluidResource resource) {
            return resource.getFluid() == Fluids.WATER;
        }

        @Override
        public int insert(int index, FluidResource resource, int amount, TransactionContext tx) { return 0; }

        @Override
        public int extract(int index, FluidResource resource, int amount, TransactionContext tx) {
            return resource.getFluid() == Fluids.WATER ? amount : 0;
        }
    };

    public WaterReservoirBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.WATER_RESERVOIR_BE.get(), pos, state);
    }

    public ResourceHandler<FluidResource> getFluidHandler() {
        return Config.isWaterReservoirInfinite() ? infiniteWaterHandler : tank;
    }

    public boolean canFillBucket() {
        if (Config.isWaterReservoirInfinite()) return true;
        return tank.getAmountAsInt(0) >= FluidType.BUCKET_VOLUME;
    }

    public void fillBucket() {
        if (!Config.isWaterReservoirInfinite()) {
            try (Transaction tx = Transaction.openRoot()) {
                tank.extract(0, WATER, FluidType.BUCKET_VOLUME, tx);
                tx.commit();
            }
        }
    }

    public boolean canDrainBucket() {
        if (Config.isWaterReservoirInfinite()) return false;
        return tank.getAmountAsInt(0) + FluidType.BUCKET_VOLUME <= MAX_CAPACITY;
    }

    public void drainBucket() {
        if (!Config.isWaterReservoirInfinite()) {
            try (Transaction tx = Transaction.openRoot()) {
                tank.insert(0, WATER, FluidType.BUCKET_VOLUME, tx);
                tx.commit();
            }
        }
    }

    public int getWaterAmount() {
        return tank.getAmountAsInt(0);
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        tank.serialize(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        tank.deserialize(input);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}