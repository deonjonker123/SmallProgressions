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

        registrar.playToServer(
                SetChannelPacket.TYPE,
                SetChannelPacket.STREAM_CODEC,
                SetChannelPacket::handle);

        registrar.playToServer(
                SetIntervalPacket.TYPE,
                SetIntervalPacket.STREAM_CODEC,
                SetIntervalPacket::handle);

        registrar.playToServer(
                SetRunningPacket.TYPE,
                SetRunningPacket.STREAM_CODEC,
                SetRunningPacket::handle);

        registrar.playToServer(
                TransmitterTogglePacket.TYPE,
                TransmitterTogglePacket.STREAM_CODEC,
                TransmitterTogglePacket::handle
        );
    }
}