package com.misterd.smallprogressions.network;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.blockentity.custom.LogisticsSenderBlockEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record LogisticsSenderConfigPacket(BlockPos pos, ConfigType configType, boolean value) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<LogisticsSenderConfigPacket> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "logistics_sender_config"));

    public static final StreamCodec<ByteBuf, LogisticsSenderConfigPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, LogisticsSenderConfigPacket::pos,
                    ByteBufCodecs.VAR_INT.map(ConfigType::fromId, ConfigType::id), LogisticsSenderConfigPacket::configType,
                    ByteBufCodecs.BOOL, LogisticsSenderConfigPacket::value,
                    LogisticsSenderConfigPacket::new);

    @Override
    public CustomPacketPayload.Type<LogisticsSenderConfigPacket> type() {
        return TYPE;
    }

    public static void handle(LogisticsSenderConfigPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            if (!(ctx.player() instanceof ServerPlayer player)) return;
            if (player.distanceToSqr(packet.pos().getCenter()) > 64) return;
            BlockEntity be = player.level().getBlockEntity(packet.pos());
            if (!(be instanceof LogisticsSenderBlockEntity sender)) return;
            switch (packet.configType()) {
                case REDSTONE_ACTIVE -> sender.setRedstoneActive(packet.value());
                case ROUND_ROBIN -> sender.setRoundRobin(packet.value());
                case FILTER_ALLOW -> sender.setFilterAllow(packet.value());
            }
        });
    }

    public enum ConfigType {
        REDSTONE_ACTIVE(0),
        ROUND_ROBIN(1),
        FILTER_ALLOW(2);

        private final int id;
        ConfigType(int id) { this.id = id; }
        public int id() { return id; }
        public static ConfigType fromId(int id) {
            for (ConfigType t : values()) if (t.id == id) return t;
            throw new IllegalArgumentException("Unknown type id: " + id);
        }
    }
}