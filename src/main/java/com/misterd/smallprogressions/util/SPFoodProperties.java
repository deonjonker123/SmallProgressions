package com.misterd.smallprogressions.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class SPFoodProperties {
    public static final FoodProperties BERRIES = new FoodProperties.Builder()
            .saturationModifier(0.6f)
            .nutrition(2)
            .fast()
            .build();

    public static final FoodProperties APPLE_JUICE = new FoodProperties.Builder()
            .saturationModifier(0.6F)
            .nutrition(2)
            .fast()
            .build();

    public static final FoodProperties TOASTED_BREAD = new FoodProperties.Builder()
            .saturationModifier(0.8F)
            .nutrition(4)
            .build();

    public static final FoodProperties PIZZA_SLICE = new FoodProperties.Builder()
            .saturationModifier(0.8F)
            .nutrition(14)
            .build();

    public static final FoodProperties FRIED_EGG = new FoodProperties.Builder()
            .saturationModifier(0.6F)
            .nutrition(2)
            .fast()
            .build();

    public static final FoodProperties RAW_BACON = new FoodProperties.Builder()
            .saturationModifier(0.6F)
            .nutrition(2)
            .fast()
            .build();

    public static final FoodProperties COOKED_BACON = new FoodProperties.Builder()
            .saturationModifier(0.8F)
            .nutrition(4)
            .fast()
            .build();

    public static final FoodProperties BACON_EGG_SANDWICH = new FoodProperties.Builder()
            .saturationModifier(1.0F)
            .nutrition(6)
            .build();

    public static final FoodProperties COOKED_APPLE = new FoodProperties.Builder()
            .saturationModifier(0.8F)
            .nutrition(6)
            .build();

    public static final FoodProperties IRON_APPLE = new FoodProperties.Builder()
            .saturationModifier(0.7F)
            .nutrition(3)
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600), 1)
            .build();

    public static final FoodProperties REDSTONE_APPLE = new FoodProperties.Builder()
            .saturationModifier(0.7F)
            .nutrition(3)
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600), 1)
            .build();

    public static final FoodProperties EMERALD_APPLE = new FoodProperties.Builder()
            .saturationModifier(0.7F)
            .nutrition(3)
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600), 1)
            .build();

    public static final FoodProperties DIAMOND_APPLE = new FoodProperties.Builder()
            .saturationModifier(0.7F)
            .nutrition(3)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1)
            .build();
}
