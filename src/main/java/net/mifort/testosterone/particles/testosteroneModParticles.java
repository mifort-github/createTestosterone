package net.mifort.testosterone.particles;

import com.mojang.serialization.MapCodec;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class testosteroneModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, testosterone.MOD_ID);


    public static final DeferredHolder<ParticleType<?>, ParticleType<runParticleData>> TESTOSTERONE_RUN =
            PARTICLES.register("testosterone_run", () ->
                    new ParticleType<>(false) {
                        @Override
                        public MapCodec<runParticleData> codec() {
                            return runParticleData.CODEC;
                        }

                        @Override
                        public StreamCodec<? super RegistryFriendlyByteBuf, runParticleData> streamCodec() {
                            return runParticleData.STREAM_CODEC;
                        }
                    }
            );

    public static final DeferredHolder<ParticleType<?>, ParticleType<airPassingParticleData>> AIR_PASSING =
            PARTICLES.register("air_passing", () ->
                    new ParticleType<>(false) {
                        @Override
                        public MapCodec<airPassingParticleData> codec() {
                            return airPassingParticleData.CODEC;
                        }

                        @Override
                        public StreamCodec<? super RegistryFriendlyByteBuf, airPassingParticleData> streamCodec() {
                            return airPassingParticleData.STREAM_CODEC;
                        }
                    }
            );
}
