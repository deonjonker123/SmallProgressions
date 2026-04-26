package com.misterd.smallprogressions.network;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.*;

public class WirelessNetwork extends SavedData {
    public static final long MAX_POOL = Integer.MAX_VALUE;

    private record Data(Map<String, Long> pools, Map<String, Boolean> publicFlags) {
        static final Codec<Data> CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.unboundedMap(Codec.STRING, Codec.LONG).fieldOf("pools").forGetter(Data::pools),
                Codec.unboundedMap(Codec.STRING, Codec.BOOL).fieldOf("public").forGetter(Data::publicFlags)
        ).apply(i, Data::new));
    }

    public static final SavedDataType<WirelessNetwork> TYPE = new SavedDataType<>(
            Identifier.fromNamespaceAndPath("smallprogressions", "wireless_network"),
            WirelessNetwork::new,
            Data.CODEC.xmap(WirelessNetwork::fromData, WirelessNetwork::toData)
    );

    private final Map<UUID, Long> pools = new HashMap<>();
    private final Map<UUID, Boolean> publicFlags = new HashMap<>();
    private Map<UUID, Long> cachedPublicPools = null;
    private boolean publicPoolsDirty = true;

    private WirelessNetwork() {}

    public static WirelessNetwork get(ServerLevel level) {
        return level.getServer().overworld().getDataStorage().computeIfAbsent(TYPE);
    }

    private static WirelessNetwork fromData(Data data) {
        WirelessNetwork network = new WirelessNetwork();
        data.pools().forEach((k, v) -> network.pools.put(UUID.fromString(k), v));
        data.publicFlags().forEach((k, v) -> network.publicFlags.put(UUID.fromString(k), v));
        return network;
    }

    private Data toData() {
        Map<String, Long> p = new HashMap<>();
        pools.forEach((k, v) -> p.put(k.toString(), v));
        Map<String, Boolean> f = new HashMap<>();
        publicFlags.forEach((k, v) -> f.put(k.toString(), v));
        return new Data(p, f);
    }

    public long getPool(UUID owner) {
        return pools.getOrDefault(owner, 0L);
    }

    public void addToPool(UUID owner, long amount) {
        pools.put(owner, Math.min(pools.getOrDefault(owner, 0L) + amount, MAX_POOL));
        setDirty();
    }

    public void removeFromPool(UUID owner, long amount) {
        pools.put(owner, Math.max(pools.getOrDefault(owner, 0L) - amount, 0L));
        setDirty();
    }

    public void setPublic(UUID owner, boolean isPublic) {
        publicFlags.put(owner, isPublic);
        publicPoolsDirty = true;
        setDirty();
    }

    public boolean isPublic(UUID owner) {
        return publicFlags.getOrDefault(owner, false);
    }

    public Map<UUID, Long> getPublicPools() {
        if (publicPoolsDirty || cachedPublicPools == null) {
            Map<UUID, Long> result = new HashMap<>();
            publicFlags.forEach((uuid, isPublic) -> {
                if (isPublic && pools.containsKey(uuid)) result.put(uuid, pools.get(uuid));
            });
            cachedPublicPools = Collections.unmodifiableMap(result);
            publicPoolsDirty = false;
        }
        return cachedPublicPools;
    }
}