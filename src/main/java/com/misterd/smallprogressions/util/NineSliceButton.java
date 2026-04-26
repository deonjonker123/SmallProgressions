package com.misterd.smallprogressions.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class NineSliceButton extends Button {
    private final Identifier texture;
    private final int texW;
    private final int texH;
    private final int corner;

    public NineSliceButton(int x, int y, int w, int h, Component label, OnPress onPress, Identifier texture, int texW, int texH, int corner) {
        super(x, y, w, h, label, onPress, DEFAULT_NARRATION);
        this.texture = texture;
        this.texW = texW;
        this.texH = texH;
        this.corner = corner;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor gfx, int mouseX, int mouseY, float partialTick) {
        blitNineSlice(gfx, getX(), getY(), getWidth(), getHeight());
        var font = Minecraft.getInstance().font;
        int labelColor = active ? 0xFF404040 : 0xFFA0A0A0;
        gfx.text(font, getMessage(), getX() + (getWidth() - font.width(getMessage())) / 2, getY() + (getHeight() - 8) / 2, labelColor, false);
    }

    private void blitNineSlice(GuiGraphicsExtractor gfx, int x, int y, int w, int h) {
        int c = corner;
        int mw = texW - c * 2;
        int mh = texH - c * 2;
        int bw = w - c * 2;
        int bh = h - c * 2;
        gfx.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, (float)0, (float)0, c, c, texW, texH);
        gfx.blit(RenderPipelines.GUI_TEXTURED, texture, x + w - c, y, (float)(c + mw), (float)0, c, c, texW, texH);
        gfx.blit(RenderPipelines.GUI_TEXTURED, texture, x, y + h - c, (float)0, (float)(c + mh), c, c, texW, texH);
        gfx.blit(RenderPipelines.GUI_TEXTURED, texture, x + w - c, y + h - c, (float)(c + mw), (float)(c + mh), c, c, texW, texH);
        gfx.blit(RenderPipelines.GUI_TEXTURED, texture, x + c, y, (float)c, (float)0, bw, c, texW, texH);
        gfx.blit(RenderPipelines.GUI_TEXTURED, texture, x + c, y + h - c, (float)c, (float)(c + mh), bw, c, texW, texH);
        gfx.blit(RenderPipelines.GUI_TEXTURED, texture, x, y + c, (float)0, (float)c, c, bh, texW, texH);
        gfx.blit(RenderPipelines.GUI_TEXTURED, texture, x + w - c, y + c, (float)(c + mw), (float)c, c, bh, texW, texH);
        gfx.blit(RenderPipelines.GUI_TEXTURED, texture, x + c, y + c, (float)c, (float)c, bw, bh, texW, texH);
    }
}