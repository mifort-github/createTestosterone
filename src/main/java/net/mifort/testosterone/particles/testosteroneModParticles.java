package net.mifort.testosterone.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import net.mifort.testosterone.testosterone;

public final class testosteroneModParticles {

	public static final ParticleType<runParticleData> TESTOSTERONE_RUN =
			Registry.register(
					BuiltInRegistries.PARTICLE_TYPE,
					new ResourceLocation(testosterone.MOD_ID, "testosterone_run"),
					FabricParticleTypes.complex(
							false,
							runParticleData.DESERIALIZER
					)
			);

	public static final ParticleType<airPassingParticleData> AIR_PASSING =
			Registry.register(
					BuiltInRegistries.PARTICLE_TYPE,
					new ResourceLocation(testosterone.MOD_ID, "air_passing"),
					FabricParticleTypes.complex(
							false,
							airPassingParticleData.DESERIALIZER
					)
			);

	public static void registerParticles() {

	}
}
