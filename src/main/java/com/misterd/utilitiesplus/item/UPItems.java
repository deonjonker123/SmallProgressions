package com.misterd.utilitiesplus.item;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class UPItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("utilitiesplus");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
