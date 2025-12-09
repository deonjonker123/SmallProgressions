package com.misterd.smallprogressions.item.custom;

import com.misterd.smallprogressions.gui.custom.BigPouchMenu;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;

public class BigPouchItem extends Item {
    private static final int INVENTORY_SIZE = 54;

    public BigPouchItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            MenuProvider menuProvider = new SimpleMenuProvider(
                    (id, playerInv, p) -> new BigPouchMenu(id, playerInv, stack, level),
                    stack.getHoverName()
            );
            serverPlayer.openMenu(menuProvider);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public static ItemStackHandler getInventory(ItemStack stack, Level level) {
        ItemStackHandler handler = new ItemStackHandler(INVENTORY_SIZE);

        if (stack.has(DataComponents.CUSTOM_DATA)) {
            CompoundTag tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
            if (tag.contains("Inventory")) {
                handler.deserializeNBT(level.registryAccess(), tag.getCompound("Inventory"));
            }
        }

        return handler;
    }

    public static void saveInventory(ItemStack stack, ItemStackHandler handler, Level level) {
        CompoundTag nbt = new CompoundTag();
        nbt.put("Inventory", handler.serializeNBT(level.registryAccess()));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
    }
}