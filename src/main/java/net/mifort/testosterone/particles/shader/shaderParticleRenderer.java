package net.mifort.testosterone.particles.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class shaderParticleRenderer {

    private static final Logger LOGGER = LoggerFactory.getLogger(shaderParticleRenderer.class);

    @Nullable
    public static ShaderInstance shaderInstance;

    public static void onShaderLoaded(ShaderInstance shader) {
        shaderInstance = shader;
        LOGGER.info("[shaderParticleRenderer] Shader loaded: '{}'", shader.getName());
    }

    public static void beginBatch(TextureManager textureManager) {
        if (shaderInstance == null) {
            LOGGER.error("[shaderParticleRenderer] shaderInstance is NULL — falling back to default particle shader. " +
                    "Check assets/testosterone/shaders/core/shader_particle.json exists.");
            RenderSystem.setShader(net.minecraft.client.renderer.GameRenderer::getParticleShader);
        } else {
            RenderSystem.setShader(() -> shaderInstance);
        }

        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);

        if (shaderInstance != null) {
            var timeUniform = shaderInstance.getUniform("GameTime");
            if (timeUniform == null) {
                LOGGER.warn("[shaderParticleRenderer] 'GameTime' uniform not found in shader.");
            } else {
                float t = Minecraft.getInstance().level != null
                        ? Minecraft.getInstance().level.getGameTime() / 20f : 0f;
                timeUniform.set(t);
            }
        }
    }

    public static void endBatch() {
        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
    }
}