package com.misterd.smallprogressions.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;

public class PlayerFenceJumpEvent extends Event {

    private final Player player;

    public PlayerFenceJumpEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}