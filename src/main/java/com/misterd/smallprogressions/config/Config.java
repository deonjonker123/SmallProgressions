package com.misterd.smallprogressions.config;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.slf4j.Logger;

public class Config {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
    private static ModConfigSpec COMMON_CONFIG;

    // Growth Crystal config values
    private static ModConfigSpec.DoubleValue GROWTH_CRYSTAL_TIER_1_RATE;
    private static ModConfigSpec.DoubleValue GROWTH_CRYSTAL_TIER_2_RATE;
    private static ModConfigSpec.DoubleValue GROWTH_CRYSTAL_TIER_3_RATE;

    // Cobblestone Generator config values
    private static ModConfigSpec.IntValue COBBLESTONE_GEN_TIER_1_TICKS;
    private static ModConfigSpec.IntValue COBBLESTONE_GEN_TIER_2_TICKS;
    private static ModConfigSpec.IntValue COBBLESTONE_GEN_TIER_3_TICKS;
    private static ModConfigSpec.IntValue COBBLESTONE_GEN_TIER_4_TICKS;
    private static ModConfigSpec.IntValue COBBLESTONE_GEN_TIER_5_TICKS;

    // Lava Generator config value
    private static ModConfigSpec.IntValue LAVA_GENERATOR_MB_PER_TICK;

    // Water Reservoir config value
    private static ModConfigSpec.BooleanValue WATER_RESERVOIR_INFINITE;

    // Greenhouse Glass config values
    private static ModConfigSpec.DoubleValue GREENHOUSE_GLASS_GROWTH_BOOST;
    private static ModConfigSpec.IntValue GREENHOUSE_GLASS_RANGE;

    // Repair Totem config values
    private static ModConfigSpec.IntValue REPAIR_TOTEM_DURABILITY_PER_TOTEM;
    private static ModConfigSpec.IntValue REPAIR_TOTEM_TICK_INTERVAL;

    // Big bucket config values
    private static ModConfigSpec.IntValue BIG_BUCKET_CAPACITY;

    static {
        buildCommonConfig();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static void register(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
    }

    private static void buildCommonConfig() {
        buildGrowthCrystalConfig();
        buildCobblestoneGeneratorConfig();
        buildLavaGeneratorConfig();
        buildWaterReservoirConfig();
        buildGreenhouseGlassConfig();
        buildRepairTotemConfig();
        buildBigBucketConfig();
    }

    private static void buildGrowthCrystalConfig() {
        COMMON_BUILDER.comment("Growth Crystal - Configure crop growth acceleration rates")
                .push("growth_crystal");

        GROWTH_CRYSTAL_TIER_1_RATE = COMMON_BUILDER
                .comment(
                        "Growth acceleration rate for Tier 1 Growth Crystal (1.0 = 100%)",
                        "Higher values increase growth speed"
                )
                .defineInRange("tier_1_rate", 0.25, 0.0, 2.0);

        GROWTH_CRYSTAL_TIER_2_RATE = COMMON_BUILDER
                .comment(
                        "Growth acceleration rate for Tier 2 Growth Crystal (1.0 = 100%)",
                        "Higher values increase growth speed"
                )
                .defineInRange("tier_2_rate", 0.50, 0.0, 2.0);

        GROWTH_CRYSTAL_TIER_3_RATE = COMMON_BUILDER
                .comment(
                        "Growth acceleration rate for Tier 3 Growth Crystal (1.0 = 100%)",
                        "Higher values increase growth speed"
                )
                .defineInRange("tier_3_rate", 1.00, 0.0, 2.0);

        COMMON_BUILDER.pop();
    }

    private static void buildCobblestoneGeneratorConfig() {
        COMMON_BUILDER.comment("Cobblestone Generator - Configure generation rates for all tiers")
                .push("cobblestone_generator");

        COBBLESTONE_GEN_TIER_1_TICKS = COMMON_BUILDER
                .comment(
                        "Cobblestone generation interval for Tier 1 (ticks)",
                        "Lower values = faster generation"
                )
                .defineInRange("tier_1_ticks", 40, 1, 200);

        COBBLESTONE_GEN_TIER_2_TICKS = COMMON_BUILDER
                .comment(
                        "Cobblestone generation interval for Tier 2 (ticks)",
                        "Lower values = faster generation"
                )
                .defineInRange("tier_2_ticks", 20, 1, 200);

        COBBLESTONE_GEN_TIER_3_TICKS = COMMON_BUILDER
                .comment(
                        "Cobblestone generation interval for Tier 3 (ticks)",
                        "Lower values = faster generation"
                )
                .defineInRange("tier_3_ticks", 10, 1, 200);

        COBBLESTONE_GEN_TIER_4_TICKS = COMMON_BUILDER
                .comment(
                        "Cobblestone generation interval for Tier 4 (ticks)",
                        "Lower values = faster generation"
                )
                .defineInRange("tier_4_ticks", 5, 1, 200);

        COBBLESTONE_GEN_TIER_5_TICKS = COMMON_BUILDER
                .comment(
                        "Cobblestone generation interval for Tier 5 (ticks)",
                        "Lower values = faster generation"
                )
                .defineInRange("tier_5_ticks", 1, 1, 200);

        COMMON_BUILDER.pop();
    }

    private static void buildLavaGeneratorConfig() {
        COMMON_BUILDER.comment("Lava Generator - Configure lava generation rate")
                .push("lava_generator");

        LAVA_GENERATOR_MB_PER_TICK = COMMON_BUILDER
                .comment(
                        "Lava generation rate (millibuckets per tick)",
                        "Higher values = faster generation"
                )
                .defineInRange("mb_per_tick", 1, 1, 1000);

        COMMON_BUILDER.pop();
    }

    private static void buildWaterReservoirConfig() {
        COMMON_BUILDER.comment("Water Reservoir - Configure infinite water source behavior")
                .push("water_reservoir");

        WATER_RESERVOIR_INFINITE = COMMON_BUILDER
                .comment(
                        "Enable infinite water source",
                        "If true, Water Reservoir acts as an infinite water source",
                        "If false, it acts as a 16-bucket tank"
                )
                .define("infinite_water", true);

        COMMON_BUILDER.pop();
    }

    private static void buildGreenhouseGlassConfig() {
        COMMON_BUILDER.comment("Greenhouse Glass - Configure growth boost and effective depth")
                .push("greenhouse_glass");

        GREENHOUSE_GLASS_GROWTH_BOOST = COMMON_BUILDER
                .comment(
                        "Growth boost percentage when in direct sunlight",
                        "Higher values increase growth speed (1.0 = 100%)"
                )
                .defineInRange("growth_boost", 1.0, 0.05, 2.0);

        GREENHOUSE_GLASS_RANGE = COMMON_BUILDER
                .comment(
                        "Number of blocks below the greenhouse glass to check for crops",
                        "Note: Larger ranges may impact performance"
                )
                .defineInRange("range", 5, 1, 32);

        COMMON_BUILDER.pop();
    }

    private static void buildRepairTotemConfig() {
        COMMON_BUILDER.comment("Repair Totem - Configure item repair rates")
                .push("repair_totem");

        REPAIR_TOTEM_DURABILITY_PER_TOTEM = COMMON_BUILDER
                .comment(
                        "Durability repaired per totem per interval",
                        "Higher values = faster repair"
                )
                .defineInRange("durability_per_totem", 1, 1, 100);

        REPAIR_TOTEM_TICK_INTERVAL = COMMON_BUILDER
                .comment(
                        "How often repair occurs (in ticks)",
                        "Lower values = more frequent repair"
                )
                .defineInRange("tick_interval", 20, 1, 200);

        COMMON_BUILDER.pop();
    }

    private static void buildBigBucketConfig() {
        COMMON_BUILDER.comment("Big Bucket - Configure capacity")
                .push("big_bucket");

        BIG_BUCKET_CAPACITY = COMMON_BUILDER
                .comment(
                        "Big Bucket capacity in buckets"
                )
                .defineInRange("capacity", 16, 8, 64);

        COMMON_BUILDER.pop();
    }

    // Getter methods
    public static double getGrowthCrystalTier1Rate() {
        return GROWTH_CRYSTAL_TIER_1_RATE.get();
    }

    public static double getGrowthCrystalTier2Rate() {
        return GROWTH_CRYSTAL_TIER_2_RATE.get();
    }

    public static double getGrowthCrystalTier3Rate() {
        return GROWTH_CRYSTAL_TIER_3_RATE.get();
    }

    public static int getCobblestoneGenTier1Ticks() {
        return COBBLESTONE_GEN_TIER_1_TICKS.get();
    }

    public static int getCobblestoneGenTier2Ticks() {
        return COBBLESTONE_GEN_TIER_2_TICKS.get();
    }

    public static int getCobblestoneGenTier3Ticks() {
        return COBBLESTONE_GEN_TIER_3_TICKS.get();
    }

    public static int getCobblestoneGenTier4Ticks() {
        return COBBLESTONE_GEN_TIER_4_TICKS.get();
    }

    public static int getCobblestoneGenTier5Ticks() {
        return COBBLESTONE_GEN_TIER_5_TICKS.get();
    }

    public static int getLavaGeneratorMbPerTick() {
        return LAVA_GENERATOR_MB_PER_TICK.get();
    }

    public static boolean isWaterReservoirInfinite() {
        return WATER_RESERVOIR_INFINITE.get();
    }

    public static double getGreenhouseGlassGrowthBoost() {
        return GREENHOUSE_GLASS_GROWTH_BOOST.get();
    }

    public static int getGreenhouseGlassRange() {
        return GREENHOUSE_GLASS_RANGE.get();
    }

    public static int getRepairTotemDurabilityPerTotem() {
        return REPAIR_TOTEM_DURABILITY_PER_TOTEM.get();
    }

    public static int getRepairTotemTickInterval() {
        return REPAIR_TOTEM_TICK_INTERVAL.get();
    }

    public static int getBigBucketCapacity() {
        return BIG_BUCKET_CAPACITY.get() * 1000;
    }

    private static void validateConfig() {
        if (getCobblestoneGenTier5Ticks() == 1 && getCobblestoneGenTier4Ticks() == 1) {
            LOGGER.warn(
                    "Multiple cobblestone generators set to 1 tick may impact server performance!"
            );
        }

        if (getLavaGeneratorMbPerTick() > 500) {
            LOGGER.warn(
                    "Lava Generator generation rate ({} mb/tick) is very high and may impact balance!",
                    getLavaGeneratorMbPerTick()
            );
        }

        if (getGrowthCrystalTier3Rate() > 1.5) {
            LOGGER.warn(
                    "Growth Crystal Tier 3 rate ({}) is very high and may impact game balance!",
                    getGrowthCrystalTier3Rate()
            );
        }

        if (getGreenhouseGlassRange() > 16) {
            LOGGER.warn(
                    "Greenhouse Glass depth ({}) is very high and may impact server performance!",
                    getGreenhouseGlassRange()
            );
        }
    }

    @SubscribeEvent
    public static void onConfigLoad(ModConfigEvent event) {
        if (event.getConfig().getType() == ModConfig.Type.COMMON) {
            LOGGER.info("Small Progressions configuration loaded");
            logConfigValues();
            validateConfig();
        }
    }

    private static void logConfigValues() {
        LOGGER.info("Growth Crystal Configuration:");
        LOGGER.info("  Tier 1 Rate: {}x", getGrowthCrystalTier1Rate());
        LOGGER.info("  Tier 2 Rate: {}x", getGrowthCrystalTier2Rate());
        LOGGER.info("  Tier 3 Rate: {}x", getGrowthCrystalTier3Rate());

        LOGGER.info("Cobblestone Generator Configuration:");
        LOGGER.info("  Tier 1: {} ticks", getCobblestoneGenTier1Ticks());
        LOGGER.info("  Tier 2: {} ticks", getCobblestoneGenTier2Ticks());
        LOGGER.info("  Tier 3: {} ticks", getCobblestoneGenTier3Ticks());
        LOGGER.info("  Tier 4: {} ticks", getCobblestoneGenTier4Ticks());
        LOGGER.info("  Tier 5: {} ticks", getCobblestoneGenTier5Ticks());

        LOGGER.info("Lava Generator Configuration:");
        LOGGER.info("  Generation Rate: {} mb/tick", getLavaGeneratorMbPerTick());

        LOGGER.info("Water Reservoir Configuration:");
        LOGGER.info("  Infinite Water: {}", isWaterReservoirInfinite());

        LOGGER.info("Greenhouse Glass Configuration:");
        LOGGER.info("  Growth Boost: {}%", getGreenhouseGlassGrowthBoost() * 100);
        LOGGER.info("  Depth: {} blocks below", getGreenhouseGlassRange());

        LOGGER.info("Repair Totem Configuration:");
        LOGGER.info("  Durability per Totem: {}", getRepairTotemDurabilityPerTotem());
        LOGGER.info("  Tick Interval: {} ticks ({} seconds)", getRepairTotemTickInterval(), getRepairTotemTickInterval() / 20.0);

        LOGGER.info("Big Bucket Configuration:");
        LOGGER.info("  Capacity: {} buckets ({}mb)", BIG_BUCKET_CAPACITY.get(), getBigBucketCapacity());
    }
}