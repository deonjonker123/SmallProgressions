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
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(
                NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, name)
        );
    }
}