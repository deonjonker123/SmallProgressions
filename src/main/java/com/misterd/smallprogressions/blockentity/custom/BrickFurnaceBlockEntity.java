package com.misterd.smallprogressions.blockentity.custom;

import com.misterd.smallprogressions.block.custom.BrickFurnaceBlock;
import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.gui.custom.BrickFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BrickFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SPEED_MULTIPLIER = 4;
    private static final int SMELT_TIME = 200 / SPEED_MULTIPLIER;

    private final Map<ResourceKey<Recipe<?>>, Integer> recipesUsed = new HashMap<>();

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(3) {
        @Override
        protected void onContentsChanged(int slot, ItemStack previous) {
            setChanged();
        }

        @Override
        public boolean isValid(int slot, ItemResource resource) {
            if (slot == 0) {
                ItemStack stack = resource.toStack();
                return level instanceof ServerLevel sl &&
                        sl.recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(stack), sl).isPresent();
            } else if (slot == 1) {
                return level != null && level.fuelValues().isFuel(resource.toStack());
            } else if (slot == 2) {
                return true;
            }
            return false;
        }
    };

    private class FurnaceItemHandler implements ResourceHandler<ItemResource> {
        @Override public int size() { return 3; }

        @Override
        public ItemResource getResource(int index) {
            return inventory.getResource(index);
        }

        @Override
        public long getAmountAsLong(int index) {
            return inventory.getAmountAsLong(index);
        }

        @Override
        public long getCapacityAsLong(int index, ItemResource resource) {
            return inventory.getCapacityAsLong(index, resource);
        }

        @Override
        public boolean isValid(int index, ItemResource resource) {
            return inventory.isValid(index, resource);
        }

        @Override
        public int insert(int index, ItemResource resource, int amount, TransactionContext tx) {
            if (index == 2) return 0;
            return inventory.insert(index, resource, amount, tx);
        }

        @Override
        public int extract(int index, ItemResource resource, int amount, TransactionContext tx) {
            if (index != 2) return 0;
            return inventory.extract(index, resource, amount, tx);
        }
    }

    public ResourceHandler<ItemResource> getItemHandler(@Nullable Direction direction) {
        return new FurnaceItemHandler();
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Item.BLOCK, SPBlockEntities.BRICK_FURNACE_BE.get(),
                (be, dir) -> be instanceof BrickFurnaceBlockEntity furnace ? furnace.getItemHandler(dir) : null);
    }

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
        public int getCount() { return 4; }
    };

    public BrickFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(SPBlockEntities.BRICK_FURNACE_BE.get(), pos, blockState);
    }

    public ItemStack getStack(int slot) {
        ItemResource res = inventory.getResource(slot);
        if (res.isEmpty()) return ItemStack.EMPTY;
        return res.toStack(inventory.getAmountAsInt(slot));
    }

    private void setStack(int slot, ItemStack stack) {
        if (stack.isEmpty()) {
            inventory.set(slot, ItemResource.EMPTY, 0);
        } else {
            inventory.set(slot, ItemResource.of(stack), stack.getCount());
        }
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;

        boolean wasBurning = isBurning();
        boolean dirty = false;
        boolean inventoryChanged = false;

        if (isBurning()) {
            fuelTime--;
            dirty = true;
        }

        ItemStack input = getStack(0);
        ItemStack fuel = getStack(1);
        ItemStack output = getStack(2);

        if (!input.isEmpty()) {
            Optional<RecipeHolder<SmeltingRecipe>> recipeHolder = getRecipe(input);

            if (recipeHolder.isPresent()) {
                SmeltingRecipe recipe = recipeHolder.get().value();
                ItemStack result = recipe.assemble(new SingleRecipeInput(input));

                boolean canInsert = output.isEmpty() ||
                        (ItemStack.isSameItemSameComponents(output, result) &&
                                output.getCount() + result.getCount() <= output.getMaxStackSize());

                if (canInsert) {
                    if (!isBurning() && !fuel.isEmpty()) {
                        int burnTime = level.fuelValues().burnDuration(fuel) / SPEED_MULTIPLIER;
                        if (burnTime > 0) {
                            fuelTime = burnTime;
                            maxFuelTime = burnTime;
                            setStack(1, fuel.copyWithCount(fuel.getCount() - 1));
                            dirty = true;
                            inventoryChanged = true;
                        }
                    }

                    if (isBurning()) {
                        progress++;
                        dirty = true;

                        if (progress >= maxProgress) {
                            progress = 0;
                            if (output.isEmpty()) {
                                setStack(2, result.copy());
                            } else {
                                setStack(2, output.copyWithCount(output.getCount() + result.getCount()));
                            }
                            setStack(0, input.copyWithCount(input.getCount() - 1));
                            recipesUsed.merge(recipeHolder.get().id(), 1, Integer::sum);
                            dirty = true;
                            inventoryChanged = true;
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
            level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(BrickFurnaceBlock.LIT, isBurning()), 3);
        }

        if (dirty) setChanged();
    }

    private Optional<RecipeHolder<SmeltingRecipe>> getRecipe(ItemStack input) {
        if (level instanceof ServerLevel sl) {
            return sl.recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(input), sl);
        }
        return Optional.empty();
    }

    public void awardUsedRecipesAndPopExperience(Player player) {
        if (!(level instanceof ServerLevel sl)) return;
        for (Map.Entry<ResourceKey<Recipe<?>>, Integer> entry : recipesUsed.entrySet()) {
            sl.recipeAccess().byKey(entry.getKey()).ifPresent(holder -> {
                if (holder.value() instanceof SmeltingRecipe smeltingRecipe) {
                    float xpPerCraft = smeltingRecipe.experience();
                    int count = entry.getValue();
                    int totalXp = (int)(xpPerCraft * count);
                    float remainder = xpPerCraft * count - totalXp;
                    if (remainder > 0 && Math.random() < remainder) totalXp++;
                    if (totalXp > 0) ExperienceOrb.award(sl, Vec3.atCenterOf(worldPosition), totalXp);
                }
            });
        }
        recipesUsed.clear();
    }

    public boolean isBurning() { return fuelTime > 0; }

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
        inventory.serialize(output);
        output.putInt("progress", progress);
        output.putInt("fuelTime", fuelTime);
        output.putInt("maxFuelTime", maxFuelTime);
        output.store("recipesUsed", Identifier.CODEC.listOf(),
                recipesUsed.entrySet().stream()
                        .flatMap(e -> java.util.stream.Stream.generate(() -> e.getKey().identifier()).limit(e.getValue()))
                        .toList());
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        inventory.deserialize(input);
        progress = input.getIntOr("progress", 0);
        fuelTime = input.getIntOr("fuelTime", 0);
        maxFuelTime = input.getIntOr("maxFuelTime", 0);
        recipesUsed.clear();
        input.read("recipesUsed", Identifier.CODEC.listOf()).ifPresent(list ->
                list.forEach(id -> recipesUsed.merge(
                        ResourceKey.create(Registries.RECIPE, id), 1, Integer::sum)));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.smallprogressions.brick_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inv, Player player) {
        return new BrickFurnaceMenu(i, inv, this);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveCustomOnly(registries);
    }
}