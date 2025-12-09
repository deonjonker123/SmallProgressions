package com.misterd.smallprogressions.item.custom;

import com.misterd.smallprogressions.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

import java.util.List;

public class BigBucketItem extends Item {
    private int getCapacity() {
        return Config.getBigBucketCapacity();
    }

    public BigBucketItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(stack);
        }

        BlockPos pos = hitResult.getBlockPos();
        Direction direction = hitResult.getDirection();

        if (!level.mayInteract(player, pos) || !player.mayUseItemAt(pos.relative(direction), direction, stack)) {
            return InteractionResultHolder.fail(stack);
        }

        FluidStack stored = getStoredFluid(stack, level);

        if (player.isShiftKeyDown()) {
            if (!stored.isEmpty() && stored.getAmount() >= 1000) {
                return placeFluid(level, player, stack, pos, hitResult, stored);
            }
        } else {
            if (stored.getAmount() < getCapacity()) {
                return pickupFluid(level, player, stack, pos, hitResult);
            }
        }

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (player == null) return InteractionResult.PASS;

        IFluidHandler handler = level.getCapability(Capabilities.FluidHandler.BLOCK, pos, context.getClickedFace());
        if (handler != null) {
            FluidStack stored = getStoredFluid(stack, level);

            if (player.isShiftKeyDown()) {
                if (!stored.isEmpty() && stored.getAmount() >= 1000) {
                    FluidStack toInsert = new FluidStack(stored.getFluid(), 1000);
                    int filled = handler.fill(toInsert, IFluidHandler.FluidAction.EXECUTE);
                    if (filled > 0) {
                        stored.shrink(filled);
                        if (stored.isEmpty()) {
                            clearStoredFluid(stack);
                        } else {
                            setStoredFluid(stack, stored, level);
                        }
                        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.SUCCESS;
                    }
                }
            } else {
                if (stored.getAmount() < getCapacity()) {
                    int spaceLeft = getCapacity() - stored.getAmount();
                    int toDrain = Math.min(1000, spaceLeft);

                    FluidStack drained = handler.drain(toDrain, IFluidHandler.FluidAction.SIMULATE);
                    if (!drained.isEmpty() && (stored.isEmpty() || stored.getFluid() == drained.getFluid())) {
                        FluidStack actual = handler.drain(toDrain, IFluidHandler.FluidAction.EXECUTE);
                        if (!actual.isEmpty()) {
                            if (stored.isEmpty()) {
                                setStoredFluid(stack, actual, level);
                            } else {
                                stored.grow(actual.getAmount());
                                setStoredFluid(stack, stored, level);
                            }
                            level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                            return InteractionResult.SUCCESS;
                        }
                    }
                }
            }
        }

        return InteractionResult.PASS;
    }

    private InteractionResultHolder<ItemStack> pickupFluid(Level level, Player player, ItemStack stack, BlockPos pos, BlockHitResult hitResult) {
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof BucketPickup pickup) {
            FluidStack stored = getStoredFluid(stack, level);

            if (stored.getAmount() + 1000 > getCapacity()) {
                return InteractionResultHolder.fail(stack);
            }

            ItemStack pickedUp = pickup.pickupBlock(player, level, pos, state);
            if (pickedUp.getItem() instanceof BucketItem bucketItem) {
                Fluid fluid = bucketItem.content;
                if (!fluid.isSame(Fluids.EMPTY)) {
                    if (!stored.isEmpty() && !stored.getFluid().isSame(fluid)) {
                        return InteractionResultHolder.fail(stack);
                    }

                    if (stored.isEmpty()) {
                        setStoredFluid(stack, new FluidStack(fluid, 1000), level);
                    } else {
                        stored.grow(1000);
                        setStoredFluid(stack, stored, level);
                    }

                    level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResultHolder.success(stack);
                }
            }
        }

        return InteractionResultHolder.fail(stack);
    }

    private InteractionResultHolder<ItemStack> placeFluid(Level level, Player player, ItemStack stack, BlockPos pos, BlockHitResult hitResult, FluidStack stored) {
        BlockPos placePos = pos.relative(hitResult.getDirection());
        BlockState state = level.getBlockState(placePos);

        if (state.canBeReplaced(stored.getFluid())) {
            if (stored.getAmount() >= 1000) {
                if (level.setBlock(placePos, stored.getFluid().defaultFluidState().createLegacyBlock(), 11)) {
                    stored.shrink(1000);
                    if (stored.isEmpty()) {
                        clearStoredFluid(stack);
                    } else {
                        setStoredFluid(stack, stored, level);
                    }
                    level.playSound(null, placePos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResultHolder.success(stack);
                }
            }
        } else if (state.getBlock() instanceof LiquidBlockContainer container) {
            if (stored.getAmount() >= 1000 && container.canPlaceLiquid(player, level, placePos, state, stored.getFluid())) {
                container.placeLiquid(level, placePos, state, stored.getFluid().defaultFluidState());
                stored.shrink(1000);
                if (stored.isEmpty()) {
                    clearStoredFluid(stack);
                } else {
                    setStoredFluid(stack, stored, level);
                }
                level.playSound(null, placePos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResultHolder.success(stack);
            }
        }

        return InteractionResultHolder.fail(stack);
    }

    private FluidStack getStoredFluid(ItemStack stack, Level level) {
        if (!stack.has(DataComponents.CUSTOM_DATA)) {
            return FluidStack.EMPTY;
        }

        CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        if (tag.contains("Fluid")) {
            return FluidStack.parseOptional(level.registryAccess(), tag.getCompound("Fluid"));
        }

        return FluidStack.EMPTY;
    }

    private void setStoredFluid(ItemStack stack, FluidStack fluid, Level level) {
        CompoundTag nbt = new CompoundTag();
        nbt.put("Fluid", fluid.saveOptional(level.registryAccess()));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
    }

    private void clearStoredFluid(ItemStack stack) {
        stack.remove(DataComponents.CUSTOM_DATA);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (context.level() != null) {
            FluidStack stored = getStoredFluid(stack, context.level());

            if (stored.isEmpty()) {
                tooltipComponents.add(Component.translatable("tooltip.smallprogressions.big_bucket.empty")
                        .withStyle(ChatFormatting.GRAY));
            } else {
                String fluidName = stored.getHoverName().getString();
                int storedBuckets = stored.getAmount() / 1000;
                int maxBuckets = getCapacity() / 1000;
                tooltipComponents.add(Component.translatable("tooltip.smallprogressions.big_bucket.contains",
                                storedBuckets, maxBuckets, fluidName)
                        .withStyle(ChatFormatting.AQUA));
            }

            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("tooltip.smallprogressions.big_bucket.usage")
                    .withStyle(ChatFormatting.GOLD));
            tooltipComponents.add(Component.translatable("tooltip.smallprogressions.big_bucket.right_click")
                    .withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.translatable("tooltip.smallprogressions.big_bucket.shift_right_click")
                    .withStyle(ChatFormatting.GRAY));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}