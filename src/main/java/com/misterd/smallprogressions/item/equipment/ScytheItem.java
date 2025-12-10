package com.misterd.smallprogressions.item.equipment;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.List;

public class ScytheItem extends Item {
    private final int radius;
    private final Tier tier;

    public ScytheItem(Tier tier, int radius, Properties properties) {
        super(properties.durability(tier.getUses()));
        this.tier = tier;
        this.radius = radius;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.smallprogressions.scythe.area", radius + "x" + radius).withStyle(ChatFormatting.AQUA));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (!level.isClientSide && player != null) {
            int harvested = 0;
            int range = (radius - 1) / 2;

            for (int x = -range; x <= range; x++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos pos = clickedPos.offset(x, 0, z);
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();

                    if (block instanceof CropBlock cropBlock) {
                        try {

                            IntegerProperty ageProperty = null;
                            for (var property : state.getProperties()) {
                                if (property instanceof IntegerProperty intProp && property.getName().equals("age")) {
                                    ageProperty = intProp;
                                    break;
                                }
                            }

                            if (ageProperty != null) {
                                int currentAge = state.getValue(ageProperty);
                                int maxAge = cropBlock.getMaxAge();

                                if (currentAge >= maxAge) {
                                    Block.dropResources(state, level, pos, null, player, stack);

                                    level.setBlock(pos, state.setValue(ageProperty, 0), 2);

                                    harvested++;
                                }
                            }
                        } catch (Exception e) {

                        }
                    }
                }
            }

            if (harvested > 0) {
                stack.hurtAndBreak(harvested, player, context.getHand() == InteractionHand.MAIN_HAND ?
                        EquipmentSlot.MAINHAND :
                        EquipmentSlot.OFFHAND);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }
}
