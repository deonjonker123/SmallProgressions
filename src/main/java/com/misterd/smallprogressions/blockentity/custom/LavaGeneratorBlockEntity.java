package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class LavaGeneratorBlockEntity extends BlockEntity {
    private static final int MAX_CAPACITY = 1000;

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
            return stack.getFluid() == Fluids.LAVA;
        }
    };

    public LavaGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.LAVA_GENERATOR_BE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, LavaGeneratorBlockEntity blockEntity) {
        if (level.isClientSide()) {
            return;
        }

        int generationRate = Config.getLavaGeneratorMbPerTick();
        if (blockEntity.tank.getFluidAmount() + generationRate <= MAX_CAPACITY) {
            blockEntity.tank.fill(new FluidStack(Fluids.LAVA, generationRate), IFluidHandler.FluidAction.EXECUTE);
        }

        blockEntity.outputToAdjacentTanks();
    }

    private void outputToAdjacentTanks() {
        if (tank.isEmpty()) {
            return;
        }

        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = worldPosition.relative(direction);
            BlockEntity adjacentBE = level.getBlockEntity(adjacentPos);

            if (adjacentBE != null) {
                IFluidHandler adjacentHandler = level.getCapability(Capabilities.FluidHandler.BLOCK, adjacentPos, direction.getOpposite());

                if (adjacentHandler != null) {
                    FluidStack drainStack = tank.drain(1000, IFluidHandler.FluidAction.SIMULATE);
                    if (!drainStack.isEmpty()) {
                        int filled = adjacentHandler.fill(drainStack, IFluidHandler.FluidAction.EXECUTE);
                        if (filled > 0) {
                            tank.drain(filled, IFluidHandler.FluidAction.EXECUTE);
                            return;
                        }
                    }
                }
            }
        }
    }

    public int getFluidAmount() {
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