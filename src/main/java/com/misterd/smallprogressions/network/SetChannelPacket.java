package com.misterd.smallprogressions.network;

import com.misterd.smallprogressions.SmallProgressions;
import com.misterd.smallprogressions.blockentity.custom.WirelessRedstoneReceiverBlockEntity;
import com.misterd.smallprogressions.blockentity.custom.WirelessRedstoneTransmitterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SetChannelPacket(BlockPos pos, int channel) implements CustomPacketPayload {

    public static final Type<SetChannelPacket> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "set_channel"));

    public static final StreamCodec<FriendlyByteBuf, SetChannelPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, SetChannelPacket::pos,
                    ByteBufCodecs.INT, SetChannelPacket::channel,
                    SetChannelPacket::new);

    @Override
    public Type<SetChannelPacket> type() {
        return TYPE;
    }

    public static void handle(SetChannelPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            if (!(ctx.player() instanceof ServerPlayer player)) return;

            int safeChannel = Math.max(0, packet.channel());
            if (player.distanceToSqr(packet.pos().getCenter()) > 64) return;

            BlockEntity be = player.level().getBlockEntity(packet.pos());
            if (be instanceof WirelessRedstoneTransmitterBlockEntity transmitter) {
                transmitter.setChannel(safeChannel);
            } else if (be instanceof WirelessRedstoneReceiverBlockEntity receiver) {
                receiver.setChannel(safeChannel);
            }
        });
    }
}