package com.misterd.smallprogressions.compat.jade;

import com.misterd.smallprogressions.block.custom.TimerBlock;
import com.misterd.smallprogressions.block.custom.WirelessRedstoneReceiverBlock;
import com.misterd.smallprogressions.block.custom.WirelessRedstoneTransmitterBlock;
import com.misterd.smallprogressions.blockentity.custom.TimerBlockEntity;
import com.misterd.smallprogressions.blockentity.custom.WirelessRedstoneReceiverBlockEntity;
import com.misterd.smallprogressions.blockentity.custom.WirelessRedstoneTransmitterBlockEntity;
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
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(TimerClientProvider.INSTANCE, TimerBlock.class);
        registration.registerBlockComponent(WirelessRedstoneReceiverClientProvider.INSTANCE, WirelessRedstoneReceiverBlock.class);
        registration.registerBlockComponent(WirelessRedstoneTransmitterClientProvider.INSTANCE, WirelessRedstoneTransmitterBlock.class);
    }
}