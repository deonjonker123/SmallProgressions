package com.misterd.smallprogressions.item;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.block.SPBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SPCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SmallProgressions.MODID);

    public static final Supplier<CreativeModeTab> SMALLPROGRESSIONS = CREATIVE_MODE_TAB.register("smallprogressions_creativetab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SPBlocks.COBBLESTONE_GENERATOR_TIER_3.get()))
                    .title(Component.translatable("creativetab.smallprogressions"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_1);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_2);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_3);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_4);
                        output.accept(SPBlocks.COBBLESTONE_GENERATOR_TIER_5);

                        output.accept(SPBlocks.GROWTH_CRYSTAL_TIER_1);
                        output.accept(SPBlocks.GROWTH_CRYSTAL_TIER_2);
                        output.accept(SPBlocks.GROWTH_CRYSTAL_TIER_3);

                        output.accept(SPBlocks.GREENHOUSE_GLASS);

                        output.accept(SPBlocks.SIMPLE_ITEM_COLLECTOR);
                        output.accept(SPBlocks.ADVANCED_ITEM_COLLECTOR);

                        output.accept(SPBlocks.HARVESTER);

                        output.accept(SPBlocks.COPPER_BARREL);
                        output.accept(SPBlocks.IRON_BARREL);
                        output.accept(SPBlocks.GOLD_BARREL);
                        output.accept(SPBlocks.DIAMOND_BARREL);

                        // Barrel upgrade Items
                        output.accept(SPItems.IRON_BARREL_UPGRADE);
                        output.accept(SPItems.GOLD_BARREL_UPGRADE);
                        output.accept(SPItems.DIAMOND_BARREL_UPGRADE);

                        output.accept(SPBlocks.COPPER_TANK);
                        output.accept(SPBlocks.IRON_TANK);
                        output.accept(SPBlocks.GOLD_TANK);
                        output.accept(SPBlocks.DIAMOND_TANK);

                        // Tank Upgrade Items
                        output.accept(SPItems.IRON_TANK_UPGRADE);
                        output.accept(SPItems.GOLD_TANK_UPGRADE);
                        output.accept(SPItems.DIAMOND_TANK_UPGRADE);

                        output.accept(SPBlocks.LINEN_SACK);

                        output.accept(SPBlocks.BRICK_FURNACE);

                        output.accept(SPBlocks.WATER_RESERVOIR);
                        output.accept(SPBlocks.LAVA_GENERATOR);

                        output.accept(SPBlocks.LAVA_INFUSED_STONE);
                        output.accept(SPBlocks.MCFLOATY_BLOCK);

                        output.accept(SPBlocks.CHARCOAL_BLOCK);
                        output.accept(SPBlocks.STEEL_BLOCK);

                        output.accept(SPBlocks.THATCH_BLOCK);
                        output.accept(SPBlocks.THATCH_STAIRS);
                        output.accept(SPBlocks.THATCH_SLAB);

                        output.accept(SPBlocks.REINFORCED_OBSIDIAN);
                        output.accept(SPBlocks.REINFORCED_GLASS);

                        output.accept(SPBlocks.HARDENED_STONE);
                        output.accept(SPBlocks.HARDENED_STONE_BRICKS);
                        output.accept(SPBlocks.HARDENED_STONE_STAIRS);
                        output.accept(SPBlocks.HARDENED_STONE_BRICK_STAIRS);
                        output.accept(SPBlocks.HARDENED_STONE_SLAB);
                        output.accept(SPBlocks.HARDENED_STONE_BRICK_SLAB);
                        output.accept(SPBlocks.HARDENED_STONE_BUTTON);
                        output.accept(SPBlocks.HARDENED_STONE_PRESSURE_PLATE);
                        output.accept(SPBlocks.HARDENED_STONE_WALL);
                        output.accept(SPBlocks.HARDENED_STONE_BRICK_WALL);

                        output.accept(SPBlocks.STONE_ENDER_ORE);
                        output.accept(SPBlocks.DEEPSLATE_ENDER_ORE);
                        output.accept(SPBlocks.NETHERRACK_ENDER_ORE);
                        output.accept(SPBlocks.ENDSTONE_ENDER_ORE);

                        // Steel Armor & Tools
                        output.accept(SPItems.STEEL_SWORD);
                        output.accept(SPItems.STEEL_PICKAXE);
                        output.accept(SPItems.STEEL_AXE);
                        output.accept(SPItems.STEEL_SHOVEL);
                        output.accept(SPItems.STEEL_HOE);
                        output.accept(SPItems.STEEL_PAXEL);
                        output.accept(SPItems.STEEL_SCYTHE);
                        output.accept(SPItems.STEEL_HELMET);
                        output.accept(SPItems.STEEL_CHESTPLATE);
                        output.accept(SPItems.STEEL_LEGGINGS);
                        output.accept(SPItems.STEEL_BOOTS);

                        // Emerald Armor & Tools
                        output.accept(SPItems.EMERALD_SWORD);
                        output.accept(SPItems.EMERALD_PICKAXE);
                        output.accept(SPItems.EMERALD_AXE);
                        output.accept(SPItems.EMERALD_SHOVEL);
                        output.accept(SPItems.EMERALD_HOE);
                        output.accept(SPItems.EMERALD_PAXEL);
                        output.accept(SPItems.EMERALD_SCYTHE);
                        output.accept(SPItems.EMERALD_HELMET);
                        output.accept(SPItems.EMERALD_CHESTPLATE);
                        output.accept(SPItems.EMERALD_LEGGINGS);
                        output.accept(SPItems.EMERALD_BOOTS);

                        // Wither Armor & Tools
                        output.accept(SPItems.WITHER_SWORD);
                        output.accept(SPItems.WITHER_PICKAXE);
                        output.accept(SPItems.WITHER_AXE);
                        output.accept(SPItems.WITHER_SHOVEL);
                        output.accept(SPItems.WITHER_HOE);
                        output.accept(SPItems.WITHER_PAXEL);
                        output.accept(SPItems.WITHER_SCYTHE);
                        output.accept(SPItems.WITHER_HELMET);
                        output.accept(SPItems.WITHER_CHESTPLATE);
                        output.accept(SPItems.WITHER_LEGGINGS);
                        output.accept(SPItems.WITHER_BOOTS);

                        // Dragon Armor & Tools
                        output.accept(SPItems.DRAGON_SWORD);
                        output.accept(SPItems.DRAGON_PICKAXE);
                        output.accept(SPItems.DRAGON_AXE);
                        output.accept(SPItems.DRAGON_SHOVEL);
                        output.accept(SPItems.DRAGON_HOE);
                        output.accept(SPItems.DRAGON_PAXEL);
                        output.accept(SPItems.DRAGON_SCYTHE);
                        output.accept(SPItems.DRAGON_HELMET);
                        output.accept(SPItems.DRAGON_CHESTPLATE);
                        output.accept(SPItems.DRAGON_LEGGINGS);
                        output.accept(SPItems.DRAGON_BOOTS);

                        // Vanilla Material Paxels
                        output.accept(SPItems.STONE_PAXEL);
                        output.accept(SPItems.IRON_PAXEL);
                        output.accept(SPItems.DIAMOND_PAXEL);
                        output.accept(SPItems.DIAMOND_SCYTHE);

                        // Watering Cans
                        output.accept(SPItems.BIG_POUCH);
                        output.accept(SPItems.WATERING_CAN_TIER_1);
                        output.accept(SPItems.WATERING_CAN_TIER_2);
                        output.accept(SPItems.WATERING_CAN_TIER_3);
                        output.accept(SPItems.BIG_BUCKET);

                        // Boss Drops Items
                        output.accept(SPItems.WITHER_RIB);
                        output.accept(SPItems.DRAGON_SCALE);

                        // Ingots Items
                        output.accept(SPItems.STEEL_NUGGET);
                        output.accept(SPItems.STEEL_INGOT);
                        output.accept(SPItems.REINFORCED_OBSIDIAN_INGOT);
                        output.accept(SPItems.ENDER_DUST);
                        output.accept(SPItems.TINY_COAL);
                        output.accept(SPItems.TINY_CHARCOAL);

                        // Crafting Material Items
                        output.accept(SPItems.STRAW);
                        output.accept(SPItems.HAMMER);
                        output.accept(SPItems.SICKLE);
                        output.accept(SPItems.REPAIR_TOTEM);

                        output.accept(SPItems.SLICED_BREAD);
                        output.accept(SPItems.TOAST);
                        output.accept(SPItems.PIZZA_SLICE);
                        output.accept(SPItems.FRIED_EGG);
                        output.accept(SPItems.RAW_BACON);
                        output.accept(SPItems.COOKED_BACON);
                        output.accept(SPItems.BACON_EGG_SANDWICH);
                        output.accept(SPItems.COOKED_APPLE);
                        output.accept(SPItems.IRON_APPLE);
                        output.accept(SPItems.REDSTONE_APPLE);
                        output.accept(SPItems.EMERALD_APPLE);
                        output.accept(SPItems.DIAMOND_APPLE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
