package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
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
    }

    private void addMobLoot(String name, String entityPath, net.neoforged.neoforge.registries.DeferredItem<?> item, float chance) {
        add(name, new AddItemModifier(
                new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(entityPath)).build(),
                        LootItemRandomChanceCondition.randomChance(chance).build()
                },
                item.get()
        ));
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
