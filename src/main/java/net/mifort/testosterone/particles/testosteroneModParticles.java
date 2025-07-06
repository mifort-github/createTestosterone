package net.mifort.testosterone.particles;

import com.mojang.serialization.Codec;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class testosteroneModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, testosterone.MOD_ID);

    public static final RegistryObject<ParticleType<runParticleData>> TESTOSTERONE_RUN =
            PARTICLES.register("testosterone_run", () -> new ParticleType<>(false, runParticleData.DESERIALIZER) {
                @Override
                public Codec<runParticleData> codec() {
                    return null;
                }
            });

    public static final RegistryObject<ParticleType<airPassingParticleData>> AIR_PASSING =
            PARTICLES.register("air_passing", () -> new ParticleType<>(false, airPassingParticleData.DESERIALIZER) {
                @Override
                public Codec<airPassingParticleData> codec() {
                    return null;
                }
            });
}
