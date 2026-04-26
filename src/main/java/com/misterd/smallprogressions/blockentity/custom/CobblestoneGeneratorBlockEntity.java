package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class CobblestoneGeneratorBlockEntity extends BlockEntity {
    private static final int PUSH_INTERVAL = 20;
    private static final Direction[] PUSH_ORDER = {
            Direction.UP, Direction.DOWN,
            Direction.NORTH, Direction.SOUTH,
            Direction.EAST, Direction.WEST
    };

    private final int tier;
    private int generationCounter = 0;
    private int pushCounter = 0;

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(1) {
        @Override
        protected void onContentsChanged(int slot, ItemStack previous) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public long getCapacityAsLong(int slot, ItemResource resource) {
            return 64;
        }
    };

    public CobblestoneGeneratorBlockEntity(BlockPos pos, BlockState state) {
        this(pos, state, 1);
    }

    public CobblestoneGeneratorBlockEntity(BlockPos pos, BlockState state, int tier) {
        super(SPBlockEntities.COBBLESTONE_GENERATOR_BE.get(), pos, state);
        this.tier = tier;
    }

    private int getGenerationInterval() {
        return switch (tier) {
            case 1 -> Config.getCobblestoneGenTier1Ticks();
            case 2 -> Config.getCobblestoneGenTier2Ticks();
            case 3 -> Config.getCobblestoneGenTier3Ticks();
            case 4 -> Config.getCobblestoneGenTier4Ticks();
            case 5 -> Config.getCobblestoneGenTier5Ticks();
            default -> 40;
        };
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;

        if (++pushCounter >= PUSH_INTERVAL) {
            pushCounter = 0;
            tryPushToAdjacentInventories();
        }

        ItemStack current = getSlot0();
        if (current.isEmpty() || (current.getCount() < 64 && current.is(Items.COBBLESTONE))) {
            if (++generationCounter >= getGenerationInterval()) {
                generationCounter = 0;
                generateCobblestone();
            }
        }
    }

    private void generateCobblestone() {
        ItemStack current = getSlot0();
        if (current.isEmpty()) {
            setSlot0(new ItemStack(Items.COBBLESTONE, 1));
        } else if (current.is(Items.COBBLESTONE) && current.getCount() < 64) {
            setSlot0(current.copyWithCount(current.getCount() + 1));
        }
    }

    private void tryPushToAdjacentInventories() {
        ItemStack stack = getSlot0();
        if (stack.isEmpty()) return;

        for (Direction direction : PUSH_ORDER) {
            BlockPos adjacentPos = worldPosition.relative(direction);
            var adjacent = level.getCapability(Capabilities.Item.BLOCK, adjacentPos, direction.getOpposite());
            if (adjacent == null) continue;

            ItemResource res = ItemResource.of(stack);
            int remaining = stack.getCount();

            for (int slot = 0; slot < adjacent.size() && remaining > 0; slot++) {
                try (Transaction tx = Transaction.openRoot()) {
                    int inserted = adjacent.insert(slot, res, remaining, tx);
                    tx.commit();
                    remaining -= inserted;
                }
            }

            if (remaining < stack.getCount()) {
                setSlot0(remaining == 0 ? ItemStack.EMPTY : stack.copyWithCount(remaining));
                if (remaining == 0) return;
                stack = getSlot0();
            }
        }
    }

    public ItemStack getSlot0() {
        ItemResource res = inventory.getResource(0);
        if (res.isEmpty()) return ItemStack.EMPTY;
        return res.toStack(inventory.getAmountAsInt(0));
    }

    public void setSlot0(ItemStack stack) {
        try (Transaction tx = Transaction.openRoot()) {
            ItemResource existing = inventory.getResource(0);
            int existingAmount = inventory.getAmountAsInt(0);
            if (!existing.isEmpty() && existingAmount > 0) {
                inventory.extract(0, existing, existingAmount, tx);
            }
            if (!stack.isEmpty()) {
                inventory.insert(0, ItemResource.of(stack), stack.getCount(), tx);
            }
            tx.commit();
        }
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (level == null) return;
        ItemStack stack = getSlot0();
        if (!stack.isEmpty()) {
            Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), stack);
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Tier", tier);
        output.putInt("GenerationCounter", generationCounter);
        output.putInt("PushCounter", pushCounter);
        inventory.serialize(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        generationCounter = input.getIntOr("GenerationCounter", 0);
        pushCounter       = input.getIntOr("PushCounter", 0);
        inventory.deserialize(input);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public int getTier() { return tier; }
}