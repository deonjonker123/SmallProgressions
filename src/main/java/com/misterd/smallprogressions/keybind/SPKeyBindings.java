package com.misterd.smallprogressions.keybind;

import com.misterd.smallprogressions.SmallProgressions;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class SPKeyBindings {

    public static final KeyMapping.Category CATEGORY = new KeyMapping.Category(
            Identifier.fromNamespaceAndPath(SmallProgressions.MODID, "main")
    );

    public static final KeyMapping TOGGLE_MAGNET = new KeyMapping(
            "key.smallprogressions.toggle_magnet",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_DIVIDE,
            CATEGORY
    );
}