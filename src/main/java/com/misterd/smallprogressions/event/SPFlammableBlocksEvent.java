package com.misterd.smallprogressions.event;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = SmallProgressions.MODID, bus = EventBusSubscriber.Bus.MOD)
public class SPFlammableBlocksEvent {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            FireBlock fireBlock = (FireBlock) Blocks.FIRE;

            fireBlock.setFlammable(SPBlocks.THATCH_BLOCK.get(), 60, 100);  // EXTREMELY flammable
            fireBlock.setFlammable(SPBlocks.THATCH_STAIRS.get(), 60, 100); // EXTREMELY flammable
            fireBlock.setFlammable(SPBlocks.THATCH_SLAB.get(), 60, 100);   // EXTREMELY flammable
        });
    }
}