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

public record SetRunningPacket(BlockPos pos, boolean running) implements CustomPacketPayload {

    public static final Type<SetRunningPacket> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "set_running"));

    public static final StreamCodec<FriendlyByteBuf, SetRunningPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,   SetRunningPacket::pos,
                    ByteBufCodecs.BOOL,      SetRunningPacket::running,
                    SetRunningPacket::new);

    @Override
    public Type<SetRunningPacket> type() {
        return TYPE;
    }

    public static void handle(SetRunningPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            if (!(ctx.player() instanceof ServerPlayer player)) return;
            if (player.distanceToSqr(packet.pos().getCenter()) > 64) return;

            if (player.level().getBlockEntity(packet.pos()) instanceof TimerBlockEntity be) {
                be.setRunning(packet.running());
            }
        });
    }
}