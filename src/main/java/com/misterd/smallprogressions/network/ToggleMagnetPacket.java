package com.misterd.smallprogressions.network;

import com.misterd.smallprogressions.compat.curios.CuriosCompat;
import com.misterd.smallprogressions.item.custom.MagnetItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ToggleMagnetPacket() implements CustomPacketPayload {

    public static final Type<ToggleMagnetPacket> TYPE = new Type<>(
            Identifier.fromNamespaceAndPath("smallprogressions", "toggle_magnet")
    );

    public static final StreamCodec<FriendlyByteBuf, ToggleMagnetPacket> STREAM_CODEC =
            StreamCodec.unit(new ToggleMagnetPacket());

    @Override
    public Type<ToggleMagnetPacket> type() {
        return TYPE;
    }

    public static void handle(ToggleMagnetPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (!(player instanceof ServerPlayer serverPlayer)) return;

            for (int i = 0; i < serverPlayer.getInventory().getContainerSize(); i++) {
                ItemStack stack = serverPlayer.getInventory().getItem(i);
                if (stack.getItem() instanceof MagnetItem) {
                    MagnetItem.toggle(serverPlayer, stack);
                    return;
                }
            }

            if (ModList.get().isLoaded("curios")) {
                CuriosCompat.toggleMagnetInCurios(serverPlayer);
            }
        });
    }
}