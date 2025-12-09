package com.misterd.smallprogressions.datagen.custom;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.SPItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class SPItemModelProvider extends ItemModelProvider {
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> TRIM_MATERIALS = new LinkedHashMap<>();

    static {
        TRIM_MATERIALS.put(TrimMaterials.QUARTZ, 0.1F);
        TRIM_MATERIALS.put(TrimMaterials.IRON, 0.2F);
        TRIM_MATERIALS.put(TrimMaterials.NETHERITE, 0.3F);
        TRIM_MATERIALS.put(TrimMaterials.REDSTONE, 0.4F);
        TRIM_MATERIALS.put(TrimMaterials.COPPER, 0.5F);
        TRIM_MATERIALS.put(TrimMaterials.GOLD, 0.6F);
        TRIM_MATERIALS.put(TrimMaterials.EMERALD, 0.7F);
        TRIM_MATERIALS.put(TrimMaterials.DIAMOND, 0.8F);
        TRIM_MATERIALS.put(TrimMaterials.LAPIS, 0.9F);
        TRIM_MATERIALS.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public SPItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SmallProgressions.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(SPItems.FLAX_SEEDS.get());
        basicItem(SPItems.COTTON_SEEDS.get());

        basicItem(SPItems.STEEL_INGOT.get());
        basicItem(SPItems.STEEL_NUGGET.get());
        basicItem(SPItems.REINFORCED_OBSIDIAN_INGOT.get());
        basicItem(SPItems.ENDER_DUST.get());

        basicItem(SPItems.WITHER_RIB.get());
        basicItem(SPItems.DRAGON_SCALE.get());

        basicItem(SPItems.BLACK_GLOWSTONE_DUST.get());
        basicItem(SPItems.BLUE_GLOWSTONE_DUST.get());
        basicItem(SPItems.BROWN_GLOWSTONE_DUST.get());
        basicItem(SPItems.CYAN_GLOWSTONE_DUST.get());
        basicItem(SPItems.GRAY_GLOWSTONE_DUST.get());
        basicItem(SPItems.GREEN_GLOWSTONE_DUST.get());
        basicItem(SPItems.LIGHT_BLUE_GLOWSTONE_DUST.get());
        basicItem(SPItems.LIGHT_GRAY_GLOWSTONE_DUST.get());
        basicItem(SPItems.LIME_GLOWSTONE_DUST.get());
        basicItem(SPItems.MAGENTA_GLOWSTONE_DUST.get());
        basicItem(SPItems.ORANGE_GLOWSTONE_DUST.get());
        basicItem(SPItems.PINK_GLOWSTONE_DUST.get());
        basicItem(SPItems.PURPLE_GLOWSTONE_DUST.get());
        basicItem(SPItems.RED_GLOWSTONE_DUST.get());
        basicItem(SPItems.WHITE_GLOWSTONE_DUST.get());
        basicItem(SPItems.YELLOW_GLOWSTONE_DUST.get());

        basicItem(SPItems.STRAW.get());
        basicItem(SPItems.JUICER.get());
        basicItem(SPItems.HAMMER.get());
        basicItem(SPItems.SICKLE.get());
        basicItem(SPItems.REPAIR_TOTEM.get());
        basicItem(SPItems.COTTON_BOLLS.get());
        basicItem(SPItems.FLAX.get());

        basicItem(SPItems.IRON_BARREL_UPGRADE.get());
        basicItem(SPItems.GOLD_BARREL_UPGRADE.get());
        basicItem(SPItems.DIAMOND_BARREL_UPGRADE.get());

        basicItem(SPItems.IRON_TANK_UPGRADE.get());
        basicItem(SPItems.GOLD_TANK_UPGRADE.get());
        basicItem(SPItems.DIAMOND_TANK_UPGRADE.get());

        basicItem(SPItems.BLACKBERRIES.get());
        basicItem(SPItems.BLUEBERRIES.get());
        basicItem(SPItems.MALOBERRIES.get());
        basicItem(SPItems.RASPBERRIES.get());
        basicItem(SPItems.APPLE_JUICE.get());
        basicItem(SPItems.BLACKBERRY_JUICE.get());
        basicItem(SPItems.BLUEBERRY_JUICE.get());
        basicItem(SPItems.MALOBERRY_JUICE.get());
        basicItem(SPItems.RASPBERRY_JUICE.get());
        basicItem(SPItems.SLICED_BREAD.get());
        basicItem(SPItems.TOAST.get());
        basicItem(SPItems.PIZZA_SLICE.get());
        basicItem(SPItems.FRIED_EGG.get());
        basicItem(SPItems.RAW_BACON.get());
        basicItem(SPItems.COOKED_BACON.get());
        basicItem(SPItems.BACON_EGG_SANDWICH.get());
        basicItem(SPItems.COOKED_APPLE.get());
        basicItem(SPItems.IRON_APPLE.get());
        basicItem(SPItems.REDSTONE_APPLE.get());
        basicItem(SPItems.EMERALD_APPLE.get());
        basicItem(SPItems.DIAMOND_APPLE.get());

        basicItem(SPItems.WATERING_CAN_TIER_1.get());
        basicItem(SPItems.WATERING_CAN_TIER_2.get());
        basicItem(SPItems.WATERING_CAN_TIER_3.get());

        buttonItem(SPBlocks.MARBLE_BUTTON, SPBlocks.MARBLE);
        buttonItem(SPBlocks.SLATE_BUTTON, SPBlocks.SLATE);
        buttonItem(SPBlocks.ASPHALT_BUTTON, SPBlocks.ASPHALT);
        buttonItem(SPBlocks.HARDENED_STONE_BUTTON, SPBlocks.HARDENED_STONE);

        wallItem(SPBlocks.MARBLE_WALL, SPBlocks.MARBLE);
        wallItem(SPBlocks.MARBLE_BRICK_WALL, SPBlocks.MARBLE_BRICKS);
        wallItem(SPBlocks.SLATE_WALL, SPBlocks.SLATE);
        wallItem(SPBlocks.SLATE_BRICK_WALL, SPBlocks.SLATE_BRICKS);
        wallItem(SPBlocks.ASPHALT_WALL, SPBlocks.ASPHALT);
        wallItem(SPBlocks.HARDENED_STONE_WALL, SPBlocks.HARDENED_STONE);
        wallItem(SPBlocks.HARDENED_STONE_BRICK_WALL, SPBlocks.HARDENED_STONE_BRICKS);
        wallItem(SPBlocks.SOUL_SANDSTONE_WALL, SPBlocks.SOUL_SANDSTONE);
        wallItem(SPBlocks.SOUL_SANDSTONE_BRICK_WALL, SPBlocks.SOUL_SANDSTONE_BRICKS);

        handheldItem(SPItems.STEEL_SWORD);
        handheldItem(SPItems.STEEL_PICKAXE);
        handheldItem(SPItems.STEEL_AXE);
        handheldItem(SPItems.STEEL_SHOVEL);
        handheldItem(SPItems.STEEL_HOE);
        handheldItem(SPItems.STEEL_PAXEL);
        handheldItem(SPItems.STEEL_SCYTHE);

        trimmedArmorItem(SPItems.STEEL_HELMET);
        trimmedArmorItem(SPItems.STEEL_CHESTPLATE);
        trimmedArmorItem(SPItems.STEEL_LEGGINGS);
        trimmedArmorItem(SPItems.STEEL_BOOTS);

        handheldItem(SPItems.EMERALD_SWORD);
        handheldItem(SPItems.EMERALD_PICKAXE);
        handheldItem(SPItems.EMERALD_AXE);
        handheldItem(SPItems.EMERALD_SHOVEL);
        handheldItem(SPItems.EMERALD_HOE);
        handheldItem(SPItems.EMERALD_PAXEL);
        handheldItem(SPItems.EMERALD_SCYTHE);

        trimmedArmorItem(SPItems.EMERALD_HELMET);
        trimmedArmorItem(SPItems.EMERALD_CHESTPLATE);
        trimmedArmorItem(SPItems.EMERALD_LEGGINGS);
        trimmedArmorItem(SPItems.EMERALD_BOOTS);

        handheldItem(SPItems.WITHER_SWORD);
        handheldItem(SPItems.WITHER_PICKAXE);
        handheldItem(SPItems.WITHER_AXE);
        handheldItem(SPItems.WITHER_SHOVEL);
        handheldItem(SPItems.WITHER_HOE);
        handheldItem(SPItems.WITHER_PAXEL);
        handheldItem(SPItems.WITHER_SCYTHE);

        trimmedArmorItem(SPItems.WITHER_HELMET);
        trimmedArmorItem(SPItems.WITHER_CHESTPLATE);
        trimmedArmorItem(SPItems.WITHER_LEGGINGS);
        trimmedArmorItem(SPItems.WITHER_BOOTS);

        handheldItem(SPItems.DRAGON_SWORD);
        handheldItem(SPItems.DRAGON_PICKAXE);
        handheldItem(SPItems.DRAGON_AXE);
        handheldItem(SPItems.DRAGON_SHOVEL);
        handheldItem(SPItems.DRAGON_HOE);
        handheldItem(SPItems.DRAGON_PAXEL);
        handheldItem(SPItems.DRAGON_SCYTHE);

        trimmedArmorItem(SPItems.DRAGON_HELMET);
        trimmedArmorItem(SPItems.DRAGON_CHESTPLATE);
        trimmedArmorItem(SPItems.DRAGON_LEGGINGS);
        trimmedArmorItem(SPItems.DRAGON_BOOTS);

        handheldItem(SPItems.STONE_PAXEL);
        handheldItem(SPItems.IRON_PAXEL);
        handheldItem(SPItems.DIAMOND_PAXEL);
        handheldItem(SPItems.DIAMOND_SCYTHE);
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(), ResourceLocation.parse("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "item/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        ArmorItem armorItem = itemDeferredItem.get();

        TRIM_MATERIALS.forEach((trimMaterial, trimValue) -> {
            String armorType = switch (armorItem.getEquipmentSlot()) {
                case HEAD -> "helmet";
                case CHEST -> "chestplate";
                case LEGS -> "leggings";
                case FEET -> "boots";
                default -> "";
            };

            if (armorType.isEmpty()) {
                return;
            }

            String armorItemPath = itemDeferredItem.getId().getPath();
            String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
            String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";

            ResourceLocation armorItemResLoc = ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, armorItemPath);
            ResourceLocation trimResLoc = ResourceLocation.parse(trimPath);
            ResourceLocation trimNameResLoc = ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, currentTrimName);

            existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

            getBuilder(currentTrimName)
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", SmallProgressions.MODID + ":item/" + armorItemPath)
                    .texture("layer1", trimResLoc);

            withExistingParent(armorItemPath, mcLoc("item/generated"))
                    .override()
                    .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath()))
                    .predicate(mcLoc("trim_type"), trimValue)
                    .end()
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID, "item/" + armorItemPath));
        });
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(SmallProgressions.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
