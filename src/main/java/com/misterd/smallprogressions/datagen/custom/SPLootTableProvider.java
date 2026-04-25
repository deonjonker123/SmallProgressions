package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.SPItems;
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

        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

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
        dropSelf(SPBlocks.STEEL_BLOCK.get());

        dropSelf(SPBlocks.THATCH_BLOCK.get());
        dropSelf(SPBlocks.THATCH_STAIRS.get());
        dropSelf(SPBlocks.THATCH_SLAB.get());

        dropSelf(SPBlocks.REINFORCED_OBSIDIAN.get());
        dropSelf(SPBlocks.REINFORCED_GLASS.get());

        dropSelf(SPBlocks.HARDENED_STONE.get());
        dropSelf(SPBlocks.HARDENED_STONE_BRICKS.get());
        dropSelf(SPBlocks.HARDENED_STONE_STAIRS.get());
        dropSelf(SPBlocks.HARDENED_STONE_BRICK_STAIRS.get());
        add(SPBlocks.HARDENED_STONE_SLAB.get(), block -> createSlabItemTable(SPBlocks.HARDENED_STONE_SLAB.get()));
        add(SPBlocks.HARDENED_STONE_BRICK_SLAB.get(), block -> createSlabItemTable(SPBlocks.HARDENED_STONE_BRICK_SLAB.get()));
        dropSelf(SPBlocks.HARDENED_STONE_BUTTON.get());
        dropSelf(SPBlocks.HARDENED_STONE_PRESSURE_PLATE.get());
        dropSelf(SPBlocks.HARDENED_STONE_WALL.get());
        dropSelf(SPBlocks.HARDENED_STONE_BRICK_WALL.get());

        add(SPBlocks.STONE_ENDER_ORE.get(),
                block -> createOreDrop(SPBlocks.STONE_ENDER_ORE.get(),
                        SPItems.ENDER_DUST.get()));

        add(SPBlocks.DEEPSLATE_ENDER_ORE.get(),
                block -> createOreDrop(SPBlocks.DEEPSLATE_ENDER_ORE.get(),
                        SPItems.ENDER_DUST.get()));

        add(SPBlocks.NETHERRACK_ENDER_ORE.get(),
                block -> createOreDrop(SPBlocks.NETHERRACK_ENDER_ORE.get(),
                        SPItems.ENDER_DUST.get()));

        add(SPBlocks.ENDSTONE_ENDER_ORE.get(),
                block -> createOreDrop(SPBlocks.ENDSTONE_ENDER_ORE.get(),
                        SPItems.ENDER_DUST.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return SPBlocks.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .toList();
    }
}
