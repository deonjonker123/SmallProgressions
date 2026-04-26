package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.block.custom.SolarPanelBlock;
import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class SolarPanelBlockEntity extends BlockEntity {

    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.SOLAR_PANEL_BE.get(), pos, state);
    }

    public EnergyHandler getEnergyHandler() {
        long rfPerTick = ((SolarPanelBlock) getBlockState().getBlock()).getRfPerTick();
        return new EnergyHandler() {
            @Override
            public long getAmountAsLong() {
                return getBlockState().getValue(SolarPanelBlock.POWERED) ? rfPerTick : 0;
            }

            @Override
            public long getCapacityAsLong() { return rfPerTick; }

            @Override
            public int insert(int amount, net.neoforged.neoforge.transfer.transaction.TransactionContext tx) { return 0; }

            @Override
            public int extract(int amount, net.neoforged.neoforge.transfer.transaction.TransactionContext tx) {
                if (!getBlockState().getValue(SolarPanelBlock.POWERED)) return 0;
                return (int) Math.min(amount, rfPerTick);
            }
        };
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SolarPanelBlockEntity be) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        boolean powered = isGenerating(serverLevel, pos);
        BlockState currentState = level.getBlockState(pos);

        if (powered != currentState.getValue(SolarPanelBlock.POWERED)) {
            level.setBlockAndUpdate(pos, currentState.setValue(SolarPanelBlock.POWERED, powered));
        }

        if (!powered) return;

        long rfPerTick = ((SolarPanelBlock) currentState.getBlock()).getRfPerTick();

        EnergyHandler storage = level.getCapability(Capabilities.Energy.BLOCK, pos.below(), Direction.UP);
        if (storage != null) {
            try (Transaction tx = Transaction.openRoot()) {
                int accepted = storage.insert((int) rfPerTick, tx);
                if (accepted > 0) tx.commit();
            }
        }
    }

    private static boolean isGenerating(ServerLevel level, BlockPos pos) {
        if (!level.dimensionType().hasSkyLight()) return false;
        if (level.isThundering()) return false;
        if (level.getSkyDarken() != 0) return false;
        return level.canSeeSky(pos.above());
    }
}