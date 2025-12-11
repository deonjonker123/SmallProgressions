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
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class CobblestoneGeneratorBlockEntity extends BlockEntity {
    private static final int PUSH_INTERVAL = 20;
    private static final Direction[] PUSH_ORDER = {
            Direction.UP,
            Direction.DOWN,
            Direction.NORTH,
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST
    };

    private final int tier;
    private int generationCounter = 0;
    private int pushCounter = 0;

    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public int getSlotLimit(int slot) {
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

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            return;
        }

        pushCounter++;
        if (pushCounter >= PUSH_INTERVAL) {
            pushCounter = 0;
            tryPushToAdjacentInventories(level, pos);
        }

        ItemStack currentStack = inventory.getStackInSlot(0);
        if (currentStack.isEmpty() || (currentStack.getCount() < 64 && currentStack.is(Items.COBBLESTONE))) {
            generationCounter++;
            int interval = getGenerationInterval();

            if (generationCounter >= interval) {
                generationCounter = 0;
                generateCobblestone();
            }
        }
    }

    private void generateCobblestone() {
        ItemStack currentStack = inventory.getStackInSlot(0);

        if (currentStack.isEmpty()) {
            inventory.setStackInSlot(0, new ItemStack(Items.COBBLESTONE, 1));
        } else if (currentStack.is(Items.COBBLESTONE) && currentStack.getCount() < 64) {
            currentStack.grow(1);
        }
    }

    private void tryPushToAdjacentInventories(Level level, BlockPos pos) {
        ItemStack stackInSlot = inventory.getStackInSlot(0);
        if (stackInSlot.isEmpty()) {
            return;
        }

        for (Direction direction : PUSH_ORDER) {
            BlockPos adjacentPos = pos.relative(direction);
            IItemHandler adjacentInventory = level.getCapability(
                    Capabilities.ItemHandler.BLOCK,
                    adjacentPos,
                    direction.getOpposite()
            );

            if (adjacentInventory != null) {
                ItemStack toInsert = stackInSlot.copy();
                ItemStack remainder = insertItem(adjacentInventory, toInsert);

                if (remainder.getCount() < toInsert.getCount()) {
                    inventory.setStackInSlot(0, remainder);

                    if (remainder.isEmpty()) {
                        return;
                    }
                }
            }
        }
    }

    private ItemStack insertItem(IItemHandler inventory, ItemStack stack) {
        ItemStack remaining = stack.copy();

        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            remaining = inventory.insertItem(slot, remaining, false);

            if (remaining.isEmpty()) {
                break;
            }
        }

        return remaining;
    }

    public void drops() {
        ItemStack buffer = inventory.getStackInSlot(0);
        if (!buffer.isEmpty()) {
            Containers.dropItemStack(this.level, this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), buffer);
        }
    }

    public int getTier() {
        return tier;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("Tier", tier);
        tag.putInt("GenerationCounter", generationCounter);
        tag.putInt("PushCounter", pushCounter);
        tag.put("Inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        generationCounter = tag.getInt("GenerationCounter");
        pushCounter = tag.getInt("PushCounter");
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