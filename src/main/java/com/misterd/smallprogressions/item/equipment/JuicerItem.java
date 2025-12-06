package com.misterd.smallprogressions.item.equipment;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class JuicerItem extends Item {
    public JuicerItem(Properties properties) {
        super(properties.durability(544));
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack damaged = itemStack.copy();
        damaged.setDamageValue(damaged.getDamageValue() + 1);

        if (damaged.getDamageValue() >= damaged.getMaxDamage()) {
            return ItemStack.EMPTY;
        }

        return damaged;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }
}