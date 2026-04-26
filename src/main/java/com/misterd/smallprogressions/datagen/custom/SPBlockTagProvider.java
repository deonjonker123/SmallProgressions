package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class SPBlockTagProvider extends BlockTagsProvider {
    public SPBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SmallProgressions.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get())
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_2.get())
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get())
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get())
                .add(SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get())

                .add(SPBlocks.HARVESTER.get())

                .add(SPBlocks.SIMPLE_ITEM_COLLECTOR.get())
                .add(SPBlocks.ADVANCED_ITEM_COLLECTOR.get())

                .add(SPBlocks.BRICK_FURNACE.get())

                .add(SPBlocks.LAVA_GENERATOR.get())
                .add(SPBlocks.WATER_RESERVOIR.get())

                .add(SPBlocks.COPPER_TANK.get())
                .add(SPBlocks.IRON_TANK.get())
                .add(SPBlocks.GOLD_TANK.get())
                .add(SPBlocks.DIAMOND_TANK.get())

                .add(SPBlocks.GROWTH_CRYSTAL_TIER_1.get())
                .add(SPBlocks.GROWTH_CRYSTAL_TIER_2.get())
                .add(SPBlocks.GROWTH_CRYSTAL_TIER_3.get())
                .add(SPBlocks.GREENHOUSE_GLASS.get())

                .add(SPBlocks.LAVA_INFUSED_STONE.get())
                .add(SPBlocks.MCFLOATY_BLOCK.get())

                .add(SPBlocks.CHARCOAL_BLOCK.get())

                .add(SPBlocks.WIRELESS_REDSTONE_TRANSMITTER.get())
                .add(SPBlocks.WIRELESS_REDSTONE_RECEIVER.get())
                .add(SPBlocks.TIMER.get())
                .add(SPBlocks.BASIC_SOLAR_PANEL.get())
                .add(SPBlocks.HARDENED_SOLAR_PANEL.get())
                .add(SPBlocks.ADVANCED_SOLAR_PANEL.get())
                .add(SPBlocks.ELITE_SOLAR_PANEL.get())
                .add(SPBlocks.ULTIMATE_SOLAR_PANEL.get())

                .add(SPBlocks.BASIC_BATTERY.get())
                .add(SPBlocks.HARDENED_BATTERY.get())
                .add(SPBlocks.ADVANCED_BATTERY.get())
                .add(SPBlocks.ELITE_BATTERY.get())
                .add(SPBlocks.ULTIMATE_BATTERY.get())

                .add(SPBlocks.BASIC_ENERGY_RECEIVER.get())
                .add(SPBlocks.HARDENED_ENERGY_RECEIVER.get())
                .add(SPBlocks.ADVANCED_ENERGY_RECEIVER.get())
                .add(SPBlocks.ELITE_ENERGY_RECEIVER.get())
                .add(SPBlocks.ULTIMATE_ENERGY_RECEIVER.get())

                .add(SPBlocks.ENERGY_TRANSMITTER.get())

                .add(SPBlocks.LOGISTICS_RECEIVER.get())
                .add(SPBlocks.LOGISTICS_SENDER.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(SPBlocks.COPPER_BARREL.get())
                .add(SPBlocks.IRON_BARREL.get())
                .add(SPBlocks.GOLD_BARREL.get())
                .add(SPBlocks.DIAMOND_BARREL.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(SPBlocks.LINEN_SACK.get());
    }
}
