package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.block.custom.EnergyReceiverBlock;
import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.network.WirelessNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EnergyReceiverBlockEntity extends BlockEntity {
    private static final String TAG_OWNER = "Owner";

    @Nullable
    private UUID ownerUUID = null;

    public EnergyReceiverBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.ENERGY_RECEIVER_BE.get(), pos, state);
    }

    public void setOwner(UUID uuid) { this.ownerUUID = uuid; setChanged(); }
    @Nullable public UUID getOwnerUUID() { return ownerUUID; }

    public static void tick(Level level, BlockPos pos, BlockState state, EnergyReceiverBlockEntity be) {
        if (!(level instanceof ServerLevel serverLevel) || be.ownerUUID == null) return;

        WirelessNetwork network = WirelessNetwork.get(serverLevel);

        List<UUID> accessiblePools = new ArrayList<>();

        if (network.getPool(be.ownerUUID) > 0) {
            accessiblePools.add(be.ownerUUID);
        }

        Map<UUID, Long> publicPools = network.getPublicPools();
        publicPools.forEach((uuid, amount) -> {
            if (!uuid.equals(be.ownerUUID) && amount > 0) {
                accessiblePools.add(uuid);
            }
        });

        if (accessiblePools.isEmpty()) return;

        long transferRate = ((EnergyReceiverBlock) state.getBlock()).getTransferRate();
        long perPool = transferRate / accessiblePools.size();
        if (perPool <= 0) return;

        for (Direction side : Direction.values()) {
            EnergyHandler storage = level.getCapability(Capabilities.Energy.BLOCK, pos.relative(side), side.getOpposite());
            if (storage == null) continue;

            for (UUID poolOwner : accessiblePools) {
                long available = network.getPool(poolOwner);
                if (available <= 0) continue;
                int toInsert = (int) Math.min(perPool, available);
                try (Transaction tx = Transaction.openRoot()) {
                    int accepted = storage.insert(toInsert, tx);
                    if (accepted > 0) {
                        network.removeFromPool(poolOwner, accepted);
                        tx.commit();
                    }
                }
            }
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (ownerUUID != null) output.putString(TAG_OWNER, ownerUUID.toString());
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ownerUUID = input.getString(TAG_OWNER).map(UUID::fromString).orElse(null);
    }
}