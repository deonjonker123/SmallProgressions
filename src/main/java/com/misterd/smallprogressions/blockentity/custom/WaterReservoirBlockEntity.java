package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class WaterReservoirBlockEntity extends BlockEntity {
    private static final int MAX_CAPACITY = 16000;

    public final FluidTank tank = new FluidTank(MAX_CAPACITY) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == Fluids.WATER;
        }
    };

    public final IFluidHandler infiniteWaterHandler = new IFluidHandler() {
        @Override
        public int getTanks() {
            return 1;
        }

        @Override
        public FluidStack getFluidInTank(int tank) {
            return new FluidStack(Fluids.WATER, Integer.MAX_VALUE);
        }

        @Override
        public int getTankCapacity(int tank) {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isFluidValid(int tank, FluidStack stack) {
            return stack.getFluid() == Fluids.WATER;
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return 0;
        }

        @Override
        public FluidStack drain(FluidStack resource, FluidAction action) {
            if (resource.getFluid() == Fluids.WATER) {
                return new FluidStack(Fluids.WATER, resource.getAmount());
            }
            return FluidStack.EMPTY;
        }

        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            return new FluidStack(Fluids.WATER, maxDrain);
        }
    };

    public WaterReservoirBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.WATER_RESERVOIR_BE.get(), pos, state);
    }

    public IFluidHandler getFluidHandler() {
        if (Config.isWaterReservoirInfinite()) {
            return infiniteWaterHandler;
        }
        return tank;
    }

    public boolean canFillBucket() {
        if (Config.isWaterReservoirInfinite()) {
            return true;
        }
        return tank.getFluidAmount() >= 1000;
    }

    public void fillBucket() {
        if (!Config.isWaterReservoirInfinite()) {
            tank.drain(1000, IFluidHandler.FluidAction.EXECUTE);
        }
    }

    public boolean canDrainBucket() {
        if (Config.isWaterReservoirInfinite()) {
            return false;
        }
        return tank.getFluidAmount() + 1000 <= MAX_CAPACITY;
    }

    public void drainBucket() {
        if (!Config.isWaterReservoirInfinite()) {
            tank.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
        }
    }

    public int getWaterAmount() {
        return tank.getFluidAmount();
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Tank", tank.writeToNBT(registries, new CompoundTag()));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        tank.readFromNBT(registries, tag.getCompound("Tank"));
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