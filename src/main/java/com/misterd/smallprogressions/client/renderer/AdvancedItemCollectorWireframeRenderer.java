package com.misterd.smallprogressions.client.renderer;

import com.misterd.smallprogressions.blockentity.custom.AdvancedItemCollectorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ExtractLevelRenderStateEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = "smallprogressions", value = Dist.CLIENT)
public class AdvancedItemCollectorWireframeRenderer {

    private static final ContextKey<AABB> WIREFRAME_KEY = new ContextKey<>(
            Identifier.fromNamespaceAndPath("smallprogressions", "advanced_item_collector_wireframe")
    );

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
    public static void onExtractLevelRenderState(ExtractLevelRenderStateEvent event) {
        if (!showWireframes || activeCollectorPos == null) return;

        Level level = event.getLevel();
        BlockEntity be = level.getBlockEntity(activeCollectorPos);

        if (be instanceof AdvancedItemCollectorBlockEntity collector) {
            event.getRenderState().setRenderData(WIREFRAME_KEY, calculateCollectionZone(collector));
        } else {
            clearWireframes();
        }
    }

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent.AfterTranslucentBlocks event) {
        LevelRenderState renderState = event.getLevelRenderState();
        AABB zone = renderState.getRenderData(WIREFRAME_KEY);
        if (zone == null) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        Vec3 camPos = mc.gameRenderer.getMainCamera().position();
        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();
        poseStack.translate(-camPos.x(), -camPos.y(), -camPos.z());

        var bufferSource = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = bufferSource.getBuffer(RenderTypes.lines());
        ShapeRenderer.renderShape(
                poseStack, buffer,
                Shapes.create(zone),
                0.0, 0.0, 0.0,
                ARGB.colorFromFloat(0.8f, 0f, 1f, 1f),
                mc.gameRenderer.getGameRenderState().windowRenderState.appropriateLineWidth
        );
        bufferSource.endLastBatch();
        poseStack.popPose();
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
}