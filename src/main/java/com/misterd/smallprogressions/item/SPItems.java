package com.misterd.smallprogressions.item;


import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.custom.McFloatyBlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SPItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("smallprogressions");

    public static final DeferredItem<Item> TINY_COAL = ITEMS.registerItem("tiny_coal",
            props -> new Item(props));

    public static final DeferredItem<Item> TINY_CHARCOAL = ITEMS.registerItem("tiny_charcoal",
            props -> new Item(props));

    public static final DeferredItem<Item> MCFLOATY_BLOCK_ITEM = ITEMS.registerItem("mcfloaty_block",
            props -> new McFloatyBlockItem(SPBlocks.MCFLOATY_BLOCK.get(), props));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
