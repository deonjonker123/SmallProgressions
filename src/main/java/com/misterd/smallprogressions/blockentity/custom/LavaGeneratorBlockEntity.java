package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class LavaGeneratorBlockEntity extends BlockEntity {
    private static final int MAX_CAPACITY = FluidType.BUCKET_VOLUME;

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
            return resource.getFluid() == Fluids.LAVA;
        }
    };

    public LavaGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.LAVA_GENERATOR_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;

        int rate = Config.getLavaGeneratorMbPerTick();
        if (tank.getAmountAsInt(0) + rate <= MAX_CAPACITY) {
            try (Transaction tx = Transaction.openRoot()) {
                tank.insert(0, FluidResource.of(Fluids.LAVA), rate, tx);
                tx.commit();
            }
        }

        outputToAdjacentTanks();
    }

    private void outputToAdjacentTanks() {
        FluidResource res = tank.getResource(0);
        if (res.isEmpty()) return;

        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = worldPosition.relative(direction);
            var adjacent = level.getCapability(Capabilities.Fluid.BLOCK, adjacentPos, direction.getOpposite());
            if (adjacent == null) continue;

            int available = tank.getAmountAsInt(0);
            try (Transaction tx = Transaction.openRoot()) {
                int filled = adjacent.insert(res, available, tx);
                if (filled > 0) {
                    tank.extract(0, res, filled, tx);
                    tx.commit();
                    return;
                }
            }
        }
    }

    public int getFluidAmount() {
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