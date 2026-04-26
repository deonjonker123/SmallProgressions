package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class HarvesterBlockEntity extends BlockEntity {
    private static final int HARVEST_INTERVAL = 20;
    private static final int HOE_SLOT = 0;

    private int tickCounter = 0;
    private boolean requiresRedstone = false;

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(1) {
        @Override
        protected void onContentsChanged(int slot, ItemStack previous) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isValid(int slot, ItemResource resource) {
            return resource.toStack().getItem() instanceof HoeItem;
        }
    };

    public HarvesterBlockEntity(BlockPos pos, BlockState state) {
        super(SPBlockEntities.HARVESTER_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;
        if (requiresRedstone && !level.hasNeighborSignal(worldPosition)) return;

        ItemStack hoeStack = getHoeStack();
        if (hoeStack.isEmpty() || !(hoeStack.getItem() instanceof HoeItem hoeItem)) return;

        if (++tickCounter >= HARVEST_INTERVAL) {
            tickCounter = 0;
            harvestCrops(getHoeRadius(hoeItem));
        }
    }

    private void harvestCrops(int radius) {
        int range = (radius - 1) / 2;
        for (int x = -range; x <= range; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos pos = worldPosition.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();
                    if (!(block instanceof CropBlock cropBlock)) continue;
                    try {
                        IntegerProperty ageProperty = null;
                        for (var property : state.getProperties()) {
                            if (property instanceof IntegerProperty intProp && property.getName().equals("age")) {
                                ageProperty = intProp;
                                break;
                            }
                        }
                        if (ageProperty != null && state.getValue(ageProperty) >= cropBlock.getMaxAge()) {
                            Block.dropResources(state, level, pos);
                            level.setBlock(pos, state.setValue(ageProperty, 0), 2);
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
    }

    private int getHoeRadius(HoeItem hoeItem) {
        ItemStack stack = getHoeStack();
        if (stack.is(Items.NETHERITE_HOE)) return 9;
        if (stack.is(Items.DIAMOND_HOE)) return 7;
        if (stack.is(Items.IRON_HOE) || stack.is(Items.GOLDEN_HOE)) return 5;
        return 3;
    }

    public ItemStack getHoeStack() {
        ItemResource res = inventory.getResource(HOE_SLOT);
        if (res.isEmpty()) return ItemStack.EMPTY;
        return res.toStack(inventory.getAmountAsInt(HOE_SLOT));
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (level == null) return;
        SimpleContainer drop = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            ItemResource res = inventory.getResource(i);
            if (!res.isEmpty()) drop.setItem(i, res.toStack(inventory.getAmountAsInt(i)));
        }
        Containers.dropContents(level, worldPosition, drop);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("TickCounter", tickCounter);
        output.putBoolean("RequiresRedstone", requiresRedstone);
        inventory.serialize(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        tickCounter = input.getIntOr("TickCounter", 0);
        requiresRedstone = input.getBooleanOr("RequiresRedstone", false);
        inventory.deserialize(input);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public boolean requiresRedstone() { return requiresRedstone; }

    public void setRequiresRedstone(boolean v) {
        requiresRedstone = v;
        setChanged();
    }
}