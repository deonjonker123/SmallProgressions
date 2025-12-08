package com.misterd.smallprogressions.client.renderer;

import com.misterd.smallprogressions.blockentity.custom.AdvancedItemCollectorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(Dist.CLIENT)
public class AdvancedItemCollectorWireframeRenderer {

    private static boolean showWireframes = false;
    private static BlockPos activeCollectorPos = null;

    public static void toggleWireframe(BlockPos collectorPos) {
        if (activeCollectorPos != null && activeCollectorPos.equals(collectorPos)) {
            showWireframes = false;
            activeCollectorPos = null;
        } else {
            showWireframes = true;
            activeCollectorPos = collectorPos;
        }
    }

    public static boolean isWireframeActive(BlockPos collectorPos) {
        return showWireframes && activeCollectorPos != null && activeCollectorPos.equals(collectorPos);
    }

    public static void clearWireframes() {
        showWireframes = false;
        activeCollectorPos = null;
    }

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) return;
        if (!showWireframes || activeCollectorPos == null) return;

        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        Level level = minecraft.level;

        if (player == null || level == null) return;

        BlockEntity be = level.getBlockEntity(activeCollectorPos);
        if (be instanceof AdvancedItemCollectorBlockEntity collector) {
            AABB collectionZone = calculateCollectionZone(collector);
            renderWireframe(event.getPoseStack(), collectionZone, event.getCamera().getPosition());
        } else {
            clearWireframes();
        }
    }

    private static AABB calculateCollectionZone(AdvancedItemCollectorBlockEntity collector) {
        BlockPos pos = collector.getBlockPos();
        int radius = 4;

        return new AABB(
                pos.getX() - radius + collector.getEastWestOffset(),
                pos.getY() - radius + collector.getDownUpOffset(),
                pos.getZ() - radius + collector.getNorthSouthOffset(),
                pos.getX() + radius + 1 + collector.getEastWestOffset(),
                pos.getY() + radius + 1 + collector.getDownUpOffset(),
                pos.getZ() + radius + 1 + collector.getNorthSouthOffset()
        );
    }

    private static void renderWireframe(PoseStack poseStack, AABB aabb, Vec3 cameraPos) {
        poseStack.pushPose();
        poseStack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);

        Minecraft minecraft = Minecraft.getInstance();
        MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
        VertexConsumer buffer = bufferSource.getBuffer(RenderType.lines());

        LevelRenderer.renderLineBox(poseStack, buffer, aabb, 0.0f, 1.0f, 1.0f, 0.8f);

        bufferSource.endBatch(RenderType.lines());
        poseStack.popPose();
    }
}