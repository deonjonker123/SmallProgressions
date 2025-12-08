package com.misterd.smallprogressions.item.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.custom.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import java.util.List;

public class TankUpgradeItem extends Item {
    private final TankTier targetTier;

    public enum TankTier {
        COPPER(0, 16000),
        IRON(1, 32000),
        GOLD(2, 64000),
        DIAMOND(3, 128000);

        private final int level;
        private final int capacity;

        TankTier(int level, int capacity) {
            this.level = level;
            this.capacity = capacity;
        }

        public int getLevel() {
            return level;
        }

        public int getCapacity() {
            return capacity;
        }

        public Block getBlock() {
            return switch (this) {
                case COPPER -> SPBlocks.COPPER_TANK.get();
                case IRON -> SPBlocks.IRON_TANK.get();
                case GOLD -> SPBlocks.GOLD_TANK.get();
                case DIAMOND -> SPBlocks.DIAMOND_TANK.get();
            };
        }

        public static TankTier fromBlock(Block block) {
            if (block == SPBlocks.COPPER_TANK.get()) return COPPER;
            if (block == SPBlocks.IRON_TANK.get()) return IRON;
            if (block == SPBlocks.GOLD_TANK.get()) return GOLD;
            if (block == SPBlocks.DIAMOND_TANK.get()) return DIAMOND;
            return null;
        }
    }

    public TankUpgradeItem(TankTier targetTier, Properties properties) {
        super(properties);
        this.targetTier = targetTier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (level.isClientSide() || player == null) {
            return InteractionResult.SUCCESS;
        }

        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        TankTier currentTier = TankTier.fromBlock(block);

        if (currentTier == null) {
            return InteractionResult.PASS;
        }

        if (currentTier.getLevel() >= targetTier.getLevel()) {
            player.displayClientMessage(
                    Component.translatable("message.smallprogressions.tank_upgrade.already_upgraded")
                            .withStyle(ChatFormatting.RED),
                    true
            );
            return InteractionResult.FAIL;
        }

        if (currentTier.getLevel() + 1 != targetTier.getLevel()) {
            player.displayClientMessage(
                    Component.translatable("message.smallprogressions.tank_upgrade.wrong_tier")
                            .withStyle(ChatFormatting.RED),
                    true
            );
            return InteractionResult.FAIL;
        }

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity == null) {
            return InteractionResult.FAIL;
        }

        FluidTank oldTank = getTankFromEntity(blockEntity);
        if (oldTank == null) {
            return InteractionResult.FAIL;
        }

        FluidStack storedFluid = oldTank.getFluid().copy();

        BlockState oldState = level.getBlockState(pos);

        level.removeBlockEntity(pos);

        BlockState newState = targetTier.getBlock().defaultBlockState();
        if (oldState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            newState = newState.setValue(
                    BlockStateProperties.HORIZONTAL_FACING,
                    oldState.getValue(BlockStateProperties.HORIZONTAL_FACING)
            );
        }

        level.setBlock(pos, newState, Block.UPDATE_NONE);

        BlockEntity newBlockEntity = level.getBlockEntity(pos);
        if (newBlockEntity == null) {
            Block newBlock = targetTier.getBlock();
            if (newBlock instanceof BaseEntityBlock baseEntityBlock) {
                newBlockEntity = baseEntityBlock.newBlockEntity(pos, newState);
                if (newBlockEntity != null) {
                    level.setBlockEntity(newBlockEntity);
                }
            }
        }

        if (newBlockEntity != null && !storedFluid.isEmpty()) {
            FluidTank newTank = getTankFromEntity(newBlockEntity);
            if (newTank != null) {
                newTank.fill(storedFluid, IFluidHandler.FluidAction.EXECUTE);
                newBlockEntity.setChanged();
            }
        }

        level.sendBlockUpdated(pos, oldState, newState, Block.UPDATE_ALL);
        level.updateNeighborsAt(pos, newState.getBlock());

        if (!player.isCreative()) {
            stack.shrink(1);
        }

        level.playSound(null, pos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
        player.displayClientMessage(
                Component.translatable("message.smallprogressions.tank_upgrade.success")
                        .withStyle(ChatFormatting.GREEN),
                true
        );

        return InteractionResult.SUCCESS;
    }

    private FluidTank getTankFromEntity(BlockEntity blockEntity) {
        if (blockEntity instanceof CopperTankBlockEntity copperTank) {
            return copperTank.tank;
        } else if (blockEntity instanceof IronTankBlockEntity ironTank) {
            return ironTank.tank;
        } else if (blockEntity instanceof GoldTankBlockEntity goldTank) {
            return goldTank.tank;
        } else if (blockEntity instanceof DiamondTankBlockEntity diamondTank) {
            return diamondTank.tank;
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        String key = "tooltip.smallprogressions." + this.getDescriptionId().split("\\.")[2];
        tooltipComponents.add(Component.translatable(key).withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}