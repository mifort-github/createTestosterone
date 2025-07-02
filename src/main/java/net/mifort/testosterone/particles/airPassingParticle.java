package net.mifort.testosterone.particles;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mifort.testosterone.config.ConfigRegistry;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;


public class airPassingParticle extends SingleQuadParticle {
    SpriteSet spriteSet;
    float yRot;
    double xRotMul;
    float zMul;
    float xMul;

    protected airPassingParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet spriteSet) {
        super(pLevel, pX, pY, pZ);
        this.spriteSet = spriteSet;

        yRot = Minecraft.getInstance().player.yBodyRot;

        yRot = -(yRot + 90) % 360;
        if (yRot < 0) yRot += 360;

        if (yRot < 90) {
            zMul = 1;
            xMul = 1;
        } else if (yRot < 180) {
            zMul = -1;
            xMul = 1;
        } else if (yRot < 270) {
            zMul = -1;
            xMul = -1;
        } else {
            zMul = 1;
            xMul = -1;
        }

        yRot = (float) Math.toRadians(yRot);

        xRotMul = (Math.asin(Math.sin(yRot - (Math.PI / 2)))) / Math.PI + 0.5;


        this.lifetime = 5;

        xo = x = x + random.nextFloat() - 0.5;
        yo = y = y + 2 * random.nextFloat();
        zo = z = z + random.nextFloat() - 0.5;
    }

    @Override
    public void render(@NotNull VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        Vec3 playerPos = pRenderInfo.getPosition();
        float pX = (float) (Mth.lerp(pPartialTicks, this.xo, this.x) - playerPos.x());
        float pY = (float) (Mth.lerp(pPartialTicks, this.yo, this.y) - playerPos.y());
        float pZ = (float) (Mth.lerp(pPartialTicks, this.zo, this.z) - playerPos.z());

        double xRot = -Math.atan2(pY, pZ * zMul);
        double xRot2 = -Math.atan2(pY, pX * xMul);

        xRot = xRot * (1 - xRotMul);
        xRot2 = xRot2 * xRotMul;

        float a = (float) (this.lifetime - this.age) / this.lifetime;

        this.alpha = a;

        Vector3f[] corners = new Vector3f[]{
                new Vector3f(-0.5F, -1.0F, 0.0F),
                new Vector3f(-0.5F, 1.0F, 0.0F),
                new Vector3f(0.5F, 1.0F, 0.0F),
                new Vector3f(0.5F, -1.0F, 0.0F)};

        for(int i = 0; i < 4; ++i) {
            Vector3f vector3f = corners[i];
            vector3f.mul(1f, a / 30, 0.1f);

            vector3f.rotateX((float) (xRot + xRot2));
            vector3f.rotateY(yRot);

            vector3f.add(pX, pY, pZ);
        }

        float u0 = this.getU0();
        float u1 = this.getU1();
        float v0 = this.getV0();
        float v1 = this.getV1();
        int lightColor = this.getLightColor(pPartialTicks);
        pBuffer.vertex(corners[0].x(), corners[0].y(), corners[0].z()).uv(u1, v1).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(lightColor).endVertex();
        pBuffer.vertex(corners[1].x(), corners[1].y(), corners[1].z()).uv(u1, v0).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(lightColor).endVertex();
        pBuffer.vertex(corners[2].x(), corners[2].y(), corners[2].z()).uv(u0, v0).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(lightColor).endVertex();
        pBuffer.vertex(corners[3].x(), corners[3].y(), corners[3].z()).uv(u0, v1).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(lightColor).endVertex();
    }

    @Override
    protected float getU0() {
        return this.spriteSet.get(this.random).getU0();
    }

    @Override
    protected float getU1() {
        return this.spriteSet.get(this.random).getU1();
    }

    @Override
    protected float getV0() {
        return this.spriteSet.get(this.random).getV0();
    }

    @Override
    protected float getV1() {
        return this.spriteSet.get(this.random).getV1();
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {


        public final SpriteSet spriteset;

        public Factory(SpriteSet spriteSet) {
            this.spriteset = spriteSet;
        }

        @Override
        public Particle createParticle(@NotNull SimpleParticleType type, @NotNull ClientLevel world,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new airPassingParticle(world, x, y, z, spriteset);
        }
    }
}
