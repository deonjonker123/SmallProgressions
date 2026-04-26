package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class SPLootTableProvider extends BlockLootSubProvider {
    public SPLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get());
        dropSelf(SPBlocks.COBBLESTONE_GENERATOR_TIER_2.get());
        dropSelf(SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get());
        dropSelf(SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get());
        dropSelf(SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get());

        dropSelf(SPBlocks.SIMPLE_ITEM_COLLECTOR.get());
        dropSelf(SPBlocks.ADVANCED_ITEM_COLLECTOR.get());

        dropSelf(SPBlocks.HARVESTER.get());

        dropSelf(SPBlocks.BRICK_FURNACE.get());

        dropSelf(SPBlocks.LAVA_GENERATOR.get());
        dropSelf(SPBlocks.WATER_RESERVOIR.get());

        dropSelf(SPBlocks.COPPER_BARREL.get());
        dropSelf(SPBlocks.IRON_BARREL.get());
        dropSelf(SPBlocks.GOLD_BARREL.get());
        dropSelf(SPBlocks.DIAMOND_BARREL.get());

        dropSelf(SPBlocks.COPPER_TANK.get());
        dropSelf(SPBlocks.IRON_TANK.get());
        dropSelf(SPBlocks.GOLD_TANK.get());
        dropSelf(SPBlocks.DIAMOND_TANK.get());

        dropSelf(SPBlocks.GROWTH_CRYSTAL_TIER_1.get());
        dropSelf(SPBlocks.GROWTH_CRYSTAL_TIER_2.get());
        dropSelf(SPBlocks.GROWTH_CRYSTAL_TIER_3.get());

        dropSelf(SPBlocks.GREENHOUSE_GLASS.get());

        dropSelf(SPBlocks.LAVA_INFUSED_STONE.get());

        dropSelf(SPBlocks.MCFLOATY_BLOCK.get());

        dropSelf(SPBlocks.CHARCOAL_BLOCK.get());

        dropSelf(SPBlocks.WIRELESS_REDSTONE_TRANSMITTER.get());
        dropSelf(SPBlocks.WIRELESS_REDSTONE_RECEIVER.get());
        dropSelf(SPBlocks.TIMER.get());

        dropSelf(SPBlocks.BASIC_SOLAR_PANEL.get());
        dropSelf(SPBlocks.HARDENED_SOLAR_PANEL.get());
        dropSelf(SPBlocks.ADVANCED_SOLAR_PANEL.get());
        dropSelf(SPBlocks.ELITE_SOLAR_PANEL.get());
        dropSelf(SPBlocks.ULTIMATE_SOLAR_PANEL.get());

        dropSelf(SPBlocks.BASIC_ENERGY_RECEIVER.get());
        dropSelf(SPBlocks.HARDENED_ENERGY_RECEIVER.get());
        dropSelf(SPBlocks.ADVANCED_ENERGY_RECEIVER.get());
        dropSelf(SPBlocks.ELITE_ENERGY_RECEIVER.get());
        dropSelf(SPBlocks.ULTIMATE_ENERGY_RECEIVER.get());

        dropSelf(SPBlocks.ENERGY_TRANSMITTER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return SPBlocks.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .filter(b -> b != SPBlocks.BASIC_BATTERY.get()
                        && b != SPBlocks.HARDENED_BATTERY.get()
                        && b != SPBlocks.ADVANCED_BATTERY.get()
                        && b != SPBlocks.ELITE_BATTERY.get()
                        && b != SPBlocks.ULTIMATE_BATTERY.get())
                .toList();
    }
}
