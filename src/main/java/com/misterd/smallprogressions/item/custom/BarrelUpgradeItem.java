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
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.List;

public class BarrelUpgradeItem extends Item {
    private final BarrelTier targetTier;

    public enum BarrelTier {
        COPPER(0),
        IRON(1),
        GOLD(2),
        DIAMOND(3);

        private final int level;

        BarrelTier(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        public Block getBlock() {
            return switch (this) {
                case COPPER -> SPBlocks.COPPER_BARREL.get();
                case IRON -> SPBlocks.IRON_BARREL.get();
                case GOLD -> SPBlocks.GOLD_BARREL.get();
                case DIAMOND -> SPBlocks.DIAMOND_BARREL.get();
            };
        }

        public static BarrelTier fromBlock(Block block) {
            if (block == SPBlocks.COPPER_BARREL.get()) return COPPER;
            if (block == SPBlocks.IRON_BARREL.get()) return IRON;
            if (block == SPBlocks.GOLD_BARREL.get()) return GOLD;
            if (block == SPBlocks.DIAMOND_BARREL.get()) return DIAMOND;
            return null;
        }
    }

    public BarrelUpgradeItem(BarrelTier targetTier, Properties properties) {
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
        BarrelTier currentTier = BarrelTier.fromBlock(block);

        if (currentTier == null) {
            return InteractionResult.PASS;
        }

        if (currentTier.getLevel() >= targetTier.getLevel()) {
            player.displayClientMessage(
                    Component.translatable("message.smallprogressions.barrel_upgrade.already_upgraded")
                            .withStyle(ChatFormatting.RED),
                    true
            );
            return InteractionResult.FAIL;
        }

        if (currentTier.getLevel() + 1 != targetTier.getLevel()) {
            player.displayClientMessage(
                    Component.translatable("message.smallprogressions.barrel_upgrade.wrong_tier")
                            .withStyle(ChatFormatting.RED),
                    true
            );
            return InteractionResult.FAIL;
        }

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity == null) {
            return InteractionResult.FAIL;
        }

        ItemStackHandler oldInventory = getInventoryFromBarrel(blockEntity);
        if (oldInventory == null) {
            return InteractionResult.FAIL;
        }

        ItemStackHandler tempInventory = new ItemStackHandler(oldInventory.getSlots());
        for (int i = 0; i < oldInventory.getSlots(); i++) {
            tempInventory.setStackInSlot(i, oldInventory.getStackInSlot(i).copy());
        }

        BlockState oldState = level.getBlockState(pos);

        level.removeBlockEntity(pos);

        BlockState newState = targetTier.getBlock().defaultBlockState();
        if (oldState.hasProperty(BlockStateProperties.FACING)) {
            newState = newState.setValue(
                    BlockStateProperties.FACING,
                    oldState.getValue(BlockStateProperties.FACING)
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

        if (newBlockEntity != null) {
            ItemStackHandler newInventory = getInventoryFromBarrel(newBlockEntity);
            if (newInventory != null) {
                for (int i = 0; i < tempInventory.getSlots() && i < newInventory.getSlots(); i++) {
                    newInventory.setStackInSlot(i, tempInventory.getStackInSlot(i).copy());
                }
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
                Component.translatable("message.smallprogressions.barrel_upgrade.success")
                        .withStyle(ChatFormatting.GREEN),
                true
        );

        return InteractionResult.SUCCESS;
    }

    private ItemStackHandler getInventoryFromBarrel(BlockEntity blockEntity) {
        if (blockEntity instanceof CopperBarrelBlockEntity copperBarrel) {
            return copperBarrel.inventory;
        } else if (blockEntity instanceof IronBarrelBlockEntity ironBarrel) {
            return ironBarrel.inventory;
        } else if (blockEntity instanceof GoldBarrelBlockEntity goldBarrel) {
            return goldBarrel.inventory;
        } else if (blockEntity instanceof DiamondBarrelBlockEntity diamondBarrel) {
            return diamondBarrel.inventory;
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        String key = "tooltip.smallprogressions." + this.getDescriptionId().split("\\.")[2];
        tooltipComponents.add(Component.translatable(key).withStyle(ChatFormatting.AQUA));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}