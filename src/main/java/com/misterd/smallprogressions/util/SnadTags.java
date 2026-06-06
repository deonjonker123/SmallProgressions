package com.misterd.smallprogressions.util;

import com.misterd.smallprogressions.SmallProgressions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SnadTags {
    public static final TagKey<Block> SNAD_GROWABLES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "snad_growables"));
    public static final TagKey<Block> SOUL_SNAD_GROWABLES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "soul_snad_growables"));
    public static final TagKey<Block> SNAD_GROWABLES_REQUIRES_WATER = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "snad_growables_requires_water"));
}
