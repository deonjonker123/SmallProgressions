package com.misterd.smallprogressions.event;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.config.Config;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(modid = SmallProgressions.MODID, bus = EventBusSubscriber.Bus.GAME)
public class SPRepairTotemEvent {
    private static final Map<UUID, Integer> playerTickCounters = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide) {
            return;
        }

        UUID playerId = player.getUUID();
        int tickCounter = playerTickCounters.getOrDefault(playerId, 0);
        tickCounter++;

        int tickInterval = Config.getRepairTotemTickInterval();

        if (tickCounter >= tickInterval) {
            tickCounter = 0;

            int totemCount = countRepairTotems(player);

            if (totemCount > 0) {
                int repairAmount = totemCount * Config.getRepairTotemDurabilityPerTotem();
                repairItems(player, repairAmount);
            }
        }

        playerTickCounters.put(playerId, tickCounter);
    }

    private static int countRepairTotems(Player player) {
        int count = 0;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(SPItems.REPAIR_TOTEM.get())) {
                count += stack.getCount();
            }
        }

        ItemStack offhandStack = player.getOffhandItem();
        if (offhandStack.is(SPItems.REPAIR_TOTEM.get())) {
            count += offhandStack.getCount();
        }

        return count;
    }

    private static void repairItems(Player player, int repairAmount) {
        for (ItemStack stack : player.getInventory().items) {
            if (canRepair(stack)) {
                repairItem(stack, repairAmount);
            }
        }

        for (ItemStack stack : player.getInventory().armor) {
            if (canRepair(stack)) {
                repairItem(stack, repairAmount);
            }
        }

        ItemStack offhandStack = player.getOffhandItem();
        if (canRepair(offhandStack)) {
            repairItem(offhandStack, repairAmount);
        }
    }

    private static boolean canRepair(ItemStack stack) {
        return !stack.isEmpty()
                && stack.isDamageableItem()
                && stack.getDamageValue() > 0;
    }

    private static void repairItem(ItemStack stack, int repairAmount) {
        int currentDamage = stack.getDamageValue();
        int newDamage = Math.max(0, currentDamage - repairAmount);
        stack.setDamageValue(newDamage);
    }
}