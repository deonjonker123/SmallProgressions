package com.misterd.smallprogressions.item;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SPCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SmallProgressions.MODID);

    public static final Supplier<CreativeModeTab> SMALLPROGRESSIONS = CREATIVE_MODE_TAB.register("smallprogressions_creativetab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get()))
                    .title(Component.translatable("creativetab.smallprogressions"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_1);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_2);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_3);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_4);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_5);

                        output.accept(SPBlocks.GROWTH_CRYSTAL_TIER_1);
                        output.accept(SPBlocks.GROWTH_CRYSTAL_TIER_2);
                        output.accept(SPBlocks.GROWTH_CRYSTAL_TIER_3);

                        output.accept(SPBlocks.GREENHOUSE_GLASS);

                        output.accept(SPBlocks.SIMPLE_ITEM_COLLECTOR);
                        output.accept(SPBlocks.ADVANCED_ITEM_COLLECTOR);

                        output.accept(SPBlocks.HARVESTER);

                        output.accept(SPBlocks.COPPER_BARREL);
                        output.accept(SPBlocks.IRON_BARREL);
                        output.accept(SPBlocks.GOLD_BARREL);
                        output.accept(SPBlocks.DIAMOND_BARREL);

                        output.accept(SPBlocks.COPPER_TANK);
                        output.accept(SPBlocks.IRON_TANK);
                        output.accept(SPBlocks.GOLD_TANK);
                        output.accept(SPBlocks.DIAMOND_TANK);

                        output.accept(SPBlocks.LINEN_SACK);

                        output.accept(SPBlocks.BRICK_FURNACE);

                        output.accept(SPBlocks.WATER_RESERVOIR);
                        output.accept(SPBlocks.LAVA_GENERATOR);

                        output.accept(SPBlocks.LAVA_INFUSED_STONE);
                        output.accept(SPBlocks.MCFLOATY_BLOCK);

                        output.accept(SPItems.MAGNET);
                        output.accept(SPItems.TROWEL);

                        output.accept(SPItems.SILICA_BLEND);
                        output.accept(SPItems.BASIC_SOLAR_CELL);
                        output.accept(SPItems.HARDENED_SOLAR_CELL);
                        output.accept(SPItems.ADVANCED_SOLAR_CELL);
                        output.accept(SPItems.ELITE_SOLAR_CELL);
                        output.accept(SPItems.ULTIMATE_SOLAR_CELL);

                        output.accept(SPBlocks.BASIC_SOLAR_PANEL);
                        output.accept(SPBlocks.HARDENED_SOLAR_PANEL);
                        output.accept(SPBlocks.ADVANCED_SOLAR_PANEL);
                        output.accept(SPBlocks.ELITE_SOLAR_PANEL);
                        output.accept(SPBlocks.ULTIMATE_SOLAR_PANEL);

                        output.accept(SPBlocks.BASIC_BATTERY);
                        output.accept(SPBlocks.HARDENED_BATTERY);
                        output.accept(SPBlocks.ADVANCED_BATTERY);
                        output.accept(SPBlocks.ELITE_BATTERY);
                        output.accept(SPBlocks.ULTIMATE_BATTERY);

                        output.accept(SPBlocks.ENERGY_TRANSMITTER);

                        output.accept(SPBlocks.BASIC_ENERGY_RECEIVER);
                        output.accept(SPBlocks.HARDENED_ENERGY_RECEIVER);
                        output.accept(SPBlocks.ADVANCED_ENERGY_RECEIVER);
                        output.accept(SPBlocks.ELITE_ENERGY_RECEIVER);
                        output.accept(SPBlocks.ULTIMATE_ENERGY_RECEIVER);

                        output.accept(SPBlocks.WIRELESS_REDSTONE_TRANSMITTER);
                        output.accept(SPBlocks.WIRELESS_REDSTONE_RECEIVER);
                        output.accept(SPBlocks.TIMER);

                        output.accept(SPBlocks.LOGISTICS_SENDER);
                        output.accept(SPBlocks.LOGISTICS_RECEIVER);

                        output.accept(SPItems.CONNECTION_WRENCH);
                        output.accept(SPItems.SPEED_UPGRADE);
                        output.accept(SPItems.STACK_UPGRADE);
                        output.accept(SPItems.NODE_UPGRADE);
                        output.accept(SPItems.RANGE_UPGRADE);

                        output.accept(SPBlocks.CHARCOAL_BLOCK);
                        output.accept(SPItems.TINY_COAL);
                        output.accept(SPItems.TINY_CHARCOAL);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
