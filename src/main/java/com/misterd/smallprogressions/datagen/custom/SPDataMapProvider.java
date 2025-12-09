package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class SPDataMapProvider extends DataMapProvider {
    public SPDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(SPBlocks.CHARCOAL_BLOCK.getId(), new FurnaceFuel(16000), false)
                .add(SPItems.TINY_COAL.getId(), new FurnaceFuel(200), false)
                .add(SPItems.TINY_CHARCOAL.getId(), new FurnaceFuel(200), false);
    }
}
