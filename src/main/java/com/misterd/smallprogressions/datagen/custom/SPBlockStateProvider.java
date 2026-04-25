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
        blockWithItem(SPBlocks.STEEL_BLOCK);

        blockWithItem(SPBlocks.THATCH_BLOCK);
        stairsBlock(SPBlocks.THATCH_STAIRS.get(), blockTexture(SPBlocks.THATCH_BLOCK.get()));
        slabBlock(SPBlocks.THATCH_SLAB.get(), blockTexture(SPBlocks.THATCH_BLOCK.get()), blockTexture(SPBlocks.THATCH_BLOCK.get()));
        blockItem(SPBlocks.THATCH_STAIRS);
        blockItem(SPBlocks.THATCH_SLAB);

        blockWithItem(SPBlocks.REINFORCED_OBSIDIAN);
        blockWithItem(SPBlocks.REINFORCED_GLASS);

        blockWithItem(SPBlocks.HARDENED_STONE);
        blockWithItem(SPBlocks.HARDENED_STONE_BRICKS);
        stairsBlock(SPBlocks.HARDENED_STONE_STAIRS.get(), blockTexture(SPBlocks.HARDENED_STONE.get()));
        stairsBlock(SPBlocks.HARDENED_STONE_BRICK_STAIRS.get(), blockTexture(SPBlocks.HARDENED_STONE_BRICKS.get()));
        slabBlock(SPBlocks.HARDENED_STONE_SLAB.get(), blockTexture(SPBlocks.HARDENED_STONE.get()), blockTexture(SPBlocks.HARDENED_STONE.get()));
        slabBlock(SPBlocks.HARDENED_STONE_BRICK_SLAB.get(), blockTexture(SPBlocks.HARDENED_STONE_BRICKS.get()), blockTexture(SPBlocks.HARDENED_STONE_BRICKS.get()));
        buttonBlock(SPBlocks.HARDENED_STONE_BUTTON.get(), blockTexture(SPBlocks.HARDENED_STONE.get()));
        pressurePlateBlock(SPBlocks.HARDENED_STONE_PRESSURE_PLATE.get(), blockTexture(SPBlocks.HARDENED_STONE.get()));
        wallBlock(SPBlocks.HARDENED_STONE_WALL.get(), blockTexture(SPBlocks.HARDENED_STONE.get()));
        wallBlock(SPBlocks.HARDENED_STONE_BRICK_WALL.get(), blockTexture(SPBlocks.HARDENED_STONE_BRICKS.get()));
        blockItem(SPBlocks.HARDENED_STONE_STAIRS);
        blockItem(SPBlocks.HARDENED_STONE_BRICK_STAIRS);
        blockItem(SPBlocks.HARDENED_STONE_SLAB);
        blockItem(SPBlocks.HARDENED_STONE_BRICK_SLAB);
        blockItem(SPBlocks.HARDENED_STONE_PRESSURE_PLATE);

        blockWithItem(SPBlocks.STONE_ENDER_ORE);
        blockWithItem(SPBlocks.DEEPSLATE_ENDER_ORE);
        blockWithItem(SPBlocks.NETHERRACK_ENDER_ORE);
        blockWithItem(SPBlocks.ENDSTONE_ENDER_ORE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("smallprogressions:block/" + deferredBlock.getId().getPath()));
    }
}
