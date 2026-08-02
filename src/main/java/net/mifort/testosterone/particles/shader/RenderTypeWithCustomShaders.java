package net.mifort.testosterone.particles.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderTypeWithCustomShaders implements ParticleRenderType {

    public static final RenderTypeWithCustomShaders INSTANCE = new RenderTypeWithCustomShaders();
    public ShaderInstance shader;

    @Override
    public BufferBuilder begin(Tesselator tesselator, TextureManager textureManager) {

        if (shaderParticleRenderer.shaderInstance != null) {
            RenderSystem.setShader(() -> shaderParticleRenderer.shaderInstance);
            shader = shaderParticleRenderer.shaderInstance;
        } else {
            RenderSystem.setShader(GameRenderer::getParticleShader);
            shader = GameRenderer.getParticleShader();
        }

        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        RenderSystem.depthMask(true);

        RenderSystem.disableCull();

        if (shaderParticleRenderer.shaderInstance != null) {
            var timeUniform = shaderParticleRenderer.shaderInstance.getUniform("GameTime");
            if (timeUniform != null) {
                float t = net.minecraft.client.Minecraft.getInstance().level != null
                        ? net.minecraft.client.Minecraft.getInstance().level.getGameTime() / 20f : 0f;
                timeUniform.set(t);
            }

            var cameraPos = shaderParticleRenderer.shaderInstance.getUniform("cameraPos");
            if (cameraPos != null) {
                Vec3 cam = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
                cameraPos.set(cam.toVector3f());
            }
        }

        return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
    }

    @Override
    public String toString() {
        return "testosterone:shader_particle";
    }
}
