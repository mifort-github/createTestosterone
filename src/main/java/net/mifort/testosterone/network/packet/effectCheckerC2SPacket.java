package net.mifort.testosterone.network.packet;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.testosterone;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record effectCheckerC2SPacket(int livingEntityID) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<effectCheckerC2SPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(testosterone.MOD_ID, "effect_checker_c2s"));

    public static final StreamCodec<FriendlyByteBuf, effectCheckerC2SPacket> STREAM_CODEC = StreamCodec.of(
            (buf, packet) -> buf.writeInt(packet.livingEntityID()),
            buf -> new effectCheckerC2SPacket(buf.readInt())
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(effectCheckerC2SPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = (ServerPlayer) context.player();

            LivingEntity livingEntity = (LivingEntity) player.level().getEntity(packet.livingEntityID());
            int effectInt;

            boolean hasTestosterone = livingEntity.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT);
            boolean hasRoidRage = livingEntity.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT);

            if (hasTestosterone && hasRoidRage) {
                int testosteroneScore = (livingEntity.getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT).getAmplifier() + 1)
                        * livingEntity.getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT).getDuration();
                int roidRageScore = (livingEntity.getEffect(testosteroneModEffects.ROID_RAGE_EFFECT).getAmplifier() + 1)
                        * livingEntity.getEffect(testosteroneModEffects.ROID_RAGE_EFFECT).getDuration();

                effectInt = (testosteroneScore > roidRageScore) ? 1 : 2;
            } else if (hasTestosterone) {
                effectInt = 1;
            } else if (hasRoidRage) {
                effectInt = 2;
            } else {
                effectInt = 0;
            }

            PacketDistributor.sendToPlayer(player, new effectCheckerS2CPacket(new int[]{packet.livingEntityID(), effectInt}));
        });
    }
}