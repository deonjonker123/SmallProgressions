package com.misterd.smallprogressions.item.equipment;

import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.util.SPTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class SPToolTiers {
    public static final Tier STEEL = new SimpleTier(
            SPTags.Blocks.INCORRECT_FOR_STEEL_TOOL,
            300,
            7.0F,
            3.0F,
            18,
            () -> Ingredient.of(SPItems.STEEL_INGOT)
    );

    public static final Tier EMERALD = new SimpleTier(
            SPTags.Blocks.INCORRECT_FOR_EMERALD_TOOL,
            2961,
            10.0F,
            6.0F,
            28,
            () -> Ingredient.of(Items.EMERALD)
    );

    public static final Tier WITHER = new SimpleTier(
            SPTags.Blocks.INCORRECT_FOR_WITHER_TOOL,
            2500,
            11.0F,
            9.0F,
            28,
            () -> Ingredient.of(SPItems.WITHER_RIB)
    );

    public static final Tier DRAGON = new SimpleTier(
            SPTags.Blocks.INCORRECT_FOR_DRAGON_TOOL,
            3000,
            12.0F,
            11.0F,
            28,
            () -> Ingredient.of(SPItems.DRAGON_SCALE)
    );
}
