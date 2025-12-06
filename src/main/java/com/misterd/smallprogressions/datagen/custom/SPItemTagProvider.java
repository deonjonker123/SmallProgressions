package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.util.SPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SPItemTagProvider extends ItemTagsProvider {
    public SPItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, "smallprogressions", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.ORES)
                .add((SPBlocks.STONE_ENDER_ORE.get()).asItem())
                .add((SPBlocks.DEEPSLATE_ENDER_ORE.get()).asItem())
                .add((SPBlocks.NETHERRACK_ENDER_ORE.get()).asItem())
                .add((SPBlocks.ENDSTONE_ENDER_ORE.get()).asItem());

        tag(Tags.Items.INGOTS)
                .add(SPItems.STEEL_INGOT.get())
                .add(SPItems.REINFORCED_OBSIDIAN_INGOT.get());

        tag(SPTags.Items.SMALL_PROGRESSIONS_ORES)
                .add((SPBlocks.STONE_ENDER_ORE.get()).asItem())
                .add((SPBlocks.DEEPSLATE_ENDER_ORE.get()).asItem())
                .add((SPBlocks.NETHERRACK_ENDER_ORE.get()).asItem())
                .add((SPBlocks.ENDSTONE_ENDER_ORE.get()).asItem());

        tag(SPTags.Items.SMALL_PROGRESSIONS_INGOTS)
                .add(SPItems.STEEL_INGOT.get())
                .add(SPItems.REINFORCED_OBSIDIAN_INGOT.get());

        tag(SPTags.Items.SMALL_PROGRESSIONS_NUGGETS)
                .add(SPItems.STEEL_NUGGET.get());
    }
}
