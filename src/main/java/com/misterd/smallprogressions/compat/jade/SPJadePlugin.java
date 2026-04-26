package com.misterd.smallprogressions.compat.jade;

import com.misterd.smallprogressions.block.custom.*;
import com.misterd.smallprogressions.blockentity.custom.*;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class SPJadePlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(TimerProvider.INSTANCE, TimerBlockEntity.class);
        registration.registerBlockDataProvider(WirelessRedstoneReceiverProvider.INSTANCE, WirelessRedstoneReceiverBlockEntity.class);
        registration.registerBlockDataProvider(WirelessRedstoneTransmitterProvider.INSTANCE, WirelessRedstoneTransmitterBlockEntity.class);
        registration.registerBlockDataProvider(BatteryProvider.INSTANCE, BatteryBlockEntity.class);
        registration.registerBlockDataProvider(EnergyReceiverProvider.INSTANCE, EnergyReceiverBlockEntity.class);
        registration.registerBlockDataProvider(EnergyTransmitterProvider.INSTANCE, EnergyTransmitterBlockEntity.class);
        registration.registerBlockDataProvider(SolarPanelProvider.INSTANCE, SolarPanelBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(TimerClientProvider.INSTANCE, TimerBlock.class);
        registration.registerBlockComponent(WirelessRedstoneReceiverClientProvider.INSTANCE, WirelessRedstoneReceiverBlock.class);
        registration.registerBlockComponent(WirelessRedstoneTransmitterClientProvider.INSTANCE, WirelessRedstoneTransmitterBlock.class);
        registration.registerBlockComponent(BatteryClientProvider.INSTANCE, BatteryBlock.class);
        registration.registerBlockComponent(EnergyReceiverClientProvider.INSTANCE, EnergyReceiverBlock.class);
        registration.registerBlockComponent(EnergyTransmitterClientProvider.INSTANCE, EnergyTransmitterBlock.class);
        registration.registerBlockComponent(SolarPanelClientProvider.INSTANCE, SolarPanelBlock.class);
    }
}