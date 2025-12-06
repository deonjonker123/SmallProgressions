package com.misterd.smallprogressions.item.equipment;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class SPArmorMaterials {
    public static final Holder<ArmorMaterial> STEEL_ARMOR_MATERIAL;
    public static final Holder<ArmorMaterial> EMERALD_ARMOR_MATERIAL;
    public static final Holder<ArmorMaterial> WITHER_ARMOR_MATERIAL;
    public static final Holder<ArmorMaterial> DRAGON_ARMOR_MATERIAL;

    static {
        STEEL_ARMOR_MATERIAL = register(
                "steel",
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(ArmorItem.Type.BOOTS, 2);
                    map.put(ArmorItem.Type.LEGGINGS, 5);
                    map.put(ArmorItem.Type.CHESTPLATE, 6);
                    map.put(ArmorItem.Type.HELMET, 2);
                    map.put(ArmorItem.Type.BODY, 11);
                }),
                16,
                2.0F,
                0.1F,
                () -> SPItems.STEEL_INGOT.get()
        );
    }

    static {
        EMERALD_ARMOR_MATERIAL = register(
                "emerald",
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(ArmorItem.Type.BOOTS, 4);
                    map.put(ArmorItem.Type.LEGGINGS, 9);
                    map.put(ArmorItem.Type.CHESTPLATE, 9);
                    map.put(ArmorItem.Type.HELMET, 4);
                    map.put(ArmorItem.Type.BODY, 15);
                }),
                25,
                6,
                0.2F,
                () -> Items.EMERALD
        );
    }

    static {
        WITHER_ARMOR_MATERIAL = register(
                "wither",
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(ArmorItem.Type.BOOTS, 6);
                    map.put(ArmorItem.Type.LEGGINGS, 10);
                    map.put(ArmorItem.Type.CHESTPLATE, 11);
                    map.put(ArmorItem.Type.HELMET, 6);
                    map.put(ArmorItem.Type.BODY, 15);
                }),
                28,
                15,
                0.5F,
                () -> SPItems.WITHER_RIB.get()
        );
    }

    static {
        DRAGON_ARMOR_MATERIAL = register(
                "dragon",
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(ArmorItem.Type.BOOTS, 7);
                    map.put(ArmorItem.Type.LEGGINGS, 11);
                    map.put(ArmorItem.Type.CHESTPLATE, 12);
                    map.put(ArmorItem.Type.HELMET, 7);
                    map.put(ArmorItem.Type.BODY, 18);
                }),
                32,
                20,
                1,
                () -> SPItems.DRAGON_SCALE.get()
        );
    }

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection, int enchantability, float toughness, float knockbackResistance, Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
