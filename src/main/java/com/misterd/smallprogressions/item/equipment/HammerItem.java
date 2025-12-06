package com.misterd.smallprogressions.item.equipment;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HammerItem extends Item {
    public HammerItem(Properties properties) {
        super(properties.durability(1024));
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