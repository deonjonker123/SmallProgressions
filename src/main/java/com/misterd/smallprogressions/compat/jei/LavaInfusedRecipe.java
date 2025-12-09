package com.misterd.smallprogressions.compat.jei;

import net.minecraft.world.item.ItemStack;

public record LavaInfusedRecipe(
        ItemStack catalyst,
        ItemStack input,
        ItemStack output
) {}