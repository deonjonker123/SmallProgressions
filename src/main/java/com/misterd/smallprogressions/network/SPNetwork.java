package com.misterd.smallprogressions.network;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class SPNetwork {
    public static void register(IEventBus eventBus) {
        eventBus.addListener(SPNetwork::registerPayloads);
    }

    private static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("smallprogressions");

        registrar.playToServer(
                ConfigPacket.TYPE,
                ConfigPacket.STREAM_CODEC,
                ConfigPacket::handle
        );
    }
}