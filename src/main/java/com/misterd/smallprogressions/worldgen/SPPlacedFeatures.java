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