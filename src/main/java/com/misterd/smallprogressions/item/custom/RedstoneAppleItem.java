package com.misterd.smallprogressions.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RedstoneAppleItem extends Item {
    public RedstoneAppleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
