package com.misterd.smallprogressions;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.blockentity.SPBlockEntities;
import com.misterd.smallprogressions.client.renderer.ber.LanternBracketBlockEntityRenderer;
import com.misterd.smallprogressions.client.renderer.ber.LogisticsSenderBlockEntityRenderer;
import com.misterd.smallprogressions.client.renderer.ber.TankBlockEntityRenderer;
import com.misterd.smallprogressions.component.SPDataComponents;
import com.misterd.smallprogressions.config.Config;
import com.misterd.smallprogressions.event.FenceJump;
import com.misterd.smallprogressions.event.MagnetEventHandler;
import com.misterd.smallprogressions.event.PlayerFenceJumpEvent;
import com.misterd.smallprogressions.gui.SPMenuTypes;
import com.misterd.smallprogressions.gui.custom.*;
import com.misterd.smallprogressions.item.SPCreativeTab;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.keybind.SPKeyBindings;
import com.misterd.smallprogressions.network.SPNetwork;
import com.misterd.smallprogressions.network.ToggleMagnetPacket;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.slf4j.Logger;

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
        SPBlockEntities.register(modEventBus);
        SPMenuTypes.register(modEventBus);
        SPNetwork.register(modEventBus);
        SPDataComponents.register(modEventBus);
        NeoForge.EVENT_BUS.register(new MagnetEventHandler());
        NeoForge.EVENT_BUS.addListener((PlayerFenceJumpEvent event) -> FenceJump.onJump(event.getPlayer()));
        Config.register(modContainer);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
            event.registerCategory(SPKeyBindings.CATEGORY);
            event.register(SPKeyBindings.TOGGLE_MAGNET);
        }

        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            while (SPKeyBindings.TOGGLE_MAGNET.consumeClick()) {
                ClientPacketDistributor.sendToServer(new ToggleMagnetPacket());
            }
        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {

        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(SPBlockEntities.COPPER_TANK_BE.get(), TankBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(SPBlockEntities.IRON_TANK_BE.get(), TankBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(SPBlockEntities.GOLD_TANK_BE.get(), TankBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(SPBlockEntities.DIAMOND_TANK_BE.get(), TankBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(SPBlockEntities.LOGISTICS_SENDER_BE.get(), LogisticsSenderBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(SPBlockEntities.LANTERN_BRACKET_BE.get(), LanternBracketBlockEntityRenderer::new);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(SPMenuTypes.BRICK_FURNACE_MENU.get(), BrickFurnaceScreen::new);
            event.register(SPMenuTypes.COPPER_BARREL_MENU.get(), CopperBarrelScreen::new);
            event.register(SPMenuTypes.IRON_BARREL_MENU.get(), IronBarrelScreen::new);
            event.register(SPMenuTypes.GOLD_BARREL_MENU.get(), GoldBarrelScreen::new);
            event.register(SPMenuTypes.DIAMOND_BARREL_MENU.get(), DiamondBarrelScreen::new);
            event.register(SPMenuTypes.ADVANCED_ITEM_COLLECTOR_MENU.get(), AdvancedItemCollectorScreen::new);
            event.register(SPMenuTypes.LINEN_SACK_MENU.get(), LinenSackScreen::new);
            event.register(SPMenuTypes.HARVESTER_MENU.get(), HarvesterScreen::new);
            event.register(SPMenuTypes.WIRELESS_REDSTONE_TRANSMITTER_MENU.get(), WirelessRedstoneTransmitterScreen::new);
            event.register(SPMenuTypes.WIRELESS_REDSTONE_RECEIVER_MENU.get(), WirelessRedstoneReceiverScreen::new);
            event.register(SPMenuTypes.TIMER_MENU.get(), TimerScreen::new);
            event.register(SPMenuTypes.BATTERY_MENU.get(), BatteryBlockScreen::new);
            event.register(SPMenuTypes.ENERGY_TRANSMITTER_MENU.get(), EnergyTransmitterScreen::new);
            event.register(SPMenuTypes.LOGISTICS_SENDER_MENU.get(), LogisticsSenderScreen::new);
        }
    }
}