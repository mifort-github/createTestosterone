package net.mifort.testosterone.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.mifort.testosterone.client.hudOverlay.*;

public class hudS2CPacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<hudS2CPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("testosterone", "hud_s2c"));

    public static final StreamCodec<FriendlyByteBuf, hudS2CPacket> STREAM_CODEC = StreamCodec.of(
            (buf, packet) -> buf.writeLongArray(packet.data),
            buf -> new hudS2CPacket(buf.readLongArray())
    );

    private final long[] data;

    public hudS2CPacket(long[] data) {
        this.data = data;
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(hudS2CPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            LocalPlayer player = Minecraft.getInstance().player;
            player.getPersistentData().putLong(END_OF_COOLDOWN_TICK_KEY, packet.data[0]);
            player.getPersistentData().putLong(ACTUAL_BEGIN_TICK_KEY, packet.data[1]);
            player.getPersistentData().putLong(BEGIN_TICK_KEY, packet.data[0] - packet.data[1]);
            player.getPersistentData().putLong(DURATION_KEY, packet.data[2]);
        });
    }
}