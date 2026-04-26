package com.misterd.smallprogressions.client.renderer.ber;

import com.misterd.smallprogressions.blockentity.custom.LogisticsSenderBlockEntity;
import com.misterd.smallprogressions.blockentity.custom.LogisticsReceiverBlockEntity;
import com.misterd.smallprogressions.item.SPItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class LogisticsSenderBlockEntityRenderer
        implements BlockEntityRenderer<LogisticsSenderBlockEntity, LogisticsSenderBlockEntityRenderer.RenderState> {

    public static class RenderState extends BlockEntityRenderState {
        List<Vec3[]> beams = new ArrayList<>();
        @Nullable TextureAtlasSprite sprite = null;
    }

    public LogisticsSenderBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public RenderState createRenderState() { return new RenderState(); }

    @Override
    public boolean shouldRenderOffScreen() { return true; }

    @Override
    public void extractRenderState(LogisticsSenderBlockEntity be, RenderState state, float partialTick,
                                   Vec3 cameraPos, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderState.extractBase(be, state, crumblingOverlay);
        state.beams.clear();
        state.sprite = null;

        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        if (!player.getMainHandItem().is(SPItems.CONNECTION_WRENCH.get()) &&
                !player.getOffhandItem().is(SPItems.CONNECTION_WRENCH.get())) return;

        Level level = be.getLevel();
        if (level == null) return;
        if (be.getConnectedReceivers().isEmpty()) return;

        Vec3 senderCenter = Vec3.atCenterOf(be.getBlockPos());
        Vec3 blockOrigin = new Vec3(be.getBlockPos().getX(), be.getBlockPos().getY(), be.getBlockPos().getZ());

        for (BlockPos receiverPos : be.getConnectedReceivers()) {
            if (!(level.getBlockEntity(receiverPos) instanceof LogisticsReceiverBlockEntity)) continue;
            Vec3 receiverWorld = Vec3.atCenterOf(receiverPos);
            Vec3 startLocal = senderCenter.subtract(blockOrigin);
            Vec3 endLocal = receiverWorld.subtract(blockOrigin);
            state.beams.add(new Vec3[]{startLocal, endLocal});
        }

        state.sprite = Minecraft.getInstance()
                .getAtlasManager()
                .getAtlasOrThrow(AtlasIds.BLOCKS)
                .getSprite(Identifier.withDefaultNamespace("block/white_concrete"));
    }

    @Override
    public void submit(RenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        if (state.beams.isEmpty() || state.sprite == null) return;

        TextureAtlasSprite s = state.sprite;
        float u0 = s.getU0(), u1 = s.getU1();
        float v0 = s.getV0(), v1 = s.getV1();
        float r = 0.835f, g = 1.0f, b = 0.0f, a = 0.5f;
        float radius = 0.02f;
        int ov = OverlayTexture.NO_OVERLAY;

        for (Vec3[] beam : state.beams) {
            Vec3 start = beam[0];
            Vec3 end = beam[1];
            Vec3 delta = end.subtract(start);
            double length = delta.length();
            if (length < 0.001) continue;
            Vec3 dir = delta.normalize();

            collector.submitCustomGeometry(poseStack, RenderTypes.entityTranslucent(TextureAtlas.LOCATION_BLOCKS), (pose, consumer) -> {
                PoseStack ps = new PoseStack();
                ps.translate(start.x, start.y, start.z);
                Vector3f fromVec = new Vector3f(0, 1, 0);
                Vector3f toVec = new Vector3f((float) dir.x, (float) dir.y, (float) dir.z);
                if (Math.abs(fromVec.dot(toVec)) < 0.9999f) {
                    ps.mulPose(new Quaternionf().rotationTo(fromVec, toVec));
                }
                Matrix4f combined = new Matrix4f(pose.pose()).mul(ps.last().pose());

                for (int i = 0; i < 4; i++) {
                    Matrix4f m = new Matrix4f(combined).rotate(Axis.YP.rotationDegrees(i * 90f));
                    int li = state.lightCoords;
                    v(consumer, m, -radius, 0, -radius, r, g, b, a, u0, v0, ov, li, 0, 0, -1);
                    v(consumer, m,  radius, 0, -radius, r, g, b, a, u1, v0, ov, li, 0, 0, -1);
                    v(consumer, m,  radius, (float) length, -radius, r, g, b, a, u1, v1, ov, li, 0, 0, -1);
                    v(consumer, m, -radius, (float) length, -radius, r, g, b, a, u0, v1, ov, li, 0, 0, -1);
                }
            });
        }
    }

    private static void v(VertexConsumer c, Matrix4f m, float x, float y, float z,
                          float r, float g, float b, float a, float u, float v, int ov, int li,
                          float nx, float ny, float nz) {
        c.addVertex(m, x, y, z).setColor(r, g, b, a).setUv(u, v).setOverlay(ov).setLight(li).setNormal(nx, ny, nz);
    }
}