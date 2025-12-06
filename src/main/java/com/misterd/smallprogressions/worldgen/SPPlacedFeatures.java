package com.misterd.smallprogressions.worldgen;

import com.misterd.smallprogressions.SmallProgressions;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class SPPlacedFeatures {
    public static final ResourceKey<PlacedFeature> OVERWORLD_ENDER_ORE_PLACED_KEY = registerKey("overworld_ender_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_ENDER_ORE_PLACED_KEY = registerKey("nether_ender_ore_placed");
    public static final ResourceKey<PlacedFeature> END_ENDER_ORE_PLACED_KEY = registerKey("end_ender_ore_placed");

    public static final ResourceKey<PlacedFeature> BLACKBERRY_BUSH_PLACED_KEY = registerKey("blackberry_bush_placed");
    public static final ResourceKey<PlacedFeature> BLUEBERRY_BUSH_PLACED_KEY = registerKey("blueberry_bush_placed");
    public static final ResourceKey<PlacedFeature> MALOBERRY_BUSH_PLACED_KEY = registerKey("maloberry_bush_placed");
    public static final ResourceKey<PlacedFeature> RASPBERRY_BUSH_PLACED_KEY = registerKey("raspberry_bush_placed");

    public static final ResourceKey<PlacedFeature> MARBLE_PLACED_KEY = registerKey("marble_placed");
    public static final ResourceKey<PlacedFeature> SLATE_PLACED_KEY = registerKey("slate_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, OVERWORLD_ENDER_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(SPConfiguredFeatures.OVERWORLD_ENDER_ORE_KEY),
                SPOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, NETHER_ENDER_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(SPConfiguredFeatures.NETHER_ENDER_ORE_KEY),
                SPOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, END_ENDER_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(SPConfiguredFeatures.END_ENDER_ORE_KEY),
                SPOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, MARBLE_PLACED_KEY,
                configuredFeatures.getOrThrow(SPConfiguredFeatures.MARBLE_KEY),
                SPOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));

        register(context, SLATE_PLACED_KEY,
                configuredFeatures.getOrThrow(SPConfiguredFeatures.SLATE_KEY),
                SPOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));

        register(context, BLACKBERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(SPConfiguredFeatures.BLACKBERRY_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

        register(context, BLUEBERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(SPConfiguredFeatures.BLUEBERRY_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

        register(context, MALOBERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(SPConfiguredFeatures.MALOBERRY_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

        register(context, RASPBERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(SPConfiguredFeatures.RASPBERRY_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context,
                                 ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}