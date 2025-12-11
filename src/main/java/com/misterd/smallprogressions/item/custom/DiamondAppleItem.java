package com.misterd.smallprogressions.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DiamondAppleItem extends Item {
    public DiamondAppleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
