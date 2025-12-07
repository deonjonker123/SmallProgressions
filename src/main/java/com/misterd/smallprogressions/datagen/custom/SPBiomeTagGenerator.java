package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.util.SPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SPBiomeTagGenerator extends BiomeTagsProvider {
    public SPBiomeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SmallProgressions.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(SPTags.Biomes.HAS_BERRY_BUSHES)
                .addTag(BiomeTags.IS_FOREST)
                .addTag(BiomeTags.IS_TAIGA)
                .addTag(BiomeTags.HAS_VILLAGE_PLAINS)
                .addTag(BiomeTags.IS_MOUNTAIN);
    }
}