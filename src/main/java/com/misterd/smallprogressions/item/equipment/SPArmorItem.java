package com.misterd.smallprogressions.item.equipment;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import java.util.List;
import java.util.Map;

public class SPArmorItem extends ArmorItem {
    private static final Map<Holder<ArmorMaterial>, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<Holder<ArmorMaterial>, List<MobEffectInstance>>())
                    .put(SPArmorMaterials.EMERALD_ARMOR_MATERIAL,
                            List.of(new MobEffectInstance(MobEffects.JUMP, 200, 1, false, false)))

                    .put(SPArmorMaterials.WITHER_ARMOR_MATERIAL,
                            List.of(
                                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1, false, false),
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 1, false, false),
                                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, false, false)
                            ))

                    .put(SPArmorMaterials.DRAGON_ARMOR_MATERIAL,
                            List.of(
                                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1, false, false),
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 1, false, false),
                                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, false, false)
                            ))
                    .build();

    public SPArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    public List<MobEffectInstance> getEffects() {
        return MATERIAL_TO_EFFECT_MAP.get(this.getMaterial());
    }
}
