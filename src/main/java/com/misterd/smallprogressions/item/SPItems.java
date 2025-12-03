package com.misterd.smallprogressions.item;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SPItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("smallprogressions");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
