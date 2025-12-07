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
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class WaterReservoirBlockEntity extends BlockEntity {
    private static final int MAX_CAPACITY = 16000; // 16 buckets (1000mb per bucket)

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
            // Only accepts water
            return stack.getFluid() == Fluids.WATER;
        }
    };

    public WaterReservoirBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.WATER_RESERVOIR_BE.get(), pos, state);
    }

    public boolean canFillBucket() {
        return tank.getFluidAmount() >= 1000;
    }

    public void fillBucket() {
        tank.drain(1000, IFluidHandler.FluidAction.EXECUTE);
    }

    public boolean canDrainBucket() {
        return tank.getFluidAmount() + 1000 <= MAX_CAPACITY;
    }

    public void drainBucket() {
        tank.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
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