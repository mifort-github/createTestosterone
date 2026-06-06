package net.mifort.testosterone.particles;


import net.mifort.testosterone.testosterone;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = testosterone.MOD_ID, value = Dist.CLIENT)
public final class testosteroneParticlesClientSetup {
    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(testosteroneModParticles.TESTOSTERONE_RUN.get(), runParticle.Factory::new);
        event.registerSpriteSet(testosteroneModParticles.AIR_PASSING.get(), airPassingParticle.Factory::new);
    }
}
