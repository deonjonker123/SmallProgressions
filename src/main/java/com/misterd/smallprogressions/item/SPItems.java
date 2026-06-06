package com.misterd.smallprogressions.item;


import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.custom.ConnectionWrench;
import com.misterd.smallprogressions.item.custom.MagnetItem;
import com.misterd.smallprogressions.item.custom.McFloatyBlockItem;
import com.misterd.smallprogressions.item.custom.TrowelItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Consumer;

public class SPItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("smallprogressions");

    public static final DeferredItem<Item> BASIC_SOLAR_CELL = ITEMS.registerItem("basic_solar_cell",
            props -> new Item(props));

    public static final DeferredItem<Item> HARDENED_SOLAR_CELL = ITEMS.registerItem("hardened_solar_cell",
            props -> new Item(props));

    public static final DeferredItem<Item> ADVANCED_SOLAR_CELL = ITEMS.registerItem("advanced_solar_cell",
            props -> new Item(props));

    public static final DeferredItem<Item> ELITE_SOLAR_CELL = ITEMS.registerItem("elite_solar_cell",
            props -> new Item(props));

    public static final DeferredItem<Item> ULTIMATE_SOLAR_CELL = ITEMS.registerItem("ultimate_solar_cell",
            props -> new Item(props));

    public static final DeferredItem<Item> SILICA_BLEND = ITEMS.registerItem("silica_blend",
            props -> new Item(props));

    public static final DeferredItem<Item> TINY_COAL = ITEMS.registerItem("tiny_coal",
            props -> new Item(props));

    public static final DeferredItem<Item> TINY_CHARCOAL = ITEMS.registerItem("tiny_charcoal",
            props -> new Item(props));

    public static final DeferredItem<Item> MAGNET = ITEMS.registerItem("magnet",
            props -> new MagnetItem(props));

    public static final DeferredItem<Item> TROWEL = ITEMS.registerItem("trowel",
            props -> new TrowelItem(props));

    public static final DeferredItem<Item> SPEED_UPGRADE = ITEMS.registerItem("speed_upgrade",
            props -> new Item(props)
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
                    adder.accept(Component.translatable("item.smallprogressions.speed_upgrade.subtitle").withStyle(ChatFormatting.LIGHT_PURPLE));
                }
            });

    public static final DeferredItem<Item> STACK_UPGRADE = ITEMS.registerItem("stack_upgrade",
            props -> new Item(props)
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
                    adder.accept(Component.translatable("item.smallprogressions.stack_upgrade.subtitle").withStyle(ChatFormatting.LIGHT_PURPLE));
                }
            });

    public static final DeferredItem<Item> NODE_UPGRADE = ITEMS.registerItem("node_upgrade",
            props -> new Item(props)
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
                    adder.accept(Component.translatable("item.smallprogressions.node_upgrade.subtitle").withStyle(ChatFormatting.LIGHT_PURPLE));
                }
            });

    public static final DeferredItem<Item> RANGE_UPGRADE = ITEMS.registerItem("range_upgrade",
            props -> new Item(props)
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
                    adder.accept(Component.translatable("item.smallprogressions.range_upgrade.subtitle").withStyle(ChatFormatting.LIGHT_PURPLE));
                }
            });

    public static final DeferredItem<Item> CONNECTION_WRENCH = ITEMS.registerItem("connection_wrench",
            props -> new ConnectionWrench(props)
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
                    adder.accept(Component.translatable("item.smallprogressions.connection_wrench.hint").withStyle(ChatFormatting.LIGHT_PURPLE));
                }
            });

    public static final DeferredItem<Item> MCFLOATY_BLOCK_ITEM = ITEMS.registerItem("mcfloaty_block",
            props -> new McFloatyBlockItem(SPBlocks.MCFLOATY_BLOCK.get(), props));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
