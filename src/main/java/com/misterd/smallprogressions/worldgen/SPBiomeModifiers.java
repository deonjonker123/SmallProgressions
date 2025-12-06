package com.misterd.smallprogressions.worldgen;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.util.SPTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class SPBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_OVERWORLD_ENDER_ORE = registerKey("add_overworld_ender_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_ENDER_ORE = registerKey("add_nether_ender_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_ENDER_ORE = registerKey("add_end_ender_ore");

    public static final ResourceKey<BiomeModifier> ADD_BLACKBERRY_BUSH = registerKey("add_blackberry_bush");
    public static final ResourceKey<BiomeModifier> ADD_BLUEBERRY_BUSH = registerKey("add_blueberry_bush");
    public static final ResourceKey<BiomeModifier> ADD_MALOBERRY_BUSH = registerKey("add_maloberry_bush");
    public static final ResourceKey<BiomeModifier> ADD_RASPBERRY_BUSH = registerKey("add_raspberry_bush");

    public static final ResourceKey<BiomeModifier> ADD_MARBLE = registerKey("add_marble");
    public static final ResourceKey<BiomeModifier> ADD_SLATE = registerKey("add_slate");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(ADD_OVERWORLD_ENDER_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(SPPlacedFeatures.OVERWORLD_ENDER_ORE_PLACED_KEY)
                        ),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        context.register(ADD_NETHER_ENDER_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_NETHER),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(SPPlacedFeatures.NETHER_ENDER_ORE_PLACED_KEY)
                        ),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        context.register(ADD_END_ENDER_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_END),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(SPPlacedFeatures.END_ENDER_ORE_PLACED_KEY)
                        ),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        context.register(ADD_MARBLE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(SPPlacedFeatures.MARBLE_PLACED_KEY)
                        ),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        context.register(ADD_SLATE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(SPPlacedFeatures.SLATE_PLACED_KEY)
                        ),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        context.register(ADD_BLACKBERRY_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(SPTags.Biomes.HAS_BERRY_BUSHES),
                HolderSet.direct(placedFeatures.getOrThrow(SPPlacedFeatures.BLACKBERRY_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_BLUEBERRY_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(SPTags.Biomes.HAS_BERRY_BUSHES),
                HolderSet.direct(placedFeatures.getOrThrow(SPPlacedFeatures.BLUEBERRY_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_MALOBERRY_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(SPTags.Biomes.HAS_BERRY_BUSHES),
                HolderSet.direct(placedFeatures.getOrThrow(SPPlacedFeatures.MALOBERRY_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_RASPBERRY_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(SPTags.Biomes.HAS_BERRY_BUSHES),
                HolderSet.direct(placedFeatures.getOrThrow(SPPlacedFeatures.RASPBERRY_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(
                NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, name)
        );
    }
}