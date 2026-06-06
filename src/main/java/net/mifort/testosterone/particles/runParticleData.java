package net.mifort.testosterone.particles;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.UUID;

public record runParticleData(UUID playerUUID, int duration, long tick) implements ParticleOptions {

    public static final MapCodec<runParticleData> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    UUIDUtil.CODEC.fieldOf("playerUUID").forGetter(runParticleData::playerUUID),
                    com.mojang.serialization.Codec.INT.fieldOf("duration").forGetter(runParticleData::duration),
                    com.mojang.serialization.Codec.LONG.fieldOf("tick").forGetter(runParticleData::tick)
            ).apply(instance, runParticleData::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, runParticleData> STREAM_CODEC =
            StreamCodec.composite(
                    UUIDUtil.STREAM_CODEC, runParticleData::playerUUID,
                    ByteBufCodecs.INT, runParticleData::duration,
                    ByteBufCodecs.VAR_LONG, runParticleData::tick,
                    runParticleData::new
            );

    @Override
    public ParticleType<runParticleData> getType() {
        return testosteroneModParticles.TESTOSTERONE_RUN.get();
    }
}