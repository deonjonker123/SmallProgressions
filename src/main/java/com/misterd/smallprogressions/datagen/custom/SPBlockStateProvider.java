package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class SPBlockStateProvider extends BlockStateProvider {
    public SPBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SmallProgressions.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(SPBlocks.GROWTH_CRYSTAL_TIER_1);
        blockWithItem(SPBlocks.GROWTH_CRYSTAL_TIER_2);
        blockWithItem(SPBlocks.GROWTH_CRYSTAL_TIER_3);

        blockWithItem(SPBlocks.GREENHOUSE_GLASS);

        blockWithItem(SPBlocks.LAVA_INFUSED_STONE);
        blockWithItem(SPBlocks.MCFLOATY_BLOCK);

        blockWithItem(SPBlocks.CHARCOAL_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("smallprogressions:block/" + deferredBlock.getId().getPath()));
    }
}
