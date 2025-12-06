package com.misterd.smallprogressions;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.SPCreativeTab;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.loot.SPLootModifiers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(SmallProgressions.MODID)
public class SmallProgressions {
    public static final String MODID = "smallprogressions";

    public static final Logger LOGGER = LogUtils.getLogger();

    public SmallProgressions(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        SPItems.register(modEventBus);
        SPBlocks.register(modEventBus);
        SPCreativeTab.register(modEventBus);
        SPLootModifiers.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.SIMPLE_ITEM_COLLECTOR.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.ADVANCED_ITEM_COLLECTOR.get(), RenderType.translucent());

                ItemBlockRenderTypes.setRenderLayer(SPBlocks.COPPER_TANK.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.IRON_TANK.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.GOLD_TANK.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.DIAMOND_TANK.get(), RenderType.translucent());

                ItemBlockRenderTypes.setRenderLayer(SPBlocks.REINFORCED_GLASS.get(), RenderType.translucent());
            });
        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {

        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {

        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {

        }
    }
}
