package com.misterd.smallprogressions.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class IronAppleItem extends Item {
    public IronAppleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
