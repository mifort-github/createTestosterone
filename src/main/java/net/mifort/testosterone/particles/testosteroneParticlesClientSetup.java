package net.mifort.testosterone.particles;


import net.mifort.testosterone.particles.shader.shaderParticle;
import net.mifort.testosterone.particles.shader.shaderParticleRenderer;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

@EventBusSubscriber(modid = testosterone.MOD_ID, value = Dist.CLIENT)
public final class testosteroneParticlesClientSetup {
    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(testosteroneModParticles.TESTOSTERONE_RUN.get(), runParticle.Factory::new);
        event.registerSpriteSet(testosteroneModParticles.AIR_PASSING.get(), airPassingParticle.Factory::new);
        event.registerSpriteSet(testosteroneModParticles.SHADER.get(), shaderParticle.Factory::new);
    }

    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(
                new ShaderInstance(
                        event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(testosterone.MOD_ID, "shader_particle"),
                        com.mojang.blaze3d.vertex.DefaultVertexFormat.PARTICLE
                ),
                shaderParticleRenderer::onShaderLoaded
        );
    }
}
