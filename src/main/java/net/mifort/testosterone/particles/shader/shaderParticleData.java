package net.mifort.testosterone.particles.shader;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.mifort.testosterone.particles.testosteroneModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record shaderParticleData(float rotation, int id) implements ParticleOptions {

    public static final StreamCodec<ByteBuf, Vec3> VEC3_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, Vec3::x,
            ByteBufCodecs.DOUBLE, Vec3::y,
            ByteBufCodecs.DOUBLE, Vec3::z,
            Vec3::new
    );

    public static final MapCodec<shaderParticleData> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT
                            .fieldOf("rotation")
                            .forGetter(shaderParticleData::rotation),
                    Codec.INT
                            .fieldOf("id")
                            .forGetter(shaderParticleData::id)
            ).apply(instance, shaderParticleData::new)
    );

    public static final StreamCodec<ByteBuf, shaderParticleData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, shaderParticleData::rotation,
            ByteBufCodecs.INT, shaderParticleData::id,
            shaderParticleData::new
    );

    @Override
    public @NotNull ParticleType<shaderParticleData> getType() {
        return testosteroneModParticles.SHADER.get();
    }
}