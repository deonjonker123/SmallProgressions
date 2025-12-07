package com.misterd.smallprogressions;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.config.Config;
import com.misterd.smallprogressions.gui.SPMenuTypes;
import com.misterd.smallprogressions.gui.custom.*;
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
        SPBlockEntities.register(modEventBus);
        SPMenuTypes.register(modEventBus);
        Config.register(modContainer);
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

                ItemBlockRenderTypes.setRenderLayer(SPBlocks.GREENHOUSE_GLASS.get(), RenderType.translucent());

                ItemBlockRenderTypes.setRenderLayer(SPBlocks.BLACK_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.BLUE_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.BROWN_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.CYAN_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.GRAY_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.GREEN_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.LIGHT_BLUE_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.LIGHT_GRAY_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.LIME_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.MAGENTA_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.ORANGE_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.PINK_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.PURPLE_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.RED_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.WHITE_GLOWSTONE_GLASS.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(SPBlocks.YELLOW_GLOWSTONE_GLASS.get(), RenderType.translucent());
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
            event.register(SPMenuTypes.BRICK_FURNACE_MENU.get(), BrickFurnaceScreen::new);
            event.register(SPMenuTypes.COPPER_BARREL_MENU.get(), CopperBarrelScreen::new);
            event.register(SPMenuTypes.IRON_BARREL_MENU.get(), IronBarrelScreen::new);
            event.register(SPMenuTypes.GOLD_BARREL_MENU.get(), GoldBarrelScreen::new);
            event.register(SPMenuTypes.DIAMOND_BARREL_MENU.get(), DiamondBarrelScreen::new);
        }
    }
}
