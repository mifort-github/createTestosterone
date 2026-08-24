package net.mifort.testosterone.particles;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public final class testosteroneParticlesClientSetup {

	public static void registerParticleFactories() {
		ParticleFactoryRegistry.getInstance().register(
				testosteroneModParticles.TESTOSTERONE_RUN,
				runParticle.Factory::new
		);

		ParticleFactoryRegistry.getInstance().register(
				testosteroneModParticles.AIR_PASSING,
				airPassingParticle.Factory::new
		);
	}
}
