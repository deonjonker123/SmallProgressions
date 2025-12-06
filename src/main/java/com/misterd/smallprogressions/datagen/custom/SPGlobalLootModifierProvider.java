package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.loot.AddItemModifier;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class SPGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public SPGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, SmallProgressions.MODID);
    }

    @Override
    protected void start() {
        addBossLoot("dragon_scale_from_ender_dragon", "entities/ender_dragon", SPItems.DRAGON_SCALE);
        addBossLoot("wither_rib_from_wither", "entities/wither", SPItems.WITHER_RIB);

        this.add("cotton_seeds_to_short_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, SPItems.COTTON_SEEDS.get()));
        this.add("cotton_seeds_to_tall_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, SPItems.COTTON_SEEDS.get()));

        this.add("flax_seeds_to_short_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, SPItems.FLAX_SEEDS.get()));
        this.add("flax_seeds_to_tall_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, SPItems.FLAX_SEEDS.get()));

        this.add("straw_from_short_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(SPItems.SICKLE.get())).build()
                }, SPItems.STRAW.get()));

        this.add("straw_from_tall_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(SPItems.SICKLE.get())).build()
                }, SPItems.STRAW.get()));
    }

    private void addBossLoot(String name, String entityPath, net.neoforged.neoforge.registries.DeferredItem<?> item) {
        add(name, new AddItemModifier(
                new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(entityPath)).build()
                },
                item.get()
        ));
    }
}
