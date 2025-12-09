package com.misterd.smallprogressions.compat.jei;

import net.minecraft.world.item.ItemStack;

public record CobbleGenRecipe(
        ItemStack generator,
        ItemStack output,
        String ticks,
        String displayTime
) {}