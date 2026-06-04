package com.misterd.smallprogressions.event;

import com.misterd.smallprogressions.compat.curios.CuriosCompat;
import com.misterd.smallprogressions.config.Config;
import com.misterd.smallprogressions.item.custom.MagnetItem;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

public class MagnetEventHandler {

    private static final double PULL_SPEED = 0.8;

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;
        if (!hasMagnetActive(player)) return;

        AABB area = player.getBoundingBox().inflate(Config.getMagnetRange());
        List<ItemEntity> itemEntities = player.level().getEntitiesOfClass(ItemEntity.class, area);

        for (ItemEntity itemEntity : itemEntities) {
            if (!itemEntity.isAlive() || itemEntity.getItem().isEmpty()) continue;
            if (itemEntity.getAge() < 20) continue;

            Vec3 toPlayer = player.position().add(0, 0.5, 0).subtract(itemEntity.position());
            double dist = toPlayer.length();

            if (dist < 1.0) {
                ItemStack remaining = addToPlayerInventory(player, itemEntity.getItem().copy());
                if (remaining.isEmpty()) {
                    itemEntity.discard();
                } else {
                    itemEntity.setItem(remaining);
                }
            } else {
                Vec3 motion = toPlayer.normalize().scale(PULL_SPEED);
                itemEntity.setDeltaMovement(motion);
                itemEntity.hurtMarked = true;
            }
        }

        List<ExperienceOrb> orbs = player.level().getEntitiesOfClass(ExperienceOrb.class, area);
        for (ExperienceOrb orb : orbs) {
            if (!orb.isAlive()) continue;

            Vec3 toPlayer = player.position().add(0, 0.5, 0).subtract(orb.position());
            double dist = toPlayer.length();

            if (dist >= 1.0) {
                Vec3 motion = toPlayer.normalize().scale(PULL_SPEED);
                orb.setDeltaMovement(motion);
                orb.hurtMarked = true;
            }
        }
    }

    private boolean hasMagnetActive(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof MagnetItem && MagnetItem.isActive(stack)) return true;
        }
        return checkCuriosSlot(player);
    }

    private boolean checkCuriosSlot(Player player) {
        if (ModList.get().isLoaded("curios")) {
            return CuriosCompat.hasMagnetActive(player);
        }
        return false;
    }

    private ItemStack addToPlayerInventory(Player player, ItemStack stack) {
        if (player.getInventory().add(stack)) return ItemStack.EMPTY;
        return stack;
    }
}