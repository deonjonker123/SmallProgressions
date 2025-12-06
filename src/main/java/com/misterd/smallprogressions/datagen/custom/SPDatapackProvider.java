package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.worldgen.SPBiomeModifiers;
import com.misterd.smallprogressions.worldgen.SPConfiguredFeatures;
import com.misterd.smallprogressions.worldgen.SPPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SPDatapackProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, SPConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, SPPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, SPBiomeModifiers::bootstrap);

    public SPDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(SmallProgressions.MODID));
    }
}
