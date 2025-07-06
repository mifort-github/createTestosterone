package net.mifort.testosterone.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record airPassingParticleData(UUID playerUUID) implements ParticleOptions {
    public static final Deserializer<airPassingParticleData> DESERIALIZER = new Deserializer<>() {

        @Override
        public @NotNull airPassingParticleData fromCommand(@NotNull ParticleType<airPassingParticleData> pParticleType, StringReader pReader) throws CommandSyntaxException {
            return new airPassingParticleData(UUID.fromString(pReader.getString()));
        }

        @Override
        public @NotNull airPassingParticleData fromNetwork(@NotNull ParticleType<airPassingParticleData> pParticleType, FriendlyByteBuf pBuffer) {
            return new airPassingParticleData(pBuffer.readUUID());
        }
    };
    
    @Override
    public @NotNull ParticleType<?> getType() {
        return testosteroneModParticles.AIR_PASSING.get();
    }

    @Override
    public void writeToNetwork(@NotNull FriendlyByteBuf pBuffer) {
        pBuffer.writeUUID(playerUUID);
    }

    @Override
    public @NotNull String writeToString() {
        return playerUUID.toString();
    }
}
