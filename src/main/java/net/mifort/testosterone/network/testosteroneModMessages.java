package net.mifort.testosterone.network;

import net.mifort.testosterone.network.packet.effectCheckerC2SPacket;
import net.mifort.testosterone.network.packet.effectCheckerS2CPacket;
import net.mifort.testosterone.network.packet.hudS2CPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static net.mifort.testosterone.testosterone.MOD_ID;

@EventBusSubscriber(modid = MOD_ID)
public class testosteroneModMessages {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1.0");

        // Client -> Server
        registrar.playToServer(
                effectCheckerC2SPacket.TYPE,
                effectCheckerC2SPacket.STREAM_CODEC,
                effectCheckerC2SPacket::handle
        );

        // Server -> Client
        registrar.playToClient(
                effectCheckerS2CPacket.TYPE,
                effectCheckerS2CPacket.STREAM_CODEC,
                effectCheckerS2CPacket::handle
        );

        registrar.playToClient(
                hudS2CPacket.TYPE,
                hudS2CPacket.STREAM_CODEC,
                hudS2CPacket::handle
        );
    }
}