package com.misterd.smallprogressions.worldgen;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class SPConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ENDER_ORE_KEY = registerKey("overworld_ender_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_ENDER_ORE_KEY = registerKey("nether_ender_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ENDER_ORE_KEY = registerKey("end_ender_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKBERRY_BUSH_KEY = registerKey("blackberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUEBERRY_BUSH_KEY = registerKey("blueberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MALOBERRY_BUSH_KEY = registerKey("maloberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RASPBERRY_BUSH_KEY = registerKey("raspberry_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MARBLE_KEY = registerKey("marble");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SLATE_KEY = registerKey("slate");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        TagMatchTest stoneOreReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        TagMatchTest deepslateOreReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        BlockMatchTest netherReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        BlockMatchTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldEnderOres = List.of(
                OreConfiguration.target(stoneOreReplaceables, SPBlocks.STONE_ENDER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateOreReplaceables, SPBlocks.DEEPSLATE_ENDER_ORE.get().defaultBlockState())
        );
        register(context, OVERWORLD_ENDER_ORE_KEY, Feature.ORE, new OreConfiguration(overworldEnderOres, 12));

        List<OreConfiguration.TargetBlockState> netherOres = List.of(
                OreConfiguration.target(netherReplaceables, SPBlocks.NETHERRACK_ENDER_ORE.get().defaultBlockState())
        );
        register(context, NETHER_ENDER_ORE_KEY, Feature.ORE, new OreConfiguration(netherOres, 12));

        List<OreConfiguration.TargetBlockState> endOres = List.of(
                OreConfiguration.target(endReplaceables, SPBlocks.ENDSTONE_ENDER_ORE.get().defaultBlockState())
        );
        register(context, END_ENDER_ORE_KEY, Feature.ORE, new OreConfiguration(endOres, 12));

        List<OreConfiguration.TargetBlockState> marbleTargets = List.of(
                OreConfiguration.target(stoneOreReplaceables, SPBlocks.MARBLE.get().defaultBlockState()),
                OreConfiguration.target(deepslateOreReplaceables, SPBlocks.MARBLE.get().defaultBlockState())
        );
        register(context, MARBLE_KEY, Feature.ORE, new OreConfiguration(marbleTargets, 64));

        List<OreConfiguration.TargetBlockState> slateTargets = List.of(
                OreConfiguration.target(stoneOreReplaceables, SPBlocks.SLATE.get().defaultBlockState()),
                OreConfiguration.target(deepslateOreReplaceables, SPBlocks.SLATE.get().defaultBlockState())
        );
        register(context, SLATE_KEY, Feature.ORE, new OreConfiguration(slateTargets, 64));

        register(context, BLACKBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(SPBlocks.BLACKBERRY_BUSH.get()
                                .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));

        register(context, BLUEBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(SPBlocks.BLUEBERRY_BUSH.get()
                                .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));

        register(context, MALOBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(SPBlocks.MALOBERRY_BUSH.get()
                                .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));

        register(context, RASPBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(SPBlocks.RASPBERRY_BUSH.get()
                                .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}