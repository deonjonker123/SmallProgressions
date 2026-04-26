package com.misterd.smallprogressions.network;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.blockentity.custom.TimerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SetIntervalPacket(BlockPos pos, int interval) implements CustomPacketPayload {

    public static final Type<SetIntervalPacket> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "set_interval"));

    public static final StreamCodec<FriendlyByteBuf, SetIntervalPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,   SetIntervalPacket::pos,
                    ByteBufCodecs.INT,       SetIntervalPacket::interval,
                    SetIntervalPacket::new);

    @Override
    public Type<SetIntervalPacket> type() {
        return TYPE;
    }

    public static void handle(SetIntervalPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            if (!(ctx.player() instanceof ServerPlayer player)) return;
            if (player.distanceToSqr(packet.pos().getCenter()) > 64) return;

            int safeInterval = Math.clamp(packet.interval(),
                    TimerBlockEntity.MIN_INTERVAL,
                    TimerBlockEntity.MAX_INTERVAL);

            if (player.level().getBlockEntity(packet.pos()) instanceof TimerBlockEntity be) {
                be.setInterval(safeInterval);
            }
        });
    }
}