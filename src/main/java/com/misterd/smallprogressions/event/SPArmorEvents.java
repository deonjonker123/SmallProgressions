package com.misterd.smallprogressions.event;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.item.equipment.SPArmorItem;
import com.misterd.smallprogressions.item.equipment.SPArmorMaterials;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

@EventBusSubscriber(modid = SmallProgressions.MODID, bus = EventBusSubscriber.Bus.GAME)
public class SPArmorEvents {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide) return;

        applyArmorEffects(player);
        handleCreativeFlight(player);
    }

    private static void applyArmorEffects(Player player) {
        if (isWearingFullSet(player, SPArmorMaterials.EMERALD_ARMOR_MATERIAL)) {
            applyEffectsForMaterial(player, SPArmorMaterials.EMERALD_ARMOR_MATERIAL);
        }

        if (isWearingFullSet(player, SPArmorMaterials.WITHER_ARMOR_MATERIAL)) {
            applyEffectsForMaterial(player, SPArmorMaterials.WITHER_ARMOR_MATERIAL);
        }

        if (isWearingFullSet(player, SPArmorMaterials.DRAGON_ARMOR_MATERIAL)) {
            applyEffectsForMaterial(player, SPArmorMaterials.DRAGON_ARMOR_MATERIAL);
        }
    }

    private static void handleCreativeFlight(Player player) {
        if (isWearingFullSet(player, SPArmorMaterials.DRAGON_ARMOR_MATERIAL)) {
            if (!player.getAbilities().mayfly) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }
        } else {
            if (!player.isCreative() && !player.isSpectator()) {
                if (player.getAbilities().mayfly) {
                    player.getAbilities().mayfly = false;
                    player.getAbilities().flying = false;
                    player.onUpdateAbilities();
                }
            }
        }
    }

    private static boolean isWearingFullSet(Player player, Holder<ArmorMaterial> material) {
        return hasMaterial(player, EquipmentSlot.HEAD, material)
                && hasMaterial(player, EquipmentSlot.CHEST, material)
                && hasMaterial(player, EquipmentSlot.LEGS, material)
                && hasMaterial(player, EquipmentSlot.FEET, material);
    }

    private static boolean hasMaterial(Player player, EquipmentSlot slot, Holder<ArmorMaterial> material) {
        var item = player.getItemBySlot(slot).getItem();
        return item instanceof ArmorItem armor && armor.getMaterial().equals(material);
    }

    private static void applyEffectsForMaterial(Player player, Holder<ArmorMaterial> material) {
        List<MobEffectInstance> effects = SPArmorItem.getEffectsForMaterial(material);

        if (effects != null) {
            for (MobEffectInstance effect : effects) {
                if (!player.hasEffect(effect.getEffect())) {
                    player.addEffect(new MobEffectInstance(effect));
                }
            }
        }
    }
}