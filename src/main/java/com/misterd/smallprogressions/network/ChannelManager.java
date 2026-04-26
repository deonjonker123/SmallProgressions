package com.misterd.smallprogressions.network;

import com.misterd.smallprogressions.block.custom.WirelessRedstoneReceiverBlock;
import com.misterd.smallprogressions.block.custom.WirelessRedstoneTransmitterBlock;
import com.misterd.smallprogressions.blockentity.custom.WirelessRedstoneReceiverBlockEntity;
import com.misterd.smallprogressions.config.Config;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.util.datafix.DataFixTypes;

import java.util.*;

public class ChannelManager extends SavedData {

    private record Entry(String owner, int channel, long pos) {
        static final Codec<Entry> CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.STRING.fieldOf("owner").forGetter(Entry::owner),
                Codec.INT.fieldOf("channel").forGetter(Entry::channel),
                Codec.LONG.fieldOf("pos").forGetter(Entry::pos)
        ).apply(i, Entry::new));
    }

    private record Data(List<Entry> transmitters, List<Entry> receivers) {
        static final Codec<Data> CODEC = RecordCodecBuilder.create(i -> i.group(
                Entry.CODEC.listOf().fieldOf("transmitters").forGetter(Data::transmitters),
                Entry.CODEC.listOf().fieldOf("receivers").forGetter(Data::receivers)
        ).apply(i, Data::new));
    }

    public static final SavedDataType<ChannelManager> TYPE = new SavedDataType<>(
            Identifier.fromNamespaceAndPath("smallprogressions", "channel_manager"),
            level -> new ChannelManager(),
            level -> Data.CODEC.xmap(ChannelManager::fromData, ChannelManager::toData),
            DataFixTypes.SAVED_DATA_MAP_DATA
    );

    private final Map<UUID, Map<Integer, Set<BlockPos>>> transmitters = new HashMap<>();
    private final Map<UUID, Map<Integer, Set<BlockPos>>> receivers = new HashMap<>();

    public static ChannelManager get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(TYPE);
    }

    private static ChannelManager fromData(Data data) {
        ChannelManager mgr = new ChannelManager();
        loadEntries(data.transmitters(), mgr.transmitters);
        loadEntries(data.receivers(), mgr.receivers);
        return mgr;
    }

    private Data toData() {
        return new Data(saveEntries(transmitters), saveEntries(receivers));
    }

    private static void loadEntries(List<Entry> entries, Map<UUID, Map<Integer, Set<BlockPos>>> target) {
        for (Entry e : entries) {
            target.computeIfAbsent(UUID.fromString(e.owner()), k -> new HashMap<>())
                    .computeIfAbsent(e.channel(), k -> new HashSet<>())
                    .add(BlockPos.of(e.pos()));
        }
    }

    private static List<Entry> saveEntries(Map<UUID, Map<Integer, Set<BlockPos>>> source) {
        List<Entry> list = new ArrayList<>();
        for (Map.Entry<UUID, Map<Integer, Set<BlockPos>>> byOwner : source.entrySet()) {
            for (Map.Entry<Integer, Set<BlockPos>> byChannel : byOwner.getValue().entrySet()) {
                for (BlockPos pos : byChannel.getValue()) {
                    list.add(new Entry(byOwner.getKey().toString(), byChannel.getKey(), pos.asLong()));
                }
            }
        }
        return list;
    }

    public Map<UUID, Map<Integer, Set<BlockPos>>> getReceivers() {
        return receivers;
    }

    public void registerTransmitter(UUID owner, int channel, BlockPos pos) {
        transmitters.computeIfAbsent(owner, k -> new HashMap<>())
                .computeIfAbsent(channel, k -> new HashSet<>())
                .add(pos);
        setDirty();
    }

    public void unregisterTransmitter(UUID owner, int channel, BlockPos pos) {
        Map<Integer, Set<BlockPos>> byChannel = transmitters.get(owner);
        if (byChannel == null) return;
        Set<BlockPos> positions = byChannel.get(channel);
        if (positions == null) return;
        positions.remove(pos);
        if (positions.isEmpty()) byChannel.remove(channel);
        if (byChannel.isEmpty()) transmitters.remove(owner);
        setDirty();
    }

    public void registerReceiver(UUID owner, int channel, BlockPos pos) {
        receivers.computeIfAbsent(owner, k -> new HashMap<>())
                .computeIfAbsent(channel, k -> new HashSet<>())
                .add(pos);
        setDirty();
    }

    public void unregisterReceiver(UUID owner, int channel, BlockPos pos) {
        Map<Integer, Set<BlockPos>> byChannel = receivers.get(owner);
        if (byChannel == null) return;
        Set<BlockPos> positions = byChannel.get(channel);
        if (positions == null) return;
        positions.remove(pos);
        if (positions.isEmpty()) byChannel.remove(channel);
        if (byChannel.isEmpty()) receivers.remove(owner);
        setDirty();
    }

    public void onTransmitterPowered(ServerLevel level, BlockPos transmitterPos, UUID owner, int channel, boolean powered) {
        if (channel < 0) return;
        Map<Integer, Set<BlockPos>> byChannel = receivers.get(owner);
        if (byChannel == null) return;
        Set<BlockPos> targets = byChannel.get(channel);
        if (targets == null || targets.isEmpty()) return;

        int range = Config.getTransmissionRange();
        double rangeSq = (double) range * range;

        for (BlockPos receiverPos : Set.copyOf(targets)) {
            if (transmitterPos.distSqr(receiverPos) > rangeSq) continue;
            BlockState state = level.getBlockState(receiverPos);
            if (!(state.getBlock() instanceof WirelessRedstoneReceiverBlock)) {
                targets.remove(receiverPos);
                setDirty();
                continue;
            }
            if (state.getValue(WirelessRedstoneReceiverBlock.POWERED) != powered) {
                level.setBlock(receiverPos, state.setValue(WirelessRedstoneReceiverBlock.POWERED, powered), 3);
            }
            if (level.getBlockEntity(receiverPos) instanceof WirelessRedstoneReceiverBlockEntity) {
                level.sendBlockUpdated(receiverPos, state, state, 3);
            }
        }
    }

    public void onReceiverChannelChanged(ServerLevel level, BlockPos receiverPos, UUID owner, int channel) {
        if (channel < 0) return;
        Map<Integer, Set<BlockPos>> byChannel = transmitters.get(owner);
        if (byChannel == null) return;
        Set<BlockPos> sources = byChannel.get(channel);
        if (sources == null || sources.isEmpty()) return;

        int range = Config.getTransmissionRange();
        double rangeSq = (double) range * range;

        for (BlockPos transmitterPos : sources) {
            if (transmitterPos.distSqr(receiverPos) > rangeSq) continue;
            BlockState txState = level.getBlockState(transmitterPos);
            if (!txState.hasProperty(WirelessRedstoneTransmitterBlock.POWERED)) continue;
            if (txState.getValue(WirelessRedstoneTransmitterBlock.POWERED)) {
                BlockState rxState = level.getBlockState(receiverPos);
                if (rxState.getBlock() instanceof WirelessRedstoneReceiverBlock) {
                    level.setBlock(receiverPos, rxState.setValue(WirelessRedstoneReceiverBlock.POWERED, true), 3);
                }
                return;
            }
        }
    }
}