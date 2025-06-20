package net.mifort.testosterone.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

import java.util.UUID;

public record runParticleData(UUID playerUUID, int duration, long tick) implements ParticleOptions {
    public static final Deserializer<runParticleData> DESERIALIZER = new Deserializer<>() {

        @Override
        public runParticleData fromCommand(ParticleType<runParticleData> pParticleType, StringReader pReader) throws CommandSyntaxException {
            return new runParticleData(UUID.fromString(pReader.readString()), pReader.readInt(), pReader.readLong());
        }

        @Override
        public runParticleData fromNetwork(ParticleType<runParticleData> pParticleType, FriendlyByteBuf pBuffer) {
            return new runParticleData(pBuffer.readUUID(), pBuffer.readInt(), pBuffer.readLong());
        }
    };

    @Override
    public ParticleType<?> getType() {
        return testosteroneModParticles.TESTOSTERONE_RUN.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeUUID(playerUUID);
        pBuffer.writeInt(duration);
        pBuffer.writeLong(tick);
    }

    @Override
    public String writeToString() {
        return playerUUID.toString() + ", " + duration + ", " + tick;
    }
}
