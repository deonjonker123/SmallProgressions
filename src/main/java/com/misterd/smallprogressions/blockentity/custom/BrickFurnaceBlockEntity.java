package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.block.custom.BrickFurnaceBlock;
import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.gui.custom.BrickFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Optional;

public class BrickFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SPEED_MULTIPLIER = 4;
    private static final int SMELT_TIME = 200 / SPEED_MULTIPLIER;

    public final ItemStackHandler inventory = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == 0) {
                return level != null && level.getRecipeManager()
                        .getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(stack), level)
                        .isPresent();
            } else if (slot == 1) {
                return stack.getBurnTime(RecipeType.SMELTING) > 0;
            } else {
                return false;
            }
        }
    };

    private int progress = 0;
    private int maxProgress = SMELT_TIME;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                case 2 -> fuelTime;
                case 3 -> maxFuelTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
                case 2 -> fuelTime = value;
                case 3 -> maxFuelTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public IItemHandler getCapabilityHandler() {
        return new IItemHandler() {
            @Override
            public int getSlots() {
                return inventory.getSlots();
            }

            @Override
            public ItemStack getStackInSlot(int slot) {
                return inventory.getStackInSlot(slot);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                if (slot == 2) {
                    return stack;
                }

                if (slot == 0) {
                    if (level != null && level.getRecipeManager()
                            .getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(stack), level)
                            .isPresent()) {
                        return inventory.insertItem(slot, stack, simulate);
                    }
                    return stack;
                }

                if (slot == 1) {
                    if (stack.getBurnTime(RecipeType.SMELTING) > 0) {
                        return inventory.insertItem(slot, stack, simulate);
                    }
                    return stack;
                }

                return stack;
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (slot == 2) {
                    return inventory.extractItem(slot, amount, simulate);
                }
                return ItemStack.EMPTY;
            }

            @Override
            public int getSlotLimit(int slot) {
                return inventory.getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return inventory.isItemValid(slot, stack);
            }
        };
    }

    public BrickFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(SPBlockEntities.BRICK_FURNACE_BE.get(), pos, blockState);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;

        boolean wasBurning = isBurning();
        boolean dirty = false;

        if (isBurning()) {
            fuelTime--;
            dirty = true;
        }

        ItemStack input = inventory.getStackInSlot(0);
        ItemStack fuel = inventory.getStackInSlot(1);
        ItemStack output = inventory.getStackInSlot(2);

        if (!input.isEmpty()) {
            Optional<RecipeHolder<SmeltingRecipe>> recipeHolder = level.getRecipeManager()
                    .getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(input), level);

            if (recipeHolder.isPresent()) {
                SmeltingRecipe recipe = recipeHolder.get().value();
                ItemStack result = recipe.getResultItem(level.registryAccess());

                boolean canInsertOutput = output.isEmpty() ||
                        (ItemStack.isSameItemSameComponents(output, result) &&
                                output.getCount() + result.getCount() <= output.getMaxStackSize());

                if (canInsertOutput) {
                    if (!isBurning() && !fuel.isEmpty()) {
                        int burnTime = fuel.getBurnTime(RecipeType.SMELTING) / SPEED_MULTIPLIER;
                        if (burnTime > 0) {
                            fuelTime = burnTime;
                            maxFuelTime = burnTime;
                            fuel.shrink(1);
                            dirty = true;
                        }
                    }

                    if (isBurning()) {
                        progress++;
                        dirty = true;

                        if (progress >= maxProgress) {
                            progress = 0;

                            if (output.isEmpty()) {
                                inventory.setStackInSlot(2, result.copy());
                            } else {
                                output.grow(result.getCount());
                            }

                            input.shrink(1);
                            dirty = true;
                        }
                    }
                } else {
                    progress = 0;
                    dirty = true;
                }
            } else {
                progress = 0;
                dirty = true;
            }
        } else {
            progress = 0;
            dirty = true;
        }

        if (wasBurning != isBurning()) {
            dirty = true;
            level.setBlock(pos, state.setValue(BrickFurnaceBlock.LIT, isBurning()), 3);
        }

        if (dirty) {
            setChanged();
        }
    }

    private boolean isBurning() {
        return fuelTime > 0;
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("progress", progress);
        tag.putInt("fuelTime", fuelTime);
        tag.putInt("maxFuelTime", maxFuelTime);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("progress");
        fuelTime = tag.getInt("fuelTime");
        maxFuelTime = tag.getInt("maxFuelTime");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.smallprogressions.brick_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new BrickFurnaceMenu(i, inventory, this);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}