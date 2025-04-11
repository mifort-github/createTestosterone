package net.mifort.testosterone.events;

import net.mifort.testosterone.client.hudOverlay;
import net.mifort.testosterone.testosterone;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class overlayRegistry {
    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onRegisterOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("overlay", hudOverlay.HUD_TEMP);
        }
    }
}