package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

public class SPModelProvider extends ModelProvider {
    public SPModelProvider(PackOutput output) {
        super(output, SmallProgressions.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // Items
        itemModels.generateFlatItem(SPItems.TINY_COAL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SPItems.TINY_CHARCOAL.get(), ModelTemplates.FLAT_ITEM);

        // Simple cube blocks (datagen generates model + blockstate)
        blockModels.createTrivialCube(SPBlocks.GROWTH_CRYSTAL_TIER_1.get());
        blockModels.createTrivialCube(SPBlocks.GROWTH_CRYSTAL_TIER_2.get());
        blockModels.createTrivialCube(SPBlocks.GROWTH_CRYSTAL_TIER_3.get());
        blockModels.createTrivialCube(SPBlocks.GREENHOUSE_GLASS.get());
        blockModels.createTrivialCube(SPBlocks.LAVA_INFUSED_STONE.get());
        blockModels.createTrivialCube(SPBlocks.MCFLOATY_BLOCK.get());
        blockModels.createTrivialCube(SPBlocks.CHARCOAL_BLOCK.get());
        blockModels.createTrivialCube(SPBlocks.COBBLESTONE_GENERATOR_TIER_1.get());
        blockModels.createTrivialCube(SPBlocks.COBBLESTONE_GENERATOR_TIER_2.get());
        blockModels.createTrivialCube(SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get());
        blockModels.createTrivialCube(SPBlocks.COBBLESTONE_GENERATOR_TIER_4.get());
        blockModels.createTrivialCube(SPBlocks.COBBLESTONE_GENERATOR_TIER_5.get());
        blockModels.createTrivialCube(SPBlocks.SIMPLE_ITEM_COLLECTOR.get());
        blockModels.createTrivialCube(SPBlocks.ADVANCED_ITEM_COLLECTOR.get());
        blockModels.createTrivialCube(SPBlocks.HARVESTER.get());
        blockModels.createTrivialCube(SPBlocks.COPPER_BARREL.get());
        blockModels.createTrivialCube(SPBlocks.IRON_BARREL.get());
        blockModels.createTrivialCube(SPBlocks.GOLD_BARREL.get());
        blockModels.createTrivialCube(SPBlocks.DIAMOND_BARREL.get());
        blockModels.createTrivialCube(SPBlocks.COPPER_TANK.get());
        blockModels.createTrivialCube(SPBlocks.IRON_TANK.get());
        blockModels.createTrivialCube(SPBlocks.GOLD_TANK.get());
        blockModels.createTrivialCube(SPBlocks.DIAMOND_TANK.get());
        blockModels.createTrivialCube(SPBlocks.LINEN_SACK.get());
        blockModels.createTrivialCube(SPBlocks.BRICK_FURNACE.get());
        blockModels.createTrivialCube(SPBlocks.LAVA_GENERATOR.get());
        blockModels.createTrivialCube(SPBlocks.WATER_RESERVOIR.get());
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return SPBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return SPItems.ITEMS.getEntries().stream();
    }
}