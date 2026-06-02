package com.misterd.smallprogressions.mixin;

import com.misterd.smallprogressions.event.PlayerFenceJumpEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class PlayerMixin {
    @Inject(at = @At("TAIL"), method = "jumpFromGround()V")
    private void jumpoverfences_jumpFromGround(CallbackInfo info) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof Player player && player.level().isClientSide()) {
            NeoForge.EVENT_BUS.post(new PlayerFenceJumpEvent(player));
        }
    }
}