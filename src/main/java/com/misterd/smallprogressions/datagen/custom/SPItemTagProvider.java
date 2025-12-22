package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.SPItems;
import com.misterd.smallprogressions.util.SPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

        tag(Tags.Items.TOOLS)
                .add(SPItems.STEEL_SWORD.get())
                .add(SPItems.STEEL_PICKAXE.get())
                .add(SPItems.STEEL_AXE.get())
                .add(SPItems.STEEL_SHOVEL.get())
                .add(SPItems.STEEL_HOE.get())
                .add(SPItems.STEEL_PAXEL.get())

                .add(SPItems.EMERALD_SWORD.get())
                .add(SPItems.EMERALD_PICKAXE.get())
                .add(SPItems.EMERALD_AXE.get())
                .add(SPItems.EMERALD_SHOVEL.get())
                .add(SPItems.EMERALD_HOE.get())
                .add(SPItems.EMERALD_PAXEL.get())

                .add(SPItems.WITHER_SWORD.get())
                .add(SPItems.WITHER_PICKAXE.get())
                .add(SPItems.WITHER_AXE.get())
                .add(SPItems.WITHER_SHOVEL.get())
                .add(SPItems.WITHER_HOE.get())
                .add(SPItems.WITHER_PAXEL.get())

                .add(SPItems.DRAGON_SWORD.get())
                .add(SPItems.DRAGON_PICKAXE.get())
                .add(SPItems.DRAGON_AXE.get())
                .add(SPItems.DRAGON_SHOVEL.get())
                .add(SPItems.DRAGON_HOE.get())
                .add(SPItems.DRAGON_PAXEL.get());

        tag(Tags.Items.ARMORS)
                .add(SPItems.STEEL_HELMET.get())
                .add(SPItems.STEEL_CHESTPLATE.get())
                .add(SPItems.STEEL_LEGGINGS.get())
                .add(SPItems.STEEL_BOOTS.get())

                .add(SPItems.EMERALD_HELMET.get())
                .add(SPItems.EMERALD_CHESTPLATE.get())
                .add(SPItems.EMERALD_LEGGINGS.get())
                .add(SPItems.EMERALD_BOOTS.get())

                .add(SPItems.WITHER_HELMET.get())
                .add(SPItems.WITHER_CHESTPLATE.get())
                .add(SPItems.WITHER_LEGGINGS.get())
                .add(SPItems.WITHER_BOOTS.get())

                .add(SPItems.DRAGON_HELMET.get())
                .add(SPItems.DRAGON_CHESTPLATE.get())
                .add(SPItems.DRAGON_LEGGINGS.get())
                .add(SPItems.DRAGON_BOOTS.get());

        tag(SPTags.Items.SMALL_PROGRESSIONS_TOOLS)
                .add(SPItems.STEEL_SWORD.get())
                .add(SPItems.STEEL_PICKAXE.get())
                .add(SPItems.STEEL_AXE.get())
                .add(SPItems.STEEL_SHOVEL.get())
                .add(SPItems.STEEL_HOE.get())
                .add(SPItems.STEEL_PAXEL.get())

                .add(SPItems.EMERALD_SWORD.get())
                .add(SPItems.EMERALD_PICKAXE.get())
                .add(SPItems.EMERALD_AXE.get())
                .add(SPItems.EMERALD_SHOVEL.get())
                .add(SPItems.EMERALD_HOE.get())
                .add(SPItems.EMERALD_PAXEL.get())

                .add(SPItems.WITHER_SWORD.get())
                .add(SPItems.WITHER_PICKAXE.get())
                .add(SPItems.WITHER_AXE.get())
                .add(SPItems.WITHER_SHOVEL.get())
                .add(SPItems.WITHER_HOE.get())
                .add(SPItems.WITHER_PAXEL.get())

                .add(SPItems.DRAGON_SWORD.get())
                .add(SPItems.DRAGON_PICKAXE.get())
                .add(SPItems.DRAGON_AXE.get())
                .add(SPItems.DRAGON_SHOVEL.get())
                .add(SPItems.DRAGON_HOE.get())
                .add(SPItems.DRAGON_PAXEL.get());

        tag(SPTags.Items.SMALL_PROGRESSIONS_ARMORS)
                .add(SPItems.STEEL_HELMET.get())
                .add(SPItems.STEEL_CHESTPLATE.get())
                .add(SPItems.STEEL_LEGGINGS.get())
                .add(SPItems.STEEL_BOOTS.get())

                .add(SPItems.EMERALD_HELMET.get())
                .add(SPItems.EMERALD_CHESTPLATE.get())
                .add(SPItems.EMERALD_LEGGINGS.get())
                .add(SPItems.EMERALD_BOOTS.get())

                .add(SPItems.WITHER_HELMET.get())
                .add(SPItems.WITHER_CHESTPLATE.get())
                .add(SPItems.WITHER_LEGGINGS.get())
                .add(SPItems.WITHER_BOOTS.get())

                .add(SPItems.DRAGON_HELMET.get())
                .add(SPItems.DRAGON_CHESTPLATE.get())
                .add(SPItems.DRAGON_LEGGINGS.get())
                .add(SPItems.DRAGON_BOOTS.get());

        tag(SPTags.Items.SMALL_PROGRESSIONS_GLOWSTONE_BLOCKS)
                .add(SPBlocks.BLACK_GLOWSTONE.asItem())
                .add(SPBlocks.BLUE_GLOWSTONE.asItem())
                .add(SPBlocks.BROWN_GLOWSTONE.asItem())
                .add(SPBlocks.CYAN_GLOWSTONE.asItem())
                .add(SPBlocks.GRAY_GLOWSTONE.asItem())
                .add(SPBlocks.GREEN_GLOWSTONE.asItem())
                .add(SPBlocks.LIGHT_BLUE_GLOWSTONE.asItem())
                .add(SPBlocks.LIGHT_GRAY_GLOWSTONE.asItem())
                .add(SPBlocks.LIME_GLOWSTONE.asItem())
                .add(SPBlocks.MAGENTA_GLOWSTONE.asItem())
                .add(SPBlocks.ORANGE_GLOWSTONE.asItem())
                .add(SPBlocks.PINK_GLOWSTONE.asItem())
                .add(SPBlocks.PURPLE_GLOWSTONE.asItem())
                .add(SPBlocks.RED_GLOWSTONE.asItem())
                .add(SPBlocks.WHITE_GLOWSTONE.asItem())
                .add(SPBlocks.YELLOW_GLOWSTONE.asItem())
                .add(Blocks.GLOWSTONE.asItem());

        tag(Tags.Items.GLASS_BLOCKS)
                .add(SPBlocks.REINFORCED_GLASS.asItem());

        tag(Tags.Items.CROPS)
                .add(SPItems.FLAX.get())
                .add(SPItems.FLAX_SEEDS.get())
                .add(SPItems.COTTON_BOLLS.get())
                .add(SPItems.COTTON_SEEDS.get())
                .add(SPItems.BLACKBERRIES.get())
                .add(SPItems.BLUEBERRIES.get())
                .add(SPItems.MALOBERRIES.get())
                .add(SPItems.RASPBERRIES.get());

        tag(Tags.Items.BUCKETS)
                .add(SPItems.BIG_BUCKET.get());

        tag(ItemTags.SWORD_ENCHANTABLE)
                .add(SPItems.STEEL_SWORD.get())
                .add(SPItems.EMERALD_SWORD.get())
                .add(SPItems.WITHER_SWORD.get())
                .add(SPItems.DRAGON_SWORD.get());

        tag(ItemTags.MINING_ENCHANTABLE)
                .add(SPItems.STEEL_PICKAXE.get())
                .add(SPItems.STEEL_AXE.get())
                .add(SPItems.STEEL_SHOVEL.get())
                .add(SPItems.EMERALD_PICKAXE.get())
                .add(SPItems.EMERALD_AXE.get())
                .add(SPItems.EMERALD_SHOVEL.get())
                .add(SPItems.WITHER_PICKAXE.get())
                .add(SPItems.WITHER_AXE.get())
                .add(SPItems.WITHER_SHOVEL.get())
                .add(SPItems.DRAGON_PICKAXE.get())
                .add(SPItems.DRAGON_AXE.get())
                .add(SPItems.DRAGON_SHOVEL.get())
                .add(SPItems.STEEL_PAXEL.get())
                .add(SPItems.EMERALD_PAXEL.get())
                .add(SPItems.WITHER_PAXEL.get())
                .add(SPItems.DRAGON_PAXEL.get())
                .add(SPItems.STONE_PAXEL.get())
                .add(SPItems.IRON_PAXEL.get())
                .add(SPItems.DIAMOND_PAXEL.get());

        tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .add(SPItems.STEEL_PICKAXE.get())
                .add(SPItems.STEEL_AXE.get())
                .add(SPItems.STEEL_SHOVEL.get())
                .add(SPItems.EMERALD_PICKAXE.get())
                .add(SPItems.EMERALD_AXE.get())
                .add(SPItems.EMERALD_SHOVEL.get())
                .add(SPItems.WITHER_PICKAXE.get())
                .add(SPItems.WITHER_AXE.get())
                .add(SPItems.WITHER_SHOVEL.get())
                .add(SPItems.DRAGON_PICKAXE.get())
                .add(SPItems.DRAGON_AXE.get())
                .add(SPItems.DRAGON_SHOVEL.get())
                .add(SPItems.STEEL_PAXEL.get())
                .add(SPItems.EMERALD_PAXEL.get())
                .add(SPItems.WITHER_PAXEL.get())
                .add(SPItems.DRAGON_PAXEL.get())
                .add(SPItems.STONE_PAXEL.get())
                .add(SPItems.IRON_PAXEL.get())
                .add(SPItems.DIAMOND_PAXEL.get());

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(SPItems.STEEL_SWORD.get())
                .add(SPItems.STEEL_AXE.get())
                .add(SPItems.EMERALD_SWORD.get())
                .add(SPItems.EMERALD_AXE.get())
                .add(SPItems.WITHER_SWORD.get())
                .add(SPItems.WITHER_AXE.get())
                .add(SPItems.DRAGON_SWORD.get())
                .add(SPItems.DRAGON_AXE.get());

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(SPItems.STEEL_SWORD.get())
                .add(SPItems.EMERALD_SWORD.get())
                .add(SPItems.WITHER_SWORD.get())
                .add(SPItems.DRAGON_SWORD.get());

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(SPItems.STEEL_SWORD.get())
                .add(SPItems.STEEL_PICKAXE.get())
                .add(SPItems.STEEL_AXE.get())
                .add(SPItems.STEEL_SHOVEL.get())
                .add(SPItems.STEEL_HOE.get())
                .add(SPItems.STEEL_PAXEL.get())
                .add(SPItems.EMERALD_SWORD.get())
                .add(SPItems.EMERALD_PICKAXE.get())
                .add(SPItems.EMERALD_AXE.get())
                .add(SPItems.EMERALD_SHOVEL.get())
                .add(SPItems.EMERALD_HOE.get())
                .add(SPItems.EMERALD_PAXEL.get())
                .add(SPItems.WITHER_SWORD.get())
                .add(SPItems.WITHER_PICKAXE.get())
                .add(SPItems.WITHER_AXE.get())
                .add(SPItems.WITHER_SHOVEL.get())
                .add(SPItems.WITHER_HOE.get())
                .add(SPItems.WITHER_PAXEL.get())
                .add(SPItems.DRAGON_SWORD.get())
                .add(SPItems.DRAGON_PICKAXE.get())
                .add(SPItems.DRAGON_AXE.get())
                .add(SPItems.DRAGON_SHOVEL.get())
                .add(SPItems.DRAGON_HOE.get())
                .add(SPItems.DRAGON_PAXEL.get())
                .add(SPItems.STONE_PAXEL.get())
                .add(SPItems.IRON_PAXEL.get())
                .add(SPItems.DIAMOND_PAXEL.get());

        tag(ItemTags.VANISHING_ENCHANTABLE)
                .add(SPItems.STEEL_SWORD.get())
                .add(SPItems.STEEL_PICKAXE.get())
                .add(SPItems.STEEL_AXE.get())
                .add(SPItems.STEEL_SHOVEL.get())
                .add(SPItems.STEEL_HOE.get())
                .add(SPItems.STEEL_PAXEL.get())
                .add(SPItems.EMERALD_SWORD.get())
                .add(SPItems.EMERALD_PICKAXE.get())
                .add(SPItems.EMERALD_AXE.get())
                .add(SPItems.EMERALD_SHOVEL.get())
                .add(SPItems.EMERALD_HOE.get())
                .add(SPItems.EMERALD_PAXEL.get())
                .add(SPItems.WITHER_SWORD.get())
                .add(SPItems.WITHER_PICKAXE.get())
                .add(SPItems.WITHER_AXE.get())
                .add(SPItems.WITHER_SHOVEL.get())
                .add(SPItems.WITHER_HOE.get())
                .add(SPItems.WITHER_PAXEL.get())
                .add(SPItems.DRAGON_SWORD.get())
                .add(SPItems.DRAGON_PICKAXE.get())
                .add(SPItems.DRAGON_AXE.get())
                .add(SPItems.DRAGON_SHOVEL.get())
                .add(SPItems.DRAGON_HOE.get())
                .add(SPItems.DRAGON_PAXEL.get())
                .add(SPItems.STONE_PAXEL.get())
                .add(SPItems.IRON_PAXEL.get())
                .add(SPItems.DIAMOND_PAXEL.get());

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(SPItems.STEEL_HELMET.get())
                .add(SPItems.STEEL_CHESTPLATE.get())
                .add(SPItems.STEEL_LEGGINGS.get())
                .add(SPItems.STEEL_BOOTS.get())
                .add(SPItems.EMERALD_HELMET.get())
                .add(SPItems.EMERALD_CHESTPLATE.get())
                .add(SPItems.EMERALD_LEGGINGS.get())
                .add(SPItems.EMERALD_BOOTS.get())
                .add(SPItems.WITHER_HELMET.get())
                .add(SPItems.WITHER_CHESTPLATE.get())
                .add(SPItems.WITHER_LEGGINGS.get())
                .add(SPItems.WITHER_BOOTS.get())
                .add(SPItems.DRAGON_HELMET.get())
                .add(SPItems.DRAGON_CHESTPLATE.get())
                .add(SPItems.DRAGON_LEGGINGS.get())
                .add(SPItems.DRAGON_BOOTS.get());

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(SPItems.STEEL_HELMET.get())
                .add(SPItems.EMERALD_HELMET.get())
                .add(SPItems.WITHER_HELMET.get())
                .add(SPItems.DRAGON_HELMET.get());

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(SPItems.STEEL_CHESTPLATE.get())
                .add(SPItems.EMERALD_CHESTPLATE.get())
                .add(SPItems.WITHER_CHESTPLATE.get())
                .add(SPItems.DRAGON_CHESTPLATE.get());

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(SPItems.STEEL_LEGGINGS.get())
                .add(SPItems.EMERALD_LEGGINGS.get())
                .add(SPItems.WITHER_LEGGINGS.get())
                .add(SPItems.DRAGON_LEGGINGS.get());

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(SPItems.STEEL_BOOTS.get())
                .add(SPItems.EMERALD_BOOTS.get())
                .add(SPItems.WITHER_BOOTS.get())
                .add(SPItems.DRAGON_BOOTS.get());
    }
}
