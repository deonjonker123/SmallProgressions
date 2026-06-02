package com.misterd.smallprogressions.compat.curios;

import com.misterd.smallprogressions.item.custom.MagnetItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

public class CuriosCompat {

    public static boolean hasMagnetActive(Player player) {
        return CuriosApi.getCuriosInventory(player).map(curios -> {
            for (var handler : curios.getCurios().values()) {
                var stacks = handler.getStacks();
                for (int i = 0; i < stacks.getSlots(); i++) {
                    ItemStack stack = stacks.getStackInSlot(i);
                    if (stack.getItem() instanceof MagnetItem && MagnetItem.isActive(stack)) return true;
                }
            }
            return false;
        }).orElse(false);
    }

    public static void toggleMagnetInCurios(Player player) {
        CuriosApi.getCuriosInventory(player).ifPresent(curios -> {
            for (var handler : curios.getCurios().values()) {
                var stacks = handler.getStacks();
                for (int i = 0; i < stacks.getSlots(); i++) {
                    ItemStack stack = stacks.getStackInSlot(i);
                    if (stack.getItem() instanceof MagnetItem) {
                        MagnetItem.toggle(player, stack);
                        return;
                    }
                }
            }
        });
    }
}