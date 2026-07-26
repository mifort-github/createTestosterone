package net.mifort.testosterone.particles;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record airPassingParticleData(UUID playerUUID, int duration) implements ParticleOptions {

    public static final MapCodec<airPassingParticleData> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    UUIDUtil.CODEC.fieldOf("playerUUID").forGetter(airPassingParticleData::playerUUID),
                    Codec.INT.fieldOf("duration").forGetter(airPassingParticleData::duration)
            ).apply(instance, airPassingParticleData::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, airPassingParticleData> STREAM_CODEC =
            StreamCodec.composite(
                    UUIDUtil.STREAM_CODEC, airPassingParticleData::playerUUID,
                    ByteBufCodecs.INT, airPassingParticleData::duration,
                    airPassingParticleData::new
            );

    @Override
    public @NotNull ParticleType<airPassingParticleData> getType() {
        return testosteroneModParticles.AIR_PASSING.get();
    }
}
