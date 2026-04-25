package com.misterd.smallprogressions.item;


import com.misterd.smallprogressions.block.SPBlocks;
import com.misterd.smallprogressions.item.custom.*;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SPItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("smallprogressions");

    public static final DeferredItem<Item> BIG_BUCKET = ITEMS.register("big_bucket",
            () -> new BigBucketItem(new Item.Properties()));

    public static final DeferredItem<Item> BIG_POUCH = ITEMS.register("big_pouch",
            () -> new BigPouchItem(new Item.Properties()));

    public static final DeferredItem<Item> IRON_BARREL_UPGRADE = ITEMS.register("iron_barrel_upgrade",
            () -> new BarrelUpgradeItem(BarrelUpgradeItem.BarrelTier.IRON, new Item.Properties()));

    public static final DeferredItem<Item> GOLD_BARREL_UPGRADE = ITEMS.register("gold_barrel_upgrade",
            () -> new BarrelUpgradeItem(BarrelUpgradeItem.BarrelTier.GOLD, new Item.Properties()));

    public static final DeferredItem<Item> DIAMOND_BARREL_UPGRADE = ITEMS.register("diamond_barrel_upgrade",
            () -> new BarrelUpgradeItem(BarrelUpgradeItem.BarrelTier.DIAMOND, new Item.Properties()));

    public static final DeferredItem<Item> IRON_TANK_UPGRADE = ITEMS.register("iron_tank_upgrade",
            () -> new TankUpgradeItem(TankUpgradeItem.TankTier.IRON, new Item.Properties()));

    public static final DeferredItem<Item> GOLD_TANK_UPGRADE = ITEMS.register("gold_tank_upgrade",
            () -> new TankUpgradeItem(TankUpgradeItem.TankTier.GOLD, new Item.Properties()));

    public static final DeferredItem<Item> DIAMOND_TANK_UPGRADE = ITEMS.register("diamond_tank_upgrade",
            () -> new TankUpgradeItem(TankUpgradeItem.TankTier.DIAMOND, new Item.Properties()));

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
