package com.misterd.smallprogressions.gui;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.gui.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SPMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, SmallProgressions.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<BrickFurnaceMenu>> BRICK_FURNACE_MENU = registerMenuType("brick_furnace_menu", BrickFurnaceMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<CopperBarrelMenu>> COPPER_BARREL_MENU = registerMenuType("copper_barrel_menu", CopperBarrelMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<IronBarrelMenu>> IRON_BARREL_MENU = registerMenuType("iron_barrel_menu", IronBarrelMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<GoldBarrelMenu>> GOLD_BARREL_MENU = registerMenuType("gold_barrel_menu", GoldBarrelMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<DiamondBarrelMenu>> DIAMOND_BARREL_MENU = registerMenuType("diamond_barrel_menu", DiamondBarrelMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<AdvancedItemCollectorMenu>> ADVANCED_ITEM_COLLECTOR_MENU = registerMenuType("advanced_item_collector_menu", AdvancedItemCollectorMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<LinenSackMenu>> LINEN_SACK_MENU = registerMenuType("linen_sack_menu", LinenSackMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
