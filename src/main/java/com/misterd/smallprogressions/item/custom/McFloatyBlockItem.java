package com.misterd.smallprogressions.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class McFloatyBlockItem extends BlockItem {

    public McFloatyBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide) {
            double x = player.getX() + player.getLookAngle().x * 4.5;
            double y = player.getEyeY() + player.getLookAngle().y * 4.5;
            double z = player.getZ() + player.getLookAngle().z * 4.5;

            BlockPos pos = new BlockPos((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));

            if (level.isInWorldBounds(pos) && level.getBlockState(pos).canBeReplaced()) {
                level.setBlock(pos, this.getBlock().defaultBlockState(), 3);

                if (!player.isCreative()) {
                    if (usedHand == InteractionHand.MAIN_HAND) {
                        player.getInventory().removeFromSelected(false);
                    } else {
                        player.getInventory().removeItem(Inventory.SLOT_OFFHAND, 1);
                    }
                }
            }
        }
        return super.use(level, player, usedHand);
    }
}