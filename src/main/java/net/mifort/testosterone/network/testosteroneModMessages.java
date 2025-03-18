package net.mifort.testosterone.network;

import net.mifort.testosterone.network.packet.effectCheckerC2SPacket;
import net.mifort.testosterone.network.packet.effectCheckerS2CPacket;
import net.mifort.testosterone.network.packet.hudS2CPacket;
import net.mifort.testosterone.testosterone;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class testosteroneModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(testosterone.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;


        net.messageBuilder(effectCheckerC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(effectCheckerC2SPacket::new)
                .encoder(effectCheckerC2SPacket::toBytes)
                .consumerMainThread(effectCheckerC2SPacket::handle)
                .add();

        net.messageBuilder(effectCheckerS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(effectCheckerS2CPacket::new)
                .encoder(effectCheckerS2CPacket::toBytes)
                .consumerMainThread(effectCheckerS2CPacket::handle)
                .add();


        net.messageBuilder(hudS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(hudS2CPacket::new)
                .encoder(hudS2CPacket::toBytes)
                .consumerMainThread(hudS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
