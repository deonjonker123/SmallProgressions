package com.misterd.smallprogressions.item;


import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.custom.*;
import com.misterd.smallprogressions.item.equipment.*;
import com.misterd.smallprogressions.util.SPFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class SPItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("smallprogressions");

    /// Crop Seeds ///
    public static final DeferredItem<Item> FLAX_SEEDS = ITEMS.register("flax_seeds",
            () -> new ItemNameBlockItem(SPBlocks.FLAX_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> COTTON_SEEDS = ITEMS.register("cotton_seeds",
            () -> new ItemNameBlockItem(SPBlocks.COTTON_CROP.get(), new Item.Properties()));


    /// Steel Tools ///
    public static final DeferredItem<SwordItem> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(SPToolTiers.STEEL,
                    new Item.Properties().attributes(SwordItem.createAttributes(SPToolTiers.STEEL, 3, -2.4F))));

    public static final DeferredItem<AxeItem> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(SPToolTiers.STEEL,
                    new Item.Properties().attributes(AxeItem.createAttributes(SPToolTiers.STEEL, 6.0F, -3.0F))));

    public static final DeferredItem<PickaxeItem> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(SPToolTiers.STEEL,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(SPToolTiers.STEEL, 1.0F, -2.8F))));

    public static final DeferredItem<HoeItem> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(SPToolTiers.STEEL,
                    new Item.Properties().attributes(HoeItem.createAttributes(SPToolTiers.STEEL, -2.0F, 0.0F))));

    public static final DeferredItem<ShovelItem> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(SPToolTiers.STEEL,
                    new Item.Properties().attributes(ShovelItem.createAttributes(SPToolTiers.STEEL, 1.5F, -3.0F))));

    public static final DeferredItem<PaxelItem> STEEL_PAXEL = ITEMS.register("steel_paxel",
            () -> new PaxelItem(SPToolTiers.STEEL, new Item.Properties()));

    public static final DeferredItem<Item> STEEL_SCYTHE = ITEMS.register("steel_scythe",
            () -> new ScytheItem(SPToolTiers.STEEL, 3, new Item.Properties()));

    /// Emerald Tools ///
    public static final DeferredItem<SwordItem> EMERALD_SWORD = ITEMS.register("emerald_sword",
            () -> new SwordItem(SPToolTiers.EMERALD,
                    new Item.Properties().attributes(SwordItem.createAttributes(SPToolTiers.EMERALD, 3, -2.4F))));

    public static final DeferredItem<AxeItem> EMERALD_AXE = ITEMS.register("emerald_axe",
            () -> new AxeItem(SPToolTiers.EMERALD,
                    new Item.Properties().attributes(AxeItem.createAttributes(SPToolTiers.EMERALD, 6.0F, -3.0F))));

    public static final DeferredItem<PickaxeItem> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe",
            () -> new PickaxeItem(SPToolTiers.EMERALD,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(SPToolTiers.EMERALD, 1.0F, -2.8F))));

    public static final DeferredItem<HoeItem> EMERALD_HOE = ITEMS.register("emerald_hoe",
            () -> new HoeItem(SPToolTiers.EMERALD,
                    new Item.Properties().attributes(HoeItem.createAttributes(SPToolTiers.EMERALD, -2.0F, 0.0F))));

    public static final DeferredItem<ShovelItem> EMERALD_SHOVEL = ITEMS.register("emerald_shovel",
            () -> new ShovelItem(SPToolTiers.EMERALD,
                    new Item.Properties().attributes(ShovelItem.createAttributes(SPToolTiers.EMERALD, 1.5F, -3.0F))));

    public static final DeferredItem<PaxelItem> EMERALD_PAXEL = ITEMS.register("emerald_paxel",
            () -> new PaxelItem(SPToolTiers.EMERALD, new Item.Properties()));

    public static final DeferredItem<Item> EMERALD_SCYTHE = ITEMS.register("emerald_scythe",
            () -> new ScytheItem(SPToolTiers.EMERALD, 7, new Item.Properties()));

    /// Wither Tool ///
    public static final DeferredItem<SwordItem> WITHER_SWORD = ITEMS.register("wither_sword",
            () -> new SwordItem(SPToolTiers.WITHER,
                    new Item.Properties().attributes(SwordItem.createAttributes(SPToolTiers.WITHER, 3, -2.4F))));

    public static final DeferredItem<AxeItem> WITHER_AXE = ITEMS.register("wither_axe",
            () -> new AxeItem(SPToolTiers.WITHER,
                    new Item.Properties().attributes(AxeItem.createAttributes(SPToolTiers.WITHER, 6.0F, -3.0F))));

    public static final DeferredItem<PickaxeItem> WITHER_PICKAXE = ITEMS.register("wither_pickaxe",
            () -> new PickaxeItem(SPToolTiers.WITHER,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(SPToolTiers.WITHER, 1.0F, -2.8F))));

    public static final DeferredItem<HoeItem> WITHER_HOE = ITEMS.register("wither_hoe",
            () -> new HoeItem(SPToolTiers.WITHER,
                    new Item.Properties().attributes(HoeItem.createAttributes(SPToolTiers.WITHER, -2.0F, 0.0F))));

    public static final DeferredItem<ShovelItem> WITHER_SHOVEL = ITEMS.register("wither_shovel",
            () -> new ShovelItem(SPToolTiers.WITHER,
                    new Item.Properties().attributes(ShovelItem.createAttributes(SPToolTiers.WITHER, 1.5F, -3.0F))));

    public static final DeferredItem<PaxelItem> WITHER_PAXEL = ITEMS.register("wither_paxel",
            () -> new PaxelItem(SPToolTiers.WITHER, new Item.Properties()));

    public static final DeferredItem<Item> WITHER_SCYTHE = ITEMS.register("wither_scythe",
            () -> new ScytheItem(SPToolTiers.WITHER, 9, new Item.Properties()));

    /// Dragon Tool ///
    public static final DeferredItem<SwordItem> DRAGON_SWORD = ITEMS.register("dragon_sword",
            () -> new SwordItem(SPToolTiers.DRAGON,
                    new Item.Properties().attributes(SwordItem.createAttributes(SPToolTiers.DRAGON, 3, -2.4F))));

    public static final DeferredItem<AxeItem> DRAGON_AXE = ITEMS.register("dragon_axe",
            () -> new AxeItem(SPToolTiers.DRAGON,
                    new Item.Properties().attributes(AxeItem.createAttributes(SPToolTiers.DRAGON, 6.0F, -3.0F))));

    public static final DeferredItem<PickaxeItem> DRAGON_PICKAXE = ITEMS.register("dragon_pickaxe",
            () -> new PickaxeItem(SPToolTiers.DRAGON,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(SPToolTiers.DRAGON, 1.0F, -2.8F))));

    public static final DeferredItem<HoeItem> DRAGON_HOE = ITEMS.register("dragon_hoe",
            () -> new HoeItem(SPToolTiers.DRAGON,
                    new Item.Properties().attributes(HoeItem.createAttributes(SPToolTiers.DRAGON, -2.0F, 0.0F))));

    public static final DeferredItem<ShovelItem> DRAGON_SHOVEL = ITEMS.register("dragon_shovel",
            () -> new ShovelItem(SPToolTiers.DRAGON,
                    new Item.Properties().attributes(ShovelItem.createAttributes(SPToolTiers.DRAGON, 1.5F, -3.0F))));

    public static final DeferredItem<PaxelItem> DRAGON_PAXEL = ITEMS.register("dragon_paxel",
            () -> new PaxelItem(SPToolTiers.DRAGON, new Item.Properties()));

    public static final DeferredItem<Item> DRAGON_SCYTHE = ITEMS.register("dragon_scythe",
            () -> new ScytheItem(SPToolTiers.DRAGON, 11, new Item.Properties()));

    /// Vanilla Variant Paxels ///
    public static final DeferredItem<PaxelItem> STONE_PAXEL = ITEMS.register("stone_paxel",
            () -> new PaxelItem(Tiers.STONE, new Item.Properties()));

    public static final DeferredItem<PaxelItem> IRON_PAXEL = ITEMS.register("iron_paxel",
            () -> new PaxelItem(Tiers.IRON, new Item.Properties()));

    public static final DeferredItem<PaxelItem> DIAMOND_PAXEL = ITEMS.register("diamond_paxel",
            () -> new PaxelItem(Tiers.DIAMOND, new Item.Properties()));

    public static final DeferredItem<Item> DIAMOND_SCYTHE = ITEMS.register("diamond_scythe",
            () -> new ScytheItem(Tiers.DIAMOND, 5, new Item.Properties()));

    ///  Steel Armor ///
    public static final DeferredItem<ArmorItem> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new SPArmorItem(SPArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(27))));

    public static final DeferredItem<ArmorItem> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(SPArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(27))));

    public static final DeferredItem<ArmorItem> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(SPArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(27))));

    public static final DeferredItem<ArmorItem> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(SPArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(27))));

    ///  Emerald Armor ///
    public static final DeferredItem<ArmorItem> EMERALD_HELMET = ITEMS.register("emerald_helmet",
            () -> new SPArmorItem(SPArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald.subtitle.line2").withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<ArmorItem> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate",
            () -> new ArmorItem(SPArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald.subtitle.line2").withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<ArmorItem> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings",
            () -> new ArmorItem(SPArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald.subtitle.line2").withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<ArmorItem> EMERALD_BOOTS = ITEMS.register("emerald_boots",
            () -> new ArmorItem(SPArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald.subtitle.line2").withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    ///  Wither Armor ///
    public static final DeferredItem<ArmorItem> WITHER_HELMET = ITEMS.register("wither_helmet",
            () -> new SPArmorItem(SPArmorMaterials.WITHER_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line2").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line3").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line4").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<ArmorItem> WITHER_CHESTPLATE = ITEMS.register("wither_chestplate",
            () -> new ArmorItem(SPArmorMaterials.WITHER_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line2").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line3").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line4").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<ArmorItem> WITHER_LEGGINGS = ITEMS.register("wither_leggings",
            () -> new ArmorItem(SPArmorMaterials.WITHER_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line2").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line3").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line4").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<ArmorItem> WITHER_BOOTS = ITEMS.register("wither_boots",
            () -> new ArmorItem(SPArmorMaterials.WITHER_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line2").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line3").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither.subtitle.line4").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    ///  Dragon Armor ///
    public static final DeferredItem<ArmorItem> DRAGON_HELMET = ITEMS.register("dragon_helmet",
            () -> new SPArmorItem(SPArmorMaterials.DRAGON_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line2").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line3").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line4").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line5").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<ArmorItem> DRAGON_CHESTPLATE = ITEMS.register("dragon_chestplate",
            () -> new ArmorItem(SPArmorMaterials.DRAGON_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line2").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line3").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line4").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line5").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<ArmorItem> DRAGON_LEGGINGS = ITEMS.register("dragon_leggings",
            () -> new ArmorItem(SPArmorMaterials.DRAGON_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line2").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line3").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line4").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line5").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<ArmorItem> DRAGON_BOOTS = ITEMS.register("dragon_boots",
            () -> new ArmorItem(SPArmorMaterials.DRAGON_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(27)))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line2").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line3").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line4").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon.subtitle.line5").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    /// Ingots ///
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> REINFORCED_OBSIDIAN_INGOT = ITEMS.register("reinforced_obsidian_ingot",
            () -> new Item(new Item.Properties()));

    /// Boss Drops ///
    public static final DeferredItem<Item> WITHER_RIB = ITEMS.register("wither_rib",
            () -> new Item(new Item.Properties())
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.wither_rib.subtitle").withStyle(ChatFormatting.AQUA));
                }
            });

    public static final DeferredItem<Item> DRAGON_SCALE = ITEMS.register("dragon_scale",
            () -> new Item(new Item.Properties())
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.dragon_scale.subtitle").withStyle(ChatFormatting.AQUA));
                }
            });

    /// Glowstone Dusts ///
    public static final DeferredItem<Item> BLACK_GLOWSTONE_DUST = ITEMS.register("black_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BLUE_GLOWSTONE_DUST = ITEMS.register("blue_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BROWN_GLOWSTONE_DUST = ITEMS.register("brown_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CYAN_GLOWSTONE_DUST = ITEMS.register("cyan_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GRAY_GLOWSTONE_DUST = ITEMS.register("gray_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GREEN_GLOWSTONE_DUST = ITEMS.register("green_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LIGHT_BLUE_GLOWSTONE_DUST = ITEMS.register("light_blue_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LIGHT_GRAY_GLOWSTONE_DUST = ITEMS.register("light_gray_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LIME_GLOWSTONE_DUST = ITEMS.register("lime_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MAGENTA_GLOWSTONE_DUST = ITEMS.register("magenta_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ORANGE_GLOWSTONE_DUST = ITEMS.register("orange_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PINK_GLOWSTONE_DUST = ITEMS.register("pink_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PURPLE_GLOWSTONE_DUST = ITEMS.register("purple_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RED_GLOWSTONE_DUST = ITEMS.register("red_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> WHITE_GLOWSTONE_DUST = ITEMS.register("white_glowstone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> YELLOW_GLOWSTONE_DUST = ITEMS.register("yellow_glowstone_dust",
            () -> new Item(new Item.Properties()));

    /// Ender Dust ///
    public static final DeferredItem<Item> ENDER_DUST = ITEMS.register("ender_dust",
            () -> new Item(new Item.Properties()));

    ///  Watering Cans ///
    public static final DeferredItem<Item> WATERING_CAN_TIER_1 = ITEMS.register("watering_can_tier_1",
            () -> new WateringCanItem(new Item.Properties(), 3));

    public static final DeferredItem<Item> WATERING_CAN_TIER_2 = ITEMS.register("watering_can_tier_2",
            () -> new WateringCanItem(new Item.Properties(), 5));

    public static final DeferredItem<Item> WATERING_CAN_TIER_3 = ITEMS.register("watering_can_tier_3",
            () -> new WateringCanItem(new Item.Properties(), 9));

    /// Big Items ///
    public static final DeferredItem<Item> BIG_BUCKET = ITEMS.register("big_bucket",
            () -> new BigBucketItem(new Item.Properties()));

    public static final DeferredItem<Item> BIG_POUCH = ITEMS.register("big_pouch",
            () -> new BigPouchItem(new Item.Properties()));

    /// Crafting Materials ///
    public static final DeferredItem<Item> STRAW = ITEMS.register("straw",
            () -> new Item(new Item.Properties())
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.straw.subtitle").withStyle(ChatFormatting.AQUA));
                }
            });

    public static final DeferredItem<Item> JUICER = ITEMS.register("juicer",
            () -> new JuicerItem(new Item.Properties())
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.juicer.subtitle").withStyle(ChatFormatting.AQUA));
                }
            });

    public static final DeferredItem<Item> HAMMER = ITEMS.register("hammer",
            () -> new HammerItem(new Item.Properties())
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.hammer.subtitle").withStyle(ChatFormatting.AQUA));
                }
            });

    public static final DeferredItem<Item> SICKLE = ITEMS.register("sickle",
            () -> new SickleItem(new Item.Properties().durability(512))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.sickle.subtitle").withStyle(ChatFormatting.AQUA));
                }
            });

    public static final DeferredItem<Item> REPAIR_TOTEM = ITEMS.register("repair_totem",
            () -> new RepairTotemItem(new Item.Properties()));

    public static final DeferredItem<Item> COTTON_BOLLS = ITEMS.register("cotton_bolls",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FLAX = ITEMS.register("flax",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MARBLE_PEBBLE = ITEMS.register("marble_pebble",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SLATE_PEBBLE = ITEMS.register("slate_pebble",
            () -> new Item(new Item.Properties()));

    /// Storage Barrel Upgrades ///
    public static final DeferredItem<Item> IRON_BARREL_UPGRADE = ITEMS.register("iron_barrel_upgrade",
            () -> new BarrelUpgradeItem(BarrelUpgradeItem.BarrelTier.IRON, new Item.Properties()));

    public static final DeferredItem<Item> GOLD_BARREL_UPGRADE = ITEMS.register("gold_barrel_upgrade",
            () -> new BarrelUpgradeItem(BarrelUpgradeItem.BarrelTier.GOLD, new Item.Properties()));

    public static final DeferredItem<Item> DIAMOND_BARREL_UPGRADE = ITEMS.register("diamond_barrel_upgrade",
            () -> new BarrelUpgradeItem(BarrelUpgradeItem.BarrelTier.DIAMOND, new Item.Properties()));

    /// Tank Upgrades ///
    public static final DeferredItem<Item> IRON_TANK_UPGRADE = ITEMS.register("iron_tank_upgrade",
            () -> new TankUpgradeItem(TankUpgradeItem.TankTier.IRON, new Item.Properties()));

    public static final DeferredItem<Item> GOLD_TANK_UPGRADE = ITEMS.register("gold_tank_upgrade",
            () -> new TankUpgradeItem(TankUpgradeItem.TankTier.GOLD, new Item.Properties()));

    public static final DeferredItem<Item> DIAMOND_TANK_UPGRADE = ITEMS.register("diamond_tank_upgrade",
            () -> new TankUpgradeItem(TankUpgradeItem.TankTier.DIAMOND, new Item.Properties()));

    /// Foods ///
    public static final DeferredItem<Item> BLACKBERRIES = ITEMS.register("blackberries",
            () -> new ItemNameBlockItem(SPBlocks.BLACKBERRY_BUSH.get(), new Item.Properties().food(SPFoodProperties.BERRIES)));

    public static final DeferredItem<Item> BLUEBERRIES = ITEMS.register("blueberries",
            () -> new ItemNameBlockItem(SPBlocks.BLUEBERRY_BUSH.get(), new Item.Properties().food(SPFoodProperties.BERRIES)));

    public static final DeferredItem<Item> MALOBERRIES = ITEMS.register("maloberries",
            () -> new ItemNameBlockItem(SPBlocks.MALOBERRY_BUSH.get(), new Item.Properties().food(SPFoodProperties.BERRIES)));

    public static final DeferredItem<Item> RASPBERRIES = ITEMS.register("raspberries",
            () -> new ItemNameBlockItem(SPBlocks.RASPBERRY_BUSH.get(), new Item.Properties().food(SPFoodProperties.BERRIES)));

    public static final DeferredItem<Item> APPLE_JUICE = ITEMS.register("apple_juice",
            () -> new Item(new Item.Properties().food(SPFoodProperties.JUICES)));

    public static final DeferredItem<Item> BLACKBERRY_JUICE = ITEMS.register("blackberry_juice",
            () -> new Item(new Item.Properties().food(SPFoodProperties.JUICES)));

    public static final DeferredItem<Item> BLUEBERRY_JUICE = ITEMS.register("blueberry_juice",
            () -> new Item(new Item.Properties().food(SPFoodProperties.JUICES)));

    public static final DeferredItem<Item> MALOBERRY_JUICE = ITEMS.register("maloberry_juice",
            () -> new Item(new Item.Properties().food(SPFoodProperties.JUICES)));

    public static final DeferredItem<Item> RASPBERRY_JUICE = ITEMS.register("raspberry_juice",
            () -> new Item(new Item.Properties().food(SPFoodProperties.JUICES)));

    public static final DeferredItem<Item> SLICED_BREAD = ITEMS.register("sliced_bread",
            () -> new Item(new Item.Properties().food(SPFoodProperties.SLICED_BREAD)));

    public static final DeferredItem<Item> TOAST = ITEMS.register("toast",
            () -> new Item(new Item.Properties().food(SPFoodProperties.TOASTED_BREAD)));

    public static final DeferredItem<Item> PIZZA_SLICE = ITEMS.register("pizza_slice",
            () -> new Item(new Item.Properties().food(SPFoodProperties.PIZZA_SLICE)));

    public static final DeferredItem<Item> FRIED_EGG = ITEMS.register("fried_egg",
            () -> new Item(new Item.Properties().food(SPFoodProperties.FRIED_EGG)));

    public static final DeferredItem<Item> RAW_BACON = ITEMS.register("raw_bacon",
            () -> new Item(new Item.Properties().food(SPFoodProperties.RAW_BACON)));

    public static final DeferredItem<Item> COOKED_BACON = ITEMS.register("cooked_bacon",
            () -> new Item(new Item.Properties().food(SPFoodProperties.COOKED_BACON)));

    public static final DeferredItem<Item> BACON_EGG_SANDWICH = ITEMS.register("bacon_egg_sandwich",
            () -> new Item(new Item.Properties().food(SPFoodProperties.BACON_EGG_SANDWICH)));

    public static final DeferredItem<Item> COOKED_APPLE = ITEMS.register("cooked_apple",
            () -> new Item(new Item.Properties().food(SPFoodProperties.COOKED_APPLE)));

    public static final DeferredItem<Item> IRON_APPLE = ITEMS.register("iron_apple",
            () -> new IronAppleItem(new Item.Properties().food(SPFoodProperties.IRON_APPLE))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.iron_apple.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.iron_apple.subtitle.line2").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.iron_apple.subtitle.line3").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<Item> REDSTONE_APPLE = ITEMS.register("redstone_apple",
            () -> new RedstoneAppleItem(new Item.Properties().food(SPFoodProperties.REDSTONE_APPLE))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.redstone_apple.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.redstone_apple.subtitle.line2").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.redstone_apple.subtitle.line3").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<Item> EMERALD_APPLE = ITEMS.register("emerald_apple",
            () -> new EmeraldAppleItem(new Item.Properties().food(SPFoodProperties.EMERALD_APPLE))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald_apple.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald_apple.subtitle.line2").withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald_apple.subtitle.line3").withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.emerald_apple.subtitle.line4").withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<Item> DIAMOND_APPLE = ITEMS.register("diamond_apple",
            () -> new DiamondAppleItem(new Item.Properties().food(SPFoodProperties.DIAMOND_APPLE))
            {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List< Component > tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.smallprogressions.diamond_apple.subtitle.line1").withStyle(ChatFormatting.GOLD));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.diamond_apple.subtitle.line2").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.diamond_apple.subtitle.line3").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.diamond_apple.subtitle.line4").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                    tooltipComponents.add(Component.translatable("item.smallprogressions.diamond_apple.subtitle.line5").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<Item> TINY_COAL = ITEMS.register("tiny_coal",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TINY_CHARCOAL = ITEMS.register("tiny_charcoal",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MCFLOATY_BLOCK_ITEM = ITEMS.register("mcfloaty_block",
            () -> new McFloatyBlockItem(SPBlocks.MCFLOATY_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
