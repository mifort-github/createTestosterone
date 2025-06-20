package net.mifort.testosterone.particles;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mifort.testosterone.testosterone;

@Mod.EventBusSubscriber(modid = testosterone.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class testosteroneParticlesClientSetup {
    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent evt) {
        evt.registerSpriteSet(testosteroneModParticles.TESTOSTERONE_RUN.get(), runParticle.Factory::new);
    }
}
