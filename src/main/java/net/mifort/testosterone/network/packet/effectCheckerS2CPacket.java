package net.mifort.testosterone.network.packet;

import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.mifort.testosterone.client.Layer.EFFECT_CHECKER_KEY;

public class effectCheckerS2CPacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<effectCheckerS2CPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(testosterone.MOD_ID, "effect_checker_s2c"));

    public static final StreamCodec<FriendlyByteBuf, effectCheckerS2CPacket> STREAM_CODEC = StreamCodec.of(
            (buf, packet) -> buf.writeVarIntArray(packet.data),
            buf -> new effectCheckerS2CPacket(buf.readVarIntArray())
    );

    private final int[] data;

    public effectCheckerS2CPacket(int[] data) {
        this.data = data;
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(effectCheckerS2CPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            Minecraft.getInstance().level.getEntity(packet.data[0])
                    .getPersistentData().putInt(EFFECT_CHECKER_KEY, packet.data[1]);
        });
    }
}