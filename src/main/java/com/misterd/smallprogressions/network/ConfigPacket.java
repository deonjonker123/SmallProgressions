package com.misterd.smallprogressions.network;

import com.misterd.smallprogressions.blockentity.custom.AdvancedItemCollectorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Optional;

public record ConfigPacket(
        ConfigTarget target,
        BlockPos pos,
        ConfigType configType,
        int intValue,
        boolean boolValue
) implements CustomPacketPayload {

    public static final Type<ConfigPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath("smallprogressions", "config_packet")
    );

    public static final StreamCodec<FriendlyByteBuf, ConfigPacket> STREAM_CODEC;

    static {
        STREAM_CODEC = StreamCodec.composite(
                ConfigTarget.STREAM_CODEC, ConfigPacket::target,
                ByteBufCodecs.optional(BlockPos.STREAM_CODEC),
                packet -> Optional.ofNullable(packet.pos()),
                ConfigType.STREAM_CODEC, ConfigPacket::configType,
                ByteBufCodecs.INT, ConfigPacket::intValue,
                ByteBufCodecs.BOOL, ConfigPacket::boolValue,
                (target, posOpt, configType, intValue, boolValue) ->
                        new ConfigPacket(target, posOpt.orElse(null), configType, intValue, boolValue)
        );
    }

    @Override
    public Type<ConfigPacket> type() {
        return TYPE;
    }

    public static void handle(ConfigPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (!(player instanceof ServerPlayer serverPlayer)) return;

            switch (packet.target()) {
                case ADVANCED_ITEM_COLLECTOR -> handleAdvancedItemCollectorConfig(packet, serverPlayer);
            }
        });
    }

    private static void handleAdvancedItemCollectorConfig(ConfigPacket packet, ServerPlayer player) {
        BlockPos pos = packet.pos();
        if (pos == null) return;

        ServerLevel level = player.serverLevel();

        if (player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) > 64.0) return;

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof AdvancedItemCollectorBlockEntity collector)) return;

        switch (packet.configType()) {
            case ADVANCED_COLLECTOR_DOWN_UP_OFFSET -> collector.setDownUpOffset(packet.intValue());
            case ADVANCED_COLLECTOR_NORTH_SOUTH_OFFSET -> collector.setNorthSouthOffset(packet.intValue());
            case ADVANCED_COLLECTOR_EAST_WEST_OFFSET -> collector.setEastWestOffset(packet.intValue());
            case ADVANCED_COLLECTOR_REDSTONE_MODE -> collector.setRequiresRedstone(packet.boolValue());
            case ADVANCED_COLLECTOR_FILTER_MODE -> collector.setFilterMode(packet.boolValue());
            default -> {}
        }

        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    public enum ConfigTarget {
        ADVANCED_ITEM_COLLECTOR;

        public static final StreamCodec<FriendlyByteBuf, ConfigTarget> STREAM_CODEC = StreamCodec.of(
                (buf, target) -> buf.writeEnum(target),
                buf -> buf.readEnum(ConfigTarget.class)
        );
    }

    public enum ConfigType {
        ADVANCED_COLLECTOR_DOWN_UP_OFFSET,
        ADVANCED_COLLECTOR_NORTH_SOUTH_OFFSET,
        ADVANCED_COLLECTOR_EAST_WEST_OFFSET,
        ADVANCED_COLLECTOR_REDSTONE_MODE,
        ADVANCED_COLLECTOR_FILTER_MODE;

        public static final StreamCodec<FriendlyByteBuf, ConfigType> STREAM_CODEC = StreamCodec.of(
                (buf, type) -> buf.writeEnum(type),
                buf -> buf.readEnum(ConfigType.class)
        );
    }
}