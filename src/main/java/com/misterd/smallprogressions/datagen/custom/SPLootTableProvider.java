package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.block.custom.CottonCropBlock;
import com.misterd.smallprogressions.block.custom.FlaxCropBlock;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

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

        dropSelf(SPBlocks.FUEL_RESERVOIR.get());

        dropSelf(SPBlocks.SIMPLE_ITEM_COLLECTOR.get());
        dropSelf(SPBlocks.ADVANCED_ITEM_COLLECTOR.get());

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

        dropSelf(SPBlocks.LAVA_INFUSED_STONE.get());

        dropSelf(SPBlocks.MCFLOATY_BLOCK.get());

        dropSelf(SPBlocks.CHARCOAL_BLOCK.get());
        dropSelf(SPBlocks.STEEL_BLOCK.get());

        dropSelf(SPBlocks.MARBLE.get());
        dropSelf(SPBlocks.MARBLE_BRICKS.get());
        dropSelf(SPBlocks.MARBLE_PILLAR.get());
        dropSelf(SPBlocks.MARBLE_STAIRS.get());
        dropSelf(SPBlocks.MARBLE_BRICK_STAIRS.get());
        add(SPBlocks.MARBLE_SLAB.get(), block -> createSlabItemTable(SPBlocks.MARBLE_SLAB.get()));
        add(SPBlocks.MARBLE_BRICK_SLAB.get(), block -> createSlabItemTable(SPBlocks.MARBLE_BRICK_SLAB.get()));
        dropSelf(SPBlocks.MARBLE_BUTTON.get());
        dropSelf(SPBlocks.MARBLE_PRESSURE_PLATE.get());
        dropSelf(SPBlocks.MARBLE_WALL.get());
        dropSelf(SPBlocks.MARBLE_BRICK_WALL.get());

        dropSelf(SPBlocks.SLATE.get());
        dropSelf(SPBlocks.SLATE_BRICKS.get());
        dropSelf(SPBlocks.SLATE_PILLAR.get());
        dropSelf(SPBlocks.SLATE_STAIRS.get());
        dropSelf(SPBlocks.SLATE_BRICK_STAIRS.get());
        add(SPBlocks.SLATE_SLAB.get(), block -> createSlabItemTable(SPBlocks.SLATE_SLAB.get()));
        add(SPBlocks.SLATE_BRICK_SLAB.get(), block -> createSlabItemTable(SPBlocks.SLATE_BRICK_SLAB.get()));
        dropSelf(SPBlocks.SLATE_BUTTON.get());
        dropSelf(SPBlocks.SLATE_PRESSURE_PLATE.get());
        dropSelf(SPBlocks.SLATE_WALL.get());
        dropSelf(SPBlocks.SLATE_BRICK_WALL.get());

        dropSelf(SPBlocks.THATCH_BLOCK.get());
        dropSelf(SPBlocks.THATCH_STAIRS.get());
        dropSelf(SPBlocks.THATCH_SLAB.get());

        dropSelf(SPBlocks.ASPHALT.get());
        dropSelf(SPBlocks.ASPHALT_STAIRS.get());
        add(SPBlocks.ASPHALT_SLAB.get(), block -> createSlabItemTable(SPBlocks.ASPHALT_SLAB.get()));
        dropSelf(SPBlocks.ASPHALT_BUTTON.get());
        dropSelf(SPBlocks.ASPHALT_PRESSURE_PLATE.get());
        dropSelf(SPBlocks.ASPHALT_WALL.get());

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

        add(SPBlocks.BLACK_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.BLACK_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.BLACK_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.BLACK_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.BLUE_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.BLUE_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.BLUE_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.BLUE_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.BROWN_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.BROWN_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.BROWN_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.BROWN_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.CYAN_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.CYAN_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.CYAN_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.CYAN_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.GRAY_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.GRAY_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.GRAY_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.GRAY_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.GREEN_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.GREEN_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.GREEN_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.GREEN_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.LIGHT_BLUE_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.LIGHT_BLUE_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.LIGHT_BLUE_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.LIGHT_BLUE_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.LIGHT_GRAY_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.LIGHT_GRAY_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.LIGHT_GRAY_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.LIGHT_GRAY_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.LIME_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.LIME_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.LIME_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.LIME_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.MAGENTA_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.MAGENTA_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.MAGENTA_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.MAGENTA_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.ORANGE_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.ORANGE_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.ORANGE_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.ORANGE_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.PINK_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.PINK_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.PINK_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.PINK_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.PURPLE_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.PURPLE_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.PURPLE_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.PURPLE_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.RED_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.RED_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.RED_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.RED_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.WHITE_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.WHITE_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.WHITE_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.WHITE_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        add(SPBlocks.YELLOW_GLOWSTONE.get(),
                block -> createSilkTouchDispatchTable(SPBlocks.YELLOW_GLOWSTONE.get(),
                        this.applyExplosionDecay(SPBlocks.YELLOW_GLOWSTONE.get(),
                                LootItem.lootTableItem(SPItems.YELLOW_GLOWSTONE_DUST.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));

        LootItemCondition.Builder flaxLootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.FLAX_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FlaxCropBlock.AGE, 3));

        this.add(SPBlocks.FLAX_CROP.get(), this.createCropDrops(SPBlocks.FLAX_CROP.get(),
                SPItems.FLAX.get(), SPItems.FLAX_SEEDS.get(), flaxLootItemConditionBuilder));

        LootItemCondition.Builder cottonLootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.COTTON_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CottonCropBlock.AGE, 5));

        this.add(SPBlocks.COTTON_CROP.get(), this.createCropDrops(SPBlocks.COTTON_CROP.get(),
                SPItems.COTTON_BOLLS.get(), SPItems.COTTON_SEEDS.get(), cottonLootItemConditionBuilder));

        this.add(SPBlocks.BLACKBERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.BLACKBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(SPItems.BLACKBERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.BLACKBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(SPItems.BLACKBERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));

        this.add(SPBlocks.BLUEBERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.BLUEBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(SPItems.BLUEBERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.BLUEBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(SPItems.BLUEBERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));

        this.add(SPBlocks.MALOBERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.MALOBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(SPItems.MALOBERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.MALOBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(SPItems.MALOBERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));

        this.add(SPBlocks.RASPBERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.RASPBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(SPItems.RASPBERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(SPBlocks.RASPBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(SPItems.RASPBERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return SPBlocks.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .toList();
    }
}
