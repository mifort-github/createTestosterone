package net.mifort.testosterone.particles;


import net.mifort.testosterone.testosterone;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = testosterone.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class testosteroneParticlesClientSetup {
    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(testosteroneModParticles.TESTOSTERONE_RUN.get(), runParticle.Factory::new);
        event.registerSpriteSet(testosteroneModParticles.AIR_PASSING.get(), airPassingParticle.Factory::new);
    }
}
