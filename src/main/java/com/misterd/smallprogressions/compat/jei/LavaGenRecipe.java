package com.misterd.smallprogressions.compat.jei;

import net.minecraft.world.item.ItemStack;

public record LavaGenRecipe(
        ItemStack generator,
        ItemStack output,
        String mbPerTick,
        String displayRate
) {}