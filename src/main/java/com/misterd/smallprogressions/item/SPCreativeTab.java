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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SPBlocks.ADVANCED_ITEM_COLLECTOR.get()))
                    .title(Component.translatable("creativetab.smallprogressions"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_1);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_2);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_3);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_4);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_5);
                        output.accept(SPBlocks.FUEL_RESERVOIR);
                        output.accept(SPBlocks.SIMPLE_ITEM_COLLECTOR);
                        output.accept(SPBlocks.ADVANCED_ITEM_COLLECTOR);

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

                        output.accept(SPBlocks.BLACK_GLOWSTONE);
                        output.accept(SPBlocks.BLUE_GLOWSTONE);
                        output.accept(SPBlocks.BROWN_GLOWSTONE);
                        output.accept(SPBlocks.CYAN_GLOWSTONE);
                        output.accept(SPBlocks.GRAY_GLOWSTONE);
                        output.accept(SPBlocks.GREEN_GLOWSTONE);
                        output.accept(SPBlocks.LIGHT_BLUE_GLOWSTONE);
                        output.accept(SPBlocks.LIGHT_GRAY_GLOWSTONE);
                        output.accept(SPBlocks.LIME_GLOWSTONE);
                        output.accept(SPBlocks.MAGENTA_GLOWSTONE);
                        output.accept(SPBlocks.ORANGE_GLOWSTONE);
                        output.accept(SPBlocks.PINK_GLOWSTONE);
                        output.accept(SPBlocks.PURPLE_GLOWSTONE);
                        output.accept(SPBlocks.RED_GLOWSTONE);
                        output.accept(SPBlocks.WHITE_GLOWSTONE);
                        output.accept(SPBlocks.YELLOW_GLOWSTONE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
