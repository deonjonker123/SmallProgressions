package com.misterd.smallprogressions.client.renderer.ber;

import com.misterd.smallprogressions.blockentity.custom.CopperTankBlockEntity;
import com.misterd.smallprogressions.blockentity.custom.DiamondTankBlockEntity;
import com.misterd.smallprogressions.blockentity.custom.GoldTankBlockEntity;
import com.misterd.smallprogressions.blockentity.custom.IronTankBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.fluid.FluidTintSource;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import org.jspecify.annotations.Nullable;
import org.joml.Matrix4f;

public class TankBlockEntityRenderer<T extends BlockEntity>
        implements BlockEntityRenderer<T, TankBlockEntityRenderer.RenderState> {

    public TankBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    public static class RenderState extends BlockEntityRenderState {
        @Nullable Fluid fluid = null;
        float fillPercentage = 0f;
        int tintColor = -1;
        @Nullable TextureAtlasSprite sprite = null;
    }

    @Override
    public RenderState createRenderState() {
        return new RenderState();
    }

    @Override
    public void extractRenderState(T blockEntity, RenderState state, float partialTick, Vec3 cameraPos, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderState.extractBase(blockEntity, state, crumblingOverlay);
        state.fluid = null;
        state.fillPercentage = 0f;
        state.tintColor = -1;
        state.sprite = null;

        FluidStacksResourceHandler tank = getTank(blockEntity);
        if (tank == null) return;

        FluidResource res = tank.getResource(0);
        if (res.isEmpty()) return;

        int amount = tank.getAmountAsInt(0);
        if (amount <= 0) return;

        Fluid fluid = res.getFluid();
        FluidModel model = Minecraft.getInstance().getModelManager().getFluidStateModelSet().get(fluid.defaultFluidState());
        FluidTintSource tintSource = model.fluidTintSource();

        state.fluid = fluid;
        state.fillPercentage = (float) amount / tank.getCapacityAsInt(0, res);
        state.tintColor = tintSource != null ? tintSource.color(fluid.defaultFluidState()) : -1;
        state.sprite = model.stillMaterial().sprite();
    }

    @Override
    public void submit(RenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        if (state.fluid == null || state.sprite == null) return;

        float minX = 0.025f, minY = 0.0625f, minZ = 0.025f;
        float maxX = 0.975f, maxZ = 0.975f;
        float maxY = minY + (0.9375f - minY) * state.fillPercentage;

        int color = state.tintColor;
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;
        int rawA = (color >> 24) & 0xFF;
        float a = rawA == 0 ? 1f : rawA / 255f;

        TextureAtlasSprite s = state.sprite;
        float u0 = s.getU0(), u1 = s.getU1();
        float v0 = s.getV0(), v1 = s.getV1();
        float uW = u1 - u0, vH = v1 - v0;

        collector.submitCustomGeometry(poseStack, RenderTypes.entityTranslucent(TextureAtlas.LOCATION_BLOCKS), (pose, consumer) -> {
            Matrix4f m = pose.pose();
            int ov = OverlayTexture.NO_OVERLAY;
            int li = state.lightCoords;

            // top
            v(consumer, m, minX, maxY, minZ, r, g, b, a, u0, v0, ov, li, 0, 1, 0);
            v(consumer, m, minX, maxY, maxZ, r, g, b, a, u0, v0 + vH * (maxZ - minZ), ov, li, 0, 1, 0);
            v(consumer, m, maxX, maxY, maxZ, r, g, b, a, u0 + uW * (maxX - minX), v0 + vH * (maxZ - minZ), ov, li, 0, 1, 0);
            v(consumer, m, maxX, maxY, minZ, r, g, b, a, u0 + uW * (maxX - minX), v0, ov, li, 0, 1, 0);
            // bottom
            v(consumer, m, minX, minY, minZ, r, g, b, a, u0, v0, ov, li, 0, -1, 0);
            v(consumer, m, maxX, minY, minZ, r, g, b, a, u0 + uW * (maxX - minX), v0, ov, li, 0, -1, 0);
            v(consumer, m, maxX, minY, maxZ, r, g, b, a, u0 + uW * (maxX - minX), v0 + vH * (maxZ - minZ), ov, li, 0, -1, 0);
            v(consumer, m, minX, minY, maxZ, r, g, b, a, u0, v0 + vH * (maxZ - minZ), ov, li, 0, -1, 0);
            // north
            v(consumer, m, minX, minY, minZ, r, g, b, a, u0, v0 + vH * (1 - (maxY - minY)), ov, li, 0, 0, -1);
            v(consumer, m, minX, maxY, minZ, r, g, b, a, u0, v0 + vH, ov, li, 0, 0, -1);
            v(consumer, m, maxX, maxY, minZ, r, g, b, a, u0 + uW * (maxX - minX), v0 + vH, ov, li, 0, 0, -1);
            v(consumer, m, maxX, minY, minZ, r, g, b, a, u0 + uW * (maxX - minX), v0 + vH * (1 - (maxY - minY)), ov, li, 0, 0, -1);
            // south
            v(consumer, m, minX, minY, maxZ, r, g, b, a, u0, v0 + vH * (1 - (maxY - minY)), ov, li, 0, 0, 1);
            v(consumer, m, maxX, minY, maxZ, r, g, b, a, u0 + uW * (maxX - minX), v0 + vH * (1 - (maxY - minY)), ov, li, 0, 0, 1);
            v(consumer, m, maxX, maxY, maxZ, r, g, b, a, u0 + uW * (maxX - minX), v0 + vH, ov, li, 0, 0, 1);
            v(consumer, m, minX, maxY, maxZ, r, g, b, a, u0, v0 + vH, ov, li, 0, 0, 1);
            // west
            v(consumer, m, minX, minY, minZ, r, g, b, a, u0, v0 + vH * (1 - (maxY - minY)), ov, li, -1, 0, 0);
            v(consumer, m, minX, minY, maxZ, r, g, b, a, u0 + uW * (maxZ - minZ), v0 + vH * (1 - (maxY - minY)), ov, li, -1, 0, 0);
            v(consumer, m, minX, maxY, maxZ, r, g, b, a, u0 + uW * (maxZ - minZ), v0 + vH, ov, li, -1, 0, 0);
            v(consumer, m, minX, maxY, minZ, r, g, b, a, u0, v0 + vH, ov, li, -1, 0, 0);
            // east
            v(consumer, m, maxX, minY, minZ, r, g, b, a, u0, v0 + vH * (1 - (maxY - minY)), ov, li, 1, 0, 0);
            v(consumer, m, maxX, maxY, minZ, r, g, b, a, u0, v0 + vH, ov, li, 1, 0, 0);
            v(consumer, m, maxX, maxY, maxZ, r, g, b, a, u0 + uW * (maxZ - minZ), v0 + vH, ov, li, 1, 0, 0);
            v(consumer, m, maxX, minY, maxZ, r, g, b, a, u0 + uW * (maxZ - minZ), v0 + vH * (1 - (maxY - minY)), ov, li, 1, 0, 0);
        });
    }

    private static void v(VertexConsumer c, Matrix4f m, float x, float y, float z, float r, float g, float b, float a, float u, float v, int ov, int li, float nx, float ny, float nz) {
        c.addVertex(m, x, y, z).setColor(r, g, b, a).setUv(u, v).setOverlay(ov).setLight(li).setNormal(nx, ny, nz);
    }

    @Nullable
    private FluidStacksResourceHandler getTank(T blockEntity) {
        if (blockEntity instanceof CopperTankBlockEntity e) return e.tank;
        if (blockEntity instanceof IronTankBlockEntity e) return e.tank;
        if (blockEntity instanceof GoldTankBlockEntity e) return e.tank;
        if (blockEntity instanceof DiamondTankBlockEntity e) return e.tank;
        return null;
    }
}