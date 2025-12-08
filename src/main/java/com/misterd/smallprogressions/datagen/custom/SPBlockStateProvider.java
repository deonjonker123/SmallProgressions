package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.block.custom.BlackberryBushBlock;
import com.misterd.smallprogressions.block.custom.BlueberryBushBlock;
import com.misterd.smallprogressions.block.custom.CottonCropBlock;
import com.misterd.smallprogressions.block.custom.FlaxCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

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

        blockWithItem(SPBlocks.MARBLE);
        blockWithItem(SPBlocks.MARBLE_BRICKS);
        axisBlock((RotatedPillarBlock)SPBlocks.MARBLE_PILLAR.get());
        stairsBlock(SPBlocks.MARBLE_STAIRS.get(), blockTexture(SPBlocks.MARBLE.get()));
        stairsBlock(SPBlocks.MARBLE_BRICK_STAIRS.get(), blockTexture(SPBlocks.MARBLE_BRICKS.get()));
        slabBlock(SPBlocks.MARBLE_SLAB.get(), blockTexture(SPBlocks.MARBLE.get()), blockTexture(SPBlocks.MARBLE.get()));
        slabBlock(SPBlocks.MARBLE_BRICK_SLAB.get(), blockTexture(SPBlocks.MARBLE_BRICKS.get()), blockTexture(SPBlocks.MARBLE_BRICKS.get()));
        buttonBlock(SPBlocks.MARBLE_BUTTON.get(), blockTexture(SPBlocks.MARBLE.get()));
        pressurePlateBlock(SPBlocks.MARBLE_PRESSURE_PLATE.get(), blockTexture(SPBlocks.MARBLE.get()));
        wallBlock(SPBlocks.MARBLE_WALL.get(), blockTexture(SPBlocks.MARBLE.get()));
        wallBlock(SPBlocks.MARBLE_BRICK_WALL.get(), blockTexture(SPBlocks.MARBLE_BRICKS.get()));
        blockItem(SPBlocks.MARBLE_PILLAR);
        blockItem(SPBlocks.MARBLE_STAIRS);
        blockItem(SPBlocks.MARBLE_BRICK_STAIRS);
        blockItem(SPBlocks.MARBLE_SLAB);
        blockItem(SPBlocks.MARBLE_BRICK_SLAB);
        blockItem(SPBlocks.MARBLE_PRESSURE_PLATE);

        blockWithItem(SPBlocks.SLATE);
        blockWithItem(SPBlocks.SLATE_BRICKS);
        axisBlock((RotatedPillarBlock)SPBlocks.SLATE_PILLAR.get());
        stairsBlock(SPBlocks.SLATE_STAIRS.get(), blockTexture(SPBlocks.SLATE.get()));
        stairsBlock(SPBlocks.SLATE_BRICK_STAIRS.get(), blockTexture(SPBlocks.SLATE_BRICKS.get()));
        slabBlock(SPBlocks.SLATE_SLAB.get(), blockTexture(SPBlocks.SLATE.get()), blockTexture(SPBlocks.SLATE.get()));
        slabBlock(SPBlocks.SLATE_BRICK_SLAB.get(), blockTexture(SPBlocks.SLATE_BRICKS.get()), blockTexture(SPBlocks.SLATE_BRICKS.get()));
        buttonBlock(SPBlocks.SLATE_BUTTON.get(), blockTexture(SPBlocks.SLATE.get()));
        pressurePlateBlock(SPBlocks.SLATE_PRESSURE_PLATE.get(), blockTexture(SPBlocks.SLATE.get()));
        wallBlock(SPBlocks.SLATE_WALL.get(), blockTexture(SPBlocks.SLATE.get()));
        wallBlock(SPBlocks.SLATE_BRICK_WALL.get(), blockTexture(SPBlocks.SLATE_BRICKS.get()));
        blockItem(SPBlocks.SLATE_PILLAR);
        blockItem(SPBlocks.SLATE_STAIRS);
        blockItem(SPBlocks.SLATE_BRICK_STAIRS);
        blockItem(SPBlocks.SLATE_SLAB);
        blockItem(SPBlocks.SLATE_BRICK_SLAB);
        blockItem(SPBlocks.SLATE_PRESSURE_PLATE);

        blockWithItem(SPBlocks.SOUL_SANDSTONE_BRICKS);
        stairsBlock(SPBlocks.SOUL_SANDSTONE_STAIRS.get(), blockTexture(SPBlocks.SOUL_SANDSTONE.get()));
        stairsBlock(SPBlocks.SOUL_SANDSTONE_BRICK_STAIRS.get(), blockTexture(SPBlocks.SOUL_SANDSTONE_BRICKS.get()));
        slabBlock(SPBlocks.SOUL_SANDSTONE_SLAB.get(), blockTexture(SPBlocks.SOUL_SANDSTONE.get()), blockTexture(SPBlocks.SOUL_SANDSTONE.get()));
        slabBlock(SPBlocks.SOUL_SANDSTONE_BRICK_SLAB.get(), blockTexture(SPBlocks.SOUL_SANDSTONE_BRICKS.get()), blockTexture(SPBlocks.SOUL_SANDSTONE_BRICKS.get()));
        wallBlock(SPBlocks.SOUL_SANDSTONE_WALL.get(), blockTexture(SPBlocks.SOUL_SANDSTONE.get()));
        wallBlock(SPBlocks.SOUL_SANDSTONE_BRICK_WALL.get(), blockTexture(SPBlocks.SOUL_SANDSTONE_BRICKS.get()));
        blockItem(SPBlocks.SOUL_SANDSTONE_STAIRS);
        blockItem(SPBlocks.SOUL_SANDSTONE_BRICK_STAIRS);
        blockItem(SPBlocks.SOUL_SANDSTONE_SLAB);
        blockItem(SPBlocks.SOUL_SANDSTONE_BRICK_SLAB);

        blockWithItem(SPBlocks.THATCH_BLOCK);
        stairsBlock(SPBlocks.THATCH_STAIRS.get(), blockTexture(SPBlocks.THATCH_BLOCK.get()));
        slabBlock(SPBlocks.THATCH_SLAB.get(), blockTexture(SPBlocks.THATCH_BLOCK.get()), blockTexture(SPBlocks.THATCH_BLOCK.get()));
        blockItem(SPBlocks.THATCH_STAIRS);
        blockItem(SPBlocks.THATCH_SLAB);

        blockWithItem(SPBlocks.ASPHALT);
        stairsBlock(SPBlocks.ASPHALT_STAIRS.get(), blockTexture(SPBlocks.ASPHALT.get()));
        slabBlock(SPBlocks.ASPHALT_SLAB.get(), blockTexture(SPBlocks.ASPHALT.get()), blockTexture(SPBlocks.ASPHALT.get()));
        buttonBlock(SPBlocks.ASPHALT_BUTTON.get(), blockTexture(SPBlocks.ASPHALT.get()));
        pressurePlateBlock(SPBlocks.ASPHALT_PRESSURE_PLATE.get(), blockTexture(SPBlocks.ASPHALT.get()));
        wallBlock(SPBlocks.ASPHALT_WALL.get(), blockTexture(SPBlocks.ASPHALT.get()));
        blockItem(SPBlocks.ASPHALT_STAIRS);
        blockItem(SPBlocks.ASPHALT_SLAB);
        blockItem(SPBlocks.ASPHALT_PRESSURE_PLATE);

        blockWithItem(SPBlocks.REINFORCED_OBSIDIAN);
        blockWithItem(SPBlocks.REINFORCED_GLASS);

        blockWithItem(SPBlocks.HARDENED_STONE);
        blockWithItem(SPBlocks.HARDENED_STONE_BRICKS);
        stairsBlock(SPBlocks.HARDENED_STONE_STAIRS.get(), blockTexture(SPBlocks.HARDENED_STONE.get()));
        stairsBlock(SPBlocks.HARDENED_STONE_BRICK_STAIRS.get(), blockTexture(SPBlocks.HARDENED_STONE_BRICKS.get()));
        slabBlock(SPBlocks.HARDENED_STONE_SLAB.get(), blockTexture(SPBlocks.SLATE.get()), blockTexture(SPBlocks.HARDENED_STONE.get()));
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

        blockWithItem(SPBlocks.BLACK_GLOWSTONE);
        blockWithItem(SPBlocks.BLUE_GLOWSTONE);
        blockWithItem(SPBlocks.BROWN_GLOWSTONE);
        blockWithItem(SPBlocks.CYAN_GLOWSTONE);
        blockWithItem(SPBlocks.GRAY_GLOWSTONE);
        blockWithItem(SPBlocks.GREEN_GLOWSTONE);
        blockWithItem(SPBlocks.LIGHT_BLUE_GLOWSTONE);
        blockWithItem(SPBlocks.LIGHT_GRAY_GLOWSTONE);
        blockWithItem(SPBlocks.LIME_GLOWSTONE);
        blockWithItem(SPBlocks.MAGENTA_GLOWSTONE);
        blockWithItem(SPBlocks.ORANGE_GLOWSTONE);
        blockWithItem(SPBlocks.PINK_GLOWSTONE);
        blockWithItem(SPBlocks.PURPLE_GLOWSTONE);
        blockWithItem(SPBlocks.RED_GLOWSTONE);
        blockWithItem(SPBlocks.WHITE_GLOWSTONE);
        blockWithItem(SPBlocks.YELLOW_GLOWSTONE);

        blockWithItem(SPBlocks.BLACK_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.BLUE_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.BROWN_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.CYAN_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.GRAY_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.GREEN_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.LIGHT_BLUE_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.LIGHT_GRAY_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.LIME_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.MAGENTA_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.ORANGE_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.PINK_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.PURPLE_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.RED_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.WHITE_GLOWSTONE_GLASS);
        blockWithItem(SPBlocks.YELLOW_GLOWSTONE_GLASS);

        makeBlackberryBush(((SweetBerryBushBlock) SPBlocks.BLACKBERRY_BUSH.get()), "blackberry_bush_stage", "blackberry_bush_stage");
        makeBlueberryBush(((SweetBerryBushBlock) SPBlocks.BLUEBERRY_BUSH.get()), "blueberry_bush_stage", "blueberry_bush_stage");
        makeMaloberryBush(((SweetBerryBushBlock) SPBlocks.MALOBERRY_BUSH.get()), "maloberry_bush_stage", "maloberry_bush_stage");
        makeRaspberryBush(((SweetBerryBushBlock) SPBlocks.RASPBERRY_BUSH.get()), "raspberry_bush_stage", "raspberry_bush_stage");

        makeFlaxCrop(((CropBlock) SPBlocks.FLAX_CROP.get()), "flax_crop_stage", "flax_crop_stage");
        makeCottonCrop(((CropBlock) SPBlocks.COTTON_CROP.get()), "cotton_crop_stage", "cotton_crop_stage");
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("smallprogressions:block/" + deferredBlock.getId().getPath()));
    }

    public void makeBlackberryBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> blackberryStates(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    public void makeBlueberryBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> blueberryStates(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    public void makeMaloberryBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> maloberryStates(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    public void makeRaspberryBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> raspberryStates(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] blackberryStates(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(BlackberryBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "block/" + textureName + state.getValue(BlackberryBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    private ConfiguredModel[] blueberryStates(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(BlackberryBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "block/" + textureName + state.getValue(BlackberryBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    private ConfiguredModel[] maloberryStates(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(BlackberryBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "block/" + textureName + state.getValue(BlackberryBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    private ConfiguredModel[] raspberryStates(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(BlackberryBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "block/" + textureName + state.getValue(BlackberryBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    public void makeFlaxCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> flaxCropStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] flaxCropStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((FlaxCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "block/" + textureName + state.getValue(((FlaxCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    public void makeCottonCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cottonCropStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cottonCropStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CottonCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "block/" + textureName + state.getValue(((CottonCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
}
