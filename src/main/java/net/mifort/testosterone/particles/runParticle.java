package net.mifort.testosterone.particles;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.effects.roidRageEffect;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class runParticle extends Particle {

    private static final ResourceLocation WHITE =
            new ResourceLocation(testosterone.MOD_ID, "textures/particle/white.png");

    private static final ParticleRenderType TYPE = new ParticleRenderType() {
        @Override
        public void begin(BufferBuilder bb, TextureManager tm) {
            RenderSystem.depthMask(true);
            RenderSystem.setShaderTexture(0, WHITE);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            bb.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
        }
        @Override
        public void end(Tesselator t) { t.end(); }
        @Override
        public String toString() { return "playerRunParticle"; }
    };

    private final float[] verts;
    private final int vertCount;
    private final float yaw;
    private final boolean swimming;

    private float red;
    private float green;
    private float blue;



    private final PoseStack scratch = new PoseStack();

    public runParticle(ClientLevel lvl, UUID playerUUID, int duration, long time, double x, double y, double z) {
        super(lvl, x, y, z);

        this.hasPhysics = false;
        this.lifetime = duration;

        Player player = lvl.getPlayerByUUID(playerUUID);
        if (player == null) {
            this.verts = new float[0];
            this.vertCount = 0;
            this.yaw = 0;
            this.swimming = false;
            return;
        }

        if (time % 2 == 0) {
            this.red = 246f / 255f;
            this.green = 0f;
            this.blue = 0f;

        } else {
            this.red = 117f / 255f;
            this.green = 245f / 255f;
            this.blue = 80f / 255f;
        }

        PlayerModel<Player> model = new PlayerModel<>(
                Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER),
                false);

        model.hat.visible = false;
        model.jacket.visible = false;
        model.leftSleeve.visible = false;
        model.rightSleeve.visible = false;
        model.leftPants.visible = false;
        model.rightPants.visible = false;

        float pt = Minecraft.getInstance().getFrameTime();
        float limbSwing = player.walkAnimation.position();
        float limbSwingAmount = player.walkAnimation.speed();

        model.attackTime = player.getAttackAnim(pt);
        model.young = player.isBaby();
        model.crouching = player.isCrouching();

        this.swimming = (player.getPose() == Pose.SWIMMING || player.getPersistentData().getBoolean(roidRageEffect.SWIMMING_KEY));
        model.swimAmount = this.swimming ? 1.0F : player.getSwimAmount(pt);

        float headYaw   = player.getYHeadRot() - player.getYRot();
        float headPitch = player.getXRot();

        model.setupAnim(player,
                limbSwing,
                limbSwingAmount,
                player.tickCount + pt,
                headYaw,
                headPitch);

        ModelGrabber grab = new ModelGrabber();
        scratch.pushPose();
        if (swimming) {
            scratch.mulPose(Axis.XP.rotationDegrees(90));
        }



        model.renderToBuffer(scratch, grab,
                LightTexture.FULL_BRIGHT,
                OverlayTexture.NO_OVERLAY,
                0xFF, 0xFF, 0xFF, 0xFF);
        scratch.popPose();

        this.verts = grab.data;
        this.vertCount = grab.vertexCount;
        this.yaw = player.yBodyRot + 180F;
    }

    @Override
    public void render(VertexConsumer out, Camera cam, float pt) {
        if (vertCount == 0 || !ConfigRegistry.RENDER_TRAIL.get()) return;

        Vec3 cp = cam.getPosition();
        float px = (float)(Mth.lerp(pt, xo, x) - cp.x);
        float py = (float)(Mth.lerp(pt, yo, y) - cp.y);
        float pz = (float)(Mth.lerp(pt, zo, z) - cp.z);

        scratch.pushPose();
        float scale = 0.96f;
        float verticalOffset = swimming ? 0.8F * scale : 1.5F * scale;
        scratch.translate(px, py + verticalOffset, pz);
        scratch.scale(-scale, -scale, scale);
        scratch.mulPose(Axis.YP.rotationDegrees(yaw));

        float alpha = 1F - (age + pt) / (lifetime);

        if (alpha < 0) {
            alpha = 0;
        }



        int skyLight = level.getBrightness(LightLayer.SKY, cam.getBlockPosition());
        int blockLight = level.getBrightness(LightLayer.BLOCK, cam.getBlockPosition());

        for (int i = 0; i < vertCount; ++i) {
            int v = i * ModelGrabber.STRIDE;
            out.vertex(scratch.last().pose(),
                            verts[v], verts[v + 1], verts[v + 2])
                    .uv(i == 1 || i == 2 ? 1 : 0, (i & 1) == 0 ? 1 : 0)
                    .color(red, green, blue, alpha * 0.5f)
                    .uv2(LightTexture.pack(skyLight, blockLight))
                    .endVertex();
        }
        scratch.popPose();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return TYPE;
    }

    private static class ModelGrabber implements VertexConsumer {
        static final int STRIDE = 3;
        float[] data = new float[12];
        int pos, vertexCount;
        @Override
        public VertexConsumer vertex(double x, double y, double z) {
            data[pos]   = (float) x;
            data[pos+1] = (float) y;
            data[pos+2] = (float) z;
            return this;
        }
        @Override
        public void endVertex() {
            pos += STRIDE;
            vertexCount++;
            if (pos >= data.length) {
                float[] n = new float[data.length + 12];
                System.arraycopy(data, 0, n, 0, data.length);
                data = n;
            }
        }

        @Override public VertexConsumer color(int r, int g, int b, int a) { return this; }
        @Override public VertexConsumer uv(float u, float v) { return this; }
        @Override public VertexConsumer overlayCoords(int u, int v) { return this; }
        @Override public VertexConsumer uv2(int u, int v) { return this; }
        @Override public VertexConsumer normal(float x, float y, float z) { return this; }
        @Override public void defaultColor(int r, int g, int b, int a) {}
        @Override public void unsetDefaultColor() {}
    }

    public static class Factory implements ParticleProvider<runParticleData> {
        public Factory(SpriteSet spriteSet) {}

        @Override
        public @Nullable Particle createParticle(runParticleData pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new runParticle(pLevel, pType.playerUUID(), pType.duration(), pType.tick(), pX, pY, pZ);
        }
    }
}
